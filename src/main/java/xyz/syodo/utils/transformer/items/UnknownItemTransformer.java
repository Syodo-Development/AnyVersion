package xyz.syodo.utils.transformer.items;

import org.cloudburstmc.protocol.bedrock.data.inventory.ItemData;
import xyz.syodo.registries.Registries;

public class UnknownItemTransformer extends ItemDataTransformer {

    @Override
    public ItemData transform(ItemData original) {
        return Registries.ITEM.getOutdated(original);
    }

}
