package xyz.syodo.utils.transformer.blocks._1_20_30;

import cn.nukkit.block.BlockProperties;
import cn.nukkit.block.BlockState;
import cn.nukkit.block.property.CommonBlockProperties;
import cn.nukkit.block.property.enums.MinecraftVerticalHalf;
import cn.nukkit.block.property.type.BooleanPropertyType;
import xyz.syodo.utils.transformer.blocks.BlockStateTransformer;

public class StoneBlockSlab3Transformer extends BlockStateTransformer {

    BooleanPropertyType TOP_SLOT_BIT = BooleanPropertyType.of("top_slot_bit", false);

    @Override
    public BlockState transform(BlockState original) {
        BlockProperties PROPERTIES = new BlockProperties(original.getIdentifier(), TOP_SLOT_BIT, CommonBlockProperties.STONE_SLAB_TYPE_3);
        return PROPERTIES.getBlockState(
                TOP_SLOT_BIT.createValue(original.getPropertyValue(CommonBlockProperties.MINECRAFT_VERTICAL_HALF) == MinecraftVerticalHalf.TOP),
                CommonBlockProperties.STONE_SLAB_TYPE_3.createValue(original.getPropertyValue(CommonBlockProperties.STONE_SLAB_TYPE_3))
        );
    }
}
