package xyz.syodo.utils.transformer.blocks._1_20_30;

import cn.nukkit.block.BlockProperties;
import cn.nukkit.block.BlockState;
import cn.nukkit.block.property.CommonBlockProperties;
import xyz.syodo.utils.transformer.blocks.BlockStateTransformer;

public class FacingToBlockTransformer extends BlockStateTransformer {

    @Override
    public BlockState transform(BlockState original) {
        BlockProperties PROPERTIES = new BlockProperties(original.getIdentifier(), CommonBlockProperties.FACING_DIRECTION);
        int direction = switch (original.getPropertyValue(CommonBlockProperties.MINECRAFT_BLOCK_FACE)) {
            case DOWN -> 0;
            case UP -> 1;
            case NORTH -> 2;
            case SOUTH -> 3;
            case WEST -> 4;
            case EAST -> 5;
        };
        return PROPERTIES.getBlockState(CommonBlockProperties.FACING_DIRECTION.createValue(direction));
    }
}
