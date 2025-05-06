package xyz.syodo.utils.transformer.blocks._1_21_20;

import cn.nukkit.block.BlockProperties;
import cn.nukkit.block.BlockState;
import cn.nukkit.block.property.enums.SandStoneType;
import cn.nukkit.block.property.type.BlockPropertyType;
import xyz.syodo.utils.transformer.blocks.BlockStateTransformer;

import java.util.stream.Collectors;

import static cn.nukkit.block.BlockID.*;
import static xyz.syodo.utils.transformer.blocks._1_21_20.SandstoneTransformer.SAND_STONE_TYPE;

public class RedSandstoneTransformer extends BlockStateTransformer {

    @Override
    public BlockState transform(BlockState original) {
        BlockProperties PROPERTIES = new BlockProperties(RED_SANDSTONE, SAND_STONE_TYPE);
        if(PROPERTIES.getPropertyTypeSet().equals(original.getBlockPropertyValues().stream().map(BlockPropertyType.BlockPropertyValue::getPropertyType).collect(Collectors.toSet()))) return original;

        SandStoneType type = switch(original.getIdentifier()) {
            case CUT_RED_SANDSTONE -> SandStoneType.CUT;
            case CHISELED_RED_SANDSTONE -> SandStoneType.HEIROGLYPHS;
            case SMOOTH_RED_SANDSTONE -> SandStoneType.SMOOTH;
            default -> SandStoneType.DEFAULT;
        };
        return PROPERTIES.getBlockState(SAND_STONE_TYPE.createValue(type));
    }
}
