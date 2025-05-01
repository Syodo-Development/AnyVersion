package xyz.syodo.utils.transformer._1_20_70;

import cn.nukkit.block.BlockProperties;
import cn.nukkit.block.BlockState;
import cn.nukkit.block.property.CommonBlockProperties;
import cn.nukkit.block.property.enums.OldLeafType;
import xyz.syodo.utils.transformer.BlockStateTransformer;

import static cn.nukkit.block.BlockID.*;
import static cn.nukkit.item.ItemID.LEAVES;

public class LeavesTransformer extends BlockStateTransformer {

    @Override
    public BlockState transform(BlockState original) {
        BlockProperties PROPERTIES = new BlockProperties(LEAVES, CommonBlockProperties.OLD_LEAF_TYPE, CommonBlockProperties.PERSISTENT_BIT, CommonBlockProperties.UPDATE_BIT);
        OldLeafType type = switch(original.getIdentifier()) {
            case OAK_DOUBLE_SLAB -> OldLeafType.OAK;
            case SPRUCE_DOUBLE_SLAB -> OldLeafType.SPRUCE;
            case BIRCH_DOUBLE_SLAB -> OldLeafType.BIRCH;
            default -> OldLeafType.JUNGLE;
        };
        return PROPERTIES.getBlockState(CommonBlockProperties.OLD_LEAF_TYPE.createValue(type),
                CommonBlockProperties.PERSISTENT_BIT.createValue(original.getPropertyValue(CommonBlockProperties.PERSISTENT_BIT)),
                CommonBlockProperties.UPDATE_BIT.createValue(original.getPropertyValue(CommonBlockProperties.UPDATE_BIT))
        );
    }
}
