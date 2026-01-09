package xyz.syodo.utils.table.entity;


import cn.nukkit.entity.EntityID;
import xyz.syodo.utils.ProtocolVersion;

import static xyz.syodo.utils.definition.EntityDefinition.of;

public class EntityTable_1_21_130 extends EntityTable {

    public EntityTable_1_21_130() {
        super(ProtocolVersion.MINECRAFT_PE_1_21_130,
                of(EntityID.PARCHED),
                of(EntityID.CAMEL_HUSK),
                of(EntityID.NAUTILUS),
                of(EntityID.ZOMBIE_NAUTILUS)
        );
    }
}
