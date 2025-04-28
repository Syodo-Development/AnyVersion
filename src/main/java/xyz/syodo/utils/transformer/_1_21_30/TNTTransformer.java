package xyz.syodo.utils.transformer._1_21_30;

import cn.nukkit.block.BlockProperties;
import cn.nukkit.block.BlockState;
import cn.nukkit.block.property.CommonBlockProperties;
import xyz.syodo.utils.transformer.BlockStateTransformer;

import static cn.nukkit.block.BlockID.*;

public class TNTTransformer extends BlockStateTransformer {

    @Override
    public BlockState transform(BlockState original) {
        BlockProperties PROPERTIES = new BlockProperties(TNT, CommonBlockProperties.EXPLODE_BIT, CommonBlockProperties.ALLOW_UNDERWATER_BIT);
        boolean type = original.getIdentifier().equals("minecraft:underwater_tnt");
        return PROPERTIES.getBlockState(
                CommonBlockProperties.ALLOW_UNDERWATER_BIT.createValue(type),
                CommonBlockProperties.EXPLODE_BIT.createValue(original.getPropertyValue(CommonBlockProperties.EXPLODE_BIT))
        );
    }

}
