package xyz.syodo.utils.transformer._712;

import cn.nukkit.block.BlockID;
import cn.nukkit.block.BlockProperties;
import cn.nukkit.block.BlockState;
import cn.nukkit.block.property.CommonBlockProperties;
import cn.nukkit.block.property.enums.DirtType;
import xyz.syodo.utils.transformer.BlockStateTransformer;

public class DirtStateTransformer extends BlockStateTransformer {

    @Override
    public BlockState transform(BlockState original) {
        BlockProperties PROPERTIES = new BlockProperties(BlockID.DIRT, CommonBlockProperties.DIRT_TYPE);
        DirtType type = switch(original.getIdentifier()) {
            case BlockID.COARSE_DIRT -> DirtType.COARSE;
            default -> DirtType.NORMAL;
        };
        return PROPERTIES.getBlockState(CommonBlockProperties.DIRT_TYPE, type);
    }

}
