package xyz.syodo.utils.transformer._1_20_30;

import cn.nukkit.block.BlockProperties;
import cn.nukkit.block.BlockState;
import cn.nukkit.block.property.CommonBlockProperties;
import cn.nukkit.block.property.enums.Color;
import xyz.syodo.utils.transformer.BlockStateTransformer;

import static cn.nukkit.block.BlockID.*;
import static cn.nukkit.item.ItemID.STAINED_GLASS_PANE;

public class StainedGlassPaneTransformer extends BlockStateTransformer {

    @Override
    public BlockState transform(BlockState original) {
        BlockProperties PROPERTIES = new BlockProperties(STAINED_GLASS_PANE, CommonBlockProperties.COLOR);
        Color type = switch(original.getIdentifier()) {
            case BLACK_STAINED_GLASS_PANE -> Color.BLACK;
            case BLUE_STAINED_GLASS_PANE -> Color.BLUE;
            case BROWN_STAINED_GLASS_PANE -> Color.BROWN;
            case CYAN_STAINED_GLASS_PANE -> Color.CYAN;
            case GRAY_STAINED_GLASS_PANE -> Color.GRAY;
            case GREEN_STAINED_GLASS_PANE -> Color.GREEN;
            case LIGHT_BLUE_STAINED_GLASS_PANE -> Color.LIGHT_BLUE;
            case LIME_STAINED_GLASS_PANE -> Color.LIME;
            case MAGENTA_STAINED_GLASS_PANE -> Color.MAGENTA;
            case ORANGE_STAINED_GLASS_PANE -> Color.ORANGE;
            case PINK_STAINED_GLASS_PANE -> Color.PINK;
            case PURPLE_STAINED_GLASS_PANE -> Color.PURPLE;
            case RED_STAINED_GLASS_PANE -> Color.RED;
            case LIGHT_GRAY_STAINED_GLASS_PANE -> Color.SILVER;
            case YELLOW_STAINED_GLASS_PANE -> Color.YELLOW;
            default -> Color.WHITE;
        };
        return PROPERTIES.getBlockState(CommonBlockProperties.COLOR.createValue(type));
    }
}
