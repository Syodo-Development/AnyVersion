package xyz.syodo.handler.handlers;

import cn.nukkit.block.BlockID;
import cn.nukkit.block.BlockUnknown;
import lombok.extern.slf4j.Slf4j;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.protocol.bedrock.data.definitions.BlockDefinition;
import org.cloudburstmc.protocol.bedrock.data.definitions.SimpleBlockDefinition;
import org.cloudburstmc.protocol.bedrock.packet.UpdateBlockPacket;
import xyz.syodo.handler.PacketHandler;
import xyz.syodo.registries.Registries;
import xyz.syodo.utils.ProtocolVersion;
import xyz.syodo.utils.definition.ItemDefinition;

@Slf4j
public class UpdateBlockHandler extends PacketHandler<UpdateBlockPacket> {

    public static final BlockDefinition UNKNOWN = new SimpleBlockDefinition(BlockID.UNKNOWN, BlockUnknown.PROPERTIES.getDefaultState().blockStateHash(), NbtMap.fromMap(BlockUnknown.PROPERTIES.getDefaultState().getBlockStateTag().parseValue()));

    @Override
    public void handle(ProtocolVersion version, UpdateBlockPacket packet) {
        if(packet.getDefinition() instanceof SimpleBlockDefinition definition) {
            if(Registries.ITEM.getProtocolVersion(ItemDefinition.of(definition.getIdentifier())).protocol() > version.protocol()) {
                packet.setDefinition(UNKNOWN);
            }
        } else log.warn("BlockDefinition is not a SimpleBlockDefinition");
    }

}
