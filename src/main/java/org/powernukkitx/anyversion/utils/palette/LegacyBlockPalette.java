package org.powernukkitx.anyversion.utils.palette;

import cn.nukkit.block.*;
import cn.nukkit.nbt.NBTIO;
import cn.nukkit.nbt.tag.CompoundTag;

import cn.nukkit.nbt.tag.ListTag;
import it.unimi.dsi.fastutil.objects.Object2ObjectArrayMap;
import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet;
import org.powernukkitx.anyversion.AnyVersion;
import org.powernukkitx.anyversion.utils.ProtocolVersion;
import org.powernukkitx.anyversion.utils.definition.LegacyBlockDefinition;

import java.io.IOException;
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
