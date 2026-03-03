/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.sharding;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.stream.Collectors;
import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.security.auth.login.LoginException;
import net.dv8tion.jda.annotations.DeprecatedSince;
import net.dv8tion.jda.annotations.ReplaceWith;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.ApplicationInfo;
import net.dv8tion.jda.api.entities.Category;
import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.entities.Emote;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.GuildChannel;
import net.dv8tion.jda.api.entities.PrivateChannel;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.StoreChannel;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.requests.RestAction;
import net.dv8tion.jda.api.utils.MiscUtil;
import net.dv8tion.jda.api.utils.cache.CacheView;
import net.dv8tion.jda.api.utils.cache.ShardCacheView;
import net.dv8tion.jda.api.utils.cache.SnowflakeCacheView;
import net.dv8tion.jda.internal.JDAImpl;
import net.dv8tion.jda.internal.requests.CompletedRestAction;
import net.dv8tion.jda.internal.requests.RestActionImpl;
import net.dv8tion.jda.internal.requests.Route;
import net.dv8tion.jda.internal.utils.Checks;

public interface ShardManager {
    default public void addEventListener(Object ... listeners) {
        Checks.noneNull(listeners, "listeners");
        this.getShardCache().forEach(jda -> jda.addEventListener(listeners));
    }

    default public void removeEventListener(Object ... listeners) {
        Checks.noneNull(listeners, "listeners");
        this.getShardCache().forEach(jda -> jda.removeEventListener(listeners));
    }

    default public void addEventListeners(@Nonnull IntFunction<Object> eventListenerProvider) {
        Checks.notNull(eventListenerProvider, "event listener provider");
        this.getShardCache().forEach(jda -> {
            Object listener = eventListenerProvider.apply(jda.getShardInfo().getShardId());
            if (listener != null) {
                jda.addEventListener(listener);
            }
        });
    }

    default public void removeEventListeners(@Nonnull IntFunction<Collection<Object>> eventListenerProvider) {
        Checks.notNull(eventListenerProvider, "event listener provider");
        this.getShardCache().forEach(jda -> jda.removeEventListener(eventListenerProvider.apply(jda.getShardInfo().getShardId())));
    }

    default public void removeEventListenerProvider(@Nonnull IntFunction<Object> eventListenerProvider) {
    }

    public int getShardsQueued();

    default public int getShardsRunning() {
        return (int)this.getShardCache().size();
    }

    default public int getShardsTotal() {
        return this.getShardsQueued() + this.getShardsRunning();
    }

    @Nonnull
    default public EnumSet<GatewayIntent> getGatewayIntents() {
        return this.getShardCache().applyStream(stream -> stream.map(JDA::getGatewayIntents).findAny().orElse(EnumSet.noneOf(GatewayIntent.class)));
    }

    @Nonnull
    default public RestAction<ApplicationInfo> retrieveApplicationInfo() {
        return ((JDA)this.getShardCache().stream().findAny().orElseThrow(() -> new IllegalStateException("no active shards"))).retrieveApplicationInfo();
    }

    default public double getAverageGatewayPing() {
        return this.getShardCache().stream().mapToLong(JDA::getGatewayPing).filter(ping -> ping != -1L).average().orElse(-1.0);
    }

    @Nonnull
    default public List<Category> getCategories() {
        return this.getCategoryCache().asList();
    }

    @Nonnull
    default public List<Category> getCategoriesByName(@Nonnull String name, boolean ignoreCase) {
        return this.getCategoryCache().getElementsByName(name, ignoreCase);
    }

    @Nullable
    default public Category getCategoryById(long id2) {
        return this.getCategoryCache().getElementById(id2);
    }

    @Nullable
    default public Category getCategoryById(@Nonnull String id2) {
        return this.getCategoryCache().getElementById(id2);
    }

