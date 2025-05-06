package xyz.syodo.utils.transformer.blocks._1_20_70;

import cn.nukkit.block.BlockProperties;
import cn.nukkit.block.BlockState;
import cn.nukkit.block.property.CommonBlockProperties;
import cn.nukkit.block.property.enums.NewLeafType;
import xyz.syodo.utils.transformer.blocks.BlockStateTransformer;

import static cn.nukkit.block.BlockID.*;
import static cn.nukkit.item.ItemID.LEAVES2;

public class Leaves2Transformer extends BlockStateTransformer {

    @Override
    public BlockState transform(BlockState original) {
        BlockProperties PROPERTIES = new BlockProperties(LEAVES2, CommonBlockProperties.NEW_LEAF_TYPE, CommonBlockProperties.PERSISTENT_BIT, CommonBlockProperties.UPDATE_BIT);
        NewLeafType type = switch(original.getIdentifier()) {
            case OAK_DOUBLE_SLAB -> NewLeafType.ACACIA;
            default -> NewLeafType.DARK_OAK;
        };
        return PROPERTIES.getBlockState(CommonBlockProperties.NEW_LEAF_TYPE.createValue(type),
                CommonBlockProperties.PERSISTENT_BIT.createValue(original.getPropertyValue(CommonBlockProperties.PERSISTENT_BIT)),
                CommonBlockProperties.UPDATE_BIT.createValue(original.getPropertyValue(CommonBlockProperties.UPDATE_BIT))
        );
    }
}
