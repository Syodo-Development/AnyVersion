package org.powernukkitx.anyversion.utils.transformer.blocks._1_20_60;

import cn.nukkit.block.BlockProperties;
import cn.nukkit.block.BlockState;
import cn.nukkit.block.property.CommonBlockProperties;
import cn.nukkit.block.property.enums.Color;
import org.powernukkitx.anyversion.utils.transformer.blocks.BlockStateTransformer;

import static cn.nukkit.block.BlockID.*;
import static cn.nukkit.item.ItemID.HARD_STAINED_GLASS_PANE;

public class HardStainedGlassPaneTransformer extends BlockStateTransformer {

    @Override
    public BlockState transform(BlockState original) {
        BlockProperties PROPERTIES = new BlockProperties(HARD_STAINED_GLASS_PANE, CommonBlockProperties.COLOR);
        Color type = switch(original.getIdentifier()) {
            case HARD_BLACK_STAINED_GLASS_PANE -> Color.BLACK;
            case HARD_BLUE_STAINED_GLASS_PANE -> Color.BLUE;
            case HARD_BROWN_STAINED_GLASS_PANE -> Color.BROWN;
            case HARD_CYAN_STAINED_GLASS_PANE -> Color.CYAN;
            case HARD_GRAY_STAINED_GLASS_PANE -> Color.GRAY;
            case HARD_GREEN_STAINED_GLASS_PANE -> Color.GREEN;
            case HARD_LIGHT_BLUE_STAINED_GLASS_PANE -> Color.LIGHT_BLUE;
            case HARD_LIME_STAINED_GLASS_PANE -> Color.LIME;
            case HARD_MAGENTA_STAINED_GLASS_PANE -> Color.MAGENTA;
            case HARD_ORANGE_STAINED_GLASS_PANE -> Color.ORANGE;
            case HARD_PINK_STAINED_GLASS_PANE -> Color.PINK;
            case HARD_PURPLE_STAINED_GLASS_PANE -> Color.PURPLE;
            case HARD_RED_STAINED_GLASS_PANE -> Color.RED;
            case HARD_LIGHT_GRAY_STAINED_GLASS_PANE -> Color.SILVER;
            case HARD_YELLOW_STAINED_GLASS_PANE -> Color.YELLOW;
            default -> Color.WHITE;
        };
        return PROPERTIES.getBlockState(CommonBlockProperties.COLOR.createValue(type));
    }
}
