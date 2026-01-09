package org.powernukkitx.anyversion.registries;

import lombok.Getter;
import org.powernukkitx.anyversion.registries.registries.*;

public class Registries {

    @Getter
    private static boolean initiated = false;

    public static final PacketHandlerRegistry PACKETHANDLER = new PacketHandlerRegistry();
    public static final ItemRegistry ITEM = new ItemRegistry();
    public static final BlockStateRegistry BLOCKSTATE = new BlockStateRegistry();
    public static final BlockPaletteRegistry BLOCKPALETTE = new BlockPaletteRegistry();
    public static final EntityRegistry ENTITY = new EntityRegistry();


    public static void init() {
        if(!initiated) {
            PACKETHANDLER.init();
            ITEM.init();
            BLOCKSTATE.init();
            BLOCKPALETTE.init();
            ENTITY.init();
        }
    }

}
