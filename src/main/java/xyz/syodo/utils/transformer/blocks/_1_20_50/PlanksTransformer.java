package xyz.syodo.utils.transformer.blocks._1_20_50;

import cn.nukkit.block.BlockProperties;
import cn.nukkit.block.BlockState;
import cn.nukkit.block.property.CommonBlockProperties;
import cn.nukkit.block.property.enums.WoodType;
import xyz.syodo.utils.transformer.blocks.BlockStateTransformer;

import static cn.nukkit.block.BlockID.*;
import static cn.nukkit.item.ItemID.PLANKS;

public class PlanksTransformer extends BlockStateTransformer {

    @Override
    public BlockState transform(BlockState original) {
        BlockProperties PROPERTIES = new BlockProperties(PLANKS, CommonBlockProperties.WOOD_TYPE);
        WoodType type = switch(original.getIdentifier()) {
            case OAK_PLANKS -> WoodType.OAK;
            case SPRUCE_PLANKS -> WoodType.SPRUCE;
            case BIRCH_PLANKS -> WoodType.BIRCH;
            case JUNGLE_PLANKS-> WoodType.JUNGLE;
            case ACACIA_PLANKS -> WoodType.ACACIA;
            default -> WoodType.DARK_OAK;
        };
        return PROPERTIES.getBlockState(CommonBlockProperties.WOOD_TYPE.createValue(type));
    }
}
