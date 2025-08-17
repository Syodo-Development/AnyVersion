package xyz.syodo.utils.table.item;

import xyz.syodo.utils.ProtocolVersion;
import xyz.syodo.utils.transformer.items._1_20_70.*;

import static cn.nukkit.item.ItemID.*;
import static cn.nukkit.tags.BlockTags.GRASS;
import static xyz.syodo.utils.definition.ItemDefinition.of;

public class ItemDataTable_1_20_70 extends ItemTable {

    public ItemDataTable_1_20_70() {
        super(ProtocolVersion.MINECRAFT_PE_1_20_70,
                of(GRASS, new GrassTransformer()),
                of(LEAVES, new LeavesTransformer()),
                of(LEAVES2, new Leaves2Transformer()),
                of(WOOD, new WoodTransformer()),
                of(WOODEN_SLAB, new WoodenSlabTransformer())
        );
    }

}
