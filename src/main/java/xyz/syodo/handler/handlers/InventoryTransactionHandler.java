package xyz.syodo.handler.handlers;

import cn.nukkit.block.BlockAir;
import cn.nukkit.block.BlockState;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.protocol.bedrock.data.definitions.SimpleBlockDefinition;
import org.cloudburstmc.protocol.bedrock.data.inventory.transaction.ItemUseTransaction;
import org.cloudburstmc.protocol.bedrock.packet.InventoryTransactionPacket;
import xyz.syodo.handler.PacketHandler;
import xyz.syodo.manager.ProtocolPlayer;

public class InventoryTransactionHandler extends PacketHandler<InventoryTransactionPacket> {

    @Override
    public void handle(ProtocolPlayer player, InventoryTransactionPacket packet) {
        if(packet.getBlockDefinition() == null) {
            BlockState AIR = BlockAir.STATE;
            packet.setBlockDefinition(new SimpleBlockDefinition(AIR.getIdentifier(), AIR.blockStateHash(), NbtMap.EMPTY));
        }
        if(packet.getTriggerType() == null) {
            packet.setTriggerType(ItemUseTransaction.TriggerType.UNKNOWN);
        }
        if(packet.getClientInteractPrediction() == null) {
            packet.setClientInteractPrediction(ItemUseTransaction.PredictedResult.FAILURE);
        }
    }

}
