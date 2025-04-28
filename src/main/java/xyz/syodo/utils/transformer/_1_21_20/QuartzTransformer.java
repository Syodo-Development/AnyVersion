package xyz.syodo.utils.transformer._1_21_20;

import cn.nukkit.block.BlockProperties;
import cn.nukkit.block.BlockState;
import cn.nukkit.block.property.CommonBlockProperties;
import cn.nukkit.block.property.enums.ChiselType;
import xyz.syodo.utils.transformer.BlockStateTransformer;

import static cn.nukkit.block.BlockID.*;

public class QuartzTransformer extends BlockStateTransformer {

    @Override
    public BlockState transform(BlockState original) {
        BlockProperties PROPERTIES = new BlockProperties(QUARTZ_BLOCK, CommonBlockProperties.CHISEL_TYPE, CommonBlockProperties.PILLAR_AXIS);
        ChiselType type = switch(original.getIdentifier()) {
            case QUARTZ_PILLAR -> ChiselType.LINES;
            case CHISELED_QUARTZ_BLOCK -> ChiselType.CHISELED;
            case SMOOTH_QUARTZ -> ChiselType.SMOOTH;
            default -> ChiselType.DEFAULT;
        };
        return PROPERTIES.getBlockState(
                CommonBlockProperties.CHISEL_TYPE.createValue(type),
                CommonBlockProperties.PILLAR_AXIS.createValue(original.getPropertyValue(CommonBlockProperties.PILLAR_AXIS))
        );
    }
}
