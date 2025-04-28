package xyz.syodo;

import cn.nukkit.Server;
import cn.nukkit.network.protocol.ProtocolInfo;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.plugin.PluginManager;
import lombok.Getter;
import xyz.syodo.manager.ProtocolManager;
import xyz.syodo.registries.Registries;
import xyz.syodo.utils.ProtocolVersion;

import java.util.Arrays;

public class AnyVersion extends PluginBase {

    @Getter
    private static AnyVersion plugin;

    @Override
    public void onLoad() {
        if(Arrays.stream(ProtocolVersion.getVersions()).anyMatch(p -> p.protocol() == ProtocolInfo.CURRENT_PROTOCOL)) {
            throw new UnsupportedOperationException("The current protocol is not supported by AnyVersion! Please update the plugin.");
        }
        setEnabled();
        AnyVersion.plugin = this;
        PluginManager pluginManager = getServer().getPluginManager();
        pluginManager.registerEvents(new ProtocolManager(), this);

        Registries.init();
    }

}
