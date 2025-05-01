package xyz.syodo.utils.transformer._1_20_70;

import cn.nukkit.block.BlockProperties;
import cn.nukkit.block.BlockState;
import cn.nukkit.block.property.CommonBlockProperties;
import cn.nukkit.block.property.enums.WoodType;
import xyz.syodo.utils.transformer.BlockStateTransformer;

import static cn.nukkit.block.BlockID.*;

public class WoodenSlabTransformer extends BlockStateTransformer {

    public static final String WOODEN_SLAB = "minecraft:wooden_slab";

    @Override
    public BlockState transform(BlockState original) {
        BlockProperties PROPERTIES = new BlockProperties(WOODEN_SLAB, CommonBlockProperties.MINECRAFT_VERTICAL_HALF, CommonBlockProperties.WOOD_TYPE);
        WoodType type = switch(original.getIdentifier()) {
            case OAK_SLAB -> WoodType.OAK;
            case SPRUCE_SLAB -> WoodType.SPRUCE;
            case BIRCH_SLAB -> WoodType.BIRCH;
            case JUNGLE_SLAB -> WoodType.JUNGLE;
            case ACACIA_SLAB -> WoodType.ACACIA;
            default -> WoodType.DARK_OAK;
        };
        return PROPERTIES.getBlockState(CommonBlockProperties.WOOD_TYPE.createValue(type),
                CommonBlockProperties.MINECRAFT_VERTICAL_HALF.createValue(original.getPropertyValue(CommonBlockProperties.MINECRAFT_VERTICAL_HALF)));
    }
}
