package xyz.syodo.utils.table.item;

import cn.nukkit.block.BlockID;
import cn.nukkit.item.ItemID;
import xyz.syodo.utils.ProtocolVersion;
import xyz.syodo.utils.transformer.items._1_21_20.*;

import static cn.nukkit.block.BlockID.*;
import static cn.nukkit.item.ItemID.*;
import static xyz.syodo.utils.definition.ItemDefinition.of;
import static xyz.syodo.utils.transformer.blocks._1_21_20.LightBlockTransformer.LIGHT_BLOCK;
import static xyz.syodo.utils.transformer.blocks._1_21_20.StoneBrickTransformer.STONEBRICK;

public class ItemDataTable_1_21_0 extends ItemTable {

    public ItemDataTable_1_21_0() {
        super(ProtocolVersion.MINECRAFT_PE_1_21_0,
                of(BOLT_ARMOR_TRIM_SMITHING_TEMPLATE),
                of(BREEZE_ROD),
                of(CORAL_BLOCK),
                of(CORAL_FAN),
                of(DOUBLE_PLANT),
                of(FLOW_ARMOR_TRIM_SMITHING_TEMPLATE),
                of(FLOW_POTTERY_SHERD),
                of(GUSTER_BANNER_PATTERN),
                of(GUSTER_POTTERY_SHERD),
                of(MACE),
                of(MUSIC_DISC_CREATOR),
                of(MUSIC_DISC_CREATOR_MUSIC_BOX),
                of(MUSIC_DISC_PRECIPICE),
                of(OMINOUS_BOTTLE),
                of(OMINOUS_TRIAL_KEY),
                of(RED_FLOWER),
                of(SAPLING),
                of(SCRAPE_POTTERY_SHERD),
                of(STONE_BLOCK_SLAB),
                of(TALLGRASS),
                of(BREEZE_SPAWN_EGG),
                of(BOGGED_SPAWN_EGG),
                of(WIND_CHARGE),
                of(LINGERING_POTION)
        );
    }

}
