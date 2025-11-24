package xyz.syodo.processors;

import cn.nukkit.Player;
import cn.nukkit.PlayerHandle;
import cn.nukkit.entity.data.EntityDataMap;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerCreationEvent;
import cn.nukkit.network.connection.BedrockSession;
import cn.nukkit.network.process.DataPacketProcessor;
import cn.nukkit.network.protocol.ProtocolInfo;
import cn.nukkit.network.protocol.SetEntityDataPacket;
import cn.nukkit.network.protocol.types.PlayerInfo;
import org.jetbrains.annotations.NotNull;

public class SetEntityDataProcessor extends DataPacketProcessor<SetEntityDataPacket> {

    @Override
    public void handle(@NotNull PlayerHandle playerHandle, @NotNull SetEntityDataPacket pk) {
        EntityDataMap map = pk.entityData.copy();

        pk.setEntityData(map);
    }

    @Override
    public int getPacketId() {
        return ProtocolInfo.SET_ENTITY_DATA_PACKET;
    }

   
}
