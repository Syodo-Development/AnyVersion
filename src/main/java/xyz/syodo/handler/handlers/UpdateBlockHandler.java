package xyz.syodo.handler.handlers;

import cn.nukkit.block.BlockStateImpl;
import lombok.extern.slf4j.Slf4j;
import org.cloudburstmc.protocol.bedrock.data.definitions.BlockDefinition;
import org.cloudburstmc.protocol.bedrock.data.definitions.SimpleBlockDefinition;
import org.cloudburstmc.protocol.bedrock.packet.UpdateBlockPacket;
import xyz.syodo.handler.PacketHandler;
import xyz.syodo.registries.Registries;
import xyz.syodo.utils.ProtocolVersion;
import xyz.syodo.utils.definition.BlockStateDefinition;

@Slf4j
public class UpdateBlockHandler extends PacketHandler<UpdateBlockPacket> {

    @Override
    public void handle(ProtocolVersion version, UpdateBlockPacket packet) {
        if(packet.getDefinition() instanceof SimpleBlockDefinition definition) {
            BlockStateDefinition blockStateDefinition = BlockStateDefinition.of(definition.getIdentifier());
            if(Registries.BLOCKSTATE.getProtocolVersion(blockStateDefinition).protocol() > version.protocol()) {
                BlockStateImpl downgraded = (BlockStateImpl) Registries.BLOCKSTATE.downgrade(version, blockStateDefinition).getDowngrade().transform(cn.nukkit.registry.Registries.BLOCKSTATE.get(definition.getRuntimeId()));
                BlockDefinition blockDefinition = new SimpleBlockDefinition(downgraded.getIdentifier(), downgraded.blockStateHash(), definition.getState());
                packet.setDefinition(blockDefinition);
            }
        } else log.warn("BlockDefinition is not a SimpleBlockDefinition");
    }

}
