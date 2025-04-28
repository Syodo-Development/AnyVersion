package xyz.syodo.utils.table.blockstate;

import xyz.syodo.utils.ProtocolVersion;

import static cn.nukkit.block.BlockID.*;
import static xyz.syodo.utils.definition.BlockStateDefinition.of;

public class BlockStateTable_1_21_70 extends BlockStateTable {

    public BlockStateTable_1_21_70() {
        super(ProtocolVersion.MINECRAFT_PE_1_21_70,
                of(BUSH),
                of(CACTUS_FLOWER),
                of(FIREFLY_BUSH),
                of(LEAF_LITTER),
                of(SHORT_DRY_GRASS),
                of(TALL_DRY_GRASS),
                of(WILDFLOWERS));
    }

}
