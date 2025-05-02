package xyz.syodo.utils.transformer._1_20_0;

import cn.nukkit.block.BlockProperties;
import cn.nukkit.block.BlockState;
import cn.nukkit.block.property.CommonBlockProperties;
import xyz.syodo.utils.transformer.BlockStateTransformer;

public class SculkSensorTransformer extends BlockStateTransformer {

    @Override
    public BlockState transform(BlockState original) {
        BlockProperties PROPERTIES = new BlockProperties(original.getIdentifier(), CommonBlockProperties.DIRECTION, CommonBlockProperties.POWERED_BIT);
        return PROPERTIES.getBlockState(
                CommonBlockProperties.POWERED_BIT.createValue(original.getPropertyValue(CommonBlockProperties.SCULK_SENSOR_PHASE) != 0),
                CommonBlockProperties.DIRECTION.createValue(original.getPropertyValue(CommonBlockProperties.DIRECTION))
        );
    }
}
