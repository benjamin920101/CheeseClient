/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api;

import com.neovisionaries.ws.client.WebSocketFactory;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.EnumSet;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledExecutorService;
import java.util.stream.Collectors;
import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.security.auth.login.LoginException;
import net.dv8tion.jda.annotations.DeprecatedSince;
import net.dv8tion.jda.annotations.ReplaceWith;
import net.dv8tion.jda.api.GatewayEncoding;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.audio.factory.IAudioSendFactory;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.hooks.IEventManager;
import net.dv8tion.jda.api.hooks.VoiceDispatchInterceptor;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.ChunkingFilter;
import net.dv8tion.jda.api.utils.Compression;
import net.dv8tion.jda.api.utils.ConcurrentSessionController;
import net.dv8tion.jda.api.utils.MemberCachePolicy;
import net.dv8tion.jda.api.utils.SessionController;
import net.dv8tion.jda.api.utils.cache.CacheFlag;
import net.dv8tion.jda.internal.JDAImpl;
import net.dv8tion.jda.internal.managers.PresenceImpl;
import net.dv8tion.jda.internal.utils.Checks;
import net.dv8tion.jda.internal.utils.IOUtil;
import net.dv8tion.jda.internal.utils.config.AuthorizationConfig;
import net.dv8tion.jda.internal.utils.config.MetaConfig;
import net.dv8tion.jda.internal.utils.config.SessionConfig;
import net.dv8tion.jda.internal.utils.config.ThreadingConfig;
import net.dv8tion.jda.internal.utils.config.flags.ConfigFlag;
import okhttp3.OkHttpClient;

public class JDABuilder {
    public static final int GUILD_SUBSCRIPTIONS = GatewayIntent.getRaw(GatewayIntent.GUILD_MEMBERS, GatewayIntent.GUILD_PRESENCES, GatewayIntent.GUILD_MESSAGE_TYPING);
    protected final List<Object> listeners = new LinkedList<Object>();
    protected final EnumSet<CacheFlag> automaticallyDisabled = EnumSet.noneOf(CacheFlag.class);
    protected ScheduledExecutorService rateLimitPool = null;
    protected boolean shutdownRateLimitPool = true;
    protected ScheduledExecutorService mainWsPool = null;
    protected boolean shutdownMainWsPool = true;
    protected ExecutorService callbackPool = null;
    protected boolean shutdownCallbackPool = true;
    protected ExecutorService eventPool = null;
    protected boolean shutdownEventPool = true;
    protected ScheduledExecutorService audioPool = null;
    protected boolean shutdownAudioPool = true;
    protected EnumSet<CacheFlag> cacheFlags = EnumSet.allOf(CacheFlag.class);
    protected ConcurrentMap<String, String> contextMap = null;
    protected SessionController controller = null;
    protected VoiceDispatchInterceptor voiceDispatchInterceptor = null;
    protected OkHttpClient.Builder httpClientBuilder = null;
    protected OkHttpClient httpClient = null;
    protected WebSocketFactory wsFactory = null;
    protected String token = null;
    protected IEventManager eventManager = null;
    protected IAudioSendFactory audioSendFactory = null;
    protected JDA.ShardInfo shardInfo = null;
    protected Compression compression = Compression.ZLIB;
    protected Activity activity = null;
    protected OnlineStatus status = OnlineStatus.ONLINE;
    protected boolean idle = false;
    protected int maxReconnectDelay = 900;
    protected int largeThreshold = 250;
    protected int maxBufferSize = 2048;
    protected int intents = -1;
    protected EnumSet<ConfigFlag> flags = ConfigFlag.getDefault();
    protected ChunkingFilter chunkingFilter = ChunkingFilter.ALL;
    protected MemberCachePolicy memberCachePolicy = MemberCachePolicy.ALL;
    protected GatewayEncoding encoding = GatewayEncoding.JSON;

    private JDABuilder(@Nullable String token, int intents) {
        this.token = token;
        this.intents = 1 | intents;
    }

    @Nonnull
    @CheckReturnValue
    public static JDABuilder createDefault(@Nullable String token) {
        return new JDABuilder(token, GatewayIntent.DEFAULT).applyDefault();
    }

