package xyz.syodo.utils;

import cn.nukkit.block.BlockState;
import cn.nukkit.level.format.palette.RuntimeDataDeserializer;
import cn.nukkit.registry.Registries;

public class BlockStateRuntimeDataDeserializer implements RuntimeDataDeserializer<BlockState> {
    @Override
    public BlockState deserialize(int i) {
        return Registries.BLOCKSTATE.get(i);
    }
}
