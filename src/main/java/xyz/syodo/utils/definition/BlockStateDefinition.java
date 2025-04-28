package xyz.syodo.utils.definition;

import cn.nukkit.block.BlockState;
import cn.nukkit.registry.Registries;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import xyz.syodo.utils.transformer.BlockStateTransformer;
import xyz.syodo.utils.transformer.UnknownStateTransformer;

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
        return new BlockStateDefinition(Registries.BLOCK.get(id).getBlockState().getIdentifier(), transformer);
    }

    public int getDowngradeHash(BlockState origen) {
        return downgrade.transform(origen).blockStateHash();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof BlockStateDefinition definition) {
            return definition.identifier.equals(identifier);
        }
        return false;
    }
}
