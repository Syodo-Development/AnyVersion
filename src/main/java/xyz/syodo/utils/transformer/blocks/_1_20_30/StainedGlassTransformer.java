package xyz.syodo.utils.transformer.blocks._1_20_30;

import cn.nukkit.block.BlockProperties;
import cn.nukkit.block.BlockState;
import cn.nukkit.block.property.CommonBlockProperties;
import cn.nukkit.block.property.enums.Color;
import xyz.syodo.utils.transformer.blocks.BlockStateTransformer;

import static cn.nukkit.block.BlockID.*;
import static cn.nukkit.item.ItemID.STAINED_GLASS;

public class StainedGlassTransformer extends BlockStateTransformer {

    @Override
    public BlockState transform(BlockState original) {
        BlockProperties PROPERTIES = new BlockProperties(STAINED_GLASS, CommonBlockProperties.COLOR);
        Color type = switch(original.getIdentifier()) {
            case BLACK_STAINED_GLASS -> Color.BLACK;
            case BLUE_STAINED_GLASS -> Color.BLUE;
            case BROWN_STAINED_GLASS -> Color.BROWN;
            case CYAN_STAINED_GLASS -> Color.CYAN;
            case GRAY_STAINED_GLASS -> Color.GRAY;
            case GREEN_STAINED_GLASS -> Color.GREEN;
            case LIGHT_BLUE_STAINED_GLASS -> Color.LIGHT_BLUE;
            case LIME_STAINED_GLASS -> Color.LIME;
            case MAGENTA_STAINED_GLASS -> Color.MAGENTA;
            case ORANGE_STAINED_GLASS -> Color.ORANGE;
            case PINK_STAINED_GLASS -> Color.PINK;
            case PURPLE_STAINED_GLASS -> Color.PURPLE;
            case RED_STAINED_GLASS -> Color.RED;
            case LIGHT_GRAY_STAINED_GLASS -> Color.SILVER;
            case YELLOW_STAINED_GLASS -> Color.YELLOW;
            default -> Color.WHITE;
        };
        return PROPERTIES.getBlockState(CommonBlockProperties.COLOR.createValue(type));
    }
}
