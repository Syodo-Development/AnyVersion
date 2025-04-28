package xyz.syodo.utils.transformer._1_21_0;

import cn.nukkit.block.BlockProperties;
import cn.nukkit.block.BlockState;
import cn.nukkit.block.property.CommonBlockProperties;
import cn.nukkit.block.property.enums.StoneSlabType3;
import xyz.syodo.utils.transformer.BlockStateTransformer;

import static cn.nukkit.block.BlockID.*;

public class StoneBlockSlab3Transformer extends BlockStateTransformer {

    @Override
    public BlockState transform(BlockState original) {
        BlockProperties PROPERTIES = new BlockProperties(STONE_BLOCK_SLAB3, CommonBlockProperties.MINECRAFT_VERTICAL_HALF, CommonBlockProperties.STONE_SLAB_TYPE_3);
        StoneSlabType3 type = switch(original.getIdentifier()) {
            case SMOOTH_RED_SANDSTONE_SLAB -> StoneSlabType3.SMOOTH_RED_SANDSTONE;
            case POLISHED_GRANITE_SLAB -> StoneSlabType3.POLISHED_GRANITE;
            case GRANITE_SLAB -> StoneSlabType3.GRANITE;
            case POLISHED_DIORITE_SLAB -> StoneSlabType3.POLISHED_DIORITE;
            case DIORITE_SLAB -> StoneSlabType3.DIORITE;
            case POLISHED_ANDESITE_SLAB -> StoneSlabType3.POLISHED_ANDESITE;
            case ANDESITE_SLAB -> StoneSlabType3.ANDESITE;
            default -> StoneSlabType3.END_STONE_BRICK;
        };
        return PROPERTIES.getBlockState(CommonBlockProperties.STONE_SLAB_TYPE_3.createValue(type),
                CommonBlockProperties.MINECRAFT_VERTICAL_HALF.createValue(original.getPropertyValue(CommonBlockProperties.MINECRAFT_VERTICAL_HALF)));
    }

}
