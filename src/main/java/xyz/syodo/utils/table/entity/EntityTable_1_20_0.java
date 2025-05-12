package xyz.syodo.utils.table.entity;

import xyz.syodo.utils.ProtocolVersion;

import static cn.nukkit.entity.EntityID.*;
import static xyz.syodo.utils.definition.EntityDefinition.of;

public class EntityTable_1_20_0 extends EntityTable {

    public EntityTable_1_20_0() {
        super(ProtocolVersion.MINECRAFT_PE_1_20_0,
                of(SNIFFER),
                of(CAMEL)
        );
    }
}
