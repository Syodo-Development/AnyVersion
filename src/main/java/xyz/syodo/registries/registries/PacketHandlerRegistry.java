package xyz.syodo.registries.registries;

import it.unimi.dsi.fastutil.objects.ObjectArraySet;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacket;
import xyz.syodo.handler.PacketHandler;
import xyz.syodo.handler.handlers.*;
import xyz.syodo.utils.ProtocolVersion;

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
    }

    public void handlePacket(ProtocolVersion version, BedrockPacket packet) {
        HANDLERS.stream().filter(f -> f.getType() == packet.getClass()).forEach(packetHandler -> packetHandler.handle(version, packet));
    }
}
