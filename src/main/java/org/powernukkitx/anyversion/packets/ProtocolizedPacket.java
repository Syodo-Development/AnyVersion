package org.powernukkitx.anyversion.packets;

import cn.nukkit.network.protocol.DataPacket;
import lombok.SneakyThrows;
import org.powernukkitx.anyversion.manager.ProtocolPlayer;

import java.lang.reflect.AccessFlag;
import java.lang.reflect.Field;

public interface ProtocolizedPacket {

    void setPlayer(ProtocolPlayer player);

    @SneakyThrows
    default void copyPacketContent(DataPacket packet) {
        for(Field field : packet.getClass().getDeclaredFields()) {
            if(!field.accessFlags().contains(AccessFlag.STATIC)) {
                if (field.canAccess(packet) && !field.accessFlags().contains(AccessFlag.FINAL)) {
                    field.set(this, field.get(packet));
                } else {
                    field.setAccessible(true);
                    field.set(this, field.get(packet));
                    field.setAccessible(false);
                }
            }
        }
    }
}
