package xyz.syodo.utils.transformer.entity;

import org.cloudburstmc.protocol.bedrock.packet.AddEntityPacket;

public abstract class EntityTransformer {

    public abstract void transform(AddEntityPacket definition);

}
