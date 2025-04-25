package xyz.syodo.utils;

import cn.nukkit.network.connection.netty.BedrockPacketWrapper;
import cn.nukkit.network.connection.netty.codec.packet.BedrockPacketCodec_v3;
import cn.nukkit.network.connection.util.HandleByteBuf;
import cn.nukkit.network.protocol.DataPacket;
import cn.nukkit.registry.Registries;
import cn.nukkit.utils.ByteBufVarInt;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.cloudburstmc.protocol.bedrock.packet.*;
import xyz.syodo.manager.ProtocolPlayer;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class PBedrockPacketCodec extends BedrockPacketCodec_v3 {

    public static final String NAME = "bedrock-packet-codec-protocolized";
    @Getter
    private final ProtocolPlayer protocolPlayer;

    @Override
    protected final void encode(ChannelHandlerContext ctx, BedrockPacketWrapper msg, List<Object> out) {
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
                ProtocolVersion protocol = protocolPlayer.getVersion();
                BedrockPacket decoded = current.codec().tryDecode(current.helper(), buf, packet.pid());
                xyz.syodo.registries.Registries.PACKETHANDLER.handlePacket(protocol, decoded);
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
    protected final void decode(ChannelHandlerContext ctx, ByteBuf msg, List<Object> out) {
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
            ProtocolVersion current = ProtocolVersion.getCurrent();
            if(current.codec().getPacketDefinition(packet.pid()) == null) {
                packet.decode(HandleByteBuf.of(msg));
                wrapper.setPacket(packet);
                out.add(wrapper.retain());
                return;
            }
            HandleByteBuf protocolizedByteBuf = HandleByteBuf.of(Unpooled.buffer());
            BedrockPacket protocolizedPacket = protocol.codec().tryDecode(protocol.helper(), msg, wrapper.getPacketId());
            xyz.syodo.registries.Registries.PACKETHANDLER.handlePacket(current, protocolizedPacket);
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
