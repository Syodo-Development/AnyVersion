package xyz.syodo.utils.transformer.blocks._1_20_0;

import cn.nukkit.block.BlockProperties;
import cn.nukkit.block.BlockState;
import cn.nukkit.block.property.CommonBlockProperties;
import cn.nukkit.block.property.enums.CoralColor;
import xyz.syodo.utils.transformer.blocks.BlockStateTransformer;

import static cn.nukkit.block.BlockID.*;
import static cn.nukkit.item.ItemID.CORAL;

public class CoralTransformer extends BlockStateTransformer {

    @Override
    public BlockState transform(BlockState original) {
        BlockProperties PROPERTIES = new BlockProperties(CORAL, CommonBlockProperties.CORAL_COLOR, CommonBlockProperties.DEAD_BIT);
        boolean dead = original.getIdentifier().startsWith("minecraft:dead_");
        CoralColor type = switch(original.getIdentifier().replace("dead_", "")) {
            case FIRE_CORAL -> CoralColor.RED;
            case BRAIN_CORAL -> CoralColor.PINK;
            case TUBE_CORAL -> CoralColor.BLUE;
            case HORN_CORAL -> CoralColor.YELLOW;
            default -> CoralColor.PURPLE;
        };
        return PROPERTIES.getBlockState(
                CommonBlockProperties.CORAL_COLOR.createValue(type),
                CommonBlockProperties.DEAD_BIT.createValue(dead)
        );
    }
}
