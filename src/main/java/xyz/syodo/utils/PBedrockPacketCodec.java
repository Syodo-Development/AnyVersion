package xyz.syodo.utils;

import cn.nukkit.block.BlockAir;
import cn.nukkit.block.BlockState;
import cn.nukkit.network.connection.netty.BedrockPacketWrapper;
import cn.nukkit.network.connection.netty.codec.packet.BedrockPacketCodec;
import cn.nukkit.network.connection.netty.codec.packet.BedrockPacketCodec_v3;
import cn.nukkit.network.connection.util.HandleByteBuf;
import cn.nukkit.network.protocol.AvailableCommandsPacket;
import cn.nukkit.network.protocol.DataPacket;
import cn.nukkit.registry.Registries;
import cn.nukkit.utils.ByteBufVarInt;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import lombok.RequiredArgsConstructor;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.nbt.util.VarInts;
import org.cloudburstmc.protocol.bedrock.data.definitions.SimpleBlockDefinition;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacket;
import org.cloudburstmc.protocol.bedrock.packet.InventoryTransactionPacket;
import xyz.syodo.manager.ProtocolPlayer;

import java.util.List;

@RequiredArgsConstructor
public class PBedrockPacketCodec extends BedrockPacketCodec_v3 {

    public static final String NAME = "bedrock-packet-codec-protocolized";
    private static final InternalLogger log = InternalLoggerFactory.getInstance(BedrockPacketCodec.class);

    private final ProtocolPlayer protocolPlayer;

    @Override
    protected final void encode(ChannelHandlerContext ctx, BedrockPacketWrapper msg, List<Object> out) throws Exception {
        if(msg.getPacket() instanceof AvailableCommandsPacket) {
            super.encode(ctx, msg, out);
            return;
        }
        if (msg.getPacketBuffer() != null) {
            // We have a pre-encoded packet buffer, just use that.
            out.add(msg.retain());
        } else {
            ByteBuf buf = Unpooled.buffer();
            try {
                DataPacket packet = msg.getPacket();
                msg.setPacketId(packet.pid());
                packet.encode(HandleByteBuf.of(buf));
                ProtocolVersion current = ProtocolVersion.getCurrent();
                BedrockPacket decoded = current.codec().tryDecode(current.helper(), buf, packet.pid());
                correctPacket(decoded);
                ProtocolVersion protocol = protocolPlayer.getVersion();
                HandleByteBuf protocolizedByteBuf = HandleByteBuf.of(Unpooled.buffer());
                encodeHeader(protocolizedByteBuf, msg, protocol.codec().getRaknetProtocolVersion());
                protocol.codec().tryEncode(protocol.helper(), protocolizedByteBuf, decoded);
                msg.setPacketBuffer(protocolizedByteBuf.retain());
                out.add(msg.retain());
            } catch (Throwable t) {
                log.error("Error encoding packet {}", msg.getPacket(), t);
            } finally {
                buf.release();
            }
        }
    }

    @Override
    protected final void decode(ChannelHandlerContext ctx, ByteBuf msg, List<Object> out) throws Exception {
        BedrockPacketWrapper wrapper = new BedrockPacketWrapper();
        wrapper.setPacketBuffer(msg.retainedSlice());
        try {
            ProtocolVersion protocol = protocolPlayer.getVersion();
            int index = msg.readerIndex();
            this.decodeHeader(msg, wrapper, protocol.codec().getRaknetProtocolVersion());
            wrapper.setHeaderLength(msg.readerIndex() - index);
            DataPacket packet = Registries.PACKET.get(wrapper.getPacketId());
            if (packet == null) {
                log.info("Failed to decode packet for packetId {}", wrapper.getPacketId());
                return;
            }
            HandleByteBuf protocolizedByteBuf = HandleByteBuf.of(Unpooled.buffer());
            BedrockPacket protocolizedPacket = protocol.codec().tryDecode(protocol.helper(), msg, wrapper.getPacketId());
            correctPacket(protocolizedPacket);
            ProtocolVersion current = ProtocolVersion.getCurrent();
            current.codec().tryEncode(current.helper(), protocolizedByteBuf, protocolizedPacket);
            packet.decode(HandleByteBuf.of(protocolizedByteBuf));
            wrapper.setPacket(packet);
            out.add(wrapper.retain());
        } catch (Throwable t) {
            log.info("Failed to decode packet", t);
            throw t;
        } finally {
            wrapper.release();
        }
    }

    private void correctPacket(BedrockPacket packet) {
        if(packet instanceof InventoryTransactionPacket transactionPacket) {
            if(transactionPacket.getBlockDefinition() == null) {
                BlockState AIR = BlockAir.STATE;
                transactionPacket.setBlockDefinition(new SimpleBlockDefinition(AIR.getIdentifier(), AIR.blockStateHash(), NbtMap.EMPTY));
            }
        }
    }

    public void encodeHeader(ByteBuf buf, BedrockPacketWrapper msg, int raknet) {
        switch (raknet) {
            case 11:
            case 10:
            case 9: // Merged & Varint-ified
                int header = 0;
                header |= msg.getPacketId() & 1023;
                header |= (msg.getSenderSubClientId() & 3) << 10;
                header |= (msg.getTargetSubClientId() & 3) << 12;
                ByteBufVarInt.writeUnsignedInt(buf, header);
                break;
            case 8: // Split-screen support
                buf.writeByte(msg.getPacketId());
                buf.writeByte(msg.getSenderSubClientId());
                buf.writeByte(msg.getTargetSubClientId());
                break;
            case 7: // Single byte packet ID
                buf.writeByte(msg.getPacketId());
                break;
            default:
                throw new UnsupportedOperationException("Unsupported RakNet protocol version: " + raknet);
        }
    }

    public void decodeHeader(ByteBuf buf, BedrockPacketWrapper msg, int raknet) {
        switch (raknet) {
            case 11:
            case 10:
            case 9: // Merged & Varint-ified
                int header = ByteBufVarInt.readUnsignedInt(buf);
                msg.setPacketId(header & 0x3ff);
                msg.setSenderSubClientId((header >> 10) & 3);
                msg.setTargetSubClientId((header >> 12) & 3);
                break;
            case 8: // Split-screen support
                msg.setPacketId(buf.readUnsignedByte());
                msg.setSenderSubClientId(buf.readUnsignedByte());
                msg.setTargetSubClientId(buf.readUnsignedByte());
                break;
            case 7: // Single byte packet ID
                msg.setPacketId(buf.readUnsignedByte());
                break;
            default:
                throw new UnsupportedOperationException("Unsupported RakNet protocol version: " + raknet);
        }
    }
}
