package xyz.syodo.utils.table.blockstate;

import xyz.syodo.utils.ProtocolVersion;
import xyz.syodo.utils.transformer._1_20_80.SaplingTransformer;

import static cn.nukkit.block.BlockID.*;
import static cn.nukkit.block.BlockID.DEAD_FIRE_CORAL_FAN;
import static xyz.syodo.utils.definition.BlockStateDefinition.of;

public class BlockStateTable_1_20_80 extends BlockStateTable {

    public BlockStateTable_1_20_80() {
        super(ProtocolVersion.MINECRAFT_PE_1_20_80,
                of(ACACIA_SAPLING, new SaplingTransformer()),
                of(BIRCH_SAPLING, new SaplingTransformer()),
                of(DARK_OAK_SAPLING, new SaplingTransformer()),
                of(JUNGLE_SAPLING, new SaplingTransformer()),
                of(OAK_SAPLING, new SaplingTransformer()),
                of(SPRUCE_SAPLING, new SaplingTransformer()),
                of(ORANGE_TULIP),
                of(PINK_TULIP),
                of(WHITE_TULIP),
                of(RED_TULIP),
                of(OXEYE_DAISY),
                of(BLUE_ORCHID),
                of(AZURE_BLUET),
                of(TUBE_CORAL_FAN),
                of(BRAIN_CORAL_FAN),
                of(BUBBLE_CORAL_FAN),
                of(HORN_CORAL_FAN),
                of(FIRE_CORAL_FAN),
                of(DEAD_TUBE_CORAL_FAN),
                of(DEAD_BRAIN_CORAL_FAN),
                of(DEAD_BUBBLE_CORAL_FAN),
                of(DEAD_HORN_CORAL_FAN),
                of(DEAD_FIRE_CORAL_FAN)
        );
    }
}
