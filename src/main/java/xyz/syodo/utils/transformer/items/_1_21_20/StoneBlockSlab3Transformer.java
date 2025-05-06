package xyz.syodo.utils.transformer.items._1_21_20;

import cn.nukkit.block.property.enums.StoneSlabType3;
import cn.nukkit.registry.Registries;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.protocol.bedrock.data.definitions.ItemDefinition;
import org.cloudburstmc.protocol.bedrock.data.definitions.SimpleBlockDefinition;
import org.cloudburstmc.protocol.bedrock.data.definitions.SimpleItemDefinition;
import org.cloudburstmc.protocol.bedrock.data.inventory.ItemData;
import xyz.syodo.utils.transformer.items.ItemDataTransformer;

import java.util.Map;

public class StoneBlockSlab3Transformer extends ItemDataTransformer {

    @Override
    public ItemData transform(ItemData original) {
        SimpleBlockDefinition definition = (SimpleBlockDefinition) original.getBlockDefinition();
        ItemDefinition originalItemDefinition = original.getDefinition();
        int runtimeId = original.getDefinition().getIdentifier().startsWith("minecraft:double_") ? -167 : -162;
        NbtMap map = NbtMap.fromMap((Map<String, Object>) definition.getState().get("states"));
        int damage = map.isEmpty() || map.getString("stone_slab_type_3").isEmpty() ? 0 : StoneSlabType3.valueOf(map.getString("stone_slab_type_3").toUpperCase()).ordinal();
        SimpleItemDefinition itemDefinition = new SimpleItemDefinition(originalItemDefinition.getIdentifier(), runtimeId, originalItemDefinition.getVersion(), originalItemDefinition.isComponentBased(), originalItemDefinition.getComponentData());
        return ItemData.builder()
                .definition(itemDefinition)
                .damage(damage)
                .count(original.getCount())
                .tag(original.getTag())
                .canPlace(original.getCanPlace())
                .canBreak(original.getCanBreak())
                .blockingTicks(original.getBlockingTicks())
                .blockDefinition(original.getBlockDefinition())
                .usingNetId(original.isUsingNetId())
                .netId(original.getNetId())
                .build();
    }

}
