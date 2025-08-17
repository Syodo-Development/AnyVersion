package xyz.syodo.utils.transformer.items._1_20_70;

import cn.nukkit.block.BlockID;
import cn.nukkit.block.property.enums.DoublePlantType;
import cn.nukkit.block.property.enums.OldLeafType;
import cn.nukkit.registry.Registries;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.protocol.bedrock.data.definitions.ItemDefinition;
import org.cloudburstmc.protocol.bedrock.data.definitions.SimpleBlockDefinition;
import org.cloudburstmc.protocol.bedrock.data.definitions.SimpleItemDefinition;
import org.cloudburstmc.protocol.bedrock.data.inventory.ItemData;
import xyz.syodo.utils.transformer.items.ItemDataTransformer;

import java.util.Map;

public class LeavesTransformer extends ItemDataTransformer {

    @Override
    public ItemData transform(ItemData original) {
        SimpleBlockDefinition definition = (SimpleBlockDefinition) original.getBlockDefinition();
        ItemDefinition originalItemDefinition = original.getDefinition();
        NbtMap map = NbtMap.fromMap((Map<String, Object>) definition.getState().get("states"));
        int damage = map.isEmpty() || map.getString("old_leaf_type").isEmpty() ? 0 : OldLeafType.valueOf(map.getString("old_leaf_type").toUpperCase()).ordinal();
        SimpleItemDefinition itemDefinition = new SimpleItemDefinition(originalItemDefinition.getIdentifier(), Registries.ITEM_RUNTIMEID.get(BlockID.OAK_LEAVES), originalItemDefinition.getVersion(), originalItemDefinition.isComponentBased(), originalItemDefinition.getComponentData());
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
