package xyz.syodo;

import cn.nukkit.plugin.PluginBase;
import cn.nukkit.plugin.PluginManager;
import lombok.Getter;
import xyz.syodo.manager.ProtocolManager;
import xyz.syodo.registries.Registries;
import xyz.syodo.utils.ProtocolVersion;
import xyz.syodo.utils.ServerStartedListener;
import xyz.syodo.utils.palette.LegacyBlockPalette;

public class AnyVersion extends PluginBase {

    @Getter
    private static AnyVersion plugin;

    @Override
    public void onLoad() {
        try {
            ProtocolVersion.getCurrent();
        } catch (Exception e) {
            new UnsupportedOperationException("The current protocol is not supported by AnyVersion! Please update the plugin.");
        }

        AnyVersion.plugin = this;
    }

    @Override
    public void onEnable() {
        PluginManager pluginManager = getServer().getPluginManager();
        pluginManager.registerEvents(new ProtocolManager(), this);
        pluginManager.registerEvents(new ServerStartedListener(), this);
        Registries.init();
    }
}
