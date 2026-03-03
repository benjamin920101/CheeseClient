/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.sharding;

import com.neovisionaries.ws.client.WebSocketFactory;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.function.IntFunction;
import java.util.stream.Collectors;
import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.security.auth.login.LoginException;
import net.dv8tion.jda.annotations.DeprecatedSince;
import net.dv8tion.jda.annotations.ReplaceWith;
import net.dv8tion.jda.api.GatewayEncoding;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.audio.factory.IAudioSendFactory;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.hooks.IEventManager;
import net.dv8tion.jda.api.hooks.VoiceDispatchInterceptor;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.sharding.DefaultShardManager;
import net.dv8tion.jda.api.sharding.ShardManager;
import net.dv8tion.jda.api.sharding.ThreadPoolProvider;
import net.dv8tion.jda.api.utils.ChunkingFilter;
import net.dv8tion.jda.api.utils.Compression;
import net.dv8tion.jda.api.utils.MemberCachePolicy;
import net.dv8tion.jda.api.utils.SessionController;
import net.dv8tion.jda.api.utils.cache.CacheFlag;
import net.dv8tion.jda.internal.JDAImpl;
import net.dv8tion.jda.internal.utils.Checks;
import net.dv8tion.jda.internal.utils.config.flags.ConfigFlag;
import net.dv8tion.jda.internal.utils.config.flags.ShardingConfigFlag;
import net.dv8tion.jda.internal.utils.config.sharding.EventConfig;
import net.dv8tion.jda.internal.utils.config.sharding.PresenceProviderConfig;
import net.dv8tion.jda.internal.utils.config.sharding.ShardingConfig;
import net.dv8tion.jda.internal.utils.config.sharding.ShardingMetaConfig;
import net.dv8tion.jda.internal.utils.config.sharding.ShardingSessionConfig;
import net.dv8tion.jda.internal.utils.config.sharding.ThreadingProviderConfig;
import okhttp3.OkHttpClient;

public class DefaultShardManagerBuilder {
    protected final List<Object> listeners = new ArrayList<Object>();
    protected final List<IntFunction<Object>> listenerProviders = new ArrayList<IntFunction<Object>>();
    protected final EnumSet<CacheFlag> automaticallyDisabled = EnumSet.noneOf(CacheFlag.class);
    protected SessionController sessionController = null;
    protected VoiceDispatchInterceptor voiceDispatchInterceptor = null;
    protected EnumSet<CacheFlag> cacheFlags = EnumSet.allOf(CacheFlag.class);
    protected EnumSet<ConfigFlag> flags = ConfigFlag.getDefault();
    protected EnumSet<ShardingConfigFlag> shardingFlags = ShardingConfigFlag.getDefault();
    protected Compression compression = Compression.ZLIB;
    protected GatewayEncoding encoding = GatewayEncoding.JSON;
    protected int shardsTotal = -1;
    protected int maxReconnectDelay = 900;
    protected int largeThreshold = 250;
    protected int maxBufferSize = 2048;
    protected int intents = -1;
    protected String token = null;
    protected IntFunction<Boolean> idleProvider = null;
    protected IntFunction<OnlineStatus> statusProvider = null;
    protected IntFunction<? extends Activity> activityProvider = null;
    protected IntFunction<? extends ConcurrentMap<String, String>> contextProvider = null;
    protected IntFunction<? extends IEventManager> eventManagerProvider = null;
    protected ThreadPoolProvider<? extends ScheduledExecutorService> rateLimitPoolProvider = null;
    protected ThreadPoolProvider<? extends ScheduledExecutorService> gatewayPoolProvider = null;
    protected ThreadPoolProvider<? extends ExecutorService> callbackPoolProvider = null;
    protected ThreadPoolProvider<? extends ExecutorService> eventPoolProvider = null;
    protected ThreadPoolProvider<? extends ScheduledExecutorService> audioPoolProvider = null;
    protected Collection<Integer> shards = null;
    protected OkHttpClient.Builder httpClientBuilder = null;
    protected OkHttpClient httpClient = null;
    protected WebSocketFactory wsFactory = null;
    protected IAudioSendFactory audioSendFactory = null;
    protected ThreadFactory threadFactory = null;
    protected ChunkingFilter chunkingFilter;
    protected MemberCachePolicy memberCachePolicy = MemberCachePolicy.ALL;