    @Nonnull
    @CheckReturnValue
    public static JDABuilder createDefault(@Nullable String token, @Nonnull GatewayIntent intent, GatewayIntent ... intents) {
        Checks.notNull((Object)intent, "GatewayIntent");
        Checks.noneNull((Object[])intents, "GatewayIntent");
        return JDABuilder.createDefault(token, EnumSet.of(intent, intents));
    }

    @Nonnull
    @CheckReturnValue
    public static JDABuilder createDefault(@Nullable String token, @Nonnull Collection<GatewayIntent> intents) {
        return JDABuilder.create(token, intents).applyDefault();
    }

    private JDABuilder applyDefault() {
        return this.setMemberCachePolicy(MemberCachePolicy.DEFAULT).setChunkingFilter(ChunkingFilter.NONE).disableCache(CacheFlag.getPrivileged()).setLargeThreshold(250);
    }

    @Nonnull
    @CheckReturnValue
    public static JDABuilder createLight(@Nullable String token) {
        return new JDABuilder(token, GatewayIntent.DEFAULT).applyLight();
    }

    @Nonnull
    @CheckReturnValue
    public static JDABuilder createLight(@Nullable String token, @Nonnull GatewayIntent intent, GatewayIntent ... intents) {
        Checks.notNull((Object)intent, "GatewayIntent");
        Checks.noneNull((Object[])intents, "GatewayIntent");
        return JDABuilder.createLight(token, EnumSet.of(intent, intents));
    }

    @Nonnull
    @CheckReturnValue
    public static JDABuilder createLight(@Nullable String token, @Nonnull Collection<GatewayIntent> intents) {
        return JDABuilder.create(token, intents).applyLight();
    }

    private JDABuilder applyLight() {
        return this.setMemberCachePolicy(MemberCachePolicy.NONE).setChunkingFilter(ChunkingFilter.NONE).disableCache(EnumSet.allOf(CacheFlag.class)).setLargeThreshold(50);
    }

    @Nonnull
    @CheckReturnValue
    public static JDABuilder create(@Nonnull GatewayIntent intent, GatewayIntent ... intents) {
        return JDABuilder.create(null, intent, intents);
    }

    @Nonnull
    @CheckReturnValue
    public static JDABuilder create(@Nonnull Collection<GatewayIntent> intents) {
        return JDABuilder.create(null, intents);
    }

    @Nonnull
    @CheckReturnValue
    public static JDABuilder create(@Nullable String token, @Nonnull GatewayIntent intent, GatewayIntent ... intents) {
        return new JDABuilder(token, GatewayIntent.getRaw(intent, intents)).applyIntents();
    }

    @Nonnull
    @CheckReturnValue
    public static JDABuilder create(@Nullable String token, @Nonnull Collection<GatewayIntent> intents) {
        return new JDABuilder(token, GatewayIntent.getRaw(intents)).applyIntents();
    }

    private JDABuilder applyIntents() {
        EnumSet<CacheFlag> disabledCache = EnumSet.allOf(CacheFlag.class);
        for (CacheFlag flag : CacheFlag.values()) {
            GatewayIntent requiredIntent = flag.getRequiredIntent();
            if (requiredIntent != null && (requiredIntent.getRawValue() & this.intents) == 0) continue;
            disabledCache.remove((Object)flag);
        }
        boolean enableMembers = (this.intents & GatewayIntent.GUILD_MEMBERS.getRawValue()) != 0;
        return this.setChunkingFilter(enableMembers ? ChunkingFilter.ALL : ChunkingFilter.NONE).setMemberCachePolicy(enableMembers ? MemberCachePolicy.ALL : MemberCachePolicy.DEFAULT).setDisabledCache(disabledCache);
    }

    private JDABuilder setDisabledCache(EnumSet<CacheFlag> flags) {
        this.disableCache(flags);
        this.automaticallyDisabled.addAll(flags);
        return this;
    }

    @Nonnull
    public JDABuilder setGatewayEncoding(@Nonnull GatewayEncoding encoding) {
        Checks.notNull((Object)encoding, "GatewayEncoding");
        this.encoding = encoding;
        return this;
    }

    @Nonnull
    public JDABuilder setRawEventsEnabled(boolean enable) {
        return this.setFlag(ConfigFlag.RAW_EVENTS, enable);
    }

    @Nonnull
    public JDABuilder setRelativeRateLimit(boolean enable) {
        return this.setFlag(ConfigFlag.USE_RELATIVE_RATELIMIT, enable);
    }

