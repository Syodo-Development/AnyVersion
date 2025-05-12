package xyz.syodo.utils.transformer.items._1_21_0;

import cn.nukkit.block.BlockID;
import cn.nukkit.block.property.enums.StoneSlabType;
import cn.nukkit.registry.Registries;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.protocol.bedrock.data.definitions.ItemDefinition;
import org.cloudburstmc.protocol.bedrock.data.definitions.SimpleBlockDefinition;
import org.cloudburstmc.protocol.bedrock.data.definitions.SimpleItemDefinition;
import org.cloudburstmc.protocol.bedrock.data.inventory.ItemData;
import xyz.syodo.utils.transformer.items.ItemDataTransformer;

import java.util.Map;

public class StoneBlockSlabTransformer extends ItemDataTransformer {

    @Override
    public ItemData transform(ItemData original) {
        SimpleBlockDefinition definition = (SimpleBlockDefinition) original.getBlockDefinition();
        ItemDefinition originalItemDefinition = original.getDefinition();
        int runtimeId = Registries.ITEM_RUNTIMEID.getInt(BlockID.SMOOTH_STONE_SLAB);
        NbtMap map = NbtMap.fromMap((Map<String, Object>) definition.getState().get("states"));
        int damage = map.isEmpty() || map.getString("stone_slab_type").isEmpty() ? 0 : StoneSlabType.valueOf(map.getString("stone_slab_type").toUpperCase()).ordinal();
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
