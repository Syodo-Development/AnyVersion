package xyz.syodo.utils.table.item;

import xyz.syodo.utils.ProtocolVersion;
import xyz.syodo.utils.transformer.items._1_20_10.ConcreteTransformer;
import xyz.syodo.utils.transformer.items._1_20_10.ShulkerBoxTransformer;

import static cn.nukkit.item.ItemID.*;
import static xyz.syodo.utils.definition.ItemDefinition.of;

public class ItemDataTable_1_20_10 extends ItemTable {

    public ItemDataTable_1_20_10() {
        super(ProtocolVersion.MINECRAFT_PE_1_20_10,
                of(CONCRETE, new ConcreteTransformer()),
                of(SHULKER_BOX, new ShulkerBoxTransformer())
        );
    }

}
