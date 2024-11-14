package xyz.syodo.manager;

import cn.nukkit.network.connection.BedrockSession;

public record ProtocolPlayer(BedrockSession player, int protocol) {
}
