package xyz.syodo.utils.transformer._1_20_40;

import cn.nukkit.block.BlockProperties;
import cn.nukkit.block.BlockState;
import cn.nukkit.block.property.CommonBlockProperties;
import xyz.syodo.utils.transformer.BlockStateTransformer;

public class FacingToCardinalTransformer extends BlockStateTransformer {

    @Override
    public BlockState transform(BlockState original) {
        BlockProperties PROPERTIES = new BlockProperties(original.getIdentifier(), CommonBlockProperties.FACING_DIRECTION);
        int direction = switch (original.getPropertyValue(CommonBlockProperties.MINECRAFT_CARDINAL_DIRECTION)) {
            case NORTH -> 2;
            case SOUTH -> 3;
            case WEST -> 4;
            default -> 5;
        };
        return PROPERTIES.getBlockState(CommonBlockProperties.FACING_DIRECTION.createValue(direction));
    }
}
