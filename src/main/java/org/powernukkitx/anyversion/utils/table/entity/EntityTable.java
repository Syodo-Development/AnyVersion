package org.powernukkitx.anyversion.utils.table.entity;

import org.jetbrains.annotations.NotNull;
import org.powernukkitx.anyversion.utils.ProtocolVersion;
import org.powernukkitx.anyversion.utils.definition.EntityDefinition;
import org.powernukkitx.anyversion.utils.table.Table;

public class EntityTable extends Table<EntityDefinition> {

    public EntityTable(@NotNull ProtocolVersion version, EntityDefinition... values) {
        super(version, values);
    }

}
