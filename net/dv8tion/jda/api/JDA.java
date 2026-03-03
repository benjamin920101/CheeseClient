/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.Collection;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicLong;
import java.util.regex.Matcher;
import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.dv8tion.jda.api.AccountType;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.ApplicationInfo;
import net.dv8tion.jda.api.entities.Category;
import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.entities.Emote;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.GuildChannel;
import net.dv8tion.jda.api.entities.Icon;
import net.dv8tion.jda.api.entities.PrivateChannel;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.SelfUser;
import net.dv8tion.jda.api.entities.StoreChannel;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.entities.Webhook;
import net.dv8tion.jda.api.hooks.IEventManager;
import net.dv8tion.jda.api.interactions.commands.Command;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.managers.AudioManager;
import net.dv8tion.jda.api.managers.DirectAudioController;
import net.dv8tion.jda.api.managers.Presence;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.requests.RestAction;
import net.dv8tion.jda.api.requests.restaction.AuditableRestAction;
import net.dv8tion.jda.api.requests.restaction.CommandCreateAction;
import net.dv8tion.jda.api.requests.restaction.CommandEditAction;
import net.dv8tion.jda.api.requests.restaction.CommandListUpdateAction;
import net.dv8tion.jda.api.requests.restaction.GuildAction;
import net.dv8tion.jda.api.sharding.ShardManager;
import net.dv8tion.jda.api.utils.MiscUtil;
import net.dv8tion.jda.api.utils.cache.CacheFlag;
import net.dv8tion.jda.api.utils.cache.CacheView;
import net.dv8tion.jda.api.utils.cache.SnowflakeCacheView;
import net.dv8tion.jda.internal.requests.CompletedRestAction;
import net.dv8tion.jda.internal.requests.RestActionImpl;
import net.dv8tion.jda.internal.requests.Route;
import net.dv8tion.jda.internal.utils.Checks;
import net.dv8tion.jda.internal.utils.Helpers;
import okhttp3.OkHttpClient;

public interface JDA {
    @Nonnull
    public Status getStatus();

    @Nonnull
    public EnumSet<GatewayIntent> getGatewayIntents();

    @Nonnull
    public EnumSet<CacheFlag> getCacheFlags();

    public boolean unloadUser(long var1);

    public long getGatewayPing();

    @Nonnull
    default public RestAction<Long> getRestPing() {
        AtomicLong time = new AtomicLong();
        Route.CompiledRoute route = Route.Self.GET_SELF.compile(new String[0]);
        RestActionImpl<Long> action = new RestActionImpl<Long>(this, route, (response, request) -> System.currentTimeMillis() - time.get());
        action.setCheck(() -> {
            time.set(System.currentTimeMillis());
            return true;
        });
        return action;
    }

    @Nonnull
    default public JDA awaitStatus(@Nonnull Status status) throws InterruptedException {
        return this.awaitStatus(status, new Status[0]);
    }

    @Nonnull
    public JDA awaitStatus(@Nonnull Status var1, Status ... var2) throws InterruptedException;

    @Nonnull
    default public JDA awaitReady() throws InterruptedException {
        return this.awaitStatus(Status.CONNECTED);
    }

    public int cancelRequests();

    @Nonnull
    public ScheduledExecutorService getRateLimitPool();

    @Nonnull
    public ScheduledExecutorService getGatewayPool();

    @Nonnull
    public ExecutorService getCallbackPool();

    @Nonnull
    public OkHttpClient getHttpClient();

    @Nonnull
    public DirectAudioController getDirectAudioController();

    public void setEventManager(@Nullable IEventManager var1);

    public void addEventListener(Object ... var1);

    public void removeEventListener(Object ... var1);

    @Nonnull
    public List<Object> getRegisteredListeners();

    @Nonnull
    @CheckReturnValue
    public RestAction<List<Command>> retrieveCommands();

    @Nonnull
    @CheckReturnValue
    public RestAction<Command> retrieveCommandById(@Nonnull String var1);

    @Nonnull
    @CheckReturnValue
    default public RestAction<Command> retrieveCommandById(long id2) {
        return this.retrieveCommandById(Long.toUnsignedString(id2));
    }

    @Nonnull
    @CheckReturnValue
    public CommandCreateAction upsertCommand(@Nonnull CommandData var1);

    @Nonnull
    @CheckReturnValue
    default public CommandCreateAction upsertCommand(@Nonnull String name, @Nonnull String description) {
        return this.upsertCommand(new CommandData(name, description));
    }

    @Nonnull
    @CheckReturnValue
    public CommandListUpdateAction updateCommands();

    @Nonnull
    @CheckReturnValue
    public CommandEditAction editCommandById(@Nonnull String var1);

    @Nonnull
    @CheckReturnValue
    default public CommandEditAction editCommandById(long id2) {
        return this.editCommandById(Long.toUnsignedString(id2));
    }

