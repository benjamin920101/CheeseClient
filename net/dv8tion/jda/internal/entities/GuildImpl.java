/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.internal.entities;

import java.time.OffsetDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.dv8tion.jda.api.AccountType;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.Region;
import net.dv8tion.jda.api.entities.AbstractChannel;
import net.dv8tion.jda.api.entities.Category;
import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.entities.Emote;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.GuildChannel;
import net.dv8tion.jda.api.entities.GuildVoiceState;
import net.dv8tion.jda.api.entities.ISnowflake;
import net.dv8tion.jda.api.entities.Icon;
import net.dv8tion.jda.api.entities.Invite;
import net.dv8tion.jda.api.entities.ListedEmote;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.StoreChannel;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.VanityInvite;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.entities.Webhook;
import net.dv8tion.jda.api.entities.templates.Template;
import net.dv8tion.jda.api.exceptions.HierarchyException;
import net.dv8tion.jda.api.exceptions.InsufficientPermissionException;
import net.dv8tion.jda.api.exceptions.PermissionException;
import net.dv8tion.jda.api.interactions.commands.Command;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.privileges.CommandPrivilege;
import net.dv8tion.jda.api.managers.AudioManager;
import net.dv8tion.jda.api.managers.GuildManager;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.requests.RestAction;
import net.dv8tion.jda.api.requests.restaction.AuditableRestAction;
import net.dv8tion.jda.api.requests.restaction.ChannelAction;
import net.dv8tion.jda.api.requests.restaction.CommandCreateAction;
import net.dv8tion.jda.api.requests.restaction.CommandEditAction;
import net.dv8tion.jda.api.requests.restaction.CommandListUpdateAction;
import net.dv8tion.jda.api.requests.restaction.MemberAction;
import net.dv8tion.jda.api.requests.restaction.RoleAction;
import net.dv8tion.jda.api.requests.restaction.order.CategoryOrderAction;
import net.dv8tion.jda.api.requests.restaction.order.ChannelOrderAction;
import net.dv8tion.jda.api.requests.restaction.order.RoleOrderAction;
import net.dv8tion.jda.api.requests.restaction.pagination.AuditLogPaginationAction;
import net.dv8tion.jda.api.utils.cache.CacheFlag;
import net.dv8tion.jda.api.utils.cache.CacheView;
import net.dv8tion.jda.api.utils.cache.MemberCacheView;
import net.dv8tion.jda.api.utils.cache.SnowflakeCacheView;
import net.dv8tion.jda.api.utils.cache.SortedSnowflakeCacheView;
import net.dv8tion.jda.api.utils.concurrent.Task;
import net.dv8tion.jda.api.utils.data.DataArray;
import net.dv8tion.jda.api.utils.data.DataObject;
import net.dv8tion.jda.internal.JDAImpl;
import net.dv8tion.jda.internal.entities.EmoteImpl;
import net.dv8tion.jda.internal.entities.EntityBuilder;
import net.dv8tion.jda.internal.entities.MemberImpl;
import net.dv8tion.jda.internal.entities.MemberPresenceImpl;
import net.dv8tion.jda.internal.entities.WebhookImpl;
import net.dv8tion.jda.internal.managers.AudioManagerImpl;
import net.dv8tion.jda.internal.managers.GuildManagerImpl;
import net.dv8tion.jda.internal.requests.CompletedRestAction;
import net.dv8tion.jda.internal.requests.DeferredRestAction;
import net.dv8tion.jda.internal.requests.MemberChunkManager;
import net.dv8tion.jda.internal.requests.Requester;
import net.dv8tion.jda.internal.requests.RestActionImpl;
import net.dv8tion.jda.internal.requests.Route;
import net.dv8tion.jda.internal.requests.WebSocketClient;
import net.dv8tion.jda.internal.requests.restaction.AuditableRestActionImpl;
import net.dv8tion.jda.internal.requests.restaction.ChannelActionImpl;
import net.dv8tion.jda.internal.requests.restaction.CommandCreateActionImpl;
import net.dv8tion.jda.internal.requests.restaction.CommandEditActionImpl;
import net.dv8tion.jda.internal.requests.restaction.CommandListUpdateActionImpl;
import net.dv8tion.jda.internal.requests.restaction.MemberActionImpl;
import net.dv8tion.jda.internal.requests.restaction.RoleActionImpl;
import net.dv8tion.jda.internal.requests.restaction.order.CategoryOrderActionImpl;
import net.dv8tion.jda.internal.requests.restaction.order.ChannelOrderActionImpl;
import net.dv8tion.jda.internal.requests.restaction.order.RoleOrderActionImpl;
import net.dv8tion.jda.internal.requests.restaction.pagination.AuditLogPaginationActionImpl;
import net.dv8tion.jda.internal.utils.Checks;
import net.dv8tion.jda.internal.utils.EncodingUtil;
import net.dv8tion.jda.internal.utils.Helpers;
import net.dv8tion.jda.internal.utils.PermissionUtil;
import net.dv8tion.jda.internal.utils.UnlockHook;
import net.dv8tion.jda.internal.utils.cache.AbstractCacheView;
import net.dv8tion.jda.internal.utils.cache.MemberCacheViewImpl;
import net.dv8tion.jda.internal.utils.cache.SnowflakeCacheViewImpl;
import net.dv8tion.jda.internal.utils.cache.SortedSnowflakeCacheViewImpl;
import net.dv8tion.jda.internal.utils.concurrent.task.GatewayTask;
import okhttp3.RequestBody;

