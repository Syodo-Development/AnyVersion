package xyz.syodo.utils.transformer._1_20_70;

import cn.nukkit.block.BlockProperties;
import cn.nukkit.block.BlockState;
import cn.nukkit.block.property.CommonBlockProperties;
import cn.nukkit.block.property.enums.WoodType;
import xyz.syodo.utils.transformer.BlockStateTransformer;

import static cn.nukkit.block.BlockID.*;
import static cn.nukkit.item.ItemID.WOOD;

public class WoodTransformer extends BlockStateTransformer {

    private static final String STRIPPED = "stripped_";

    @Override
    public BlockState transform(BlockState original) {
        BlockProperties PROPERTIES = new BlockProperties(WOOD, CommonBlockProperties.PILLAR_AXIS, CommonBlockProperties.STRIPPED_BIT, CommonBlockProperties.WOOD_TYPE);
        WoodType type = switch(original.getIdentifier().replace(STRIPPED, "")) {
            case OAK_SLAB -> WoodType.OAK;
            case SPRUCE_SLAB -> WoodType.SPRUCE;
            case BIRCH_SLAB -> WoodType.BIRCH;
            case JUNGLE_SLAB -> WoodType.JUNGLE;
            case ACACIA_SLAB -> WoodType.ACACIA;
            default -> WoodType.DARK_OAK;
        };
        return PROPERTIES.getBlockState(CommonBlockProperties.WOOD_TYPE.createValue(type),
                CommonBlockProperties.STRIPPED_BIT.createValue(original.getIdentifier().contains(STRIPPED)),
                CommonBlockProperties.MINECRAFT_VERTICAL_HALF.createValue(original.getPropertyValue(CommonBlockProperties.MINECRAFT_VERTICAL_HALF)));
    }
}