    private DefaultShardManagerBuilder(@Nullable String token, int intents) {
        this.token = token;
        this.intents = 1 | intents;
    }

    @Nonnull
    @CheckReturnValue
    public static DefaultShardManagerBuilder createDefault(@Nullable String token) {
        return new DefaultShardManagerBuilder(token, GatewayIntent.DEFAULT).applyDefault();
    }

    @Nonnull
    @CheckReturnValue
    public static DefaultShardManagerBuilder createDefault(@Nullable String token, @Nonnull GatewayIntent intent, GatewayIntent ... intents) {
        Checks.notNull((Object)intent, "GatewayIntent");
        Checks.noneNull((Object[])intents, "GatewayIntent");
        return DefaultShardManagerBuilder.createDefault(token, EnumSet.of(intent, intents));
    }

    @Nonnull
    @CheckReturnValue
    public static DefaultShardManagerBuilder createDefault(@Nullable String token, @Nonnull Collection<GatewayIntent> intents) {
        return DefaultShardManagerBuilder.create(token, intents).applyDefault();
    }

    private DefaultShardManagerBuilder applyDefault() {
        return this.setMemberCachePolicy(MemberCachePolicy.DEFAULT).setChunkingFilter(ChunkingFilter.NONE).disableCache(CacheFlag.getPrivileged()).setLargeThreshold(250);
    }

    @Nonnull
    @CheckReturnValue
    public static DefaultShardManagerBuilder createLight(@Nullable String token) {
        return new DefaultShardManagerBuilder(token, GatewayIntent.DEFAULT).applyLight();
    }

    @Nonnull
    @CheckReturnValue
    public static DefaultShardManagerBuilder createLight(@Nullable String token, @Nonnull GatewayIntent intent, GatewayIntent ... intents) {
        Checks.notNull((Object)intent, "GatewayIntent");
        Checks.noneNull((Object[])intents, "GatewayIntent");
        return DefaultShardManagerBuilder.createLight(token, EnumSet.of(intent, intents));
    }

    @Nonnull
    @CheckReturnValue
    public static DefaultShardManagerBuilder createLight(@Nullable String token, @Nonnull Collection<GatewayIntent> intents) {
        return DefaultShardManagerBuilder.create(token, intents).applyLight();
    }

    private DefaultShardManagerBuilder applyLight() {
        return this.setMemberCachePolicy(MemberCachePolicy.NONE).setChunkingFilter(ChunkingFilter.NONE).disableCache(EnumSet.allOf(CacheFlag.class)).setLargeThreshold(50);
    }

    @Nonnull
    @CheckReturnValue
    public static DefaultShardManagerBuilder create(@Nonnull GatewayIntent intent, GatewayIntent ... intents) {
        return DefaultShardManagerBuilder.create(null, intent, intents);
    }

    @Nonnull
    @CheckReturnValue
    public static DefaultShardManagerBuilder create(@Nonnull Collection<GatewayIntent> intents) {
        return DefaultShardManagerBuilder.create(null, intents);
    }

    @Nonnull
    @CheckReturnValue
    public static DefaultShardManagerBuilder create(@Nullable String token, @Nonnull GatewayIntent intent, GatewayIntent ... intents) {
        return new DefaultShardManagerBuilder(token, GatewayIntent.getRaw(intent, intents)).applyIntents();
    }

    @Nonnull
    @CheckReturnValue
    public static DefaultShardManagerBuilder create(@Nullable String token, @Nonnull Collection<GatewayIntent> intents) {
        return new DefaultShardManagerBuilder(token, GatewayIntent.getRaw(intents)).applyIntents();
    }