public class GuildImpl
implements Guild {
    private final long id;
    private final JDAImpl api;
    private final SortedSnowflakeCacheViewImpl<Category> categoryCache = new SortedSnowflakeCacheViewImpl<Category>(Category.class, AbstractChannel::getName, Comparator.naturalOrder());
    private final SortedSnowflakeCacheViewImpl<VoiceChannel> voiceChannelCache = new SortedSnowflakeCacheViewImpl<VoiceChannel>(VoiceChannel.class, AbstractChannel::getName, Comparator.naturalOrder());
    private final SortedSnowflakeCacheViewImpl<StoreChannel> storeChannelCache = new SortedSnowflakeCacheViewImpl<StoreChannel>(StoreChannel.class, AbstractChannel::getName, Comparator.naturalOrder());
    private final SortedSnowflakeCacheViewImpl<TextChannel> textChannelCache = new SortedSnowflakeCacheViewImpl<TextChannel>(TextChannel.class, AbstractChannel::getName, Comparator.naturalOrder());
    private final SortedSnowflakeCacheViewImpl<Role> roleCache = new SortedSnowflakeCacheViewImpl<Role>(Role.class, Role::getName, Comparator.reverseOrder());
    private final SnowflakeCacheViewImpl<Emote> emoteCache = new SnowflakeCacheViewImpl<Emote>(Emote.class, Emote::getName);
    private final MemberCacheViewImpl memberCache = new MemberCacheViewImpl();
    private final CacheView.SimpleCacheView<MemberPresenceImpl> memberPresences;
    private GuildManager manager;
    private Member owner;
    private String name;
    private String iconId;
    private String splashId;
    private String region;
    private String vanityCode;
    private String description;
    private String banner;
    private int maxPresences;
    private int maxMembers;
    private int boostCount;
    private long ownerId;
    private Set<String> features;
    private VoiceChannel afkChannel;
    private TextChannel systemChannel;
    private TextChannel rulesChannel;
    private TextChannel communityUpdatesChannel;
    private Role publicRole;
    private Guild.VerificationLevel verificationLevel = Guild.VerificationLevel.UNKNOWN;
    private Guild.NotificationLevel defaultNotificationLevel = Guild.NotificationLevel.UNKNOWN;
    private Guild.MFALevel mfaLevel = Guild.MFALevel.UNKNOWN;
    private Guild.ExplicitContentLevel explicitContentLevel = Guild.ExplicitContentLevel.UNKNOWN;
    private Guild.Timeout afkTimeout;
    private Guild.BoostTier boostTier = Guild.BoostTier.NONE;
    private Locale preferredLocale = Locale.ENGLISH;
    private boolean available;
    private boolean canSendVerification = false;
    private int memberCount;

    public GuildImpl(JDAImpl api2, long id2) {
        this.id = id2;
        this.api = api2;
        this.memberPresences = api2.getCacheFlags().stream().anyMatch(CacheFlag::isPresence) ? new CacheView.SimpleCacheView<MemberPresenceImpl>(MemberPresenceImpl.class, null) : null;
    }

    @Override
    @Nonnull
    public RestAction<List<Command>> retrieveCommands() {
        Route.CompiledRoute route = Route.Interactions.GET_GUILD_COMMANDS.compile(this.getJDA().getSelfUser().getApplicationId(), this.getId());
        return new RestActionImpl<List<Command>>((JDA)this.getJDA(), route, (response, request) -> response.getArray().stream(DataArray::getObject).map(json -> new Command(this.getJDA(), this, (DataObject)json)).collect(Collectors.toList()));
    }

    @Override
    @Nonnull
    public RestAction<Command> retrieveCommandById(@Nonnull String id2) {
        Checks.isSnowflake(id2);
        Route.CompiledRoute route = Route.Interactions.GET_GUILD_COMMAND.compile(this.getJDA().getSelfUser().getApplicationId(), this.getId(), id2);
        return new RestActionImpl<Command>((JDA)this.getJDA(), route, (response, request) -> new Command(this.getJDA(), this, response.getObject()));
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
        Route.CompiledRoute route = Route.Interactions.UPDATE_GUILD_COMMANDS.compile(this.getJDA().getSelfUser().getApplicationId(), this.getId());
        return new CommandListUpdateActionImpl((JDA)this.getJDA(), this, route);
    }

    @Override
    @Nonnull
    public CommandEditAction editCommandById(@Nonnull String id2) {
        Checks.isSnowflake(id2);
        return new CommandEditActionImpl(this, id2);
    }

    @Override
    @Nonnull
    public RestAction<Void> deleteCommandById(@Nonnull String commandId) {
        Checks.isSnowflake(commandId);
        Route.CompiledRoute route = Route.Interactions.DELETE_GUILD_COMMAND.compile(this.getJDA().getSelfUser().getApplicationId(), this.getId(), commandId);
        return new RestActionImpl<Void>(this.getJDA(), route);
    }

    @Override
    @Nonnull
    public RestAction<List<CommandPrivilege>> retrieveCommandPrivilegesById(@Nonnull String commandId) {
        Checks.isSnowflake(commandId, "ID");
        Route.CompiledRoute route = Route.Interactions.GET_COMMAND_PERMISSIONS.compile(this.getJDA().getSelfUser().getApplicationId(), this.getId(), commandId);
        return new RestActionImpl<List<CommandPrivilege>>((JDA)this.getJDA(), route, (response, request) -> this.parsePrivilegesList(response.getObject()));
    }

    @Override
    @Nonnull
    public RestAction<Map<String, List<CommandPrivilege>>> retrieveCommandPrivileges() {
        Route.CompiledRoute route = Route.Interactions.GET_ALL_COMMAND_PERMISSIONS.compile(this.getJDA().getSelfUser().getApplicationId(), this.getId());
        return new RestActionImpl<Map<String, List<CommandPrivilege>>>((JDA)this.getJDA(), route, (response, request) -> {
            HashMap privileges = new HashMap();
            response.getArray().stream(DataArray::getObject).forEach(obj -> {
                String id2 = obj.getString("id");
                List<CommandPrivilege> list = this.parsePrivilegesList((DataObject)obj);
                privileges.put(id2, list);
            });
            return privileges;
        });
    }

    @Override
    @Nonnull
    public RestAction<List<CommandPrivilege>> updateCommandPrivilegesById(@Nonnull String id2, @Nonnull Collection<? extends CommandPrivilege> privileges) {
        Checks.isSnowflake(id2, "ID");
        Checks.noneNull(privileges, "Privileges");
        Checks.check(privileges.size() <= 10, "Cannot have more than 10 privileges for a command!");
        Route.CompiledRoute route = Route.Interactions.EDIT_COMMAND_PERMISSIONS.compile(this.getJDA().getSelfUser().getApplicationId(), this.getId(), id2);
        DataArray array = DataArray.fromCollection(privileges);
        return new RestActionImpl<List<CommandPrivilege>>((JDA)this.getJDA(), route, DataObject.empty().put("permissions", array), (response, request) -> this.parsePrivilegesList(response.getObject()));
    }

    @Override
    @Nonnull
    public RestAction<Map<String, List<CommandPrivilege>>> updateCommandPrivileges(@Nonnull Map<String, Collection<? extends CommandPrivilege>> privileges) {
        Checks.notNull(privileges, "Privileges");
        privileges.forEach((key, value) -> {
            Checks.isSnowflake(key, "Map Key");
            Checks.noneNull(value, "Privilege List for Command");
            Checks.check(value.size() <= 10, "Cannot have more than 10 privileges for a command!");
        });
        DataArray array = DataArray.empty();
        privileges.forEach((commandId, list) -> {
            DataObject entry = DataObject.empty();
            entry.put("id", commandId);
            entry.put("permissions", DataArray.fromCollection(list));
            array.add(entry);
        });
        Route.CompiledRoute route = Route.Interactions.EDIT_ALL_COMMAND_PERMISSIONS.compile(this.getJDA().getSelfUser().getApplicationId(), this.getId());
        return new RestActionImpl<Map<String, List<CommandPrivilege>>>((JDA)this.getJDA(), route, RequestBody.create(Requester.MEDIA_TYPE_JSON, array.toJson()), (response, request) -> {
            HashMap map = new HashMap();
            response.getArray().stream(DataArray::getObject).forEach(obj -> {
                String id2 = obj.getString("id");
                List<CommandPrivilege> list = this.parsePrivilegesList((DataObject)obj);
                map.put(id2, list);
            });
            return map;
        });
    }

    private List<CommandPrivilege> parsePrivilegesList(DataObject obj) {
        return obj.getArray("permissions").stream(DataArray::getObject).map(this::parsePrivilege).collect(Collectors.toList());
    }

    private CommandPrivilege parsePrivilege(DataObject data) {
        CommandPrivilege.Type type = CommandPrivilege.Type.fromKey(data.getInt("type", 1));
        boolean enabled = data.getBoolean("permission");
        return new CommandPrivilege(type, enabled, data.getUnsignedLong("id"));
    }

    @Override
    @Nonnull
    public RestAction<EnumSet<Region>> retrieveRegions(boolean includeDeprecated) {
        Route.CompiledRoute route = Route.Guilds.GET_VOICE_REGIONS.compile(this.getId());
        return new RestActionImpl<EnumSet<Region>>((JDA)this.getJDA(), route, (response, request) -> {
            EnumSet<Region> set = EnumSet.noneOf(Region.class);
            DataArray arr2 = response.getArray();
            for (int i2 = 0; i2 < arr2.length(); ++i2) {
                String id2;
                Region region;
                DataObject obj = arr2.getObject(i2);
                if (!includeDeprecated && obj.getBoolean("deprecated") || (region = Region.fromKey(id2 = obj.getString("id", ""))) == Region.UNKNOWN) continue;
                set.add(region);
            }
            return set;
        });
    }

    @Override
    @Nonnull
    public MemberAction addMember(@Nonnull String accessToken, @Nonnull String userId) {
        Checks.notBlank(accessToken, "Access-Token");
        Checks.isSnowflake(userId, "User ID");
        Checks.check(this.getMemberById(userId) == null, "User is already in this guild");
        if (!this.getSelfMember().hasPermission(Permission.CREATE_INSTANT_INVITE)) {
            throw new InsufficientPermissionException(this, Permission.CREATE_INSTANT_INVITE);
        }
        return new MemberActionImpl((JDA)this.getJDA(), this, userId, accessToken);
    }

    @Override
    public boolean isLoaded() {
        return this.getJDA().isIntent(GatewayIntent.GUILD_MEMBERS) && (long)this.getMemberCount() <= this.getMemberCache().size();
    }

    @Override
    public void pruneMemberCache() {
        try (UnlockHook h2 = this.memberCache.writeLock();){
            EntityBuilder builder = this.getJDA().getEntityBuilder();
            Set<Member> members = this.memberCache.asSet();
            members.forEach(m2 -> builder.updateMemberCache((MemberImpl)m2));
        }
    }

    @Override
    public boolean unloadMember(long userId) {
        if (userId == this.api.getSelfUser().getIdLong()) {
            return false;
        }
        MemberImpl member = (MemberImpl)this.getMemberById(userId);
        if (member == null) {
            return false;
        }
        this.api.getEntityBuilder().updateMemberCache(member, true);
        return true;
    }

    @Override
    public int getMemberCount() {
        return this.memberCount;
    }

    @Override
    @Nonnull
    public String getName() {
        return this.name;
    }

    @Override
    public String getIconId() {
        return this.iconId;
    }

    @Override
    @Nonnull
    public Set<String> getFeatures() {
        return this.features;
    }

    @Override
    public String getSplashId() {
        return this.splashId;
    }

    @Override
    @Nonnull
    @Deprecated
    public RestAction<String> retrieveVanityUrl() {
        if (!this.getSelfMember().hasPermission(Permission.MANAGE_SERVER)) {
            throw new InsufficientPermissionException(this, Permission.MANAGE_SERVER);
        }
        if (!this.getFeatures().contains("VANITY_URL")) {
            throw new IllegalStateException("This guild doesn't have a vanity url");
        }
        Route.CompiledRoute route = Route.Guilds.GET_VANITY_URL.compile(this.getId());
        return new RestActionImpl<String>((JDA)this.getJDA(), route, (response, request) -> response.getObject().getString("code"));
    }

    @Override
    @Nullable
    public String getVanityCode() {
        return this.vanityCode;
    }

    @Override
    @Nonnull
    public RestAction<VanityInvite> retrieveVanityInvite() {
        this.checkPermission(Permission.MANAGE_SERVER);
        JDAImpl api2 = this.getJDA();
        Route.CompiledRoute route = Route.Guilds.GET_VANITY_URL.compile(this.getId());
        return new RestActionImpl<VanityInvite>((JDA)api2, route, (response, request) -> new VanityInvite(this.vanityCode, response.getObject().getInt("uses")));
    }

    @Override
    @Nullable
    public String getDescription() {
        return this.description;
    }

    @Override
    @Nonnull
    public Locale getLocale() {
        return this.preferredLocale;
    }

    @Override
    @Nullable
    public String getBannerId() {
        return this.banner;
    }

    @Override
    @Nonnull
    public Guild.BoostTier getBoostTier() {
        return this.boostTier;
    }

    @Override
    public int getBoostCount() {
        return this.boostCount;
    }

    @Override
    @Nonnull
    public List<Member> getBoosters() {
        return this.memberCache.applyStream(members -> members.filter(m2 -> m2.getTimeBoosted() != null).sorted(Comparator.comparing(Member::getTimeBoosted)).collect(Collectors.toList()));
    }

    @Override
    public int getMaxMembers() {
        return this.maxMembers;
    }

    @Override
    public int getMaxPresences() {
        return this.maxPresences;
    }

    @Override
    @Nonnull
    public RestAction<Guild.MetaData> retrieveMetaData() {
        Route.CompiledRoute route = Route.Guilds.GET_GUILD.compile(this.getId());
        route = route.withQueryParams("with_counts", "true");
        return new RestActionImpl<Guild.MetaData>((JDA)this.getJDA(), route, (response, request) -> {
            DataObject json = response.getObject();
            int memberLimit = json.getInt("max_members", 0);
            int presenceLimit = json.getInt("max_presences", 5000);
            this.maxMembers = memberLimit;
            this.maxPresences = presenceLimit;
            int approxMembers = json.getInt("approximate_member_count", this.memberCount);
            int approxPresence = json.getInt("approximate_presence_count", 0);
            return new Guild.MetaData(memberLimit, presenceLimit, approxPresence, approxMembers);
        });
    }

    @Override
    public VoiceChannel getAfkChannel() {
        return this.afkChannel;
    }

    @Override
    public TextChannel getSystemChannel() {
        return this.systemChannel;
    }

    @Override
    public TextChannel getRulesChannel() {
        return this.rulesChannel;
    }

    @Override
    public TextChannel getCommunityUpdatesChannel() {
        return this.communityUpdatesChannel;
    }

    @Override
    @Nonnull
    public RestAction<List<Webhook>> retrieveWebhooks() {
        if (!this.getSelfMember().hasPermission(Permission.MANAGE_WEBHOOKS)) {
            throw new InsufficientPermissionException(this, Permission.MANAGE_WEBHOOKS);
        }
        Route.CompiledRoute route = Route.Guilds.GET_WEBHOOKS.compile(this.getId());
        return new RestActionImpl<List<Webhook>>((JDA)this.getJDA(), route, (response, request) -> {
            DataArray array = response.getArray();
            ArrayList<WebhookImpl> webhooks = new ArrayList<WebhookImpl>(array.length());
            EntityBuilder builder = this.api.getEntityBuilder();
            for (int i2 = 0; i2 < array.length(); ++i2) {
                try {
                    webhooks.add(builder.createWebhook(array.getObject(i2)));
                    continue;
                }
                catch (Exception e2) {
                    JDAImpl.LOG.error("Error creating webhook from json", e2);
                }
            }
            return Collections.unmodifiableList(webhooks);
        });
    }

    @Override
    public Member getOwner() {
        return this.owner;
    }

    @Override
    public long getOwnerIdLong() {
        return this.ownerId;
    }

    @Override
    @Nonnull
    public Guild.Timeout getAfkTimeout() {
        return this.afkTimeout;
    }

    @Override
    @Nonnull
    public String getRegionRaw() {
        return this.region;
    }

    @Override
    public boolean isMember(@Nonnull User user) {
        return this.memberCache.get(user.getIdLong()) != null;
    }

    @Override
    @Nonnull
    public Member getSelfMember() {
        Member member = this.getMember(this.getJDA().getSelfUser());
        if (member == null) {
            throw new IllegalStateException("Guild does not have a self member");
        }
        return member;
    }

    @Override
    public Member getMember(@Nonnull User user) {
        Checks.notNull(user, "User");
        return this.getMemberById(user.getIdLong());
    }

    @Override
    @Nonnull
    public MemberCacheView getMemberCache() {
        return this.memberCache;
    }

    @Override
    @Nonnull
    public SortedSnowflakeCacheView<Category> getCategoryCache() {
        return this.categoryCache;
    }

    @Override
    @Nonnull
    public SortedSnowflakeCacheView<StoreChannel> getStoreChannelCache() {
        return this.storeChannelCache;
    }

    @Override
    @Nonnull
    public SortedSnowflakeCacheView<TextChannel> getTextChannelCache() {
        return this.textChannelCache;
    }

    @Override
    @Nonnull
    public SortedSnowflakeCacheView<VoiceChannel> getVoiceChannelCache() {
        return this.voiceChannelCache;
    }

    @Override
    @Nonnull
    public SortedSnowflakeCacheView<Role> getRoleCache() {
        return this.roleCache;
    }

    @Override
    @Nonnull
    public SnowflakeCacheView<Emote> getEmoteCache() {
        return this.emoteCache;
    }

    @Override
    @Nonnull
    public List<GuildChannel> getChannels(boolean includeHidden) {
        ArrayList<GuildChannel> channels;
        List categories;
        List voiceChannels;
        List textChannels;
        List storeChannels;
        Member self = this.getSelfMember();
        Predicate<GuildChannel> filterHidden = it2 -> self.hasPermission((GuildChannel)it2, Permission.VIEW_CHANNEL);
        SortedSnowflakeCacheViewImpl<Category> categoryView = this.getCategoriesView();
        SortedSnowflakeCacheViewImpl<VoiceChannel> voiceView = this.getVoiceChannelsView();
        SortedSnowflakeCacheViewImpl<TextChannel> textView = this.getTextChannelsView();
        SortedSnowflakeCacheViewImpl<StoreChannel> storeView = this.getStoreChannelView();
        try (UnlockHook categoryHook = categoryView.readLock();
             UnlockHook voiceHook = voiceView.readLock();
             UnlockHook textHook = textView.readLock();
             UnlockHook storeHook = storeView.readLock();){
            if (includeHidden) {
                storeChannels = ((AbstractCacheView)storeView).asList();
                textChannels = ((AbstractCacheView)textView).asList();
                voiceChannels = ((AbstractCacheView)voiceView).asList();
            } else {
                storeChannels = ((AbstractCacheView)storeView).stream().filter(filterHidden).collect(Collectors.toList());
                textChannels = ((AbstractCacheView)textView).stream().filter(filterHidden).collect(Collectors.toList());
                voiceChannels = ((AbstractCacheView)voiceView).stream().filter(filterHidden).collect(Collectors.toList());
            }
            categories = ((AbstractCacheView)categoryView).asList();
            channels = new ArrayList<GuildChannel>((int)categoryView.size() + voiceChannels.size() + textChannels.size() + storeChannels.size());
        }
        storeChannels.stream().filter(it2 -> it2.getParent() == null).forEach(channels::add);
        textChannels.stream().filter(it2 -> it2.getParent() == null).forEach(channels::add);
        Collections.sort(channels);
        voiceChannels.stream().filter(it2 -> it2.getParent() == null).forEach(channels::add);
        for (Category category : categories) {
            List<GuildChannel> children;
            if (includeHidden) {
                children = category.getChannels();
            } else {
                children = category.getChannels().stream().filter(filterHidden).collect(Collectors.toList());
                if (children.isEmpty()) continue;
            }
            channels.add(category);
            channels.addAll(children);
        }
        return Collections.unmodifiableList(channels);
    }

    @Override
    @Nonnull
    public RestAction<List<ListedEmote>> retrieveEmotes() {
        Route.CompiledRoute route = Route.Emotes.GET_EMOTES.compile(this.getId());
        return new RestActionImpl<List<ListedEmote>>((JDA)this.getJDA(), route, (response, request) -> {
            EntityBuilder builder = this.getJDA().getEntityBuilder();
            DataArray emotes = response.getArray();
            ArrayList<EmoteImpl> list = new ArrayList<EmoteImpl>(emotes.length());
            for (int i2 = 0; i2 < emotes.length(); ++i2) {
                DataObject emote = emotes.getObject(i2);
                list.add(builder.createEmote(this, emote));
            }
            return Collections.unmodifiableList(list);
        });
    }

    @Override
    @Nonnull
    public RestAction<ListedEmote> retrieveEmoteById(@Nonnull String id2) {
        Checks.isSnowflake(id2, "Emote ID");
        JDAImpl jda = this.getJDA();
        return new DeferredRestAction<ListedEmote, AuditableRestActionImpl>(jda, ListedEmote.class, () -> {
            ListedEmote listedEmote;
            Emote emote = this.getEmoteById(id2);
            if (emote != null && ((listedEmote = (ListedEmote)emote).hasUser() || !this.getSelfMember().hasPermission(Permission.MANAGE_EMOTES))) {
                return listedEmote;
            }
            return null;
        }, () -> {
            Route.CompiledRoute route = Route.Emotes.GET_EMOTE.compile(this.getId(), id2);
            return new AuditableRestActionImpl<ListedEmote>((JDA)jda, route, (response, request) -> {
                EntityBuilder builder = this.getJDA().getEntityBuilder();
                return builder.createEmote(this, response.getObject());
            });
        });
    }

    @Nonnull
    public RestActionImpl<List<Guild.Ban>> retrieveBanList() {
        if (!this.getSelfMember().hasPermission(Permission.BAN_MEMBERS)) {
            throw new InsufficientPermissionException(this, Permission.BAN_MEMBERS);
        }
        Route.CompiledRoute route = Route.Guilds.GET_BANS.compile(this.getId());
        return new RestActionImpl<List<Guild.Ban>>((JDA)this.getJDA(), route, (response, request) -> {
            EntityBuilder builder = this.api.getEntityBuilder();
            LinkedList<Guild.Ban> bans = new LinkedList<Guild.Ban>();
            DataArray bannedArr = response.getArray();
            for (int i2 = 0; i2 < bannedArr.length(); ++i2) {
                DataObject object = bannedArr.getObject(i2);
                DataObject user = object.getObject("user");
                bans.add(new Guild.Ban(builder.createUser(user), object.getString("reason", null)));
            }
            return Collections.unmodifiableList(bans);
        });
    }

    @Override
    @Nonnull
    public RestAction<Guild.Ban> retrieveBanById(@Nonnull String userId) {
        if (!this.getSelfMember().hasPermission(Permission.BAN_MEMBERS)) {
            throw new InsufficientPermissionException(this, Permission.BAN_MEMBERS);
        }
        Checks.isSnowflake(userId, "User ID");
        Route.CompiledRoute route = Route.Guilds.GET_BAN.compile(this.getId(), userId);
        return new RestActionImpl<Guild.Ban>((JDA)this.getJDA(), route, (response, request) -> {
            EntityBuilder builder = this.api.getEntityBuilder();
            DataObject bannedObj = response.getObject();
            DataObject user = bannedObj.getObject("user");
            return new Guild.Ban(builder.createUser(user), bannedObj.getString("reason", null));
        });
    }

    @Override
    @Nonnull
    public RestAction<Integer> retrievePrunableMemberCount(int days) {
        if (!this.getSelfMember().hasPermission(Permission.KICK_MEMBERS)) {
            throw new InsufficientPermissionException(this, Permission.KICK_MEMBERS);
        }
        Checks.check(days >= 1 && days <= 30, "Provided %d days must be between 1 and 30.", (Object)days);
        Route.CompiledRoute route = Route.Guilds.PRUNABLE_COUNT.compile(this.getId()).withQueryParams("days", Integer.toString(days));
        return new RestActionImpl<Integer>((JDA)this.getJDA(), route, (response, request) -> response.getObject().getInt("pruned"));
    }

    @Override
    @Nonnull
    public Role getPublicRole() {
        return this.publicRole;
    }

    @Override
    @Nullable
    public TextChannel getDefaultChannel() {
        Role role = this.getPublicRole();
        return this.getTextChannelsView().stream().filter(c2 -> role.hasPermission((GuildChannel)c2, Permission.MESSAGE_READ)).min(Comparator.naturalOrder()).orElse(null);
    }

    @Override
    @Nonnull
    public GuildManager getManager() {
        if (this.manager == null) {
            this.manager = new GuildManagerImpl(this);
            return this.manager;
        }
        return this.manager;
    }

    @Override
    @Nonnull
    public AuditLogPaginationAction retrieveAuditLogs() {
        return new AuditLogPaginationActionImpl(this);
    }

    @Override
    @Nonnull
    public RestAction<Void> leave() {
        if (this.getSelfMember().isOwner()) {
            throw new IllegalStateException("Cannot leave a guild that you are the owner of! Transfer guild ownership first!");
        }
        Route.CompiledRoute route = Route.Self.LEAVE_GUILD.compile(this.getId());
        return new RestActionImpl<Void>(this.getJDA(), route);
    }

    @Override
    @Nonnull
    public RestAction<Void> delete() {
        if (!this.getJDA().getSelfUser().isBot() && this.getJDA().getSelfUser().isMfaEnabled()) {
            throw new IllegalStateException("Cannot delete a guild without providing MFA code. Use Guild#delete(String)");
        }
        return this.delete(null);
    }

    @Override
    @Nonnull
    public RestAction<Void> delete(String mfaCode) {
        if (!this.getSelfMember().isOwner()) {
            throw new PermissionException("Cannot delete a guild that you do not own!");
        }
        DataObject mfaBody = null;
        if (!this.getJDA().getSelfUser().isBot() && this.getJDA().getSelfUser().isMfaEnabled()) {
            Checks.notEmpty(mfaCode, "Provided MultiFactor Auth code");
            mfaBody = DataObject.empty().put("code", mfaCode);
        }
        Route.CompiledRoute route = Route.Guilds.DELETE_GUILD.compile(this.getId());
        return new RestActionImpl<Void>((JDA)this.getJDA(), route, mfaBody);
    }

    @Override
    @Nonnull
    public AudioManager getAudioManager() {
        if (!this.getJDA().isIntent(GatewayIntent.GUILD_VOICE_STATES)) {
            throw new IllegalStateException("Cannot use audio features with disabled GUILD_VOICE_STATES intent!");
        }
        AbstractCacheView<AudioManager> managerMap = this.getJDA().getAudioManagersView();
        AudioManager mng = managerMap.get(this.id);
        if (mng == null) {
            try (UnlockHook hook = managerMap.writeLock();){
                GuildImpl cachedGuild = (GuildImpl)this.getJDA().getGuildById(this.id);
                if (cachedGuild == null) {
                    throw new IllegalStateException("Cannot get an AudioManager instance on an uncached Guild");
                }
                mng = managerMap.get(this.id);
                if (mng == null) {
                    mng = new AudioManagerImpl(cachedGuild);
                    managerMap.getMap().put(this.id, mng);
                }
            }
        }
        return mng;
    }

    @Override
    @Nonnull
    public JDAImpl getJDA() {
        return this.api;
    }

    @Override
    @Nonnull
    public List<GuildVoiceState> getVoiceStates() {
        return Collections.unmodifiableList(this.getMembersView().stream().map(Member::getVoiceState).filter(Objects::nonNull).collect(Collectors.toList()));
    }

    @Override
    @Nonnull
    public Guild.VerificationLevel getVerificationLevel() {
        return this.verificationLevel;
    }

    @Override
    @Nonnull
    public Guild.NotificationLevel getDefaultNotificationLevel() {
        return this.defaultNotificationLevel;
    }

    @Override
    @Nonnull
    public Guild.MFALevel getRequiredMFALevel() {
        return this.mfaLevel;
    }

    @Override
    @Nonnull
    public Guild.ExplicitContentLevel getExplicitContentLevel() {
        return this.explicitContentLevel;
    }

    @Override
    @Deprecated
    public boolean checkVerification() {
        if (this.getJDA().getAccountType() == AccountType.BOT) {
            return true;
        }
        if (this.canSendVerification) {
            return true;
        }
        switch (this.verificationLevel) {
            case VERY_HIGH: {
                break;
            }
            case HIGH: {
                if (ChronoUnit.MINUTES.between(this.getSelfMember().getTimeJoined(), OffsetDateTime.now()) < 10L) break;
            }
            case MEDIUM: {
                if (ChronoUnit.MINUTES.between(this.getJDA().getSelfUser().getTimeCreated(), OffsetDateTime.now()) < 5L) break;
            }
            case LOW: {
                if (!this.getJDA().getSelfUser().isVerified()) break;
            }
            case NONE: {
                this.canSendVerification = true;
                return true;
            }
            case UNKNOWN: {
                return true;
            }
        }
        return false;
    }

    @Override
    @Deprecated
    public boolean isAvailable() {
        return this.available;
    }

    @Override
    @Nonnull
    @Deprecated
    public CompletableFuture<Void> retrieveMembers() {
        if (!this.getJDA().isIntent(GatewayIntent.GUILD_MEMBERS)) {
            CompletableFuture<Void> future = new CompletableFuture<Void>();
            future.completeExceptionally(new IllegalStateException("Unable to start member chunking on a guild with disabled GUILD_MEMBERS intent!"));
            return future;
        }
        if (this.isLoaded()) {
            return CompletableFuture.completedFuture(null);
        }
        Task<List<Member>> task = this.loadMembers();
        CompletableFuture<Void> future = new CompletableFuture<Void>();
        task.onError(future::completeExceptionally);
        task.onSuccess(members -> {
            try (UnlockHook hook = this.memberCache.writeLock();){
                members.forEach(it2 -> this.memberCache.getMap().put(it2.getIdLong(), it2));
            }
            future.complete(null);
        });
        return future;
    }

    @Override
    @Nonnull
    public Task<Void> loadMembers(@Nonnull Consumer<Member> callback) {
        Checks.notNull(callback, "Callback");
        if (!this.getJDA().isIntent(GatewayIntent.GUILD_MEMBERS)) {
            throw new IllegalStateException("Cannot use loadMembers without GatewayIntent.GUILD_MEMBERS!");
        }
        if (this.isLoaded()) {
            this.memberCache.forEachUnordered(callback);
            return new GatewayTask<Object>(CompletableFuture.completedFuture(null), () -> {});
        }
        MemberChunkManager chunkManager = this.getJDA().getClient().getChunkManager();
        boolean includePresences = this.getJDA().isIntent(GatewayIntent.GUILD_PRESENCES);
        CompletableFuture<Void> handler = chunkManager.chunkGuild(this, includePresences, (last, list) -> list.forEach(callback));
        handler.exceptionally(ex2 -> {
            WebSocketClient.LOG.error("Encountered exception trying to handle member chunk response", (Throwable)ex2);
            return null;
        });
        return new GatewayTask<Void>(handler, () -> handler.cancel(false));
    }

    private Member getMember(long id2, boolean update, JDAImpl jda) {
        if (!update || jda.isIntent(GatewayIntent.GUILD_MEMBERS)) {
            Member member = this.getMemberById(id2);
            if (!update || member != null && member.hasTimeJoined()) {
                return member;
            }
        }
        return null;
    }

    @Override
    @Nonnull
    public RestAction<Member> retrieveMemberById(long id2, boolean update) {
        JDAImpl jda = this.getJDA();
        if (id2 == jda.getSelfUser().getIdLong()) {
            return new CompletedRestAction<Member>((JDA)jda, this.getSelfMember());
        }
        return new DeferredRestAction<Member, RestActionImpl>(jda, Member.class, () -> this.getMember(id2, update, jda), () -> {
            Route.CompiledRoute route = Route.Guilds.GET_MEMBER.compile(this.getId(), Long.toUnsignedString(id2));
            return new RestActionImpl<Member>((JDA)jda, route, (resp, req) -> {
                MemberImpl member = jda.getEntityBuilder().createMember(this, resp.getObject());
                jda.getEntityBuilder().updateMemberCache(member);
                return member;
            });
        });
    }

    @Override
    @Nonnull
    public Task<List<Member>> retrieveMembersByIds(boolean includePresence, long ... ids) {
        Checks.notNull(ids, "ID Array");
        Checks.check(!includePresence || this.api.isIntent(GatewayIntent.GUILD_PRESENCES), "Cannot retrieve presences of members without GUILD_PRESENCES intent!");
        if (ids.length == 0) {
            return new GatewayTask<List<Member>>(CompletableFuture.completedFuture(Collections.emptyList()), () -> {});
        }
        Checks.check(ids.length <= 100, "You can only request 100 members at once");
        MemberChunkManager chunkManager = this.api.getClient().getChunkManager();
        ArrayList collect = new ArrayList(ids.length);
        CompletableFuture result = new CompletableFuture();
        CompletableFuture<Void> handle = chunkManager.chunkGuild(this, includePresence, ids, (last, list) -> {
            collect.addAll(list);
            if (last.booleanValue()) {
                result.complete(collect);
            }
        });
        result.exceptionally(ex2 -> {
            WebSocketClient.LOG.error("Encountered exception trying to handle member chunk response", (Throwable)ex2);
            return null;
        });
        return new GatewayTask<List<Member>>(result, () -> handle.cancel(false));
    }

    @Override
    @Nonnull
    @CheckReturnValue
    public Task<List<Member>> retrieveMembersByPrefix(@Nonnull String prefix, int limit) {
        Checks.notEmpty(prefix, "Prefix");
        Checks.positive(limit, "Limit");
        Checks.check(limit <= 100, "Limit must not be greater than 100");
        MemberChunkManager chunkManager = this.api.getClient().getChunkManager();
        ArrayList collect = new ArrayList(limit);
        CompletableFuture result = new CompletableFuture();
        CompletableFuture<Void> handle = chunkManager.chunkGuild(this, prefix, limit, (last, list) -> {
            collect.addAll(list);
            if (last.booleanValue()) {
                result.complete(collect);
            }
        });
        result.exceptionally(ex2 -> {
            WebSocketClient.LOG.error("Encountered exception trying to handle member chunk response", (Throwable)ex2);
            return null;
        });
        return new GatewayTask<List<Member>>(result, () -> handle.cancel(false));
    }

    @Override
    public long getIdLong() {
        return this.id;
    }

    @Override
    @Nonnull
    public RestAction<List<Invite>> retrieveInvites() {
        if (!this.getSelfMember().hasPermission(Permission.MANAGE_SERVER)) {
            throw new InsufficientPermissionException(this, Permission.MANAGE_SERVER);
        }
        Route.CompiledRoute route = Route.Invites.GET_GUILD_INVITES.compile(this.getId());
        return new RestActionImpl<List<Invite>>((JDA)this.getJDA(), route, (response, request) -> {
            EntityBuilder entityBuilder = this.api.getEntityBuilder();
            DataArray array = response.getArray();
            ArrayList<Invite> invites = new ArrayList<Invite>(array.length());
            for (int i2 = 0; i2 < array.length(); ++i2) {
                invites.add(entityBuilder.createInvite(array.getObject(i2)));
            }
            return Collections.unmodifiableList(invites);
        });
    }

    @Override
    @Nonnull
    public RestAction<List<Template>> retrieveTemplates() {
        if (!this.getSelfMember().hasPermission(Permission.MANAGE_SERVER)) {
            throw new InsufficientPermissionException(this, Permission.MANAGE_SERVER);
        }
        Route.CompiledRoute route = Route.Templates.GET_GUILD_TEMPLATES.compile(this.getId());
        return new RestActionImpl<List<Template>>((JDA)this.getJDA(), route, (response, request) -> {
            EntityBuilder entityBuilder = this.api.getEntityBuilder();
            DataArray array = response.getArray();
            ArrayList<Template> templates = new ArrayList<Template>(array.length());
            for (int i2 = 0; i2 < array.length(); ++i2) {
                try {
                    templates.add(entityBuilder.createTemplate(array.getObject(i2)));
                    continue;
                }
                catch (Exception e2) {
                    JDAImpl.LOG.error("Error creating template from json", e2);
                }
            }
            return Collections.unmodifiableList(templates);
        });
    }

    @Override
    @Nonnull
    public RestAction<Template> createTemplate(@Nonnull String name, @Nullable String description) {
        this.checkPermission(Permission.MANAGE_SERVER);
        Checks.notBlank(name, "Name");
        name = name.trim();
        Checks.notLonger(name, 100, "Name");
        if (description != null) {
            Checks.notLonger(description, 120, "Description");
        }
        Route.CompiledRoute route = Route.Templates.CREATE_TEMPLATE.compile(this.getId());
        DataObject object = DataObject.empty();
        object.put("name", name);
        object.put("description", description);
        return new RestActionImpl<Template>((JDA)this.getJDA(), route, object, (response, request) -> {
            EntityBuilder entityBuilder = this.api.getEntityBuilder();
            return entityBuilder.createTemplate(response.getObject());
        });
    }

    @Override
    @Nonnull
    public RestAction<Void> moveVoiceMember(@Nonnull Member member, @Nullable VoiceChannel voiceChannel) {
        GuildVoiceState vState;
        Checks.notNull(member, "Member");
        this.checkGuild(member.getGuild(), "Member");
        if (voiceChannel != null) {
            this.checkGuild(voiceChannel.getGuild(), "VoiceChannel");
        }
        if ((vState = member.getVoiceState()) == null) {
            throw new IllegalStateException("Cannot move a Member with disabled CacheFlag.VOICE_STATE");
        }
        VoiceChannel channel = vState.getChannel();
        if (channel == null) {
            throw new IllegalStateException("You cannot move a Member who isn't in a VoiceChannel!");
        }
        if (!PermissionUtil.checkPermission(channel, this.getSelfMember(), Permission.VOICE_MOVE_OTHERS)) {
            throw new InsufficientPermissionException(channel, Permission.VOICE_MOVE_OTHERS, "This account does not have Permission to MOVE_OTHERS out of the channel that the Member is currently in.");
        }
        if (voiceChannel != null && !PermissionUtil.checkPermission(voiceChannel, this.getSelfMember(), Permission.VOICE_CONNECT) && !PermissionUtil.checkPermission(voiceChannel, member, Permission.VOICE_CONNECT)) {
            throw new InsufficientPermissionException(voiceChannel, Permission.VOICE_CONNECT, "Neither this account nor the Member that is attempting to be moved have the VOICE_CONNECT permission for the destination VoiceChannel, so the move cannot be done.");
        }
        DataObject body = DataObject.empty().put("channel_id", voiceChannel == null ? null : voiceChannel.getId());
        Route.CompiledRoute route = Route.Guilds.MODIFY_MEMBER.compile(this.getId(), member.getUser().getId());
        return new RestActionImpl<Void>((JDA)this.getJDA(), route, body);
    }

    @Override
    @Nonnull
    public AuditableRestAction<Void> modifyNickname(@Nonnull Member member, String nickname) {
        Checks.notNull(member, "Member");
        this.checkGuild(member.getGuild(), "Member");
        if (member.equals(this.getSelfMember())) {
            if (!member.hasPermission(Permission.NICKNAME_CHANGE) && !member.hasPermission(Permission.NICKNAME_MANAGE)) {
                throw new InsufficientPermissionException((Guild)this, Permission.NICKNAME_CHANGE, "You neither have NICKNAME_CHANGE nor NICKNAME_MANAGE permission!");
            }
        } else {
            this.checkPermission(Permission.NICKNAME_MANAGE);
            this.checkPosition(member);
        }
        JDAImpl jda = this.getJDA();
        return new DeferredRestAction(jda, () -> {
            DataObject body = DataObject.empty().put("nick", nickname == null ? "" : nickname);
            Route.CompiledRoute route = member.equals(this.getSelfMember()) ? Route.Guilds.MODIFY_SELF_NICK.compile(this.getId()) : Route.Guilds.MODIFY_MEMBER.compile(this.getId(), member.getUser().getId());
            return new AuditableRestActionImpl((JDA)jda, route, body);
        }).setCacheCheck(() -> !Objects.equals(nickname, member.getNickname()));
    }

    @Override
    @Nonnull
    public AuditableRestAction<Integer> prune(int days, boolean wait, Role ... roles) {
        this.checkPermission(Permission.KICK_MEMBERS);
        Checks.check(days >= 1 && days <= 30, "Provided %d days must be between 1 and 30.", (Object)days);
        Checks.notNull(roles, "Roles");
        Route.CompiledRoute route = Route.Guilds.PRUNE_MEMBERS.compile(this.getId());
        DataObject body = DataObject.empty();
        body.put("days", days);
        if (!wait) {
            body.put("compute_prune_count", false);
        }
        if (roles.length != 0) {
            for (Role role : roles) {
                Checks.notNull(role, "Role");
                Checks.check(role.getGuild().equals(this), "Role is not from the same guild!");
            }
            body.put("include_roles", Arrays.stream(roles).map(ISnowflake::getId).collect(Collectors.toList()));
        }
        return new AuditableRestActionImpl<Integer>((JDA)this.getJDA(), route, body, (response, request) -> response.getObject().getInt("pruned", 0));
    }

    @Override
    @Nonnull
    public AuditableRestAction<Void> kick(@Nonnull Member member, String reason) {
        Checks.notNull(member, "member");
        this.checkGuild(member.getGuild(), "member");
        this.checkPermission(Permission.KICK_MEMBERS);
        this.checkPosition(member);
        return this.kick0(member.getUser().getId(), reason);
    }

    @Override
    @Nonnull
    public AuditableRestAction<Void> kick(@Nonnull String userId, @Nullable String reason) {
        Member member = this.getMemberById(userId);
        if (member != null) {
            return this.kick(member, reason);
        }
        Checks.check(!userId.equals(this.getOwnerId()), "Cannot kick the owner of a guild!");
        this.checkPermission(Permission.KICK_MEMBERS);
        return this.kick0(userId, reason);
    }

    @Nonnull
    private AuditableRestAction<Void> kick0(@Nonnull String userId, @Nullable String reason) {
        Route.CompiledRoute route = Route.Guilds.KICK_MEMBER.compile(this.getId(), userId);
        if (!Helpers.isBlank(reason)) {
            Checks.check(reason.length() <= 512, "Reason cannot be longer than 512 characters.");
            route = route.withQueryParams("reason", EncodingUtil.encodeUTF8(reason));
        }
        return new AuditableRestActionImpl<Void>(this.getJDA(), route);
    }

    @Override
    @Nonnull
    public AuditableRestAction<Void> ban(@Nonnull User user, int delDays, String reason) {
        Checks.notNull(user, "User");
        this.checkPermission(Permission.BAN_MEMBERS);
        if (this.isMember(user)) {
            this.checkPosition(this.getMember(user));
        }
        return this.ban0(user.getId(), delDays, reason);
    }

    @Override
    @Nonnull
    public AuditableRestAction<Void> ban(@Nonnull String userId, int delDays, String reason) {
        Checks.notNull(userId, "User");
        this.checkPermission(Permission.BAN_MEMBERS);
        User user = this.getJDA().getUserById(userId);
        if (user != null) {
            return this.ban(user, delDays, reason);
        }
        return this.ban0(userId, delDays, reason);
    }

    @Nonnull
    private AuditableRestAction<Void> ban0(@Nonnull String userId, int delDays, String reason) {
        Checks.notNegative(delDays, "Deletion Days");
        Checks.check(delDays <= 7, "Deletion Days must not be bigger than 7.");
        Route.CompiledRoute route = Route.Guilds.BAN.compile(this.getId(), userId);
        DataObject params = DataObject.empty();
        if (!Helpers.isBlank(reason)) {
            Checks.check(reason.length() <= 512, "Reason cannot be longer than 512 characters.");
            params.put("reason", reason);
        }
        if (delDays > 0) {
            params.put("delete_message_days", delDays);
        }
        return new AuditableRestActionImpl<Void>((JDA)this.getJDA(), route, params);
    }

    @Override
    @Nonnull
    public AuditableRestAction<Void> unban(@Nonnull String userId) {
        Checks.isSnowflake(userId, "User ID");
        this.checkPermission(Permission.BAN_MEMBERS);
        Route.CompiledRoute route = Route.Guilds.UNBAN.compile(this.getId(), userId);
        return new AuditableRestActionImpl<Void>(this.getJDA(), route);
    }

    @Override
    @Nonnull
    public AuditableRestAction<Void> deafen(@Nonnull Member member, boolean deafen) {
        Checks.notNull(member, "Member");
        this.checkGuild(member.getGuild(), "Member");
        this.checkPermission(Permission.VOICE_DEAF_OTHERS);
        GuildVoiceState voiceState = member.getVoiceState();
        if (voiceState != null) {
            if (voiceState.getChannel() == null) {
                throw new IllegalStateException("Can only deafen members who are currently in a voice channel");
            }
            if (voiceState.isGuildDeafened() == deafen) {
                return new CompletedRestAction<Void>((JDA)this.getJDA(), null);
            }
        }
        DataObject body = DataObject.empty().put("deaf", deafen);
        Route.CompiledRoute route = Route.Guilds.MODIFY_MEMBER.compile(this.getId(), member.getUser().getId());
        return new AuditableRestActionImpl<Void>((JDA)this.getJDA(), route, body);
    }

    @Override
    @Nonnull
    public AuditableRestAction<Void> mute(@Nonnull Member member, boolean mute) {
        Checks.notNull(member, "Member");
        this.checkGuild(member.getGuild(), "Member");
        this.checkPermission(Permission.VOICE_MUTE_OTHERS);
        GuildVoiceState voiceState = member.getVoiceState();
        if (voiceState != null) {
            if (voiceState.getChannel() == null) {
                throw new IllegalStateException("Can only mute members who are currently in a voice channel");
            }
            if (voiceState.isGuildMuted() == mute) {
                return new CompletedRestAction<Void>((JDA)this.getJDA(), null);
            }
        }
        DataObject body = DataObject.empty().put("mute", mute);
        Route.CompiledRoute route = Route.Guilds.MODIFY_MEMBER.compile(this.getId(), member.getUser().getId());
        return new AuditableRestActionImpl<Void>((JDA)this.getJDA(), route, body);
    }

    @Override
    @Nonnull
    public AuditableRestAction<Void> addRoleToMember(@Nonnull Member member, @Nonnull Role role) {
        Checks.notNull(member, "Member");
        Checks.notNull(role, "Role");
        this.checkGuild(member.getGuild(), "Member");
        this.checkGuild(role.getGuild(), "Role");
        this.checkPermission(Permission.MANAGE_ROLES);
        this.checkPosition(role);
        Route.CompiledRoute route = Route.Guilds.ADD_MEMBER_ROLE.compile(this.getId(), member.getUser().getId(), role.getId());
        return new AuditableRestActionImpl<Void>(this.getJDA(), route);
    }

    @Override
    @Nonnull
    public AuditableRestAction<Void> removeRoleFromMember(@Nonnull Member member, @Nonnull Role role) {
        Checks.notNull(member, "Member");
        Checks.notNull(role, "Role");
        this.checkGuild(member.getGuild(), "Member");
        this.checkGuild(role.getGuild(), "Role");
        this.checkPermission(Permission.MANAGE_ROLES);
        this.checkPosition(role);
        Route.CompiledRoute route = Route.Guilds.REMOVE_MEMBER_ROLE.compile(this.getId(), member.getUser().getId(), role.getId());
        return new AuditableRestActionImpl<Void>(this.getJDA(), route);
    }

    @Override
    @Nonnull
    public AuditableRestAction<Void> modifyMemberRoles(@Nonnull Member member, Collection<Role> rolesToAdd, Collection<Role> rolesToRemove) {
        Checks.notNull(member, "Member");
        this.checkGuild(member.getGuild(), "Member");
        this.checkPermission(Permission.MANAGE_ROLES);
        HashSet<Role> currentRoles = new HashSet<Role>(((MemberImpl)member).getRoleSet());
        if (rolesToAdd != null) {
            this.checkRoles(rolesToAdd, "add", "to");
            currentRoles.addAll(rolesToAdd);
        }
        if (rolesToRemove != null) {
            this.checkRoles(rolesToRemove, "remove", "from");
            currentRoles.removeAll(rolesToRemove);
        }
        return this.modifyMemberRoles(member, currentRoles);
    }

    @Override
    @Nonnull
    public AuditableRestAction<Void> modifyMemberRoles(@Nonnull Member member, @Nonnull Collection<Role> roles) {
        Checks.notNull(member, "Member");
        Checks.notNull(roles, "Roles");
        this.checkGuild(member.getGuild(), "Member");
        roles.forEach(role -> {
            Checks.notNull(role, "Role in collection");
            this.checkGuild(role.getGuild(), "Role: " + role.toString());
        });
        Checks.check(!roles.contains(this.getPublicRole()), "Cannot add the PublicRole of a Guild to a Member. All members have this role by default!");
        List<Role> memberRoles = member.getRoles();
        if (Helpers.deepEqualsUnordered(roles, memberRoles)) {
            return new CompletedRestAction<Void>((JDA)this.getJDA(), null);
        }
        for (Role r2 : memberRoles) {
            if (roles.contains(r2)) continue;
            this.checkPosition(r2);
            Checks.check(!r2.isManaged(), "Cannot remove managed role from member. Role: %s", (Object)r2);
        }
        for (Role r2 : roles) {
            if (memberRoles.contains(r2)) continue;
            this.checkPosition(r2);
            Checks.check(!r2.isManaged(), "Cannot add managed role to member. Role: %s", (Object)r2);
        }
        DataObject body = DataObject.empty().put("roles", roles.stream().map(ISnowflake::getId).collect(Collectors.toSet()));
        Route.CompiledRoute route = Route.Guilds.MODIFY_MEMBER.compile(this.getId(), member.getUser().getId());
        return new AuditableRestActionImpl<Void>((JDA)this.getJDA(), route, body);
    }

    @Override
    @Nonnull
    public AuditableRestAction<Void> transferOwnership(@Nonnull Member newOwner) {
        Checks.notNull(newOwner, "Member");
        this.checkGuild(newOwner.getGuild(), "Member");
        if (!this.getSelfMember().isOwner()) {
            throw new PermissionException("The logged in account must be the owner of this Guild to be able to transfer ownership");
        }
        Checks.check(!this.getSelfMember().equals(newOwner), "The member provided as the newOwner is the currently logged in account. Provide a different member to give ownership to.");
        Checks.check(!newOwner.getUser().isBot(), "Cannot transfer ownership of a Guild to a Bot!");
        DataObject body = DataObject.empty().put("owner_id", newOwner.getUser().getId());
        Route.CompiledRoute route = Route.Guilds.MODIFY_GUILD.compile(this.getId());
        return new AuditableRestActionImpl<Void>((JDA)this.getJDA(), route, body);
    }

    @Override
    @Nonnull
    public ChannelAction<TextChannel> createTextChannel(@Nonnull String name, Category parent) {
        if (parent != null) {
            Checks.check(parent.getGuild().equals(this), "Category is not from the same guild!");
            if (!this.getSelfMember().hasPermission((GuildChannel)parent, Permission.MANAGE_CHANNEL)) {
                throw new InsufficientPermissionException(parent, Permission.MANAGE_CHANNEL);
            }
        } else {
            this.checkPermission(Permission.MANAGE_CHANNEL);
        }
        Checks.notBlank(name, "Name");
        name = name.trim();
        Checks.notEmpty(name, "Name");
        Checks.notLonger(name, 100, "Name");
        return new ChannelActionImpl<TextChannel>(TextChannel.class, name, this, ChannelType.TEXT).setParent(parent);
    }

    @Override
    @Nonnull
    public ChannelAction<VoiceChannel> createVoiceChannel(@Nonnull String name, Category parent) {
        if (parent != null) {
            Checks.check(parent.getGuild().equals(this), "Category is not from the same guild!");
            if (!this.getSelfMember().hasPermission((GuildChannel)parent, Permission.MANAGE_CHANNEL)) {
                throw new InsufficientPermissionException(parent, Permission.MANAGE_CHANNEL);
            }
        } else {
            this.checkPermission(Permission.MANAGE_CHANNEL);
        }
        Checks.notBlank(name, "Name");
        name = name.trim();
        Checks.notEmpty(name, "Name");
        Checks.notLonger(name, 100, "Name");
        return new ChannelActionImpl<VoiceChannel>(VoiceChannel.class, name, this, ChannelType.VOICE).setParent(parent);
    }

    @Override
    @Nonnull
    public ChannelAction<Category> createCategory(@Nonnull String name) {
        this.checkPermission(Permission.MANAGE_CHANNEL);
        Checks.notBlank(name, "Name");
        name = name.trim();
        Checks.notEmpty(name, "Name");
        Checks.notLonger(name, 100, "Name");
        return new ChannelActionImpl<Category>(Category.class, name, this, ChannelType.CATEGORY);
    }

    @Override
    @Nonnull
    public RoleAction createRole() {
        this.checkPermission(Permission.MANAGE_ROLES);
        return new RoleActionImpl(this);
    }

    @Override
    @Nonnull
    public AuditableRestAction<Emote> createEmote(@Nonnull String name, @Nonnull Icon icon, Role ... roles) {
        this.checkPermission(Permission.MANAGE_EMOTES);
        Checks.notBlank(name, "Emote name");
        Checks.notNull(icon, "Emote icon");
        Checks.notNull(roles, "Roles");
        DataObject body = DataObject.empty();
        body.put("name", name);
        body.put("image", icon.getEncoding());
        if (roles.length > 0) {
            body.put("roles", Stream.of(roles).filter(Objects::nonNull).map(ISnowflake::getId).collect(Collectors.toSet()));
        }
        JDAImpl jda = this.getJDA();
        Route.CompiledRoute route = Route.Emotes.CREATE_EMOTE.compile(this.getId());
        return new AuditableRestActionImpl<Emote>((JDA)jda, route, body, (response, request) -> {
            DataObject obj = response.getObject();
            return jda.getEntityBuilder().createEmote(this, obj);
        });
    }

    @Override
    @Nonnull
    public ChannelOrderAction modifyCategoryPositions() {
        return new ChannelOrderActionImpl(this, ChannelType.CATEGORY.getSortBucket());
    }

    @Override
    @Nonnull
    public ChannelOrderAction modifyTextChannelPositions() {
        return new ChannelOrderActionImpl(this, ChannelType.TEXT.getSortBucket());
    }

    @Override
    @Nonnull
    public ChannelOrderAction modifyVoiceChannelPositions() {
        return new ChannelOrderActionImpl(this, ChannelType.VOICE.getSortBucket());
    }

    @Override
    @Nonnull
    public CategoryOrderAction modifyTextChannelPositions(@Nonnull Category category) {
        Checks.notNull(category, "Category");
        this.checkGuild(category.getGuild(), "Category");
        return new CategoryOrderActionImpl(category, ChannelType.TEXT.getSortBucket());
    }

    @Override
    @Nonnull
    public CategoryOrderAction modifyVoiceChannelPositions(@Nonnull Category category) {
        Checks.notNull(category, "Category");
        this.checkGuild(category.getGuild(), "Category");
        return new CategoryOrderActionImpl(category, ChannelType.VOICE.getSortBucket());
    }

    @Override
    @Nonnull
    public RoleOrderAction modifyRolePositions(boolean useAscendingOrder) {
        return new RoleOrderActionImpl(this, useAscendingOrder);
    }

    protected void checkGuild(Guild providedGuild, String comment) {
        if (!this.equals(providedGuild)) {
            throw new IllegalArgumentException("Provided " + comment + " is not part of this Guild!");
        }
    }

    protected void checkPermission(Permission perm) {
        if (!this.getSelfMember().hasPermission(perm)) {
            throw new InsufficientPermissionException(this, perm);
        }
    }

    protected void checkPosition(Member member) {
        if (!this.getSelfMember().canInteract(member)) {
            throw new HierarchyException("Can't modify a member with higher or equal highest role than yourself!");
        }
    }

    protected void checkPosition(Role role) {
        if (!this.getSelfMember().canInteract(role)) {
            throw new HierarchyException("Can't modify a role with higher or equal highest role than yourself! Role: " + role.toString());
        }
    }

    private void checkRoles(Collection<Role> roles, String type, String preposition) {
        roles.forEach(role -> {
            Checks.notNull(role, "Role in roles to " + type);
            this.checkGuild(role.getGuild(), "Role: " + role.toString());
            this.checkPosition((Role)role);
            Checks.check(!role.isManaged(), "Cannot %s a managed role %s a Member. Role: %s", type, preposition, role.toString());
        });
    }

    public GuildImpl setAvailable(boolean available) {
        this.available = available;
        return this;
    }

    public GuildImpl setOwner(Member owner) {
        if (owner != null && this.getMemberById(owner.getIdLong()) != null) {
            this.owner = owner;
        }
        return this;
    }

    public GuildImpl setName(String name) {
        this.name = name;
        return this;
    }

    public GuildImpl setIconId(String iconId) {
        this.iconId = iconId;
        return this;
    }

    public GuildImpl setFeatures(Set<String> features) {
        this.features = Collections.unmodifiableSet(features);
        return this;
    }

    public GuildImpl setSplashId(String splashId) {
        this.splashId = splashId;
        return this;
    }

    public GuildImpl setRegion(String region) {
        this.region = region;
        return this;
    }

    public GuildImpl setVanityCode(String code) {
        this.vanityCode = code;
        return this;
    }

    public GuildImpl setDescription(String description) {
        this.description = description;
        return this;
    }

    public GuildImpl setBannerId(String bannerId) {
        this.banner = bannerId;
        return this;
    }

    public GuildImpl setMaxPresences(int maxPresences) {
        this.maxPresences = maxPresences;
        return this;
    }

    public GuildImpl setMaxMembers(int maxMembers) {
        this.maxMembers = maxMembers;
        return this;
    }

    public GuildImpl setAfkChannel(VoiceChannel afkChannel) {
        this.afkChannel = afkChannel;
        return this;
    }

    public GuildImpl setSystemChannel(TextChannel systemChannel) {
        this.systemChannel = systemChannel;
        return this;
    }

    public GuildImpl setRulesChannel(TextChannel rulesChannel) {
        this.rulesChannel = rulesChannel;
        return this;
    }

    public GuildImpl setCommunityUpdatesChannel(TextChannel communityUpdatesChannel) {
        this.communityUpdatesChannel = communityUpdatesChannel;
        return this;
    }

    public GuildImpl setPublicRole(Role publicRole) {
        this.publicRole = publicRole;
        return this;
    }

    public GuildImpl setVerificationLevel(Guild.VerificationLevel level) {
        this.verificationLevel = level;
        this.canSendVerification = false;
        return this;
    }

    public GuildImpl setDefaultNotificationLevel(Guild.NotificationLevel level) {
        this.defaultNotificationLevel = level;
        return this;
    }

    public GuildImpl setRequiredMFALevel(Guild.MFALevel level) {
        this.mfaLevel = level;
        return this;
    }

    public GuildImpl setExplicitContentLevel(Guild.ExplicitContentLevel level) {
        this.explicitContentLevel = level;
        return this;
    }

    public GuildImpl setAfkTimeout(Guild.Timeout afkTimeout) {
        this.afkTimeout = afkTimeout;
        return this;
    }

    public GuildImpl setLocale(String locale) {
        this.preferredLocale = Locale.forLanguageTag(locale);
        return this;
    }

    public GuildImpl setBoostTier(int tier) {
        this.boostTier = Guild.BoostTier.fromKey(tier);
        return this;
    }

    public GuildImpl setBoostCount(int count) {
        this.boostCount = count;
        return this;
    }

    public GuildImpl setOwnerId(long ownerId) {
        this.ownerId = ownerId;
        return this;
    }

    public GuildImpl setMemberCount(int count) {
        this.memberCount = count;
        return this;
    }

    public SortedSnowflakeCacheViewImpl<Category> getCategoriesView() {
        return this.categoryCache;
    }

    public SortedSnowflakeCacheViewImpl<StoreChannel> getStoreChannelView() {
        return this.storeChannelCache;
    }

    public SortedSnowflakeCacheViewImpl<TextChannel> getTextChannelsView() {
        return this.textChannelCache;
    }

    public SortedSnowflakeCacheViewImpl<VoiceChannel> getVoiceChannelsView() {
        return this.voiceChannelCache;
    }

    public SortedSnowflakeCacheViewImpl<Role> getRolesView() {
        return this.roleCache;
    }

    public SnowflakeCacheViewImpl<Emote> getEmotesView() {
        return this.emoteCache;
    }

    public MemberCacheViewImpl getMembersView() {
        return this.memberCache;
    }

    @Nullable
    public CacheView.SimpleCacheView<MemberPresenceImpl> getPresenceView() {
        return this.memberPresences;
    }

    public void onMemberAdd() {
        ++this.memberCount;
    }

    public void onMemberRemove() {
        --this.memberCount;
    }

    public boolean equals(Object o2) {
        if (o2 == this) {
            return true;
        }
        if (!(o2 instanceof GuildImpl)) {
            return false;
        }
        GuildImpl oGuild = (GuildImpl)o2;
        return this.id == oGuild.id;
    }

    public int hashCode() {
        return Long.hashCode(this.id);
    }

    public String toString() {
        return "G:" + this.getName() + '(' + this.id + ')';
    }
}