    @Nonnull
    @Deprecated
    @ReplaceWith(value="enableCache(flags) and disableCache(flags)")
    @DeprecatedSince(value="4.2.0")
    public JDABuilder setEnabledCacheFlags(@Nullable EnumSet<CacheFlag> flags) {
        this.cacheFlags = flags == null ? EnumSet.noneOf(CacheFlag.class) : EnumSet.copyOf(flags);
        return this;
    }

    @Nonnull
    public JDABuilder enableCache(@Nonnull Collection<CacheFlag> flags) {
        Checks.noneNull(flags, "CacheFlags");
        this.cacheFlags.addAll(flags);
        return this;
    }

    @Nonnull
    public JDABuilder enableCache(@Nonnull CacheFlag flag, CacheFlag ... flags) {
        Checks.notNull((Object)flag, "CacheFlag");
        Checks.noneNull((Object[])flags, "CacheFlag");
        this.cacheFlags.addAll(EnumSet.of(flag, flags));
        return this;
    }

    @Nonnull
    @Deprecated
    @ReplaceWith(value="enableCache(flags) and disableCache(flags)")
    @DeprecatedSince(value="4.2.0")
    public JDABuilder setDisabledCacheFlags(@Nullable EnumSet<CacheFlag> flags) {
        return this.setEnabledCacheFlags(flags == null ? EnumSet.allOf(CacheFlag.class) : EnumSet.complementOf(flags));
    }

    @Nonnull
    public JDABuilder disableCache(@Nonnull Collection<CacheFlag> flags) {
        Checks.noneNull(flags, "CacheFlags");
        this.automaticallyDisabled.removeAll(flags);
        this.cacheFlags.removeAll(flags);
        return this;
    }

    @Nonnull
    public JDABuilder disableCache(@Nonnull CacheFlag flag, CacheFlag ... flags) {
        Checks.notNull((Object)flag, "CacheFlag");
        Checks.noneNull((Object[])flags, "CacheFlag");
        return this.disableCache(EnumSet.of(flag, flags));
    }

    @Nonnull
    public JDABuilder setMemberCachePolicy(@Nullable MemberCachePolicy policy) {
        this.memberCachePolicy = policy == null ? MemberCachePolicy.ALL : policy;
        return this;
    }

    @Nonnull
    public JDABuilder setContextMap(@Nullable ConcurrentMap<String, String> map) {
        this.contextMap = map;
        if (map != null) {
            this.setContextEnabled(true);
        }
        return this;
    }

    @Nonnull
    public JDABuilder setContextEnabled(boolean enable) {
        return this.setFlag(ConfigFlag.MDC_CONTEXT, enable);
    }

    @Nonnull
    public JDABuilder setCompression(@Nonnull Compression compression) {
        Checks.notNull((Object)compression, "Compression");
        this.compression = compression;
        return this;
    }

    @Nonnull
    public JDABuilder setRequestTimeoutRetry(boolean retryOnTimeout) {
        return this.setFlag(ConfigFlag.RETRY_TIMEOUT, retryOnTimeout);
    }

    @Nonnull
    public JDABuilder setToken(@Nullable String token) {
        this.token = token;
        return this;
    }

    @Nonnull
    public JDABuilder setHttpClientBuilder(@Nullable OkHttpClient.Builder builder) {
        this.httpClientBuilder = builder;
        return this;
    }

    @Nonnull
    public JDABuilder setHttpClient(@Nullable OkHttpClient client) {
        this.httpClient = client;
        return this;
    }

    @Nonnull
    public JDABuilder setWebsocketFactory(@Nullable WebSocketFactory factory) {
        this.wsFactory = factory;
        return this;
    }

    @Nonnull
    public JDABuilder setRateLimitPool(@Nullable ScheduledExecutorService pool) {
        return this.setRateLimitPool(pool, pool == null);
    }

    @Nonnull
    public JDABuilder setRateLimitPool(@Nullable ScheduledExecutorService pool, boolean automaticShutdown) {
        this.rateLimitPool = pool;
        this.shutdownRateLimitPool = automaticShutdown;
        return this;
    }

    @Nonnull
    public JDABuilder setGatewayPool(@Nullable ScheduledExecutorService pool) {
        return this.setGatewayPool(pool, pool == null);
    }

