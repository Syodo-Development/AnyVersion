package xyz.syodo.utils;

import cn.nukkit.camera.data.CameraPreset;
import cn.nukkit.nbt.tag.CompoundTag;
import cn.nukkit.registry.ItemRegistry;
import cn.nukkit.registry.ItemRuntimeIdRegistry;
import cn.nukkit.registry.Registries;
import lombok.AccessLevel;
import lombok.Getter;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.protocol.bedrock.data.definitions.*;
import org.cloudburstmc.protocol.bedrock.data.inventory.ItemVersion;
import org.cloudburstmc.protocol.common.DefinitionRegistry;
import org.cloudburstmc.protocol.common.NamedDefinition;
import org.cloudburstmc.protocol.common.SimpleDefinitionRegistry;

import java.util.ArrayList;
import java.util.List;

@Getter(AccessLevel.PACKAGE)
public class CloudburstRegistry {

    //We use the same palette for every version of the game and translate later on.

    private static CloudburstRegistry INSTANCE;

    public static CloudburstRegistry get() {
        if(INSTANCE == null) {
            INSTANCE = new CloudburstRegistry();
        }
        return INSTANCE;
    }

    private final DefinitionRegistry<ItemDefinition> itemDefinitionRegistry;
    private final DefinitionRegistry<BlockDefinition> blockDefinitionRegistry;
    private final DefinitionRegistry<NamedDefinition> namedDefinitionRegistry;

    private CloudburstRegistry() {
        List<ItemDefinition> itemDefinitions = new ArrayList<>();
        for(ItemRuntimeIdRegistry.ItemData data : ItemRuntimeIdRegistry.getITEMDATA()) {
            CompoundTag tag = new CompoundTag();

            if (ItemRegistry.getItemComponents().containsCompound(data.identifier())) {
                CompoundTag item_tag = ItemRegistry.getItemComponents().getCompound(data.identifier());
                tag.putCompound("components", item_tag.getCompound("components"));
            }
            else if (Registries.ITEM.getCustomItemDefinition().containsKey(data.identifier())) {
                tag = Registries.ITEM.getCustomItemDefinition().get(data.identifier()).nbt();
            }
            SimpleItemDefinition definition = new SimpleItemDefinition(data.identifier(), data.runtimeId(), ItemVersion.from(data.version()), data.componentBased(), NbtMap.fromMap(tag.parseValue()));
            itemDefinitions.add(definition);
        }
        List<BlockDefinition> blockDefinitions = new ArrayList<>();
        for (var blockState : Registries.BLOCKSTATE.getAllState()) {
            NbtMap map = NbtMap.fromMap(blockState.getBlockStateTag().parseValue());
            SimpleBlockDefinition definition = new SimpleBlockDefinition(blockState.getIdentifier(), blockState.blockStateHash(), map);
            blockDefinitions.add(definition);
        }
        List<NamedDefinition> namedDefinitions = new ArrayList<>();
        for(CameraPreset cameraPreset : CameraPreset.getPresets().values()) {
            SimpleNamedDefinition definition = new SimpleNamedDefinition(cameraPreset.getIdentifier(), cameraPreset.getId());
            namedDefinitions.add(definition);
        }
        itemDefinitionRegistry = SimpleDefinitionRegistry.<ItemDefinition>builder().addAll(itemDefinitions).build();
        blockDefinitionRegistry = SimpleDefinitionRegistry.<BlockDefinition>builder().addAll(blockDefinitions).build();
        namedDefinitionRegistry = SimpleDefinitionRegistry.<NamedDefinition>builder().addAll(namedDefinitions).build();
    }
}
