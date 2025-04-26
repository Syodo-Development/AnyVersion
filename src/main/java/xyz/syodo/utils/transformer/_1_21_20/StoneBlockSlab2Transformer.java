package xyz.syodo.utils.transformer._1_21_20;

import cn.nukkit.block.BlockID;
import cn.nukkit.block.BlockProperties;
import cn.nukkit.block.BlockState;
import cn.nukkit.block.property.CommonBlockProperties;
import cn.nukkit.block.property.enums.DirtType;
import cn.nukkit.block.property.enums.StoneSlabType2;
import xyz.syodo.utils.transformer.BlockStateTransformer;

public class StoneBlockSlab2Transformer extends BlockStateTransformer {

    @Override
    public BlockState transform(BlockState original) {
        BlockProperties PROPERTIES = new BlockProperties(BlockID.STONE_BLOCK_SLAB2, CommonBlockProperties.MINECRAFT_VERTICAL_HALF, CommonBlockProperties.STONE_SLAB_TYPE_2);
        StoneSlabType2 type = switch(original.getIdentifier()) {
            case BlockID.PRISMARINE_SLAB -> StoneSlabType2.PRISMARINE_ROUGH;
            case BlockID.DARK_PRISMARINE_SLAB -> StoneSlabType2.PRISMARINE_DARK;
            case BlockID.SMOOTH_SANDSTONE_SLAB -> StoneSlabType2.SMOOTH_SANDSTONE;
            case BlockID.PURPUR_SLAB -> StoneSlabType2.PURPUR;
            case BlockID.RED_NETHER_BRICK_SLAB -> StoneSlabType2.RED_NETHER_BRICK;
            case BlockID.PRISMARINE_BRICK_SLAB -> StoneSlabType2.PRISMARINE_BRICK;
            case BlockID.MOSSY_COBBLESTONE_SLAB -> StoneSlabType2.MOSSY_COBBLESTONE;
            default -> StoneSlabType2.RED_SANDSTONE;
        };
        return PROPERTIES.getBlockState(CommonBlockProperties.STONE_SLAB_TYPE_2.createValue(type),
                CommonBlockProperties.MINECRAFT_VERTICAL_HALF.createValue(original.getPropertyValue(CommonBlockProperties.MINECRAFT_VERTICAL_HALF)));
    }

}
