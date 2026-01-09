package org.powernukkitx.anyversion.processors;

import cn.nukkit.Player;
import cn.nukkit.PlayerHandle;
import cn.nukkit.network.process.processor.EmoteProcessor;
import cn.nukkit.network.protocol.EmotePacket;
import cn.nukkit.network.protocol.ProtocolInfo;
import cn.nukkit.utils.UUIDValidator;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.powernukkitx.anyversion.manager.ProtocolManager;
import org.powernukkitx.anyversion.manager.ProtocolPlayer;
import org.powernukkitx.anyversion.packets.PEmotePacket;

@Slf4j
public class PEmoteProcessor extends EmoteProcessor {

    @Override
    public void handle(@NotNull PlayerHandle playerHandle, @NotNull EmotePacket pk) {
        if (!playerHandle.player.spawned) {
            return;
        }
        if (pk.runtimeId != playerHandle.player.getId()) {
            log.warn("{} sent EmotePacket with invalid entity id: {} != {}", playerHandle.getUsername(), pk.runtimeId, playerHandle.player.getId());
            return;
        }
        if(!UUIDValidator.isValidUUID(pk.emoteID)) {
            log.warn("{} sent EmotePacket with invalid emoteId: {}", playerHandle.getUsername(), pk.emoteID);
            return;
        }

        for (Player viewer : playerHandle.player.getViewers().values()) {
            PEmotePacket protocolizedPacket;
            if(pk instanceof PEmotePacket packet) {
                protocolizedPacket = packet.clone();
            } else {
                protocolizedPacket = new PEmotePacket();
                protocolizedPacket.copyPacketContent(pk);
            }
            ProtocolPlayer protocolPlayer = ProtocolManager.get(viewer);
            if(protocolPlayer == null) protocolPlayer = new ProtocolPlayer(null, ProtocolInfo.CURRENT_PROTOCOL);
            protocolizedPacket.setPlayer(protocolPlayer);
            viewer.dataPacket(protocolizedPacket);
        }
    }

}
