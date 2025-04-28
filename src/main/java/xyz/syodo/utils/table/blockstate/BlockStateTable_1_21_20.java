package xyz.syodo.utils.table.blockstate;

import xyz.syodo.utils.ProtocolVersion;
import xyz.syodo.utils.transformer.IdentifierTransformer;
import xyz.syodo.utils.transformer._1_21_20.*;


import static cn.nukkit.block.BlockID.*;
import static xyz.syodo.utils.definition.BlockStateDefinition.of;

public class BlockStateTable_1_21_20 extends BlockStateTable {

    public BlockStateTable_1_21_20() {
        super(ProtocolVersion.MINECRAFT_PE_1_21_20,
                of(LIGHT_BLOCK_0, new LightBlockTransformer()),
                of(LIGHT_BLOCK_1, new LightBlockTransformer()),
                of(LIGHT_BLOCK_2, new LightBlockTransformer()),
                of(LIGHT_BLOCK_3, new LightBlockTransformer()),
                of(LIGHT_BLOCK_4, new LightBlockTransformer()),
                of(LIGHT_BLOCK_5, new LightBlockTransformer()),
                of(LIGHT_BLOCK_6, new LightBlockTransformer()),
                of(LIGHT_BLOCK_7, new LightBlockTransformer()),
                of(LIGHT_BLOCK_8, new LightBlockTransformer()),
                of(LIGHT_BLOCK_9, new LightBlockTransformer()),
                of(LIGHT_BLOCK_10, new LightBlockTransformer()),
                of(LIGHT_BLOCK_11, new LightBlockTransformer()),
                of(LIGHT_BLOCK_12, new LightBlockTransformer()),
                of(LIGHT_BLOCK_13, new LightBlockTransformer()),
                of(LIGHT_BLOCK_14, new LightBlockTransformer()),
                of(LIGHT_BLOCK_15, new LightBlockTransformer()),
                of(SANDSTONE, new SandstoneTransformer()),
                of(CUT_SANDSTONE, new SandstoneTransformer()),
                of(SMOOTH_SANDSTONE, new SandstoneTransformer()),
                of(CHISELED_SANDSTONE, new SandstoneTransformer()),
                of(RED_SANDSTONE, new RedSandstoneTransformer()),
                of(CUT_RED_SANDSTONE, new RedSandstoneTransformer()),
                of(SMOOTH_RED_SANDSTONE, new RedSandstoneTransformer()),
                of(CHISELED_RED_SANDSTONE, new RedSandstoneTransformer()),
                of(DIRT, new DirtTransformer()),
                of(COARSE_DIRT, new DirtTransformer()),
                of(QUARTZ_BLOCK, new QuartzTransformer()),
                of(CHISELED_QUARTZ_BLOCK, new QuartzTransformer()),
                of(QUARTZ_PILLAR, new QuartzTransformer()),
                of(SMOOTH_QUARTZ, new QuartzTransformer()),
                of(SAND, new SandTransformer()),
                of(RED_SAND, new SandTransformer()),
                of(DANDELION, new IdentifierTransformer("minecraft:yellow_flower")),
                of(ANVIL, new AnvilTransformer()),
                of(DAMAGED_ANVIL, new AnvilTransformer()),
                of(CHIPPED_ANVIL, new AnvilTransformer())
                );
    }
}