    @Nonnull
    default public SnowflakeCacheView<Category> getCategoryCache() {
        return CacheView.allSnowflakes(() -> this.getShardCache().stream().map(JDA::getCategoryCache));
    }

    @Nullable
    default public Emote getEmoteById(long id2) {
        return this.getEmoteCache().getElementById(id2);
    }

    @Nullable
    default public Emote getEmoteById(@Nonnull String id2) {
        return this.getEmoteCache().getElementById(id2);
    }

    @Nonnull
    default public SnowflakeCacheView<Emote> getEmoteCache() {
        return CacheView.allSnowflakes(() -> this.getShardCache().stream().map(JDA::getEmoteCache));
    }

    @Nonnull
    default public List<Emote> getEmotes() {
        return this.getEmoteCache().asList();
    }

    @Nonnull
    default public List<Emote> getEmotesByName(@Nonnull String name, boolean ignoreCase) {
        return this.getEmoteCache().getElementsByName(name, ignoreCase);
    }

    @Nullable
    default public Guild getGuildById(long id2) {
        return this.getGuildCache().getElementById(id2);
    }

    @Nullable
    default public Guild getGuildById(@Nonnull String id2) {
        return this.getGuildById(MiscUtil.parseSnowflake(id2));
    }

    @Nonnull
    default public List<Guild> getGuildsByName(@Nonnull String name, boolean ignoreCase) {
        return this.getGuildCache().getElementsByName(name, ignoreCase);
    }

    @Nonnull
    default public SnowflakeCacheView<Guild> getGuildCache() {
        return CacheView.allSnowflakes(() -> this.getShardCache().stream().map(JDA::getGuildCache));
    }

    @Nonnull
    default public List<Guild> getGuilds() {
        return this.getGuildCache().asList();
    }

    @Nonnull
    default public List<Guild> getMutualGuilds(@Nonnull Collection<User> users) {
        Checks.noneNull(users, "users");
        return Collections.unmodifiableList(this.getGuildCache().stream().filter(guild -> users.stream().allMatch(guild::isMember)).collect(Collectors.toList()));
    }

    @Nonnull
    default public List<Guild> getMutualGuilds(User ... users) {
        Checks.notNull(users, "users");
        return this.getMutualGuilds(Arrays.asList(users));
    }

    @Nonnull
    @CheckReturnValue
    default public RestAction<User> retrieveUserById(@Nonnull String id2) {
        return this.retrieveUserById(MiscUtil.parseSnowflake(id2));
    }

    @Nonnull
    @CheckReturnValue
    default public RestAction<User> retrieveUserById(long id2) {
        JDA api2 = null;
        Iterator iterator = this.getShardCache().iterator();
        while (iterator.hasNext()) {
            boolean isUpdated;
            JDA shard;
            api2 = shard = (JDA)iterator.next();
            EnumSet<GatewayIntent> intents = shard.getGatewayIntents();
            User user = shard.getUserById(id2);
            boolean bl2 = isUpdated = intents.contains((Object)GatewayIntent.GUILD_PRESENCES) || intents.contains((Object)GatewayIntent.GUILD_MEMBERS);
            if (user == null || !isUpdated) continue;
            return new CompletedRestAction<User>(shard, user);
        }
        if (api2 == null) {
            throw new IllegalStateException("no shards active");
        }
        JDAImpl jda = (JDAImpl)api2;
        Route.CompiledRoute route = Route.Users.GET_USER.compile(Long.toUnsignedString(id2));
        return new RestActionImpl<User>((JDA)jda, route, (response, request) -> jda.getEntityBuilder().createUser(response.getObject()));
    }

    @Nullable
    default public User getUserByTag(@Nonnull String tag) {
        return this.getShardCache().applyStream(stream -> stream.map(jda -> jda.getUserByTag(tag)).filter(Objects::nonNull).findFirst().orElse(null));
    }

