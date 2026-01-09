package xyz.syodo.handler.handlers;

import cn.nukkit.Server;
import org.cloudburstmc.protocol.bedrock.packet.AddEntityPacket;
import org.cloudburstmc.protocol.bedrock.packet.TextPacket;
import xyz.syodo.handler.PacketHandler;
import xyz.syodo.manager.ProtocolPlayer;
import xyz.syodo.registries.Registries;
import xyz.syodo.utils.ProtocolVersion;

public class TextHandler extends PacketHandler<TextPacket> {

    @Override
    public void handle(ProtocolPlayer player, TextPacket packet) {
        ProtocolVersion version = player.getVersion();
        if(version.protocol() < ProtocolVersion.MINECRAFT_PE_1_21_130.protocol()) {
            if(packet.getType() == TextPacket.Type.TRANSLATION) {
                packet.setNeedsTranslation(false);
                packet.setType(TextPacket.Type.RAW);
                packet.setMessage(Server.getInstance().getLanguage().tr(packet.getMessage(), packet.getParameters().toArray()));
            }
        }
    }

}
