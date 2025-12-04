package xyz.syodo.handler.handlers;

import cn.nukkit.block.BlockID;
import org.cloudburstmc.protocol.bedrock.data.inventory.ItemData;
import org.cloudburstmc.protocol.bedrock.packet.AddPlayerPacket;
import org.cloudburstmc.protocol.bedrock.packet.MobEquipmentPacket;
import xyz.syodo.handler.PacketHandler;
import xyz.syodo.manager.ProtocolPlayer;
import xyz.syodo.registries.Registries;
import xyz.syodo.utils.ProtocolVersion;

public class AddPlayerHandler extends PacketHandler<AddPlayerPacket> {

    @Override
    public void handle(ProtocolPlayer player, AddPlayerPacket packet) {
        ItemData body = packet.getHand();
        ProtocolVersion version = player.getVersion();
        SetEntityDataHandler.fixEntityFlags(version, packet.getMetadata());
        if(!body.getDefinition().getIdentifier().equals(BlockID.AIR)) {
            ItemData downgraded = Registries.ITEM.downgrade(version, body);
            packet.setHand(downgraded);
        }
    }

}
