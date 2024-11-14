package xyz.syodo.packets;

import cn.nukkit.camera.data.CameraPreset;
import cn.nukkit.network.connection.util.HandleByteBuf;
import cn.nukkit.network.protocol.CameraPresetsPacket;
import cn.nukkit.network.protocol.SetEntityLinkPacket;
import xyz.syodo.manager.ProtocolPlayer;
import xyz.syodo.utils.ProtocolVersion;

public class PSetEntityLinkPacket extends SetEntityLinkPacket implements ProtocolizedPacket {

    protected ProtocolPlayer protocolPlayer;

    @Override
    public void encode(HandleByteBuf byteBuf) {
        byteBuf.writeEntityUniqueId(this.vehicleUniqueId);
        byteBuf.writeEntityUniqueId(this.riderUniqueId);
        byteBuf.writeByte((byte)this.type.ordinal());
        byteBuf.writeByte(this.immediate);
        byteBuf.writeBoolean(this.riderInitiated);
        if(protocolPlayer.protocol() >= ProtocolVersion.MINECRAFT_PE_1_21_20.protocol()) {
            byteBuf.writeFloatLE(this.vehicleAngularVelocity);
        }
    }

    @Override
    public void setPlayer(ProtocolPlayer player) {
        this.protocolPlayer = player;
    }

}
