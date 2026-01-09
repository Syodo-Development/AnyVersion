package org.powernukkitx.anyversion.utils.transformer.blocks._1_19_80;

import cn.nukkit.block.BlockProperties;
import cn.nukkit.block.BlockState;
import cn.nukkit.block.property.CommonBlockProperties;
import cn.nukkit.block.property.enums.WoodType;
import org.powernukkitx.anyversion.utils.transformer.blocks.BlockStateTransformer;

import static cn.nukkit.block.BlockID.*;
import static cn.nukkit.item.ItemID.FENCE;

public class FenceTransformer extends BlockStateTransformer {

    @Override
    public BlockState transform(BlockState original) {
        BlockProperties PROPERTIES = new BlockProperties(FENCE, CommonBlockProperties.WOOD_TYPE);
        WoodType type = switch(original.getIdentifier()) {
            case OAK_FENCE -> WoodType.OAK;
            case SPRUCE_FENCE -> WoodType.SPRUCE;
            case BIRCH_FENCE -> WoodType.BIRCH;
            case JUNGLE_FENCE -> WoodType.JUNGLE;
            case ACACIA_FENCE -> WoodType.ACACIA;
            default -> WoodType.DARK_OAK;
        };
        return PROPERTIES.getBlockState(CommonBlockProperties.WOOD_TYPE.createValue(type));
    }
}
