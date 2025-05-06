package xyz.syodo.utils.table.item;

import xyz.syodo.utils.ProtocolVersion;
import xyz.syodo.utils.transformer.items._1_19_80.FenceTransformer;

import static xyz.syodo.utils.definition.ItemDefinition.of;
import static cn.nukkit.item.ItemID.*;

public class ItemDataTable_1_19_80 extends ItemTable {

    public ItemDataTable_1_19_80() {
        super(ProtocolVersion.MINECRAFT_PE_1_19_80,
                of(FENCE, new FenceTransformer())
        );
    }

}
