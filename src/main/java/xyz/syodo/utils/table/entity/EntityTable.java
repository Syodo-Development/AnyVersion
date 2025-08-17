package xyz.syodo.utils.table.entity;

import org.jetbrains.annotations.NotNull;
import xyz.syodo.utils.ProtocolVersion;
import xyz.syodo.utils.definition.EntityDefinition;
import xyz.syodo.utils.definition.ItemDefinition;
import xyz.syodo.utils.table.Table;

public class EntityTable extends Table<EntityDefinition> {

    public EntityTable(@NotNull ProtocolVersion version, EntityDefinition... values) {
        super(version, values);
    }

}
