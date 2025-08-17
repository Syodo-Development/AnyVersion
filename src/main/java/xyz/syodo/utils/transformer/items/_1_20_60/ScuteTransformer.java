package xyz.syodo.utils.transformer.items._1_20_60;

import cn.nukkit.registry.Registries;
import org.cloudburstmc.protocol.bedrock.data.definitions.ItemDefinition;
import org.cloudburstmc.protocol.bedrock.data.definitions.SimpleItemDefinition;
import org.cloudburstmc.protocol.bedrock.data.inventory.ItemData;
import xyz.syodo.utils.transformer.items.ItemDataTransformer;

public class ScuteTransformer extends ItemDataTransformer {

    public static final String SCUTE = "minecraft:scute";

    @Override
    public ItemData transform(ItemData original) {
        ItemDefinition originalItemDefinition = original.getDefinition();
        SimpleItemDefinition itemDefinition = new SimpleItemDefinition(SCUTE, originalItemDefinition.getRuntimeId(), originalItemDefinition.getVersion(), originalItemDefinition.isComponentBased(), originalItemDefinition.getComponentData());
        return ItemData.builder()
                .definition(itemDefinition)
                .damage(original.getDamage())
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
