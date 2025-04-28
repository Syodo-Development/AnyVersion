package xyz.syodo.handler.handlers;

import org.cloudburstmc.protocol.bedrock.data.command.CommandData;
import org.cloudburstmc.protocol.bedrock.data.command.CommandOverloadData;
import org.cloudburstmc.protocol.bedrock.data.command.CommandParam;
import org.cloudburstmc.protocol.bedrock.data.command.CommandParamData;
import org.cloudburstmc.protocol.bedrock.packet.AvailableCommandsPacket;
import xyz.syodo.handler.PacketHandler;
import xyz.syodo.manager.ProtocolPlayer;
import xyz.syodo.utils.ProtocolVersion;

public class AvailableCommandsHandler extends PacketHandler<AvailableCommandsPacket> {
    @Override
    public void handle(ProtocolVersion version, AvailableCommandsPacket packet) {
        for(CommandData command : packet.getCommands()) {
            for(CommandOverloadData overload : command.getOverloads()) {
                for(CommandParamData param : overload.getOverloads()) {
                    if(param.getType() == null) {
                        param.setType(CommandParam.UNKNOWN);
                    }
                }
            }
        }
    }
}
