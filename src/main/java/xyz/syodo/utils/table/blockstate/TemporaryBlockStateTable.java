package xyz.syodo.utils.table.blockstate;

import xyz.syodo.utils.ProtocolVersion;
import xyz.syodo.utils.definition.BlockStateDefinition;

public class TemporaryBlockStateTable extends BlockStateTable {

    public TemporaryBlockStateTable(ProtocolVersion version, BlockStateDefinition... values) {
        super(version, values);
    }

}
