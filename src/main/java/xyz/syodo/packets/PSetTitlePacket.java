package xyz.syodo.packets;

import cn.nukkit.network.connection.util.HandleByteBuf;
import cn.nukkit.network.protocol.SetTitlePacket;
import cn.nukkit.network.protocol.StopSoundPacket;
import xyz.syodo.manager.ProtocolPlayer;
import xyz.syodo.utils.ProtocolVersion;

import java.lang.reflect.Field;

public class PSetTitlePacket extends SetTitlePacket implements ProtocolizedPacket{

    protected ProtocolPlayer protocolPlayer;

    @Override
    public void encode(HandleByteBuf byteBuf) {
        byteBuf.writeVarInt(this.type);
        byteBuf.writeString(this.text);
        byteBuf.writeVarInt(this.fadeInTime);
        byteBuf.writeVarInt(this.stayTime);
        byteBuf.writeVarInt(this.fadeOutTime);
        byteBuf.writeString(this.xuid);
        byteBuf.writeString(this.platformOnlineId);
        if (protocolPlayer.protocol() >= ProtocolVersion.MINECRAFT_PE_1_21_20.protocol()) {
            try {
                Field field = this.getClass().getSuperclass().getDeclaredField("filteredTitleText");
                field.setAccessible(true);
                byteBuf.writeString((String) field.get(this));
                field.setAccessible(false);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void decode(HandleByteBuf byteBuf) {
        this.type = byteBuf.readVarInt();
        this.text = byteBuf.readString();
        this.fadeInTime = byteBuf.readVarInt();
        this.stayTime = byteBuf.readVarInt();
        this.fadeOutTime = byteBuf.readVarInt();
        this.xuid = byteBuf.readString();
        this.platformOnlineId = byteBuf.readString();
        if (protocolPlayer.protocol() >= ProtocolVersion.MINECRAFT_PE_1_21_20.protocol()) {
            try {
                Field field = this.getClass().getSuperclass().getDeclaredField("filteredTitleText");
                field.setAccessible(true);
                field.set(this, byteBuf.readString());
                field.setAccessible(false);
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
