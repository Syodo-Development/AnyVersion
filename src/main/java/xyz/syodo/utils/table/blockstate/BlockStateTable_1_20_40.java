package xyz.syodo.utils.table.blockstate;

import xyz.syodo.utils.ProtocolVersion;
import xyz.syodo.utils.transformer._1_20_40.FacingToCardinalTransformer;

import static cn.nukkit.block.BlockID.*;
import static xyz.syodo.utils.definition.BlockStateDefinition.of;

public class BlockStateTable_1_20_40 extends BlockStateTable {

    public BlockStateTable_1_20_40() {
        super(ProtocolVersion.MINECRAFT_PE_1_20_40,
                of(CHEST, new FacingToCardinalTransformer()),
                of(ENDER_CHEST, new FacingToCardinalTransformer()),
                of(STONECUTTER_BLOCK, new FacingToCardinalTransformer()),
                of(TRAPPED_CHEST, new FacingToCardinalTransformer())
        );
    }
}
