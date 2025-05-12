package xyz.syodo.utils.table.item;

import xyz.syodo.utils.ProtocolVersion;
import xyz.syodo.utils.transformer.items._1_20_50.PlanksTransformer;
import xyz.syodo.utils.transformer.items._1_20_50.StoneTransformer;

import static cn.nukkit.block.BlockID.STONE;
import static cn.nukkit.item.ItemID.*;
import static xyz.syodo.utils.definition.ItemDefinition.of;

public class ItemDataTable_1_20_50 extends ItemTable {

    public ItemDataTable_1_20_50() {
        super(ProtocolVersion.MINECRAFT_PE_1_20_50,
                of(PLANKS, new PlanksTransformer()),
                of(STONE, new StoneTransformer())
        );
    }

}
