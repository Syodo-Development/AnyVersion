package xyz.syodo.packets;

import cn.nukkit.item.Item;
import cn.nukkit.network.connection.util.HandleByteBuf;
import cn.nukkit.network.protocol.DisconnectPacket;
import cn.nukkit.network.protocol.MobArmorEquipmentPacket;
import xyz.syodo.manager.ProtocolPlayer;
import xyz.syodo.utils.ProtocolVersion;

public class PMobArmorEquipmentPacket extends MobArmorEquipmentPacket implements ProtocolizedPacket {

    protected ProtocolPlayer protocolPlayer;

    public void decode(HandleByteBuf byteBuf) {
        this.eid = byteBuf.readEntityRuntimeId();
        this.slots = new Item[4];
        this.slots[0] = byteBuf.readSlot();
        this.slots[1] = byteBuf.readSlot();
        this.slots[2] = byteBuf.readSlot();
        this.slots[3] = byteBuf.readSlot();
        if(protocolPlayer.protocol() >= ProtocolVersion.MINECRAFT_PE_1_21_20.protocol()) {
            this.body = byteBuf.readSlot();
        }
    }

    public void encode(HandleByteBuf byteBuf) {
        byteBuf.writeEntityRuntimeId(this.eid);
        byteBuf.writeSlot(this.slots[0]);
        byteBuf.writeSlot(this.slots[1]);
        byteBuf.writeSlot(this.slots[2]);
        byteBuf.writeSlot(this.slots[3]);
        if(protocolPlayer.protocol() >= ProtocolVersion.MINECRAFT_PE_1_21_20.protocol()) {
            byteBuf.writeSlot(this.body);
        }
    }

    @Override
    public void setPlayer(ProtocolPlayer player) {
        this.protocolPlayer = player;
    }
}
