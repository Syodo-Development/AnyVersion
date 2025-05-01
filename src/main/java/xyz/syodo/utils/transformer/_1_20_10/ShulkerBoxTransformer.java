package xyz.syodo.utils.transformer._1_20_10;

import cn.nukkit.block.BlockProperties;
import cn.nukkit.block.BlockState;
import cn.nukkit.block.property.CommonBlockProperties;
import cn.nukkit.block.property.enums.Color;
import xyz.syodo.utils.transformer.BlockStateTransformer;

import static cn.nukkit.block.BlockID.*;
import static cn.nukkit.item.ItemID.SHULKER_BOX;

public class ShulkerBoxTransformer extends BlockStateTransformer {

    @Override
    public BlockState transform(BlockState original) {
        BlockProperties PROPERTIES = new BlockProperties(SHULKER_BOX, CommonBlockProperties.COLOR);
        Color type = switch(original.getIdentifier()) {
            case BLACK_SHULKER_BOX -> Color.BLACK;
            case BLUE_SHULKER_BOX -> Color.BLUE;
            case BROWN_SHULKER_BOX -> Color.BROWN;
            case CYAN_SHULKER_BOX -> Color.CYAN;
            case GRAY_SHULKER_BOX -> Color.GRAY;
            case GREEN_SHULKER_BOX -> Color.GREEN;
            case LIGHT_BLUE_SHULKER_BOX -> Color.LIGHT_BLUE;
            case LIME_SHULKER_BOX -> Color.LIME;
            case MAGENTA_SHULKER_BOX -> Color.MAGENTA;
            case ORANGE_SHULKER_BOX -> Color.ORANGE;
            case PINK_SHULKER_BOX -> Color.PINK;
            case PURPLE_SHULKER_BOX -> Color.PURPLE;
            case RED_SHULKER_BOX -> Color.RED;
            case LIGHT_GRAY_SHULKER_BOX -> Color.SILVER;
            case YELLOW_SHULKER_BOX -> Color.YELLOW;
            default -> Color.WHITE;
        };
        return PROPERTIES.getBlockState(CommonBlockProperties.COLOR.createValue(type));
    }
}
