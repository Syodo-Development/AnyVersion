package xyz.syodo.packets;

import cn.nukkit.network.connection.util.HandleByteBuf;
import cn.nukkit.network.protocol.ResourcePacksInfoPacket;
import cn.nukkit.resourcepacks.ResourcePack;
import xyz.syodo.manager.ProtocolPlayer;
import xyz.syodo.utils.ProtocolVersion;

public class PResourcePacksInfoPacket extends ResourcePacksInfoPacket implements ProtocolizedPacket {

    protected ProtocolPlayer protocolPlayer;

    @Override
    public void encode(HandleByteBuf byteBuf) {
        byteBuf.writeBoolean(this.mustAccept);
        byteBuf.writeBoolean(this.hasAddonPacks);
        byteBuf.writeBoolean(this.scripting);
        if(protocolPlayer.protocol() < ProtocolVersion.MINECRAFT_PE_1_21_30.protocol()) {
            byteBuf.writeBoolean(false);
            this.encodePacks(byteBuf, this.resourcePackEntries, true);
        }
        this.encodePacks(byteBuf, this.resourcePackEntries, false);
        if(protocolPlayer.protocol() < ProtocolVersion.MINECRAFT_PE_1_21_40.protocol()) {
            byteBuf.writeUnsignedVarInt(0);
        }
    }
    private void encodePacks(HandleByteBuf byteBuf, ResourcePack[] packs, boolean behaviour) {
        byteBuf.writeShortLE(packs.length);
        for (ResourcePack entry : packs) {
            byteBuf.writeString(entry.getPackId().toString());
            byteBuf.writeString(entry.getPackVersion());
            byteBuf.writeLongLE(entry.getPackSize());
            byteBuf.writeString(entry.getEncryptionKey()); // encryption key
            byteBuf.writeString(""); // sub-pack name
            byteBuf.writeString(!entry.getEncryptionKey().isEmpty() ? entry.getPackId().toString() : ""); // content identity
            byteBuf.writeBoolean(false); // scripting
            byteBuf.writeBoolean(false);    // isAddonPack
            byteBuf.writeString(entry.cdnUrl());    // cdnUrl
            if (!behaviour) {
                byteBuf.writeBoolean(false); // raytracing capable
            }
        }
    }

    @Override
    public void setPlayer(ProtocolPlayer player) {
        this.protocolPlayer = player;
    }
}
