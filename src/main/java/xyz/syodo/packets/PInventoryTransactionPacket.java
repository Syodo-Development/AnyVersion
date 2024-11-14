package xyz.syodo.packets;

import cn.nukkit.network.connection.util.HandleByteBuf;
import cn.nukkit.network.protocol.InventoryTransactionPacket;
import cn.nukkit.network.protocol.types.LegacySetItemSlotData;
import cn.nukkit.network.protocol.types.inventory.transaction.NetworkInventoryAction;
import cn.nukkit.network.protocol.types.inventory.transaction.ReleaseItemData;
import cn.nukkit.network.protocol.types.inventory.transaction.UseItemData;
import cn.nukkit.network.protocol.types.inventory.transaction.UseItemOnEntityData;
import xyz.syodo.manager.ProtocolPlayer;
import xyz.syodo.utils.ProtocolVersion;

import java.util.ArrayDeque;
import java.util.Collection;

public class PInventoryTransactionPacket extends InventoryTransactionPacket implements ProtocolizedPacket {

    protected ProtocolPlayer protocolPlayer;

    @Override
    public void encode(HandleByteBuf byteBuf) {
        byteBuf.clear();
        byteBuf.writeVarInt(this.legacyRequestId);
        byteBuf.writeUnsignedVarInt(this.transactionType);

        if (legacyRequestId != 0) {
            byteBuf.writeUnsignedVarInt(this.legacySlots.size());
            for (var slot : legacySlots) {
                byteBuf.writeByte((byte) slot.getContainerId());
                byteBuf.writeByteArray(slot.getSlots());
            }
        }

        byteBuf.writeUnsignedVarInt(this.actions.length);
        for (NetworkInventoryAction action : this.actions) {
            action.write(byteBuf);
        }

        switch (this.transactionType) {
            case TYPE_NORMAL:
            case TYPE_MISMATCH:
                break;
            case TYPE_USE_ITEM:
                UseItemData useItemData = (UseItemData) this.transactionData;

                byteBuf.writeUnsignedVarInt(useItemData.actionType);
                if (protocolPlayer.protocol() >= ProtocolVersion.MINECRAFT_PE_1_21_20.protocol()) {
                    byteBuf.writeUnsignedVarInt(useItemData.triggerType.ordinal());
                }
                byteBuf.writeBlockVector3(useItemData.blockPos);
                byteBuf.writeBlockFace(useItemData.face);
                byteBuf.writeVarInt(useItemData.hotbarSlot);
                byteBuf.writeSlot(useItemData.itemInHand);
                byteBuf.writeVector3f(useItemData.playerPos.asVector3f());
                byteBuf.writeVector3f(useItemData.clickPos);

                byteBuf.writeUnsignedVarInt(useItemData.blockRuntimeId);
                if (protocolPlayer.protocol() >= ProtocolVersion.MINECRAFT_PE_1_21_20.protocol()) {
                    byteBuf.writeUnsignedVarInt(useItemData.clientInteractPrediction.ordinal());
                }

                break;
            case TYPE_USE_ITEM_ON_ENTITY:
                UseItemOnEntityData useItemOnEntityData = (UseItemOnEntityData) this.transactionData;

                byteBuf.writeEntityRuntimeId(useItemOnEntityData.entityRuntimeId);
                byteBuf.writeUnsignedVarInt(useItemOnEntityData.actionType);
                byteBuf.writeVarInt(useItemOnEntityData.hotbarSlot);
                byteBuf.writeSlot(useItemOnEntityData.itemInHand);
                byteBuf.writeVector3f(useItemOnEntityData.playerPos.asVector3f());
                byteBuf.writeVector3f(useItemOnEntityData.clickPos.asVector3f());
                break;
            case TYPE_RELEASE_ITEM:
                ReleaseItemData releaseItemData = (ReleaseItemData) this.transactionData;

                byteBuf.writeUnsignedVarInt(releaseItemData.actionType);
                byteBuf.writeVarInt(releaseItemData.hotbarSlot);
                byteBuf.writeSlot(releaseItemData.itemInHand);
                byteBuf.writeVector3f(releaseItemData.headRot.asVector3f());
                break;
            default:
                throw new RuntimeException("Unknown transaction type " + this.transactionType);
        }
    }

