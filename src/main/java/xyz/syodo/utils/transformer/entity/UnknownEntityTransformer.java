package xyz.syodo.utils.transformer.entity;

import cn.nukkit.entity.EntityID;
import org.cloudburstmc.protocol.bedrock.data.entity.EntityDataTypes;
import org.cloudburstmc.protocol.bedrock.packet.AddEntityPacket;

public class UnknownEntityTransformer extends EntityTransformer {

    @Override
    public void transform(AddEntityPacket packet) {
        packet.getMetadata().put(EntityDataTypes.NAME, "§c§lOutdated client\n§c" + packet.getIdentifier().replace("minecraft:", "").toUpperCase());
        packet.getMetadata().put(EntityDataTypes.NAMETAG_ALWAYS_SHOW, (byte) 1);
        packet.setIdentifier(EntityID.ZOMBIE);

    }

}
