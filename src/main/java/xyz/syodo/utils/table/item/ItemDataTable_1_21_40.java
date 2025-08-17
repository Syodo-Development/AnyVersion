package xyz.syodo.utils.table.item;

import cn.nukkit.block.BlockID;
import xyz.syodo.utils.ProtocolVersion;
import xyz.syodo.utils.transformer.items._1_21_40.SkullTransformer;
import xyz.syodo.utils.transformer.items._1_21_40.StemTransformer;

import static cn.nukkit.item.ItemID.*;
import static xyz.syodo.utils.definition.ItemDefinition.of;

public class ItemDataTable_1_21_40 extends ItemTable {

    public ItemDataTable_1_21_40() {
        super(ProtocolVersion.MINECRAFT_PE_1_21_40,
                of(BlockID.SKULL, new SkullTransformer()),
                of(BlockID.RED_MUSHROOM_BLOCK, new StemTransformer()),
                of(BlockID.BROWN_MUSHROOM_BLOCK, new StemTransformer()),
                of(BUNDLE),
                of(BLACK_BUNDLE),
                of(BLUE_BUNDLE),
                of(BROWN_BUNDLE),
                of(CYAN_BUNDLE),
                of(GRAY_BUNDLE),
                of(GREEN_BUNDLE),
                of(LIGHT_BLUE_BUNDLE),
                of(LIGHT_GRAY_BUNDLE),
                of(LIME_BUNDLE),
                of(MAGENTA_BUNDLE),
                of(ORANGE_BUNDLE),
                of(PINK_BUNDLE),
                of(PURPLE_BUNDLE),
                of(RED_BUNDLE),
                of(WHITE_BUNDLE),
                of(YELLOW_BUNDLE)
        );
    }

}
