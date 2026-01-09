package org.powernukkitx.anyversion.handler.handlers;

import cn.nukkit.block.BlockID;
import org.cloudburstmc.protocol.bedrock.data.inventory.ItemData;
import org.cloudburstmc.protocol.bedrock.packet.MobArmorEquipmentPacket;
import org.powernukkitx.anyversion.handler.PacketHandler;
import org.powernukkitx.anyversion.manager.ProtocolPlayer;
import org.powernukkitx.anyversion.registries.Registries;

public class MobArmorEquipmentHandler extends PacketHandler<MobArmorEquipmentPacket> {

    @Override
    public void handle(ProtocolPlayer player, MobArmorEquipmentPacket packet) {
        ItemData body = packet.getBody();
        if(!body.getDefinition().getIdentifier().equals(BlockID.AIR)) {
            ItemData downgraded = Registries.ITEM.downgrade(player.getVersion(), body);
            packet.setBody(downgraded);
        }
        ItemData boots = packet.getBoots();
        if(!boots.getDefinition().getIdentifier().equals(BlockID.AIR)) {
            ItemData downgraded = Registries.ITEM.downgrade(player.getVersion(), boots);
            packet.setBoots(downgraded);
        }
        ItemData leggings = packet.getLeggings();
        if(!leggings.getDefinition().getIdentifier().equals(BlockID.AIR)) {
            ItemData downgraded = Registries.ITEM.downgrade(player.getVersion(), leggings);
            packet.setLeggings(downgraded);
        }
        ItemData chestplate = packet.getBody();
        if(!chestplate.getDefinition().getIdentifier().equals(BlockID.AIR)) {
            ItemData downgraded = Registries.ITEM.downgrade(player.getVersion(), chestplate);
            packet.setChestplate(downgraded);
        }
        ItemData helmet = packet.getBody();
        if(!helmet.getDefinition().getIdentifier().equals(BlockID.AIR)) {
            ItemData downgraded = Registries.ITEM.downgrade(player.getVersion(), helmet);
            packet.setHelmet(downgraded);
        }
    }

}
