package xyz.syodo.handler.handlers;

import cn.nukkit.nbt.tag.CompoundTag;
import cn.nukkit.registry.ItemRegistry;
import cn.nukkit.registry.ItemRuntimeIdRegistry;
import cn.nukkit.registry.Registries;
import org.cloudburstmc.nbt.NbtMap;
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
                    CompoundTag tag = Registries.ITEM.getCustomItemDefinition().get(data.identifier()).nbt();
                    definitions.add(new SimpleItemDefinition(data.identifier(), data.runtimeId(), ItemVersion.from(data.version()), data.componentBased(), NbtMap.fromMap(tag.parseValue())));
                }
            }
            packet.getItems().clear();
            packet.getItems().addAll(definitions);
        }
    }

}
