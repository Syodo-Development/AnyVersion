package xyz.syodo.handler.handlers;

import cn.nukkit.block.BlockState;
import cn.nukkit.block.BlockStateImpl;
import lombok.extern.slf4j.Slf4j;
import org.cloudburstmc.protocol.bedrock.data.LevelEvent;
import org.cloudburstmc.protocol.bedrock.packet.LevelEventPacket;
import xyz.syodo.handler.PacketHandler;
import xyz.syodo.manager.ProtocolPlayer;
import xyz.syodo.registries.Registries;
import xyz.syodo.utils.ProtocolVersion;
import xyz.syodo.utils.definition.BlockStateDefinition;

@Slf4j
public class LevelEventHandler extends PacketHandler<LevelEventPacket> {

    @Override
    public void handle(ProtocolPlayer player, LevelEventPacket packet) {
        if(packet.getType() == LevelEvent.PARTICLE_DESTROY_BLOCK) {
            BlockState origen = cn.nukkit.registry.Registries.BLOCKSTATE.get(packet.getData());
            BlockStateImpl downgraded = (BlockStateImpl) Registries.BLOCKSTATE.downgrade(player.getVersion(), origen);
            packet.setData(downgraded.blockStateHash());
        }
    }

}
