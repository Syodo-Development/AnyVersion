package xyz.syodo.utils.transformer.blocks._1_20_50;

import cn.nukkit.block.BlockProperties;
import cn.nukkit.block.BlockState;
import cn.nukkit.block.property.type.EnumPropertyType;
import xyz.syodo.utils.transformer.blocks.BlockStateTransformer;

import static cn.nukkit.block.BlockID.*;

public class StoneTransformer extends BlockStateTransformer {

    EnumPropertyType<StoneType> STONE_TYPE = EnumPropertyType.of("stone_type", StoneType.class, StoneType.values()[0]);

    @Override
    public BlockState transform(BlockState original) {

        BlockProperties PROPERTIES = new BlockProperties(STONE, STONE_TYPE);
        StoneType type = switch(original.getIdentifier()) {
            case GRANITE -> StoneType.GRANITE;
            case DIORITE -> StoneType.DIORITE;
            case ANDESITE -> StoneType.ANDESITE;
            case POLISHED_GRANITE -> StoneType.GRANITE_SMOOTH;
            case POLISHED_DIORITE -> StoneType.DIORITE_SMOOTH;
            case POLISHED_ANDESITE -> StoneType.ANDESITE_SMOOTH;
            default -> StoneType.STONE;
        };
        return PROPERTIES.getBlockState(STONE_TYPE.createValue(type));
    }

    private enum StoneType {
        STONE,
        GRANITE,
        GRANITE_SMOOTH,
        DIORITE,
        DIORITE_SMOOTH,
        ANDESITE,
        ANDESITE_SMOOTH
    }
}
