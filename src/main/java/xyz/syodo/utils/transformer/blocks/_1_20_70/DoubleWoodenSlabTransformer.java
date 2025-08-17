package xyz.syodo.utils.transformer.blocks._1_20_70;

import cn.nukkit.block.BlockProperties;
import cn.nukkit.block.BlockState;
import cn.nukkit.block.property.CommonBlockProperties;
import cn.nukkit.block.property.enums.WoodType;
import xyz.syodo.utils.transformer.blocks.BlockStateTransformer;

import static cn.nukkit.block.BlockID.*;

public class DoubleWoodenSlabTransformer extends BlockStateTransformer {

    public static final String DOUBLE_WOODEN_SLAB = "minecraft:double_wooden_slab";

    @Override
    public BlockState transform(BlockState original) {
        BlockProperties PROPERTIES = new BlockProperties(DOUBLE_WOODEN_SLAB, CommonBlockProperties.MINECRAFT_VERTICAL_HALF, CommonBlockProperties.WOOD_TYPE);
        WoodType type = switch(original.getIdentifier()) {
            case OAK_DOUBLE_SLAB -> WoodType.OAK;
            case SPRUCE_DOUBLE_SLAB -> WoodType.SPRUCE;
            case BIRCH_DOUBLE_SLAB -> WoodType.BIRCH;
            case JUNGLE_DOUBLE_SLAB -> WoodType.JUNGLE;
            case ACACIA_DOUBLE_SLAB -> WoodType.ACACIA;
            default -> WoodType.DARK_OAK;
        };
        return PROPERTIES.getBlockState(CommonBlockProperties.WOOD_TYPE.createValue(type),
                CommonBlockProperties.MINECRAFT_VERTICAL_HALF.createValue(original.getPropertyValue(CommonBlockProperties.MINECRAFT_VERTICAL_HALF)));
    }
}
