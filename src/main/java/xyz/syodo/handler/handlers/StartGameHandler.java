package xyz.syodo.handler.handlers;

import cn.nukkit.nbt.tag.CompoundTag;
import cn.nukkit.registry.ItemRegistry;
import cn.nukkit.registry.ItemRuntimeIdRegistry;
import cn.nukkit.registry.Registries;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.protocol.bedrock.data.definitions.ItemDefinition;
import org.cloudburstmc.protocol.bedrock.data.definitions.SimpleItemDefinition;
import org.cloudburstmc.protocol.bedrock.data.inventory.ItemData;
import org.cloudburstmc.protocol.bedrock.data.inventory.ItemVersion;
import org.cloudburstmc.protocol.bedrock.packet.StartGamePacket;
import xyz.syodo.handler.PacketHandler;
import xyz.syodo.manager.ProtocolPlayer;
import xyz.syodo.utils.ProtocolVersion;

import java.util.ArrayList;
import java.util.List;

public class StartGameHandler extends PacketHandler<StartGamePacket> {


    @Override
    public void handle(ProtocolPlayer player, StartGamePacket packet) {
        List<ItemDefinition> definitions = new ArrayList<>();
        if(player.protocol() < ProtocolVersion.MINECRAFT_PE_1_21_60.protocol()) {
            for(ItemRuntimeIdRegistry.ItemData data : ItemRuntimeIdRegistry.getITEMDATA()) {
                CompoundTag tag = new CompoundTag();

                if (ItemRegistry.getItemComponents().containsCompound(data.identifier())) {
                    CompoundTag item_tag = ItemRegistry.getItemComponents().getCompound(data.identifier());
                    tag.putCompound("components", item_tag.getCompound("components"));
                }
                else if (Registries.ITEM.getCustomItemDefinition().containsKey(data.identifier())) {
                    tag = Registries.ITEM.getCustomItemDefinition().get(data.identifier()).nbt();
                }


                SimpleItemDefinition definition = new SimpleItemDefinition(data.identifier(), data.runtimeId(), ItemVersion.from(data.version()), data.componentBased(), NbtMap.fromMap(tag.parseValue()));
                ItemData cbItemdata = ItemData.builder().definition(definition).build();
                SimpleItemDefinition downgraded = (SimpleItemDefinition) xyz.syodo.registries.Registries.ITEM.downgrade(player.getVersion(), cbItemdata).getDefinition();
                if(downgraded.getIdentifier().equals(xyz.syodo.registries.Registries.ITEM.getOutdated(cbItemdata).getDefinition().getIdentifier())) continue;
                definitions.add(downgraded);
            }
        }
        packet.setItemDefinitions(definitions);
    }

}
