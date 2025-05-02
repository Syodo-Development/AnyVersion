package xyz.syodo.utils.palette;

import cn.nukkit.block.BlockState;
import xyz.syodo.utils.ProtocolVersion;

public class BlockHashBlockPalette extends BlockPalette {

    public BlockHashBlockPalette() {
        super(ProtocolVersion.getCurrent());
    }

    @Override
    public int getRuntimeId(BlockState state) {
        return state.blockStateHash();
    }
}
