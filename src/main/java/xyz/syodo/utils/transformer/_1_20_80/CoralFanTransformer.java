package xyz.syodo.utils.transformer._1_20_80;

import cn.nukkit.block.BlockProperties;
import cn.nukkit.block.BlockState;
import cn.nukkit.block.property.CommonBlockProperties;
import cn.nukkit.block.property.enums.CoralColor;
import cn.nukkit.block.property.type.EnumPropertyType;
import xyz.syodo.utils.transformer.BlockStateTransformer;

import static cn.nukkit.block.BlockID.*;

public class CoralFanTransformer extends BlockStateTransformer {

    private static final String CORAL_FAN = "minecraft:coral_fan";

    EnumPropertyType<CoralColor> CORAL_COLOR = EnumPropertyType.of("coral_color", CoralColor.class, CoralColor.values()[0]);


    @Override
    public BlockState transform(BlockState original) {
        BlockProperties PROPERTIES = new BlockProperties(CORAL_FAN, CORAL_COLOR, CommonBlockProperties.CORAL_FAN_DIRECTION);
        CoralColor type = switch(original.getIdentifier()) {
            case TUBE_CORAL_FAN -> CoralColor.BLUE;
            case BRAIN_CORAL_FAN -> CoralColor.PINK;
            case FIRE_CORAL_FAN -> CoralColor.RED;
            case BUBBLE_CORAL_FAN -> CoralColor.PURPLE;
            default -> CoralColor.YELLOW;
        };
        return PROPERTIES.getBlockState(CORAL_COLOR.createValue(type),
                CommonBlockProperties.CORAL_FAN_DIRECTION.createValue(original.getPropertyValue(CommonBlockProperties.CORAL_FAN_DIRECTION)));
    }

}
