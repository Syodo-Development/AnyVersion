package xyz.syodo.utils.transformer.blocks._1_21_20;

import cn.nukkit.block.BlockProperties;
import cn.nukkit.block.BlockState;
import cn.nukkit.block.property.CommonBlockProperties;
import cn.nukkit.block.property.enums.PrismarineBlockType;
import xyz.syodo.utils.transformer.blocks.BlockStateTransformer;

import static cn.nukkit.block.BlockID.*;

public class PrismarineTransformer extends BlockStateTransformer {

    @Override
    public BlockState transform(BlockState original) {
        BlockProperties PROPERTIES = new BlockProperties(PRISMARINE, CommonBlockProperties.PRISMARINE_BLOCK_TYPE);
        PrismarineBlockType type = switch(original.getIdentifier()) {
            case PRISMARINE_BRICKS -> PrismarineBlockType.BRICKS;
            case DARK_PRISMARINE -> PrismarineBlockType.DARK;
            default -> PrismarineBlockType.DEFAULT;
        };
        return PROPERTIES.getBlockState(CommonBlockProperties.PRISMARINE_BLOCK_TYPE.createValue(type));
    }

}
