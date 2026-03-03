/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.internal.audio;

import com.neovisionaries.ws.client.ThreadType;
import com.neovisionaries.ws.client.WebSocket;
import com.neovisionaries.ws.client.WebSocketAdapter;
import com.neovisionaries.ws.client.WebSocketException;
import com.neovisionaries.ws.client.WebSocketFactory;
import com.neovisionaries.ws.client.WebSocketFrame;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.NoRouteToHostException;
import java.net.Socket;
import java.net.SocketException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.EnumSet;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import net.dv8tion.jda.api.audio.SpeakingMode;
import net.dv8tion.jda.api.audio.hooks.ConnectionListener;
import net.dv8tion.jda.api.audio.hooks.ConnectionStatus;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.events.ExceptionEvent;
import net.dv8tion.jda.api.utils.MiscUtil;
import net.dv8tion.jda.api.utils.data.DataArray;
import net.dv8tion.jda.api.utils.data.DataObject;
import net.dv8tion.jda.internal.JDAImpl;
import net.dv8tion.jda.internal.audio.AudioConnection;
import net.dv8tion.jda.internal.audio.AudioEncryption;
import net.dv8tion.jda.internal.audio.VoiceCode;
import net.dv8tion.jda.internal.managers.AudioManagerImpl;
import net.dv8tion.jda.internal.utils.Helpers;
import net.dv8tion.jda.internal.utils.IOUtil;
import net.dv8tion.jda.internal.utils.JDALogger;
import org.slf4j.Logger;

