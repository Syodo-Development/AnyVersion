package xyz.syodo.utils.table.item;

import xyz.syodo.utils.ProtocolVersion;
import xyz.syodo.utils.transformer.items._1_20_80.CoralFanTransformer;
import xyz.syodo.utils.transformer.items._1_20_80.RedFlowerTransformer;
import xyz.syodo.utils.transformer.items._1_20_80.SaplingTransformer;

import static cn.nukkit.item.ItemID.*;
import static xyz.syodo.utils.definition.ItemDefinition.of;

public class ItemDataTable_1_20_80 extends ItemTable {

    public ItemDataTable_1_20_80() {
        super(ProtocolVersion.MINECRAFT_PE_1_20_80,
                of(ARMADILLO_SCUTE),
                of(WOLF_ARMOR),
                of(FLOW_ARMOR_TRIM_SMITHING_TEMPLATE),
                of(FLOW_POTTERY_SHERD),
                of(FLOW_BANNER_PATTERN),
                of(CORAL_FAN, new CoralFanTransformer()),
                of(CORAL_FAN_DEAD, new CoralFanTransformer()),
                of(RED_FLOWER, new RedFlowerTransformer()),
                of(SAPLING, new SaplingTransformer())
        );
    }

}
