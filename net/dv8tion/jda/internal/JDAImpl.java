/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.internal;

import com.neovisionaries.ws.client.WebSocketFactory;
import gnu.trove.set.TLongSet;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledExecutorService;
import java.util.stream.Collectors;
import javax.annotation.Nonnull;
import javax.security.auth.login.LoginException;
import net.dv8tion.jda.api.AccountType;
import net.dv8tion.jda.api.GatewayEncoding;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.audio.factory.DefaultSendFactory;
import net.dv8tion.jda.api.audio.factory.IAudioSendFactory;
import net.dv8tion.jda.api.audio.hooks.ConnectionStatus;
import net.dv8tion.jda.api.entities.AbstractChannel;
import net.dv8tion.jda.api.entities.ApplicationInfo;
import net.dv8tion.jda.api.entities.Category;
import net.dv8tion.jda.api.entities.Emote;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Icon;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.PrivateChannel;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.SelfUser;
import net.dv8tion.jda.api.entities.StoreChannel;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.entities.Webhook;
import net.dv8tion.jda.api.events.GatewayPingEvent;
import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.StatusChangeEvent;
import net.dv8tion.jda.api.exceptions.AccountTypeException;
import net.dv8tion.jda.api.exceptions.RateLimitedException;
import net.dv8tion.jda.api.hooks.IEventManager;
import net.dv8tion.jda.api.hooks.InterfacedEventManager;
import net.dv8tion.jda.api.hooks.VoiceDispatchInterceptor;
import net.dv8tion.jda.api.interactions.commands.Command;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.managers.AudioManager;
import net.dv8tion.jda.api.managers.Presence;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.requests.Request;
import net.dv8tion.jda.api.requests.Response;
import net.dv8tion.jda.api.requests.RestAction;
import net.dv8tion.jda.api.requests.restaction.CommandCreateAction;
import net.dv8tion.jda.api.requests.restaction.CommandEditAction;
import net.dv8tion.jda.api.requests.restaction.CommandListUpdateAction;
import net.dv8tion.jda.api.sharding.ShardManager;
import net.dv8tion.jda.api.utils.ChunkingFilter;
import net.dv8tion.jda.api.utils.Compression;
import net.dv8tion.jda.api.utils.MemberCachePolicy;
import net.dv8tion.jda.api.utils.MiscUtil;
import net.dv8tion.jda.api.utils.SessionController;
import net.dv8tion.jda.api.utils.cache.CacheFlag;
import net.dv8tion.jda.api.utils.cache.CacheView;
import net.dv8tion.jda.api.utils.cache.SnowflakeCacheView;
import net.dv8tion.jda.api.utils.data.DataArray;
import net.dv8tion.jda.api.utils.data.DataObject;
import net.dv8tion.jda.internal.entities.EntityBuilder;
import net.dv8tion.jda.internal.entities.UserImpl;
import net.dv8tion.jda.internal.handle.EventCache;
import net.dv8tion.jda.internal.handle.GuildSetupController;
import net.dv8tion.jda.internal.hooks.EventManagerProxy;
import net.dv8tion.jda.internal.managers.AudioManagerImpl;
import net.dv8tion.jda.internal.managers.DirectAudioControllerImpl;
import net.dv8tion.jda.internal.managers.PresenceImpl;
import net.dv8tion.jda.internal.requests.CompletedRestAction;
import net.dv8tion.jda.internal.requests.DeferredRestAction;
import net.dv8tion.jda.internal.requests.Requester;
import net.dv8tion.jda.internal.requests.RestActionImpl;
import net.dv8tion.jda.internal.requests.Route;
import net.dv8tion.jda.internal.requests.WebSocketClient;
import net.dv8tion.jda.internal.requests.restaction.CommandCreateActionImpl;
import net.dv8tion.jda.internal.requests.restaction.CommandEditActionImpl;
import net.dv8tion.jda.internal.requests.restaction.CommandListUpdateActionImpl;
import net.dv8tion.jda.internal.requests.restaction.GuildActionImpl;
import net.dv8tion.jda.internal.utils.Checks;
import net.dv8tion.jda.internal.utils.Helpers;
import net.dv8tion.jda.internal.utils.JDALogger;
import net.dv8tion.jda.internal.utils.UnlockHook;
import net.dv8tion.jda.internal.utils.cache.AbstractCacheView;
import net.dv8tion.jda.internal.utils.cache.SnowflakeCacheViewImpl;
import net.dv8tion.jda.internal.utils.config.AuthorizationConfig;
import net.dv8tion.jda.internal.utils.config.MetaConfig;
import net.dv8tion.jda.internal.utils.config.SessionConfig;
import net.dv8tion.jda.internal.utils.config.ThreadingConfig;
import okhttp3.OkHttpClient;
import org.slf4j.Logger;
import org.slf4j.MDC;

