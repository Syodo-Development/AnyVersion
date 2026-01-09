package org.powernukkitx.anyversion.utils.transformer.entity._1_21_50;

import org.cloudburstmc.protocol.bedrock.data.entity.EntityDataTypes;
import org.cloudburstmc.protocol.bedrock.packet.AddEntityPacket;
import org.powernukkitx.anyversion.utils.transformer.entity.EntityTransformer;

public class BoatTransformer extends EntityTransformer {

    @Override
    public void transform(AddEntityPacket packet) {
        if(packet.getMetadata().get(EntityDataTypes.VARIANT) == 9) {
            packet.getMetadata().put(EntityDataTypes.NAME, "§c§lOutdated client\n§cPALE_OAK_" + packet.getIdentifier().replace("minecraft:", "").toUpperCase());
            packet.getMetadata().put(EntityDataTypes.NAMETAG_ALWAYS_SHOW, (byte) 1);
            packet.getMetadata().put(EntityDataTypes.VARIANT, 0);
        }
    }

}
