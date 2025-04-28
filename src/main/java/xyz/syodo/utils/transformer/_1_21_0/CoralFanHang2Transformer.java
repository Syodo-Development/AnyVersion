package xyz.syodo.utils.transformer._1_21_0;

import cn.nukkit.block.BlockProperties;
import cn.nukkit.block.BlockState;
import cn.nukkit.block.property.CommonBlockProperties;
import xyz.syodo.utils.transformer.BlockStateTransformer;

import static cn.nukkit.block.BlockID.*;

public class CoralFanHang2Transformer extends BlockStateTransformer {

    @Override
    public BlockState transform(BlockState original) {
        BlockProperties PROPERTIES = new BlockProperties(CORAL_FAN_HANG2, CommonBlockProperties.CORAL_DIRECTION, CommonBlockProperties.CORAL_HANG_TYPE_BIT, CommonBlockProperties.DEAD_BIT);
        boolean dead = original.getIdentifier().startsWith("minecraft:dead_");
        boolean type = original.getIdentifier().contains("fire");
        return PROPERTIES.getBlockState(CommonBlockProperties.DEAD_BIT.createValue(dead),
                CommonBlockProperties.CORAL_HANG_TYPE_BIT.createValue(type),
                CommonBlockProperties.CORAL_DIRECTION.createValue(original.getPropertyValue(CommonBlockProperties.CORAL_DIRECTION)));
    }

}
