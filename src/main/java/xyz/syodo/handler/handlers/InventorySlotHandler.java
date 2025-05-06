package xyz.syodo.handler.handlers;

import cn.nukkit.block.BlockID;
import org.cloudburstmc.protocol.bedrock.data.inventory.ItemData;
import org.cloudburstmc.protocol.bedrock.packet.InventorySlotPacket;
import xyz.syodo.handler.PacketHandler;
import xyz.syodo.manager.ProtocolPlayer;
import xyz.syodo.registries.Registries;

public class InventorySlotHandler extends PacketHandler<InventorySlotPacket> {

    @Override
    public void handle(ProtocolPlayer player, InventorySlotPacket packet) {
        ItemData data = packet.getItem();
        if(data.getDefinition().getIdentifier().equals(BlockID.AIR)) return;
        ItemData downgraded = Registries.ITEM.downgrade(player.getVersion(), data);
        packet.setItem(downgraded);
    }

}
