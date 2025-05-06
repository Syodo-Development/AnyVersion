package xyz.syodo.utils.transformer.items._1_21_30;

import cn.nukkit.registry.Registries;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.protocol.bedrock.data.definitions.ItemDefinition;
import org.cloudburstmc.protocol.bedrock.data.definitions.SimpleBlockDefinition;
import org.cloudburstmc.protocol.bedrock.data.definitions.SimpleItemDefinition;
import org.cloudburstmc.protocol.bedrock.data.inventory.ItemData;
import xyz.syodo.utils.transformer.items.ItemDataTransformer;

import java.util.Map;

import static cn.nukkit.block.BlockID.*;

public class PurpurTransformer extends ItemDataTransformer {

    @Override
    public ItemData transform(ItemData original) {
        SimpleBlockDefinition definition = (SimpleBlockDefinition) original.getBlockDefinition();
        ItemDefinition originalItemDefinition = original.getDefinition();
        NbtMap map = NbtMap.fromMap((Map<String, Object>) definition.getState().get("states"));
        int damage = switch (map.getString("identifier")) {
            case "minecraft:deprecated_purpur_block_1" -> 1;
            case PURPUR_PILLAR -> 2;
            case "minecraft:deprecated_purpur_block_2" -> 3;
            default -> 0;
        };
        SimpleItemDefinition itemDefinition = new SimpleItemDefinition(originalItemDefinition.getIdentifier(), Registries.ITEM_RUNTIMEID.getInt(PURPUR_BLOCK), originalItemDefinition.getVersion(), originalItemDefinition.isComponentBased(), originalItemDefinition.getComponentData());
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
