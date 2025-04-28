package xyz.syodo.utils.transformer;

import cn.nukkit.block.BlockProperties;
import cn.nukkit.block.BlockState;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class IdentifierTransformer extends BlockStateTransformer {

    public final String identifier;

    @Override
    public BlockState transform(BlockState original) {
        return new BlockProperties(identifier).getDefaultState();
    }
}
