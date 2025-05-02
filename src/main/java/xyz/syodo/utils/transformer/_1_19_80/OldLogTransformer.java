package xyz.syodo.utils.transformer._1_19_80;

import cn.nukkit.block.BlockProperties;
import cn.nukkit.block.BlockState;
import cn.nukkit.block.property.CommonBlockProperties;
import cn.nukkit.block.property.enums.CoralColor;
import cn.nukkit.block.property.enums.OldLogType;
import cn.nukkit.block.property.enums.WoodType;
import cn.nukkit.block.property.type.EnumPropertyType;
import xyz.syodo.utils.transformer.BlockStateTransformer;

import static cn.nukkit.block.BlockID.*;
import static cn.nukkit.item.ItemID.FENCE;
import static cn.nukkit.item.ItemID.LOG;

public class OldLogTransformer extends BlockStateTransformer {

    EnumPropertyType<OldLogType> OLD_LOG_TYPE = EnumPropertyType.of("old_log_type", OldLogType.class, OldLogType.values()[0]);

    @Override
    public BlockState transform(BlockState original) {
        BlockProperties PROPERTIES = new BlockProperties(LOG, OLD_LOG_TYPE, CommonBlockProperties.PILLAR_AXIS);
        OldLogType type = switch(original.getIdentifier()) {
            case OAK_LOG -> OldLogType.OAK;
            case SPRUCE_LOG -> OldLogType.SPRUCE;
            case BIRCH_LOG -> OldLogType.BIRCH;
            default -> OldLogType.JUNGLE;
        };
        return PROPERTIES.getBlockState(
                OLD_LOG_TYPE.createValue(type),
                CommonBlockProperties.PILLAR_AXIS.createValue(original.getPropertyValue(CommonBlockProperties.PILLAR_AXIS))
        );
    }
}