    @Nonnull
    public JDABuilder setGatewayPool(@Nullable ScheduledExecutorService pool, boolean automaticShutdown) {
        this.mainWsPool = pool;
        this.shutdownMainWsPool = automaticShutdown;
        return this;
    }

    @Nonnull
    public JDABuilder setCallbackPool(@Nullable ExecutorService executor) {
        return this.setCallbackPool(executor, executor == null);
    }

    @Nonnull
    public JDABuilder setCallbackPool(@Nullable ExecutorService executor, boolean automaticShutdown) {
        this.callbackPool = executor;
        this.shutdownCallbackPool = automaticShutdown;
        return this;
    }

    @Nonnull
    public JDABuilder setEventPool(@Nullable ExecutorService executor) {
        return this.setEventPool(executor, executor == null);
    }

    @Nonnull
    public JDABuilder setEventPool(@Nullable ExecutorService executor, boolean automaticShutdown) {
        this.eventPool = executor;
        this.shutdownEventPool = automaticShutdown;
        return this;
    }

    @Nonnull
    public JDABuilder setAudioPool(@Nullable ScheduledExecutorService pool) {
        return this.setAudioPool(pool, pool == null);
    }

    @Nonnull
    public JDABuilder setAudioPool(@Nullable ScheduledExecutorService pool, boolean automaticShutdown) {
        this.audioPool = pool;
        this.shutdownAudioPool = automaticShutdown;
        return this;
    }

    @Nonnull
    public JDABuilder setBulkDeleteSplittingEnabled(boolean enabled) {
        return this.setFlag(ConfigFlag.BULK_DELETE_SPLIT, enabled);
    }

    @Nonnull
    public JDABuilder setEnableShutdownHook(boolean enable) {
        return this.setFlag(ConfigFlag.SHUTDOWN_HOOK, enable);
    }

    @Nonnull
    public JDABuilder setAutoReconnect(boolean autoReconnect) {
        return this.setFlag(ConfigFlag.AUTO_RECONNECT, autoReconnect);
    }

    @Nonnull
    public JDABuilder setEventManager(@Nullable IEventManager manager) {
        this.eventManager = manager;
        return this;
    }

    @Nonnull
    public JDABuilder setAudioSendFactory(@Nullable IAudioSendFactory factory) {
        this.audioSendFactory = factory;
        return this;
    }

    @Nonnull
    public JDABuilder setIdle(boolean idle) {
        this.idle = idle;
        return this;
    }

    @Nonnull
    public JDABuilder setActivity(@Nullable Activity activity) {
        this.activity = activity;
        return this;
    }

    @Nonnull
    public JDABuilder setStatus(@Nonnull OnlineStatus status) {
        if (status == null || status == OnlineStatus.UNKNOWN) {
            throw new IllegalArgumentException("OnlineStatus cannot be null or unknown!");
        }
        this.status = status;
        return this;
    }

    @Nonnull
    public JDABuilder addEventListeners(Object ... listeners) {
        Checks.noneNull(listeners, "listeners");
        Collections.addAll(this.listeners, listeners);
        return this;
    }

    @Nonnull
    public JDABuilder removeEventListeners(Object ... listeners) {
        Checks.noneNull(listeners, "listeners");
        this.listeners.removeAll(Arrays.asList(listeners));
        return this;
    }

    @Nonnull
    public JDABuilder setMaxReconnectDelay(int maxReconnectDelay) {
        Checks.check(maxReconnectDelay >= 32, "Max reconnect delay must be 32 seconds or greater. You provided %d.", (Object)maxReconnectDelay);
        this.maxReconnectDelay = maxReconnectDelay;
        return this;
    }

    @Nonnull
    public JDABuilder useSharding(int shardId, int shardTotal) {
        Checks.notNegative(shardId, "Shard ID");
        Checks.positive(shardTotal, "Shard Total");
        Checks.check(shardId < shardTotal, "The shard ID must be lower than the shardTotal! Shard IDs are 0-based.");
        this.shardInfo = new JDA.ShardInfo(shardId, shardTotal);
        return this;
    }

    @Nonnull
    public JDABuilder setSessionController(@Nullable SessionController controller) {
        this.controller = controller;
        return this;
    }

    @Nonnull
    public JDABuilder setVoiceDispatchInterceptor(@Nullable VoiceDispatchInterceptor interceptor) {
        this.voiceDispatchInterceptor = interceptor;
        return this;
    }

