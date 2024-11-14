package xyz.syodo.packets;

import cn.nukkit.entity.Attribute;
import cn.nukkit.network.connection.util.HandleByteBuf;
import cn.nukkit.network.protocol.UpdateAttributesPacket;
import xyz.syodo.manager.ProtocolPlayer;
import xyz.syodo.utils.ProtocolVersion;

public class PUpdateAttributesPacket extends UpdateAttributesPacket implements ProtocolizedPacket {

    protected ProtocolPlayer protocolPlayer;

    @Override
    public void encode(HandleByteBuf byteBuf) {
        byteBuf.writeEntityRuntimeId(this.entityId);

        if (this.entries == null) {
            byteBuf.writeUnsignedVarInt(0);
        } else {
            byteBuf.writeUnsignedVarInt(this.entries.length);
            for (Attribute entry : this.entries) {
                byteBuf.writeFloatLE(entry.getMinValue());
                byteBuf.writeFloatLE(entry.getMaxValue());
                byteBuf.writeFloatLE(entry.getValue());
                if(protocolPlayer.protocol() >= ProtocolVersion.MINECRAFT_PE_1_21_30.protocol()) {
                    byteBuf.writeFloatLE(entry.getDefaultMinimum());
                    byteBuf.writeFloatLE(entry.getDefaultMaximum());
                }
                byteBuf.writeFloatLE(entry.getDefaultValue());
                byteBuf.writeString(entry.getName());
                byteBuf.writeUnsignedVarInt(0); // Modifiers
            }
        }
        byteBuf.writeUnsignedVarInt((int) this.frame);
    }

    @Override
    public void setPlayer(ProtocolPlayer player) {
        this.protocolPlayer = player;
    }
}