    private DefaultShardManagerBuilder applyIntents() {
        EnumSet<CacheFlag> disabledCache = EnumSet.allOf(CacheFlag.class);
        for (CacheFlag flag : CacheFlag.values()) {
            GatewayIntent requiredIntent = flag.getRequiredIntent();
            if (requiredIntent != null && (requiredIntent.getRawValue() & this.intents) == 0) continue;
            disabledCache.remove((Object)flag);
        }
        boolean enableMembers = (this.intents & GatewayIntent.GUILD_MEMBERS.getRawValue()) != 0;
        return this.setChunkingFilter(enableMembers ? ChunkingFilter.ALL : ChunkingFilter.NONE).setMemberCachePolicy(enableMembers ? MemberCachePolicy.ALL : MemberCachePolicy.DEFAULT).setDisabledCache(disabledCache);
    }

    private DefaultShardManagerBuilder setDisabledCache(EnumSet<CacheFlag> flags) {
        this.disableCache(flags);
        this.automaticallyDisabled.addAll(flags);
        return this;
    }

    @Nonnull
    public DefaultShardManagerBuilder setGatewayEncoding(@Nonnull GatewayEncoding encoding) {
        Checks.notNull((Object)encoding, "GatewayEncoding");
        this.encoding = encoding;
        return this;
    }

    @Nonnull
    public DefaultShardManagerBuilder setRawEventsEnabled(boolean enable) {
        return this.setFlag(ConfigFlag.RAW_EVENTS, enable);
    }

    @Nonnull
    public DefaultShardManagerBuilder setRelativeRateLimit(boolean enable) {
        return this.setFlag(ConfigFlag.USE_RELATIVE_RATELIMIT, enable);
    }

    @Nonnull
    @Deprecated
    @ReplaceWith(value="enableCache(flags) and disableCache(flags)")
    @DeprecatedSince(value="4.2.0")
    public DefaultShardManagerBuilder setEnabledCacheFlags(@Nullable EnumSet<CacheFlag> flags) {
        this.cacheFlags = flags == null ? EnumSet.noneOf(CacheFlag.class) : EnumSet.copyOf(flags);
        return this;
    }

    @Nonnull
    public DefaultShardManagerBuilder enableCache(@Nonnull Collection<CacheFlag> flags) {
        Checks.noneNull(flags, "CacheFlags");
        this.cacheFlags.addAll(flags);
        return this;
    }

    @Nonnull
    public DefaultShardManagerBuilder enableCache(@Nonnull CacheFlag flag, CacheFlag ... flags) {
        Checks.notNull((Object)flag, "CacheFlag");
        Checks.noneNull((Object[])flags, "CacheFlag");
        this.cacheFlags.addAll(EnumSet.of(flag, flags));
        return this;
    }

    @Nonnull
    @Deprecated
    @ReplaceWith(value="enableCache(flags) and disableCache(flags)")
    @DeprecatedSince(value="4.2.0")
    public DefaultShardManagerBuilder setDisabledCacheFlags(@Nullable EnumSet<CacheFlag> flags) {
        return this.setEnabledCacheFlags(flags == null ? EnumSet.allOf(CacheFlag.class) : EnumSet.complementOf(flags));
    }

    @Nonnull
    public DefaultShardManagerBuilder disableCache(@Nonnull Collection<CacheFlag> flags) {
        Checks.noneNull(flags, "CacheFlags");
        this.automaticallyDisabled.removeAll(flags);
        this.cacheFlags.removeAll(flags);
        return this;
    }

    @Nonnull
    public DefaultShardManagerBuilder disableCache(@Nonnull CacheFlag flag, CacheFlag ... flags) {
        Checks.notNull((Object)flag, "CacheFlag");
        Checks.noneNull((Object[])flags, "CacheFlag");
        return this.disableCache(EnumSet.of(flag, flags));
    }

    @Nonnull
    public DefaultShardManagerBuilder setMemberCachePolicy(@Nullable MemberCachePolicy policy) {
        this.memberCachePolicy = policy == null ? MemberCachePolicy.ALL : policy;
        return this;
    }

    @Nonnull
    public DefaultShardManagerBuilder setSessionController(@Nullable SessionController controller) {
        this.sessionController = controller;
        return this;
    }

    @Nonnull
    public DefaultShardManagerBuilder setVoiceDispatchInterceptor(@Nullable VoiceDispatchInterceptor interceptor) {
        this.voiceDispatchInterceptor = interceptor;
        return this;
    }

