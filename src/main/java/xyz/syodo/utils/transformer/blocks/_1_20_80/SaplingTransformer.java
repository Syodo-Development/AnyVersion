package xyz.syodo.utils.transformer.blocks._1_20_80;

import cn.nukkit.block.BlockProperties;
import cn.nukkit.block.BlockState;
import cn.nukkit.block.property.CommonBlockProperties;
import cn.nukkit.block.property.type.EnumPropertyType;
import xyz.syodo.utils.transformer.blocks.BlockStateTransformer;

import static cn.nukkit.block.BlockID.*;

public class SaplingTransformer extends BlockStateTransformer {

    private static final String SAPLING = "minecraft:sapling";

    EnumPropertyType<SaplingType> SAPLING_TYPE = EnumPropertyType.of("sapling_type", SaplingType.class, SaplingType.values()[0]);

    @Override
    public BlockState transform(BlockState original) {
        BlockProperties PROPERTIES = new BlockProperties(SAPLING, CommonBlockProperties.AGE_BIT, SAPLING_TYPE);
        SaplingType type = switch(original.getIdentifier()) {
            case ACACIA_SAPLING -> SaplingType.ACACIA;
            case BIRCH_SAPLING -> SaplingType.BIRCH;
            case DARK_OAK_SAPLING -> SaplingType.DARK_OAK;
            case JUNGLE_SAPLING -> SaplingType.JUNGLE;
            case SPRUCE_SAPLING -> SaplingType.SPRUCE;
            default -> SaplingType.OAK;
        };
        return PROPERTIES.getBlockState(SAPLING_TYPE.createValue(type),
                CommonBlockProperties.AGE_BIT.createValue(original.getPropertyValue(CommonBlockProperties.AGE_BIT)));
    }



    public enum SaplingType {
        ACACIA,

        BIRCH,

        DARK_OAK,

        JUNGLE,

        OAK,

        SPRUCE
    }

}
