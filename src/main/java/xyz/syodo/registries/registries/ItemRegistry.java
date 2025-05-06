package xyz.syodo.registries.registries;

import cn.nukkit.block.*;
import cn.nukkit.item.ItemID;
import cn.nukkit.nbt.tag.CompoundTag;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import it.unimi.dsi.fastutil.objects.ObjectArraySet;
import lombok.extern.slf4j.Slf4j;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.protocol.bedrock.data.definitions.SimpleBlockDefinition;
import org.cloudburstmc.protocol.bedrock.data.definitions.SimpleItemDefinition;
import org.cloudburstmc.protocol.bedrock.data.inventory.ItemData;
import org.cloudburstmc.protocol.bedrock.data.inventory.ItemVersion;
import xyz.syodo.registries.Registries;
import xyz.syodo.utils.ProtocolVersion;
import xyz.syodo.utils.definition.ItemDefinition;
import xyz.syodo.utils.table.item.*;
import xyz.syodo.utils.transformer.items.ItemBlockTransformer;
import xyz.syodo.utils.transformer.items.ItemDataTransformer;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static xyz.syodo.utils.definition.ItemDefinition.of;
import static cn.nukkit.item.ItemID.*;

@Slf4j
public class ItemRegistry extends Registry {

    private final ObjectArraySet<ItemTable> TABLES = new ObjectArraySet<>();

    public ObjectArraySet<ItemTable> getTables() {
        return TABLES;
    }

    @Override
    public void init() {
        TABLES.add(new ItemDataTable_1_21_70());
        TABLES.add(new ItemDataTable_1_21_50());
        TABLES.add(new ItemDataTable_1_21_40());
        TABLES.add(new ItemDataTable_1_21_30());
        TABLES.add(new ItemDataTable_1_21_20());
        TABLES.add(new ItemDataTable_1_19_80());
    }

    public ItemData downgrade(ProtocolVersion version, ItemData data) {
        return downgrade(version, data, BlockAir.STATE, new ObjectArraySet<>());
    }

    public ItemData downgrade(ProtocolVersion version, ItemData data, BlockState state, ObjectArraySet<Class<? extends ItemDataTransformer>> usedDefinitions) {
        SimpleItemDefinition stateDefinition = (SimpleItemDefinition) data.getDefinition();
        SimpleBlockDefinition simpleBlockDefinition = (SimpleBlockDefinition) data.getBlockDefinition();
        BlockState blockState = state;
        if(usedDefinitions.isEmpty()) {
            if(blockState.getIdentifier().equals(BlockID.AIR)) {
                Block block = Block.get(stateDefinition.getIdentifier());
                if (block != null) {
                    blockState = block.getProperties().getDefaultState();
                    simpleBlockDefinition = new SimpleBlockDefinition(stateDefinition.getIdentifier(), blockState.blockStateHash(), NbtMap.fromMap(blockState.getBlockStateTag().parseValue()));
                }
            } else simpleBlockDefinition = new SimpleBlockDefinition(blockState.getIdentifier(), blockState.blockStateHash(), NbtMap.fromMap(blockState.getBlockStateTag().parseValue()));
        }
        data = ItemData.builder()
                .definition(stateDefinition)
                .damage(data.getDamage())
                .count(data.getCount())
                .tag(data.getTag())
                .canPlace(data.getCanPlace())
                .canBreak(data.getCanBreak())
                .blockingTicks(data.getBlockingTicks())
                .blockDefinition(simpleBlockDefinition)
                .usingNetId(data.isUsingNetId())
                .netId(data.getNetId())
                .build();
        ItemDefinition definition = of(data);
        ObjectArrayList<ItemTable> tables = new ObjectArrayList<>();
        for(ItemTable table : TABLES) {
            var optresult = table.getContent().stream().filter(definition::equals).findFirst();
            if(optresult.isPresent()) {
                tables.add(table);
            }
        }
        final String identifier = data.getDefinition().getIdentifier();

        tables.sort(Comparator.comparingInt(o -> o.getVersion().protocol()));
        for(ItemTable table : tables.reversed()) {
            int prot = table.getVersion().protocol();
            if(prot > version.protocol()) {
                List<ItemDefinition> definitions = new ArrayList<>(table.getContent().stream().filter(def -> definition.equals(def) && !usedDefinitions.contains(def.getDowngrade().getClass())).toList());
                if(definitions.isEmpty()) definitions.add(definition);
                for(ItemDefinition itemDefinition : definitions) {
                    usedDefinitions.add(itemDefinition.getDowngrade().getClass());
                    data = itemDefinition.getDowngrade().transform(data);
                }
            }
        }
        if(!identifier.equals(data.getDefinition().getIdentifier())) {
            data = downgrade(version, data, blockState, usedDefinitions);

        }
//        if(data.getBlockDefinition() != null) {
//            SimpleBlockDefinition blockDefinition = (SimpleBlockDefinition) data.getBlockDefinition();
//            if(blockState == BlockAir.STATE) return data;
//            if(blockDefinition.getState() != null) {
//                if(blockDefinition.getState().getCompound("states") != null) {
//                    data = ItemData.builder()
//                            .definition(data.getDefinition())
//                            .damage(data.getDamage())
//                            .count(data.getCount())
//                            .tag(data.getTag())
//                            .canPlace(data.getCanPlace())
//                            .canBreak(data.getCanBreak())
//                            .blockingTicks(data.getBlockingTicks())
//                            .blockDefinition(new SimpleBlockDefinition(blockDefinition.getIdentifier(), Registries.BLOCKPALETTE.getRuntimeId(version, blockState), blockDefinition.getState()))
//                            .usingNetId(data.isUsingNetId())
//                            .netId(data.getNetId())
//                            .build();
//                }
//            }
//        }
        return data;
    }

    public ItemData getOutdated(ItemData original) {
        SimpleItemDefinition itemDefinition = new SimpleItemDefinition(BlockID.BARRIER, cn.nukkit.registry.Registries.ITEM_RUNTIMEID.getInt(BlockID.BARRIER), ItemVersion.NONE, false, NbtMap.EMPTY);
        NbtMap nbtData = NbtMap.builder()
                .putInt("RepairCost", 0)
                .putCompound("display", NbtMap.builder().putString("Name", "§c%updateScreen.title: " + original.getDefinition().getIdentifier()).build())
                .build();
        NbtMap state = NbtMap.builder()
                .putString("name", BlockID.BARRIER)
                .putCompound("states", NbtMap.EMPTY)
                .putInt("version",18155264)
                .build();
        SimpleBlockDefinition blockDefinition = new SimpleBlockDefinition(BlockID.BARRIER, BlockBarrier.PROPERTIES.getDefaultState().blockStateHash(), state);
        return ItemData.builder()
                .definition(itemDefinition)
                .damage(0)
                .count(1)
                .tag(nbtData)
                .canPlace()
                .canBreak()
                .blockingTicks(0)
                .blockDefinition(blockDefinition)
                .usingNetId(original.isUsingNetId())
                .netId(original.getNetId())
                .build();
    }
}
