package org.powernukkitx.anyversion.utils.transformer.blocks._1_20_70;

import cn.nukkit.block.BlockProperties;
import cn.nukkit.block.BlockState;
import cn.nukkit.block.property.CommonBlockProperties;
import cn.nukkit.block.property.enums.WoodType;
import org.powernukkitx.anyversion.utils.transformer.blocks.BlockStateTransformer;

import static cn.nukkit.block.BlockID.*;
import static cn.nukkit.item.ItemID.WOOD;

public class WoodTransformer extends BlockStateTransformer {

    private static final String STRIPPED = "stripped_";

    @Override
    public BlockState transform(BlockState original) {
        BlockProperties PROPERTIES = new BlockProperties(WOOD, CommonBlockProperties.PILLAR_AXIS, CommonBlockProperties.STRIPPED_BIT, CommonBlockProperties.WOOD_TYPE);
        WoodType type = switch(original.getIdentifier().replace(STRIPPED, "")) {
            case OAK_WOOD -> WoodType.OAK;
            case SPRUCE_WOOD -> WoodType.SPRUCE;
            case BIRCH_WOOD -> WoodType.BIRCH;
            case JUNGLE_WOOD -> WoodType.JUNGLE;
            case ACACIA_WOOD -> WoodType.ACACIA;
            default -> WoodType.DARK_OAK;
        };

        return PROPERTIES.getBlockState(CommonBlockProperties.WOOD_TYPE.createValue(type),
                CommonBlockProperties.STRIPPED_BIT.createValue(original.getIdentifier().contains(STRIPPED)),
                CommonBlockProperties.PILLAR_AXIS.createValue(original.getPropertyValue(CommonBlockProperties.PILLAR_AXIS))
        );
    }
}
