package org.powernukkitx.anyversion.handler.handlers;

import org.cloudburstmc.protocol.bedrock.data.inventory.ContainerType;
import org.cloudburstmc.protocol.bedrock.packet.ContainerClosePacket;
import org.powernukkitx.anyversion.handler.PacketHandler;
import org.powernukkitx.anyversion.manager.ProtocolPlayer;

public class ContainerCloseHandler extends PacketHandler<ContainerClosePacket> {

    @Override
    public void handle(ProtocolPlayer player, ContainerClosePacket packet) {
        if(packet.getType() == null) {
            packet.setType(ContainerType.from(player.player().getPlayer().getWindowById(packet.getId()).getType().getNetworkType()));
        }
    }

}
