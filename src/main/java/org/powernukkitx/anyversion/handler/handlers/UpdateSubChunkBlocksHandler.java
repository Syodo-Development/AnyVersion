package org.powernukkitx.anyversion.handler.handlers;

import cn.nukkit.block.BlockStateImpl;
import org.cloudburstmc.protocol.bedrock.data.BlockChangeEntry;
import org.cloudburstmc.protocol.bedrock.data.definitions.BlockDefinition;
import org.cloudburstmc.protocol.bedrock.data.definitions.SimpleBlockDefinition;
import org.cloudburstmc.protocol.bedrock.packet.UpdateSubChunkBlocksPacket;
import org.powernukkitx.anyversion.handler.PacketHandler;
import org.powernukkitx.anyversion.manager.ProtocolPlayer;
import org.powernukkitx.anyversion.registries.Registries;

import java.util.ArrayList;
import java.util.List;

public class UpdateSubChunkBlocksHandler extends PacketHandler<UpdateSubChunkBlocksPacket> {
    @Override
    public void handle(ProtocolPlayer player, UpdateSubChunkBlocksPacket packet) {
        List<BlockChangeEntry> entries = new ArrayList<>(packet.getStandardBlocks());
        packet.getStandardBlocks().clear();
        for(BlockChangeEntry entry : entries) {
            if(entry.getDefinition() instanceof SimpleBlockDefinition definition) {
                BlockStateImpl downgraded = (BlockStateImpl) Registries.BLOCKSTATE.downgrade(player.getVersion(), cn.nukkit.registry.Registries.BLOCKSTATE.get(definition.getRuntimeId()));
                BlockDefinition blockDefinition = new SimpleBlockDefinition(downgraded.getIdentifier(), Registries.BLOCKPALETTE.getRuntimeId(player.getVersion(), downgraded), definition.getState());
                BlockChangeEntry updated = new BlockChangeEntry(entry.getPosition(), blockDefinition, entry.getUpdateFlags(), entry.getMessageEntityId(), entry.getMessageType());
                packet.getStandardBlocks().add(updated);
            }
        }
    }
}
