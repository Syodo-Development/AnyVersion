package xyz.syodo.handler.handlers;

import cn.nukkit.block.BlockID;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import org.cloudburstmc.protocol.bedrock.data.definitions.SimpleBlockDefinition;
import org.cloudburstmc.protocol.bedrock.data.inventory.ItemData;
import org.cloudburstmc.protocol.bedrock.packet.InventoryContentPacket;
import xyz.syodo.handler.PacketHandler;
import xyz.syodo.manager.ProtocolPlayer;
import xyz.syodo.registries.Registries;

import java.util.List;

public class InventoryContentHandler extends PacketHandler<InventoryContentPacket> {

    @Override
    public void handle(ProtocolPlayer player, InventoryContentPacket packet) {
        List<ItemData> content = new ObjectArrayList<>();
        for(ItemData data : packet.getContents()) {
            if(data.getDefinition().getIdentifier().equals(BlockID.AIR)) {
                content.add(data);
                continue;
            }
            ItemData downgraded = Registries.ITEM.downgrade(player.getVersion(), data);
            if(data.getDefinition().getIdentifier().equals(Registries.ITEM.getOutdated(downgraded).getDefinition().getIdentifier())) {
                content.add(downgraded);
                continue;
            }
            if(downgraded.getBlockDefinition() != null) {
                if(downgraded.getBlockDefinition() instanceof SimpleBlockDefinition definition) {
                    if(definition.getState() != null) {
                        definition.getState().getCompound("states").remove("identifier");
                    }
                }
            }
            content.add(downgraded);
        }
        packet.setContents(content);
    }

}
