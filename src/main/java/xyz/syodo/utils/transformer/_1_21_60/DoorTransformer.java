package xyz.syodo.utils.transformer._1_21_60;

import cn.nukkit.block.BlockProperties;
import cn.nukkit.block.BlockState;
import cn.nukkit.block.property.CommonBlockProperties;
import xyz.syodo.utils.transformer.BlockStateTransformer;

import static cn.nukkit.block.BlockID.SKULL;

public class DoorTransformer extends BlockStateTransformer {

    @Override
    public BlockState transform(BlockState original) {
        BlockProperties PROPERTIES = new BlockProperties(original.getIdentifier(), CommonBlockProperties.DIRECTION, CommonBlockProperties.DOOR_HINGE_BIT, CommonBlockProperties.OPEN_BIT, CommonBlockProperties.UPPER_BLOCK_BIT);
        int direction = switch (original.getPropertyValue(CommonBlockProperties.MINECRAFT_CARDINAL_DIRECTION)) {
            case SOUTH -> 0;
            case WEST -> 1;
            case NORTH -> 2;
            case EAST -> 3;
        };
        return PROPERTIES.getBlockState(
                CommonBlockProperties.DIRECTION.createValue(direction),
                CommonBlockProperties.DOOR_HINGE_BIT.createValue(original.getPropertyValue(CommonBlockProperties.DOOR_HINGE_BIT)),
                CommonBlockProperties.OPEN_BIT.createValue(original.getPropertyValue(CommonBlockProperties.OPEN_BIT)),
                CommonBlockProperties.UPPER_BLOCK_BIT.createValue(original.getPropertyValue(CommonBlockProperties.UPPER_BLOCK_BIT))
        );
    }
}
