package org.powernukkitx.anyversion.handler.handlers;

import org.cloudburstmc.protocol.bedrock.packet.AddEntityPacket;
import org.powernukkitx.anyversion.handler.PacketHandler;
import org.powernukkitx.anyversion.manager.ProtocolPlayer;
import org.powernukkitx.anyversion.registries.Registries;
import org.powernukkitx.anyversion.utils.ProtocolVersion;

public class AddEntityHandler extends PacketHandler<AddEntityPacket> {

    @Override
    public void handle(ProtocolPlayer player, AddEntityPacket packet) {
        ProtocolVersion version = player.getVersion();
        Registries.ENTITY.transform(version, packet);
        SetEntityDataHandler.fixEntityFlags(version, packet.getMetadata());
    }

}
