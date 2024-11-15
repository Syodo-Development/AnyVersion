package xyz.syodo.manager;

import cn.nukkit.network.connection.BedrockSession;
import xyz.syodo.utils.ProtocolVersion;

public record ProtocolPlayer(BedrockSession player, int protocol) {

    public ProtocolVersion getVersion() {
        return ProtocolVersion.get(protocol);
    }

}