class AudioWebSocket
extends WebSocketAdapter {
    public static final Logger LOG = JDALogger.getLog(AudioWebSocket.class);
    public static final int DISCORD_SECRET_KEY_LENGTH = 32;
    private static final byte[] UDP_KEEP_ALIVE = new byte[]{-55, 0, 0, 0, 0, 0, 0, 0, 0};
    protected volatile AudioEncryption encryption;
    protected WebSocket socket;
    private final AudioConnection audioConnection;
    private final ConnectionListener listener;
    private final ScheduledExecutorService keepAlivePool;
    private final Guild guild;
    private final String sessionId;
    private final String token;
    private final String wssEndpoint;
    private volatile ConnectionStatus connectionStatus = ConnectionStatus.NOT_CONNECTED;
    private boolean ready = false;
    private boolean reconnecting = false;
    private boolean shouldReconnect;
    private int ssrc;
    private byte[] secretKey;
    private Future<?> keepAliveHandle;
    private InetSocketAddress address;
    private volatile boolean shutdown = false;

    protected AudioWebSocket(AudioConnection audioConnection, ConnectionListener listener, String endpoint, Guild guild, String sessionId, String token, boolean shouldReconnect) {
        this.audioConnection = audioConnection;
        this.listener = listener;
        this.guild = guild;
        this.sessionId = sessionId;
        this.token = token;
        this.shouldReconnect = shouldReconnect;
        this.keepAlivePool = this.getJDA().getAudioLifeCyclePool();
        this.wssEndpoint = Helpers.format("wss://%s/?v=%d", endpoint, 4);
        if (sessionId == null || sessionId.isEmpty()) {
            throw new IllegalArgumentException("Cannot create a voice connection using a null/empty sessionId!");
        }
        if (token == null || token.isEmpty()) {
            throw new IllegalArgumentException("Cannot create a voice connection using a null/empty token!");
        }
    }

    protected void send(String message) {
        LOG.trace("<- {}", (Object)message);
        this.socket.sendText(message);
    }

    protected void send(int op2, Object data) {
        this.send(DataObject.empty().put("op", op2).put("d", data).toString());
    }

    protected void startConnection() {
        if (!this.reconnecting && this.socket != null) {
            throw new IllegalStateException("Somehow, someway, this AudioWebSocket has already attempted to start a connection!");
        }
        try {
            WebSocketFactory socketFactory = new WebSocketFactory(this.getJDA().getWebSocketFactory());
            IOUtil.setServerName(socketFactory, this.wssEndpoint);
            if (socketFactory.getSocketTimeout() > 0) {
                socketFactory.setSocketTimeout(Math.max(1000, socketFactory.getSocketTimeout()));
            } else {
                socketFactory.setSocketTimeout(10000);
            }
            this.socket = socketFactory.createSocket(this.wssEndpoint);
            this.socket.setDirectTextMessage(true);
            this.socket.addListener(this);
            this.changeStatus(ConnectionStatus.CONNECTING_AWAITING_WEBSOCKET_CONNECT);
            this.socket.connectAsynchronously();
        }
        catch (IOException e2) {
            LOG.warn("Encountered IOException while attempting to connect to {}: {}\nClosing connection and attempting to reconnect.", (Object)this.wssEndpoint, (Object)e2.getMessage());
            this.close(ConnectionStatus.ERROR_WEBSOCKET_UNABLE_TO_CONNECT);
        }
    }

    protected void close(ConnectionStatus closeStatus) {
        if (this.shutdown) {
            return;
        }
        this.locked(manager -> {
            Guild connGuild;
            if (this.shutdown) {
                return;
            }
            ConnectionStatus status = closeStatus;
            this.ready = false;
            this.shutdown = true;
            this.stopKeepAlive();
            if (this.audioConnection.udpSocket != null) {
                this.audioConnection.udpSocket.close();
            }
            if (this.socket != null) {
                this.socket.sendClose();
            }
            this.audioConnection.shutdown();
            VoiceChannel disconnectedChannel = manager.getConnectedChannel();
            manager.setAudioConnection(null);
            JDAImpl api2 = this.getJDA();
            if (!(status != ConnectionStatus.DISCONNECTED_KICKED_FROM_CHANNEL || api2.getClient().isSession() && api2.getClient().isConnected())) {
                LOG.debug("Connection was closed due to session invalidate!");
                status = ConnectionStatus.ERROR_CANNOT_RESUME;
            } else if ((status == ConnectionStatus.ERROR_LOST_CONNECTION || status == ConnectionStatus.DISCONNECTED_KICKED_FROM_CHANNEL) && (connGuild = api2.getGuildById(this.guild.getIdLong())) != null && connGuild.getVoiceChannelById(this.audioConnection.getChannel().getIdLong()) == null) {
                status = ConnectionStatus.DISCONNECTED_CHANNEL_DELETED;
            }
            this.changeStatus(status);
            if (this.shouldReconnect && status.shouldReconnect() && status != ConnectionStatus.AUDIO_REGION_CHANGE) {
                if (disconnectedChannel == null) {
                    LOG.debug("Cannot reconnect due to null voice channel");
                    return;
                }
                api2.getDirectAudioController().reconnect(disconnectedChannel);
            } else if (status == ConnectionStatus.DISCONNECTED_REMOVED_FROM_GUILD) {
                api2.getAudioManagersView().remove(this.guild.getIdLong());
            } else if (status != ConnectionStatus.AUDIO_REGION_CHANGE && status != ConnectionStatus.DISCONNECTED_KICKED_FROM_CHANNEL) {
                api2.getDirectAudioController().disconnect(this.guild);
            }
        });
    }

    protected void changeStatus(ConnectionStatus newStatus) {
        this.connectionStatus = newStatus;
        this.listener.onStatusChange(newStatus);
    }

    protected void setAutoReconnect(boolean shouldReconnect) {
        this.shouldReconnect = shouldReconnect;
    }

    protected ConnectionStatus getConnectionStatus() {
        return this.connectionStatus;
    }

    protected InetSocketAddress getAddress() {
        return this.address;
    }

    protected byte[] getSecretKey() {
        return this.secretKey;
    }

    protected int getSSRC() {
        return this.ssrc;
    }

    protected boolean isReady() {
        return this.ready;
    }

    @Override
    public void onThreadStarted(WebSocket websocket, ThreadType threadType, Thread thread) {
        this.getJDA().setContext();
    }

    @Override
    public void onConnected(WebSocket websocket, Map<String, List<String>> headers) {
        if (this.shutdown) {
            this.socket.sendClose(1000);
            return;
        }
        if (this.reconnecting) {
            this.resume();
        } else {
            this.identify();
        }
        this.changeStatus(ConnectionStatus.CONNECTING_AWAITING_AUTHENTICATION);
        this.audioConnection.prepareReady();
        this.reconnecting = false;
    }

    @Override
    public void onTextMessage(WebSocket websocket, byte[] data) {
        try {
            this.handleEvent(DataObject.fromJson(data));
        }
        catch (Exception ex2) {
            String message = "malformed";
            try {
                message = new String(data, StandardCharsets.UTF_8);
            }
            catch (Exception exception) {
                // empty catch block
            }
            LOG.error("Encountered exception trying to handle an event message: {}", (Object)message, (Object)ex2);
        }
    }

    @Override
    public void onDisconnected(WebSocket websocket, WebSocketFrame serverCloseFrame, WebSocketFrame clientCloseFrame, boolean closedByServer) {
        if (this.shutdown) {
            return;
        }
        LOG.debug("The Audio connection was closed!\nBy remote? {}", (Object)closedByServer);
        if (serverCloseFrame != null) {
            LOG.debug("Reason: {}\nClose code: {}", (Object)serverCloseFrame.getCloseReason(), (Object)serverCloseFrame.getCloseCode());
            int code = serverCloseFrame.getCloseCode();
            VoiceCode.Close closeCode = VoiceCode.Close.from(code);
            switch (closeCode) {
                case SERVER_NOT_FOUND: 
                case SERVER_CRASH: 
                case INVALID_SESSION: {
                    this.close(ConnectionStatus.ERROR_CANNOT_RESUME);
                    break;
                }
                case AUTHENTICATION_FAILED: {
                    this.close(ConnectionStatus.DISCONNECTED_AUTHENTICATION_FAILURE);
                    break;
                }
                case DISCONNECTED: {
                    this.close(ConnectionStatus.DISCONNECTED_KICKED_FROM_CHANNEL);
                    break;
                }
                default: {
                    this.reconnect();
                }
            }
            return;
        }
        if (clientCloseFrame != null) {
            LOG.debug("ClientReason: {}\nClientCode: {}", (Object)clientCloseFrame.getCloseReason(), (Object)clientCloseFrame.getCloseCode());
            if (clientCloseFrame.getCloseCode() != 1000) {
                this.reconnect();
                return;
            }
        }
        this.close(ConnectionStatus.NOT_CONNECTED);
    }

    @Override
    public void onUnexpectedError(WebSocket websocket, WebSocketException cause) {
        this.handleCallbackError(websocket, cause);
    }

    @Override
    public void handleCallbackError(WebSocket websocket, Throwable cause) {
        LOG.error("There was some audio websocket error", cause);
        JDAImpl api2 = this.getJDA();
        api2.handleEvent(new ExceptionEvent(api2, cause, true));
    }

    @Override
    public void onThreadCreated(WebSocket websocket, ThreadType threadType, Thread thread) {
        String identifier = this.getJDA().getIdentifierString();
        String guildId = this.guild.getId();
        switch (threadType) {
            case CONNECT_THREAD: {
                thread.setName(identifier + " AudioWS-ConnectThread (guildId: " + guildId + ')');
                break;
            }
            case FINISH_THREAD: {
                thread.setName(identifier + " AudioWS-FinishThread (guildId: " + guildId + ')');
                break;
            }
            case WRITING_THREAD: {
                thread.setName(identifier + " AudioWS-WriteThread (guildId: " + guildId + ')');
                break;
            }
            case READING_THREAD: {
                thread.setName(identifier + " AudioWS-ReadThread (guildId: " + guildId + ')');
                break;
            }
            default: {
                thread.setName(identifier + " AudioWS-" + (Object)((Object)threadType) + " (guildId: " + guildId + ')');
            }
        }
    }

    @Override
    public void onConnectError(WebSocket webSocket, WebSocketException e2) {
        LOG.warn("Failed to establish websocket connection to {}: {} - {}\nClosing connection and attempting to reconnect.", new Object[]{this.wssEndpoint, e2.getError(), e2.getMessage()});
        this.close(ConnectionStatus.ERROR_WEBSOCKET_UNABLE_TO_CONNECT);
    }

    private void handleEvent(DataObject contentAll) {
        int opCode = contentAll.getInt("op");
        switch (opCode) {
            case 8: {
                LOG.trace("-> HELLO {}", (Object)contentAll);
                DataObject payload = contentAll.getObject("d");
                int interval = payload.getInt("heartbeat_interval");
                this.stopKeepAlive();
                this.setupKeepAlive(interval);
                break;
            }
            case 2: {
                InetSocketAddress externalIpAndPort;
                LOG.trace("-> READY {}", (Object)contentAll);
                DataObject content = contentAll.getObject("d");
                this.ssrc = content.getInt("ssrc");
                int port = content.getInt("port");
                String ip2 = content.getString("ip");
                DataArray modes = content.getArray("modes");
                this.encryption = AudioEncryption.getPreferredMode(modes);
                if (this.encryption == null) {
                    this.close(ConnectionStatus.ERROR_UNSUPPORTED_ENCRYPTION_MODES);
                    LOG.error("None of the provided encryption modes are supported: {}", (Object)modes);
                    return;
                }
                LOG.debug("Using encryption mode " + this.encryption.getKey());
                this.changeStatus(ConnectionStatus.CONNECTING_ATTEMPTING_UDP_DISCOVERY);
                int tries = 0;
                do {
                    if ((externalIpAndPort = this.handleUdpDiscovery(new InetSocketAddress(ip2, port), this.ssrc)) != null || ++tries <= 5) continue;
                    this.close(ConnectionStatus.ERROR_UDP_UNABLE_TO_CONNECT);
                    return;
                } while (externalIpAndPort == null);
                DataObject object = DataObject.empty().put("protocol", "udp").put("data", DataObject.empty().put("address", externalIpAndPort.getHostString()).put("port", externalIpAndPort.getPort()).put("mode", this.encryption.getKey()));
                this.send(1, object);
                this.changeStatus(ConnectionStatus.CONNECTING_AWAITING_READY);
                break;
            }
            case 9: {
                LOG.trace("-> RESUMED {}", (Object)contentAll);
                LOG.debug("Successfully resumed session!");
                this.changeStatus(ConnectionStatus.CONNECTED);
                this.ready = true;
                break;
            }
            case 4: {
                LOG.trace("-> SESSION_DESCRIPTION {}", (Object)contentAll);
                this.send(5, DataObject.empty().put("delay", 0).put("speaking", 0).put("ssrc", this.ssrc));
                DataArray keyArray = contentAll.getObject("d").getArray("secret_key");
                this.secretKey = new byte[32];
                for (int i2 = 0; i2 < keyArray.length(); ++i2) {
                    this.secretKey[i2] = (byte)keyArray.getInt(i2);
                }
                LOG.debug("Audio connection has finished connecting!");
                this.ready = true;
                this.changeStatus(ConnectionStatus.CONNECTED);
                break;
            }
            case 3: {
                LOG.trace("-> HEARTBEAT {}", (Object)contentAll);
                this.send(3, System.currentTimeMillis());
                break;
            }
            case 6: {
                LOG.trace("-> HEARTBEAT_ACK {}", (Object)contentAll);
                long ping = System.currentTimeMillis() - contentAll.getLong("d");
                this.listener.onPing(ping);
                break;
            }
            case 5: {
                LOG.trace("-> USER_SPEAKING_UPDATE {}", (Object)contentAll);
                DataObject content = contentAll.getObject("d");
                EnumSet<SpeakingMode> speaking = SpeakingMode.getModes(content.getInt("speaking"));
                int ssrc = content.getInt("ssrc");
                long userId = content.getLong("user_id");
                User user = this.getUser(userId);
                if (user == null) {
                    LOG.trace("Got an Audio USER_SPEAKING_UPDATE for a non-existent User. JSON: {}", (Object)contentAll);
                    break;
                }
                this.audioConnection.updateUserSSRC(ssrc, userId);
                this.listener.onUserSpeaking(user, speaking);
                break;
            }
            case 13: {
                LOG.trace("-> USER_DISCONNECT {}", (Object)contentAll);
                DataObject payload = contentAll.getObject("d");
                long userId = payload.getLong("user_id");
                this.audioConnection.removeUserSSRC(userId);
                break;
            }
            case 12: 
            case 14: {
                LOG.trace("-> OP {} {}", (Object)opCode, (Object)contentAll);
                break;
            }
            default: {
                LOG.debug("Unknown Audio OP code.\n{}", (Object)contentAll);
            }
        }
    }

    private void identify() {
        DataObject connectObj = DataObject.empty().put("server_id", this.guild.getId()).put("user_id", this.getJDA().getSelfUser().getId()).put("session_id", this.sessionId).put("token", this.token);
        this.send(0, connectObj);
    }

    private void resume() {
        LOG.debug("Sending resume payload...");
        DataObject resumeObj = DataObject.empty().put("server_id", this.guild.getId()).put("session_id", this.sessionId).put("token", this.token);
        this.send(7, resumeObj);
    }

    private JDAImpl getJDA() {
        return this.audioConnection.getJDA();
    }

    private void locked(Consumer<AudioManagerImpl> consumer) {
        AudioManagerImpl manager = (AudioManagerImpl)this.guild.getAudioManager();
        MiscUtil.locked(manager.CONNECTION_LOCK, () -> consumer.accept(manager));
    }

    private void reconnect() {
        if (this.shutdown) {
            return;
        }
        this.locked(unused -> {
            if (this.shutdown) {
                return;
            }
            this.ready = false;
            this.reconnecting = true;
            this.changeStatus(ConnectionStatus.ERROR_LOST_CONNECTION);
            this.startConnection();
        });
    }

    private InetSocketAddress handleUdpDiscovery(InetSocketAddress address, int ssrc) {
        try {
            if (this.audioConnection.udpSocket != null) {
                this.audioConnection.udpSocket.close();
            }
            this.audioConnection.udpSocket = new DatagramSocket();
            ByteBuffer buffer = ByteBuffer.allocate(70);
            buffer.putShort((short)1);
            buffer.putShort((short)70);
            buffer.putInt(ssrc);
            DatagramPacket discoveryPacket = new DatagramPacket(buffer.array(), buffer.array().length, address);
            this.audioConnection.udpSocket.send(discoveryPacket);
            DatagramPacket receivedPacket = new DatagramPacket(new byte[70], 70);
            this.audioConnection.udpSocket.setSoTimeout(1000);
            this.audioConnection.udpSocket.receive(receivedPacket);
            byte[] received = receivedPacket.getData();
            String ourIP = new String(received, 4, received.length - 6);
            ourIP = ourIP.trim();
            int ourPort = IOUtil.getShortBigEndian(received, received.length - 2) & 0xFFFF;
            this.address = address;
            return new InetSocketAddress(ourIP, ourPort);
        }
        catch (IOException e2) {
            return null;
        }
    }

    private void stopKeepAlive() {
        if (this.keepAliveHandle != null) {
            this.keepAliveHandle.cancel(true);
        }
        this.keepAliveHandle = null;
    }

    private void setupKeepAlive(int keepAliveInterval) {
        if (this.keepAliveHandle != null) {
            LOG.error("Setting up a KeepAlive runnable while the previous one seems to still be active!!");
        }
        try {
            Socket rawSocket;
            if (this.socket != null && (rawSocket = this.socket.getSocket()) != null) {
                rawSocket.setSoTimeout(keepAliveInterval + 10000);
            }
        }
        catch (SocketException ex2) {
            LOG.warn("Failed to setup timeout for socket", ex2);
        }
        Runnable keepAliveRunnable = () -> {
            this.getJDA().setContext();
            if (this.socket != null && this.socket.isOpen()) {
                this.send(3, System.currentTimeMillis());
            }
            if (this.audioConnection.udpSocket != null && !this.audioConnection.udpSocket.isClosed()) {
                try {
                    DatagramPacket keepAlivePacket = new DatagramPacket(UDP_KEEP_ALIVE, UDP_KEEP_ALIVE.length, this.address);
                    this.audioConnection.udpSocket.send(keepAlivePacket);
                }
                catch (NoRouteToHostException e2) {
                    LOG.warn("Closing AudioConnection due to inability to ping audio packets.");
                    LOG.warn("Cannot send audio packet because JDA navigate the route to Discord.\nAre you sure you have internet connection? It is likely that you've lost connection.");
                    this.close(ConnectionStatus.ERROR_LOST_CONNECTION);
                }
                catch (IOException e3) {
                    LOG.error("There was some error sending an audio keepalive packet", e3);
                }
            }
        };
        try {
            this.keepAliveHandle = this.keepAlivePool.scheduleAtFixedRate(keepAliveRunnable, 0L, keepAliveInterval, TimeUnit.MILLISECONDS);
        }
        catch (RejectedExecutionException rejectedExecutionException) {
            // empty catch block
        }
    }

    private User getUser(long userId) {
        return this.getJDA().getUserById(userId);
    }

    protected void finalize() {
        if (!this.shutdown) {
            LOG.error("Finalization hook of AudioWebSocket was triggered without properly shutting down");
            this.close(ConnectionStatus.NOT_CONNECTED);
        }
    }
}

