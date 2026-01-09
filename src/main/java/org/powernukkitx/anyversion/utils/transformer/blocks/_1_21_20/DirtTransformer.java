package org.powernukkitx.anyversion.utils.transformer.blocks._1_21_20;

import cn.nukkit.block.BlockProperties;
import cn.nukkit.block.BlockState;
import cn.nukkit.block.property.CommonBlockProperties;
import cn.nukkit.block.property.enums.DirtType;
import org.powernukkitx.anyversion.utils.transformer.blocks.BlockStateTransformer;

import static cn.nukkit.block.BlockID.*;

public class DirtTransformer extends BlockStateTransformer {

    @Override
    public BlockState transform(BlockState original) {
        BlockProperties PROPERTIES = new BlockProperties(DIRT, CommonBlockProperties.DIRT_TYPE);
        DirtType type = switch(original.getIdentifier()) {
            case COARSE_DIRT -> DirtType.COARSE;
            default -> DirtType.NORMAL;
        };
        return PROPERTIES.getBlockState(CommonBlockProperties.DIRT_TYPE, type);
    }

}
