package xyz.syodo.handler.handlers;

import org.cloudburstmc.protocol.bedrock.data.inventory.CreativeItemData;
import org.cloudburstmc.protocol.bedrock.data.inventory.ItemData;
import org.cloudburstmc.protocol.bedrock.packet.CreativeContentPacket;
import xyz.syodo.handler.PacketHandler;
import xyz.syodo.manager.ProtocolPlayer;
import xyz.syodo.registries.Registries;

import java.util.ArrayList;
import java.util.List;

public class CreativeContentHandler extends PacketHandler<CreativeContentPacket> {

    @Override
    public void handle(ProtocolPlayer player, CreativeContentPacket packet) {
        List<CreativeItemData> c = new ArrayList<>(packet.getContents());
        packet.getContents().clear();
        for(CreativeItemData data : c) {
            ItemData itemData = Registries.ITEM.downgrade(player.getVersion(), data.getItem());
            if(itemData.getDefinition().getIdentifier().equals(Registries.ITEM.getOutdated(itemData).getDefinition().getIdentifier())) continue;
            CreativeItemData newData = CreativeItemData.builder()
                    .item(itemData)
                    .netId(data.getNetId())
                    .groupId(data.getGroupId())
                    .build();
            packet.getContents().add(newData);
        }
    }

}
