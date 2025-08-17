package xyz.syodo;

import cn.nukkit.network.protocol.ProtocolInfo;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.plugin.PluginManager;
import lombok.Getter;
import xyz.syodo.manager.ProtocolManager;
import xyz.syodo.registries.Registries;
import xyz.syodo.utils.ProtocolVersion;
import xyz.syodo.utils.ServerStartedListener;

public class AnyVersion extends PluginBase {

    @Getter
    private static AnyVersion plugin;

    private boolean failedToLoad = false;

    @Override
    public void onLoad() {
        AnyVersion.plugin = this;
    }

    @Override
    public void onEnable() {
        if(ProtocolInfo.CURRENT_PROTOCOL > ProtocolVersion.getMax().protocol()) {
            getPlugin().getLogger().critical("The current protocol is not supported by AnyVersion! Please update the plugin.");
            setEnabled(false);
            return;
        }
        PluginManager pluginManager = getServer().getPluginManager();
        pluginManager.registerEvents(new ProtocolManager(), this);
        pluginManager.registerEvents(new ServerStartedListener(), this);
        Registries.init();
    }
}
