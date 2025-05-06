package xyz.syodo.utils.palette;

import cn.nukkit.block.*;
import cn.nukkit.block.property.type.BlockPropertyType;
import cn.nukkit.block.property.type.BooleanPropertyType;
import cn.nukkit.block.property.type.EnumPropertyType;
import cn.nukkit.block.property.type.IntPropertyType;
import cn.nukkit.nbt.NBTIO;
import cn.nukkit.nbt.stream.NBTInputStream;
import cn.nukkit.nbt.tag.CompoundTag;

import cn.nukkit.nbt.tag.CompoundTagView;
import cn.nukkit.nbt.tag.ListTag;
import it.unimi.dsi.fastutil.objects.Object2ObjectArrayMap;
import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet;
import org.cloudburstmc.nbt.NbtMap;
import org.jetbrains.annotations.UnmodifiableView;
import xyz.syodo.AnyVersion;
import xyz.syodo.utils.ProtocolVersion;
import xyz.syodo.utils.definition.LegacyBlockDefinition;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static cn.nukkit.block.BlockID.INFO_UPDATE;

public class LegacyBlockPalette extends BlockPalette {

    private final Object2ObjectArrayMap<String, ObjectOpenHashSet<LegacyBlockDefinition>> states = new Object2ObjectArrayMap<>();

    public LegacyBlockPalette(ProtocolVersion version)  {
        super(version);
        try (var stream = AnyVersion.getPlugin().getResource("states/block_palette_" + version.protocol() + ".nbt")) {
            ListTag<CompoundTag> itemComponents = NBTIO.readCompressed(stream).getList("blocks", CompoundTag.class);
            int runtimeId = 0;
            for(CompoundTag tag : itemComponents.getAll()) {
                String name = tag.getString("name");
                if(!states.containsKey(name)) {
                    this.states.put(name, new ObjectOpenHashSet<>());
                }
                int id = tag.getInt("id");
                Map<String, Object> states = tag.getCompound("states").parseValue();
                LegacyBlockDefinition definition = new LegacyBlockDefinition(name, id, runtimeId++, states);
                this.states.get(name).add(definition);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public int getRuntimeId(BlockState state) {
        ObjectOpenHashSet<LegacyBlockDefinition> definitions = states.get(state.getIdentifier());
        if(definitions != null) {
            for(LegacyBlockDefinition definition : definitions) {
                if(definition.equals(state)) {
                    return definition.getRuntimeId();
                }
            }
        }
        return getRuntimeId(new BlockProperties(INFO_UPDATE).getDefaultState());
    }
}
