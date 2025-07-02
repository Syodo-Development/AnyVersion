package xyz.syodo.handler.handlers;

import lombok.extern.slf4j.Slf4j;
import org.cloudburstmc.protocol.bedrock.data.auth.AuthType;
import org.cloudburstmc.protocol.bedrock.data.auth.CertificateChainPayload;
import org.cloudburstmc.protocol.bedrock.packet.LoginPacket;
import xyz.syodo.handler.PacketHandler;
import xyz.syodo.manager.ProtocolPlayer;
import xyz.syodo.utils.ProtocolVersion;

@Slf4j
public class LoginHandler extends PacketHandler<LoginPacket> {

    @Override
    public void handle(ProtocolPlayer player, LoginPacket packet) {
        if(packet.getProtocolVersion() < ProtocolVersion.MINECRAFT_PE_1_21_90.protocol()) {
            CertificateChainPayload certificateChainPayload = (CertificateChainPayload) packet.getAuthPayload();
            packet.setAuthPayload(new CertificateChainPayload(certificateChainPayload.getChain(), AuthType.SELF_SIGNED));
        }
    }

}
