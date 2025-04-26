package xyz.syodo.registries;

import xyz.syodo.registries.registries.BlockStateRegistry;
import xyz.syodo.registries.registries.ItemRegistry;
import xyz.syodo.registries.registries.PacketHandlerRegistry;

public class Registries {

    public static final PacketHandlerRegistry PACKETHANDLER = new PacketHandlerRegistry();
    public static final ItemRegistry ITEM = new ItemRegistry();
    public static final BlockStateRegistry BLOCKSTATE = new BlockStateRegistry();


    public static void init() {
        PACKETHANDLER.init();
        ITEM.init();
        BLOCKSTATE.init();
    }

}
