package xyz.syodo.utils.transformer._1_21_0;

import cn.nukkit.block.BlockProperties;
import cn.nukkit.block.BlockState;
import cn.nukkit.block.property.CommonBlockProperties;
import cn.nukkit.block.property.enums.StoneSlabType2;
import xyz.syodo.utils.transformer.BlockStateTransformer;

import static cn.nukkit.block.BlockID.*;

public class StoneBlockSlab2Transformer extends BlockStateTransformer {

    @Override
    public BlockState transform(BlockState original) {
        BlockProperties PROPERTIES = new BlockProperties(STONE_BLOCK_SLAB2, CommonBlockProperties.MINECRAFT_VERTICAL_HALF, CommonBlockProperties.STONE_SLAB_TYPE_2);
        StoneSlabType2 type = switch(original.getIdentifier()) {
            case PRISMARINE_SLAB -> StoneSlabType2.PRISMARINE_ROUGH;
            case DARK_PRISMARINE_SLAB -> StoneSlabType2.PRISMARINE_DARK;
            case SMOOTH_SANDSTONE_SLAB -> StoneSlabType2.SMOOTH_SANDSTONE;
            case PURPUR_SLAB -> StoneSlabType2.PURPUR;
            case RED_NETHER_BRICK_SLAB -> StoneSlabType2.RED_NETHER_BRICK;
            case PRISMARINE_BRICK_SLAB -> StoneSlabType2.PRISMARINE_BRICK;
            case MOSSY_COBBLESTONE_SLAB -> StoneSlabType2.MOSSY_COBBLESTONE;
            default -> StoneSlabType2.RED_SANDSTONE;
        };
        return PROPERTIES.getBlockState(
                CommonBlockProperties.MINECRAFT_VERTICAL_HALF.createValue(original.getPropertyValue(CommonBlockProperties.MINECRAFT_VERTICAL_HALF)),
                CommonBlockProperties.STONE_SLAB_TYPE_2.createValue(type)
        );
    }

}
