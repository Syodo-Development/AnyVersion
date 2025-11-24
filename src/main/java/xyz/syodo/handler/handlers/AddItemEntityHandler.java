package xyz.syodo.handler.handlers;

import cn.nukkit.block.BlockID;
import org.cloudburstmc.protocol.bedrock.data.inventory.ItemData;
import org.cloudburstmc.protocol.bedrock.packet.AddItemEntityPacket;
import org.cloudburstmc.protocol.bedrock.packet.MobEquipmentPacket;
import xyz.syodo.handler.PacketHandler;
import xyz.syodo.manager.ProtocolPlayer;
import xyz.syodo.registries.Registries;
import xyz.syodo.utils.ProtocolVersion;

public class AddItemEntityHandler extends PacketHandler<AddItemEntityPacket> {

    @Override
    public void handle(ProtocolPlayer player, AddItemEntityPacket packet) {
        ProtocolVersion version = player.getVersion();
        ItemData body = packet.getItemInHand();
        if(!body.getDefinition().getIdentifier().equals(BlockID.AIR)) {
            ItemData downgraded = Registries.ITEM.downgrade(version, body);
            packet.setItemInHand(downgraded);
        }
        SetEntityDataHandler.fixEntityFlags(version, packet.getMetadata());
    }

}
