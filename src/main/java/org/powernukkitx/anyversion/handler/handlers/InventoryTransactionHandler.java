package org.powernukkitx.anyversion.handler.handlers;

import cn.nukkit.Player;
import cn.nukkit.block.Block;
import cn.nukkit.block.BlockAir;
import cn.nukkit.block.BlockState;
import cn.nukkit.item.Item;
import cn.nukkit.level.Level;
import cn.nukkit.math.BlockFace;
import cn.nukkit.nbt.NBTIO;
import cn.nukkit.nbt.tag.CompoundTag;
import org.cloudburstmc.math.vector.Vector3i;
import org.cloudburstmc.nbt.*;
import org.cloudburstmc.protocol.bedrock.data.definitions.SimpleBlockDefinition;
import org.cloudburstmc.protocol.bedrock.data.definitions.SimpleItemDefinition;
import org.cloudburstmc.protocol.bedrock.data.inventory.ItemData;
import org.cloudburstmc.protocol.bedrock.data.inventory.transaction.ItemUseTransaction;
import org.cloudburstmc.protocol.bedrock.packet.InventoryTransactionPacket;
import org.powernukkitx.anyversion.handler.PacketHandler;
import org.powernukkitx.anyversion.manager.ProtocolPlayer;
import org.powernukkitx.anyversion.registries.Registries;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.nio.ByteOrder;

import static cn.nukkit.network.protocol.InventoryTransactionPacket.USE_ITEM_ACTION_CLICK_BLOCK;
import static org.cloudburstmc.protocol.bedrock.data.inventory.transaction.InventoryTransactionType.ITEM_USE;

public class InventoryTransactionHandler extends PacketHandler<InventoryTransactionPacket> {

    @Override
    public void handle(ProtocolPlayer player, InventoryTransactionPacket packet) {

        Player p = player.player().getPlayer();

        if(packet.getBlockDefinition() == null) {
            BlockState AIR = BlockAir.STATE;
            packet.setBlockDefinition(new SimpleBlockDefinition(AIR.getIdentifier(), AIR.blockStateHash(), NbtMap.EMPTY));
        }
        if(packet.getTriggerType() == null) {
            packet.setTriggerType(ItemUseTransaction.TriggerType.PLAYER_INPUT);
        }

        Item iHand = p.getInventory().getItemInHand();
        ItemData.Builder builder = ItemData.builder();
        builder.definition(new SimpleItemDefinition(iHand.getId(), iHand.getRuntimeId(), false));
        if(iHand.hasCompoundTag()) {
            builder.tag(deepcopy(iHand.getOrCreateNamedTag()));
        }
        if(iHand.isBlock()) {
            Block block = iHand.getBlockUnsafe();
            builder.blockDefinition(new SimpleBlockDefinition(block.getId(), block.getRuntimeId(), NbtMap.EMPTY));
        }
        ItemData serverHand = builder.build();
        ItemData data = packet.getItemInHand();
        if(Registries.ITEM.downgrade(player.getVersion(), serverHand).getDefinition().getRuntimeId() == data.getDefinition().getRuntimeId()) {
            packet.setItemInHand(serverHand);
        }

        if(packet.getClientInteractPrediction() == null) {
            if(packet.getTransactionType() == ITEM_USE) {
                if(packet.getActionType() == USE_ITEM_ACTION_CLICK_BLOCK) {
                    if (p != null) {
                        try {
                            Method method = Level.class.getDeclaredMethod("beforePlaceBlock", Item.class, BlockFace.class, Block.class, Block.class);
                            method.setAccessible(true);
                            BlockFace blockFace = BlockFace.fromIndex(packet.getBlockFace());
                            Vector3i targetLoc = packet.getBlockPosition();
                            if(targetLoc != null) {
                                Block target = p.getLevel().getBlock(targetLoc.getX(), targetLoc.getY(), targetLoc.getZ());
                                Block hand = (Block) method.invoke(p.getLevel(), p.getInventory().getItemInHand(), blockFace, target.getSide(blockFace), target);
                                method.setAccessible(false);
                                if(hand != null) {
                                    var diff = p.getNextPosition().subtract(p.getPosition());
                                    var aabb = p.getBoundingBox().getOffsetBoundingBox(diff.x, diff.y, diff.z);
                                    if(aabb != null && hand.getBoundingBox() != null) {
                                        if (aabb.intersectsWith(hand.getBoundingBox())) {
                                            if(aabb.intersection(hand.getBoundingBox()).getVolume() > 0.005f) {
                                                packet.setClientInteractPrediction(ItemUseTransaction.PredictedResult.FAILURE);
                                                return;
                                            }
                                        }
                                    }
                                }
                            }
                        } catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                }
            }
            packet.setClientInteractPrediction(ItemUseTransaction.PredictedResult.SUCCESS);
        }
    }

    public static NbtMap deepcopy(CompoundTag tag) {
        try {
            byte[] bytes = NBTIO.write(tag, ByteOrder.LITTLE_ENDIAN);
            try(InputStream stream = new ByteArrayInputStream(bytes);
                NBTInputStream v = NbtUtils.createReaderLE(stream)) {
                return (NbtMap) v.readTag(Integer.MAX_VALUE);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}