package xyz.syodo.registries.registries;

import it.unimi.dsi.fastutil.objects.ObjectArraySet;
import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet;
import xyz.syodo.registries.Registries;
import xyz.syodo.utils.ProtocolVersion;
import xyz.syodo.utils.definition.BlockStateDefinition;
import xyz.syodo.utils.definition.ItemDefinition;
import xyz.syodo.utils.table.ItemTable;
import xyz.syodo.utils.table.blockstate.*;

public class BlockStateRegistry extends Registry {

    private final ObjectArraySet<BlockStateTable> TABLES = new ObjectArraySet<>();

    @Override
    public void init() {
        TABLES.add(new BlockStateTable_1_21_70());
        TABLES.add(new BlockStateTable_1_21_60());
        TABLES.add(new BlockStateTable_1_21_50());
        TABLES.add(new BlockStateTable_1_21_40());
        TABLES.add(new BlockStateTable_1_21_30());
        TABLES.add(new BlockStateTable_1_21_20());
        TABLES.add(new BlockStateTable_1_21_0());
        TABLES.add(new BlockStateTable_1_20_80());

        for(BlockStateTable table : TABLES) {
            ItemTable itemTable;
            var opt = Registries.ITEM.getTables().stream().filter(itemTable1 -> itemTable1.getVersion().protocol() == table.getVersion().protocol()).findFirst();
            if(opt.isPresent()) {
                itemTable = opt.get();
            } else {
                itemTable = new ItemTable(table.getVersion());
                Registries.ITEM.getTables().add(itemTable);
            }
            for(BlockStateDefinition definition : table.getContent()) {
                itemTable.addAll(ItemDefinition.of(definition.getIdentifier()));
            }
        }
    }

    public ProtocolVersion getProtocolVersion(BlockStateDefinition definition) {
        for(BlockStateTable table : TABLES) {
            if(table.getContent().stream().anyMatch(definition::equals)) {
                return table.getVersion();
            }
        }
        return ProtocolVersion.getMin();
    }

    public BlockStateDefinition downgrade(ProtocolVersion version, BlockStateDefinition definition) {
        ObjectOpenHashSet<BlockStateTable> tables = new ObjectOpenHashSet<>();
        for(BlockStateTable table : TABLES) {
            var optresult = table.getContent().stream().filter(definition::equals).findFirst();
            if(optresult.isPresent()) {
                tables.add(table);
            }
        }
        BlockStateTable usedTable = new TemporaryBlockStateTable(ProtocolVersion.getCurrent(), definition);
        for(BlockStateTable table : tables) {
            int prot = table.getVersion().protocol();
            if(prot > version.protocol() && prot < usedTable.getVersion().protocol()) {
                usedTable = table;
            }
        }
        return usedTable.getContent().stream().filter(definition::equals).findFirst().orElse(definition);
    }

}
