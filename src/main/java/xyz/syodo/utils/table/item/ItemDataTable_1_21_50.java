package xyz.syodo.utils.table.item;

import xyz.syodo.utils.ProtocolVersion;

import static cn.nukkit.item.ItemID.*;
import static xyz.syodo.utils.definition.ItemDefinition.of;

public class ItemDataTable_1_21_50 extends ItemTable {

    public ItemDataTable_1_21_50() {
        super(ProtocolVersion.MINECRAFT_PE_1_21_50,
                of(CREAKING_SPAWN_EGG),
                of(PALE_OAK_BOAT),
                of(PALE_OAK_CHEST_BOAT),
                of(PALE_OAK_HANGING_SIGN),
                of(PALE_OAK_SIGN),
                of(RESIN_BRICK)
        );
    }

}
