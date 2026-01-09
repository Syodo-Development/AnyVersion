package org.powernukkitx.anyversion.handler.handlers;

import org.cloudburstmc.math.vector.Vector2f;
import org.cloudburstmc.math.vector.Vector3f;
import org.cloudburstmc.protocol.bedrock.packet.PlayerAuthInputPacket;
import org.powernukkitx.anyversion.handler.PacketHandler;
import org.powernukkitx.anyversion.manager.ProtocolPlayer;

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
