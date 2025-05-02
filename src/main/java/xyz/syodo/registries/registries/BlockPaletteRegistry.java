package xyz.syodo.registries.registries;

import cn.nukkit.block.BlockState;
import it.unimi.dsi.fastutil.ints.Int2ObjectArrayMap;
import xyz.syodo.utils.ProtocolVersion;
import xyz.syodo.utils.palette.BlockHashBlockPalette;
import xyz.syodo.utils.palette.BlockPalette;
import xyz.syodo.utils.palette.LegacyBlockPalette;

public class BlockPaletteRegistry extends Registry {

    private BlockHashBlockPalette DEFAULT = new BlockHashBlockPalette();

    private Int2ObjectArrayMap<BlockPalette> PALETTES = new Int2ObjectArrayMap<>();

    @Override
    public void init() {
        register(new LegacyBlockPalette(ProtocolVersion.MINECRAFT_PE_1_19_70));
    }

    public int getRuntimeId(ProtocolVersion version, BlockState state) {
        return PALETTES.getOrDefault(version.protocol(), DEFAULT).getRuntimeId(state);
    }

    private void register(BlockPalette palette) {
        PALETTES.put(palette.getVersion().protocol(), palette);
    }

}
