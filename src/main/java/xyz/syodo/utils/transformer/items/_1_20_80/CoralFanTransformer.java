package xyz.syodo.utils.transformer.items._1_20_80;

import cn.nukkit.block.BlockID;
import cn.nukkit.block.property.enums.CoralColor;
import cn.nukkit.registry.Registries;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.protocol.bedrock.data.definitions.ItemDefinition;
import org.cloudburstmc.protocol.bedrock.data.definitions.SimpleBlockDefinition;
import org.cloudburstmc.protocol.bedrock.data.definitions.SimpleItemDefinition;
import org.cloudburstmc.protocol.bedrock.data.inventory.ItemData;
import xyz.syodo.utils.transformer.items.ItemDataTransformer;

import java.util.Map;

public class CoralFanTransformer extends ItemDataTransformer {

    @Override
    public ItemData transform(ItemData original) {
        SimpleBlockDefinition definition = (SimpleBlockDefinition) original.getBlockDefinition();
        ItemDefinition originalItemDefinition = original.getDefinition();
        NbtMap map = NbtMap.fromMap((Map<String, Object>) definition.getState().get("states"));
        int damage = map.isEmpty() || map.getString("coral_color").isEmpty() ? 0 : CoralColor.valueOf(map.getString("coral_color").toUpperCase()).ordinal();
        boolean dead = original.getDefinition().getIdentifier().contains("_dead");
        SimpleItemDefinition itemDefinition = new SimpleItemDefinition(originalItemDefinition.getIdentifier(), Registries.ITEM_RUNTIMEID.get(dead ? BlockID.DEAD_TUBE_CORAL_FAN : BlockID.TUBE_CORAL_FAN), originalItemDefinition.getVersion(), originalItemDefinition.isComponentBased(), originalItemDefinition.getComponentData());
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
