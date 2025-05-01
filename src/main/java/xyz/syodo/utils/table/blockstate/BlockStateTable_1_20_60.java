package xyz.syodo.utils.table.blockstate;

import xyz.syodo.utils.ProtocolVersion;
import xyz.syodo.utils.transformer.IdentifierTransformer;
import xyz.syodo.utils.transformer._1_20_60.HardStainedGlassPaneTransformer;
import xyz.syodo.utils.transformer._1_20_60.HardStainedGlassTransformer;
import xyz.syodo.utils.transformer._1_20_70.*;

import static cn.nukkit.block.BlockID.*;
import static cn.nukkit.tags.BlockTags.GRASS;
import static xyz.syodo.utils.definition.BlockStateDefinition.of;

public class BlockStateTable_1_20_60 extends BlockStateTable {

    public BlockStateTable_1_20_60() {
        super(ProtocolVersion.MINECRAFT_PE_1_20_60,
                of(HARD_BLACK_STAINED_GLASS, new HardStainedGlassTransformer()),
                of(HARD_BLUE_STAINED_GLASS, new HardStainedGlassTransformer()),
                of(HARD_BROWN_STAINED_GLASS, new HardStainedGlassTransformer()),
                of(HARD_CYAN_STAINED_GLASS, new HardStainedGlassTransformer()),
                of(HARD_GRAY_STAINED_GLASS, new HardStainedGlassTransformer()),
                of(HARD_GREEN_STAINED_GLASS, new HardStainedGlassTransformer()),
                of(HARD_LIGHT_BLUE_STAINED_GLASS, new HardStainedGlassTransformer()),
                of(HARD_LIME_STAINED_GLASS, new HardStainedGlassTransformer()),
                of(HARD_MAGENTA_STAINED_GLASS, new HardStainedGlassTransformer()),
                of(HARD_ORANGE_STAINED_GLASS, new HardStainedGlassTransformer()),
                of(HARD_PINK_STAINED_GLASS, new HardStainedGlassTransformer()),
                of(HARD_PURPLE_STAINED_GLASS, new HardStainedGlassTransformer()),
                of(HARD_RED_STAINED_GLASS, new HardStainedGlassTransformer()),
                of(HARD_LIGHT_GRAY_STAINED_GLASS, new HardStainedGlassTransformer()),
                of(HARD_YELLOW_STAINED_GLASS, new HardStainedGlassTransformer()),
                of(HARD_WHITE_STAINED_GLASS, new HardStainedGlassTransformer()),
                of(HARD_BLACK_STAINED_GLASS_PANE, new HardStainedGlassPaneTransformer()),
                of(HARD_BLUE_STAINED_GLASS_PANE, new HardStainedGlassPaneTransformer()),
                of(HARD_BROWN_STAINED_GLASS_PANE, new HardStainedGlassPaneTransformer()),
                of(HARD_CYAN_STAINED_GLASS_PANE, new HardStainedGlassPaneTransformer()),
                of(HARD_GRAY_STAINED_GLASS_PANE, new HardStainedGlassPaneTransformer()),
                of(HARD_GREEN_STAINED_GLASS_PANE, new HardStainedGlassPaneTransformer()),
                of(HARD_LIGHT_BLUE_STAINED_GLASS_PANE, new HardStainedGlassPaneTransformer()),
                of(HARD_LIME_STAINED_GLASS_PANE, new HardStainedGlassPaneTransformer()),
                of(HARD_MAGENTA_STAINED_GLASS_PANE, new HardStainedGlassPaneTransformer()),
                of(HARD_ORANGE_STAINED_GLASS_PANE, new HardStainedGlassPaneTransformer()),
                of(HARD_PINK_STAINED_GLASS_PANE, new HardStainedGlassPaneTransformer()),
                of(HARD_PURPLE_STAINED_GLASS_PANE, new HardStainedGlassPaneTransformer()),
                of(HARD_RED_STAINED_GLASS_PANE, new HardStainedGlassPaneTransformer()),
                of(HARD_LIGHT_GRAY_STAINED_GLASS_PANE, new HardStainedGlassPaneTransformer()),
                of(HARD_YELLOW_STAINED_GLASS_PANE, new HardStainedGlassPaneTransformer()),
                of(HARD_WHITE_STAINED_GLASS_PANE, new HardStainedGlassPaneTransformer())
        );
    }
}
