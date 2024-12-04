package xyz.syodo.packets;

import cn.nukkit.math.Vector2;
import cn.nukkit.math.Vector2f;
import cn.nukkit.math.Vector3;
import cn.nukkit.math.Vector3f;
import cn.nukkit.network.connection.util.HandleByteBuf;
import cn.nukkit.network.protocol.PlayerAuthInputPacket;
import cn.nukkit.network.protocol.types.*;
import cn.nukkit.network.protocol.types.itemstack.request.ItemStackRequest;
import xyz.syodo.manager.ProtocolPlayer;
import xyz.syodo.utils.ProtocolVersion;

import java.util.Set;


public class PPlayerAuthInputPacket extends PlayerAuthInputPacket implements ProtocolizedPacket {

    protected ProtocolPlayer protocolPlayer;

    public Vector3f vrGazeDirection;

    @Override
    public void decode(HandleByteBuf byteBuf) {
        this.pitch = byteBuf.readFloatLE();
        this.yaw = byteBuf.readFloatLE();
        this.position = byteBuf.readVector3f();
        this.motion = new Vector2((double)byteBuf.readFloatLE(), (double)byteBuf.readFloatLE());
        this.headYaw = byteBuf.readFloatLE();
        long inputData = byteBuf.readUnsignedVarLong();
        for (int i = 0; i < AuthInputAction.size(); i++) {
            if ((inputData & (1L << i)) != 0) {
                this.inputData.add(AuthInputAction.from(i));
            }
        }

        this.inputMode = InputMode.fromOrdinal(byteBuf.readUnsignedVarInt());
        this.playMode = ClientPlayMode.fromOrdinal(byteBuf.readUnsignedVarInt());
        this.interactionModel = AuthInteractionModel.fromOrdinal(byteBuf.readUnsignedVarInt());

        if (protocolPlayer.protocol() >= ProtocolVersion.MINECRAFT_PE_1_21_40.protocol()) {
            this.interactRotation = byteBuf.readVector2f();
        } else {
            this.interactRotation = new Vector2f();
            if (this.playMode == ClientPlayMode.REALITY) {
                this.vrGazeDirection = byteBuf.readVector3f();
            }
        }

        this.tick = new PlayerInputTick(byteBuf.readUnsignedVarLong());
        this.delta = byteBuf.readVector3f();

        if (this.inputData.contains(AuthInputAction.PERFORM_ITEM_STACK_REQUEST)) {
            this.itemStackRequest = byteBuf.readItemStackRequest();
        }

        if (this.inputData.contains(AuthInputAction.PERFORM_BLOCK_ACTIONS)) {
            int arraySize = byteBuf.readVarInt();
            if (arraySize > 256) {
                throw new IllegalArgumentException("PlayerAuthInputPacket PERFORM_BLOCK_ACTIONS is too long: " + arraySize);
            }
            for (int i = 0; i < arraySize; i++) {
                PlayerActionType type = PlayerActionType.from(byteBuf.readVarInt());
                switch (type) {
                    case START_DESTROY_BLOCK:
                    case ABORT_DESTROY_BLOCK:
                    case CRACK_BLOCK:
                    case PREDICT_DESTROY_BLOCK:
                    case CONTINUE_DESTROY_BLOCK:
                        this.blockActionData.put(type, new PlayerBlockActionData(type, byteBuf.readSignedBlockPosition(), byteBuf.readVarInt()));
                        break;
                    default:
                        this.blockActionData.put(type, new PlayerBlockActionData(type, null, -1));
                }
            }
        }

            if (this.inputData.contains(AuthInputAction.IN_CLIENT_PREDICTED_IN_VEHICLE)) {
                this.vehicleRotation = byteBuf.readVector2f();
                this.predictedVehicle = byteBuf.readVarLong();
            }

            this.analogMoveVector = byteBuf.readVector2f();

            if (protocolPlayer.protocol() >= ProtocolVersion.MINECRAFT_PE_1_21_40.protocol()) {
                this.cameraOrientation = byteBuf.readVector3f();
            } else this.cameraOrientation = new Vector3f();

    }

    @Override
    public void setPlayer(ProtocolPlayer player) {
        this.protocolPlayer = player;
    }
}
