package xyz.syodo.handler;

import lombok.RequiredArgsConstructor;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacket;
import xyz.syodo.manager.ProtocolPlayer;

import java.lang.reflect.ParameterizedType;

@RequiredArgsConstructor
public abstract class PacketHandler<E extends BedrockPacket> {

    public abstract void handle(ProtocolPlayer player, E packet);

    public Class getType() {
        return (Class<BedrockPacket>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];
    }
}
