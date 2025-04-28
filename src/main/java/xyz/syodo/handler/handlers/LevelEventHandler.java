package xyz.syodo.handler.handlers;

import cn.nukkit.block.BlockState;
import cn.nukkit.block.BlockStateImpl;
import lombok.extern.slf4j.Slf4j;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.protocol.bedrock.data.LevelEvent;
import org.cloudburstmc.protocol.bedrock.data.LevelEventType;
import org.cloudburstmc.protocol.bedrock.data.definitions.BlockDefinition;
import org.cloudburstmc.protocol.bedrock.data.definitions.SimpleBlockDefinition;
import org.cloudburstmc.protocol.bedrock.packet.LevelEventPacket;
import org.cloudburstmc.protocol.bedrock.packet.UpdateBlockPacket;
import xyz.syodo.handler.PacketHandler;
import xyz.syodo.registries.Registries;
import xyz.syodo.utils.ProtocolVersion;
import xyz.syodo.utils.definition.BlockStateDefinition;

@Slf4j
public class LevelEventHandler extends PacketHandler<LevelEventPacket> {

    @Override
    public void handle(ProtocolVersion version, LevelEventPacket packet) {
        if(packet.getType() == LevelEvent.PARTICLE_DESTROY_BLOCK) {
            BlockState origen = cn.nukkit.registry.Registries.BLOCKSTATE.get(packet.getData());
            BlockStateDefinition blockStateDefinition = BlockStateDefinition.of(origen.getIdentifier());
            if(Registries.BLOCKSTATE.getProtocolVersion(blockStateDefinition).protocol() > version.protocol()) {
                BlockStateImpl downgraded = (BlockStateImpl) Registries.BLOCKSTATE.downgrade(version, blockStateDefinition).getDowngrade().transform(origen);
                packet.setData(downgraded.blockStateHash());
            }
        }
    }

}
