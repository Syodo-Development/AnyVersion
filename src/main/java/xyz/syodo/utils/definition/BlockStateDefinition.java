package xyz.syodo.utils.definition;

import cn.nukkit.block.BlockState;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import xyz.syodo.utils.transformer.blocks.BlockStateTransformer;
import xyz.syodo.utils.transformer.blocks.UnknownStateTransformer;

@Getter
@AllArgsConstructor
@RequiredArgsConstructor
public class BlockStateDefinition extends Definition {

    private final String identifier;
    private BlockStateTransformer downgrade = new UnknownStateTransformer();

    public static BlockStateDefinition of(String identifier) {
        return new BlockStateDefinition(identifier);
    }

    public static BlockStateDefinition of(BlockState state) {
        return new BlockStateDefinition(state.getIdentifier());
    }

    public static BlockStateDefinition of(String id, BlockStateTransformer transformer) {
        return new BlockStateDefinition(id, transformer);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof BlockStateDefinition definition) {
            return definition.identifier.equals(identifier);
        }
        return false;
    }
}
