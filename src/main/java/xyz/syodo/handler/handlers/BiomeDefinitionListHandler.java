package xyz.syodo.handler.handlers;

import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.nbt.NbtUtils;
import org.cloudburstmc.protocol.bedrock.packet.BiomeDefinitionListPacket;
import xyz.syodo.AnyVersion;
import xyz.syodo.handler.PacketHandler;
import xyz.syodo.manager.ProtocolPlayer;

import java.io.IOException;

public class BiomeDefinitionListHandler extends PacketHandler<BiomeDefinitionListPacket> {

    @Override
    public void handle(ProtocolPlayer player, BiomeDefinitionListPacket packet) {
        try (var stream = AnyVersion.getPlugin().getResource("biome_definitions.nbt")) {
            packet.setDefinitions((NbtMap) NbtUtils.createNetworkReader(stream).readTag());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
