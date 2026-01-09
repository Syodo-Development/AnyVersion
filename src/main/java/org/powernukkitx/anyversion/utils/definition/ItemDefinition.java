package org.powernukkitx.anyversion.utils.definition;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.cloudburstmc.protocol.bedrock.data.inventory.ItemData;
import org.powernukkitx.anyversion.utils.transformer.items.ItemDataTransformer;
import org.powernukkitx.anyversion.utils.transformer.items.UnknownItemTransformer;

@Getter
@RequiredArgsConstructor
public class ItemDefinition extends Definition {

    private final String namespaceId;
    private final ItemDataTransformer downgrade;

    public static ItemDefinition of(ItemData data) {
        return of(data.getDefinition().getIdentifier());
    }

    public static ItemDefinition of(String namespaceId) {
        return of(namespaceId, new UnknownItemTransformer());
    }

    public static ItemDefinition of(String namespaceId, ItemDataTransformer transformer) {
        return new ItemDefinition(namespaceId, transformer);
    }


    @Override
    public boolean equals(Object obj) {
        if(obj instanceof ItemDefinition definition) {
            return definition.namespaceId.equals(namespaceId);
        }
        return false;
    }
}
