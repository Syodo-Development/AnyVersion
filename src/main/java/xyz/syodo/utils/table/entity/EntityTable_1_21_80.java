package xyz.syodo.utils.table.entity;


import xyz.syodo.utils.ProtocolVersion;

import static cn.nukkit.entity.EntityID.ARMADILLO;
import static cn.nukkit.entity.EntityID.HAPPY_GHAST;
import static xyz.syodo.utils.definition.EntityDefinition.of;

public class EntityTable_1_21_80 extends EntityTable {

    public EntityTable_1_21_80() {
        super(ProtocolVersion.MINECRAFT_PE_1_21_80,
                of(ARMADILLO)
        );
    }
}
