package xyz.syodo.utils.transformer.blocks._1_21_20;

import cn.nukkit.block.BlockProperties;
import cn.nukkit.block.BlockState;
import cn.nukkit.block.property.CommonBlockProperties;
import xyz.syodo.utils.transformer.blocks.BlockStateTransformer;

import static cn.nukkit.block.BlockID.CORAL_FAN_HANG;

public class CoralFanHang3Transformer extends BlockStateTransformer {

    @Override
    public BlockState transform(BlockState original) {
        BlockProperties PROPERTIES = new BlockProperties(CORAL_FAN_HANG, CommonBlockProperties.CORAL_DIRECTION, CommonBlockProperties.CORAL_HANG_TYPE_BIT, CommonBlockProperties.DEAD_BIT);
        boolean dead = original.getIdentifier().startsWith("minecraft:dead_");
        return PROPERTIES.getBlockState(CommonBlockProperties.DEAD_BIT.createValue(dead),
                CommonBlockProperties.CORAL_HANG_TYPE_BIT.createValue(false),
                CommonBlockProperties.CORAL_DIRECTION.createValue(original.getPropertyValue(CommonBlockProperties.CORAL_DIRECTION)));
    }

}
