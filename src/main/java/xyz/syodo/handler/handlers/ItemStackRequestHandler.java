package xyz.syodo.handler.handlers;

import lombok.SneakyThrows;
import org.cloudburstmc.protocol.bedrock.data.inventory.FullContainerName;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.ItemStackRequest;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.ItemStackRequestSlotData;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action.*;
import org.cloudburstmc.protocol.bedrock.packet.ItemStackRequestPacket;
import xyz.syodo.handler.PacketHandler;
import xyz.syodo.manager.ProtocolPlayer;
import xyz.syodo.utils.ProtocolVersion;

import java.lang.reflect.Field;

public class ItemStackRequestHandler extends PacketHandler<ItemStackRequestPacket> {
    @SneakyThrows
    @Override
    public void handle(ProtocolPlayer player, ItemStackRequestPacket packet) {
        for(ItemStackRequest request : packet.getRequests()) {
            for(ItemStackRequestAction action : request.getActions()) {
                if(action instanceof TransferItemStackRequestAction requestAction) {
                    ItemStackRequestSlotData source = requestAction.getSource();
                    ItemStackRequestSlotData destination = requestAction.getDestination();
                    if(source.getContainerName() == null) {
                        Field field = source.getClass().getDeclaredField("containerName");
                        field.setAccessible(true);
                        field.set(source, new FullContainerName(source.getContainer(), 0));
                        field.setAccessible(false);
                    }
                    if(destination.getContainerName() == null) {
                        Field field = destination.getClass().getDeclaredField("containerName");
                        field.setAccessible(true);
                        field.set(destination, new FullContainerName(destination.getContainer(), 0));
                        field.setAccessible(false);
                    }
                } else if(action instanceof DestroyAction destroyAction) {
                   ItemStackRequestSlotData source = destroyAction.getSource();
                    if(source.getContainerName() == null) {
                        Field field = source.getClass().getDeclaredField("containerName");
                        field.setAccessible(true);
                        field.set(source, new FullContainerName(source.getContainer(), 0));
                        field.setAccessible(false);
                    }
                } else if(action instanceof DropAction dropAction) {
                    ItemStackRequestSlotData source = dropAction.getSource();
                    if(source.getContainerName() == null) {
                        Field field = source.getClass().getDeclaredField("containerName");
                        field.setAccessible(true);
                        field.set(source, new FullContainerName(source.getContainer(), 0));
                        field.setAccessible(false);
                    }
                } else if(action instanceof ConsumeAction consumeAction) {
                    ItemStackRequestSlotData source = consumeAction.getSource();
                    if(source.getContainerName() == null) {
                        Field field = source.getClass().getDeclaredField("containerName");
                        field.setAccessible(true);
                        field.set(source, new FullContainerName(source.getContainer(), 0));
                        field.setAccessible(false);
                    }
                } else if(action instanceof CraftRecipeAction craftRecipeAction) {
                    if(player.protocol() < ProtocolVersion.MINECRAFT_PE_1_21_20.protocol()) {
                        Field field = CraftRecipeAction.class.getDeclaredField("numberOfRequestedCrafts");
                        field.setAccessible(true);
                        field.set(craftRecipeAction, 1);
                        field.setAccessible(false);
                    }
                } else if(action instanceof SwapAction swapAction) {
                    ItemStackRequestSlotData source = swapAction.getSource();
                    ItemStackRequestSlotData destination = swapAction.getDestination();
                    if(source.getContainerName() == null) {
                        Field field = source.getClass().getDeclaredField("containerName");
                        field.setAccessible(true);
                        field.set(source, new FullContainerName(source.getContainer(), 0));
                        field.setAccessible(false);
                    }
                    if(destination.getContainerName() == null) {
                        Field field = destination.getClass().getDeclaredField("containerName");
                        field.setAccessible(true);
                        field.set(destination, new FullContainerName(destination.getContainer(), 0));
                        field.setAccessible(false);
                    }
                }
            }
        }
    }
}
