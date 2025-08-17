package xyz.syodo.utils.table.blockstate;

import org.jetbrains.annotations.NotNull;
import xyz.syodo.utils.ProtocolVersion;
import xyz.syodo.utils.definition.BlockStateDefinition;
import xyz.syodo.utils.table.Table;

public abstract class BlockStateTable extends Table<BlockStateDefinition> {

    public BlockStateTable(@NotNull ProtocolVersion version, BlockStateDefinition... values) {
        super(version, values);
    }

}
