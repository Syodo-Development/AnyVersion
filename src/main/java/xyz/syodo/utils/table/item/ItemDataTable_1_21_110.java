package xyz.syodo.utils.table.item;

import xyz.syodo.utils.ProtocolVersion;

import static cn.nukkit.item.ItemID.*;
import static xyz.syodo.utils.definition.ItemDefinition.of;

public class ItemDataTable_1_21_110 extends ItemTable {

    public ItemDataTable_1_21_110() {
        super(ProtocolVersion.MINECRAFT_PE_1_21_110,
                of(COPPER_GOLEM_SPAWN_EGG),
                of(COPPER_NUGGET),
                of(COPPER_BOOTS),
                of(COPPER_CHESTPLATE),
                of(COPPER_HELMET),
                of(COPPER_HORSE_ARMOR),
                of(COPPER_LEGGINGS),
                of(COPPER_AXE),
                of(COPPER_HOE),
                of(COPPER_PICKAXE),
                of(COPPER_SHOVEL),
                of(COPPER_SWORD)
        );
    }

}
