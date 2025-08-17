package xyz.syodo.utils.table.item;

import xyz.syodo.utils.ProtocolVersion;
import xyz.syodo.utils.transformer.items._1_21_30.PurpurTransformer;
import xyz.syodo.utils.transformer.items._1_21_30.SpongeTransformer;
import xyz.syodo.utils.transformer.items._1_21_30.WallTransformer;

import static cn.nukkit.block.BlockID.*;
import static xyz.syodo.utils.definition.ItemDefinition.of;

public class ItemDataTable_1_21_30 extends ItemTable {

    public ItemDataTable_1_21_30() {
        super(ProtocolVersion.MINECRAFT_PE_1_21_30,
                of(COBBLESTONE_WALL, new WallTransformer()),
                of(PURPUR_BLOCK, new PurpurTransformer()),
                of(SPONGE, new SpongeTransformer())
        );
    }

}
