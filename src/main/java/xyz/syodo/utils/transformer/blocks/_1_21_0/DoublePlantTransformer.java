package xyz.syodo.utils.transformer.blocks._1_21_0;

import cn.nukkit.block.BlockProperties;
import cn.nukkit.block.BlockState;
import cn.nukkit.block.property.CommonBlockProperties;
import cn.nukkit.block.property.enums.DoublePlantType;
import xyz.syodo.utils.transformer.blocks.BlockStateTransformer;

import static cn.nukkit.block.BlockID.*;
import static cn.nukkit.item.ItemID.DOUBLE_PLANT;

public class DoublePlantTransformer extends BlockStateTransformer {

    @Override
    public BlockState transform(BlockState original) {
        BlockProperties PROPERTIES = new BlockProperties(DOUBLE_PLANT, CommonBlockProperties.DOUBLE_PLANT_TYPE, CommonBlockProperties.UPPER_BLOCK_BIT);

        DoublePlantType type = switch(original.getIdentifier()) {
            case SUNFLOWER -> DoublePlantType.SUNFLOWER;
            case LILAC -> DoublePlantType.SYRINGA;
            case TALL_GRASS -> DoublePlantType.GRASS;
            case LARGE_FERN -> DoublePlantType.FERN;
            case ROSE_BUSH -> DoublePlantType.ROSE;
            default -> DoublePlantType.PAEONIA;
        };
        return PROPERTIES.getBlockState(CommonBlockProperties.DOUBLE_PLANT_TYPE.createValue(type),
                CommonBlockProperties.UPPER_BLOCK_BIT.createValue(original.getPropertyValue(CommonBlockProperties.UPPER_BLOCK_BIT)));
    }

}