    @Nonnull
    public DefaultShardManagerBuilder setContextMap(@Nullable IntFunction<? extends ConcurrentMap<String, String>> provider) {
        this.contextProvider = provider;
        if (provider != null) {
            this.setContextEnabled(true);
        }
        return this;
    }

    @Nonnull
    public DefaultShardManagerBuilder setContextEnabled(boolean enable) {
        return this.setFlag(ConfigFlag.MDC_CONTEXT, enable);
    }

    @Nonnull
    public DefaultShardManagerBuilder setCompression(@Nonnull Compression compression) {
        Checks.notNull((Object)compression, "Compression");
        this.compression = compression;
        return this;
    }

    @Nonnull
    public DefaultShardManagerBuilder addEventListeners(Object ... listeners) {
        return this.addEventListeners(Arrays.asList(listeners));
    }

    @Nonnull
    public DefaultShardManagerBuilder addEventListeners(@Nonnull Collection<Object> listeners) {
        Checks.noneNull(listeners, "listeners");
        this.listeners.addAll(listeners);
        return this;
    }

    @Nonnull
    public DefaultShardManagerBuilder removeEventListeners(Object ... listeners) {
        return this.removeEventListeners(Arrays.asList(listeners));
    }

    @Nonnull
    public DefaultShardManagerBuilder removeEventListeners(@Nonnull Collection<Object> listeners) {
        Checks.noneNull(listeners, "listeners");
        this.listeners.removeAll(listeners);
        return this;
    }

    @Nonnull
    public DefaultShardManagerBuilder addEventListenerProvider(@Nonnull IntFunction<Object> listenerProvider) {
        return this.addEventListenerProviders(Collections.singleton(listenerProvider));
    }

    @Nonnull
    public DefaultShardManagerBuilder addEventListenerProviders(@Nonnull Collection<IntFunction<Object>> listenerProviders) {
        Checks.noneNull(listenerProviders, "listener providers");
        this.listenerProviders.addAll(listenerProviders);
        return this;
    }

    @Nonnull
    public DefaultShardManagerBuilder removeEventListenerProvider(@Nonnull IntFunction<Object> listenerProvider) {
        return this.removeEventListenerProviders(Collections.singleton(listenerProvider));
    }

    @Nonnull
    public DefaultShardManagerBuilder removeEventListenerProviders(@Nonnull Collection<IntFunction<Object>> listenerProviders) {
        Checks.noneNull(listenerProviders, "listener providers");
        this.listenerProviders.removeAll(listenerProviders);
        return this;
    }

    @Nonnull
    public DefaultShardManagerBuilder setAudioSendFactory(@Nullable IAudioSendFactory factory) {
        this.audioSendFactory = factory;
        return this;
    }

    @Nonnull
    public DefaultShardManagerBuilder setAutoReconnect(boolean autoReconnect) {
        return this.setFlag(ConfigFlag.AUTO_RECONNECT, autoReconnect);
    }

    @Nonnull
    public DefaultShardManagerBuilder setBulkDeleteSplittingEnabled(boolean enabled) {
        return this.setFlag(ConfigFlag.BULK_DELETE_SPLIT, enabled);
    }

    @Nonnull
    public DefaultShardManagerBuilder setEnableShutdownHook(boolean enable) {
        return this.setFlag(ConfigFlag.SHUTDOWN_HOOK, enable);
    }

    @Nonnull
    @Deprecated
    @DeprecatedSince(value="3.8.1")
    @ReplaceWith(value="setEventManagerProvider((id) -> manager)")
    public DefaultShardManagerBuilder setEventManager(@Nonnull IEventManager manager) {
        Checks.notNull(manager, "manager");
        return this.setEventManagerProvider(id2 -> manager);
    }

    @Nonnull
    public DefaultShardManagerBuilder setEventManagerProvider(@Nonnull IntFunction<? extends IEventManager> eventManagerProvider) {
        Checks.notNull(eventManagerProvider, "eventManagerProvider");
        this.eventManagerProvider = eventManagerProvider;
        return this;
    }

