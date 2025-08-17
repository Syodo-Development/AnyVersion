package xyz.syodo.utils.table.entity;


import xyz.syodo.utils.ProtocolVersion;

import static xyz.syodo.utils.definition.EntityDefinition.*;
import static cn.nukkit.entity.EntityID.*;

public class EntityTable_1_21_0 extends EntityTable {

    public EntityTable_1_21_0() {
        super(ProtocolVersion.MINECRAFT_PE_1_21_0,
                of(BOGGED),
                of(BREEZE)
        );
    }
}
