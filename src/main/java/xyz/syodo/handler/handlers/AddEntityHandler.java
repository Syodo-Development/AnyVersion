package xyz.syodo.handler.handlers;

import org.cloudburstmc.protocol.bedrock.packet.AddEntityPacket;
import xyz.syodo.handler.PacketHandler;
import xyz.syodo.manager.ProtocolPlayer;
import xyz.syodo.registries.Registries;

public class AddEntityHandler extends PacketHandler<AddEntityPacket> {

    @Override
    public void handle(ProtocolPlayer player, AddEntityPacket packet) {
        Registries.ENTITY.transform(player.getVersion(), packet);
    }

}
