package org.powernukkitx.anyversion.utils.table.item;

import org.jetbrains.annotations.NotNull;
import org.powernukkitx.anyversion.utils.ProtocolVersion;
import org.powernukkitx.anyversion.utils.definition.ItemDefinition;
import org.powernukkitx.anyversion.utils.table.Table;

public class ItemTable extends Table<ItemDefinition> {

    public ItemTable(@NotNull ProtocolVersion version, ItemDefinition... values) {
        super(version, values);
    }

}
