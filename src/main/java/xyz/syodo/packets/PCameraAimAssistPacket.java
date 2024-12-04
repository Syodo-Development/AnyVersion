package xyz.syodo.packets;

import cn.nukkit.network.connection.util.HandleByteBuf;
import cn.nukkit.network.protocol.CameraAimAssistPacket;
import xyz.syodo.manager.ProtocolPlayer;
import xyz.syodo.utils.ProtocolVersion;

public class PCameraAimAssistPacket extends CameraAimAssistPacket implements ProtocolizedPacket {

    protected ProtocolPlayer protocolPlayer;

    @Override
    public void decode(HandleByteBuf byteBuf) {
        if (protocolPlayer.protocol() >= ProtocolVersion.MINECRAFT_PE_1_21_50.protocol()) {
            this.setPresetId(byteBuf.readString());
        } else this.setPresetId("");
        this.setViewAngle(byteBuf.readVector2f());
        this.setDistance(byteBuf.readFloatLE());
        this.setTargetMode(CameraAimAssistPacket.TargetMode.values()[byteBuf.readUnsignedByte()]);
        this.setAction(CameraAimAssistPacket.Action.values()[byteBuf.readUnsignedByte()]);
    }

    @Override
    public void encode(HandleByteBuf byteBuf) {
        if (protocolPlayer.protocol() >= ProtocolVersion.MINECRAFT_PE_1_21_50.protocol()) {
            byteBuf.writeString(this.getPresetId());
        }
        byteBuf.writeVector2f(this.getViewAngle());
        byteBuf.writeFloatLE(this.getDistance());
        byteBuf.writeByte(this.getTargetMode().ordinal());
        byteBuf.writeByte(this.getAction().ordinal());
    }

    @Override
    public void setPlayer(ProtocolPlayer player) {
        this.protocolPlayer = player;
    }
}
