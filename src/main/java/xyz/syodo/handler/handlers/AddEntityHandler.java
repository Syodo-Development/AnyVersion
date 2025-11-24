package xyz.syodo.handler.handlers;

import org.cloudburstmc.protocol.bedrock.packet.AddEntityPacket;
import xyz.syodo.handler.PacketHandler;
import xyz.syodo.manager.ProtocolPlayer;
import xyz.syodo.registries.Registries;
import xyz.syodo.utils.ProtocolVersion;

public class AddEntityHandler extends PacketHandler<AddEntityPacket> {

    @Override
    public void handle(ProtocolPlayer player, AddEntityPacket packet) {
        ProtocolVersion version = player.getVersion();
        Registries.ENTITY.transform(version, packet);
        SetEntityDataHandler.fixEntityFlags(version, packet.getMetadata());
    }

}
