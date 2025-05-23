package xyz.syodo.utils.table.blockstate;

import xyz.syodo.utils.ProtocolVersion;
import xyz.syodo.utils.transformer._1_20_0.SculkSensorTransformer;
import xyz.syodo.utils.transformer._1_20_0.CarpetTransformer;
import xyz.syodo.utils.transformer._1_20_0.CoralTransformer;
import xyz.syodo.utils.transformer._1_20_30.DirectionToCardinalTransformer;

import static cn.nukkit.block.BlockID.*;
import static cn.nukkit.block.BlockID.DEAD_HORN_CORAL;
import static xyz.syodo.utils.definition.BlockStateDefinition.of;

public class BlockStateTable_1_20_0 extends BlockStateTable {

    public BlockStateTable_1_20_0() {
        super(ProtocolVersion.MINECRAFT_PE_1_20_0,
                of(BLACK_CARPET, new CarpetTransformer()),
                of(BLUE_CARPET, new CarpetTransformer()),
                of(BROWN_CARPET, new CarpetTransformer()),
                of(CYAN_CARPET, new CarpetTransformer()),
                of(GRAY_CARPET, new CarpetTransformer()),
                of(GREEN_CARPET, new CarpetTransformer()),
                of(LIGHT_BLUE_CARPET, new CarpetTransformer()),
                of(LIME_CARPET, new CarpetTransformer()),
                of(MAGENTA_CARPET, new CarpetTransformer()),
                of(ORANGE_CARPET, new CarpetTransformer()),
                of(PINK_CARPET, new CarpetTransformer()),
                of(PURPLE_CARPET, new CarpetTransformer()),
                of(RED_CARPET, new CarpetTransformer()),
                of(LIGHT_GRAY_CARPET, new CarpetTransformer()),
                of(YELLOW_CARPET, new CarpetTransformer()),
                of(WHITE_CARPET, new CarpetTransformer()),
                of(FIRE_CORAL, new CoralTransformer()),
                of(BRAIN_CORAL, new CoralTransformer()),
                of(TUBE_CORAL, new CoralTransformer()),
                of(HORN_CORAL, new CoralTransformer()),
                of(BUBBLE_CORAL, new CoralTransformer()),
                of(DEAD_FIRE_CORAL, new CoralTransformer()),
                of(DEAD_BRAIN_CORAL, new CoralTransformer()),
                of(DEAD_TUBE_CORAL, new CoralTransformer()),
                of(DEAD_HORN_CORAL, new CoralTransformer()),
                of(DEAD_BUBBLE_CORAL, new CoralTransformer()),
                of(SCULK_SENSOR, new SculkSensorTransformer()),
                of(PUMPKIN, new DirectionToCardinalTransformer()),
                of(LIT_PUMPKIN, new DirectionToCardinalTransformer()),
                of(CARVED_PUMPKIN, new DirectionToCardinalTransformer()),
                of(ACACIA_HANGING_SIGN),
                of(BAMBOO_BUTTON),
                of(BAMBOO_DOOR),
                of(BAMBOO_FENCE),
                of(BAMBOO_FENCE_GATE),
                of(BAMBOO_HANGING_SIGN),
                of(BAMBOO_MOSAIC),
                of(BAMBOO_MOSAIC_SLAB),
                of(BAMBOO_MOSAIC_DOUBLE_SLAB),
                of(BAMBOO_MOSAIC_STAIRS),
                of(BAMBOO_PLANKS),
                of(BAMBOO_PRESSURE_PLATE),
                of(BAMBOO_WALL_SIGN),
                of(BAMBOO_STANDING_SIGN),
                of(BAMBOO_SLAB),
                of(BAMBOO_DOUBLE_SLAB),
                of(BAMBOO_STAIRS),
                of(BAMBOO_TRAPDOOR),
                of(BIRCH_HANGING_SIGN),
                of(BAMBOO_BLOCK),
                of(STRIPPED_BAMBOO_BLOCK),
                of(CALIBRATED_SCULK_SENSOR),
                of(CHERRY_BUTTON),
                of(CHERRY_DOOR),
                of(CHERRY_FENCE),
                of(CHERRY_FENCE_GATE),
                of(CHERRY_HANGING_SIGN),
                of(CHERRY_LEAVES),
                of(CHERRY_LOG),
                of(CHERRY_PLANKS),
                of(CHERRY_PRESSURE_PLATE),
                of(CHERRY_SAPLING),
                of(CHERRY_WALL_SIGN),
                of(CHERRY_STANDING_SIGN),
                of(CHERRY_SLAB),
                of(CHERRY_DOUBLE_SLAB),
                of(CHERRY_STAIRS),
                of(CHERRY_TRAPDOOR),
                of(CHERRY_WOOD),
                of(CHISELED_BOOKSHELF),
                of(CRIMSON_HANGING_SIGN),
                of(DARK_OAK_HANGING_SIGN),
                of(DECORATED_POT),
                of(JUNGLE_HANGING_SIGN),
                of(MANGROVE_HANGING_SIGN),
                of(OAK_HANGING_SIGN),
                of(PIGLIN_HEAD),
                of(PINK_PETALS),
                of(PITCHER_PLANT),
                of(SNIFFER_EGG),
                of(SPRUCE_HANGING_SIGN),
                of(STRIPPED_CHERRY_LOG),
                of(STRIPPED_CHERRY_WOOD),
                of(SUSPICIOUS_GRAVEL),
                of(SUSPICIOUS_SAND),
                of(TORCHFLOWER),
                of(TORCHFLOWER_CROP),
                of(WARPED_HANGING_SIGN)
        );
    }
}
