package xyz.syodo.utils.transformer;

import cn.nukkit.block.BlockState;
import cn.nukkit.block.BlockUnknown;

public class UnknownStateTransformer extends BlockStateTransformer {
    @Override
    public BlockState transform(BlockState original) {
        return BlockUnknown.PROPERTIES.getDefaultState();
    }
}
