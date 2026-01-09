package org.powernukkitx.anyversion.utils.table.blockstate;

import org.powernukkitx.anyversion.utils.ProtocolVersion;
import org.powernukkitx.anyversion.utils.definition.BlockStateDefinition;

public class TemporaryBlockStateTable extends BlockStateTable {

    public TemporaryBlockStateTable(ProtocolVersion version, BlockStateDefinition... values) {
        super(version, values);
    }

}
