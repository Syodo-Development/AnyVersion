package xyz.syodo.handler.handlers;

import it.unimi.dsi.fastutil.objects.Object2IntMap;
import org.cloudburstmc.protocol.bedrock.codec.BaseBedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.EntityDataTypeMap;
import org.cloudburstmc.protocol.bedrock.data.entity.EntityDataMap;
import org.cloudburstmc.protocol.bedrock.data.entity.EntityDataTypes;
import org.cloudburstmc.protocol.bedrock.data.entity.EntityFlag;
import org.cloudburstmc.protocol.bedrock.packet.SetEntityDataPacket;
import org.cloudburstmc.protocol.bedrock.transformer.FlagTransformer;
import org.cloudburstmc.protocol.common.util.TypeMap;
import xyz.syodo.handler.PacketHandler;
import xyz.syodo.manager.ProtocolPlayer;
import xyz.syodo.utils.ProtocolVersion;

import java.lang.reflect.Field;

public class SetEntityDataHandler extends PacketHandler<SetEntityDataPacket> {

    private static final Field entityDataField;
    private static final Field typeMapField;
    private static final Field toIdField;

    static {
        try {
            entityDataField = BaseBedrockCodecHelper.class.getDeclaredField("entityData");
            entityDataField.setAccessible(true);
            typeMapField = FlagTransformer.class.getDeclaredField("typeMap");
            typeMapField.setAccessible(true);
            toIdField = TypeMap.class.getDeclaredField("toId");
            toIdField.setAccessible(true);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void handle(ProtocolPlayer player, SetEntityDataPacket packet) {
        ProtocolVersion protocol = player.getVersion();
        fixEntityFlags(protocol, packet.getMetadata());
    }

    public static final void fixEntityFlags(ProtocolVersion version, EntityDataMap meta) {
        BedrockCodecHelper codec = version.helper();
        if(meta.getFlags() != null) {
            try {
                EntityDataTypeMap map = (EntityDataTypeMap) entityDataField.get(codec);
                if(map.fromType(EntityDataTypes.FLAGS).getTransformer() instanceof FlagTransformer transformer) {
                    TypeMap<EntityFlag> typeMap = (TypeMap<EntityFlag>) typeMapField.get(transformer);
                    Object2IntMap<EntityFlag> toId = (Object2IntMap<EntityFlag>) toIdField.get(typeMap);
                    for(EntityFlag flag : meta.getFlags().keySet().stream().toList()) {
                        if(!toId.containsKey(flag)) {
                            meta.clearFlag(flag);
                        }
                    }
                }
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
