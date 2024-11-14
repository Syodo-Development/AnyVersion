package xyz.syodo.packets;

import cn.nukkit.network.connection.util.HandleByteBuf;
import cn.nukkit.network.protocol.ChangeDimensionPacket;
import xyz.syodo.manager.ProtocolPlayer;

import java.lang.reflect.Field;

public class PChangeDimensionPacket extends ChangeDimensionPacket implements ProtocolizedPacket {

    protected ProtocolPlayer protocolPlayer;

    public void encode(HandleByteBuf byteBuf) {
        byteBuf.writeVarInt(this.dimension);
        byteBuf.writeVector3f(this.x, this.y, this.z);
        byteBuf.writeBoolean(this.respawn);

        try {
            Field field = this.getClass().getSuperclass().getDeclaredField("loadingScreenId");
            field.setAccessible(true);
            Integer loadingScreenId = (Integer) field.get(this);
            field.setAccessible(false);
            byteBuf.writeBoolean(loadingScreenId != null);
            if (loadingScreenId != null) {
                byteBuf.writeIntLE(loadingScreenId);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
    @Override
    public void setPlayer(ProtocolPlayer player) {
        this.protocolPlayer = player;
    }
}
