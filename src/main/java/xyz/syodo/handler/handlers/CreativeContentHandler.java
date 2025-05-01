package xyz.syodo.handler.handlers;

import org.cloudburstmc.protocol.bedrock.data.inventory.CreativeItemData;
import org.cloudburstmc.protocol.bedrock.packet.CreativeContentPacket;
import xyz.syodo.handler.PacketHandler;
import xyz.syodo.manager.ProtocolPlayer;
import xyz.syodo.registries.Registries;
import xyz.syodo.utils.definition.ItemDefinition;

import java.util.ArrayList;
import java.util.List;

public class CreativeContentHandler extends PacketHandler<CreativeContentPacket> {

    @Override
    public void handle(ProtocolPlayer player, CreativeContentPacket packet) {
        List<CreativeItemData> c = new ArrayList<>(packet.getContents());
        packet.getContents().clear();
        for(CreativeItemData data : c) {
            if(Registries.ITEM.getProtocolVersion(ItemDefinition.of(data.getItem().getDefinition().getIdentifier())).protocol() <= player.protocol()) {
                packet.getContents().add(data);
            }
        }
    }

}