    @Nonnull
    public DefaultShardManagerBuilder setActivity(@Nullable Activity activity) {
        return this.setActivityProvider(id2 -> activity);
    }

    @Nonnull
    public DefaultShardManagerBuilder setActivityProvider(@Nullable IntFunction<? extends Activity> activityProvider) {
        this.activityProvider = activityProvider;
        return this;
    }

    @Nonnull
    public DefaultShardManagerBuilder setIdle(boolean idle) {
        return this.setIdleProvider(id2 -> idle);
    }

    @Nonnull
    public DefaultShardManagerBuilder setIdleProvider(@Nullable IntFunction<Boolean> idleProvider) {
        this.idleProvider = idleProvider;
        return this;
    }

    @Nonnull
    public DefaultShardManagerBuilder setStatus(@Nullable OnlineStatus status) {
        Checks.notNull((Object)status, "status");
        Checks.check(status != OnlineStatus.UNKNOWN, "OnlineStatus cannot be unknown!");
        return this.setStatusProvider(id2 -> status);
    }

    @Nonnull
    public DefaultShardManagerBuilder setStatusProvider(@Nullable IntFunction<OnlineStatus> statusProvider) {
        this.statusProvider = statusProvider;
        return this;
    }

    @Nonnull
    public DefaultShardManagerBuilder setThreadFactory(@Nullable ThreadFactory threadFactory) {
        this.threadFactory = threadFactory;
        return this;
    }

    @Nonnull
    public DefaultShardManagerBuilder setHttpClientBuilder(@Nullable OkHttpClient.Builder builder) {
        this.httpClientBuilder = builder;
        return this;
    }

    @Nonnull
    public DefaultShardManagerBuilder setHttpClient(@Nullable OkHttpClient client) {
        this.httpClient = client;
        return this;
    }

    @Nonnull
    public DefaultShardManagerBuilder setRateLimitPool(@Nullable ScheduledExecutorService pool) {
        return this.setRateLimitPool(pool, pool == null);
    }

    @Nonnull
    public DefaultShardManagerBuilder setRateLimitPool(@Nullable ScheduledExecutorService pool, boolean automaticShutdown) {
        return this.setRateLimitPoolProvider((ThreadPoolProvider<? extends ScheduledExecutorService>)(pool == null ? null : new ThreadPoolProviderImpl<ScheduledExecutorService>(pool, automaticShutdown)));
    }

    @Nonnull
    public DefaultShardManagerBuilder setRateLimitPoolProvider(@Nullable ThreadPoolProvider<? extends ScheduledExecutorService> provider) {
        this.rateLimitPoolProvider = provider;
        return this;
    }

    @Nonnull
    public DefaultShardManagerBuilder setGatewayPool(@Nullable ScheduledExecutorService pool) {
        return this.setGatewayPool(pool, pool == null);
    }

    @Nonnull
    public DefaultShardManagerBuilder setGatewayPool(@Nullable ScheduledExecutorService pool, boolean automaticShutdown) {
        return this.setGatewayPoolProvider((ThreadPoolProvider<? extends ScheduledExecutorService>)(pool == null ? null : new ThreadPoolProviderImpl<ScheduledExecutorService>(pool, automaticShutdown)));
    }

    @Nonnull
    public DefaultShardManagerBuilder setGatewayPoolProvider(@Nullable ThreadPoolProvider<? extends ScheduledExecutorService> provider) {
        this.gatewayPoolProvider = provider;
        return this;
    }

    @Nonnull
    public DefaultShardManagerBuilder setCallbackPool(@Nullable ExecutorService executor) {
        return this.setCallbackPool(executor, executor == null);
    }

    @Nonnull
    public DefaultShardManagerBuilder setCallbackPool(@Nullable ExecutorService executor, boolean automaticShutdown) {
        return this.setCallbackPoolProvider((ThreadPoolProvider<? extends ExecutorService>)(executor == null ? null : new ThreadPoolProviderImpl<ExecutorService>(executor, automaticShutdown)));
    }