    @Nullable
    default public User getUserByTag(@Nonnull String username, @Nonnull String discriminator) {
        return this.getShardCache().applyStream(stream -> stream.map(jda -> jda.getUserByTag(username, discriminator)).filter(Objects::nonNull).findFirst().orElse(null));
    }

    @Nullable
    default public PrivateChannel getPrivateChannelById(long id2) {
        return this.getPrivateChannelCache().getElementById(id2);
    }

    @Nullable
    default public PrivateChannel getPrivateChannelById(@Nonnull String id2) {
        return this.getPrivateChannelCache().getElementById(id2);
    }

    @Nonnull
    default public SnowflakeCacheView<PrivateChannel> getPrivateChannelCache() {
        return CacheView.allSnowflakes(() -> this.getShardCache().stream().map(JDA::getPrivateChannelCache));
    }

    @Nonnull
    default public List<PrivateChannel> getPrivateChannels() {
        return this.getPrivateChannelCache().asList();
    }

    @Nullable
    default public Role getRoleById(long id2) {
        return this.getRoleCache().getElementById(id2);
    }

    @Nullable
    default public Role getRoleById(@Nonnull String id2) {
        return this.getRoleCache().getElementById(id2);
    }

    @Nonnull
    default public SnowflakeCacheView<Role> getRoleCache() {
        return CacheView.allSnowflakes(() -> this.getShardCache().stream().map(JDA::getRoleCache));
    }

    @Nonnull
    default public List<Role> getRoles() {
        return this.getRoleCache().asList();
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
        for (JDA shard : this.getShards()) {
            GuildChannel channel = shard.getGuildChannelById(id2);
            if (channel == null) continue;
            return channel;
        }
        return null;
    }

    @Nullable
    default public GuildChannel getGuildChannelById(@Nonnull ChannelType type, @Nonnull String id2) {
        return this.getGuildChannelById(type, MiscUtil.parseSnowflake(id2));
    }

    @Nullable
    default public GuildChannel getGuildChannelById(@Nonnull ChannelType type, long id2) {
        Checks.notNull((Object)type, "ChannelType");
        for (JDA shard : this.getShards()) {
            GuildChannel channel = shard.getGuildChannelById(type, id2);
            if (channel == null) continue;
            return channel;
        }
        return null;
    }

    @Nullable
    default public JDA getShardById(int id2) {
        return this.getShardCache().getElementById(id2);
    }

    @Nullable
    default public JDA getShardById(@Nonnull String id2) {
        return this.getShardCache().getElementById(id2);
    }

    @Nonnull
    public ShardCacheView getShardCache();

    @Nonnull
    default public List<JDA> getShards() {
        return this.getShardCache().asList();
    }

    @Nullable
    default public JDA.Status getStatus(int shardId) {
        JDA jda = this.getShardCache().getElementById(shardId);
        return jda == null ? null : jda.getStatus();
    }

    @Nonnull
    default public Map<JDA, JDA.Status> getStatuses() {
        return Collections.unmodifiableMap(this.getShardCache().stream().collect(Collectors.toMap(Function.identity(), JDA::getStatus)));
    }

    @Nullable
    default public TextChannel getTextChannelById(long id2) {
        return this.getTextChannelCache().getElementById(id2);
    }

    @Nullable
    default public TextChannel getTextChannelById(@Nonnull String id2) {
        return this.getTextChannelCache().getElementById(id2);
    }

    @Nonnull
    default public SnowflakeCacheView<TextChannel> getTextChannelCache() {
        return CacheView.allSnowflakes(() -> this.getShardCache().stream().map(JDA::getTextChannelCache));
    }

    @Nonnull
    default public List<TextChannel> getTextChannels() {
        return this.getTextChannelCache().asList();
    }

    @Nullable
    default public StoreChannel getStoreChannelById(long id2) {
        return this.getStoreChannelCache().getElementById(id2);
    }

    @Nullable
    default public StoreChannel getStoreChannelById(@Nonnull String id2) {
        return this.getStoreChannelCache().getElementById(id2);
    }