public class JDAImpl
implements JDA {
    public static final Logger LOG = JDALogger.getLog(JDA.class);
    protected final SnowflakeCacheViewImpl<User> userCache = new SnowflakeCacheViewImpl<User>(User.class, User::getName);
    protected final SnowflakeCacheViewImpl<Guild> guildCache = new SnowflakeCacheViewImpl<Guild>(Guild.class, Guild::getName);
    protected final SnowflakeCacheViewImpl<Category> categories = new SnowflakeCacheViewImpl<Category>(Category.class, AbstractChannel::getName);
    protected final SnowflakeCacheViewImpl<StoreChannel> storeChannelCache = new SnowflakeCacheViewImpl<StoreChannel>(StoreChannel.class, AbstractChannel::getName);
    protected final SnowflakeCacheViewImpl<TextChannel> textChannelCache = new SnowflakeCacheViewImpl<TextChannel>(TextChannel.class, AbstractChannel::getName);
    protected final SnowflakeCacheViewImpl<VoiceChannel> voiceChannelCache = new SnowflakeCacheViewImpl<VoiceChannel>(VoiceChannel.class, AbstractChannel::getName);
    protected final SnowflakeCacheViewImpl<PrivateChannel> privateChannelCache = new SnowflakeCacheViewImpl<PrivateChannel>(PrivateChannel.class, AbstractChannel::getName);
    protected final LinkedList<Long> privateChannelLRU = new LinkedList();
    protected final AbstractCacheView<AudioManager> audioManagers = new CacheView.SimpleCacheView<AudioManager>(AudioManager.class, m2 -> m2.getGuild().getName());
    protected final PresenceImpl presence;
    protected final Thread shutdownHook;
    protected final EntityBuilder entityBuilder = new EntityBuilder(this);
    protected final EventCache eventCache;
    protected final EventManagerProxy eventManager;
    protected final GuildSetupController guildSetupController;
    protected final DirectAudioControllerImpl audioController;
    protected final AuthorizationConfig authConfig;
    protected final ThreadingConfig threadConfig;
    protected final SessionConfig sessionConfig;
    protected final MetaConfig metaConfig;
    protected WebSocketClient client;
    protected Requester requester;
    protected IAudioSendFactory audioSendFactory = new DefaultSendFactory();
    protected JDA.Status status = JDA.Status.INITIALIZING;
    protected SelfUser selfUser;
    protected JDA.ShardInfo shardInfo;
    protected long responseTotal;
    protected long gatewayPing = -1L;
    protected String gatewayUrl;
    protected ChunkingFilter chunkingFilter;
    protected String clientId = null;
    protected String requiredScopes = "bot";
    protected ShardManager shardManager = null;
    protected MemberCachePolicy memberCachePolicy = MemberCachePolicy.ALL;

    public JDAImpl(AuthorizationConfig authConfig) {
        this(authConfig, null, null, null);
    }

    public JDAImpl(AuthorizationConfig authConfig, SessionConfig sessionConfig, ThreadingConfig threadConfig, MetaConfig metaConfig) {
        this.authConfig = authConfig;
        this.threadConfig = threadConfig == null ? ThreadingConfig.getDefault() : threadConfig;
        this.sessionConfig = sessionConfig == null ? SessionConfig.getDefault() : sessionConfig;
        this.metaConfig = metaConfig == null ? MetaConfig.getDefault() : metaConfig;
        this.shutdownHook = this.metaConfig.isUseShutdownHook() ? new Thread(this::shutdown, "JDA Shutdown Hook") : null;
        this.presence = new PresenceImpl(this);
        this.requester = new Requester(this);
        this.requester.setRetryOnTimeout(this.sessionConfig.isRetryOnTimeout());
        this.guildSetupController = new GuildSetupController(this);
        this.audioController = new DirectAudioControllerImpl(this);
        this.eventCache = new EventCache();
        this.eventManager = new EventManagerProxy(new InterfacedEventManager(), this.threadConfig.getEventPool());
    }

    public void handleEvent(@Nonnull GenericEvent event) {
        this.eventManager.handle(event);
    }

    public boolean isRawEvents() {
        return this.sessionConfig.isRawEvents();
    }

    public boolean isRelativeRateLimit() {
        return this.sessionConfig.isRelativeRateLimit();
    }

    public boolean isCacheFlagSet(CacheFlag flag) {
        return this.metaConfig.getCacheFlags().contains((Object)flag);
    }

    public boolean isIntent(GatewayIntent intent) {
        int raw = intent.getRawValue();
        return (this.client.getGatewayIntents() & raw) == raw;
    }

    public int getLargeThreshold() {
        return this.sessionConfig.getLargeThreshold();
    }

    public int getMaxBufferSize() {
        return this.metaConfig.getMaxBufferSize();
    }

    public boolean chunkGuild(long id2) {
        try {
            return this.isIntent(GatewayIntent.GUILD_MEMBERS) && this.chunkingFilter.filter(id2);
        }
        catch (Exception e2) {
            LOG.error("Uncaught exception from chunking filter", e2);
            return true;
        }
    }

    public void setChunkingFilter(ChunkingFilter filter) {
        this.chunkingFilter = filter;
    }

    public boolean cacheMember(Member member) {
        try {
            return member.getUser().equals(this.getSelfUser()) || this.chunkGuild(member.getGuild().getIdLong()) || this.memberCachePolicy.cacheMember(member);
        }
        catch (Exception e2) {
            LOG.error("Uncaught exception from member cache policy", e2);
            return true;
        }
    }

    public void setMemberCachePolicy(MemberCachePolicy policy) {
        this.memberCachePolicy = policy;
    }

    public SessionController getSessionController() {
        return this.sessionConfig.getSessionController();
    }

    public GuildSetupController getGuildSetupController() {
        return this.guildSetupController;
    }

    public VoiceDispatchInterceptor getVoiceInterceptor() {
        return this.sessionConfig.getVoiceDispatchInterceptor();
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void usedPrivateChannel(long id2) {
        LinkedList<Long> linkedList = this.privateChannelLRU;
        synchronized (linkedList) {
            this.privateChannelLRU.remove(id2);
            this.privateChannelLRU.addFirst(id2);
            if (this.privateChannelLRU.size() > 10) {
                long removed = this.privateChannelLRU.removeLast();
                this.privateChannelCache.remove(removed);
            }
        }
    }

    public int login() throws LoginException {
        return this.login(null, null, Compression.ZLIB, true, GatewayIntent.ALL_INTENTS, GatewayEncoding.JSON);
    }

    public int login(JDA.ShardInfo shardInfo, Compression compression, boolean validateToken, int intents, GatewayEncoding encoding) throws LoginException {
        return this.login(null, shardInfo, compression, validateToken, intents, encoding);
    }

    public int login(String gatewayUrl, JDA.ShardInfo shardInfo, Compression compression, boolean validateToken, int intents, GatewayEncoding encoding) throws LoginException {
        this.shardInfo = shardInfo;
        this.threadConfig.init(this::getIdentifierString);
        this.requester.getRateLimiter().init();
        this.gatewayUrl = gatewayUrl == null ? this.getGateway() : gatewayUrl;
        Checks.notNull(this.gatewayUrl, "Gateway URL");
        String token = this.authConfig.getToken();
        this.setStatus(JDA.Status.LOGGING_IN);
        if (token == null || token.isEmpty()) {
            throw new LoginException("Provided token was null or empty!");
        }
        Map<String, String> previousContext = null;
        ConcurrentMap<String, String> contextMap = this.metaConfig.getMdcContextMap();
        if (contextMap != null) {
            if (shardInfo != null) {
                contextMap.put("jda.shard", shardInfo.getShardString());
                contextMap.put("jda.shard.id", String.valueOf(shardInfo.getShardId()));
                contextMap.put("jda.shard.total", String.valueOf(shardInfo.getShardTotal()));
            }
            previousContext = MDC.getCopyOfContextMap();
            contextMap.forEach(MDC::put);
            this.requester.setContextReady(true);
        }
        if (validateToken) {
            this.verifyToken();
            LOG.info("Login Successful!");
        }
        this.client = new WebSocketClient(this, compression, intents, encoding);
        if (previousContext != null) {
            previousContext.forEach(MDC::put);
        }
        if (this.shutdownHook != null) {
            Runtime.getRuntime().addShutdownHook(this.shutdownHook);
        }
        return shardInfo == null ? -1 : shardInfo.getShardTotal();
    }

    public String getGateway() {
        return this.getSessionController().getGateway(this);
    }

    public SessionController.ShardedGateway getShardedGateway() {
        return this.getSessionController().getShardedGateway(this);
    }

    public ConcurrentMap<String, String> getContextMap() {
        return this.metaConfig.getMdcContextMap() == null ? null : new ConcurrentHashMap<String, String>(this.metaConfig.getMdcContextMap());
    }

    public void setContext() {
        if (this.metaConfig.getMdcContextMap() != null) {
            this.metaConfig.getMdcContextMap().forEach(MDC::put);
        }
    }

    public void setToken(String token) {
        this.authConfig.setToken(token);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void setStatus(JDA.Status status) {
        JDA.Status status2 = this.status;
        synchronized (status2) {
            JDA.Status oldStatus = this.status;
            this.status = status;
            this.handleEvent(new StatusChangeEvent(this, status, oldStatus));
        }
    }

    public void verifyToken() throws LoginException {
        RestActionImpl login = new RestActionImpl<DataObject>((JDA)this, Route.Self.GET_SELF.compile(new String[0])){

            @Override
            public void handleResponse(Response response, Request<DataObject> request) {
                if (response.isOk()) {
                    request.onSuccess(response.getObject());
                } else if (response.isRateLimit()) {
                    request.onFailure(new RateLimitedException(request.getRoute(), response.retryAfter));
                } else if (response.code == 401) {
                    request.onSuccess(null);
                } else {
                    request.onFailure(response);
                }
            }
        }.priority();
        DataObject userResponse = (DataObject)login.complete();
        if (userResponse != null) {
            this.getEntityBuilder().createSelfUser(userResponse);
            return;
        }
        this.shutdownNow();
        throw new LoginException("The provided token is invalid!");
    }

    public AuthorizationConfig getAuthorizationConfig() {
        return this.authConfig;
    }

    @Override
    @Nonnull
    public String getToken() {
        return this.authConfig.getToken();
    }

    @Override
    public boolean isBulkDeleteSplittingEnabled() {
        return this.sessionConfig.isBulkDeleteSplittingEnabled();
    }

    @Override
    public void setAutoReconnect(boolean autoReconnect) {
        this.sessionConfig.setAutoReconnect(autoReconnect);
        WebSocketClient client = this.getClient();
        if (client != null) {
            client.setAutoReconnect(autoReconnect);
        }
    }

    @Override
    public void setRequestTimeoutRetry(boolean retryOnTimeout) {
        this.requester.setRetryOnTimeout(retryOnTimeout);
    }

    @Override
    public boolean isAutoReconnect() {
        return this.sessionConfig.isAutoReconnect();
    }

    @Override
    @Nonnull
    public JDA.Status getStatus() {
        return this.status;
    }

    @Override
    @Nonnull
    public EnumSet<GatewayIntent> getGatewayIntents() {
        return GatewayIntent.getIntents(this.client.getGatewayIntents());
    }

    @Override
    @Nonnull
    public EnumSet<CacheFlag> getCacheFlags() {
        return Helpers.copyEnumSet(CacheFlag.class, this.metaConfig.getCacheFlags());
    }

    @Override
    public boolean unloadUser(long userId) {
        if (userId == this.selfUser.getIdLong()) {
            return false;
        }
        User user = this.getUserById(userId);
        if (user == null) {
            return false;
        }
        return this.getGuildCache().stream().filter(guild -> guild.unloadMember(userId)).count() > 0L;
    }

    @Override
    public long getGatewayPing() {
        return this.gatewayPing;
    }

    @Override
    @Nonnull
    public JDA awaitStatus(@Nonnull JDA.Status status, JDA.Status ... failOn) throws InterruptedException {
        Checks.notNull((Object)status, "Status");
        Checks.check(status.isInit(), "Cannot await the status %s as it is not part of the login cycle!", (Object)status);
        if (this.getStatus() == JDA.Status.CONNECTED) {
            return this;
        }
        List<JDA.Status> failStatus = Arrays.asList(failOn);
        while (!this.getStatus().isInit() || this.getStatus().ordinal() < status.ordinal()) {
            if (this.getStatus() == JDA.Status.SHUTDOWN) {
                throw new IllegalStateException("Was shutdown trying to await status");
            }
            if (failStatus.contains((Object)this.getStatus())) {
                return this;
            }
            Thread.sleep(50L);
        }
        return this;
    }

    @Override
    public int cancelRequests() {
        return this.requester.getRateLimiter().cancelRequests();
    }

    @Override
    @Nonnull
    public ScheduledExecutorService getRateLimitPool() {
        return this.threadConfig.getRateLimitPool();
    }

    @Override
    @Nonnull
    public ScheduledExecutorService getGatewayPool() {
        return this.threadConfig.getGatewayPool();
    }

    @Override
    @Nonnull
    public ExecutorService getCallbackPool() {
        return this.threadConfig.getCallbackPool();
    }

    @Override
    @Nonnull
    public OkHttpClient getHttpClient() {
        return this.sessionConfig.getHttpClient();
    }

    @Override
    @Nonnull
    public DirectAudioControllerImpl getDirectAudioController() {
        if (!this.isIntent(GatewayIntent.GUILD_VOICE_STATES)) {
            throw new IllegalStateException("Cannot use audio features with disabled GUILD_VOICE_STATES intent!");
        }
        return this.audioController;
    }

    @Override
    @Nonnull
    public List<Guild> getMutualGuilds(User ... users) {
        Checks.notNull(users, "users");
        return this.getMutualGuilds(Arrays.asList(users));
    }

    @Override
    @Nonnull
    public List<Guild> getMutualGuilds(@Nonnull Collection<User> users) {
        Checks.notNull(users, "users");
        for (User u2 : users) {
            Checks.notNull(u2, "All users");
        }
        return Collections.unmodifiableList(this.getGuilds().stream().filter(guild -> users.stream().allMatch(guild::isMember)).collect(Collectors.toList()));
    }

    @Override
    @Nonnull
    public RestAction<User> retrieveUserById(@Nonnull String id2) {
        return this.retrieveUserById(MiscUtil.parseSnowflake(id2));
    }

    @Override
    @Nonnull
    public RestAction<User> retrieveUserById(long id2, boolean update) {
        if (id2 == this.getSelfUser().getIdLong()) {
            return new CompletedRestAction<User>((JDA)this, this.getSelfUser());
        }
        AccountTypeException.check(this.getAccountType(), AccountType.BOT);
        return new DeferredRestAction<User, RestActionImpl>(this, User.class, () -> !update || this.isIntent(GatewayIntent.GUILD_MEMBERS) || this.isIntent(GatewayIntent.GUILD_PRESENCES) ? this.getUserById(id2) : null, () -> {
            Route.CompiledRoute route = Route.Users.GET_USER.compile(Long.toUnsignedString(id2));
            return new RestActionImpl<User>((JDA)this, route, (response, request) -> this.getEntityBuilder().createUser(response.getObject()));
        });
    }

    @Override
    @Nonnull
    public CacheView<AudioManager> getAudioManagerCache() {
        return this.audioManagers;
    }

    @Override
    @Nonnull
    public SnowflakeCacheView<Guild> getGuildCache() {
        return this.guildCache;
    }

    @Override
    @Nonnull
    public Set<String> getUnavailableGuilds() {
        TLongSet unavailableGuilds = this.guildSetupController.getUnavailableGuilds();
        HashSet<String> copy = new HashSet<String>();
        unavailableGuilds.forEach(id2 -> copy.add(Long.toUnsignedString(id2)));
        return copy;
    }

    @Override
    public boolean isUnavailable(long guildId) {
        return this.guildSetupController.isUnavailable(guildId);
    }

    @Override
    @Nonnull
    public SnowflakeCacheView<Role> getRoleCache() {
        return CacheView.allSnowflakes(() -> this.guildCache.stream().map(Guild::getRoleCache));
    }

    @Override
    @Nonnull
    public SnowflakeCacheView<Emote> getEmoteCache() {
        return CacheView.allSnowflakes(() -> this.guildCache.stream().map(Guild::getEmoteCache));
    }

    @Override
    @Nonnull
    public SnowflakeCacheView<Category> getCategoryCache() {
        return this.categories;
    }

    @Override
    @Nonnull
    public SnowflakeCacheView<StoreChannel> getStoreChannelCache() {
        return this.storeChannelCache;
    }

    @Override
    @Nonnull
    public SnowflakeCacheView<TextChannel> getTextChannelCache() {
        return this.textChannelCache;
    }

    @Override
    @Nonnull
    public SnowflakeCacheView<VoiceChannel> getVoiceChannelCache() {
        return this.voiceChannelCache;
    }

    @Override
    @Nonnull
    public SnowflakeCacheView<PrivateChannel> getPrivateChannelCache() {
        return this.privateChannelCache;
    }

    @Override
    public PrivateChannel getPrivateChannelById(@Nonnull String id2) {
        return this.getPrivateChannelById(MiscUtil.parseSnowflake(id2));
    }

    @Override
    public PrivateChannel getPrivateChannelById(long id2) {
        PrivateChannel channel = JDA.super.getPrivateChannelById(id2);
        if (channel != null) {
            this.usedPrivateChannel(id2);
        }
        return channel;
    }

    @Override
    @Nonnull
    public RestAction<PrivateChannel> openPrivateChannelById(long userId) {
        if (this.selfUser != null && userId == this.selfUser.getIdLong()) {
            throw new UnsupportedOperationException("Cannot open private channel with yourself!");
        }
        return new DeferredRestAction<PrivateChannel, RestActionImpl>(this, PrivateChannel.class, () -> {
            User user = this.getUserById(userId);
            if (user instanceof UserImpl) {
                return ((UserImpl)user).getPrivateChannel();
            }
            return null;
        }, () -> {
            Route.CompiledRoute route = Route.Self.CREATE_PRIVATE_CHANNEL.compile(new String[0]);
            DataObject body = DataObject.empty().put("recipient_id", userId);
            return new RestActionImpl<PrivateChannel>((JDA)this, route, body, (response, request) -> this.getEntityBuilder().createPrivateChannel(response.getObject()));
        });
    }

    @Override
    @Nonnull
    public SnowflakeCacheView<User> getUserCache() {
        return this.userCache;
    }

    public boolean hasSelfUser() {
        return this.selfUser != null;
    }

    @Override
    @Nonnull
    public SelfUser getSelfUser() {
        if (this.selfUser == null) {
            throw new IllegalStateException("Session is not yet ready!");
        }
        return this.selfUser;
    }

    @Override
    public synchronized void shutdownNow() {
        this.requester.shutdown();
        this.shutdown();
        this.threadConfig.shutdownNow();
    }

    @Override
    public synchronized void shutdown() {
        if (this.status == JDA.Status.SHUTDOWN || this.status == JDA.Status.SHUTTING_DOWN) {
            return;
        }
        this.setStatus(JDA.Status.SHUTTING_DOWN);
        this.shutdownInternals();
        WebSocketClient client = this.getClient();
        if (client != null) {
            client.getChunkManager().shutdown();
            client.shutdown();
        }
    }

    public synchronized void shutdownInternals() {
        if (this.status == JDA.Status.SHUTDOWN) {
            return;
        }
        this.closeAudioConnections();
        this.guildSetupController.close();
        if (this.requester.stop()) {
            this.shutdownRequester();
        }
        this.threadConfig.shutdown();
        if (this.shutdownHook != null) {
            try {
                Runtime.getRuntime().removeShutdownHook(this.shutdownHook);
            }
            catch (Exception exception) {
                // empty catch block
            }
        }
        this.setStatus(JDA.Status.SHUTDOWN);
    }

    public synchronized void shutdownRequester() {
        this.requester.shutdown();
        this.threadConfig.shutdownRequester();
    }

    private void closeAudioConnections() {
        this.getAudioManagerCache().stream().map(AudioManagerImpl.class::cast).forEach(m2 -> m2.closeAudioConnection(ConnectionStatus.SHUTTING_DOWN));
    }

    @Override
    public long getResponseTotal() {
        return this.responseTotal;
    }

    @Override
    public int getMaxReconnectDelay() {
        return this.sessionConfig.getMaxReconnectDelay();
    }

    @Override
    @Nonnull
    public JDA.ShardInfo getShardInfo() {
        return this.shardInfo == null ? JDA.ShardInfo.SINGLE : this.shardInfo;
    }

    @Override
    @Nonnull
    public Presence getPresence() {
        return this.presence;
    }

    @Override
    @Nonnull
    public IEventManager getEventManager() {
        return this.eventManager.getSubject();
    }

    @Override
    @Nonnull
    public AccountType getAccountType() {
        return this.authConfig.getAccountType();
    }

    @Override
    public void setEventManager(IEventManager eventManager) {
        this.eventManager.setSubject(eventManager);
    }

    @Override
    public void addEventListener(Object ... listeners) {
        Checks.noneNull(listeners, "listeners");
        for (Object listener : listeners) {
            this.eventManager.register(listener);
        }
    }

    @Override
    public void removeEventListener(Object ... listeners) {
        Checks.noneNull(listeners, "listeners");
        for (Object listener : listeners) {
            this.eventManager.unregister(listener);
        }
    }

    @Override
    @Nonnull
    public List<Object> getRegisteredListeners() {
        return this.eventManager.getRegisteredListeners();
    }

    @Override
    @Nonnull
    public RestAction<List<Command>> retrieveCommands() {
        Route.CompiledRoute route = Route.Interactions.GET_COMMANDS.compile(this.getSelfUser().getApplicationId());
        return new RestActionImpl<List<Command>>((JDA)this, route, (response, request) -> response.getArray().stream(DataArray::getObject).map(json -> new Command(this, null, (DataObject)json)).collect(Collectors.toList()));
    }

    @Override
    @Nonnull
    public RestAction<Command> retrieveCommandById(@Nonnull String id2) {
        Checks.isSnowflake(id2);
        Route.CompiledRoute route = Route.Interactions.GET_COMMAND.compile(this.getSelfUser().getApplicationId(), id2);
        return new RestActionImpl<Command>((JDA)this, route, (response, request) -> new Command(this, null, response.getObject()));
    }

    @Override
    @Nonnull
    public CommandCreateAction upsertCommand(@Nonnull CommandData command) {
        Checks.notNull(command, "CommandData");
        return new CommandCreateActionImpl(this, command);
    }

    @Override
    @Nonnull
    public CommandListUpdateAction updateCommands() {
        Route.CompiledRoute route = Route.Interactions.UPDATE_COMMANDS.compile(this.getSelfUser().getApplicationId());
        return new CommandListUpdateActionImpl((JDA)this, null, route);
    }

    @Override
    @Nonnull
    public CommandEditAction editCommandById(@Nonnull String id2) {
        Checks.isSnowflake(id2);
        return new CommandEditActionImpl((JDA)this, id2);
    }

    @Override
    @Nonnull
    public RestAction<Void> deleteCommandById(@Nonnull String commandId) {
        Checks.isSnowflake(commandId);
        Route.CompiledRoute route = Route.Interactions.DELETE_COMMAND.compile(this.getSelfUser().getApplicationId(), commandId);
        return new RestActionImpl<Void>(this, route);
    }

    @Override
    @Nonnull
    public GuildActionImpl createGuild(@Nonnull String name) {
        if (this.guildCache.size() >= 10L) {
            throw new IllegalStateException("Cannot create a Guild with a Bot in 10 or more guilds!");
        }
        return new GuildActionImpl((JDA)this, name);
    }

    @Override
    @Nonnull
    public RestAction<Void> createGuildFromTemplate(@Nonnull String code, @Nonnull String name, Icon icon) {
        if (this.guildCache.size() >= 10L) {
            throw new IllegalStateException("Cannot create a Guild with a Bot in 10 or more guilds!");
        }
        Checks.notBlank(code, "Template code");
        Checks.notBlank(name, "Name");
        name = name.trim();
        Checks.notLonger(name, 100, "Name");
        Route.CompiledRoute route = Route.Templates.CREATE_GUILD_FROM_TEMPLATE.compile(code);
        DataObject object = DataObject.empty();
        object.put("name", name);
        if (icon != null) {
            object.put("icon", icon.getEncoding());
        }
        return new RestActionImpl<Void>((JDA)this, route, object);
    }

    @Override
    @Nonnull
    public RestAction<Webhook> retrieveWebhookById(@Nonnull String webhookId) {
        Checks.isSnowflake(webhookId, "Webhook ID");
        Route.CompiledRoute route = Route.Webhooks.GET_WEBHOOK.compile(webhookId);
        return new RestActionImpl<Webhook>((JDA)this, route, (response, request) -> {
            DataObject object = response.getObject();
            EntityBuilder builder = this.getEntityBuilder();
            return builder.createWebhook(object);
        });
    }

    @Override
    @Nonnull
    public RestAction<ApplicationInfo> retrieveApplicationInfo() {
        AccountTypeException.check(this.getAccountType(), AccountType.BOT);
        Route.CompiledRoute route = Route.Applications.GET_BOT_APPLICATION.compile(new String[0]);
        return new RestActionImpl<ApplicationInfo>((JDA)this, route, (response, request) -> {
            ApplicationInfo info = this.getEntityBuilder().createApplicationInfo(response.getObject());
            this.clientId = info.getId();
            return info;
        });
    }

    @Override
    @Nonnull
    public JDA setRequiredScopes(@Nonnull Collection<String> scopes) {
        Checks.noneNull(scopes, "Scopes");
        this.requiredScopes = String.join((CharSequence)"+", scopes);
        if (!this.requiredScopes.contains("bot")) {
            this.requiredScopes = this.requiredScopes.isEmpty() ? "bot" : this.requiredScopes + "+bot";
        }
        return this;
    }

    @Override
    @Nonnull
    public String getInviteUrl(Permission ... permissions) {
        StringBuilder builder = this.buildBaseInviteUrl();
        if (permissions != null && permissions.length > 0) {
            builder.append("&permissions=").append(Permission.getRaw(permissions));
        }
        return builder.toString();
    }

    @Override
    @Nonnull
    public String getInviteUrl(Collection<Permission> permissions) {
        StringBuilder builder = this.buildBaseInviteUrl();
        if (permissions != null && !permissions.isEmpty()) {
            builder.append("&permissions=").append(Permission.getRaw(permissions));
        }
        return builder.toString();
    }

    private StringBuilder buildBaseInviteUrl() {
        if (this.clientId == null) {
            if (this.selfUser != null) {
                this.clientId = this.selfUser.getApplicationId();
            } else {
                this.retrieveApplicationInfo().complete();
            }
        }
        StringBuilder builder = new StringBuilder("https://discord.com/oauth2/authorize?client_id=");
        builder.append(this.clientId);
        builder.append("&scope=").append(this.requiredScopes);
        return builder;
    }

    public void setShardManager(ShardManager shardManager) {
        this.shardManager = shardManager;
    }

    @Override
    public ShardManager getShardManager() {
        return this.shardManager;
    }

    public EntityBuilder getEntityBuilder() {
        return this.entityBuilder;
    }

    public IAudioSendFactory getAudioSendFactory() {
        return this.audioSendFactory;
    }

    public void setAudioSendFactory(IAudioSendFactory factory) {
        Checks.notNull(factory, "Provided IAudioSendFactory");
        this.audioSendFactory = factory;
    }

    public void setGatewayPing(long ping) {
        long oldPing = this.gatewayPing;
        this.gatewayPing = ping;
        this.handleEvent(new GatewayPingEvent(this, oldPing));
    }

    public Requester getRequester() {
        return this.requester;
    }

    public WebSocketFactory getWebSocketFactory() {
        return this.sessionConfig.getWebSocketFactory();
    }

    public WebSocketClient getClient() {
        return this.client;
    }

    public SnowflakeCacheViewImpl<User> getUsersView() {
        return this.userCache;
    }

    public SnowflakeCacheViewImpl<Guild> getGuildsView() {
        return this.guildCache;
    }

    public SnowflakeCacheViewImpl<Category> getCategoriesView() {
        return this.categories;
    }

    public SnowflakeCacheViewImpl<StoreChannel> getStoreChannelsView() {
        return this.storeChannelCache;
    }

    public SnowflakeCacheViewImpl<TextChannel> getTextChannelsView() {
        return this.textChannelCache;
    }

    public SnowflakeCacheViewImpl<VoiceChannel> getVoiceChannelsView() {
        return this.voiceChannelCache;
    }

    public SnowflakeCacheViewImpl<PrivateChannel> getPrivateChannelsView() {
        return this.privateChannelCache;
    }

    public AbstractCacheView<AudioManager> getAudioManagersView() {
        return this.audioManagers;
    }

    public void setSelfUser(SelfUser selfUser) {
        try (UnlockHook hook = this.userCache.writeLock();){
            this.userCache.getMap().put(selfUser.getIdLong(), selfUser);
        }
        this.selfUser = selfUser;
    }

    public void setResponseTotal(int responseTotal) {
        this.responseTotal = responseTotal;
    }

    public String getIdentifierString() {
        if (this.shardInfo != null) {
            return "JDA " + this.shardInfo.getShardString();
        }
        return "JDA";
    }

    public EventCache getEventCache() {
        return this.eventCache;
    }

    public String getGatewayUrl() {
        if (this.gatewayUrl == null) {
            this.gatewayUrl = this.getGateway();
            return this.gatewayUrl;
        }
        return this.gatewayUrl;
    }

    public void resetGatewayUrl() {
        this.gatewayUrl = null;
    }

    public ScheduledExecutorService getAudioLifeCyclePool() {
        return this.threadConfig.getAudioPool(this::getIdentifierString);
    }
}

