package xyz.syodo.utils.transformer.blocks._1_21_30;

import cn.nukkit.block.BlockProperties;
import cn.nukkit.block.BlockState;
import cn.nukkit.block.property.CommonBlockProperties;
import cn.nukkit.block.property.enums.WallBlockType;
import xyz.syodo.utils.transformer.blocks.BlockStateTransformer;

import static cn.nukkit.block.BlockID.*;

public class CobblestoneWallTransformer extends BlockStateTransformer {

    @Override
    public BlockState transform(BlockState original) {
        BlockProperties PROPERTIES = new BlockProperties(COBBLESTONE_WALL, CommonBlockProperties.WALL_BLOCK_TYPE, CommonBlockProperties.WALL_CONNECTION_TYPE_EAST, CommonBlockProperties.WALL_CONNECTION_TYPE_NORTH, CommonBlockProperties.WALL_CONNECTION_TYPE_SOUTH, CommonBlockProperties.WALL_CONNECTION_TYPE_WEST, CommonBlockProperties.WALL_POST_BIT);
        WallBlockType type = switch(original.getIdentifier()) {
            case ANDESITE_WALL -> WallBlockType.ANDESITE;
            case BRICK_WALL -> WallBlockType.BRICK;
            case DIORITE_WALL -> WallBlockType.DIORITE;
            case END_STONE_BRICK_WALL -> WallBlockType.END_BRICK;
            case GRANITE_WALL -> WallBlockType.GRANITE;
            case MOSSY_COBBLESTONE_WALL -> WallBlockType.MOSSY_COBBLESTONE;
            case MOSSY_STONE_BRICK_WALL -> WallBlockType.MOSSY_STONE_BRICK;
            case NETHER_BRICK_WALL -> WallBlockType.NETHER_BRICK;
            case PRISMARINE_WALL -> WallBlockType.PRISMARINE;
            case RED_NETHER_BRICK_WALL -> WallBlockType.RED_NETHER_BRICK;
            case RED_SANDSTONE_WALL -> WallBlockType.RED_SANDSTONE;
            case SANDSTONE_WALL -> WallBlockType.SANDSTONE;
            case STONE_BRICK_WALL -> WallBlockType.STONE_BRICK;
            default -> WallBlockType.COBBLESTONE;
        };
        return PROPERTIES.getBlockState(
                CommonBlockProperties.WALL_BLOCK_TYPE.createValue(type),
                CommonBlockProperties.WALL_CONNECTION_TYPE_EAST.createValue(original.getPropertyValue(CommonBlockProperties.WALL_CONNECTION_TYPE_EAST)),
                CommonBlockProperties.WALL_CONNECTION_TYPE_NORTH.createValue(original.getPropertyValue(CommonBlockProperties.WALL_CONNECTION_TYPE_NORTH)),
                CommonBlockProperties.WALL_CONNECTION_TYPE_SOUTH.createValue(original.getPropertyValue(CommonBlockProperties.WALL_CONNECTION_TYPE_SOUTH)),
                CommonBlockProperties.WALL_CONNECTION_TYPE_WEST.createValue(original.getPropertyValue(CommonBlockProperties.WALL_CONNECTION_TYPE_WEST)),
                CommonBlockProperties.WALL_POST_BIT.createValue(original.getPropertyValue(CommonBlockProperties.WALL_POST_BIT))
        );
    }
}
