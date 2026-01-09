package org.powernukkitx.anyversion.manager;

import cn.nukkit.network.connection.BedrockSession;
import org.powernukkitx.anyversion.utils.ProtocolVersion;

public record ProtocolPlayer(BedrockSession player, int protocol) {

    public ProtocolVersion getVersion() {
        return ProtocolVersion.get(protocol);
    }

}
