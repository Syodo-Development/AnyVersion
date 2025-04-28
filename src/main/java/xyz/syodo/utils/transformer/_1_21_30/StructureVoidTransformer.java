package xyz.syodo.utils.transformer._1_21_30;

import cn.nukkit.block.BlockProperties;
import cn.nukkit.block.BlockState;
import cn.nukkit.block.property.CommonBlockProperties;
import cn.nukkit.block.property.enums.SpongeType;
import cn.nukkit.block.property.enums.StructureVoidType;
import xyz.syodo.utils.transformer.BlockStateTransformer;

import static cn.nukkit.block.BlockID.*;

public class StructureVoidTransformer extends BlockStateTransformer {

    @Override
    public BlockState transform(BlockState original) {
        BlockProperties PROPERTIES = new BlockProperties(STRUCTURE_VOID, CommonBlockProperties.STRUCTURE_VOID_TYPE);
        return PROPERTIES.getBlockState(CommonBlockProperties.STRUCTURE_VOID_TYPE.createValue(StructureVoidType.VOID));
    }

}
