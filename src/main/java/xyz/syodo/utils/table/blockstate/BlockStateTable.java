package xyz.syodo.utils.table.blockstate;

import xyz.syodo.utils.ProtocolVersion;
import xyz.syodo.utils.definition.BlockStateDefinition;
import xyz.syodo.utils.table.Table;

public abstract class BlockStateTable extends Table<BlockStateDefinition> {

    public BlockStateTable(ProtocolVersion version, BlockStateDefinition... values) {
        super(version, values);
    }

}
