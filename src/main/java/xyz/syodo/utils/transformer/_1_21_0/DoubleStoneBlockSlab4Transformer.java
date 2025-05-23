package xyz.syodo.utils.transformer._1_21_0;

import cn.nukkit.block.BlockProperties;
import cn.nukkit.block.BlockState;
import cn.nukkit.block.property.CommonBlockProperties;
import cn.nukkit.block.property.enums.StoneSlabType4;
import xyz.syodo.utils.transformer.BlockStateTransformer;

import static cn.nukkit.block.BlockID.*;

public class DoubleStoneBlockSlab4Transformer extends BlockStateTransformer {

    @Override
    public BlockState transform(BlockState original) {
        BlockProperties PROPERTIES = new BlockProperties(DOUBLE_STONE_BLOCK_SLAB4, CommonBlockProperties.MINECRAFT_VERTICAL_HALF, CommonBlockProperties.STONE_SLAB_TYPE_4);
        StoneSlabType4 type = switch(original.getIdentifier()) {
            case MOSSY_STONE_BRICK_DOUBLE_SLAB -> StoneSlabType4.MOSSY_STONE_BRICK;
            case SMOOTH_QUARTZ_DOUBLE_SLAB -> StoneSlabType4.SMOOTH_QUARTZ;
            case NORMAL_STONE_DOUBLE_SLAB -> StoneSlabType4.STONE;
            case CUT_SANDSTONE_DOUBLE_SLAB -> StoneSlabType4.CUT_SANDSTONE;
            default -> StoneSlabType4.CUT_RED_SANDSTONE;
        };
        return PROPERTIES.getBlockState(CommonBlockProperties.STONE_SLAB_TYPE_4.createValue(type),
                CommonBlockProperties.MINECRAFT_VERTICAL_HALF.createValue(original.getPropertyValue(CommonBlockProperties.MINECRAFT_VERTICAL_HALF)));
    }

}
