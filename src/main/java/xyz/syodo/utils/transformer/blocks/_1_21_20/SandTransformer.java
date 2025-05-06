package xyz.syodo.utils.transformer.blocks._1_21_20;

import cn.nukkit.block.BlockProperties;
import cn.nukkit.block.BlockState;
import cn.nukkit.block.property.CommonBlockProperties;
import cn.nukkit.block.property.enums.SandType;
import xyz.syodo.utils.transformer.blocks.BlockStateTransformer;

import static cn.nukkit.block.BlockID.*;

public class SandTransformer extends BlockStateTransformer {

    @Override
    public BlockState transform(BlockState original) {
        BlockProperties PROPERTIES = new BlockProperties(SAND, CommonBlockProperties.SAND_TYPE);
        SandType type = switch(original.getIdentifier()) {
            case RED_SAND -> SandType.RED;
            default -> SandType.NORMAL;
        };
        return PROPERTIES.getBlockState(CommonBlockProperties.SAND_TYPE, type);
    }

}
