package xyz.syodo.handler.handlers;

import cn.nukkit.block.BlockID;
import org.cloudburstmc.protocol.bedrock.data.inventory.ItemData;
import org.cloudburstmc.protocol.bedrock.packet.MobArmorEquipmentPacket;
import org.cloudburstmc.protocol.bedrock.packet.MobEquipmentPacket;
import xyz.syodo.handler.PacketHandler;
import xyz.syodo.manager.ProtocolPlayer;
import xyz.syodo.registries.Registries;

public class MobEquipmentHandler extends PacketHandler<MobEquipmentPacket> {

    @Override
    public void handle(ProtocolPlayer player, MobEquipmentPacket packet) {
        ItemData body = packet.getItem();
        if(body.getDefinition() == null) return; //Packet inbound
        if(!body.getDefinition().getIdentifier().equals(BlockID.AIR)) {
            ItemData downgraded = Registries.ITEM.downgrade(player.getVersion(), body);
            packet.setItem(downgraded);
        }
    }

}
