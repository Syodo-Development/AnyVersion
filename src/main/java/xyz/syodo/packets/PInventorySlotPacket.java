package xyz.syodo.packets;

import cn.nukkit.network.connection.util.HandleByteBuf;
import cn.nukkit.network.protocol.InventorySlotPacket;
import cn.nukkit.network.protocol.types.inventory.FullContainerName;
import cn.nukkit.network.protocol.types.itemstack.ContainerSlotType;
import xyz.syodo.manager.ProtocolPlayer;
import xyz.syodo.utils.ProtocolVersion;

public class PInventorySlotPacket extends InventorySlotPacket implements ProtocolizedPacket {

    protected ProtocolPlayer protocolPlayer;

    public int dynamicContainerSize;

    public PInventorySlotPacket() {
        super();
        this.fullContainerName = new FullContainerName(ContainerSlotType.ANVIL_INPUT, null);
    }

    public void decode(HandleByteBuf byteBuf) {
        this.inventoryId = byteBuf.readUnsignedVarInt();
        this.slot = byteBuf.readUnsignedVarInt();
        if (protocolPlayer.protocol() >= ProtocolVersion.MINECRAFT_PE_1_21_30.protocol()) {
            this.fullContainerName = byteBuf.readFullContainerName();
            if (protocolPlayer.protocol() >= ProtocolVersion.MINECRAFT_PE_1_21_40.protocol()) {
                this.storageItem = byteBuf.readSlot();
            } else {
                this.dynamicContainerSize = byteBuf.readUnsignedVarInt();
            }
        } else this.fullContainerName = new FullContainerName(ContainerSlotType.ANVIL_INPUT, byteBuf.readUnsignedVarInt());
        this.item = byteBuf.readSlot();
    }

    public void encode(HandleByteBuf byteBuf) {
        byteBuf.writeUnsignedVarInt(this.inventoryId);
        byteBuf.writeUnsignedVarInt(this.slot);
        if (protocolPlayer.protocol() >= ProtocolVersion.MINECRAFT_PE_1_21_30.protocol()) {
            byteBuf.writeFullContainerName(this.fullContainerName);
            if (protocolPlayer.protocol() >= ProtocolVersion.MINECRAFT_PE_1_21_40.protocol()) {
                byteBuf.writeSlot(this.storageItem);
            } else {
                byteBuf.writeUnsignedVarInt(this.dynamicContainerSize);
            }
        } else byteBuf.writeUnsignedVarInt(this.fullContainerName == null || this.fullContainerName.getDynamicId() == null ? 0 : this.fullContainerName.getDynamicId());
        byteBuf.writeSlot(this.item);
    }

    @Override
    public void setPlayer(ProtocolPlayer player) {
        this.protocolPlayer = player;
    }
}