    @Nonnull
    public JDABuilder setChunkingFilter(@Nullable ChunkingFilter filter) {
        this.chunkingFilter = filter == null ? ChunkingFilter.ALL : filter;
        return this;
    }

    @Nonnull
    @Deprecated
    @ReplaceWith(value="setDisabledIntents(...).setMemberCachePolicy(...)")
    @DeprecatedSince(value="4.2.0")
    public JDABuilder setGuildSubscriptionsEnabled(boolean enabled) {
        if (!enabled) {
            this.setMemberCachePolicy(MemberCachePolicy.VOICE);
            this.intents &= ~GUILD_SUBSCRIPTIONS;
        }
        return this;
    }

    @Nonnull
    public JDABuilder setDisabledIntents(@Nonnull GatewayIntent intent, GatewayIntent ... intents) {
        Checks.notNull((Object)intent, "Intents");
        Checks.noneNull((Object[])intents, "Intents");
        return this.setDisabledIntents(EnumSet.of(intent, intents));
    }

    @Nonnull
    public JDABuilder setDisabledIntents(@Nullable Collection<GatewayIntent> intents) {
        this.intents = GatewayIntent.ALL_INTENTS;
        if (intents != null) {
            this.intents &= ~GatewayIntent.getRaw(intents);
        }
        return this;
    }

    @Nonnull
    public JDABuilder disableIntents(@Nonnull Collection<GatewayIntent> intents) {
        Checks.noneNull(intents, "GatewayIntent");
        int raw = GatewayIntent.getRaw(intents);
        this.intents &= ~raw;
        return this;
    }

    @Nonnull
    public JDABuilder disableIntents(@Nonnull GatewayIntent intent, GatewayIntent ... intents) {
        Checks.notNull((Object)intent, "GatewayIntent");
        Checks.noneNull((Object[])intents, "GatewayIntent");
        int raw = GatewayIntent.getRaw(intent, intents);
        this.intents &= ~raw;
        return this;
    }

    @Nonnull
    public JDABuilder setEnabledIntents(@Nonnull GatewayIntent intent, GatewayIntent ... intents) {
        Checks.notNull((Object)intent, "Intents");
        Checks.noneNull((Object[])intents, "Intents");
        EnumSet<GatewayIntent[]> set = EnumSet.of(intent, intents);
        return this.setDisabledIntents(EnumSet.complementOf(set));
    }

    @Nonnull
    public JDABuilder setEnabledIntents(@Nullable Collection<GatewayIntent> intents) {
        if (intents == null || intents.isEmpty()) {
            this.setDisabledIntents(EnumSet.allOf(GatewayIntent.class));
        } else if (intents instanceof EnumSet) {
            this.setDisabledIntents(EnumSet.complementOf((EnumSet)intents));
        } else {
            this.setDisabledIntents(EnumSet.complementOf(EnumSet.copyOf(intents)));
        }
        return this;
    }

    @Nonnull
    public JDABuilder enableIntents(@Nonnull Collection<GatewayIntent> intents) {
        Checks.noneNull(intents, "GatewayIntent");
        int raw = GatewayIntent.getRaw(intents);
        this.intents |= raw;
        return this;
    }

    @Nonnull
    public JDABuilder enableIntents(@Nonnull GatewayIntent intent, GatewayIntent ... intents) {
        Checks.notNull((Object)intent, "GatewayIntent");
        Checks.noneNull((Object[])intents, "GatewayIntent");
        int raw = GatewayIntent.getRaw(intent, intents);
        this.intents |= raw;
        return this;
    }

    @Nonnull
    public JDABuilder setLargeThreshold(int threshold) {
        this.largeThreshold = Math.max(50, Math.min(250, threshold));
        return this;
    }

    @Nonnull
    public JDABuilder setMaxBufferSize(int bufferSize) {
        Checks.notNegative(bufferSize, "The buffer size");
        this.maxBufferSize = bufferSize;
        return this;
    }

