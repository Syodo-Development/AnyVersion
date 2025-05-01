package xyz.syodo.utils.transformer._1_20_10;

import cn.nukkit.block.BlockProperties;
import cn.nukkit.block.BlockState;
import cn.nukkit.block.property.CommonBlockProperties;
import cn.nukkit.block.property.enums.Color;
import xyz.syodo.utils.transformer.BlockStateTransformer;

import static cn.nukkit.block.BlockID.*;
import static cn.nukkit.item.ItemID.CONCRETE;

public class ConcreteTransformer extends BlockStateTransformer {

    @Override
    public BlockState transform(BlockState original) {
        BlockProperties PROPERTIES = new BlockProperties(CONCRETE, CommonBlockProperties.COLOR);
        Color type = switch(original.getIdentifier()) {
            case BLACK_CONCRETE -> Color.BLACK;
            case BLUE_CONCRETE -> Color.BLUE;
            case BROWN_CONCRETE -> Color.BROWN;
            case CYAN_CONCRETE -> Color.CYAN;
            case GRAY_CONCRETE -> Color.GRAY;
            case GREEN_CONCRETE -> Color.GREEN;
            case LIGHT_BLUE_CONCRETE -> Color.LIGHT_BLUE;
            case LIME_CONCRETE -> Color.LIME;
            case MAGENTA_CONCRETE -> Color.MAGENTA;
            case ORANGE_CONCRETE -> Color.ORANGE;
            case PINK_CONCRETE -> Color.PINK;
            case PURPLE_CONCRETE -> Color.PURPLE;
            case RED_CONCRETE -> Color.RED;
            case LIGHT_GRAY_CONCRETE -> Color.SILVER;
            case YELLOW_CONCRETE -> Color.YELLOW;
            default -> Color.WHITE;
        };
        return PROPERTIES.getBlockState(CommonBlockProperties.COLOR.createValue(type));
    }
}
