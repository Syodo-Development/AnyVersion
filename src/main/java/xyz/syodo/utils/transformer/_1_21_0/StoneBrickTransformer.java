package xyz.syodo.utils.transformer._1_21_0;

import cn.nukkit.block.BlockProperties;
import cn.nukkit.block.BlockState;
import cn.nukkit.block.property.CommonBlockProperties;
import cn.nukkit.block.property.enums.MonsterEggStoneType;
import cn.nukkit.block.property.enums.StoneBrickType;
import xyz.syodo.utils.transformer.BlockStateTransformer;

import static cn.nukkit.block.BlockID.*;

public class StoneBrickTransformer extends BlockStateTransformer {

    @Override
    public BlockState transform(BlockState original) {
        BlockProperties PROPERTIES = new BlockProperties("minecraft:stonebrick", CommonBlockProperties.STONE_BRICK_TYPE);
        StoneBrickType type = switch(original.getIdentifier()) {
            case MOSSY_STONE_BRICKS -> StoneBrickType.MOSSY;
            case CRACKED_STONE_BRICKS -> StoneBrickType.CRACKED;
            case CHISELED_STONE_BRICKS -> StoneBrickType.CHISELED;
            default -> StoneBrickType.DEFAULT;
        };
        return PROPERTIES.getBlockState(CommonBlockProperties.STONE_BRICK_TYPE, type);
    }

}
