package xyz.syodo.registries.registries;

import it.unimi.dsi.fastutil.objects.ObjectArraySet;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacket;
import xyz.syodo.handler.PacketHandler;
import xyz.syodo.handler.handlers.*;
import xyz.syodo.manager.ProtocolPlayer;
import xyz.syodo.utils.ProtocolVersion;

import java.util.stream.Stream;

public class PacketHandlerRegistry extends Registry {

    private final ObjectArraySet<PacketHandler> HANDLERS = new ObjectArraySet<>();

    @Override
    public void init() {
        HANDLERS.add(new InventoryTransactionHandler());
        HANDLERS.add(new PlayerAuthInputHandler());
        HANDLERS.add(new AvailableCommandsHandler());
        HANDLERS.add(new CreativeContentHandler());
        HANDLERS.add(new ItemStackRequestHandler());
        HANDLERS.add(new UpdateBlockHandler());
        HANDLERS.add(new LevelChunkHandler());
        HANDLERS.add(new LevelEventHandler());
        HANDLERS.add(new StartGameHandler());
        HANDLERS.add(new ItemRegistryHandler());
        HANDLERS.add(new ContainerCloseHandler());
        HANDLERS.add(new ModalFormRequestHandler());
        HANDLERS.add(new ModalFormResponseHandler());
        HANDLERS.add(new ServerSettingsResponseHandler());
        HANDLERS.add(new InventoryContentHandler());
        HANDLERS.add(new InventorySlotHandler());
        HANDLERS.add(new MobArmorEquipmentHandler());
        HANDLERS.add(new MobEquipmentHandler());
        HANDLERS.add(new AddItemEntityHandler());
        HANDLERS.add(new AddPlayerHandler());
        HANDLERS.add(new CraftingDataHandler());
        HANDLERS.add(new BiomeDefinitionListHandler());
        HANDLERS.add(new AddEntityHandler());
    }

    public boolean handlePacket(ProtocolPlayer player, BedrockPacket packet) {
        return HANDLERS.stream()
                .filter(handler -> handler.getType() == packet.getClass())
                .peek(handler -> handler.handle(player, packet))
                .findAny()
                .isPresent();
    }
}
