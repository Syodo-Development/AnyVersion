package xyz.syodo.packets;

import cn.nukkit.item.Item;
import cn.nukkit.network.connection.util.HandleByteBuf;
import cn.nukkit.network.protocol.DisconnectPacket;
import cn.nukkit.network.protocol.InventoryContentPacket;
import cn.nukkit.network.protocol.types.DisconnectFailReason;
import cn.nukkit.network.protocol.types.inventory.FullContainerName;
import cn.nukkit.network.protocol.types.itemstack.ContainerSlotType;
import xyz.syodo.manager.ProtocolPlayer;
import xyz.syodo.utils.ProtocolVersion;

import java.lang.reflect.Field;

public class PInventoryContentPacket extends InventoryContentPacket implements ProtocolizedPacket {

    protected ProtocolPlayer protocolPlayer;

    public int dynamicContainerSize;

    public FullContainerName containerNameData;

    public PInventoryContentPacket() {
        super();
        this.containerNameData = new FullContainerName(ContainerSlotType.ANVIL_INPUT, null);
        this.fullContainerName = containerNameData;
    }

    @Override
    public void encode(HandleByteBuf byteBuf) {
        byteBuf.writeUnsignedVarInt(this.inventoryId);
        byteBuf.writeUnsignedVarInt(this.slots.length);
        for (Item slot : this.slots) {
            byteBuf.writeSlot(slot);
        }
        if (protocolPlayer.protocol() >= ProtocolVersion.MINECRAFT_PE_1_21_30.protocol()) {
            byteBuf.writeFullContainerName(this.fullContainerName);
            if ((protocolPlayer.protocol() >= ProtocolVersion.MINECRAFT_PE_1_21_40.protocol())) {
                byteBuf.writeSlot(this.storageItem);
            } else {
                byteBuf.writeUnsignedVarInt(this.dynamicContainerSize);
            }
        } else if (protocolPlayer.protocol() >= ProtocolVersion.MINECRAFT_PE_1_21_20.protocol()) {
            byteBuf.writeUnsignedVarInt(this.containerNameData == null || this.containerNameData.getDynamicId() == null ? 0 : this.containerNameData.getDynamicId());
        }
    }

    @Override
    public void setPlayer(ProtocolPlayer player) {
        this.protocolPlayer = player;
    }
}
