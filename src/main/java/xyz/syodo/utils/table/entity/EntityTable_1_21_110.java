package xyz.syodo.utils.table.entity;


import cn.nukkit.entity.EntityID;
import xyz.syodo.utils.ProtocolVersion;

import static xyz.syodo.utils.definition.EntityDefinition.of;

public class EntityTable_1_21_110 extends EntityTable {

    public EntityTable_1_21_110() {
        super(ProtocolVersion.MINECRAFT_PE_1_21_110,
                of(EntityID.COPPER_GOLEM)
        );
    }
}
