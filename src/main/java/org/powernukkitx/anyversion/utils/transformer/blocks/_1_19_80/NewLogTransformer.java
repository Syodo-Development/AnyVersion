package org.powernukkitx.anyversion.utils.transformer.blocks._1_19_80;

import cn.nukkit.block.BlockProperties;
import cn.nukkit.block.BlockState;
import cn.nukkit.block.property.CommonBlockProperties;
import cn.nukkit.block.property.enums.NewLogType;
import cn.nukkit.block.property.type.EnumPropertyType;
import org.powernukkitx.anyversion.utils.transformer.blocks.BlockStateTransformer;

import static cn.nukkit.block.BlockID.*;
import static cn.nukkit.item.ItemID.LOG;

public class NewLogTransformer extends BlockStateTransformer {

    EnumPropertyType<NewLogType> NEW_LOG_TYPE = EnumPropertyType.of("new_log_type", NewLogType.class, NewLogType.values()[0]);

    @Override
    public BlockState transform(BlockState original) {
        BlockProperties PROPERTIES = new BlockProperties(LOG, NEW_LOG_TYPE, CommonBlockProperties.PILLAR_AXIS);
        NewLogType type = switch(original.getIdentifier()) {
            case ACACIA_LOG -> NewLogType.ACACIA;
            default -> NewLogType.DARK_OAK;
        };
        return PROPERTIES.getBlockState(
                NEW_LOG_TYPE.createValue(type),
                CommonBlockProperties.PILLAR_AXIS.createValue(original.getPropertyValue(CommonBlockProperties.PILLAR_AXIS))
        );
    }
}
