package xyz.syodo.packets;

import cn.nukkit.network.connection.util.HandleByteBuf;
import cn.nukkit.network.protocol.DisconnectPacket;
import cn.nukkit.network.protocol.types.DisconnectFailReason;
import xyz.syodo.manager.ProtocolPlayer;
import xyz.syodo.utils.ProtocolVersion;

import java.lang.reflect.Field;

public class PDisconnectPacket extends DisconnectPacket implements ProtocolizedPacket{

    protected ProtocolPlayer protocolPlayer;

    @Override
    public void decode(HandleByteBuf byteBuf) {
        this.reason = DisconnectFailReason.values()[byteBuf.readVarInt()];
        this.hideDisconnectionScreen = byteBuf.readBoolean();
        if (!this.hideDisconnectionScreen) {
            this.message = byteBuf.readString();
            try {
                Field field = DisconnectPacket.class.getDeclaredField("filteredMessage");
                field.setAccessible(true);
                if (protocolPlayer.protocol() >= ProtocolVersion.MINECRAFT_PE_1_21_20.protocol()) {
                    field.set(this, byteBuf.readString());
                } else field.set(this, message);
                field.setAccessible(false);
            } catch (NoSuchFieldException | IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void encode(HandleByteBuf byteBuf) {
        byteBuf.clear();
        byteBuf.writeVarInt(this.reason.ordinal());
        byteBuf.writeBoolean(this.hideDisconnectionScreen);
        if (!this.hideDisconnectionScreen) {
            byteBuf.writeString(this.message);
            if (protocolPlayer.protocol() >= ProtocolVersion.MINECRAFT_PE_1_21_20.protocol()) {
                try {
                    Field field = DisconnectPacket.class.getDeclaredField("filteredMessage");
                    field.setAccessible(true);
                    byteBuf.writeString((String) field.get(this));
                    field.setAccessible(false);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    @Override
    public void setPlayer(ProtocolPlayer player) {
        this.protocolPlayer = player;
    }
}
