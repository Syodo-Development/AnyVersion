package xyz.syodo.utils.table.entity;


import xyz.syodo.utils.ProtocolVersion;
import xyz.syodo.utils.transformer.entity._1_21_50.BoatTransformer;

import static cn.nukkit.entity.EntityID.*;
import static xyz.syodo.utils.definition.EntityDefinition.of;

public class EntityTable_1_21_50 extends EntityTable {

    public EntityTable_1_21_50() {
        super(ProtocolVersion.MINECRAFT_PE_1_21_50,
                of(BOAT, new BoatTransformer()),
                of(CHEST_BOAT, new BoatTransformer())
        );
    }
}
