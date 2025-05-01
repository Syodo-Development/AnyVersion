package xyz.syodo.registries.registries;

import cn.nukkit.block.BlockID;
import cn.nukkit.item.ItemID;
import it.unimi.dsi.fastutil.objects.ObjectArraySet;
import xyz.syodo.utils.ProtocolVersion;
import xyz.syodo.utils.definition.ItemDefinition;
import xyz.syodo.utils.table.ItemTable;

import static xyz.syodo.utils.definition.ItemDefinition.of;
import static cn.nukkit.item.ItemID.*;
import static cn.nukkit.block.BlockID.*;

public class ItemRegistry extends Registry {

    private final ObjectArraySet<ItemTable> TABLES = new ObjectArraySet<>();

    public ObjectArraySet<ItemTable> getTables() {
        return TABLES;
    }

    @Override
    public void init() {
        TABLES.add(new ItemTable(ProtocolVersion.MINECRAFT_PE_1_21_70,
                of(BLUE_EGG),
                of(BROWN_EGG),
                of(BUSH),
                of(CACTUS_FLOWER),
                of(FIREFLY_BUSH),
                of(LEAF_LITTER),
                of(SHORT_DRY_GRASS),
                of(TALL_DRY_GRASS),
                of(WILDFLOWERS),
                of(WHITE_DYE),
                of(LIGHT_GRAY_DYE),
                of(GRAY_DYE),
                of(BLACK_DYE),
                of(BROWN_DYE),
                of(RED_DYE),
                of(ORANGE_DYE),
                of(YELLOW_DYE),
                of(LIME_DYE),
                of(GREEN_DYE),
                of(CYAN_DYE),
                of(LIGHT_BLUE_DYE),
                of(BLUE_DYE),
                of(PURPLE_DYE),
                of(MAGENTA_DYE),
                of(PINK_DYE)
        ));
        TABLES.add(new ItemTable(ProtocolVersion.MINECRAFT_PE_1_21_50,
                of(RESIN_BLOCK),
                of(CHISELED_RESIN_BRICKS),
                of(CLOSED_EYEBLOSSOM),
                of(CREAKING_HEART),
                of(CREAKING_SPAWN_EGG),
                of(OPEN_EYEBLOSSOM),
                of(PALE_HANGING_MOSS),
                of(PALE_MOSS_BLOCK),
                of(PALE_MOSS_CARPET),
                of(PALE_OAK_BOAT),
                of(PALE_OAK_CHEST_BOAT),
                of(PALE_OAK_BUTTON),
                of(PALE_OAK_DOOR),
                of(PALE_OAK_FENCE),
                of(PALE_OAK_FENCE_GATE),
                of(ItemID.PALE_OAK_HANGING_SIGN),
                of(PALE_OAK_LEAVES),
                of(PALE_OAK_LOG),
                of(PALE_OAK_PLANKS),
                of(PALE_OAK_PRESSURE_PLATE),
                of(PALE_OAK_SAPLING),
                of(PALE_OAK_SIGN),
                of(PALE_OAK_SLAB),
                of(PALE_OAK_STAIRS),
                of(PALE_OAK_TRAPDOOR),
                of(PALE_OAK_WOOD),
                of(RESIN_BRICK),
                of(RESIN_BRICK_SLAB),
                of(RESIN_BRICK_STAIRS),
                of(RESIN_BRICK_WALL),
                of(RESIN_BRICKS),
                of(RESIN_CLUMP),
                of(STRIPPED_PALE_OAK_LOG),
                of(STRIPPED_PALE_OAK_WOOD)
        ));
        TABLES.add(new ItemTable(ProtocolVersion.MINECRAFT_PE_1_21_40,
                of(BORDURE_INDENTED_BANNER_PATTERN),
                of(FIELD_MASONED_BANNER_PATTERN),
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
                of(YELLOW_BUNDLE),
                of(BlockID.SKELETON_SKULL),
                of(BlockID.PLAYER_HEAD),
                of(BlockID.WITHER_SKELETON_SKULL),
                of(BlockID.ZOMBIE_HEAD),
                of(BlockID.CREEPER_HEAD),
                of(BlockID.DRAGON_HEAD),
                of(BlockID.PIGLIN_HEAD)
        ));
        TABLES.add(new ItemTable(ProtocolVersion.MINECRAFT_PE_1_21_30,
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
        TABLES.add(new ItemTable(ProtocolVersion.MINECRAFT_PE_1_21_20,
                of(CUT_SANDSTONE),
                of(CHISELED_SANDSTONE),
                of(SMOOTH_SANDSTONE),
                of(CHISELED_STONE_BRICKS),
                of(QUARTZ_PILLAR),
                of(SMOOTH_QUARTZ),
                of(CUT_RED_SANDSTONE),
                of(CHISELED_RED_SANDSTONE),
                of(SMOOTH_RED_SANDSTONE),
                of(RED_SAND),
                of(BlockID.RED_SANDSTONE),
                of(COARSE_DIRT),
                of(DAMAGED_ANVIL),
                of(CHIPPED_ANVIL),
                of(DANDELION)
        ));
        TABLES.add(new ItemTable(ProtocolVersion.MINECRAFT_PE_1_21_0,
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
        ));
    }

    public ProtocolVersion getProtocolVersion(ItemDefinition definition) {
        for(ItemTable table : TABLES) {
            if(table.getContent().stream().anyMatch(definition::equals)) {
                return table.getVersion();
            }
        }
        return ProtocolVersion.getMin();
    }

}
