package xyz.syodo.utils.transformer.blocks._1_20_30;

import cn.nukkit.block.BlockProperties;
import cn.nukkit.block.BlockState;
import cn.nukkit.block.property.CommonBlockProperties;
import xyz.syodo.utils.transformer.blocks.BlockStateTransformer;

public class BigDripleafTransformer extends BlockStateTransformer {

    @Override
    public BlockState transform(BlockState original) {
        BlockProperties PROPERTIES = new BlockProperties(original.getIdentifier(), CommonBlockProperties.DIRECTION, CommonBlockProperties.BIG_DRIPLEAF_TILT, CommonBlockProperties.BIG_DRIPLEAF_HEAD);
        int direction = switch (original.getPropertyValue(CommonBlockProperties.MINECRAFT_CARDINAL_DIRECTION)) {
            case SOUTH -> 0;
            case WEST -> 1;
            case NORTH -> 2;
            case EAST -> 3;
        };
        return PROPERTIES.getBlockState(
                CommonBlockProperties.DIRECTION.createValue(direction),
                CommonBlockProperties.BIG_DRIPLEAF_HEAD.createValue(original.getPropertyValue(CommonBlockProperties.BIG_DRIPLEAF_HEAD)),
                CommonBlockProperties.BIG_DRIPLEAF_TILT.createValue(original.getPropertyValue(CommonBlockProperties.BIG_DRIPLEAF_TILT))
        );
    }
}
