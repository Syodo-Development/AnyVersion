package xyz.syodo.handler.handlers;

import org.cloudburstmc.protocol.bedrock.data.inventory.ContainerType;
import org.cloudburstmc.protocol.bedrock.packet.ContainerClosePacket;
import xyz.syodo.handler.PacketHandler;
import xyz.syodo.manager.ProtocolPlayer;

public class ContainerCloseHandler extends PacketHandler<ContainerClosePacket> {

    @Override
    public void handle(ProtocolPlayer player, ContainerClosePacket packet) {
        if(packet.getType() == null) {
            packet.setType(ContainerType.from(player.player().getPlayer().getWindowById(packet.getId()).getType().getNetworkType()));
        }
    }

}
