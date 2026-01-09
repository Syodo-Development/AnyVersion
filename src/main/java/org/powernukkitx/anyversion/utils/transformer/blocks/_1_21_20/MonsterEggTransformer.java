package org.powernukkitx.anyversion.utils.transformer.blocks._1_21_20;

import cn.nukkit.block.BlockProperties;
import cn.nukkit.block.BlockState;
import cn.nukkit.block.property.CommonBlockProperties;
import cn.nukkit.block.property.enums.MonsterEggStoneType;
import org.powernukkitx.anyversion.utils.transformer.blocks.BlockStateTransformer;

import static cn.nukkit.block.BlockID.*;

public class MonsterEggTransformer extends BlockStateTransformer {

    @Override
    public BlockState transform(BlockState original) {
        BlockProperties PROPERTIES = new BlockProperties(MONSTER_EGG, CommonBlockProperties.MONSTER_EGG_STONE_TYPE);
        MonsterEggStoneType type = switch(original.getIdentifier()) {
            case INFESTED_COBBLESTONE -> MonsterEggStoneType.COBBLESTONE;
            case INFESTED_STONE_BRICKS -> MonsterEggStoneType.STONE_BRICK;
            case INFESTED_MOSSY_STONE_BRICKS -> MonsterEggStoneType.MOSSY_STONE_BRICK;
            case INFESTED_CRACKED_STONE_BRICKS -> MonsterEggStoneType.CRACKED_STONE_BRICK;
            case INFESTED_CHISELED_STONE_BRICKS -> MonsterEggStoneType.CHISELED_STONE_BRICK;
            default -> MonsterEggStoneType.STONE;
        };
        return PROPERTIES.getBlockState(CommonBlockProperties.MONSTER_EGG_STONE_TYPE, type);
    }

}
