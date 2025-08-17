package xyz.syodo.utils.transformer.blocks._1_21_0;

import cn.nukkit.block.BlockProperties;
import cn.nukkit.block.BlockState;
import cn.nukkit.block.property.CommonBlockProperties;
import cn.nukkit.block.property.enums.DoublePlantType;
import cn.nukkit.block.property.enums.TallGrassType;
import xyz.syodo.utils.transformer.blocks.BlockStateTransformer;

import static cn.nukkit.block.BlockID.*;
import static cn.nukkit.item.ItemID.DOUBLE_PLANT;
import static cn.nukkit.item.ItemID.TALLGRASS;

public class TallgrassTransformer extends BlockStateTransformer {

    @Override
    public BlockState transform(BlockState original) {
        BlockProperties PROPERTIES = new BlockProperties(TALLGRASS, CommonBlockProperties.TALL_GRASS_TYPE);

        TallGrassType type = switch(original.getIdentifier()) {
            case FERN -> TallGrassType.FERN;
            default -> TallGrassType.DEFAULT;
        };
        return PROPERTIES.getBlockState(CommonBlockProperties.TALL_GRASS_TYPE.createValue(type));
    }

}