    @Nonnull
    public DefaultShardManagerBuilder setCallbackPoolProvider(@Nullable ThreadPoolProvider<? extends ExecutorService> provider) {
        this.callbackPoolProvider = provider;
        return this;
    }

    @Nonnull
    public DefaultShardManagerBuilder setEventPool(@Nullable ExecutorService executor) {
        return this.setEventPool(executor, executor == null);
    }

    @Nonnull
    public DefaultShardManagerBuilder setEventPool(@Nullable ExecutorService executor, boolean automaticShutdown) {
        return this.setEventPoolProvider((ThreadPoolProvider<? extends ExecutorService>)(executor == null ? null : new ThreadPoolProviderImpl<ExecutorService>(executor, automaticShutdown)));
    }

    @Nonnull
    public DefaultShardManagerBuilder setEventPoolProvider(@Nullable ThreadPoolProvider<? extends ExecutorService> provider) {
        this.eventPoolProvider = provider;
        return this;
    }

    @Nonnull
    public DefaultShardManagerBuilder setAudioPool(@Nullable ScheduledExecutorService pool) {
        return this.setAudioPool(pool, pool == null);
    }

    @Nonnull
    public DefaultShardManagerBuilder setAudioPool(@Nullable ScheduledExecutorService pool, boolean automaticShutdown) {
        return this.setAudioPoolProvider((ThreadPoolProvider<? extends ScheduledExecutorService>)(pool == null ? null : new ThreadPoolProviderImpl<ScheduledExecutorService>(pool, automaticShutdown)));
    }

    @Nonnull
    public DefaultShardManagerBuilder setAudioPoolProvider(@Nullable ThreadPoolProvider<? extends ScheduledExecutorService> provider) {
        this.audioPoolProvider = provider;
        return this;
    }

    @Nonnull
    public DefaultShardManagerBuilder setMaxReconnectDelay(int maxReconnectDelay) {
        Checks.check(maxReconnectDelay >= 32, "Max reconnect delay must be 32 seconds or greater. You provided %d.", (Object)maxReconnectDelay);
        this.maxReconnectDelay = maxReconnectDelay;
        return this;
    }

    @Nonnull
    public DefaultShardManagerBuilder setRequestTimeoutRetry(boolean retryOnTimeout) {
        return this.setFlag(ConfigFlag.RETRY_TIMEOUT, retryOnTimeout);
    }

    @Nonnull
    public DefaultShardManagerBuilder setShards(int ... shardIds) {
        Checks.notNull(shardIds, "shardIds");
        for (int id2 : shardIds) {
            Checks.notNegative(id2, "minShardId");
            Checks.check(id2 < this.shardsTotal, "maxShardId must be lower than shardsTotal");
        }
        this.shards = Arrays.stream(shardIds).boxed().collect(Collectors.toSet());
        return this;
    }

    @Nonnull
    public DefaultShardManagerBuilder setShards(int minShardId, int maxShardId) {
        Checks.notNegative(minShardId, "minShardId");
        Checks.check(maxShardId < this.shardsTotal, "maxShardId must be lower than shardsTotal");
        Checks.check(minShardId <= maxShardId, "minShardId must be lower than or equal to maxShardId");
        ArrayList<Integer> shards = new ArrayList<Integer>(maxShardId - minShardId + 1);
        for (int i2 = minShardId; i2 <= maxShardId; ++i2) {
            shards.add(i2);
        }
        this.shards = shards;
        return this;
    }

    @Nonnull
    public DefaultShardManagerBuilder setShards(@Nonnull Collection<Integer> shardIds) {
        Checks.notNull(shardIds, "shardIds");
        for (Integer id2 : shardIds) {
            Checks.notNegative(id2, "minShardId");
            Checks.check(id2 < this.shardsTotal, "maxShardId must be lower than shardsTotal");
        }
        this.shards = new ArrayList<Integer>(shardIds);
        return this;
    }

    @Nonnull
    public DefaultShardManagerBuilder setShardsTotal(int shardsTotal) {
        Checks.check(shardsTotal == -1 || shardsTotal > 0, "shardsTotal must either be -1 or greater than 0");
        this.shardsTotal = shardsTotal;
        return this;
    }

