package xyz.syodo.utils.transformer.blocks._1_20_30;

import cn.nukkit.block.BlockProperties;
import cn.nukkit.block.BlockState;
import cn.nukkit.block.property.CommonBlockProperties;
import cn.nukkit.block.property.enums.Color;
import xyz.syodo.utils.transformer.blocks.BlockStateTransformer;

import static cn.nukkit.block.BlockID.*;
import static cn.nukkit.item.ItemID.CONCRETE_POWDER;

public class ConcretePowderTransformer extends BlockStateTransformer {

    @Override
    public BlockState transform(BlockState original) {
        BlockProperties PROPERTIES = new BlockProperties(CONCRETE_POWDER, CommonBlockProperties.COLOR);
        Color type = switch(original.getIdentifier()) {
            case BLACK_CONCRETE_POWDER -> Color.BLACK;
            case BLUE_CONCRETE_POWDER -> Color.BLUE;
            case BROWN_CONCRETE_POWDER -> Color.BROWN;
            case CYAN_CONCRETE_POWDER -> Color.CYAN;
            case GRAY_CONCRETE_POWDER -> Color.GRAY;
            case GREEN_CONCRETE_POWDER -> Color.GREEN;
            case LIGHT_BLUE_CONCRETE_POWDER -> Color.LIGHT_BLUE;
            case LIME_CONCRETE_POWDER -> Color.LIME;
            case MAGENTA_CONCRETE_POWDER -> Color.MAGENTA;
            case ORANGE_CONCRETE_POWDER -> Color.ORANGE;
            case PINK_CONCRETE_POWDER -> Color.PINK;
            case PURPLE_CONCRETE_POWDER -> Color.PURPLE;
            case RED_CONCRETE_POWDER -> Color.RED;
            case LIGHT_GRAY_CONCRETE_POWDER -> Color.SILVER;
            case YELLOW_CONCRETE_POWDER -> Color.YELLOW;
            default -> Color.WHITE;
        };
        return PROPERTIES.getBlockState(CommonBlockProperties.COLOR.createValue(type));
    }
}
