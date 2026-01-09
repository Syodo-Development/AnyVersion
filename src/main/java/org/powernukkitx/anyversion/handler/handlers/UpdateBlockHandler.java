package org.powernukkitx.anyversion.handler.handlers;

import cn.nukkit.block.BlockStateImpl;
import lombok.extern.slf4j.Slf4j;
import org.cloudburstmc.protocol.bedrock.data.definitions.BlockDefinition;
import org.cloudburstmc.protocol.bedrock.data.definitions.SimpleBlockDefinition;
import org.cloudburstmc.protocol.bedrock.packet.UpdateBlockPacket;
import org.powernukkitx.anyversion.handler.PacketHandler;
import org.powernukkitx.anyversion.manager.ProtocolPlayer;
import org.powernukkitx.anyversion.registries.Registries;

@Slf4j
public class UpdateBlockHandler extends PacketHandler<UpdateBlockPacket> {

    @Override
    public void handle(ProtocolPlayer player, UpdateBlockPacket packet) {
        if(packet.getDefinition() instanceof SimpleBlockDefinition definition) {
            BlockStateImpl downgraded = (BlockStateImpl) Registries.BLOCKSTATE.downgrade(player.getVersion(), cn.nukkit.registry.Registries.BLOCKSTATE.get(definition.getRuntimeId()));
            BlockDefinition blockDefinition = new SimpleBlockDefinition(downgraded.getIdentifier(), Registries.BLOCKPALETTE.getRuntimeId(player.getVersion(), downgraded), definition.getState());
            packet.setDefinition(blockDefinition);
        } else log.warn("BlockDefinition is not a SimpleBlockDefinition");
    }

}
