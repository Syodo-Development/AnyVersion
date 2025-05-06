package xyz.syodo.utils.transformer.blocks._1_21_40;

import cn.nukkit.block.BlockProperties;
import cn.nukkit.block.BlockState;
import cn.nukkit.block.property.CommonBlockProperties;
import xyz.syodo.utils.transformer.blocks.BlockStateTransformer;

import static cn.nukkit.block.BlockID.*;

public class StemTransformer extends BlockStateTransformer {

    @Override
    public BlockState transform(BlockState original) {
        String identifier = switch (original.getIdentifier()) {
            case RED_MUSHROOM_BLOCK -> RED_MUSHROOM_BLOCK;
            default -> BROWN_MUSHROOM_BLOCK;
        };
        BlockProperties PROPERTIES = new BlockProperties(identifier, CommonBlockProperties.HUGE_MUSHROOM_BITS);
        int bits = switch (original.getIdentifier()) {
            case MUSHROOM_STEM -> 15;
            default -> original.getPropertyValue(CommonBlockProperties.HUGE_MUSHROOM_BITS);
        };
        return PROPERTIES.getBlockState(CommonBlockProperties.HUGE_MUSHROOM_BITS.createValue(bits));
    }
}