    @Nonnull
    public DefaultShardManagerBuilder setToken(@Nonnull String token) {
        Checks.notBlank(token, "token");
        this.token = token;
        return this;
    }

    @Nonnull
    public DefaultShardManagerBuilder setUseShutdownNow(boolean useShutdownNow) {
        return this.setFlag(ShardingConfigFlag.SHUTDOWN_NOW, useShutdownNow);
    }

    @Nonnull
    public DefaultShardManagerBuilder setWebsocketFactory(@Nullable WebSocketFactory factory) {
        this.wsFactory = factory;
        return this;
    }

    @Nonnull
    public DefaultShardManagerBuilder setChunkingFilter(@Nullable ChunkingFilter filter) {
        this.chunkingFilter = filter;
        return this;
    }

    @Nonnull
    @Deprecated
    @ReplaceWith(value="setDisabledIntents(...).setMemberCachePolicy(...)")
    @DeprecatedSince(value="4.2.0")
    public DefaultShardManagerBuilder setGuildSubscriptionsEnabled(boolean enabled) {
        if (!enabled) {
            this.setMemberCachePolicy(MemberCachePolicy.VOICE);
            this.intents &= ~JDABuilder.GUILD_SUBSCRIPTIONS;
        }
        return this;
    }

    @Nonnull
    public DefaultShardManagerBuilder setDisabledIntents(@Nonnull GatewayIntent intent, GatewayIntent ... intents) {
        Checks.notNull((Object)intent, "Intent");
        Checks.noneNull((Object[])intents, "Intent");
        EnumSet<GatewayIntent[]> set = EnumSet.of(intent, intents);
        return this.setDisabledIntents(set);
    }

    @Nonnull
    public DefaultShardManagerBuilder setDisabledIntents(@Nullable Collection<GatewayIntent> intents) {
        this.intents = GatewayIntent.ALL_INTENTS;
        if (intents != null) {
            this.intents &= ~GatewayIntent.getRaw(intents);
        }
        return this;
    }

    @Nonnull
    public DefaultShardManagerBuilder disableIntents(@Nonnull Collection<GatewayIntent> intents) {
        Checks.noneNull(intents, "GatewayIntent");
        int raw = GatewayIntent.getRaw(intents);
        this.intents &= ~raw;
        return this;
    }

    @Nonnull
    public DefaultShardManagerBuilder disableIntents(@Nonnull GatewayIntent intent, GatewayIntent ... intents) {
        Checks.notNull((Object)intent, "GatewayIntent");
        Checks.noneNull((Object[])intents, "GatewayIntent");
        int raw = GatewayIntent.getRaw(intent, intents);
        this.intents &= ~raw;
        return this;
    }

    @Nonnull
    public DefaultShardManagerBuilder setEnabledIntents(@Nonnull GatewayIntent intent, GatewayIntent ... intents) {
        Checks.notNull((Object)intent, "Intent");
        Checks.noneNull((Object[])intents, "Intent");
        EnumSet<GatewayIntent[]> set = EnumSet.of(intent, intents);
        return this.setDisabledIntents(EnumSet.complementOf(set));
    }

