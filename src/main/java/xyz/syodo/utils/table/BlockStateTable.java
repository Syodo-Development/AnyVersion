package xyz.syodo.utils.table;

import xyz.syodo.utils.ProtocolVersion;
import xyz.syodo.utils.definition.BlockStateDefinition;
import xyz.syodo.utils.definition.ItemDefinition;

public class BlockStateTable extends Table<BlockStateDefinition> {

    public BlockStateTable(ProtocolVersion version, BlockStateDefinition... values) {
        super(version, values);
    }

}
