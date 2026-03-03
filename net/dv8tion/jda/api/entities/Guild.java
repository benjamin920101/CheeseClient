/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.entities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;
import java.util.function.Predicate;
import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.dv8tion.jda.annotations.DeprecatedSince;
import net.dv8tion.jda.annotations.ForRemoval;
import net.dv8tion.jda.annotations.ReplaceWith;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.Region;
import net.dv8tion.jda.api.entities.Category;
import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.entities.Emote;
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
import net.dv8tion.jda.api.utils.MiscUtil;
import net.dv8tion.jda.api.utils.cache.MemberCacheView;
import net.dv8tion.jda.api.utils.cache.SnowflakeCacheView;
import net.dv8tion.jda.api.utils.cache.SortedSnowflakeCacheView;
import net.dv8tion.jda.api.utils.concurrent.Task;
import net.dv8tion.jda.internal.requests.DeferredRestAction;
import net.dv8tion.jda.internal.requests.Route;
import net.dv8tion.jda.internal.requests.restaction.AuditableRestActionImpl;
import net.dv8tion.jda.internal.utils.Checks;
import net.dv8tion.jda.internal.utils.concurrent.task.GatewayTask;

public interface Guild
extends ISnowflake {
    public static final String ICON_URL = "https://cdn.discordapp.com/icons/%s/%s.%s";
    public static final String SPLASH_URL = "https://cdn.discordapp.com/splashes/%s/%s.png";
    public static final String BANNER_URL = "https://cdn.discordapp.com/banners/%s/%s.png";

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
    public RestAction<List<CommandPrivilege>> retrieveCommandPrivilegesById(@Nonnull String var1);

    @Nonnull
    @CheckReturnValue
    default public RestAction<List<CommandPrivilege>> retrieveCommandPrivilegesById(long commandId) {
        return this.retrieveCommandPrivilegesById(Long.toUnsignedString(commandId));
    }

    @Nonnull
    @CheckReturnValue
    public RestAction<Map<String, List<CommandPrivilege>>> retrieveCommandPrivileges();

    @Nonnull
    @CheckReturnValue
    public RestAction<List<CommandPrivilege>> updateCommandPrivilegesById(@Nonnull String var1, @Nonnull Collection<? extends CommandPrivilege> var2);

    @Nonnull
    @CheckReturnValue
    default public RestAction<List<CommandPrivilege>> updateCommandPrivilegesById(@Nonnull String id2, CommandPrivilege ... privileges) {
        Checks.noneNull(privileges, "CommandPrivileges");
        return this.updateCommandPrivilegesById(id2, Arrays.asList(privileges));
    }

    @Nonnull
    @CheckReturnValue
    default public RestAction<List<CommandPrivilege>> updateCommandPrivilegesById(long id2, @Nonnull Collection<? extends CommandPrivilege> privileges) {
        return this.updateCommandPrivilegesById(Long.toUnsignedString(id2), privileges);
    }

    @Nonnull
    @CheckReturnValue
    default public RestAction<List<CommandPrivilege>> updateCommandPrivilegesById(long id2, CommandPrivilege ... privileges) {
        Checks.noneNull(privileges, "CommandPrivileges");
        return this.updateCommandPrivilegesById(id2, Arrays.asList(privileges));
    }

    @Nonnull
    @CheckReturnValue
    public RestAction<Map<String, List<CommandPrivilege>>> updateCommandPrivileges(@Nonnull Map<String, Collection<? extends CommandPrivilege>> var1);

    @Nonnull
    @CheckReturnValue
    default public RestAction<EnumSet<Region>> retrieveRegions() {
        return this.retrieveRegions(true);
    }

    @Nonnull
    @CheckReturnValue
    public RestAction<EnumSet<Region>> retrieveRegions(boolean var1);

    @Nonnull
    @CheckReturnValue
    public MemberAction addMember(@Nonnull String var1, @Nonnull String var2);

    @Nonnull
    @CheckReturnValue
    default public MemberAction addMember(@Nonnull String accessToken, @Nonnull User user) {
        Checks.notNull(user, "User");
        return this.addMember(accessToken, user.getId());
    }

    @Nonnull
    @CheckReturnValue
    default public MemberAction addMember(@Nonnull String accessToken, long userId) {
        return this.addMember(accessToken, Long.toUnsignedString(userId));
    }

    public boolean isLoaded();

    public void pruneMemberCache();

    public boolean unloadMember(long var1);

    public int getMemberCount();

    @Nonnull
    public String getName();

    @Nullable
    public String getIconId();

    @Nullable
    default public String getIconUrl() {
        String iconId = this.getIconId();
        return iconId == null ? null : String.format(ICON_URL, this.getId(), iconId, iconId.startsWith("a_") ? "gif" : "png");
    }

    @Nonnull
    public Set<String> getFeatures();

    @Nullable
    public String getSplashId();

    @Nullable
    default public String getSplashUrl() {
        String splashId = this.getSplashId();
        return splashId == null ? null : String.format(SPLASH_URL, this.getId(), splashId);
    }

    @Nonnull
    @Deprecated
    @ForRemoval(deadline="4.4.0")
    @DeprecatedSince(value="4.0.0")
    @ReplaceWith(value="getVanityCode()")
    @CheckReturnValue
    public RestAction<String> retrieveVanityUrl();

    @Nullable
    public String getVanityCode();

    @Nullable
    default public String getVanityUrl() {
        return this.getVanityCode() == null ? null : "https://discord.gg/" + this.getVanityCode();
    }

    @Nonnull
    @CheckReturnValue
    public RestAction<VanityInvite> retrieveVanityInvite();

    @Nullable
    public String getDescription();

    @Nonnull
    public Locale getLocale();

    @Nullable
    public String getBannerId();

    @Nullable
    default public String getBannerUrl() {
        String bannerId = this.getBannerId();
        return bannerId == null ? null : String.format(BANNER_URL, this.getId(), bannerId);
    }

    @Nonnull
    public BoostTier getBoostTier();

    public int getBoostCount();

    @Nonnull
    public List<Member> getBoosters();

    default public int getMaxBitrate() {
        int maxBitrate = this.getFeatures().contains("VIP_REGIONS") ? 384000 : 96000;
        return Math.max(maxBitrate, this.getBoostTier().getMaxBitrate());
    }

    default public long getMaxFileSize() {
        return this.getBoostTier().getMaxFileSize();
    }

    default public int getMaxEmotes() {
        int maxEmotes = this.getFeatures().contains("MORE_EMOJI") ? 200 : 50;
        return Math.max(maxEmotes, this.getBoostTier().getMaxEmotes());
    }

    public int getMaxMembers();

    public int getMaxPresences();

    @Nonnull
    @CheckReturnValue
    public RestAction<MetaData> retrieveMetaData();

    @Nullable
    public VoiceChannel getAfkChannel();

    @Nullable
    public TextChannel getSystemChannel();

    @Nullable
    public TextChannel getRulesChannel();

    @Nullable
    public TextChannel getCommunityUpdatesChannel();

    @Nullable
    public Member getOwner();

    public long getOwnerIdLong();

    @Nonnull
    default public String getOwnerId() {
        return Long.toUnsignedString(this.getOwnerIdLong());
    }

    @Nonnull
    public Timeout getAfkTimeout();

    @Deprecated
    @ReplaceWith(value="VoiceChannel.getRegion()")
    @DeprecatedSince(value="4.3.0")
    @Nonnull
    default public Region getRegion() {
        return Region.fromKey(this.getRegionRaw());
    }

    @Deprecated
    @ReplaceWith(value="VoiceChannel.getRegionRaw()")
    @DeprecatedSince(value="4.3.0")
    @Nonnull
    public String getRegionRaw();

    public boolean isMember(@Nonnull User var1);

    @Nonnull
    public Member getSelfMember();

    @Nullable
    public Member getMember(@Nonnull User var1);

    @Nullable
    default public Member getMemberById(@Nonnull String userId) {
        return this.getMemberCache().getElementById(userId);
    }

    @Nullable
    default public Member getMemberById(long userId) {
        return this.getMemberCache().getElementById(userId);
    }

    @Nullable
    default public Member getMemberByTag(@Nonnull String tag) {
        User user = this.getJDA().getUserByTag(tag);
        return user == null ? null : this.getMember(user);
    }

    @Nullable
    default public Member getMemberByTag(@Nonnull String username, @Nonnull String discriminator) {
        User user = this.getJDA().getUserByTag(username, discriminator);
        return user == null ? null : this.getMember(user);
    }

    @Nonnull
    default public List<Member> getMembers() {
        return this.getMemberCache().asList();
    }

    @Nonnull
    default public List<Member> getMembersByName(@Nonnull String name, boolean ignoreCase) {
        return this.getMemberCache().getElementsByUsername(name, ignoreCase);
    }

    @Nonnull
    default public List<Member> getMembersByNickname(@Nullable String nickname, boolean ignoreCase) {
        return this.getMemberCache().getElementsByNickname(nickname, ignoreCase);
    }

    @Nonnull
    default public List<Member> getMembersByEffectiveName(@Nonnull String name, boolean ignoreCase) {
        return this.getMemberCache().getElementsByName(name, ignoreCase);
    }

    @Nonnull
    default public List<Member> getMembersWithRoles(Role ... roles) {
        return this.getMemberCache().getElementsWithRoles(roles);
    }

    @Nonnull
    default public List<Member> getMembersWithRoles(@Nonnull Collection<Role> roles) {
        return this.getMemberCache().getElementsWithRoles(roles);
    }

    @Nonnull
    public MemberCacheView getMemberCache();

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

    @Nullable
    default public Category getCategoryById(@Nonnull String id2) {
        return (Category)this.getCategoryCache().getElementById(id2);
    }

    @Nullable
    default public Category getCategoryById(long id2) {
        return (Category)this.getCategoryCache().getElementById(id2);
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
    public SortedSnowflakeCacheView<Category> getCategoryCache();

    @Nullable
    default public StoreChannel getStoreChannelById(@Nonnull String id2) {
        return (StoreChannel)this.getStoreChannelCache().getElementById(id2);
    }

    @Nullable
    default public StoreChannel getStoreChannelById(long id2) {
        return (StoreChannel)this.getStoreChannelCache().getElementById(id2);
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
    public SortedSnowflakeCacheView<StoreChannel> getStoreChannelCache();

    @Nullable
    default public TextChannel getTextChannelById(@Nonnull String id2) {
        return (TextChannel)this.getTextChannelCache().getElementById(id2);
    }

    @Nullable
    default public TextChannel getTextChannelById(long id2) {
        return (TextChannel)this.getTextChannelCache().getElementById(id2);
    }

    @Nonnull
    default public List<TextChannel> getTextChannels() {
        return this.getTextChannelCache().asList();
    }

    @Nonnull
    default public List<TextChannel> getTextChannelsByName(@Nonnull String name, boolean ignoreCase) {
        return this.getTextChannelCache().getElementsByName(name, ignoreCase);
    }

    @Nonnull
    public SortedSnowflakeCacheView<TextChannel> getTextChannelCache();

    @Nullable
    default public VoiceChannel getVoiceChannelById(@Nonnull String id2) {
        return (VoiceChannel)this.getVoiceChannelCache().getElementById(id2);
    }

    @Nullable
    default public VoiceChannel getVoiceChannelById(long id2) {
        return (VoiceChannel)this.getVoiceChannelCache().getElementById(id2);
    }

    @Nonnull
    default public List<VoiceChannel> getVoiceChannels() {
        return this.getVoiceChannelCache().asList();
    }

    @Nonnull
    default public List<VoiceChannel> getVoiceChannelsByName(@Nonnull String name, boolean ignoreCase) {
        return this.getVoiceChannelCache().getElementsByName(name, ignoreCase);
    }

    @Nonnull
    public SortedSnowflakeCacheView<VoiceChannel> getVoiceChannelCache();

    @Nonnull
    default public List<GuildChannel> getChannels() {
        return this.getChannels(true);
    }

    @Nonnull
    public List<GuildChannel> getChannels(boolean var1);

    @Nullable
    default public Role getRoleById(@Nonnull String id2) {
        return (Role)this.getRoleCache().getElementById(id2);
    }

    @Nullable
    default public Role getRoleById(long id2) {
        return (Role)this.getRoleCache().getElementById(id2);
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
    default public Role getRoleByBot(long userId) {
        return this.getRoleCache().applyStream(stream -> stream.filter(role -> role.getTags().getBotIdLong() == userId).findFirst().orElse(null));
    }

    @Nullable
    default public Role getRoleByBot(@Nonnull String userId) {
        return this.getRoleByBot(MiscUtil.parseSnowflake(userId));
    }

    @Nullable
    default public Role getRoleByBot(@Nonnull User user) {
        Checks.notNull(user, "User");
        return this.getRoleByBot(user.getIdLong());
    }

    @Nullable
    default public Role getBotRole() {
        return this.getRoleByBot(this.getJDA().getSelfUser());
    }

    @Nullable
    default public Role getBoostRole() {
        return this.getRoleCache().applyStream(stream -> stream.filter(role -> role.getTags().isBoost()).findFirst().orElse(null));
    }

    @Nonnull
    public SortedSnowflakeCacheView<Role> getRoleCache();

    @Nullable
    default public Emote getEmoteById(@Nonnull String id2) {
        return this.getEmoteCache().getElementById(id2);
    }

    @Nullable
    default public Emote getEmoteById(long id2) {
        return this.getEmoteCache().getElementById(id2);
    }

    @Nonnull
    default public List<Emote> getEmotes() {
        return this.getEmoteCache().asList();
    }

    @Nonnull
    default public List<Emote> getEmotesByName(@Nonnull String name, boolean ignoreCase) {
        return this.getEmoteCache().getElementsByName(name, ignoreCase);
    }

    @Nonnull
    public SnowflakeCacheView<Emote> getEmoteCache();

    @Nonnull
    @CheckReturnValue
    public RestAction<List<ListedEmote>> retrieveEmotes();

    @Nonnull
    @CheckReturnValue
    public RestAction<ListedEmote> retrieveEmoteById(@Nonnull String var1);

    @Nonnull
    @CheckReturnValue
    default public RestAction<ListedEmote> retrieveEmoteById(long id2) {
        return this.retrieveEmoteById(Long.toUnsignedString(id2));
    }

    @Nonnull
    @CheckReturnValue
    default public RestAction<ListedEmote> retrieveEmote(@Nonnull Emote emote) {
        Checks.notNull(emote, "Emote");
        if (emote.getGuild() != null) {
            Checks.check(emote.getGuild().equals(this), "Emote must be from the same Guild!");
        }
        JDA jda = this.getJDA();
        return new DeferredRestAction<ListedEmote, RestAction>(jda, ListedEmote.class, () -> {
            ListedEmote listedEmote;
            if (emote instanceof ListedEmote && ((listedEmote = (ListedEmote)emote).hasUser() || !this.getSelfMember().hasPermission(Permission.MANAGE_EMOTES))) {
                return listedEmote;
            }
            return null;
        }, () -> this.retrieveEmoteById(emote.getId()));
    }

    @Nonnull
    @CheckReturnValue
    public RestAction<List<Ban>> retrieveBanList();

    @Nonnull
    @CheckReturnValue
    default public RestAction<Ban> retrieveBanById(long userId) {
        return this.retrieveBanById(Long.toUnsignedString(userId));
    }

    @Nonnull
    @CheckReturnValue
    public RestAction<Ban> retrieveBanById(@Nonnull String var1);

    @Nonnull
    @CheckReturnValue
    default public RestAction<Ban> retrieveBan(@Nonnull User bannedUser) {
        Checks.notNull(bannedUser, "bannedUser");
        return this.retrieveBanById(bannedUser.getId());
    }

    @Nonnull
    @CheckReturnValue
    public RestAction<Integer> retrievePrunableMemberCount(int var1);

    @Nonnull
    public Role getPublicRole();

    @Nullable
    public TextChannel getDefaultChannel();

    @Nonnull
    public GuildManager getManager();

    @Nonnull
    @CheckReturnValue
    public AuditLogPaginationAction retrieveAuditLogs();

    @Nonnull
    @CheckReturnValue
    public RestAction<Void> leave();

    @Nonnull
    @CheckReturnValue
    public RestAction<Void> delete();

    @Nonnull
    @CheckReturnValue
    public RestAction<Void> delete(@Nullable String var1);

    @Nonnull
    public AudioManager getAudioManager();

    @Nonnull
    public JDA getJDA();

    @Nonnull
    @CheckReturnValue
    public RestAction<List<Invite>> retrieveInvites();

    @Nonnull
    @CheckReturnValue
    public RestAction<List<Template>> retrieveTemplates();

    @Nonnull
    @CheckReturnValue
    public RestAction<Template> createTemplate(@Nonnull String var1, @Nullable String var2);

    @Nonnull
    @CheckReturnValue
    public RestAction<List<Webhook>> retrieveWebhooks();

    @Nonnull
    public List<GuildVoiceState> getVoiceStates();

    @Nonnull
    public VerificationLevel getVerificationLevel();

    @Nonnull
    public NotificationLevel getDefaultNotificationLevel();

    @Nonnull
    public MFALevel getRequiredMFALevel();

    @Nonnull
    public ExplicitContentLevel getExplicitContentLevel();

    @Deprecated
    @ForRemoval(deadline="4.4.0")
    @DeprecatedSince(value="4.2.0")
    public boolean checkVerification();

    @ForRemoval(deadline="4.4.0")
    @Deprecated
    @DeprecatedSince(value="4.1.0")
    @ReplaceWith(value="getJDA().isUnavailable(guild.getIdLong())")
    public boolean isAvailable();

    @Nonnull
    @Deprecated
    @DeprecatedSince(value="4.2.0")
    @ReplaceWith(value="loadMembers(Consumer<Member>) or loadMembers()")
    public CompletableFuture<Void> retrieveMembers();

    @Nonnull
    @CheckReturnValue
    default public Task<List<Member>> loadMembers() {
        return this.findMembers(m2 -> true);
    }

    @Nonnull
    @CheckReturnValue
    default public Task<List<Member>> findMembers(@Nonnull Predicate<? super Member> filter) {
        Checks.notNull(filter, "Filter");
        ArrayList list = new ArrayList();
        CompletableFuture future = new CompletableFuture();
        Task<Void> reference = this.loadMembers(member -> {
            if (filter.test((Member)member)) {
                list.add(member);
            }
        });
        GatewayTask<List<Member>> task = new GatewayTask<List<Member>>(future, reference::cancel);
        reference.onSuccess(it2 -> future.complete(list)).onError(future::completeExceptionally);
        return task;
    }

    @Nonnull
    @CheckReturnValue
    default public Task<List<Member>> findMembersWithRoles(@Nonnull Collection<Role> roles) {
        Checks.noneNull(roles, "Roles");
        for (Role role : roles) {
            Checks.check(this.equals(role.getGuild()), "All roles must be from the same guild!");
        }
        if (this.isLoaded() || roles.isEmpty() || roles.contains(this.getPublicRole())) {
            CompletableFuture<List<Member>> future = CompletableFuture.completedFuture(this.getMembersWithRoles(roles));
            return new GatewayTask<List<Member>>(future, () -> {});
        }
        return this.findMembers(member -> member.getRoles().containsAll(roles));
    }

    @Nonnull
    @CheckReturnValue
    default public Task<List<Member>> findMembersWithRoles(Role ... roles) {
        Checks.noneNull(roles, "Roles");
        return this.findMembersWithRoles(Arrays.asList(roles));
    }

    @Nonnull
    public Task<Void> loadMembers(@Nonnull Consumer<Member> var1);

    @Nonnull
    default public RestAction<Member> retrieveMember(@Nonnull User user) {
        Checks.notNull(user, "User");
        return this.retrieveMemberById(user.getId());
    }

    @Nonnull
    default public RestAction<Member> retrieveMemberById(@Nonnull String id2) {
        return this.retrieveMemberById(MiscUtil.parseSnowflake(id2));
    }

    @Nonnull
    default public RestAction<Member> retrieveMemberById(long id2) {
        return this.retrieveMemberById(id2, true);
    }

    @Nonnull
    default public RestAction<Member> retrieveOwner() {
        return this.retrieveMemberById(this.getOwnerIdLong());
    }

    @Nonnull
    default public RestAction<Member> retrieveMember(@Nonnull User user, boolean update) {
        Checks.notNull(user, "User");
        return this.retrieveMemberById(user.getId(), update);
    }

    @Nonnull
    default public RestAction<Member> retrieveMemberById(@Nonnull String id2, boolean update) {
        return this.retrieveMemberById(MiscUtil.parseSnowflake(id2), update);
    }

    @Nonnull
    public RestAction<Member> retrieveMemberById(long var1, boolean var3);

    @Nonnull
    default public RestAction<Member> retrieveOwner(boolean update) {
        return this.retrieveMemberById(this.getOwnerIdLong(), update);
    }

    @Nonnull
    @CheckReturnValue
    default public Task<List<Member>> retrieveMembers(@Nonnull Collection<User> users) {
        Checks.noneNull(users, "Users");
        if (users.isEmpty()) {
            return new GatewayTask<List<Member>>(CompletableFuture.completedFuture(Collections.emptyList()), () -> {});
        }
        long[] ids = users.stream().mapToLong(ISnowflake::getIdLong).toArray();
        return this.retrieveMembersByIds(ids);
    }

    @Nonnull
    @CheckReturnValue
    default public Task<List<Member>> retrieveMembersByIds(@Nonnull Collection<Long> ids) {
        Checks.noneNull(ids, "IDs");
        if (ids.isEmpty()) {
            return new GatewayTask<List<Member>>(CompletableFuture.completedFuture(Collections.emptyList()), () -> {});
        }
        long[] arr2 = ids.stream().mapToLong(Long::longValue).toArray();
        return this.retrieveMembersByIds(arr2);
    }

    @Nonnull
    @CheckReturnValue
    default public Task<List<Member>> retrieveMembersByIds(String ... ids) {
        Checks.notNull(ids, "Array");
        if (ids.length == 0) {
            return new GatewayTask<List<Member>>(CompletableFuture.completedFuture(Collections.emptyList()), () -> {});
        }
        long[] arr2 = new long[ids.length];
        for (int i2 = 0; i2 < ids.length; ++i2) {
            arr2[i2] = MiscUtil.parseSnowflake(ids[i2]);
        }
        return this.retrieveMembersByIds(arr2);
    }

    @Nonnull
    @CheckReturnValue
    default public Task<List<Member>> retrieveMembersByIds(long ... ids) {
        boolean presence = this.getJDA().getGatewayIntents().contains((Object)GatewayIntent.GUILD_PRESENCES);
        return this.retrieveMembersByIds(presence, ids);
    }

    @Nonnull
    @CheckReturnValue
    default public Task<List<Member>> retrieveMembers(boolean includePresence, @Nonnull Collection<User> users) {
        Checks.noneNull(users, "Users");
        if (users.isEmpty()) {
            return new GatewayTask<List<Member>>(CompletableFuture.completedFuture(Collections.emptyList()), () -> {});
        }
        long[] ids = users.stream().mapToLong(ISnowflake::getIdLong).toArray();
        return this.retrieveMembersByIds(includePresence, ids);
    }

    @Nonnull
    @CheckReturnValue
    default public Task<List<Member>> retrieveMembersByIds(boolean includePresence, @Nonnull Collection<Long> ids) {
        Checks.noneNull(ids, "IDs");
        if (ids.isEmpty()) {
            return new GatewayTask<List<Member>>(CompletableFuture.completedFuture(Collections.emptyList()), () -> {});
        }
        long[] arr2 = ids.stream().mapToLong(Long::longValue).toArray();
        return this.retrieveMembersByIds(includePresence, arr2);
    }

    @Nonnull
    @CheckReturnValue
    default public Task<List<Member>> retrieveMembersByIds(boolean includePresence, String ... ids) {
        Checks.notNull(ids, "Array");
        if (ids.length == 0) {
            return new GatewayTask<List<Member>>(CompletableFuture.completedFuture(Collections.emptyList()), () -> {});
        }
        long[] arr2 = new long[ids.length];
        for (int i2 = 0; i2 < ids.length; ++i2) {
            arr2[i2] = MiscUtil.parseSnowflake(ids[i2]);
        }
        return this.retrieveMembersByIds(includePresence, arr2);
    }

    @Nonnull
    @CheckReturnValue
    public Task<List<Member>> retrieveMembersByIds(boolean var1, long ... var2);

    @Nonnull
    @CheckReturnValue
    public Task<List<Member>> retrieveMembersByPrefix(@Nonnull String var1, int var2);

    @Nonnull
    @CheckReturnValue
    public RestAction<Void> moveVoiceMember(@Nonnull Member var1, @Nullable VoiceChannel var2);

    @Nonnull
    @CheckReturnValue
    default public RestAction<Void> kickVoiceMember(@Nonnull Member member) {
        return this.moveVoiceMember(member, null);
    }

    @Nonnull
    @CheckReturnValue
    public AuditableRestAction<Void> modifyNickname(@Nonnull Member var1, @Nullable String var2);

    @Nonnull
    @CheckReturnValue
    default public AuditableRestAction<Integer> prune(int days, Role ... roles) {
        return this.prune(days, true, roles);
    }

    @Nonnull
    @CheckReturnValue
    public AuditableRestAction<Integer> prune(int var1, boolean var2, Role ... var3);

    @Nonnull
    @CheckReturnValue
    public AuditableRestAction<Void> kick(@Nonnull Member var1, @Nullable String var2);

    @Nonnull
    @CheckReturnValue
    public AuditableRestAction<Void> kick(@Nonnull String var1, @Nullable String var2);

    @Nonnull
    @CheckReturnValue
    default public AuditableRestAction<Void> kick(@Nonnull Member member) {
        return this.kick(member, null);
    }

    @Nonnull
    @CheckReturnValue
    default public AuditableRestAction<Void> kick(@Nonnull String userId) {
        return this.kick(userId, null);
    }

    @Nonnull
    @CheckReturnValue
    public AuditableRestAction<Void> ban(@Nonnull User var1, int var2, @Nullable String var3);

    @Nonnull
    @CheckReturnValue
    public AuditableRestAction<Void> ban(@Nonnull String var1, int var2, @Nullable String var3);

    @Nonnull
    @CheckReturnValue
    default public AuditableRestAction<Void> ban(@Nonnull Member member, int delDays, @Nullable String reason) {
        Checks.notNull(member, "Member");
        return this.ban(member.getUser(), delDays, reason);
    }

    @Nonnull
    @CheckReturnValue
    default public AuditableRestAction<Void> ban(@Nonnull Member member, int delDays) {
        return this.ban(member, delDays, null);
    }

    @Nonnull
    @CheckReturnValue
    default public AuditableRestAction<Void> ban(@Nonnull User user, int delDays) {
        return this.ban(user, delDays, null);
    }

    @Nonnull
    @CheckReturnValue
    default public AuditableRestAction<Void> ban(@Nonnull String userId, int delDays) {
        return this.ban(userId, delDays, null);
    }

    @Nonnull
    @CheckReturnValue
    default public AuditableRestAction<Void> unban(@Nonnull User user) {
        Checks.notNull(user, "User");
        return this.unban(user.getId());
    }

    @Nonnull
    @CheckReturnValue
    public AuditableRestAction<Void> unban(@Nonnull String var1);

    @Nonnull
    @CheckReturnValue
    public AuditableRestAction<Void> deafen(@Nonnull Member var1, boolean var2);

    @Nonnull
    @CheckReturnValue
    public AuditableRestAction<Void> mute(@Nonnull Member var1, boolean var2);

    @Nonnull
    @CheckReturnValue
    public AuditableRestAction<Void> addRoleToMember(@Nonnull Member var1, @Nonnull Role var2);

    @Nonnull
    @CheckReturnValue
    default public AuditableRestAction<Void> addRoleToMember(long userId, @Nonnull Role role) {
        Checks.notNull(role, "Role");
        Checks.check(role.getGuild().equals(this), "Role must be from the same guild! Trying to use role from %s in %s", role.getGuild().toString(), this.toString());
        Member member = this.getMemberById(userId);
        if (member != null) {
            return this.addRoleToMember(member, role);
        }
        if (!this.getSelfMember().hasPermission(Permission.MANAGE_ROLES)) {
            throw new InsufficientPermissionException(this, Permission.MANAGE_ROLES);
        }
        if (!this.getSelfMember().canInteract(role)) {
            throw new HierarchyException("Can't modify a role with higher or equal highest role than yourself! Role: " + role.toString());
        }
        Route.CompiledRoute route = Route.Guilds.ADD_MEMBER_ROLE.compile(this.getId(), Long.toUnsignedString(userId), role.getId());
        return new AuditableRestActionImpl<Void>(this.getJDA(), route);
    }

    @Nonnull
    @CheckReturnValue
    default public AuditableRestAction<Void> addRoleToMember(@Nonnull String userId, @Nonnull Role role) {
        return this.addRoleToMember(MiscUtil.parseSnowflake(userId), role);
    }

    @Nonnull
    @CheckReturnValue
    public AuditableRestAction<Void> removeRoleFromMember(@Nonnull Member var1, @Nonnull Role var2);

    @Nonnull
    @CheckReturnValue
    default public AuditableRestAction<Void> removeRoleFromMember(long userId, @Nonnull Role role) {
        Checks.notNull(role, "Role");
        Checks.check(role.getGuild().equals(this), "Role must be from the same guild! Trying to use role from %s in %s", role.getGuild().toString(), this.toString());
        Member member = this.getMemberById(userId);
        if (member != null) {
            return this.removeRoleFromMember(member, role);
        }
        if (!this.getSelfMember().hasPermission(Permission.MANAGE_ROLES)) {
            throw new InsufficientPermissionException(this, Permission.MANAGE_ROLES);
        }
        if (!this.getSelfMember().canInteract(role)) {
            throw new HierarchyException("Can't modify a role with higher or equal highest role than yourself! Role: " + role.toString());
        }
        Route.CompiledRoute route = Route.Guilds.REMOVE_MEMBER_ROLE.compile(this.getId(), Long.toUnsignedString(userId), role.getId());
        return new AuditableRestActionImpl<Void>(this.getJDA(), route);
    }

    @Nonnull
    @CheckReturnValue
    default public AuditableRestAction<Void> removeRoleFromMember(@Nonnull String userId, @Nonnull Role role) {
        return this.removeRoleFromMember(MiscUtil.parseSnowflake(userId), role);
    }

    @Nonnull
    @CheckReturnValue
    public AuditableRestAction<Void> modifyMemberRoles(@Nonnull Member var1, @Nullable Collection<Role> var2, @Nullable Collection<Role> var3);

    @Nonnull
    @CheckReturnValue
    default public AuditableRestAction<Void> modifyMemberRoles(@Nonnull Member member, Role ... roles) {
        return this.modifyMemberRoles(member, Arrays.asList(roles));
    }

    @Nonnull
    @CheckReturnValue
    public AuditableRestAction<Void> modifyMemberRoles(@Nonnull Member var1, @Nonnull Collection<Role> var2);

    @Nonnull
    @CheckReturnValue
    public AuditableRestAction<Void> transferOwnership(@Nonnull Member var1);

    @Nonnull
    @CheckReturnValue
    default public ChannelAction<TextChannel> createTextChannel(@Nonnull String name) {
        return this.createTextChannel(name, null);
    }

    @Nonnull
    @CheckReturnValue
    public ChannelAction<TextChannel> createTextChannel(@Nonnull String var1, @Nullable Category var2);

    @Nonnull
    @CheckReturnValue
    default public ChannelAction<VoiceChannel> createVoiceChannel(@Nonnull String name) {
        return this.createVoiceChannel(name, null);
    }

    @Nonnull
    @CheckReturnValue
    public ChannelAction<VoiceChannel> createVoiceChannel(@Nonnull String var1, @Nullable Category var2);

    @Nonnull
    @CheckReturnValue
    public ChannelAction<Category> createCategory(@Nonnull String var1);

    @Nonnull
    @CheckReturnValue
    default public <T extends GuildChannel> ChannelAction<T> createCopyOfChannel(@Nonnull T channel) {
        Checks.notNull(channel, "Channel");
        return channel.createCopy(this);
    }

    @Nonnull
    @CheckReturnValue
    public RoleAction createRole();

    @Nonnull
    @CheckReturnValue
    default public RoleAction createCopyOfRole(@Nonnull Role role) {
        Checks.notNull(role, "Role");
        return role.createCopy(this);
    }

    @Nonnull
    @CheckReturnValue
    public AuditableRestAction<Emote> createEmote(@Nonnull String var1, @Nonnull Icon var2, Role ... var3);

    @Nonnull
    @CheckReturnValue
    public ChannelOrderAction modifyCategoryPositions();

    @Nonnull
    @CheckReturnValue
    public ChannelOrderAction modifyTextChannelPositions();

    @Nonnull
    @CheckReturnValue
    public ChannelOrderAction modifyVoiceChannelPositions();

    @Nonnull
    @CheckReturnValue
    public CategoryOrderAction modifyTextChannelPositions(@Nonnull Category var1);

    @Nonnull
    @CheckReturnValue
    public CategoryOrderAction modifyVoiceChannelPositions(@Nonnull Category var1);

    @Nonnull
    @CheckReturnValue
    default public RoleOrderAction modifyRolePositions() {
        return this.modifyRolePositions(true);
    }

    @Nonnull
    @CheckReturnValue
    public RoleOrderAction modifyRolePositions(boolean var1);

    public static class MetaData {
        private final int memberLimit;
        private final int presenceLimit;
        private final int approximatePresences;
        private final int approximateMembers;

        public MetaData(int memberLimit, int presenceLimit, int approximatePresences, int approximateMembers) {
            this.memberLimit = memberLimit;
            this.presenceLimit = presenceLimit;
            this.approximatePresences = approximatePresences;
            this.approximateMembers = approximateMembers;
        }

        public int getMemberLimit() {
            return this.memberLimit;
        }

        public int getPresenceLimit() {
            return this.presenceLimit;
        }

        public int getApproximatePresences() {
            return this.approximatePresences;
        }

        public int getApproximateMembers() {
            return this.approximateMembers;
        }
    }

    public static class Ban {
        protected final User user;
        protected final String reason;

        public Ban(User user, String reason) {
            this.user = user;
            this.reason = reason;
        }

        @Nonnull
        public User getUser() {
            return this.user;
        }

        @Nullable
        public String getReason() {
            return this.reason;
        }

        public String toString() {
            return "GuildBan:" + this.user + (this.reason == null ? "" : '(' + this.reason + ')');
        }
    }

    public static enum BoostTier {
        NONE(0, 96000, 50),
        TIER_1(1, 128000, 100),
        TIER_2(2, 256000, 150),
        TIER_3(3, 384000, 250),
        UNKNOWN(-1, Integer.MAX_VALUE, Integer.MAX_VALUE);

        private final int key;
        private final int maxBitrate;
        private final int maxEmotes;

        private BoostTier(int key, int maxBitrate, int maxEmotes) {
            this.key = key;
            this.maxBitrate = maxBitrate;
            this.maxEmotes = maxEmotes;
        }

        public int getKey() {
            return this.key;
        }

        public int getMaxBitrate() {
            return this.maxBitrate;
        }

        public int getMaxEmotes() {
            return this.maxEmotes;
        }

        public long getMaxFileSize() {
            if (this.key == 2) {
                return 0x3200000L;
            }
            if (this.key == 3) {
                return 0x6400000L;
            }
            return 0x800000L;
        }

        @Nonnull
        public static BoostTier fromKey(int key) {
            for (BoostTier tier : BoostTier.values()) {
                if (tier.key != key) continue;
                return tier;
            }
            return UNKNOWN;
        }
    }

    public static enum ExplicitContentLevel {
        OFF(0, "Don't scan any messages."),
        NO_ROLE(1, "Scan messages from members without a role."),
        ALL(2, "Scan messages sent by all members."),
        UNKNOWN(-1, "Unknown filter level!");

        private final int key;
        private final String description;

        private ExplicitContentLevel(int key, String description) {
            this.key = key;
            this.description = description;
        }

        public int getKey() {
            return this.key;
        }

        @Nonnull
        public String getDescription() {
            return this.description;
        }

        @Nonnull
        public static ExplicitContentLevel fromKey(int key) {
            for (ExplicitContentLevel level : ExplicitContentLevel.values()) {
                if (level.key != key) continue;
                return level;
            }
            return UNKNOWN;
        }
    }

    public static enum MFALevel {
        NONE(0),
        TWO_FACTOR_AUTH(1),
        UNKNOWN(-1);

        private final int key;

        private MFALevel(int key) {
            this.key = key;
        }

        public int getKey() {
            return this.key;
        }

        @Nonnull
        public static MFALevel fromKey(int key) {
            for (MFALevel level : MFALevel.values()) {
                if (level.getKey() != key) continue;
                return level;
            }
            return UNKNOWN;
        }
    }

    public static enum NotificationLevel {
        ALL_MESSAGES(0),
        MENTIONS_ONLY(1),
        UNKNOWN(-1);

        private final int key;

        private NotificationLevel(int key) {
            this.key = key;
        }

        public int getKey() {
            return this.key;
        }

        @Nonnull
        public static NotificationLevel fromKey(int key) {
            for (NotificationLevel level : NotificationLevel.values()) {
                if (level.getKey() != key) continue;
                return level;
            }
            return UNKNOWN;
        }
    }

    public static enum VerificationLevel {
        NONE(0),
        LOW(1),
        MEDIUM(2),
        HIGH(3),
        VERY_HIGH(4),
        UNKNOWN(-1);

        private final int key;

        private VerificationLevel(int key) {
            this.key = key;
        }

        public int getKey() {
            return this.key;
        }

        @Nonnull
        public static VerificationLevel fromKey(int key) {
            for (VerificationLevel level : VerificationLevel.values()) {
                if (level.getKey() != key) continue;
                return level;
            }
            return UNKNOWN;
        }
    }

    public static enum Timeout {
        SECONDS_60(60),
        SECONDS_300(300),
        SECONDS_900(900),
        SECONDS_1800(1800),
        SECONDS_3600(3600);

        private final int seconds;

        private Timeout(int seconds) {
            this.seconds = seconds;
        }

        public int getSeconds() {
            return this.seconds;
        }

        @Nonnull
        public static Timeout fromKey(int seconds) {
            for (Timeout t2 : Timeout.values()) {
                if (t2.getSeconds() != seconds) continue;
                return t2;
            }
            throw new IllegalArgumentException("Provided key was not recognized. Seconds: " + seconds);
        }
    }
}

