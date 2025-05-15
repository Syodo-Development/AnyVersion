package xyz.syodo.handler.handlers;

import cn.nukkit.Server;
import cn.nukkit.nbt.tag.*;
import cn.nukkit.registry.ItemRegistry;
import cn.nukkit.registry.ItemRuntimeIdRegistry;
import cn.nukkit.registry.Registries;
import org.cloudburstmc.nbt.*;
import org.cloudburstmc.protocol.bedrock.data.definitions.ItemDefinition;
import org.cloudburstmc.protocol.bedrock.data.definitions.SimpleItemDefinition;
import org.cloudburstmc.protocol.bedrock.data.inventory.ItemVersion;
import org.cloudburstmc.protocol.bedrock.packet.ItemComponentPacket;
import xyz.syodo.handler.PacketHandler;
import xyz.syodo.manager.ProtocolPlayer;
import xyz.syodo.utils.ProtocolVersion;

import java.util.ArrayList;
import java.util.List;

public class ItemRegistryHandler extends PacketHandler<ItemComponentPacket> {

    @Override
    public void handle(ProtocolPlayer player, ItemComponentPacket packet) {
        if(player.protocol() < ProtocolVersion.MINECRAFT_PE_1_21_60.protocol()) {
            List<ItemDefinition> definitions = new ArrayList<>();
            for(ItemRuntimeIdRegistry.ItemData data : ItemRuntimeIdRegistry.getITEMDATA()) {
                if (Registries.ITEM.getCustomItemDefinition().containsKey(data.identifier())) {
                    CompoundTag tag = Registries.ITEM.getCustomItemDefinition().get(data.identifier()).nbt().copy();
                    if(player.getVersion().protocol() < ProtocolVersion.MINECRAFT_PE_1_20_60.protocol()) {
                        CompoundTag icon = tag.getCompound("components")
                                .getCompound("item_properties")
                                .getCompound("minecraft:icon");
                        icon.putString("texture", icon.getCompound("textures").getString("default"));
                        icon.remove("textures");
                    }
                    NbtMap map = fromCompound(tag);
                    definitions.add(new SimpleItemDefinition(data.identifier(), data.runtimeId(), ItemVersion.from(data.version()), data.componentBased(), map));
                }
            }
            packet.getItems().clear();
            packet.getItems().addAll(definitions);
        }
    }

    public NbtMap fromCompound(CompoundTag tag) {
        NbtMapBuilder builder = NbtMap.builder();
        for(var entry : tag.getTags().entrySet()) {
            String key = entry.getKey();
            Tag value = entry.getValue();
            if(value instanceof CompoundTag v) {
                builder.put(key, fromCompound(v));
            } else if(value instanceof ListTag<?> v) {
                NbtType type = NbtType.byId(v.type);
                builder.putList(key, type, v.parseValue().toArray());
            } else if(value instanceof ByteTag v) {
                builder.putByte(key, (byte) (v.data & 0xFF));
            } else if(value instanceof ShortTag v) {
                builder.putShort(key, v.data);
            } else builder.put(key, value.parseValue());
        }
        return builder.build();
    }
}
