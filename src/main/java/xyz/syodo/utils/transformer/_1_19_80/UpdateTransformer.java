package xyz.syodo.utils.transformer._1_19_80;

import cn.nukkit.block.BlockProperties;
import cn.nukkit.block.BlockState;
import cn.nukkit.block.property.CommonBlockProperties;
import cn.nukkit.block.property.enums.Color;
import xyz.syodo.utils.definition.BlockStateDefinition;
import xyz.syodo.utils.transformer.BlockStateTransformer;

import static cn.nukkit.block.BlockID.*;
import static cn.nukkit.item.ItemID.CARPET;

public class UpdateTransformer extends BlockStateTransformer {

    @Override
    public BlockState transform(BlockState original) {
        BlockProperties PROPERTIES = new BlockProperties(INFO_UPDATE);
        return PROPERTIES.getDefaultState();
    }

    //For versions pre 1.19.80, use this "of" method!
    public static BlockStateDefinition of(String state) {
        return of(state, new UpdateTransformer());
    }

    //For versions pre 1.19.80, use this "of" method!
    public static BlockStateDefinition of(String state, BlockStateTransformer transformer) {
        return new BlockStateDefinition(state, transformer);
    }
}
