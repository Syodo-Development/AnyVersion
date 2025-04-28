package xyz.syodo.utils.transformer._1_21_0;

import cn.nukkit.block.BlockProperties;
import cn.nukkit.block.BlockState;
import cn.nukkit.block.property.CommonBlockProperties;
import cn.nukkit.block.property.enums.DirtType;
import xyz.syodo.utils.transformer.BlockStateTransformer;

import static cn.nukkit.block.BlockID.*;

public class DirtStateTransformer extends BlockStateTransformer {

    @Override
    public BlockState transform(BlockState original) {
        BlockProperties PROPERTIES = new BlockProperties(DIRT, CommonBlockProperties.DIRT_TYPE);
        DirtType type = switch(original.getIdentifier()) {
            case COARSE_DIRT -> DirtType.COARSE;
            default -> DirtType.NORMAL;
        };
        return PROPERTIES.getBlockState(CommonBlockProperties.DIRT_TYPE, type);
    }

}
