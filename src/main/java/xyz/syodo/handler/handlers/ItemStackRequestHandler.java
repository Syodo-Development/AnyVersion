package xyz.syodo.handler.handlers;

import cn.nukkit.Player;
import cn.nukkit.inventory.Inventory;
import lombok.SneakyThrows;
import org.cloudburstmc.protocol.bedrock.data.inventory.ContainerSlotType;
import org.cloudburstmc.protocol.bedrock.data.inventory.FullContainerName;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.ItemStackRequest;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.ItemStackRequestSlotData;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action.DestroyAction;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action.ItemStackRequestAction;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action.TransferItemStackRequestAction;
import org.cloudburstmc.protocol.bedrock.packet.ItemStackRequestPacket;
import xyz.syodo.handler.PacketHandler;
import xyz.syodo.manager.ProtocolPlayer;
import xyz.syodo.utils.ProtocolVersion;

import java.lang.reflect.Field;

public class ItemStackRequestHandler extends PacketHandler<ItemStackRequestPacket> {
    @SneakyThrows
    @Override
    public void handle(ProtocolVersion version, ItemStackRequestPacket packet) {
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
                }
            }
        }
    }
}
