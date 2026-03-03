/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.internal.requests;

import com.neovisionaries.ws.client.ThreadType;
import com.neovisionaries.ws.client.WebSocket;
import com.neovisionaries.ws.client.WebSocketAdapter;
import com.neovisionaries.ws.client.WebSocketException;
import com.neovisionaries.ws.client.WebSocketFactory;
import com.neovisionaries.ws.client.WebSocketFrame;
import com.neovisionaries.ws.client.WebSocketListener;
import gnu.trove.iterator.TLongObjectIterator;
import gnu.trove.map.TLongObjectMap;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.ref.SoftReference;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.nio.charset.StandardCharsets;
import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.zip.DataFormatException;
import javax.annotation.Nonnull;
import net.dv8tion.jda.api.AccountType;
import net.dv8tion.jda.api.GatewayEncoding;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.audio.hooks.ConnectionListener;
import net.dv8tion.jda.api.audio.hooks.ConnectionStatus;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.GuildChannel;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.events.DisconnectEvent;
import net.dv8tion.jda.api.events.ExceptionEvent;
import net.dv8tion.jda.api.events.RawGatewayEvent;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.events.ReconnectedEvent;
import net.dv8tion.jda.api.events.ResumedEvent;
import net.dv8tion.jda.api.events.ShutdownEvent;
import net.dv8tion.jda.api.exceptions.ParsingException;
import net.dv8tion.jda.api.managers.AudioManager;
import net.dv8tion.jda.api.requests.CloseCode;
import net.dv8tion.jda.api.utils.Compression;
import net.dv8tion.jda.api.utils.MiscUtil;
import net.dv8tion.jda.api.utils.SessionController;
import net.dv8tion.jda.api.utils.data.DataArray;
import net.dv8tion.jda.api.utils.data.DataObject;
import net.dv8tion.jda.api.utils.data.DataType;
import net.dv8tion.jda.internal.JDAImpl;
import net.dv8tion.jda.internal.audio.ConnectionRequest;
import net.dv8tion.jda.internal.audio.ConnectionStage;
import net.dv8tion.jda.internal.entities.GuildImpl;
import net.dv8tion.jda.internal.handle.ApplicationCommandCreateHandler;
import net.dv8tion.jda.internal.handle.ApplicationCommandDeleteHandler;
import net.dv8tion.jda.internal.handle.ApplicationCommandUpdateHandler;
import net.dv8tion.jda.internal.handle.ChannelCreateHandler;
import net.dv8tion.jda.internal.handle.ChannelDeleteHandler;
import net.dv8tion.jda.internal.handle.ChannelUpdateHandler;
import net.dv8tion.jda.internal.handle.GuildBanHandler;
import net.dv8tion.jda.internal.handle.GuildCreateHandler;
import net.dv8tion.jda.internal.handle.GuildDeleteHandler;
import net.dv8tion.jda.internal.handle.GuildEmojisUpdateHandler;
import net.dv8tion.jda.internal.handle.GuildMemberAddHandler;
import net.dv8tion.jda.internal.handle.GuildMemberRemoveHandler;
import net.dv8tion.jda.internal.handle.GuildMemberUpdateHandler;
import net.dv8tion.jda.internal.handle.GuildMembersChunkHandler;
import net.dv8tion.jda.internal.handle.GuildRoleCreateHandler;
import net.dv8tion.jda.internal.handle.GuildRoleDeleteHandler;
import net.dv8tion.jda.internal.handle.GuildRoleUpdateHandler;
import net.dv8tion.jda.internal.handle.GuildSetupController;
import net.dv8tion.jda.internal.handle.GuildSyncHandler;
import net.dv8tion.jda.internal.handle.GuildUpdateHandler;
import net.dv8tion.jda.internal.handle.InteractionCreateHandler;
import net.dv8tion.jda.internal.handle.InviteCreateHandler;
import net.dv8tion.jda.internal.handle.InviteDeleteHandler;
import net.dv8tion.jda.internal.handle.MessageBulkDeleteHandler;
import net.dv8tion.jda.internal.handle.MessageCreateHandler;
import net.dv8tion.jda.internal.handle.MessageDeleteHandler;
import net.dv8tion.jda.internal.handle.MessageReactionBulkRemoveHandler;
import net.dv8tion.jda.internal.handle.MessageReactionClearEmoteHandler;
import net.dv8tion.jda.internal.handle.MessageReactionHandler;
import net.dv8tion.jda.internal.handle.MessageUpdateHandler;
import net.dv8tion.jda.internal.handle.PresenceUpdateHandler;
import net.dv8tion.jda.internal.handle.ReadyHandler;
import net.dv8tion.jda.internal.handle.SocketHandler;
import net.dv8tion.jda.internal.handle.TypingStartHandler;
import net.dv8tion.jda.internal.handle.UserUpdateHandler;
import net.dv8tion.jda.internal.handle.VoiceServerUpdateHandler;
import net.dv8tion.jda.internal.handle.VoiceStateUpdateHandler;
import net.dv8tion.jda.internal.managers.AudioManagerImpl;
import net.dv8tion.jda.internal.managers.PresenceImpl;
import net.dv8tion.jda.internal.requests.MemberChunkManager;
import net.dv8tion.jda.internal.requests.WebSocketSendingThread;
import net.dv8tion.jda.internal.utils.IOUtil;
import net.dv8tion.jda.internal.utils.JDALogger;
import net.dv8tion.jda.internal.utils.UnlockHook;
import net.dv8tion.jda.internal.utils.cache.AbstractCacheView;
import net.dv8tion.jda.internal.utils.compress.Decompressor;
import net.dv8tion.jda.internal.utils.compress.ZlibDecompressor;
import org.slf4j.Logger;
import org.slf4j.MDC;