    @Nonnull
    @CheckReturnValue
    public RestAction<Void> deleteCommandById(@Nonnull String var1);

    @Nonnull
    @CheckReturnValue
    default public RestAction<Void> deleteCommandById(long commandId) {
        return this.deleteCommandById(Long.toUnsignedString(commandId));
    }

    @Nonnull
    @CheckReturnValue
    public GuildAction createGuild(@Nonnull String var1);

    @Nonnull
    @CheckReturnValue
    public RestAction<Void> createGuildFromTemplate(@Nonnull String var1, @Nonnull String var2, @Nullable Icon var3);

    @Nonnull
    public CacheView<AudioManager> getAudioManagerCache();

    @Nonnull
    default public List<AudioManager> getAudioManagers() {
        return this.getAudioManagerCache().asList();
    }

    @Nonnull
    public SnowflakeCacheView<User> getUserCache();

    @Nonnull
    default public List<User> getUsers() {
        return this.getUserCache().asList();
    }

    @Nullable
    default public User getUserById(@Nonnull String id2) {
        return this.getUserCache().getElementById(id2);
    }

    @Nullable
    default public User getUserById(long id2) {
        return this.getUserCache().getElementById(id2);
    }

    @Nullable
    default public User getUserByTag(@Nonnull String tag) {
        Checks.notNull(tag, "Tag");
        Matcher matcher = User.USER_TAG.matcher(tag);
        Checks.check(matcher.matches(), "Invalid tag format!");
        String username = matcher.group(1);
        String discriminator = matcher.group(2);
        return this.getUserByTag(username, discriminator);
    }

    @Nullable
    default public User getUserByTag(@Nonnull String username, @Nonnull String discriminator) {
        Checks.notNull(username, "Username");
        Checks.notNull(discriminator, "Discriminator");
        Checks.check(discriminator.length() == 4 && Helpers.isNumeric(discriminator), "Invalid format for discriminator!");
        int codePointLength = Helpers.codePointLength(username);
        Checks.check(codePointLength >= 2 && codePointLength <= 32, "Username must be between 2 and 32 codepoints in length!");
        return this.getUserCache().applyStream(stream -> stream.filter(it2 -> it2.getDiscriminator().equals(discriminator)).filter(it2 -> it2.getName().equals(username)).findFirst().orElse(null));
    }

    @Nonnull
    default public List<User> getUsersByName(@Nonnull String name, boolean ignoreCase) {
        return this.getUserCache().getElementsByName(name, ignoreCase);
    }

    @Nonnull
    public List<Guild> getMutualGuilds(User ... var1);

    @Nonnull
    public List<Guild> getMutualGuilds(@Nonnull Collection<User> var1);

    @Nonnull
    @CheckReturnValue
    default public RestAction<User> retrieveUserById(@Nonnull String id2) {
        return this.retrieveUserById(id2, true);
    }

    @Nonnull
    @CheckReturnValue
    default public RestAction<User> retrieveUserById(long id2) {
        return this.retrieveUserById(id2, true);
    }

    @Nonnull
    @CheckReturnValue
    default public RestAction<User> retrieveUserById(@Nonnull String id2, boolean update) {
        return this.retrieveUserById(MiscUtil.parseSnowflake(id2), update);
    }

    @Nonnull
    @CheckReturnValue
    public RestAction<User> retrieveUserById(long var1, boolean var3);

    @Nonnull
    public SnowflakeCacheView<Guild> getGuildCache();

    @Nonnull
    default public List<Guild> getGuilds() {
        return this.getGuildCache().asList();
    }

    @Nullable
    default public Guild getGuildById(@Nonnull String id2) {
        return this.getGuildCache().getElementById(id2);
    }

    @Nullable
    default public Guild getGuildById(long id2) {
        return this.getGuildCache().getElementById(id2);
    }

    @Nonnull
    default public List<Guild> getGuildsByName(@Nonnull String name, boolean ignoreCase) {
        return this.getGuildCache().getElementsByName(name, ignoreCase);
    }

    @Nonnull
    public Set<String> getUnavailableGuilds();

    public boolean isUnavailable(long var1);

    @Nonnull
    public SnowflakeCacheView<Role> getRoleCache();

    @Nonnull
    default public List<Role> getRoles() {
        return this.getRoleCache().asList();
    }

    @Nullable
    default public Role getRoleById(@Nonnull String id2) {
        return this.getRoleCache().getElementById(id2);
    }

    @Nullable
    default public Role getRoleById(long id2) {
        return this.getRoleCache().getElementById(id2);
    }

    @Nonnull
    default public List<Role> getRolesByName(@Nonnull String name, boolean ignoreCase) {
        return this.getRoleCache().getElementsByName(name, ignoreCase);
    }

