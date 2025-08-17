package xyz.syodo.utils.transformer.blocks._1_21_20;

import cn.nukkit.block.BlockProperties;
import cn.nukkit.block.BlockState;
import cn.nukkit.block.property.CommonBlockProperties;
import cn.nukkit.block.property.enums.StoneBrickType;
import xyz.syodo.utils.transformer.blocks.BlockStateTransformer;

import static cn.nukkit.block.BlockID.*;

public class StoneBrickTransformer extends BlockStateTransformer {

    public static final String STONEBRICK = "minecraft:stonebrick";

    @Override
    public BlockState transform(BlockState original) {
        BlockProperties PROPERTIES = new BlockProperties(STONEBRICK, CommonBlockProperties.STONE_BRICK_TYPE);
        StoneBrickType type = switch(original.getIdentifier()) {
            case MOSSY_STONE_BRICKS -> StoneBrickType.MOSSY;
            case CRACKED_STONE_BRICKS -> StoneBrickType.CRACKED;
            case CHISELED_STONE_BRICKS -> StoneBrickType.CHISELED;
            default -> StoneBrickType.DEFAULT;
        };
        return PROPERTIES.getBlockState(CommonBlockProperties.STONE_BRICK_TYPE, type);
    }

}
