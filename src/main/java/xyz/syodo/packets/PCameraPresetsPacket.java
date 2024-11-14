package xyz.syodo.packets;

import cn.nukkit.camera.data.CameraPreset;
import cn.nukkit.network.connection.util.HandleByteBuf;
import cn.nukkit.network.protocol.CameraPresetsPacket;
import cn.nukkit.network.protocol.DisconnectPacket;
import cn.nukkit.network.protocol.types.DisconnectFailReason;
import xyz.syodo.manager.ProtocolPlayer;
import xyz.syodo.utils.ProtocolVersion;

import java.lang.reflect.Field;

public class PCameraPresetsPacket extends CameraPresetsPacket implements ProtocolizedPacket {

    protected ProtocolPlayer protocolPlayer;

    @Override
    public void writePreset(HandleByteBuf byteBuf, CameraPreset preset) {
        byteBuf.writeString(preset.getIdentifier());
        byteBuf.writeString(preset.getInheritFrom());
        byteBuf.writeNotNull(preset.getPos(), (v) -> byteBuf.writeFloatLE(v.getX()));
        byteBuf.writeNotNull(preset.getPos(), (v) -> byteBuf.writeFloatLE(v.getY()));
        byteBuf.writeNotNull(preset.getPos(), (v) -> byteBuf.writeFloatLE(v.getZ()));
        byteBuf.writeNotNull(preset.getPitch(), byteBuf::writeFloatLE);
        byteBuf.writeNotNull(preset.getYaw(), byteBuf::writeFloatLE);
        if (protocolPlayer.protocol() >= ProtocolVersion.MINECRAFT_PE_1_21_20.protocol()) {
            if (protocolPlayer.protocol() >= ProtocolVersion.MINECRAFT_PE_1_21_30.protocol()) {
                byteBuf.writeNotNull(preset.getRotationSpeed(), byteBuf::writeFloatLE);
                byteBuf.writeOptional(preset.getSnapToTarget(), byteBuf::writeBoolean);
                if (protocolPlayer.protocol() >= ProtocolVersion.MINECRAFT_PE_1_21_40.protocol()) {
                    byteBuf.writeNotNull(preset.getHorizontalRotationLimit(), byteBuf::writeVector2f);
                    byteBuf.writeNotNull(preset.getVerticalRotationLimit(), byteBuf::writeVector2f);
                    byteBuf.writeOptional(preset.getContinueTargeting(), byteBuf::writeBoolean);
                }
            }
            byteBuf.writeNotNull(preset.getViewOffset(), byteBuf::writeVector2f);
            if (protocolPlayer.protocol() >= ProtocolVersion.MINECRAFT_PE_1_21_30.protocol()) {
                byteBuf.writeNotNull(preset.getEntityOffset(), byteBuf::writeVector3f);
            }
            byteBuf.writeNotNull(preset.getRadius(), byteBuf::writeFloatLE);
        }
        byteBuf.writeNotNull(preset.getListener(), (l) -> byteBuf.writeByte((byte) l.ordinal()));
        byteBuf.writeOptional(preset.getPlayEffect(), byteBuf::writeBoolean);
        if (protocolPlayer.protocol() >= ProtocolVersion.MINECRAFT_PE_1_21_40.protocol()) {
            byteBuf.writeOptional(preset.getAlignTargetAndCameraForward(), byteBuf::writeBoolean);
        }
    }

    @Override
    public void setPlayer(ProtocolPlayer player) {
        this.protocolPlayer = player;
    }
}
