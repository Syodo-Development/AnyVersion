package xyz.syodo;

import cn.nukkit.plugin.PluginBase;
import cn.nukkit.plugin.PluginManager;
import lombok.Getter;
import xyz.syodo.manager.PacketManager;
import xyz.syodo.manager.ProtocolManager;


public class VersionBypass extends PluginBase {

    @Getter
    private static VersionBypass plugin;

    @Override
    public void onLoad() {
        setEnabled();

        VersionBypass.plugin = this;

        PacketManager.init();

        PluginManager pluginManager = getServer().getPluginManager();
        pluginManager.registerEvents(new ProtocolManager(), this);
    }

}
