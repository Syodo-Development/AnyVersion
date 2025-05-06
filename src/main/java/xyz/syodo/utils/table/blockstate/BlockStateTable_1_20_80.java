package xyz.syodo.utils.table.blockstate;

import xyz.syodo.utils.ProtocolVersion;
import xyz.syodo.utils.transformer.blocks._1_20_80.CoralFanTransformer;
import xyz.syodo.utils.transformer.blocks._1_20_80.DeadCoralFanTransformer;
import xyz.syodo.utils.transformer.blocks._1_20_80.SaplingTransformer;
import xyz.syodo.utils.transformer.blocks._1_20_80.RedFlowerTransformer;

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
                of(ALLIUM, new RedFlowerTransformer()),
                of(CORNFLOWER, new RedFlowerTransformer()),
                of(AZURE_BLUET, new RedFlowerTransformer()),
                of(LILY_OF_THE_VALLEY, new RedFlowerTransformer()),
                of(BLUE_ORCHID, new RedFlowerTransformer()),
                of(OXEYE_DAISY, new RedFlowerTransformer()),
                of(POPPY, new RedFlowerTransformer()),
                of(ORANGE_TULIP, new RedFlowerTransformer()),
                of(PINK_TULIP, new RedFlowerTransformer()),
                of(WHITE_TULIP, new RedFlowerTransformer()),
                of(RED_TULIP, new RedFlowerTransformer()),
                of(TUBE_CORAL_FAN, new CoralFanTransformer()),
                of(BRAIN_CORAL_FAN, new CoralFanTransformer()),
                of(BUBBLE_CORAL_FAN, new CoralFanTransformer()),
                of(HORN_CORAL_FAN, new CoralFanTransformer()),
                of(FIRE_CORAL_FAN, new CoralFanTransformer()),
                of(DEAD_TUBE_CORAL_FAN, new DeadCoralFanTransformer()),
                of(DEAD_BRAIN_CORAL_FAN, new DeadCoralFanTransformer()),
                of(DEAD_BUBBLE_CORAL_FAN, new DeadCoralFanTransformer()),
                of(DEAD_HORN_CORAL_FAN, new DeadCoralFanTransformer()),
                of(DEAD_FIRE_CORAL_FAN, new DeadCoralFanTransformer())
        );
    }
}
