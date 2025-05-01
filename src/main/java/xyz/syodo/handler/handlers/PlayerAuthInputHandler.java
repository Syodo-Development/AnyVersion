package xyz.syodo.handler.handlers;

import org.cloudburstmc.math.vector.Vector2f;
import org.cloudburstmc.math.vector.Vector3f;
import org.cloudburstmc.protocol.bedrock.packet.PlayerAuthInputPacket;
import xyz.syodo.handler.PacketHandler;
import xyz.syodo.manager.ProtocolPlayer;
import xyz.syodo.utils.ProtocolVersion;

public class PlayerAuthInputHandler extends PacketHandler<PlayerAuthInputPacket> {

    @Override
    public void handle(ProtocolPlayer player, PlayerAuthInputPacket packet) {
        if(packet.getInteractRotation() == null) {
            packet.setInteractRotation(Vector2f.ZERO);
        }
        if(packet.getVehicleRotation() == null) {
            packet.setVehicleRotation(Vector2f.ZERO);
        }
        if(packet.getCameraOrientation() == null) {
            packet.setCameraOrientation(Vector3f.ZERO);
        }
        if(packet.getRawMoveVector() == null) {
            packet.setRawMoveVector(Vector2f.ZERO);
        }
    }

}
