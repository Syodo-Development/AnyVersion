package xyz.syodo.utils.transformer.items._1_21_20;

import org.cloudburstmc.protocol.bedrock.data.definitions.ItemDefinition;
import org.cloudburstmc.protocol.bedrock.data.definitions.SimpleItemDefinition;
import org.cloudburstmc.protocol.bedrock.data.inventory.ItemData;
import xyz.syodo.utils.transformer.items.ItemDataTransformer;

public class FlowerTransformer extends ItemDataTransformer {

    public static final String YELLOW_FLOWER = "minecraft:yellow_flower";

    @Override
    public ItemData transform(ItemData original) {
        ItemDefinition originalItemDefinition = original.getDefinition();
        SimpleItemDefinition itemDefinition = new SimpleItemDefinition(YELLOW_FLOWER, 37, originalItemDefinition.getVersion(), originalItemDefinition.isComponentBased(), originalItemDefinition.getComponentData());
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
