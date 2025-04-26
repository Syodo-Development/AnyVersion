package xyz.syodo.registries.registries;

import it.unimi.dsi.fastutil.objects.ObjectArraySet;
import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet;
import xyz.syodo.utils.ProtocolVersion;
import xyz.syodo.utils.definition.BlockStateDefinition;
import xyz.syodo.utils.table.blockstate.BlockStateTable_1_21_20;
import xyz.syodo.utils.transformer._1_21_20.DirtStateTransformer;
import xyz.syodo.utils.table.blockstate.BlockStateTable;
import xyz.syodo.utils.transformer._1_21_20.StoneBlockSlab2Transformer;

import static cn.nukkit.block.BlockID.*;
import static xyz.syodo.utils.definition.BlockStateDefinition.of;

public class BlockStateRegistry extends Registry {

    private final ObjectArraySet<BlockStateTable> TABLES = new ObjectArraySet<>();


    @Override
    public void init() {
        TABLES.add(new BlockStateTable(ProtocolVersion.MINECRAFT_PE_1_21_70,
                of(BUSH),
                of(CACTUS_FLOWER),
                of(FIREFLY_BUSH),
                of(LEAF_LITTER),
                of(SHORT_DRY_GRASS),
                of(TALL_DRY_GRASS),
                of(WILDFLOWERS)
        ));
        TABLES.add(new BlockStateTable(ProtocolVersion.MINECRAFT_PE_1_21_50,
                of(RESIN_BLOCK),
                of(CHISELED_RESIN_BRICKS),
                of(CLOSED_EYEBLOSSOM),
                of(CREAKING_HEART),
                of(OPEN_EYEBLOSSOM),
                of(PALE_HANGING_MOSS),
                of(PALE_MOSS_BLOCK),
                of(PALE_MOSS_CARPET),
                of(PALE_OAK_BUTTON),
                of(PALE_OAK_DOOR),
                of(PALE_OAK_FENCE),
                of(PALE_OAK_FENCE_GATE),
                of(PALE_OAK_HANGING_SIGN),
                of(PALE_OAK_LEAVES),
                of(PALE_OAK_LOG),
                of(PALE_OAK_PLANKS),
                of(PALE_OAK_PRESSURE_PLATE),
                of(PALE_OAK_SAPLING),
                of(PALE_OAK_STANDING_SIGN),
                of(PALE_OAK_WALL_SIGN),
                of(PALE_OAK_SLAB),
                of(PALE_OAK_STAIRS),
                of(PALE_OAK_TRAPDOOR),
                of(PALE_OAK_WOOD),
                of(RESIN_BRICK_SLAB),
                of(RESIN_BRICK_STAIRS),
                of(RESIN_BRICK_WALL),
                of(RESIN_BRICKS),
                of(RESIN_CLUMP),
                of(STRIPPED_PALE_OAK_LOG),
                of(STRIPPED_PALE_OAK_WOOD)
        ));
        TABLES.add(new BlockStateTable(ProtocolVersion.MINECRAFT_PE_1_21_40,
                of(SKELETON_SKULL),
                of(PLAYER_HEAD),
                of(WITHER_SKELETON_SKULL),
                of(ZOMBIE_HEAD),
                of(CREEPER_HEAD),
                of(DRAGON_HEAD),
                of(PIGLIN_HEAD)
        ));
        TABLES.add(new BlockStateTable(ProtocolVersion.MINECRAFT_PE_1_21_30,
                of(PRISMARINE_WALL),
                of(RED_SANDSTONE_WALL),
                of(MOSSY_STONE_BRICK_WALL),
                of(MOSSY_COBBLESTONE_WALL),
                of(SANDSTONE_WALL),
                of(NETHER_BRICK_WALL),
                of(GRANITE_WALL),
                of(RED_NETHER_BRICK_WALL),
                of(STONE_BRICK_WALL),
                of(END_STONE_BRICK_WALL),
                of(BRICK_WALL),
                of(ANDESITE_WALL),
                of(DIORITE_WALL),
                of(PURPUR_PILLAR),
                of(WET_SPONGE),
                of(STRUCTURE_VOID)
        ));
        TABLES.add(new BlockStateTable_1_21_20());
    }

    public ProtocolVersion getProtocolVersion(BlockStateDefinition definition) {
        for(BlockStateTable table : TABLES) {
            if(table.getContent().stream().anyMatch(definition::equals)) {
                return table.getVersion();
            }
        }
        return ProtocolVersion.getMin();
    }

    public BlockStateDefinition downgrade(ProtocolVersion version, BlockStateDefinition definition) {
        ObjectOpenHashSet<BlockStateTable> tables = new ObjectOpenHashSet<>();
        for(BlockStateTable table : TABLES) {
            var optresult = table.getContent().stream().filter(definition::equals).findFirst();
            if(optresult.isPresent()) {
                tables.add(table);
            }
        }
        BlockStateTable usedTable = new BlockStateTable(ProtocolVersion.getMax(), definition);
        for(BlockStateTable table : tables) {
            int prot = table.getVersion().protocol();
            if(prot > version.protocol() && prot < usedTable.getVersion().protocol()) {
                usedTable = table;
            }
        }
        return usedTable.getContent().stream().filter(definition::equals).findFirst().orElse(definition);
    }

}