    @Nonnull
    public JDA build() throws LoginException {
        WebSocketFactory wsFactory;
        this.checkIntents();
        OkHttpClient httpClient = this.httpClient;
        if (httpClient == null) {
            if (this.httpClientBuilder == null) {
                this.httpClientBuilder = IOUtil.newHttpClientBuilder();
            }
            httpClient = this.httpClientBuilder.build();
        }
        WebSocketFactory webSocketFactory = wsFactory = this.wsFactory == null ? new WebSocketFactory() : this.wsFactory;
        if (this.controller == null && this.shardInfo != null) {
            this.controller = new ConcurrentSessionController();
        }
        AuthorizationConfig authConfig = new AuthorizationConfig(this.token);
        ThreadingConfig threadingConfig = new ThreadingConfig();
        threadingConfig.setCallbackPool(this.callbackPool, this.shutdownCallbackPool);
        threadingConfig.setGatewayPool(this.mainWsPool, this.shutdownMainWsPool);
        threadingConfig.setRateLimitPool(this.rateLimitPool, this.shutdownRateLimitPool);
        threadingConfig.setEventPool(this.eventPool, this.shutdownEventPool);
        threadingConfig.setAudioPool(this.audioPool, this.shutdownAudioPool);
        SessionConfig sessionConfig = new SessionConfig(this.controller, httpClient, wsFactory, this.voiceDispatchInterceptor, this.flags, this.maxReconnectDelay, this.largeThreshold);
        MetaConfig metaConfig = new MetaConfig(this.maxBufferSize, this.contextMap, this.cacheFlags, this.flags);
        JDAImpl jda = new JDAImpl(authConfig, sessionConfig, threadingConfig, metaConfig);
        jda.setMemberCachePolicy(this.memberCachePolicy);
        if ((this.intents & GatewayIntent.GUILD_MEMBERS.getRawValue()) == 0) {
            jda.setChunkingFilter(ChunkingFilter.NONE);
        } else {
            jda.setChunkingFilter(this.chunkingFilter);
        }
        if (this.eventManager != null) {
            jda.setEventManager(this.eventManager);
        }
        if (this.audioSendFactory != null) {
            jda.setAudioSendFactory(this.audioSendFactory);
        }
        this.listeners.forEach(xva$0 -> jda.addEventListener(xva$0));
        jda.setStatus(JDA.Status.INITIALIZED);
        ((PresenceImpl)jda.getPresence()).setCacheActivity(this.activity).setCacheIdle(this.idle).setCacheStatus(this.status);
        jda.login(this.shardInfo, this.compression, true, this.intents, this.encoding);
        return jda;
    }

    private JDABuilder setFlag(ConfigFlag flag, boolean enable) {
        if (enable) {
            this.flags.add(flag);
        } else {
            this.flags.remove((Object)flag);
        }
        return this;
    }

    private void checkIntents() {
        boolean membersIntent;
        boolean bl2 = membersIntent = (this.intents & GatewayIntent.GUILD_MEMBERS.getRawValue()) != 0;
        if (!membersIntent && this.memberCachePolicy == MemberCachePolicy.ALL) {
            throw new IllegalStateException("Cannot use MemberCachePolicy.ALL without GatewayIntent.GUILD_MEMBERS enabled!");
        }
        if (!membersIntent && this.chunkingFilter != ChunkingFilter.NONE) {
            JDAImpl.LOG.warn("Member chunking is disabled due to missing GUILD_MEMBERS intent.");
        }
        if (!this.automaticallyDisabled.isEmpty()) {
            JDAImpl.LOG.warn("Automatically disabled CacheFlags due to missing intents");
            this.automaticallyDisabled.stream().map(it2 -> "Disabled CacheFlag." + (Object)it2 + " (missing GatewayIntent." + (Object)((Object)it2.getRequiredIntent()) + ")").forEach(JDAImpl.LOG::warn);
            JDAImpl.LOG.warn("You can manually disable these flags to remove this warning by using disableCache({}) on your JDABuilder", (Object)this.automaticallyDisabled.stream().map(it2 -> "CacheFlag." + (Object)it2).collect(Collectors.joining(", ")));
            this.automaticallyDisabled.clear();
        }
        if (this.cacheFlags.isEmpty()) {
            return;
        }
        EnumSet<GatewayIntent> providedIntents = GatewayIntent.getIntents(this.intents);
        for (CacheFlag flag : this.cacheFlags) {
            GatewayIntent intent = flag.getRequiredIntent();
            if (intent == null || providedIntents.contains((Object)intent)) continue;
            throw new IllegalArgumentException("Cannot use CacheFlag." + (Object)((Object)flag) + " without GatewayIntent." + (Object)((Object)intent) + "!");
        }
    }
}

