package xyz.syodo.utils.transformer.blocks._1_21_110;

import cn.nukkit.block.BlockProperties;
import cn.nukkit.block.BlockState;
import cn.nukkit.block.property.CommonBlockProperties;
import cn.nukkit.block.property.enums.Color;
import cn.nukkit.item.ItemID;
import xyz.syodo.utils.transformer.blocks.BlockStateTransformer;

import static cn.nukkit.block.BlockID.*;
import static cn.nukkit.block.property.CommonBlockProperties.PILLAR_AXIS;
import static cn.nukkit.item.ItemID.HARD_STAINED_GLASS;

public class IronChainTransformer extends BlockStateTransformer {

    public static final String CHAIN = "minecraft:chain";

    @Override
    public BlockState transform(BlockState original) {
        BlockProperties PROPERTIES = new BlockProperties(CHAIN, PILLAR_AXIS);
        return PROPERTIES.getBlockState(PILLAR_AXIS.createValue(original.getPropertyValue(PILLAR_AXIS)));
    }
}
