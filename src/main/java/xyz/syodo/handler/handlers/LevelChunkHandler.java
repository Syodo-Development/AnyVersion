package xyz.syodo.handler.handlers;

import cn.nukkit.block.*;
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
import xyz.syodo.utils.ProtocolVersion;
import xyz.syodo.utils.definition.BlockStateDefinition;
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
                modified.writeByte(data.readByte()); //Layer Count
                modified.writeByte(data.readByte()); //Y coordinate
                BlockPalette palette1 = new BlockPalette(BlockAir.STATE);
                palette1.readFromNetwork(data, new BlockStateRuntimeDataDeserializer());
                BlockPalette palette2 = new BlockPalette(BlockAir.STATE);
                palette2.readFromNetwork(data, new BlockStateRuntimeDataDeserializer());
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
                //Converting the second palette is not required since its just used for water and snow logging.

                palette1.writeToNetwork(modified, blockState -> Registries.BLOCKPALETTE.getRuntimeId(player.getVersion(), blockState));
                palette2.writeToNetwork(modified, blockState -> Registries.BLOCKPALETTE.getRuntimeId(player.getVersion(), blockState));
            }
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        } finally {
            field.setAccessible(false);
        }
        packet.setData(modified);
    }

}
