package xyz.syodo.utils.transformer.items;

import org.cloudburstmc.protocol.bedrock.data.inventory.ItemData;

public abstract class ItemDataTransformer {

    public abstract ItemData transform(ItemData original);

}
