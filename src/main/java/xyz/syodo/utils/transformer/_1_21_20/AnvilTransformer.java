package xyz.syodo.utils.transformer._1_21_20;

import cn.nukkit.block.BlockProperties;
import cn.nukkit.block.BlockState;
import cn.nukkit.block.property.CommonBlockProperties;
import cn.nukkit.block.property.enums.Damage;
import xyz.syodo.utils.transformer.BlockStateTransformer;

import static cn.nukkit.block.BlockID.*;

public class AnvilTransformer extends BlockStateTransformer {

    @Override
    public BlockState transform(BlockState original) {
        BlockProperties PROPERTIES = new BlockProperties(ANVIL, CommonBlockProperties.DAMAGE, CommonBlockProperties.MINECRAFT_CARDINAL_DIRECTION);
        Damage type = switch(original.getIdentifier()) {
            case CHIPPED_ANVIL -> Damage.SLIGHTLY_DAMAGED;
            //case "minecraft:deprecated_anvil" -> Damage.VERY_DAMAGED;
            case DAMAGED_ANVIL -> Damage.BROKEN;
            default -> Damage.UNDAMAGED;
        };
        return PROPERTIES.getBlockState(
                CommonBlockProperties.DAMAGE.createValue(type),
                CommonBlockProperties.MINECRAFT_CARDINAL_DIRECTION.createValue(original.getPropertyValue(CommonBlockProperties.MINECRAFT_CARDINAL_DIRECTION))
        );
    }
}
