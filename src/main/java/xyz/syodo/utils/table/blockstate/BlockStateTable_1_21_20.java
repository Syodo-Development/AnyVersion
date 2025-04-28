package xyz.syodo.utils.table.blockstate;

import xyz.syodo.utils.ProtocolVersion;
import xyz.syodo.utils.transformer._1_21_0.*;

import static cn.nukkit.block.BlockID.*;
import static xyz.syodo.utils.definition.BlockStateDefinition.of;

public class BlockStateTable_1_21_20 extends BlockStateTable {

    public BlockStateTable_1_21_20() {
        super(ProtocolVersion.MINECRAFT_PE_1_21_20,
                of(CUT_SANDSTONE),
                of(CHISELED_SANDSTONE),
                of(SMOOTH_SANDSTONE),
                of(CHISELED_STONE_BRICKS),
                of(QUARTZ_PILLAR),
                of(SMOOTH_QUARTZ),
                of(CUT_RED_SANDSTONE),
                of(CHISELED_RED_SANDSTONE),
                of(SMOOTH_RED_SANDSTONE),
                of(RED_SAND),
                of(RED_SANDSTONE),
                of(DIRT, new DirtStateTransformer()),
                of(COARSE_DIRT, new DirtStateTransformer()),
                of(DAMAGED_ANVIL),
                of(CHIPPED_ANVIL)
                );
    }
}
