package xyz.syodo.manager;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerJoinEvent;
import cn.nukkit.event.player.PlayerQuitEvent;
import cn.nukkit.event.server.DataPacketReceiveEvent;
import cn.nukkit.network.Network;
import cn.nukkit.network.connection.BedrockSession;
import cn.nukkit.network.connection.netty.codec.packet.BedrockPacketCodec;
import cn.nukkit.network.connection.netty.codec.packet.BedrockPacketCodec_v3;
import cn.nukkit.network.connection.netty.initializer.BedrockChannelInitializer;
import cn.nukkit.network.process.SessionState;
import cn.nukkit.network.protocol.LoginPacket;
import cn.nukkit.network.protocol.ProtocolInfo;
import cn.nukkit.network.protocol.RequestNetworkSettingsPacket;
import cn.nukkit.network.protocol.types.PlayerInfo;
import com.github.oxo42.stateless4j.StateMachine;
import com.github.oxo42.stateless4j.StateMachineConfig;
import io.netty.channel.ChannelPipeline;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import xyz.syodo.handlers.PResourcePackHandler;
import xyz.syodo.utils.PBedrockPacketCodec;
import xyz.syodo.utils.ProtocolVersion;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.InetSocketAddress;
import java.util.Arrays;
import java.util.Map;
import java.util.UUID;

public class ProtocolManager implements Listener {

    private static Object2ObjectOpenHashMap<UUID, ProtocolPlayer> players = new Object2ObjectOpenHashMap<>();

    @EventHandler
    public void onRequestNetworkSettingsPacket(DataPacketReceiveEvent event) {
        if(event.getPacket() instanceof RequestNetworkSettingsPacket packet) {
            int client_protocol = packet.protocolVersion;
            int server_protocol = ProtocolInfo.CURRENT_PROTOCOL;
            if(Arrays.stream(ProtocolVersion.values()).anyMatch(p -> p.protocol() == client_protocol)) {
                if(client_protocol < server_protocol) {
                    packet.protocolVersion = server_protocol;
                }
            }
        }
    }

    @EventHandler
    public void on(DataPacketReceiveEvent event) throws NoSuchFieldException, IllegalAccessException, NoSuchMethodException {
        if(event.getPacket() instanceof LoginPacket packet) {
            if(packet.protocol == ProtocolInfo.CURRENT_PROTOCOL) return;
            Field fSessionMap = Network.class.getDeclaredField("sessionMap");
            fSessionMap.setAccessible(true);
            Map<InetSocketAddress, BedrockSession> sessionMap = (Map<InetSocketAddress, BedrockSession>) fSessionMap.get(Server.getInstance().getNetwork());
            fSessionMap.setAccessible(false);

            for(BedrockSession bedrockSession : sessionMap.values()) {
                Field fMachine = BedrockSession.class.getDeclaredField("machine");
                fMachine.setAccessible(true);
                StateMachine<SessionState, SessionState> machine = (StateMachine<SessionState, SessionState>) fMachine.get(bedrockSession);
                fMachine.setAccessible(false);
                if(machine.getState() == SessionState.LOGIN) {
                    ProtocolPlayer player = new ProtocolPlayer(bedrockSession, packet.protocol);
                    PBedrockPacketCodec codec = new PBedrockPacketCodec(player);

                    ChannelPipeline pipeline = bedrockSession.getPeer().getChannel().pipeline();
                    pipeline.addBefore(BedrockPacketCodec.NAME, PBedrockPacketCodec.NAME, codec);
                    pipeline.remove(BedrockPacketCodec.NAME);
                    Field fConfig = StateMachine.class.getDeclaredField("config");
                    fConfig.setAccessible(true);
                    StateMachineConfig<SessionState, SessionState> config = (StateMachineConfig<SessionState, SessionState>) fConfig.get(machine);
                    fConfig.setAccessible(false);
                    Method onServerLoginSuccess = BedrockSession.class.getDeclaredMethod("onServerLoginSuccess");
                    Field fInfo = BedrockSession.class.getDeclaredField("info");
                    config.configure(SessionState.LOGIN).onExit(() -> {
                        try {
                            onServerLoginSuccess.setAccessible(true);
                            onServerLoginSuccess.invoke(bedrockSession);
                            onServerLoginSuccess.setAccessible(false);
                            fInfo.setAccessible(true);
                            PlayerInfo info = (PlayerInfo) fInfo.get(bedrockSession);
                            fInfo.setAccessible(false);
                            if(info.getUniqueId().equals(packet.clientUUID)) {
                                config.configure(SessionState.RESOURCE_PACK).onEntry(() -> {
                                    bedrockSession.setPacketHandler(new PResourcePackHandler(bedrockSession, packet.clientUUID));
                                });
                                players.put(packet.clientUUID, player);
                            }
                        } catch (IllegalAccessException | InvocationTargetException e) {
                            throw new RuntimeException(e);
                        }
                    });
                }
            }
        }
    }

    @EventHandler
    public void on(PlayerJoinEvent event) {
        Player p = event.getPlayer();
        if(players.containsKey(p.getUniqueId())) {
            ProtocolPlayer protocolPlayer = get(p);
            p.sendMessage("§4You are running " + ProtocolVersion.get(protocolPlayer.protocol()).version() + ". This is outdated and may cause problems.\n§4Please update your client.");
        }
    }

    @EventHandler
    public void on(PlayerQuitEvent event) {
        players.remove(event.getPlayer().getUniqueId());
    }

    public static ProtocolPlayer get(UUID uuid) {
        return players.get(uuid);
    }

    public static ProtocolPlayer get(Player player) {
        return get(player.getUniqueId());
    }
}
