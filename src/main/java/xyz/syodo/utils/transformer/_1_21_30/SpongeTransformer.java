package xyz.syodo.utils.transformer._1_21_30;

import cn.nukkit.block.BlockProperties;
import cn.nukkit.block.BlockState;
import cn.nukkit.block.property.CommonBlockProperties;
import cn.nukkit.block.property.enums.SpongeType;
import xyz.syodo.utils.transformer.BlockStateTransformer;

import static cn.nukkit.block.BlockID.*;

public class SpongeTransformer extends BlockStateTransformer {

    @Override
    public BlockState transform(BlockState original) {
        BlockProperties PROPERTIES = new BlockProperties(SPONGE, CommonBlockProperties.SPONGE_TYPE);
        SpongeType type = switch(original.getIdentifier()) {
            case WET_SPONGE -> SpongeType.WET;
            default -> SpongeType.DRY;
        };
        return PROPERTIES.getBlockState(CommonBlockProperties.SPONGE_TYPE.createValue(type));
    }

}