public class WebSocketClient
extends WebSocketAdapter
implements WebSocketListener {
    public static final ThreadLocal<Boolean> WS_THREAD = ThreadLocal.withInitial(() -> false);
    public static final Logger LOG = JDALogger.getLog(WebSocketClient.class);
    public static final int IDENTIFY_DELAY = 5;
    public static final int ZLIB_SUFFIX = 65535;
    protected static final String INVALIDATE_REASON = "INVALIDATE_SESSION";
    protected static final long IDENTIFY_BACKOFF = TimeUnit.SECONDS.toMillis(5L);
    protected final JDAImpl api;
    protected final JDA.ShardInfo shardInfo;
    protected final Map<String, SocketHandler> handlers = new HashMap<String, SocketHandler>();
    protected final Compression compression;
    protected final int gatewayIntents;
    protected final MemberChunkManager chunkManager;
    protected final GatewayEncoding encoding;
    public WebSocket socket;
    protected volatile String sessionId = null;
    protected final Object readLock = new Object();
    protected Decompressor decompressor;
    protected final ReentrantLock queueLock = new ReentrantLock();
    protected final ScheduledExecutorService executor;
    protected WebSocketSendingThread ratelimitThread;
    protected volatile Future<?> keepAliveThread;
    protected boolean initiating;
    protected int missedHeartbeats = 0;
    protected int reconnectTimeoutS = 2;
    protected long heartbeatStartTime;
    protected long identifyTime = 0L;
    protected final TLongObjectMap<ConnectionRequest> queuedAudioConnections = MiscUtil.newLongMap();
    protected final Queue<DataObject> chunkSyncQueue = new ConcurrentLinkedQueue<DataObject>();
    protected final Queue<DataObject> ratelimitQueue = new ConcurrentLinkedQueue<DataObject>();
    protected volatile long ratelimitResetTime;
    protected final AtomicInteger messagesSent = new AtomicInteger(0);
    protected volatile boolean shutdown = false;
    protected boolean shouldReconnect;
    protected boolean handleIdentifyRateLimit = false;
    protected boolean connected = false;
    protected volatile boolean printedRateLimitMessage = false;
    protected volatile boolean sentAuthInfo = false;
    protected boolean firstInit = true;
    protected boolean processingReady = true;
    protected volatile ConnectNode connectNode;

    public WebSocketClient(JDAImpl api2, Compression compression, int gatewayIntents, GatewayEncoding encoding) {
        this.api = api2;
        this.executor = api2.getGatewayPool();
        this.shardInfo = api2.getShardInfo();
        this.compression = compression;
        this.gatewayIntents = gatewayIntents;
        this.chunkManager = new MemberChunkManager(this);
        this.encoding = encoding;
        this.shouldReconnect = api2.isAutoReconnect();
        this.connectNode = new StartingNode();
        this.setupHandlers();
        try {
            api2.getSessionController().appendSession(this.connectNode);
        }
        catch (Error | RuntimeException e2) {
            LOG.error("Failed to append new session to session controller queue. Shutting down!", e2);
            this.api.setStatus(JDA.Status.SHUTDOWN);
            this.api.handleEvent(new ShutdownEvent(api2, OffsetDateTime.now(), 1006));
            if (e2 instanceof RuntimeException) {
                throw (RuntimeException)e2;
            }
            throw (Error)e2;
        }
    }

    public JDA getJDA() {
        return this.api;
    }

    public void setAutoReconnect(boolean reconnect) {
        this.shouldReconnect = reconnect;
    }

    public boolean isConnected() {
        return this.connected;
    }

    public int getGatewayIntents() {
        return this.gatewayIntents;
    }

    public MemberChunkManager getChunkManager() {
        return this.chunkManager;
    }

    public void ready() {
        if (this.initiating) {
            this.initiating = false;
            this.processingReady = false;
            if (this.firstInit) {
                this.firstInit = false;
                if (this.api.getGuilds().size() >= 2000) {
                    JDAImpl.LOG.warn(" __      __ _    ___  _  _  ___  _  _   ___  _ ");
                    JDAImpl.LOG.warn(" \\ \\    / //_\\  | _ \\| \\| ||_ _|| \\| | / __|| |");
                    JDAImpl.LOG.warn("  \\ \\/\\/ // _ \\ |   /| .` | | | | .` || (_ ||_|");
                    JDAImpl.LOG.warn("   \\_/\\_//_/ \\_\\|_|_\\|_|\\_||___||_|\\_| \\___|(_)");
                    JDAImpl.LOG.warn("You're running a session with over 2000 connected");
                    JDAImpl.LOG.warn("guilds. You should shard the connection in order");
                    JDAImpl.LOG.warn("to split the load or things like resuming");
                    JDAImpl.LOG.warn("connection might not work as expected.");
                    JDAImpl.LOG.warn("For more info see https://git.io/vrFWP");
                }
                JDAImpl.LOG.info("Finished Loading!");
                this.api.handleEvent(new ReadyEvent(this.api, this.api.getResponseTotal()));
            } else {
                this.updateAudioManagerReferences();
                JDAImpl.LOG.info("Finished (Re)Loading!");
                this.api.handleEvent(new ReconnectedEvent(this.api, this.api.getResponseTotal()));
            }
        } else {
            JDAImpl.LOG.debug("Successfully resumed Session!");
            this.api.handleEvent(new ResumedEvent(this.api, this.api.getResponseTotal()));
        }
        this.api.setStatus(JDA.Status.CONNECTED);
    }

    public boolean isReady() {
        return !this.initiating;
    }

    public boolean isSession() {
        return this.sessionId != null;
    }

    public void handle(List<DataObject> events) {
        events.forEach(this::onDispatch);
    }

    public void send(DataObject message) {
        this.locked("Interrupted while trying to add request to queue", () -> this.ratelimitQueue.add(message));
    }

    public void cancelChunkRequest(String nonce) {
        this.locked("Interrupted while trying to cancel chunk request", () -> this.chunkSyncQueue.removeIf(it2 -> it2.getString("nonce", "").equals(nonce)));
    }

    public void sendChunkRequest(DataObject request) {
        this.locked("Interrupted while trying to add chunk request", () -> this.chunkSyncQueue.add(request));
    }

    protected boolean send(DataObject message, boolean skipQueue) {
        if (!this.connected) {
            return false;
        }
        long now = System.currentTimeMillis();
        if (this.ratelimitResetTime <= now) {
            this.messagesSent.set(0);
            this.ratelimitResetTime = now + 60000L;
            this.printedRateLimitMessage = false;
        }
        if (this.messagesSent.get() <= 115 || skipQueue && this.messagesSent.get() <= 119) {
            LOG.trace("<- {}", (Object)message);
            if (this.encoding == GatewayEncoding.ETF) {
                this.socket.sendBinary(message.toETF());
            } else {
                this.socket.sendText(message.toString());
            }
            this.messagesSent.getAndIncrement();
            return true;
        }
        if (!this.printedRateLimitMessage) {
            LOG.warn("Hit the WebSocket RateLimit! This can be caused by too many presence or voice status updates (connect/disconnect/mute/deaf). Regular: {} Voice: {} Chunking: {}", this.ratelimitQueue.size(), this.queuedAudioConnections.size(), this.chunkSyncQueue.size());
            this.printedRateLimitMessage = true;
        }
        return false;
    }

    protected void setupSendingThread() {
        this.ratelimitThread = new WebSocketSendingThread(this);
        this.ratelimitThread.start();
    }

    private void prepareClose() {
        try {
            Socket rawSocket;
            if (this.socket != null && (rawSocket = this.socket.getSocket()) != null) {
                rawSocket.setSoTimeout(10000);
            }
        }
        catch (SocketException socketException) {
            // empty catch block
        }
    }

    public void close() {
        this.prepareClose();
        if (this.socket != null) {
            this.socket.sendClose(1000);
        }
    }

    public void close(int code) {
        this.prepareClose();
        if (this.socket != null) {
            this.socket.sendClose(code);
        }
    }

    public void close(int code, String reason) {
        this.prepareClose();
        if (this.socket != null) {
            this.socket.sendClose(code, reason);
        }
    }

    public synchronized void shutdown() {
        this.shutdown = true;
        this.shouldReconnect = false;
        if (this.connectNode != null) {
            this.api.getSessionController().removeSession(this.connectNode);
        }
        this.close(1000, "Shutting down");
    }

    protected synchronized void connect() {
        if (this.api.getStatus() != JDA.Status.ATTEMPTING_TO_RECONNECT) {
            this.api.setStatus(JDA.Status.CONNECTING_TO_WEBSOCKET);
        }
        if (this.shutdown) {
            throw new RejectedExecutionException("JDA is shutdown!");
        }
        this.initiating = true;
        String url = this.api.getGatewayUrl() + "?encoding=" + this.encoding.name().toLowerCase() + "&v=" + 8;
        if (this.compression != Compression.NONE) {
            url = url + "&compress=" + this.compression.getKey();
            switch (this.compression) {
                case ZLIB: {
                    if (this.decompressor != null && this.decompressor.getType() == Compression.ZLIB) break;
                    this.decompressor = new ZlibDecompressor(this.api.getMaxBufferSize());
                    break;
                }
                default: {
                    throw new IllegalStateException("Unknown compression");
                }
            }
        }
        try {
            WebSocketFactory socketFactory = new WebSocketFactory(this.api.getWebSocketFactory());
            IOUtil.setServerName(socketFactory, url);
            if (socketFactory.getSocketTimeout() > 0) {
                socketFactory.setSocketTimeout(Math.max(1000, socketFactory.getSocketTimeout()));
            } else {
                socketFactory.setSocketTimeout(10000);
            }
            this.socket = socketFactory.createSocket(url);
            this.socket.setDirectTextMessage(true);
            this.socket.addHeader("Accept-Encoding", "gzip").addListener(this).connect();
        }
        catch (WebSocketException | IOException e2) {
            this.api.resetGatewayUrl();
            throw new IllegalStateException(e2);
        }
    }

    @Override
    public void onThreadStarted(WebSocket websocket, ThreadType threadType, Thread thread) throws Exception {
        this.api.setContext();
    }

    @Override
    public void onConnected(WebSocket websocket, Map<String, List<String>> headers) {
        this.prepareClose();
        this.api.setStatus(JDA.Status.IDENTIFYING_SESSION);
        if (this.sessionId == null) {
            LOG.info("Connected to WebSocket");
            LOG.debug("Connected with gateway intents: {}", (Object)Integer.toBinaryString(this.gatewayIntents));
        } else {
            LOG.debug("Connected to WebSocket");
        }
        this.connected = true;
        this.messagesSent.set(0);
        this.ratelimitResetTime = System.currentTimeMillis() + 60000L;
        if (this.sessionId == null) {
            this.sendIdentify();
        } else {
            this.sendResume();
        }
    }

    @Override
    public void onDisconnected(WebSocket websocket, WebSocketFrame serverCloseFrame, WebSocketFrame clientCloseFrame, boolean closedByServer) {
        this.sentAuthInfo = false;
        this.connected = false;
        if (Thread.currentThread().isInterrupted()) {
            Thread thread = new Thread(() -> this.handleDisconnect(websocket, serverCloseFrame, clientCloseFrame, closedByServer));
            thread.setName(this.api.getIdentifierString() + " MainWS-ReconnectThread");
            thread.start();
        } else {
            this.handleDisconnect(websocket, serverCloseFrame, clientCloseFrame, closedByServer);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private void handleDisconnect(WebSocket websocket, WebSocketFrame serverCloseFrame, WebSocketFrame clientCloseFrame, boolean closedByServer) {
        boolean closeCodeIsReconnect;
        this.api.setStatus(JDA.Status.DISCONNECTED);
        CloseCode closeCode = null;
        int rawCloseCode = 1005;
        boolean isInvalidate = false;
        if (this.keepAliveThread != null) {
            this.keepAliveThread.cancel(false);
            this.keepAliveThread = null;
        }
        if (closedByServer && serverCloseFrame != null) {
            rawCloseCode = serverCloseFrame.getCloseCode();
            String rawCloseReason = serverCloseFrame.getCloseReason();
            closeCode = CloseCode.from(rawCloseCode);
            if (closeCode == CloseCode.RATE_LIMITED) {
                LOG.error("WebSocket connection closed due to ratelimit! Sent more than 120 websocket messages in under 60 seconds!");
            } else if (closeCode == CloseCode.UNKNOWN_ERROR) {
                LOG.error("WebSocket connection closed due to server error! {}: {}", (Object)rawCloseCode, (Object)rawCloseReason);
            } else if (closeCode != null) {
                LOG.debug("WebSocket connection closed with code {}", (Object)closeCode);
            } else if (rawCloseReason != null) {
                LOG.warn("WebSocket connection closed with code {}: {}", (Object)rawCloseCode, (Object)rawCloseReason);
            } else {
                LOG.warn("WebSocket connection closed with unknown meaning for close-code {}", (Object)rawCloseCode);
            }
        } else if (clientCloseFrame != null && (rawCloseCode = clientCloseFrame.getCloseCode()) == 1000 && INVALIDATE_REASON.equals(clientCloseFrame.getCloseReason())) {
            isInvalidate = true;
        }
        boolean bl2 = closeCodeIsReconnect = closeCode == null || closeCode.isReconnect();
        if (!this.shouldReconnect || !closeCodeIsReconnect || this.executor.isShutdown()) {
            if (this.ratelimitThread != null) {
                this.ratelimitThread.shutdown();
                this.ratelimitThread = null;
            }
            if (!closeCodeIsReconnect) {
                LOG.error("WebSocket connection was closed and cannot be recovered due to identification issues\n{}", (Object)closeCode);
            }
            if (this.decompressor != null) {
                this.decompressor.shutdown();
            }
            this.api.shutdownInternals();
            this.api.handleEvent(new ShutdownEvent(this.api, OffsetDateTime.now(), rawCloseCode));
        } else {
            Object object = this.readLock;
            synchronized (object) {
                if (this.decompressor != null) {
                    this.decompressor.reset();
                }
            }
            if (isInvalidate) {
                this.invalidate();
            }
            this.api.handleEvent(new DisconnectEvent(this.api, serverCloseFrame, clientCloseFrame, closedByServer, OffsetDateTime.now()));
            try {
                this.handleReconnect(rawCloseCode);
            }
            catch (InterruptedException e2) {
                LOG.error("Failed to resume due to interrupted thread", e2);
                this.invalidate();
                this.queueReconnect();
            }
        }
    }

    private void handleReconnect(int code) throws InterruptedException {
        if (this.sessionId == null) {
            if (this.handleIdentifyRateLimit) {
                long backoff = this.calculateIdentifyBackoff();
                if (backoff > 0L) {
                    LOG.error("Encountered IDENTIFY Rate Limit! Waiting {} milliseconds before trying again!", (Object)backoff);
                    Thread.sleep(backoff);
                } else {
                    LOG.error("Encountered IDENTIFY Rate Limit!");
                }
            }
            LOG.warn("Got disconnected from WebSocket (Code {}). Appending to reconnect queue", (Object)code);
            this.queueReconnect();
        } else {
            LOG.debug("Got disconnected from WebSocket (Code: {}). Attempting to resume session", (Object)code);
            this.reconnect();
        }
    }

    protected long calculateIdentifyBackoff() {
        long currentTime = System.currentTimeMillis();
        return currentTime - (this.identifyTime + IDENTIFY_BACKOFF);
    }

    protected void queueReconnect() {
        try {
            this.api.setStatus(JDA.Status.RECONNECT_QUEUED);
            this.connectNode = new ReconnectNode();
            this.api.getSessionController().appendSession(this.connectNode);
        }
        catch (IllegalStateException ex2) {
            LOG.error("Reconnect queue rejected session. Shutting down...");
            this.api.setStatus(JDA.Status.SHUTDOWN);
            this.api.handleEvent(new ShutdownEvent(this.api, OffsetDateTime.now(), 1006));
        }
    }

    protected void reconnect() throws InterruptedException {
        this.reconnect(false);
    }

    public void reconnect(boolean callFromQueue) throws InterruptedException {
        Set<MDC.MDCCloseable> contextEntries = null;
        Map<String, String> previousContext = null;
        ConcurrentMap<String, String> contextMap = this.api.getContextMap();
        if (callFromQueue && contextMap != null) {
            previousContext = MDC.getCopyOfContextMap();
            contextEntries = contextMap.entrySet().stream().map(entry -> MDC.putCloseable((String)entry.getKey(), (String)entry.getValue())).collect(Collectors.toSet());
        }
        if (this.shutdown) {
            this.api.setStatus(JDA.Status.SHUTDOWN);
            this.api.handleEvent(new ShutdownEvent(this.api, OffsetDateTime.now(), 1000));
            return;
        }
        String message = "";
        if (callFromQueue) {
            message = String.format("Queue is attempting to reconnect a shard...%s ", this.shardInfo != null ? " Shard: " + this.shardInfo.getShardString() : "");
        }
        if (this.sessionId != null) {
            this.reconnectTimeoutS = 0;
        }
        LOG.debug("{}Attempting to reconnect in {}s", (Object)message, (Object)this.reconnectTimeoutS);
        while (this.shouldReconnect) {
            this.api.setStatus(JDA.Status.WAITING_TO_RECONNECT);
            int delay = this.reconnectTimeoutS;
            this.reconnectTimeoutS = this.reconnectTimeoutS == 0 ? 2 : Math.min(this.reconnectTimeoutS << 1, this.api.getMaxReconnectDelay());
            Thread.sleep(delay * 1000);
            this.handleIdentifyRateLimit = false;
            this.api.setStatus(JDA.Status.ATTEMPTING_TO_RECONNECT);
            LOG.debug("Attempting to reconnect!");
            try {
                this.connect();
                break;
            }
            catch (RejectedExecutionException ex2) {
                this.api.setStatus(JDA.Status.SHUTDOWN);
                this.api.handleEvent(new ShutdownEvent(this.api, OffsetDateTime.now(), 1000));
                return;
            }
            catch (RuntimeException ex3) {
                LOG.warn("Reconnect failed! Next attempt in {}s", (Object)this.reconnectTimeoutS);
            }
        }
        if (contextEntries != null) {
            contextEntries.forEach(MDC.MDCCloseable::close);
        }
        if (previousContext != null) {
            previousContext.forEach(MDC::put);
        }
    }

    protected void setupKeepAlive(int timeout) {
        try {
            Socket rawSocket = this.socket.getSocket();
            if (rawSocket != null) {
                rawSocket.setSoTimeout(timeout + 10000);
            }
        }
        catch (SocketException ex2) {
            LOG.warn("Failed to setup timeout for socket", ex2);
        }
        this.keepAliveThread = this.executor.scheduleAtFixedRate(() -> {
            this.api.setContext();
            if (this.connected) {
                this.sendKeepAlive();
            }
        }, 0L, timeout, TimeUnit.MILLISECONDS);
    }

    protected void sendKeepAlive() {
        DataObject keepAlivePacket = DataObject.empty().put("op", 1).put("d", this.api.getResponseTotal());
        if (this.missedHeartbeats >= 2) {
            this.missedHeartbeats = 0;
            LOG.warn("Missed 2 heartbeats! Trying to reconnect...");
            this.prepareClose();
            this.socket.disconnect(4900, "ZOMBIE CONNECTION");
        } else {
            ++this.missedHeartbeats;
            this.send(keepAlivePacket, true);
            this.heartbeatStartTime = System.currentTimeMillis();
        }
    }

    protected void sendIdentify() {
        LOG.debug("Sending Identify-packet...");
        PresenceImpl presenceObj = (PresenceImpl)this.api.getPresence();
        DataObject connectionProperties = DataObject.empty().put("$os", System.getProperty("os.name")).put("$browser", "JDA").put("$device", "JDA").put("$referring_domain", "").put("$referrer", "");
        DataObject payload = DataObject.empty().put("presence", presenceObj.getFullPresence()).put("token", this.getToken()).put("properties", connectionProperties).put("v", 8).put("large_threshold", this.api.getLargeThreshold());
        payload.put("intents", this.gatewayIntents);
        DataObject identify = DataObject.empty().put("op", 2).put("d", payload);
        if (this.shardInfo != null) {
            payload.put("shard", DataArray.empty().add(this.shardInfo.getShardId()).add(this.shardInfo.getShardTotal()));
        }
        this.send(identify, true);
        this.handleIdentifyRateLimit = true;
        this.identifyTime = System.currentTimeMillis();
        this.sentAuthInfo = true;
        this.api.setStatus(JDA.Status.AWAITING_LOGIN_CONFIRMATION);
    }

    protected void sendResume() {
        LOG.debug("Sending Resume-packet...");
        DataObject resume = DataObject.empty().put("op", 6).put("d", DataObject.empty().put("session_id", this.sessionId).put("token", this.getToken()).put("seq", this.api.getResponseTotal()));
        this.send(resume, true);
        this.api.setStatus(JDA.Status.AWAITING_LOGIN_CONFIRMATION);
    }

    protected void invalidate() {
        this.sessionId = null;
        this.sentAuthInfo = false;
        this.locked("Interrupted while trying to invalidate chunk/sync queue", this.chunkSyncQueue::clear);
        this.api.getTextChannelsView().clear();
        this.api.getVoiceChannelsView().clear();
        this.api.getStoreChannelsView().clear();
        this.api.getCategoriesView().clear();
        this.api.getGuildsView().clear();
        this.api.getUsersView().clear();
        this.api.getPrivateChannelsView().clear();
        this.api.getEventCache().clear();
        this.api.getGuildSetupController().clearCache();
        this.chunkManager.clear();
    }

    protected void updateAudioManagerReferences() {
        AbstractCacheView<AudioManager> managerView = this.api.getAudioManagersView();
        try (UnlockHook hook = managerView.writeLock();){
            TLongObjectMap<AudioManager> managerMap = managerView.getMap();
            if (managerMap.size() > 0) {
                LOG.trace("Updating AudioManager references");
            }
            TLongObjectIterator<AudioManager> it2 = managerMap.iterator();
            while (it2.hasNext()) {
                it2.advance();
                long guildId = it2.key();
                AudioManagerImpl mng = (AudioManagerImpl)it2.value();
                GuildImpl guild = (GuildImpl)this.api.getGuildById(guildId);
                if (guild != null) continue;
                this.queuedAudioConnections.remove(guildId);
                mng.closeAudioConnection(ConnectionStatus.DISCONNECTED_REMOVED_DURING_RECONNECT);
                it2.remove();
            }
        }
    }

    protected String getToken() {
        if (this.api.getAccountType() == AccountType.BOT) {
            return this.api.getToken().substring("Bot ".length());
        }
        return this.api.getToken();
    }

    protected List<DataObject> convertPresencesReplace(long responseTotal, DataArray array) {
        LinkedList<DataObject> output = new LinkedList<DataObject>();
        for (int i2 = 0; i2 < array.length(); ++i2) {
            DataObject presence = array.getObject(i2);
            DataObject obj = DataObject.empty();
            obj.put("comment", "This was constructed from a PRESENCES_REPLACE payload").put("op", 0).put("s", responseTotal).put("d", presence).put("t", "PRESENCE_UPDATE");
            output.add(obj);
        }
        return output;
    }

    protected void handleEvent(DataObject content) {
        try {
            this.onEvent(content);
        }
        catch (Exception ex2) {
            LOG.error("Encountered exception on lifecycle level\nJSON: {}", (Object)content, (Object)ex2);
            this.api.handleEvent(new ExceptionEvent(this.api, ex2, true));
        }
    }

    protected void onEvent(DataObject content) {
        WS_THREAD.set(true);
        int opCode = content.getInt("op");
        if (!content.isNull("s")) {
            this.api.setResponseTotal(content.getInt("s"));
        }
        switch (opCode) {
            case 0: {
                this.onDispatch(content);
                break;
            }
            case 1: {
                LOG.debug("Got Keep-Alive request (OP 1). Sending response...");
                this.sendKeepAlive();
                break;
            }
            case 7: {
                LOG.debug("Got Reconnect request (OP 7). Closing connection now...");
                this.close(4900, "OP 7: RECONNECT");
                break;
            }
            case 9: {
                int closeCode;
                LOG.debug("Got Invalidate request (OP 9). Invalidating...");
                this.handleIdentifyRateLimit = this.handleIdentifyRateLimit && System.currentTimeMillis() - this.identifyTime < IDENTIFY_BACKOFF;
                this.sentAuthInfo = false;
                boolean isResume = content.getBoolean("d");
                int n2 = closeCode = isResume ? 4900 : 1000;
                if (isResume) {
                    LOG.debug("Session can be recovered... Closing and sending new RESUME request");
                } else {
                    this.invalidate();
                }
                this.close(closeCode, INVALIDATE_REASON);
                break;
            }
            case 10: {
                LOG.debug("Got HELLO packet (OP 10). Initializing keep-alive.");
                DataObject data = content.getObject("d");
                this.setupKeepAlive(data.getInt("heartbeat_interval"));
                break;
            }
            case 11: {
                LOG.trace("Got Heartbeat Ack (OP 11).");
                this.missedHeartbeats = 0;
                this.api.setGatewayPing(System.currentTimeMillis() - this.heartbeatStartTime);
                break;
            }
            default: {
                LOG.debug("Got unknown op-code: {} with content: {}", (Object)opCode, (Object)content);
            }
        }
    }

    protected void onDispatch(DataObject raw) {
        String type = raw.getString("t");
        long responseTotal = this.api.getResponseTotal();
        if (!raw.isType("d", DataType.OBJECT)) {
            if (type.equals("PRESENCES_REPLACE")) {
                DataArray payload = raw.getArray("d");
                List<DataObject> converted = this.convertPresencesReplace(responseTotal, payload);
                Object handler = this.getHandler("PRESENCE_UPDATE");
                LOG.trace("{} -> {}", (Object)type, (Object)payload);
                for (DataObject o2 : converted) {
                    ((SocketHandler)handler).handle(responseTotal, o2);
                    if (!this.api.isRawEvents()) continue;
                    this.api.handleEvent(new RawGatewayEvent(this.api, responseTotal, o2));
                }
            } else {
                LOG.debug("Received event with unhandled body type JSON: {}", (Object)raw);
            }
            return;
        }
        DataObject content = raw.getObject("d");
        LOG.trace("{} -> {}", (Object)type, (Object)content);
        JDAImpl jda = (JDAImpl)this.getJDA();
        try {
            switch (type) {
                case "READY": {
                    this.reconnectTimeoutS = 2;
                    this.api.setStatus(JDA.Status.LOADING_SUBSYSTEMS);
                    this.processingReady = true;
                    this.handleIdentifyRateLimit = false;
                    this.handlers.get("READY").handle(responseTotal, raw);
                    this.sessionId = content.getString("session_id");
                    break;
                }
                case "RESUMED": {
                    this.reconnectTimeoutS = 2;
                    this.sentAuthInfo = true;
                    if (!this.processingReady) {
                        this.initiating = false;
                        this.ready();
                        break;
                    }
                    LOG.debug("Resumed while still processing initial ready");
                    jda.setStatus(JDA.Status.LOADING_SUBSYSTEMS);
                    break;
                }
                default: {
                    long guildId = content.getLong("guild_id", 0L);
                    if (this.api.isUnavailable(guildId) && !type.equals("GUILD_CREATE") && !type.equals("GUILD_DELETE")) {
                        LOG.debug("Ignoring {} for unavailable guild with id {}. JSON: {}", type, guildId, content);
                        break;
                    }
                    SocketHandler handler = this.handlers.get(type);
                    if (handler != null) {
                        handler.handle(responseTotal, raw);
                        break;
                    }
                    LOG.debug("Unrecognized event:\n{}", (Object)raw);
                }
            }
            if (this.api.isRawEvents()) {
                this.api.handleEvent(new RawGatewayEvent(this.api, responseTotal, raw));
            }
        }
        catch (ParsingException ex2) {
            LOG.warn("Got an unexpected Json-parse error. Please redirect following message to the devs:\n\t{}\n\t{} -> {}", ex2.getMessage(), type, content, ex2);
        }
        catch (Exception ex3) {
            LOG.error("Got an unexpected error. Please redirect following message to the devs:\n\t{} -> {}", type, content, ex3);
        }
        if (responseTotal % 100L == 0L) {
            jda.getEventCache().timeout(responseTotal);
        }
    }

    @Override
    public void onTextMessage(WebSocket websocket, byte[] data) {
        this.handleEvent(DataObject.fromJson(data));
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void onBinaryMessage(WebSocket websocket, byte[] binary) throws DataFormatException {
        DataObject message;
        Object object = this.readLock;
        synchronized (object) {
            message = this.handleBinary(binary);
        }
        if (message != null) {
            this.handleEvent(message);
        }
    }

    protected DataObject handleBinary(byte[] binary) throws DataFormatException {
        byte[] data;
        if (this.decompressor == null) {
            if (this.encoding == GatewayEncoding.ETF) {
                return DataObject.fromETF(binary);
            }
            throw new IllegalStateException("Cannot decompress binary message due to unknown compression algorithm: " + (Object)((Object)this.compression));
        }
        try {
            data = this.decompressor.decompress(binary);
            if (data == null) {
                return null;
            }
        }
        catch (DataFormatException e2) {
            this.close(4900, "MALFORMED_PACKAGE");
            throw e2;
        }
        try {
            if (this.encoding == GatewayEncoding.ETF) {
                return DataObject.fromETF(data);
            }
            return DataObject.fromJson(data);
        }
        catch (ParsingException e3) {
            String jsonString = "malformed";
            try {
                jsonString = new String(data, StandardCharsets.UTF_8);
            }
            catch (Exception exception) {
                // empty catch block
            }
            LOG.error("Failed to parse json: {}", (Object)jsonString);
            throw e3;
        }
    }

    @Override
    public void onError(WebSocket websocket, WebSocketException cause) throws Exception {
        if (cause.getCause() instanceof SocketTimeoutException) {
            LOG.debug("Socket timed out");
        } else if (cause.getCause() instanceof IOException) {
            LOG.debug("Encountered I/O error", cause);
        } else {
            LOG.error("There was an error in the WebSocket connection", cause);
            this.api.handleEvent(new ExceptionEvent(this.api, cause, true));
        }
    }

    @Override
    public void onThreadCreated(WebSocket websocket, ThreadType threadType, Thread thread) throws Exception {
        String identifier = this.api.getIdentifierString();
        switch (threadType) {
            case CONNECT_THREAD: {
                thread.setName(identifier + " MainWS-ConnectThread");
                break;
            }
            case FINISH_THREAD: {
                thread.setName(identifier + " MainWS-FinishThread");
                break;
            }
            case READING_THREAD: {
                thread.setName(identifier + " MainWS-ReadThread");
                break;
            }
            case WRITING_THREAD: {
                thread.setName(identifier + " MainWS-WriteThread");
                break;
            }
            default: {
                thread.setName(identifier + " MainWS-" + (Object)((Object)threadType));
            }
        }
    }

    protected void maybeUnlock() {
        if (this.queueLock.isHeldByCurrentThread()) {
            this.queueLock.unlock();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    protected void locked(String comment, Runnable task) {
        try {
            if (!this.queueLock.tryLock() && !this.queueLock.tryLock(10L, TimeUnit.SECONDS)) {
                throw new IllegalStateException("Could not acquire lock in reasonable timeframe! (10 seconds)");
            }
            task.run();
        }
        catch (InterruptedException e2) {
            LOG.error(comment, e2);
        }
        finally {
            this.maybeUnlock();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    protected <T> T locked(String comment, Supplier<T> task) {
        try {
            if (!this.queueLock.tryLock() && !this.queueLock.tryLock(10L, TimeUnit.SECONDS)) {
                throw new IllegalStateException("Could not acquire lock in reasonable timeframe! (10 seconds)");
            }
            T t2 = task.get();
            return t2;
        }
        catch (InterruptedException e2) {
            LOG.error(comment, e2);
            T t3 = null;
            return t3;
        }
        finally {
            this.maybeUnlock();
        }
    }

    public void queueAudioReconnect(VoiceChannel channel) {
        this.locked("There was an error queueing the audio reconnect", () -> {
            long guildId = channel.getGuild().getIdLong();
            ConnectionRequest request = this.queuedAudioConnections.get(guildId);
            if (request == null) {
                request = new ConnectionRequest(channel, ConnectionStage.RECONNECT);
                this.queuedAudioConnections.put(guildId, request);
            } else {
                request.setStage(ConnectionStage.RECONNECT);
            }
            request.setChannel(channel);
        });
    }

    public void queueAudioConnect(VoiceChannel channel) {
        this.locked("There was an error queueing the audio connect", () -> {
            long guildId = channel.getGuild().getIdLong();
            ConnectionRequest request = this.queuedAudioConnections.get(guildId);
            if (request == null) {
                request = new ConnectionRequest(channel, ConnectionStage.CONNECT);
                this.queuedAudioConnections.put(guildId, request);
            } else if (request.getStage() == ConnectionStage.DISCONNECT) {
                request.setStage(ConnectionStage.RECONNECT);
            }
            request.setChannel(channel);
        });
    }

    public void queueAudioDisconnect(Guild guild) {
        this.locked("There was an error queueing the audio disconnect", () -> {
            long guildId = guild.getIdLong();
            ConnectionRequest request = this.queuedAudioConnections.get(guildId);
            if (request == null) {
                this.queuedAudioConnections.put(guildId, new ConnectionRequest(guild));
            } else {
                request.setStage(ConnectionStage.DISCONNECT);
            }
        });
    }

    public ConnectionRequest removeAudioConnection(long guildId) {
        return this.locked("There was an error cleaning up audio connections for deleted guild", () -> this.queuedAudioConnections.remove(guildId));
    }

    public ConnectionRequest updateAudioConnection(long guildId, VoiceChannel connectedChannel) {
        return this.locked("There was an error updating the audio connection", () -> this.updateAudioConnection0(guildId, connectedChannel));
    }

    public ConnectionRequest updateAudioConnection0(long guildId, VoiceChannel connectedChannel) {
        ConnectionRequest request = this.queuedAudioConnections.get(guildId);
        if (request == null) {
            return null;
        }
        ConnectionStage requestStage = request.getStage();
        if (connectedChannel == null) {
            switch (requestStage) {
                case DISCONNECT: {
                    return this.queuedAudioConnections.remove(guildId);
                }
                case RECONNECT: {
                    request.setStage(ConnectionStage.CONNECT);
                    request.setNextAttemptEpoch(System.currentTimeMillis());
                }
            }
            return null;
        }
        if (requestStage == ConnectionStage.CONNECT && request.getChannelId() == connectedChannel.getIdLong()) {
            return this.queuedAudioConnections.remove(guildId);
        }
        return null;
    }

    private SoftReference<ByteArrayOutputStream> newDecompressBuffer() {
        return new SoftReference<ByteArrayOutputStream>(new ByteArrayOutputStream(1024));
    }

    protected ConnectionRequest getNextAudioConnectRequest() {
        if (this.sessionId == null) {
            return null;
        }
        long now = System.currentTimeMillis();
        AtomicReference request = new AtomicReference();
        this.queuedAudioConnections.retainEntries((guildId, audioRequest) -> {
            if (audioRequest.getNextAttemptEpoch() < now) {
                Guild guild = this.api.getGuildById(guildId);
                if (guild == null) {
                    GuildSetupController controller = this.api.getGuildSetupController();
                    if (!controller.isKnown(guildId)) {
                        LOG.debug("Removing audio connection request because the guild has been removed. {}", audioRequest);
                        return false;
                    }
                    return true;
                }
                ConnectionListener listener = guild.getAudioManager().getConnectionListener();
                if (audioRequest.getStage() != ConnectionStage.DISCONNECT) {
                    VoiceChannel channel = guild.getVoiceChannelById(audioRequest.getChannelId());
                    if (channel == null) {
                        if (listener != null) {
                            listener.onStatusChange(ConnectionStatus.DISCONNECTED_CHANNEL_DELETED);
                        }
                        return false;
                    }
                    if (!guild.getSelfMember().hasPermission((GuildChannel)channel, Permission.VOICE_CONNECT)) {
                        if (listener != null) {
                            listener.onStatusChange(ConnectionStatus.DISCONNECTED_LOST_PERMISSION);
                        }
                        return false;
                    }
                }
                request.compareAndSet(null, audioRequest);
            }
            return true;
        });
        return (ConnectionRequest)request.get();
    }

    public Map<String, SocketHandler> getHandlers() {
        return this.handlers;
    }

    public <T extends SocketHandler> T getHandler(String type) {
        try {
            return (T)this.handlers.get(type);
        }
        catch (ClassCastException e2) {
            throw new IllegalStateException(e2);
        }
    }

    protected void setupHandlers() {
        SocketHandler.NOPHandler nopHandler = new SocketHandler.NOPHandler(this.api);
        this.handlers.put("APPLICATION_COMMAND_UPDATE", new ApplicationCommandUpdateHandler(this.api));
        this.handlers.put("APPLICATION_COMMAND_DELETE", new ApplicationCommandDeleteHandler(this.api));
        this.handlers.put("APPLICATION_COMMAND_CREATE", new ApplicationCommandCreateHandler(this.api));
        this.handlers.put("CHANNEL_CREATE", new ChannelCreateHandler(this.api));
        this.handlers.put("CHANNEL_DELETE", new ChannelDeleteHandler(this.api));
        this.handlers.put("CHANNEL_UPDATE", new ChannelUpdateHandler(this.api));
        this.handlers.put("GUILD_BAN_ADD", new GuildBanHandler(this.api, true));
        this.handlers.put("GUILD_BAN_REMOVE", new GuildBanHandler(this.api, false));
        this.handlers.put("GUILD_CREATE", new GuildCreateHandler(this.api));
        this.handlers.put("GUILD_DELETE", new GuildDeleteHandler(this.api));
        this.handlers.put("GUILD_EMOJIS_UPDATE", new GuildEmojisUpdateHandler(this.api));
        this.handlers.put("GUILD_MEMBER_ADD", new GuildMemberAddHandler(this.api));
        this.handlers.put("GUILD_MEMBER_REMOVE", new GuildMemberRemoveHandler(this.api));
        this.handlers.put("GUILD_MEMBER_UPDATE", new GuildMemberUpdateHandler(this.api));
        this.handlers.put("GUILD_MEMBERS_CHUNK", new GuildMembersChunkHandler(this.api));
        this.handlers.put("GUILD_ROLE_CREATE", new GuildRoleCreateHandler(this.api));
        this.handlers.put("GUILD_ROLE_DELETE", new GuildRoleDeleteHandler(this.api));
        this.handlers.put("GUILD_ROLE_UPDATE", new GuildRoleUpdateHandler(this.api));
        this.handlers.put("GUILD_SYNC", new GuildSyncHandler(this.api));
        this.handlers.put("GUILD_UPDATE", new GuildUpdateHandler(this.api));
        this.handlers.put("INTERACTION_CREATE", new InteractionCreateHandler(this.api));
        this.handlers.put("INVITE_CREATE", new InviteCreateHandler(this.api));
        this.handlers.put("INVITE_DELETE", new InviteDeleteHandler(this.api));
        this.handlers.put("MESSAGE_CREATE", new MessageCreateHandler(this.api));
        this.handlers.put("MESSAGE_DELETE", new MessageDeleteHandler(this.api));
        this.handlers.put("MESSAGE_DELETE_BULK", new MessageBulkDeleteHandler(this.api));
        this.handlers.put("MESSAGE_REACTION_ADD", new MessageReactionHandler(this.api, true));
        this.handlers.put("MESSAGE_REACTION_REMOVE", new MessageReactionHandler(this.api, false));
        this.handlers.put("MESSAGE_REACTION_REMOVE_ALL", new MessageReactionBulkRemoveHandler(this.api));
        this.handlers.put("MESSAGE_REACTION_REMOVE_EMOTE", new MessageReactionClearEmoteHandler(this.api));
        this.handlers.put("MESSAGE_UPDATE", new MessageUpdateHandler(this.api));
        this.handlers.put("READY", new ReadyHandler(this.api));
        this.handlers.put("USER_UPDATE", new UserUpdateHandler(this.api));
        this.handlers.put("VOICE_SERVER_UPDATE", new VoiceServerUpdateHandler(this.api));
        this.handlers.put("VOICE_STATE_UPDATE", new VoiceStateUpdateHandler(this.api));
        this.handlers.put("PRESENCE_UPDATE", new PresenceUpdateHandler(this.api));
        this.handlers.put("TYPING_START", new TypingStartHandler(this.api));
        this.handlers.put("CHANNEL_PINS_ACK", nopHandler);
        this.handlers.put("CHANNEL_PINS_UPDATE", nopHandler);
        this.handlers.put("GUILD_INTEGRATIONS_UPDATE", nopHandler);
        this.handlers.put("PRESENCES_REPLACE", nopHandler);
        this.handlers.put("WEBHOOKS_UPDATE", nopHandler);
    }

    protected class ReconnectNode
    extends ConnectNode {
        protected ReconnectNode() {
        }

        @Override
        public boolean isReconnect() {
            return true;
        }

        @Override
        public void run(boolean isLast) throws InterruptedException {
            if (WebSocketClient.this.shutdown) {
                return;
            }
            WebSocketClient.this.reconnect(true);
            if (isLast) {
                return;
            }
            try {
                WebSocketClient.this.api.awaitStatus(JDA.Status.LOADING_SUBSYSTEMS, JDA.Status.RECONNECT_QUEUED);
            }
            catch (IllegalStateException ex2) {
                WebSocketClient.this.close();
                LOG.debug("Shutdown while trying to reconnect");
            }
        }

        public int hashCode() {
            return Objects.hash("R", this.getJDA());
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof ReconnectNode)) {
                return false;
            }
            ReconnectNode node = (ReconnectNode)obj;
            return node.getJDA().equals(this.getJDA());
        }
    }

    protected class StartingNode
    extends ConnectNode {
        protected StartingNode() {
        }

        @Override
        public boolean isReconnect() {
            return false;
        }

        @Override
        public void run(boolean isLast) throws InterruptedException {
            if (WebSocketClient.this.shutdown) {
                return;
            }
            WebSocketClient.this.setupSendingThread();
            WebSocketClient.this.connect();
            if (isLast) {
                return;
            }
            try {
                WebSocketClient.this.api.awaitStatus(JDA.Status.LOADING_SUBSYSTEMS, JDA.Status.RECONNECT_QUEUED);
            }
            catch (IllegalStateException ex2) {
                WebSocketClient.this.close();
                LOG.debug("Shutdown while trying to connect");
            }
        }

        public int hashCode() {
            return Objects.hash("C", this.getJDA());
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof StartingNode)) {
                return false;
            }
            StartingNode node = (StartingNode)obj;
            return node.getJDA().equals(this.getJDA());
        }
    }

    protected abstract class ConnectNode
    implements SessionController.SessionConnectNode {
        protected ConnectNode() {
        }

        @Override
        @Nonnull
        public JDA getJDA() {
            return WebSocketClient.this.api;
        }

        @Override
        @Nonnull
        public JDA.ShardInfo getShardInfo() {
            return WebSocketClient.this.api.getShardInfo();
        }
    }
}

