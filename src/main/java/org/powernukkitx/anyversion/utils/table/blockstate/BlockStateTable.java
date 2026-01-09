package org.powernukkitx.anyversion.utils.table.blockstate;

import org.jetbrains.annotations.NotNull;
import org.powernukkitx.anyversion.utils.ProtocolVersion;
import org.powernukkitx.anyversion.utils.definition.BlockStateDefinition;
import org.powernukkitx.anyversion.utils.table.Table;

public abstract class BlockStateTable extends Table<BlockStateDefinition> {

    public BlockStateTable(@NotNull ProtocolVersion version, BlockStateDefinition... values) {
        super(version, values);
    }

}
