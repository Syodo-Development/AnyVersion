package xyz.syodo.utils.transformer._1_20_0;

import cn.nukkit.block.BlockProperties;
import cn.nukkit.block.BlockState;
import cn.nukkit.block.property.CommonBlockProperties;
import cn.nukkit.block.property.enums.Color;
import xyz.syodo.utils.transformer.BlockStateTransformer;

import static cn.nukkit.block.BlockID.*;
import static cn.nukkit.item.ItemID.CARPET;
import static cn.nukkit.item.ItemID.CONCRETE;

public class CarpetTransformer extends BlockStateTransformer {

    @Override
    public BlockState transform(BlockState original) {
        BlockProperties PROPERTIES = new BlockProperties(CARPET, CommonBlockProperties.COLOR);
        Color type = switch(original.getIdentifier()) {
            case BLACK_CARPET -> Color.BLACK;
            case BLUE_CARPET -> Color.BLUE;
            case BROWN_CARPET -> Color.BROWN;
            case CYAN_CARPET -> Color.CYAN;
            case GRAY_CARPET -> Color.GRAY;
            case GREEN_CARPET -> Color.GREEN;
            case LIGHT_BLUE_CARPET -> Color.LIGHT_BLUE;
            case LIME_CARPET -> Color.LIME;
            case MAGENTA_CARPET -> Color.MAGENTA;
            case ORANGE_CARPET -> Color.ORANGE;
            case PINK_CARPET -> Color.PINK;
            case PURPLE_CARPET -> Color.PURPLE;
            case RED_CARPET -> Color.RED;
            case LIGHT_GRAY_CARPET -> Color.SILVER;
            case YELLOW_CARPET -> Color.YELLOW;
            default -> Color.WHITE;
        };
        return PROPERTIES.getBlockState(CommonBlockProperties.COLOR.createValue(type));
    }
}
