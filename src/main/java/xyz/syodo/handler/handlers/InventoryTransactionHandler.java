package xyz.syodo.handler.handlers;

import cn.nukkit.Player;
import cn.nukkit.block.Block;
import cn.nukkit.block.BlockAir;
import cn.nukkit.block.BlockState;
import cn.nukkit.item.Item;
import cn.nukkit.level.Level;
import cn.nukkit.math.BlockFace;
import cn.nukkit.nbt.tag.ByteTag;
import cn.nukkit.nbt.tag.CompoundTag;
import cn.nukkit.nbt.tag.Tag;
import lombok.Value;
import org.cloudburstmc.math.vector.Vector3i;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.nbt.NbtMapBuilder;
import org.cloudburstmc.protocol.bedrock.data.definitions.SimpleBlockDefinition;
import org.cloudburstmc.protocol.bedrock.data.definitions.SimpleItemDefinition;
import org.cloudburstmc.protocol.bedrock.data.inventory.ItemData;
import org.cloudburstmc.protocol.bedrock.data.inventory.transaction.ItemUseTransaction;
import org.cloudburstmc.protocol.bedrock.packet.InventoryTransactionPacket;
import xyz.syodo.handler.PacketHandler;
import xyz.syodo.manager.ProtocolPlayer;
import xyz.syodo.registries.Registries;

import java.lang.reflect.Method;

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

    private NbtMap deepcopy(CompoundTag tag) {
        NbtMapBuilder nbtMapBuilder = NbtMap.builder();
        for(var kv : tag.getTags().entrySet()) {
            String k = kv.getKey();
            Tag v = kv.getValue();
            if(v instanceof CompoundTag subTag) {
                nbtMapBuilder.put(k, deepcopy(subTag));
            } else if(v instanceof ByteTag subTag) {
                nbtMapBuilder.putByte(k, (byte) subTag.data);
            } else nbtMapBuilder.put(k, v.parseValue());
        }
        return nbtMapBuilder.build();
    }

}