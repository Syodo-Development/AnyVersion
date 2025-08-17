package xyz.syodo.utils.transformer.items._1_21_0;

import org.cloudburstmc.protocol.bedrock.data.inventory.ItemData;
import xyz.syodo.registries.Registries;
import xyz.syodo.utils.transformer.items.ItemDataTransformer;

public class PotionTransformer extends ItemDataTransformer {

    @Override
    public ItemData transform(ItemData original) {
        if(original.getDamage() < 43) {
            return original;
        } else return Registries.ITEM.getOutdated(original);
    }

}