    @Nonnull
    public DefaultShardManagerBuilder setEnabledIntents(@Nullable Collection<GatewayIntent> intents) {
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
    public DefaultShardManagerBuilder enableIntents(@Nonnull Collection<GatewayIntent> intents) {
        Checks.noneNull(intents, "GatewayIntent");
        int raw = GatewayIntent.getRaw(intents);
        this.intents |= raw;
        return this;
    }

    @Nonnull
    public DefaultShardManagerBuilder enableIntents(@Nonnull GatewayIntent intent, GatewayIntent ... intents) {
        Checks.notNull((Object)intent, "GatewayIntent");
        Checks.noneNull((Object[])intents, "GatewayIntent");
        int raw = GatewayIntent.getRaw(intent, intents);
        this.intents |= raw;
        return this;
    }

    @Nonnull
    public DefaultShardManagerBuilder setLargeThreshold(int threshold) {
        this.largeThreshold = Math.max(50, Math.min(250, threshold));
        return this;
    }

    @Nonnull
    public DefaultShardManagerBuilder setMaxBufferSize(int bufferSize) {
        Checks.notNegative(bufferSize, "The buffer size");
        this.maxBufferSize = bufferSize;
        return this;
    }

    @Nonnull
    public ShardManager build() throws LoginException, IllegalArgumentException {
        return this.build(true);
    }

    @Nonnull
    public ShardManager build(boolean login) throws LoginException, IllegalArgumentException {
        this.checkIntents();
        boolean useShutdownNow = this.shardingFlags.contains((Object)ShardingConfigFlag.SHUTDOWN_NOW);
        ShardingConfig shardingConfig = new ShardingConfig(this.shardsTotal, useShutdownNow, this.intents, this.memberCachePolicy);
        EventConfig eventConfig = new EventConfig(this.eventManagerProvider);
        this.listeners.forEach(eventConfig::addEventListener);
        this.listenerProviders.forEach(eventConfig::addEventListenerProvider);
        PresenceProviderConfig presenceConfig = new PresenceProviderConfig();
        presenceConfig.setActivityProvider(this.activityProvider);
        presenceConfig.setStatusProvider(this.statusProvider);
        presenceConfig.setIdleProvider(this.idleProvider);
        ThreadingProviderConfig threadingConfig = new ThreadingProviderConfig(this.rateLimitPoolProvider, this.gatewayPoolProvider, this.callbackPoolProvider, this.eventPoolProvider, this.audioPoolProvider, this.threadFactory);
        ShardingSessionConfig sessionConfig = new ShardingSessionConfig(this.sessionController, this.voiceDispatchInterceptor, this.httpClient, this.httpClientBuilder, this.wsFactory, this.audioSendFactory, this.flags, this.shardingFlags, this.maxReconnectDelay, this.largeThreshold);
        ShardingMetaConfig metaConfig = new ShardingMetaConfig(this.maxBufferSize, this.contextProvider, this.cacheFlags, this.flags, this.compression, this.encoding);
        DefaultShardManager manager = new DefaultShardManager(this.token, this.shards, shardingConfig, eventConfig, presenceConfig, threadingConfig, sessionConfig, metaConfig, this.chunkingFilter);
        if (login) {
            manager.login();
        }
        return manager;
    }

    private DefaultShardManagerBuilder setFlag(ConfigFlag flag, boolean enable) {
        if (enable) {
            this.flags.add(flag);
        } else {
            this.flags.remove((Object)flag);
        }
        return this;
    }

    private DefaultShardManagerBuilder setFlag(ShardingConfigFlag flag, boolean enable) {
        if (enable) {
            this.shardingFlags.add(flag);
        } else {
            this.shardingFlags.remove((Object)flag);
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
            DefaultShardManager.LOG.warn("Member chunking is disabled due to missing GUILD_MEMBERS intent.");
        }
        if (!this.automaticallyDisabled.isEmpty()) {
            JDAImpl.LOG.warn("Automatically disabled CacheFlags due to missing intents");
            this.automaticallyDisabled.stream().map(it2 -> "Disabled CacheFlag." + (Object)it2 + " (missing GatewayIntent." + (Object)((Object)it2.getRequiredIntent()) + ")").forEach(JDAImpl.LOG::warn);
            JDAImpl.LOG.warn("You can manually disable these flags to remove this warning by using disableCache({}) on your DefaultShardManagerBuilder", (Object)this.automaticallyDisabled.stream().map(it2 -> "CacheFlag." + (Object)it2).collect(Collectors.joining(", ")));
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

    private static class ThreadPoolProviderImpl<T extends ExecutorService>
    implements ThreadPoolProvider<T> {
        private final boolean autoShutdown;
        private final T pool;

        public ThreadPoolProviderImpl(T pool, boolean autoShutdown) {
            this.autoShutdown = autoShutdown;
            this.pool = pool;
        }

        @Override
        public T provide(int shardId) {
            return this.pool;
        }

        @Override
        public boolean shouldShutdownAutomatically(int shardId) {
            return this.autoShutdown;
        }
    }
}

