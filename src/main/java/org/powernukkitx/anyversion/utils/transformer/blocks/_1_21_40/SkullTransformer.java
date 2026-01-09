package org.powernukkitx.anyversion.utils.transformer.blocks._1_21_40;

import cn.nukkit.block.BlockProperties;
import cn.nukkit.block.BlockState;
import cn.nukkit.block.property.CommonBlockProperties;
import org.powernukkitx.anyversion.utils.transformer.blocks.BlockStateTransformer;

import static cn.nukkit.block.BlockID.SKULL;

public class SkullTransformer extends BlockStateTransformer {

    @Override
    public BlockState transform(BlockState original) {
        BlockProperties PROPERTIES = new BlockProperties(SKULL, CommonBlockProperties.FACING_DIRECTION);
        return PROPERTIES.getBlockState(CommonBlockProperties.FACING_DIRECTION.createValue(original.getPropertyValue(CommonBlockProperties.FACING_DIRECTION)));
    }
}
