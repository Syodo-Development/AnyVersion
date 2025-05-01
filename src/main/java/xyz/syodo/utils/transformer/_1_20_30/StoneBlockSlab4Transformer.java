package xyz.syodo.utils.transformer._1_20_30;

import cn.nukkit.block.BlockProperties;
import cn.nukkit.block.BlockState;
import cn.nukkit.block.property.CommonBlockProperties;
import cn.nukkit.block.property.enums.MinecraftVerticalHalf;
import cn.nukkit.block.property.type.BooleanPropertyType;
import xyz.syodo.utils.transformer.BlockStateTransformer;

public class StoneBlockSlab4Transformer extends BlockStateTransformer {

    BooleanPropertyType TOP_SLOT_BIT = BooleanPropertyType.of("top_slot_bit", false);

    @Override
    public BlockState transform(BlockState original) {
        BlockProperties PROPERTIES = new BlockProperties(original.getIdentifier(), TOP_SLOT_BIT, CommonBlockProperties.STONE_SLAB_TYPE_4);
        return PROPERTIES.getBlockState(
                TOP_SLOT_BIT.createValue(original.getPropertyValue(CommonBlockProperties.MINECRAFT_VERTICAL_HALF) == MinecraftVerticalHalf.TOP),
                CommonBlockProperties.STONE_SLAB_TYPE_4.createValue(original.getPropertyValue(CommonBlockProperties.STONE_SLAB_TYPE_4))
        );
    }
}
