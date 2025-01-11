package xyz.syodo.utils;

import cn.nukkit.network.connection.netty.BedrockPacketWrapper;
import cn.nukkit.network.connection.netty.codec.packet.BedrockPacketCodec;
import cn.nukkit.network.connection.netty.codec.packet.BedrockPacketCodec_v3;
import cn.nukkit.network.connection.util.HandleByteBuf;
import cn.nukkit.network.protocol.DataPacket;
import cn.nukkit.registry.Registries;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import lombok.RequiredArgsConstructor;
import xyz.syodo.manager.PacketManager;
import xyz.syodo.manager.ProtocolPlayer;
import xyz.syodo.packets.ProtocolizedPacket;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class PBedrockPacketCodec extends BedrockPacketCodec_v3 {

    public static final String NAME = "bedrock-packet-codec-protocolized";
    private static final InternalLogger log = InternalLoggerFactory.getInstance(BedrockPacketCodec.class);

    private final ProtocolPlayer protocolPlayer;

    @Override
    protected final void encode(ChannelHandlerContext ctx, BedrockPacketWrapper msg, List<Object> out) throws Exception {
        if (msg.getPacketBuffer() != null && msg.getPacket() == null) {
            out.add(msg.retain());
        } else {
            ByteBuf buf = ctx.alloc().buffer(128);
            try {
                DataPacket packet = msg.getPacket();
                msg.setPacketId(packet.pid());
                encodeHeader(buf, msg);
                Optional<Class<? extends ProtocolizedPacket>> optionalProtocolizedPacket = PacketManager.get(packet);
                if(optionalProtocolizedPacket.isPresent()) {
                    ProtocolizedPacket protocolizedPacket = optionalProtocolizedPacket.get().getConstructor().newInstance();
                    if(protocolPlayer.protocol() < protocolizedPacket.getMinProtocolVersion().protocol()) return;
                    if(protocolizedPacket instanceof DataPacket dataPacket) {
                        protocolizedPacket.setPlayer(protocolPlayer);
                        protocolizedPacket.copyPacketContent(packet);
                        packet = dataPacket;
                    }
                }

                HandleByteBuf byteBuf = HandleByteBuf.of(buf);
                packet.encode(byteBuf);
                msg.setPacketBuffer(buf.retain());
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
            int index = msg.readerIndex();
            this.decodeHeader(msg, wrapper);
            wrapper.setHeaderLength(msg.readerIndex() - index);
            DataPacket packet = Registries.PACKET.get(wrapper.getPacketId());
            Optional<Class<? extends ProtocolizedPacket>> optionalProtocolizedPacket = PacketManager.get(packet);
            if(optionalProtocolizedPacket.isPresent()) {
                ProtocolizedPacket protocolizedPacket = optionalProtocolizedPacket.get().getConstructor().newInstance();
                if(protocolizedPacket instanceof DataPacket dataPacket) {
                    protocolizedPacket.setPlayer(protocolPlayer);
                    packet = dataPacket;
                }
            }
            if (packet == null) {
                log.info("Failed to decode packet for packetId {}", wrapper.getPacketId());
                return;
            }
            packet.decode(HandleByteBuf.of(msg));
            wrapper.setPacket(packet);
            out.add(wrapper.retain());
        } catch (Throwable t) {
            log.info("Failed to decode packet", t);
            throw t;
        } finally {
            wrapper.release();
        }
    }

}
