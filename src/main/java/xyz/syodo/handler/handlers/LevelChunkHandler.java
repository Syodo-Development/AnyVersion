package xyz.syodo.handler.handlers;

import cn.nukkit.block.*;
import cn.nukkit.level.biome.BiomeID;
import cn.nukkit.level.format.ChunkSection;
import cn.nukkit.level.format.bitarray.BitArrayVersion;
import cn.nukkit.level.format.palette.BlockPalette;
import cn.nukkit.level.format.palette.Palette;
import cn.nukkit.network.connection.util.HandleByteBuf;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import lombok.SneakyThrows;
import org.cloudburstmc.protocol.bedrock.packet.LevelChunkPacket;
import xyz.syodo.handler.PacketHandler;
import xyz.syodo.manager.ProtocolPlayer;
import xyz.syodo.registries.Registries;
import xyz.syodo.utils.BlockStateRuntimeDataDeserializer;
import xyz.syodo.utils.IntegerRuntimeDataDeserializer;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class LevelChunkHandler extends PacketHandler<LevelChunkPacket> {

    @SneakyThrows
    @Override
    public void handle(ProtocolPlayer player, LevelChunkPacket packet) {
        ByteBuf data = packet.getData().copy();
        int total = packet.getSubChunksLength();
        HandleByteBuf modified = HandleByteBuf.of(Unpooled.buffer());
        Field field = Palette.class.getDeclaredField("palette");
        try {
            field.setAccessible(true);
            for(int i = 0; i < total; i++) {
                modified.writeByte(data.readByte()); //Version
                short layerCount = data.readByte();
                modified.writeByte(layerCount); //Layer Count
                modified.writeByte(data.readByte()); //Y coordinate
                for(int l = 0; l < layerCount; l++) {
                    BlockPalette palette1 = new BlockPalette(BlockAir.STATE);
                    palette1.readFromNetwork(data, new BlockStateRuntimeDataDeserializer());
                    List<BlockState> paletteList = (List<BlockState>) field.get(palette1);
                    List<BlockState> overwritten = new ArrayList<>();
                    for(BlockState state : paletteList) {
                        if(state != BlockAir.STATE) {
                            overwritten.add(Registries.BLOCKSTATE.downgrade(player.getVersion(), state));
                            continue;
                        }
                        overwritten.add(state);
                    }
                    field.set(palette1, overwritten);
                    palette1.writeToNetwork(modified, blockState -> Registries.BLOCKPALETTE.getRuntimeId(player.getVersion(), blockState));
                }
            }
            for(int i = 0; i < total; i++) {
                Palette<Integer> palette = new Palette<>(0);
                ((List<Integer>) field.get(palette)).clear();
                palette.readFromNetwork(data, new IntegerRuntimeDataDeserializer());
                palette.writeToNetwork(modified, Integer::intValue);
            }

            } catch (Exception exception) {
            throw new RuntimeException(exception);
        } finally {
            field.setAccessible(false);
        }

        packet.setData(modified);
    }

}
