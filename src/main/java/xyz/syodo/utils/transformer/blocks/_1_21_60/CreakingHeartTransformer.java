package xyz.syodo.utils.transformer.blocks._1_21_60;

import cn.nukkit.block.BlockProperties;
import cn.nukkit.block.BlockState;
import cn.nukkit.block.property.CommonBlockProperties;
import cn.nukkit.block.property.enums.CreakingHeartState;
import xyz.syodo.utils.transformer.blocks.BlockStateTransformer;

import static cn.nukkit.block.BlockID.CREAKING_HEART;

public class CreakingHeartTransformer extends BlockStateTransformer {

    @Override
    public BlockState transform(BlockState original) {
        BlockProperties PROPERTIES = new BlockProperties(CREAKING_HEART, CommonBlockProperties.PILLAR_AXIS, CommonBlockProperties.ACTIVE, CommonBlockProperties.NATURAL);
        CreakingHeartState type = original.getPropertyValue(CommonBlockProperties.CREAKING_HEART_STATE);
        return PROPERTIES.getBlockState(
                CommonBlockProperties.ACTIVE.createValue(type != CreakingHeartState.UPROOTED),
                CommonBlockProperties.PILLAR_AXIS.createValue(original.getPropertyValue(CommonBlockProperties.PILLAR_AXIS)),
                CommonBlockProperties.NATURAL.createValue(original.getPropertyValue(CommonBlockProperties.NATURAL))
        );
    }
}
