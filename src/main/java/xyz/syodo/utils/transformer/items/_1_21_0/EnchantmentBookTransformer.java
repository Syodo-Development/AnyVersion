package xyz.syodo.utils.transformer.items._1_21_0;

import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.nbt.NbtType;
import org.cloudburstmc.protocol.bedrock.data.inventory.ItemData;
import xyz.syodo.registries.Registries;
import xyz.syodo.utils.transformer.items.ItemDataTransformer;

import java.util.List;

public class EnchantmentBookTransformer extends ItemDataTransformer {

    @Override
    public ItemData transform(ItemData original) {
        NbtMap map = original.getTag();
        List<NbtMap> enchantments = map.getList("ench", NbtType.COMPOUND);
        for(NbtMap ench : enchantments) {
            if(ench.getShort("id") > 37) {
                return Registries.ITEM.getOutdated(original);
            }
        }
        return original;
    }

}
