package xyz.syodo.utils.table.item;

import xyz.syodo.utils.ProtocolVersion;

import static cn.nukkit.item.ItemID.*;
import static xyz.syodo.utils.definition.ItemDefinition.of;

public class ItemDataTable_1_21_70 extends ItemTable {

    public ItemDataTable_1_21_70() {
        super(ProtocolVersion.MINECRAFT_PE_1_21_70,
                of(BLUE_EGG),
                of(BROWN_EGG),
                of(WHITE_DYE),
                of(LIGHT_GRAY_DYE),
                of(GRAY_DYE),
                of(BLACK_DYE),
                of(BROWN_DYE),
                of(RED_DYE),
                of(ORANGE_DYE),
                of(YELLOW_DYE),
                of(LIME_DYE),
                of(GREEN_DYE),
                of(CYAN_DYE),
                of(LIGHT_BLUE_DYE),
                of(BLUE_DYE),
                of(PURPLE_DYE),
                of(MAGENTA_DYE),
                of(PINK_DYE)
        );
    }

}
