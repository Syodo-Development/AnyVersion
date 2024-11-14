package xyz.syodo.packets;

import cn.nukkit.camera.instruction.impl.TargetInstruction;
import cn.nukkit.network.connection.util.HandleByteBuf;
import cn.nukkit.network.protocol.CameraInstructionPacket;
import cn.nukkit.utils.OptionalBoolean;
import xyz.syodo.manager.ProtocolPlayer;
import xyz.syodo.utils.ProtocolVersion;

import java.lang.reflect.Field;

public class PCameraInstructionPacket extends CameraInstructionPacket implements ProtocolizedPacket {

    protected ProtocolPlayer protocolPlayer;

    @Override
    public void encode(HandleByteBuf byteBuf) {
        byteBuf.writeNotNull(setInstruction, (s) -> {
            byteBuf.writeIntLE(s.getPreset().getId());
            byteBuf.writeNotNull(s.getEase(), e -> this.writeEase(byteBuf, e));
            byteBuf.writeNotNull(s.getPos(), byteBuf::writeVector3f);
            byteBuf.writeNotNull(s.getRot(), byteBuf::writeVector2f);
            byteBuf.writeNotNull(s.getFacing(), byteBuf::writeVector3f);
            byteBuf.writeNotNull(s.getViewOffset(), byteBuf::writeVector2f);
            byteBuf.writeNotNull(s.getEntityOffset(), byteBuf::writeVector3f);
            byteBuf.writeOptional(s.getDefaultPreset(), byteBuf::writeBoolean);
        });

        if (clearInstruction == null) {
            byteBuf.writeBoolean(false);
        } else {
            byteBuf.writeBoolean(true);//optional.isPresent
            byteBuf.writeBoolean(true);//actual data
        }

        byteBuf.writeNotNull(fadeInstruction, (f) -> {
            byteBuf.writeNotNull(f.getTime(), t -> this.writeTimeData(byteBuf, t));
            byteBuf.writeNotNull(f.getColor(), c -> this.writeColor(byteBuf, c));
        });

        if(protocolPlayer.protocol() >= ProtocolVersion.MINECRAFT_PE_1_21_20.protocol()) {
            try {
                Field field = this.getClass().getSuperclass().getDeclaredField("targetInstruction");
                field.setAccessible(true);
                TargetInstruction targetInstruction = (TargetInstruction) field.get(this);
                field.setAccessible(false);
                byteBuf.writeNotNull(targetInstruction, target -> {
                    byteBuf.writeNotNull(target.getTargetCenterOffset(), byteBuf::writeVector3f);
                    byteBuf.writeLongLE(targetInstruction.getUniqueEntityId());
                });
                Field field2 = this.getClass().getSuperclass().getDeclaredField("removeTarget");
                field2.setAccessible(true);
                OptionalBoolean removeTarget = (OptionalBoolean) field2.get(this);
                field2.setAccessible(false);
                byteBuf.writeOptional(removeTarget.toOptionalValue(), byteBuf::writeBoolean);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void setPlayer(ProtocolPlayer player) {
        this.protocolPlayer = player;
    }
}
