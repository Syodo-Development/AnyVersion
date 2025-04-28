package xyz.syodo.utils.transformer._1_21_20;

import cn.nukkit.block.BlockProperties;
import cn.nukkit.block.BlockState;
import cn.nukkit.block.property.enums.SandStoneType;
import cn.nukkit.block.property.type.EnumPropertyType;
import xyz.syodo.utils.transformer.BlockStateTransformer;

import static cn.nukkit.block.BlockID.*;

public class SandstoneTransformer extends BlockStateTransformer {

    @Override
    public BlockState transform(BlockState original) {
        BlockProperties PROPERTIES = new BlockProperties(SANDSTONE, SAND_STONE_TYPE);
        SandStoneType type = switch(original.getIdentifier()) {
            case CUT_SANDSTONE -> SandStoneType.CUT;
            case CHISELED_SANDSTONE -> SandStoneType.HEIROGLYPHS;
            case SMOOTH_SANDSTONE -> SandStoneType.SMOOTH;
            default -> SandStoneType.DEFAULT;
        };
        return PROPERTIES.getBlockState(SAND_STONE_TYPE.createValue(type));
    }

    public static EnumPropertyType<SandStoneType> SAND_STONE_TYPE = EnumPropertyType.of("sand_stone_type", SandStoneType.class, SandStoneType.values()[0]);
}
