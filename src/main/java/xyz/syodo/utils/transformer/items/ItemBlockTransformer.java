package xyz.syodo.utils.transformer.items;

import cn.nukkit.block.Block;
import cn.nukkit.block.BlockID;
import cn.nukkit.block.BlockState;
import lombok.RequiredArgsConstructor;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.protocol.bedrock.data.definitions.BlockDefinition;
import org.cloudburstmc.protocol.bedrock.data.definitions.ItemDefinition;
import org.cloudburstmc.protocol.bedrock.data.definitions.SimpleBlockDefinition;
import org.cloudburstmc.protocol.bedrock.data.definitions.SimpleItemDefinition;
import org.cloudburstmc.protocol.bedrock.data.inventory.ItemData;
import xyz.syodo.registries.Registries;
import xyz.syodo.utils.ProtocolVersion;

@RequiredArgsConstructor
public class ItemBlockTransformer extends ItemDataTransformer {

    protected final ProtocolVersion version;

    @Override
    public ItemData transform(ItemData original) {
        SimpleBlockDefinition simpleBlockDefinition = (SimpleBlockDefinition) original.getBlockDefinition();
        if(simpleBlockDefinition == null) {
            BlockState state = Block.get(original.getDefinition().getIdentifier()).getBlockState();
            if(state == null) throw new RuntimeException(original.getDefinition().getIdentifier() + " has no SimpleBlockDefinition!");
            simpleBlockDefinition = new SimpleBlockDefinition(state.getIdentifier(), state.blockStateHash(), NbtMap.fromMap(state.getBlockStateTag().parseValue()));
        }
        BlockState originalBlockState = cn.nukkit.registry.Registries.BLOCKSTATE.get(simpleBlockDefinition.getRuntimeId());
        if(originalBlockState == null) originalBlockState = Block.get(original.getDefinition().getIdentifier()).getProperties().getDefaultState();
        if(originalBlockState == null) return Registries.ITEM.getOutdated(original);
        BlockState downgraded = Registries.BLOCKSTATE.downgrade(version, originalBlockState, false);
        if(downgraded.getIdentifier().equals(BlockID.UNKNOWN) || downgraded.getIdentifier().equals(BlockID.INFO_UPDATE)) return Registries.ITEM.getOutdated(original);
        SimpleBlockDefinition blockDefinition = new SimpleBlockDefinition(downgraded.getIdentifier(), downgraded.blockStateHash(), NbtMap.fromMap(downgraded.getBlockStateTag().parseValue()));
        ItemDefinition originalItemDefinition = original.getDefinition();
        ItemDefinition itemDefinition = new SimpleItemDefinition(downgraded.getIdentifier(), originalItemDefinition.getRuntimeId(), originalItemDefinition.getVersion(), originalItemDefinition.isComponentBased(), originalItemDefinition.getComponentData());
        return ItemData.builder()
                .definition(itemDefinition)
                .damage(original.getDamage())
                .count(original.getCount())
                .tag(original.getTag())
                .canPlace(original.getCanPlace())
                .canBreak(original.getCanBreak())
                .blockingTicks(original.getBlockingTicks())
                .blockDefinition(blockDefinition)
                .usingNetId(original.isUsingNetId())
                .netId(original.getNetId())
                .build();
    }
}
