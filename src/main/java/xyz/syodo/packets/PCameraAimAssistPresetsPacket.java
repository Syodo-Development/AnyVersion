package xyz.syodo.packets;

import cn.nukkit.network.connection.util.HandleByteBuf;
import cn.nukkit.network.protocol.CameraAimAssistPacket;
import cn.nukkit.network.protocol.CameraAimAssistPresetsPacket;
import xyz.syodo.manager.ProtocolPlayer;
import xyz.syodo.utils.ProtocolVersion;

public class PCameraAimAssistPresetsPacket extends CameraAimAssistPresetsPacket implements ProtocolizedPacket {

    protected ProtocolPlayer protocolPlayer;

    @Override
    public void setPlayer(ProtocolPlayer player) {
        this.protocolPlayer = player;
    }

    @Override
    public ProtocolVersion getMinProtocolVersion() {
        return ProtocolVersion.MINECRAFT_PE_1_21_50;
    }
}
