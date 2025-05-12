package xyz.syodo.utils.table.item;

import xyz.syodo.utils.ProtocolVersion;

import static cn.nukkit.item.ItemID.*;
import static xyz.syodo.utils.definition.ItemDefinition.of;

public class ItemDataTable_1_21_70 extends ItemTable {

    public ItemDataTable_1_21_70() {
        super(ProtocolVersion.MINECRAFT_PE_1_21_70,
                of(BLUE_EGG),
                of(BROWN_EGG)
        );
    }

}