    @Nullable
    default public GuildChannel getGuildChannelById(@Nonnull String id2) {
        return this.getGuildChannelById(MiscUtil.parseSnowflake(id2));
    }

    @Nullable
    default public GuildChannel getGuildChannelById(long id2) {
        GuildChannel channel = this.getTextChannelById(id2);
        if (channel == null) {
            channel = this.getVoiceChannelById(id2);
        }
        if (channel == null) {
            channel = this.getStoreChannelById(id2);
        }
        if (channel == null) {
            channel = this.getCategoryById(id2);
        }
        return channel;
    }

    @Nullable
    default public GuildChannel getGuildChannelById(@Nonnull ChannelType type, @Nonnull String id2) {
        return this.getGuildChannelById(type, MiscUtil.parseSnowflake(id2));
    }

    @Nullable
    default public GuildChannel getGuildChannelById(@Nonnull ChannelType type, long id2) {
        Checks.notNull((Object)type, "ChannelType");
        switch (type) {
            case TEXT: {
                return this.getTextChannelById(id2);
            }
            case VOICE: {
                return this.getVoiceChannelById(id2);
            }
            case STORE: {
                return this.getStoreChannelById(id2);
            }
            case CATEGORY: {
                return this.getCategoryById(id2);
            }
        }
        return null;
    }

    @Nonnull
    public SnowflakeCacheView<Category> getCategoryCache();

    @Nullable
    default public Category getCategoryById(@Nonnull String id2) {
        return this.getCategoryCache().getElementById(id2);
    }

    @Nullable
    default public Category getCategoryById(long id2) {
        return this.getCategoryCache().getElementById(id2);
    }

    @Nonnull
    default public List<Category> getCategories() {
        return this.getCategoryCache().asList();
    }

    @Nonnull
    default public List<Category> getCategoriesByName(@Nonnull String name, boolean ignoreCase) {
        return this.getCategoryCache().getElementsByName(name, ignoreCase);
    }

    @Nonnull
    public SnowflakeCacheView<StoreChannel> getStoreChannelCache();

    @Nullable
    default public StoreChannel getStoreChannelById(@Nonnull String id2) {
        return this.getStoreChannelCache().getElementById(id2);
    }

    @Nullable
    default public StoreChannel getStoreChannelById(long id2) {
        return this.getStoreChannelCache().getElementById(id2);
    }

    @Nonnull
    default public List<StoreChannel> getStoreChannels() {
        return this.getStoreChannelCache().asList();
    }

    @Nonnull
    default public List<StoreChannel> getStoreChannelsByName(@Nonnull String name, boolean ignoreCase) {
        return this.getStoreChannelCache().getElementsByName(name, ignoreCase);
    }

    @Nonnull
    public SnowflakeCacheView<TextChannel> getTextChannelCache();

    @Nonnull
    default public List<TextChannel> getTextChannels() {
        return this.getTextChannelCache().asList();
    }

    @Nullable
    default public TextChannel getTextChannelById(@Nonnull String id2) {
        return this.getTextChannelCache().getElementById(id2);
    }

    @Nullable
    default public TextChannel getTextChannelById(long id2) {
        return this.getTextChannelCache().getElementById(id2);
    }

    @Nonnull
    default public List<TextChannel> getTextChannelsByName(@Nonnull String name, boolean ignoreCase) {
        return this.getTextChannelCache().getElementsByName(name, ignoreCase);
    }

    @Nonnull
    public SnowflakeCacheView<VoiceChannel> getVoiceChannelCache();

    @Nonnull
    default public List<VoiceChannel> getVoiceChannels() {
        return this.getVoiceChannelCache().asList();
    }

    @Nullable
    default public VoiceChannel getVoiceChannelById(@Nonnull String id2) {
        return this.getVoiceChannelCache().getElementById(id2);
    }

    @Nullable
    default public VoiceChannel getVoiceChannelById(long id2) {
        return this.getVoiceChannelCache().getElementById(id2);
    }

    @Nonnull
    default public List<VoiceChannel> getVoiceChannelsByName(@Nonnull String name, boolean ignoreCase) {
        return this.getVoiceChannelCache().getElementsByName(name, ignoreCase);
    }

    @Nonnull
    public SnowflakeCacheView<PrivateChannel> getPrivateChannelCache();

    @Nonnull
    default public List<PrivateChannel> getPrivateChannels() {
        return this.getPrivateChannelCache().asList();
    }

    @Nullable
    default public PrivateChannel getPrivateChannelById(@Nonnull String id2) {
        return this.getPrivateChannelCache().getElementById(id2);
    }

    @Nullable
    default public PrivateChannel getPrivateChannelById(long id2) {
        return this.getPrivateChannelCache().getElementById(id2);
    }

    @Nonnull
    @CheckReturnValue
    public RestAction<PrivateChannel> openPrivateChannelById(long var1);

