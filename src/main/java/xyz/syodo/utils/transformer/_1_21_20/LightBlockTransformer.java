package xyz.syodo.utils.transformer._1_21_20;

import cn.nukkit.block.BlockProperties;
import cn.nukkit.block.BlockState;
import cn.nukkit.block.property.CommonBlockProperties;
import xyz.syodo.utils.transformer.BlockStateTransformer;

public class LightBlockTransformer extends BlockStateTransformer {

    private static final String LIGHT_BLOCK = "minecraft:light_block";

    @Override
    public BlockState transform(BlockState original) {
        BlockProperties PROPERTIES = new BlockProperties(LIGHT_BLOCK, CommonBlockProperties.BLOCK_LIGHT_LEVEL);
        int lightLevel = Integer.parseInt(original.getIdentifier().replace(LIGHT_BLOCK + "_", ""));
        return PROPERTIES.getBlockState(CommonBlockProperties.BLOCK_LIGHT_LEVEL.createValue(lightLevel));
    }
}
