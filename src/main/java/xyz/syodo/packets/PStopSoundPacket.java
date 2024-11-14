package xyz.syodo.packets;

import cn.nukkit.network.connection.util.HandleByteBuf;
import cn.nukkit.network.protocol.InventorySlotPacket;
import cn.nukkit.network.protocol.StopSoundPacket;
import cn.nukkit.network.protocol.types.inventory.FullContainerName;
import cn.nukkit.network.protocol.types.itemstack.ContainerSlotType;
import xyz.syodo.manager.ProtocolPlayer;
import xyz.syodo.utils.ProtocolVersion;

public class PStopSoundPacket extends StopSoundPacket implements ProtocolizedPacket {

    protected ProtocolPlayer protocolPlayer;

    public void encode(HandleByteBuf byteBuf) {
        byteBuf.writeString(this.name);
        byteBuf.writeBoolean(this.stopAll);
        if(protocolPlayer.protocol() >= ProtocolVersion.MINECRAFT_PE_1_21_20.protocol()) {
            byteBuf.writeBoolean(this.stopMusicLegacy);
        }
    }

    @Override
    public void setPlayer(ProtocolPlayer player) {
        this.protocolPlayer = player;
    }
}
