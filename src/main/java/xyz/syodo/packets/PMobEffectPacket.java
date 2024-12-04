package xyz.syodo.packets;

import cn.nukkit.network.connection.util.HandleByteBuf;
import cn.nukkit.network.protocol.MobEffectPacket;
import xyz.syodo.manager.ProtocolPlayer;
import xyz.syodo.utils.ProtocolVersion;

public class PMobEffectPacket extends MobEffectPacket implements ProtocolizedPacket {

    protected ProtocolPlayer protocolPlayer;

    @Override
    public void encode(HandleByteBuf byteBuf) {
        byteBuf.writeEntityRuntimeId(this.eid);
        byteBuf.writeByte((byte)this.eventId);
        byteBuf.writeVarInt(this.effectId);
        byteBuf.writeVarInt(this.amplifier);
        byteBuf.writeBoolean(this.particles);
        byteBuf.writeVarInt(this.duration);
        if (protocolPlayer.protocol() >= ProtocolVersion.MINECRAFT_PE_1_21_40.protocol()) {
            byteBuf.writeUnsignedVarLong(this.tick);
        } else byteBuf.writeLongLE(this.tick);
    }
    @Override
    public void setPlayer(ProtocolPlayer player) {
        this.protocolPlayer = player;
    }
}
