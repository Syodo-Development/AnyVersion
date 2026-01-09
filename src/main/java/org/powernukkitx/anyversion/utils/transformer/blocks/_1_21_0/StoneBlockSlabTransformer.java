package org.powernukkitx.anyversion.utils.transformer.blocks._1_21_0;

import cn.nukkit.block.BlockProperties;
import cn.nukkit.block.BlockState;
import cn.nukkit.block.property.CommonBlockProperties;
import cn.nukkit.block.property.enums.StoneSlabType;
import org.powernukkitx.anyversion.utils.transformer.blocks.BlockStateTransformer;

import static cn.nukkit.block.BlockID.*;
import static cn.nukkit.item.ItemID.STONE_BLOCK_SLAB;

public class StoneBlockSlabTransformer extends BlockStateTransformer {

    @Override
    public BlockState transform(BlockState original) {
        BlockProperties PROPERTIES = new BlockProperties(STONE_BLOCK_SLAB, CommonBlockProperties.MINECRAFT_VERTICAL_HALF, CommonBlockProperties.STONE_SLAB_TYPE);
        StoneSlabType type = switch(original.getIdentifier()) {
            case SMOOTH_STONE_SLAB -> StoneSlabType.SMOOTH_STONE;
            case SANDSTONE_SLAB -> StoneSlabType.SANDSTONE;
            case PETRIFIED_OAK_SLAB -> StoneSlabType.WOOD;
            case COBBLESTONE_SLAB -> StoneSlabType.COBBLESTONE;
            case BRICK_SLAB -> StoneSlabType.BRICK;
            case STONE_BRICK_SLAB -> StoneSlabType.STONE_BRICK;
            case QUARTZ_SLAB -> StoneSlabType.QUARTZ;
            default -> StoneSlabType.NETHER_BRICK;
        };
        return PROPERTIES.getBlockState(
                CommonBlockProperties.MINECRAFT_VERTICAL_HALF.createValue(original.getPropertyValue(CommonBlockProperties.MINECRAFT_VERTICAL_HALF)),
                CommonBlockProperties.STONE_SLAB_TYPE.createValue(type)
        );
    }

}
