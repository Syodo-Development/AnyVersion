package xyz.syodo.utils.transformer._1_20_30;

import cn.nukkit.block.BlockProperties;
import cn.nukkit.block.BlockState;
import cn.nukkit.block.property.CommonBlockProperties;
import cn.nukkit.block.property.enums.MinecraftVerticalHalf;
import cn.nukkit.block.property.type.BooleanPropertyType;
import xyz.syodo.utils.transformer.BlockStateTransformer;

public class BasicSlabTransformer extends BlockStateTransformer {

    BooleanPropertyType TOP_SLOT_BIT = BooleanPropertyType.of("top_slot_bit", false);

    @Override
    public BlockState transform(BlockState original) {
        BlockProperties PROPERTIES = new BlockProperties(original.getIdentifier(), TOP_SLOT_BIT);
        return PROPERTIES.getBlockState(TOP_SLOT_BIT.createValue(original.getPropertyValue(CommonBlockProperties.MINECRAFT_VERTICAL_HALF) == MinecraftVerticalHalf.TOP));
    }
}
