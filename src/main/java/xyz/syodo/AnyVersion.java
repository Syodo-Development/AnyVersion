package xyz.syodo;

import cn.nukkit.plugin.PluginBase;
import cn.nukkit.plugin.PluginManager;
import lombok.Getter;
import xyz.syodo.manager.ProtocolManager;
import xyz.syodo.utils.CloudburstRegistry;

public class AnyVersion extends PluginBase {

    @Getter
    private static AnyVersion plugin;

    @Override
    public void onLoad() {
        setEnabled();

        AnyVersion.plugin = this;
        PluginManager pluginManager = getServer().getPluginManager();
        pluginManager.registerEvents(new ProtocolManager(), this);
    }

}
