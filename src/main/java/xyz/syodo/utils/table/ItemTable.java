package xyz.syodo.utils.table;

import xyz.syodo.utils.ProtocolVersion;
import xyz.syodo.utils.definition.ItemDefinition;

public class ItemTable extends Table<ItemDefinition> {

    public ItemTable(ProtocolVersion version, ItemDefinition... values) {
        super(version, values);
    }

}
