package org.powernukkitx.anyversion.utils;

import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.server.ServerStartedEvent;

public class ServerStartedListener implements Listener {

    @EventHandler
    public void on(ServerStartedEvent event) {
        CloudburstRegistry.get().reload();
        for(ProtocolVersion version : ProtocolVersion.getVersions()) {
            version.helper().setItemDefinitions(CloudburstRegistry.get().getItemDefinitionRegistry());
            version.helper().setBlockDefinitions(CloudburstRegistry.get().getBlockDefinitionRegistry());
        }
    }

}