    @Nonnull
    default public SnowflakeCacheView<StoreChannel> getStoreChannelCache() {
        return CacheView.allSnowflakes(() -> this.getShardCache().stream().map(JDA::getStoreChannelCache));
    }

    @Nonnull
    default public List<StoreChannel> getStoreChannels() {
        return this.getStoreChannelCache().asList();
    }

    @Nullable
    default public User getUserById(long id2) {
        return this.getUserCache().getElementById(id2);
    }

    @Nullable
    default public User getUserById(@Nonnull String id2) {
        return this.getUserCache().getElementById(id2);
    }

    @Nonnull
    default public SnowflakeCacheView<User> getUserCache() {
        return CacheView.allSnowflakes(() -> this.getShardCache().stream().map(JDA::getUserCache));
    }

    @Nonnull
    default public List<User> getUsers() {
        return this.getUserCache().asList();
    }

    @Nullable
    default public VoiceChannel getVoiceChannelById(long id2) {
        return this.getVoiceChannelCache().getElementById(id2);
    }

    @Nullable
    default public VoiceChannel getVoiceChannelById(@Nonnull String id2) {
        return this.getVoiceChannelCache().getElementById(id2);
    }

    @Nonnull
    default public SnowflakeCacheView<VoiceChannel> getVoiceChannelCache() {
        return CacheView.allSnowflakes(() -> this.getShardCache().stream().map(JDA::getVoiceChannelCache));
    }

    @Nonnull
    default public List<VoiceChannel> getVoiceChannels() {
        return this.getVoiceChannelCache().asList();
    }

    public void restart();

    public void restart(int var1);

    @Deprecated
    @DeprecatedSince(value="4.0.0")
    @ReplaceWith(value="setActivity()")
    default public void setGame(@Nullable Activity game) {
        this.setActivityProvider(id2 -> game);
    }

    default public void setActivity(@Nullable Activity activity) {
        this.setActivityProvider(id2 -> activity);
    }

    default public void setActivityProvider(@Nullable IntFunction<? extends Activity> activityProvider) {
        this.getShardCache().forEach(jda -> jda.getPresence().setActivity(activityProvider == null ? null : (Activity)activityProvider.apply(jda.getShardInfo().getShardId())));
    }

    default public void setIdle(boolean idle) {
        this.setIdleProvider(id2 -> idle);
    }

    default public void setIdleProvider(@Nonnull IntFunction<Boolean> idleProvider) {
        this.getShardCache().forEach(jda -> jda.getPresence().setIdle((Boolean)idleProvider.apply(jda.getShardInfo().getShardId())));
    }

    default public void setPresence(@Nullable OnlineStatus status, @Nullable Activity activity) {
        this.setPresenceProvider(id2 -> status, id2 -> activity);
    }

    default public void setPresenceProvider(@Nullable IntFunction<OnlineStatus> statusProvider, @Nullable IntFunction<? extends Activity> activityProvider) {
        this.getShardCache().forEach(jda -> jda.getPresence().setPresence(statusProvider == null ? null : (OnlineStatus)((Object)((Object)statusProvider.apply(jda.getShardInfo().getShardId()))), activityProvider == null ? null : (Activity)activityProvider.apply(jda.getShardInfo().getShardId())));
    }

    default public void setStatus(@Nullable OnlineStatus status) {
        this.setStatusProvider(id2 -> status);
    }

    default public void setStatusProvider(@Nullable IntFunction<OnlineStatus> statusProvider) {
        this.getShardCache().forEach(jda -> jda.getPresence().setStatus(statusProvider == null ? null : (OnlineStatus)((Object)((Object)statusProvider.apply(jda.getShardInfo().getShardId())))));
    }

    public void shutdown();

    public void shutdown(int var1);

    public void start(int var1);

    public void login() throws LoginException;
}

