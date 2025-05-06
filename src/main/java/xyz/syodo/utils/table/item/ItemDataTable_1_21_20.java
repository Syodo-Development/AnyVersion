package xyz.syodo.utils.table.item;

import cn.nukkit.block.BlockID;
import cn.nukkit.item.ItemID;
import xyz.syodo.utils.ProtocolVersion;
import xyz.syodo.utils.transformer.items._1_21_20.*;


import static cn.nukkit.block.BlockID.*;
import static xyz.syodo.utils.definition.ItemDefinition.of;
import static xyz.syodo.utils.transformer.blocks._1_21_20.StoneBrickTransformer.STONEBRICK;
import static xyz.syodo.utils.transformer.blocks._1_21_20.LightBlockTransformer.LIGHT_BLOCK;

public class ItemDataTable_1_21_20 extends ItemTable {

    public ItemDataTable_1_21_20() {
        super(ProtocolVersion.MINECRAFT_PE_1_21_20,
                of(BlockID.ANVIL, new AnvilTransformer()),
                of("minecraft:yellow_flower", new FlowerTransformer()),
                of(BlockID.DIRT, new DirtTransformer()),
                of(LIGHT_BLOCK, new LightBlockTransformer()),
                of(MONSTER_EGG, new MonsterEggTransformer()),
                of(BlockID.PRISMARINE, new PrismarineTransformer()),
                of(BlockID.QUARTZ_BLOCK, new QuartzTransformer()),
                of(ItemID.RED_SANDSTONE, new SandstoneTransformer()),
                of(BlockID.SAND, new SandTransformer()),
                of(ItemID.SANDSTONE, new SandstoneTransformer()),
                of(STONEBRICK, new StoneBrickTransformer()),
                of(DOUBLE_STONE_BLOCK_SLAB2, new StoneBlockSlab2Transformer()),
                of(DOUBLE_STONE_BLOCK_SLAB3, new StoneBlockSlab3Transformer()),
                of(DOUBLE_STONE_BLOCK_SLAB4, new StoneBlockSlab4Transformer()),
                of(STONE_BLOCK_SLAB2, new StoneBlockSlab2Transformer()),
                of(STONE_BLOCK_SLAB3, new StoneBlockSlab3Transformer()),
                of(STONE_BLOCK_SLAB4, new StoneBlockSlab4Transformer())
        );
    }

}
