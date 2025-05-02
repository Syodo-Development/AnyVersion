package xyz.syodo.utils.palette;

import cn.nukkit.block.BlockState;
import cn.nukkit.nbt.stream.NBTInputStream;
import cn.nukkit.nbt.tag.CompoundTag;
import cn.nukkit.nbt.tag.ListTag;
import it.unimi.dsi.fastutil.objects.Object2ObjectArrayMap;
import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet;
import lombok.Getter;
import xyz.syodo.AnyVersion;
import xyz.syodo.utils.ProtocolVersion;
import xyz.syodo.utils.definition.LegacyBlockDefinition;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.nio.ByteOrder;
import java.util.Map;
import java.util.zip.GZIPInputStream;

public abstract class BlockPalette {

    @Getter
    protected final ProtocolVersion version;

    protected BlockPalette(ProtocolVersion version)  {
        this.version = version;
    }

    public abstract int getRuntimeId(BlockState state);
}
