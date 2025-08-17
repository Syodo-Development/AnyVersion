package xyz.syodo.utils.table.item;

import org.jetbrains.annotations.NotNull;
import xyz.syodo.utils.ProtocolVersion;
import xyz.syodo.utils.definition.ItemDefinition;
import xyz.syodo.utils.table.Table;

public class ItemTable extends Table<ItemDefinition> {

    public ItemTable(@NotNull ProtocolVersion version, ItemDefinition... values) {
        super(version, values);
    }

}
