package xyz.syodo.manager;

import cn.nukkit.network.process.DataPacketManager;
import cn.nukkit.network.protocol.DataPacket;
import xyz.syodo.packets.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PacketManager {

    private static List<Class<? extends ProtocolizedPacket>> packets = new ArrayList<>();

    public static void addPacket(Class<? extends ProtocolizedPacket> packet) {
        packets.add(packet);
    }

    public static Optional<Class<? extends ProtocolizedPacket>> get(DataPacket packet) {
        return packets.stream().filter(f -> f.getSuperclass() == packet.getClass()).findFirst();
    }

    public static void init() {
        addPacket(PCameraInstructionPacket.class);
        addPacket(PCameraPresetsPacket.class);
        addPacket(PChangeDimensionPacket.class);
        addPacket(PDisconnectPacket.class);
        addPacket(PEmotePacket.class);
        addPacket(PInventoryContentPacket.class);
        addPacket(PInventorySlotPacket.class);
        addPacket(PInventoryTransactionPacket.class);
        addPacket(PMobArmorEquipmentPacket.class);
        addPacket(PMobEffectPacket.class);
        addPacket(PPlayerAuthInputPacket.class);
        addPacket(PResourcePacksInfoPacket.class);
        addPacket(PSetEntityLinkPacket.class);
        addPacket(PSetTitlePacket.class);
        addPacket(PStopSoundPacket.class);
        addPacket(PTransferPacket.class);
        addPacket(PUpdateAttributesPacket.class);
    }

}
