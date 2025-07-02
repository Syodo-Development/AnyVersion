package xyz.syodo.utils.table.entity;


import xyz.syodo.utils.ProtocolVersion;

import static cn.nukkit.entity.EntityID.*;
import static xyz.syodo.utils.definition.EntityDefinition.of;

public class EntityTable_1_21_90 extends EntityTable {

    public EntityTable_1_21_90() {
        super(ProtocolVersion.MINECRAFT_PE_1_21_90,
                of(HAPPY_GHAST)
        );
    }
}
