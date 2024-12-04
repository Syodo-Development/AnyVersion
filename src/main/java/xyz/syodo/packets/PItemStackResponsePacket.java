package xyz.syodo.packets;

import cn.nukkit.network.connection.util.HandleByteBuf;
import cn.nukkit.network.protocol.CameraAimAssistPacket;
import cn.nukkit.network.protocol.ItemStackResponsePacket;
import cn.nukkit.network.protocol.types.itemstack.response.ItemStackResponseStatus;
import xyz.syodo.manager.ProtocolPlayer;
import xyz.syodo.utils.ProtocolVersion;

public class PItemStackResponsePacket extends ItemStackResponsePacket implements ProtocolizedPacket {

    protected ProtocolPlayer protocolPlayer;

    @Override
    public void encode(HandleByteBuf byteBuf) {
        byteBuf.writeArray(entries, (r) -> {
            byteBuf.writeByte((byte) r.getResult().ordinal());
            byteBuf.writeVarInt(r.getRequestId());
            if (r.getResult() != ItemStackResponseStatus.OK) return;
            byteBuf.writeArray(r.getContainers(), (container) -> {
                byteBuf.writeFullContainerName(container.getContainerName());
                byteBuf.writeArray(container.getItems(), (item) -> {
                    byteBuf.writeByte((byte) item.getSlot());
                    byteBuf.writeByte((byte) item.getHotbarSlot());
                    byteBuf.writeByte((byte) item.getCount());
                    byteBuf.writeVarInt(item.getStackNetworkId());
                    byteBuf.writeString(item.getCustomName());
                    if (protocolPlayer.protocol() >= ProtocolVersion.MINECRAFT_PE_1_21_50.protocol()) {
                        byteBuf.writeString(item.getFilteredCustomName());
                    }
                    byteBuf.writeVarInt(item.getDurabilityCorrection());
                });
            });
        });
    }

    @Override
    public void setPlayer(ProtocolPlayer player) {
        this.protocolPlayer = player;
    }
}
