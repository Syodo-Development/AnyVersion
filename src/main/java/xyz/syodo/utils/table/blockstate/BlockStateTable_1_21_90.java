package xyz.syodo.utils.table.blockstate;

import xyz.syodo.utils.ProtocolVersion;

import static cn.nukkit.block.BlockID.*;
import static xyz.syodo.utils.definition.BlockStateDefinition.of;

public class BlockStateTable_1_21_90 extends BlockStateTable {

    public BlockStateTable_1_21_90() {
        super(ProtocolVersion.MINECRAFT_PE_1_21_90,
                of(DRIED_GHAST)
        );
    }

}
