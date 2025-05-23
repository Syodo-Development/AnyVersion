package xyz.syodo.utils.table.blockstate;

import xyz.syodo.utils.ProtocolVersion;
import xyz.syodo.utils.transformer._1_20_50.PlanksTransformer;
import xyz.syodo.utils.transformer._1_20_50.StoneTransformer;
import xyz.syodo.utils.transformer._1_20_60.HardStainedGlassPaneTransformer;
import xyz.syodo.utils.transformer._1_20_60.HardStainedGlassTransformer;

import static cn.nukkit.block.BlockID.*;
import static xyz.syodo.utils.definition.BlockStateDefinition.of;

public class BlockStateTable_1_20_50 extends BlockStateTable {

    public BlockStateTable_1_20_50() {
        super(ProtocolVersion.MINECRAFT_PE_1_20_50,
                of(OAK_PLANKS, new PlanksTransformer()),
                of(SPRUCE_PLANKS, new PlanksTransformer()),
                of(BIRCH_PLANKS, new PlanksTransformer()),
                of(JUNGLE_PLANKS, new PlanksTransformer()),
                of(ACACIA_PLANKS, new PlanksTransformer()),
                of(DARK_OAK_PLANKS, new PlanksTransformer()),
                of(POLISHED_DIORITE, new StoneTransformer()),
                of(POLISHED_GRANITE, new StoneTransformer()),
                of(POLISHED_ANDESITE, new StoneTransformer())
        );
    }
}
