package xyz.syodo.utils.transformer.items._1_20_70;

import cn.nukkit.item.ItemID;
import cn.nukkit.registry.Registries;
import lombok.RequiredArgsConstructor;
import org.cloudburstmc.protocol.bedrock.data.definitions.ItemDefinition;
import org.cloudburstmc.protocol.bedrock.data.definitions.SimpleItemDefinition;
import org.cloudburstmc.protocol.bedrock.data.inventory.ItemData;
import xyz.syodo.utils.transformer.items.ItemDataTransformer;

import static cn.nukkit.tags.BlockTags.GRASS;

@RequiredArgsConstructor
public class SpawnEggTransformer extends ItemDataTransformer {

    final int eid;

    @Override
    public ItemData transform(ItemData original) {
        ItemDefinition originalItemDefinition = original.getDefinition();
        SimpleItemDefinition itemDefinition = new SimpleItemDefinition(ItemID.SPAWN_EGG, Registries.ITEM_RUNTIMEID.get(ItemID.SPAWN_EGG), originalItemDefinition.getVersion(), originalItemDefinition.isComponentBased(), originalItemDefinition.getComponentData());
        return ItemData.builder()
                .definition(itemDefinition)
                .damage(eid)
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