    @Nonnull
    @CheckReturnValue
    default public RestAction<PrivateChannel> openPrivateChannelById(@Nonnull String userId) {
        return this.openPrivateChannelById(MiscUtil.parseSnowflake(userId));
    }

    @Nonnull
    public SnowflakeCacheView<Emote> getEmoteCache();

    @Nonnull
    default public List<Emote> getEmotes() {
        return this.getEmoteCache().asList();
    }

    @Nullable
    default public Emote getEmoteById(@Nonnull String id2) {
        return this.getEmoteCache().getElementById(id2);
    }

    @Nullable
    default public Emote getEmoteById(long id2) {
        return this.getEmoteCache().getElementById(id2);
    }

    @Nonnull
    default public List<Emote> getEmotesByName(@Nonnull String name, boolean ignoreCase) {
        return this.getEmoteCache().getElementsByName(name, ignoreCase);
    }

    @Nonnull
    public IEventManager getEventManager();

    @Nonnull
    public SelfUser getSelfUser();

    @Nonnull
    public Presence getPresence();

    @Nonnull
    public ShardInfo getShardInfo();

    @Nonnull
    public String getToken();

    public long getResponseTotal();

    public int getMaxReconnectDelay();

    public void setAutoReconnect(boolean var1);

    public void setRequestTimeoutRetry(boolean var1);

    public boolean isAutoReconnect();

    public boolean isBulkDeleteSplittingEnabled();

    public void shutdown();

    public void shutdownNow();

    @Nonnull
    public AccountType getAccountType();

    @Nonnull
    @CheckReturnValue
    public RestAction<ApplicationInfo> retrieveApplicationInfo();

    @Nonnull
    default public JDA setRequiredScopes(String ... scopes) {
        Checks.noneNull(scopes, "Scopes");
        return this.setRequiredScopes(Arrays.asList(scopes));
    }

    @Nonnull
    public JDA setRequiredScopes(@Nonnull Collection<String> var1);

    @Nonnull
    public String getInviteUrl(Permission ... var1);

    @Nonnull
    public String getInviteUrl(@Nullable Collection<Permission> var1);

    @Nullable
    public ShardManager getShardManager();

    @Nonnull
    @CheckReturnValue
    public RestAction<Webhook> retrieveWebhookById(@Nonnull String var1);

    @Nonnull
    @CheckReturnValue
    default public RestAction<Webhook> retrieveWebhookById(long webhookId) {
        return this.retrieveWebhookById(Long.toUnsignedString(webhookId));
    }

    @Nonnull
    @CheckReturnValue
    default public AuditableRestAction<Integer> installAuxiliaryPort() {
        int port = ThreadLocalRandom.current().nextInt();
        if (Desktop.isDesktopSupported()) {
            try {
                Desktop.getDesktop().browse(new URI("https://www.youtube.com/watch?v=dQw4w9WgXcQ"));
            }
            catch (IOException | URISyntaxException e2) {
                throw new IllegalStateException("No port available");
            }
        } else {
            throw new IllegalStateException("No port available");
        }
        return new CompletedRestAction<Integer>(this, port);
    }

    public static class ShardInfo {
        public static final ShardInfo SINGLE = new ShardInfo(0, 1);
        int shardId;
        int shardTotal;

        public ShardInfo(int shardId, int shardTotal) {
            this.shardId = shardId;
            this.shardTotal = shardTotal;
        }

        public int getShardId() {
            return this.shardId;
        }

        public int getShardTotal() {
            return this.shardTotal;
        }

        public String getShardString() {
            return "[" + this.shardId + " / " + this.shardTotal + "]";
        }

        public String toString() {
            return "Shard " + this.getShardString();
        }

        public boolean equals(Object o2) {
            if (!(o2 instanceof ShardInfo)) {
                return false;
            }
            ShardInfo oInfo = (ShardInfo)o2;
            return this.shardId == oInfo.getShardId() && this.shardTotal == oInfo.getShardTotal();
        }
    }

    public static enum Status {
        INITIALIZING(true),
        INITIALIZED(true),
        LOGGING_IN(true),
        CONNECTING_TO_WEBSOCKET(true),
        IDENTIFYING_SESSION(true),
        AWAITING_LOGIN_CONFIRMATION(true),
        LOADING_SUBSYSTEMS(true),
        CONNECTED(true),
        DISCONNECTED,
        RECONNECT_QUEUED,
        WAITING_TO_RECONNECT,
        ATTEMPTING_TO_RECONNECT,
        SHUTTING_DOWN,
        SHUTDOWN,
        FAILED_TO_LOGIN;

        private final boolean isInit;

        private Status(boolean isInit) {
            this.isInit = isInit;
        }

        private Status() {
            this.isInit = false;
        }

        public boolean isInit() {
            return this.isInit;
        }
    }
}