    @Override
    public void decode(HandleByteBuf byteBuf) {

        this.legacyRequestId = byteBuf.readVarInt();
        if (legacyRequestId != 0) {
            int length = byteBuf.readUnsignedVarInt();
            for (int i = 0; i < length; i++) {
                byte containerId = byteBuf.readByte();
                byte[] slots = byteBuf.readByteArray();
                this.legacySlots.add(new LegacySetItemSlotData(containerId, slots));
            }
        }


        this.transactionType = byteBuf.readUnsignedVarInt();

        int length = byteBuf.readUnsignedVarInt();
        Collection<NetworkInventoryAction> actions = new ArrayDeque<>();
        for (int i = 0; i < length; i++) {
            actions.add(new NetworkInventoryAction().read(this, byteBuf));
        }

        switch (this.transactionType) {
            case TYPE_NORMAL:
            case TYPE_MISMATCH:
                //Regular ComplexInventoryTransaction doesn't read any extra data
                break;
            case TYPE_USE_ITEM:
                UseItemData itemData = new UseItemData();

                itemData.actionType = byteBuf.readUnsignedVarInt();
                if (protocolPlayer.protocol() >= ProtocolVersion.MINECRAFT_PE_1_21_20.protocol()) {
                    itemData.triggerType = UseItemData.TriggerType.values()[byteBuf.readUnsignedVarInt()];
                }
                itemData.blockPos = byteBuf.readBlockVector3();
                itemData.face = byteBuf.readBlockFace();
                itemData.hotbarSlot = byteBuf.readVarInt();
                itemData.itemInHand = byteBuf.readSlot();
                itemData.playerPos = byteBuf.readVector3f().asVector3();
                itemData.clickPos = byteBuf.readVector3f();

                    itemData.blockRuntimeId = byteBuf.readUnsignedVarInt();
                    if (protocolPlayer.protocol() >= ProtocolVersion.MINECRAFT_PE_1_21_20.protocol()) {
                        itemData.clientInteractPrediction = UseItemData.PredictedResult.values()[byteBuf.readUnsignedVarInt()];
                    }


                this.transactionData = itemData;
                break;
            case TYPE_USE_ITEM_ON_ENTITY:
                UseItemOnEntityData useItemOnEntityData = new UseItemOnEntityData();

                useItemOnEntityData.entityRuntimeId = byteBuf.readEntityRuntimeId();
                useItemOnEntityData.actionType = byteBuf.readUnsignedVarInt();
                useItemOnEntityData.hotbarSlot = byteBuf.readVarInt();
                useItemOnEntityData.itemInHand = byteBuf.readSlot();
                useItemOnEntityData.playerPos = byteBuf.readVector3f().asVector3();
                useItemOnEntityData.clickPos = byteBuf.readVector3f().asVector3();

                this.transactionData = useItemOnEntityData;
                break;
            case TYPE_RELEASE_ITEM:
                ReleaseItemData releaseItemData = new ReleaseItemData();

                releaseItemData.actionType = byteBuf.readUnsignedVarInt();
                releaseItemData.hotbarSlot = byteBuf.readVarInt();
                releaseItemData.itemInHand = byteBuf.readSlot();
                releaseItemData.headRot = byteBuf.readVector3f().asVector3();

                this.transactionData = releaseItemData;
                break;
            default:
                throw new RuntimeException("Unknown transaction type " + this.transactionType);
        }
    }

    @Override
    public void setPlayer(ProtocolPlayer player) {
        this.protocolPlayer = player;
    }
}
