package xyz.syodo.utils.table.blockstate;

import xyz.syodo.utils.ProtocolVersion;
import xyz.syodo.utils.transformer._1_21_40.SkullTransformer;

import static cn.nukkit.block.BlockID.*;
import static xyz.syodo.utils.definition.BlockStateDefinition.of;

public class BlockStateTable_1_21_40 extends BlockStateTable {

    public BlockStateTable_1_21_40() {
        super(ProtocolVersion.MINECRAFT_PE_1_21_40,
                of(SKELETON_SKULL, new SkullTransformer()),
                of(PLAYER_HEAD, new SkullTransformer()),
                of(WITHER_SKELETON_SKULL, new SkullTransformer()),
                of(ZOMBIE_HEAD, new SkullTransformer()),
                of(CREEPER_HEAD, new SkullTransformer()),
                of(DRAGON_HEAD, new SkullTransformer()),
                of(PIGLIN_HEAD, new SkullTransformer())
        );
    }
}
