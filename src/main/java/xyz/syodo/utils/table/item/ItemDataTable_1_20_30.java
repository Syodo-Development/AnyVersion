package xyz.syodo.utils.table.item;

import xyz.syodo.utils.ProtocolVersion;
import xyz.syodo.utils.transformer.items._1_20_30.ConcretePowderTransformer;
import xyz.syodo.utils.transformer.items._1_20_30.StainedGlassPaneTransformer;
import xyz.syodo.utils.transformer.items._1_20_30.StainedGlassTransformer;
import xyz.syodo.utils.transformer.items._1_20_30.TerracottaTransformer;

import static cn.nukkit.block.BlockID.STONE;
import static cn.nukkit.item.ItemID.*;
import static xyz.syodo.utils.definition.ItemDefinition.of;

public class ItemDataTable_1_20_30 extends ItemTable {

    public ItemDataTable_1_20_30() {
        super(ProtocolVersion.MINECRAFT_PE_1_20_30,
                of(CONCRETE_POWDER, new ConcretePowderTransformer()),
                of(STAINED_HARDENED_CLAY, new TerracottaTransformer()),
                of(STAINED_GLASS, new StainedGlassTransformer()),
                of(STAINED_GLASS_PANE, new StainedGlassPaneTransformer())
        );
    }

}
