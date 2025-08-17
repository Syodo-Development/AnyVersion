package xyz.syodo.utils.table.item;

import xyz.syodo.utils.ProtocolVersion;
import xyz.syodo.utils.transformer.items._1_20_60.HardStainedGlassTransformer;
import xyz.syodo.utils.transformer.items._1_20_60.ScuteTransformer;
import xyz.syodo.utils.transformer.items._1_20_70.SpawnEggTransformer;

import static cn.nukkit.item.ItemID.*;
import static cn.nukkit.tags.BlockTags.GRASS;
import static xyz.syodo.utils.definition.ItemDefinition.of;

public class ItemDataTable_1_20_60 extends ItemTable {

    public ItemDataTable_1_20_60() {
        super(ProtocolVersion.MINECRAFT_PE_1_20_60,
                of(TURTLE_SCUTE, new ScuteTransformer()),
                of(HARD_STAINED_GLASS, new HardStainedGlassTransformer()),
                of(HARD_STAINED_GLASS_PANE, new HardStainedGlassTransformer())
        );
    }

}
