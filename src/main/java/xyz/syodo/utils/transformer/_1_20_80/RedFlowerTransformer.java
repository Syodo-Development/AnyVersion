package xyz.syodo.utils.transformer._1_20_80;

import cn.nukkit.block.BlockProperties;
import cn.nukkit.block.BlockState;
import cn.nukkit.block.property.CommonBlockProperties;
import cn.nukkit.block.property.enums.FlowerType;
import cn.nukkit.block.property.type.EnumPropertyType;
import xyz.syodo.utils.transformer.BlockStateTransformer;

import static cn.nukkit.block.BlockID.*;
import static cn.nukkit.item.ItemID.RED_FLOWER;

public class RedFlowerTransformer extends BlockStateTransformer {

    EnumPropertyType<FlowerType> FLOWER_TYPE = EnumPropertyType.of("flower_type", FlowerType.class, FlowerType.values()[0]);

    @Override
    public BlockState transform(BlockState original) {
        BlockProperties PROPERTIES = new BlockProperties(RED_FLOWER, FLOWER_TYPE);
        FlowerType type = switch(original.getIdentifier()) {
            case ALLIUM -> FlowerType.ALLIUM;
            case CORNFLOWER -> FlowerType.CORNFLOWER;
            case AZURE_BLUET -> FlowerType.HOUSTONIA;
            case LILY_OF_THE_VALLEY -> FlowerType.LILY_OF_THE_VALLEY;
            case BLUE_ORCHID -> FlowerType.ORCHID;
            case OXEYE_DAISY -> FlowerType.OXEYE;
            case POPPY -> FlowerType.POPPY;
            case ORANGE_TULIP -> FlowerType.TULIP_ORANGE;
            case PINK_TULIP -> FlowerType.TULIP_PINK;
            case RED_TULIP -> FlowerType.TULIP_RED;
            default -> FlowerType.TULIP_WHITE;
        };
        return PROPERTIES.getBlockState(FLOWER_TYPE.createValue(type),
                CommonBlockProperties.AGE_BIT.createValue(original.getPropertyValue(CommonBlockProperties.AGE_BIT)));
    }

}
