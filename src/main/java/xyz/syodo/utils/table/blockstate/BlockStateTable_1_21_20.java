package xyz.syodo.utils.table.blockstate;

import xyz.syodo.utils.ProtocolVersion;
import xyz.syodo.utils.transformer._1_21_20.DirtStateTransformer;
import xyz.syodo.utils.transformer._1_21_20.StoneBlockSlab2Transformer;
import xyz.syodo.utils.transformer._1_21_20.StoneBlockSlab3Transformer;

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
                of(CHIPPED_ANVIL),
                of(DANDELION),
                of(PRISMARINE_SLAB, new StoneBlockSlab2Transformer()),
                of(DARK_PRISMARINE_SLAB, new StoneBlockSlab2Transformer()),
                of(SMOOTH_SANDSTONE_SLAB, new StoneBlockSlab2Transformer()),
                of(PURPUR_SLAB, new StoneBlockSlab2Transformer()),
                of(RED_NETHER_BRICK_SLAB, new StoneBlockSlab2Transformer()),
                of(PRISMARINE_BRICK_SLAB, new StoneBlockSlab2Transformer()),
                of(MOSSY_COBBLESTONE_SLAB, new StoneBlockSlab2Transformer()),
                of(RED_SANDSTONE_SLAB, new StoneBlockSlab2Transformer()),
                of(SMOOTH_RED_SANDSTONE_SLAB, new StoneBlockSlab3Transformer()),
                of(POLISHED_GRANITE_SLAB, new StoneBlockSlab3Transformer()),
                of(GRANITE_SLAB, new StoneBlockSlab3Transformer()),
                of(POLISHED_DIORITE_SLAB, new StoneBlockSlab3Transformer()),
                of(DIORITE_SLAB, new StoneBlockSlab3Transformer()),
                of(POLISHED_ANDESITE_SLAB, new StoneBlockSlab3Transformer()),
                of(ANDESITE_SLAB, new StoneBlockSlab3Transformer()),
                of(END_STONE_BRICK_SLAB, new StoneBlockSlab3Transformer())
                );
    }

}
