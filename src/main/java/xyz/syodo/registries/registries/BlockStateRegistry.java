package xyz.syodo.registries.registries;

import cn.nukkit.Server;
import cn.nukkit.block.BlockID;
import cn.nukkit.block.BlockState;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import it.unimi.dsi.fastutil.objects.ObjectArraySet;
import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet;
import xyz.syodo.registries.Registries;
import xyz.syodo.utils.ProtocolVersion;
import xyz.syodo.utils.definition.BlockStateDefinition;
import xyz.syodo.utils.definition.ItemDefinition;
import xyz.syodo.utils.table.ItemTable;
import xyz.syodo.utils.table.blockstate.*;

import java.util.Comparator;

public class BlockStateRegistry extends Registry {

    private final ObjectArrayList<BlockStateTable> TABLES = new ObjectArrayList<>();

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
        TABLES.add(new BlockStateTable_1_20_70());
        TABLES.add(new BlockStateTable_1_20_60());
        TABLES.add(new BlockStateTable_1_20_50());
        TABLES.add(new BlockStateTable_1_20_40());
        TABLES.add(new BlockStateTable_1_20_30());
        TABLES.add(new BlockStateTable_1_20_10());
        TABLES.add(new BlockStateTable_1_20_0());
        TABLES.add(new BlockStateTable_1_19_80());

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

    public BlockState downgrade(ProtocolVersion version, BlockState state) {
        BlockStateDefinition definition = BlockStateDefinition.of(state);
        ObjectArrayList<BlockStateTable> tables = new ObjectArrayList<>();
        for(BlockStateTable table : TABLES) {
            var optresult = table.getContent().stream().filter(definition::equals).findFirst();
            if(optresult.isPresent()) {
                tables.add(table);
            }
        }

        final String identifier = state.getIdentifier();
        tables.sort(Comparator.comparingInt(o -> o.getVersion().protocol()));
        for(BlockStateTable table : tables.reversed()) {
            int prot = table.getVersion().protocol();
            if(prot > version.protocol()) {
                state = table.getContent().stream().filter(definition::equals).findFirst().orElse(definition).getDowngrade().transform(state);
            }
        }
        if(!identifier.equals(state.getIdentifier())) {
            state = downgrade(version, state);
        }
        return state;
    }

}
