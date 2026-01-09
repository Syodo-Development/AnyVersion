package xyz.syodo.registries.registries;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import it.unimi.dsi.fastutil.objects.ObjectArraySet;
import org.cloudburstmc.protocol.bedrock.packet.AddEntityPacket;
import xyz.syodo.utils.ProtocolVersion;
import xyz.syodo.utils.table.entity.*;

import java.util.Comparator;

public class EntityRegistry extends Registry {

    private final ObjectArraySet<EntityTable> TABLES = new ObjectArraySet<>();

    @Override
    public void init() {
        TABLES.add(new EntityTable_1_21_130());
        TABLES.add(new EntityTable_1_21_110());
        TABLES.add(new EntityTable_1_21_90());
        TABLES.add(new EntityTable_1_21_0());
        TABLES.add(new EntityTable_1_21_50());
        TABLES.add(new EntityTable_1_21_80());
        TABLES.add(new EntityTable_1_20_0());
    }

    public void transform(ProtocolVersion version, AddEntityPacket packet) {
        String networkId = packet.getIdentifier();
        ObjectArrayList<EntityTable> tables = new ObjectArrayList<>();
        for(EntityTable table : TABLES) {
            var optresult = table.getContent().stream().filter(definition -> {
                return definition.getNetworkId().equals(networkId);
            }).findFirst();
            if(optresult.isPresent()) {
                tables.add(table);
            }
        }

        tables.sort(Comparator.comparingInt(o -> o.getVersion().protocol()));
        for(EntityTable table : tables.reversed()) {
            int prot = table.getVersion().protocol();
            if(prot > version.protocol()) {
                table.getContent().stream().filter(definition -> definition.getNetworkId().equals(networkId)).findFirst().get().getDowngrade().transform(packet);
                return;
            }
        }
    }
}
