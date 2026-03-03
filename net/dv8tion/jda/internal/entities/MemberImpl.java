/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.internal.entities;

import gnu.trove.map.TLongObjectMap;
import java.awt.Color;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.ClientType;
import net.dv8tion.jda.api.entities.Emote;
import net.dv8tion.jda.api.entities.GuildChannel;
import net.dv8tion.jda.api.entities.GuildVoiceState;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.PermissionOverride;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.utils.cache.CacheFlag;
import net.dv8tion.jda.api.utils.cache.CacheView;
import net.dv8tion.jda.internal.JDAImpl;
import net.dv8tion.jda.internal.entities.AbstractChannelImpl;
import net.dv8tion.jda.internal.entities.GuildImpl;
import net.dv8tion.jda.internal.entities.GuildVoiceStateImpl;
import net.dv8tion.jda.internal.entities.MemberPresenceImpl;
import net.dv8tion.jda.internal.utils.Checks;
import net.dv8tion.jda.internal.utils.Helpers;
import net.dv8tion.jda.internal.utils.PermissionUtil;

public class MemberImpl
implements Member {
    private static final ZoneOffset OFFSET = ZoneOffset.of("+00:00");
    private final JDAImpl api;
    private final Set<Role> roles = ConcurrentHashMap.newKeySet();
    private final GuildVoiceState voiceState;
    private GuildImpl guild;
    private User user;
    private String nickname;
    private long joinDate;
    private long boostDate;
    private boolean pending = false;

    public MemberImpl(GuildImpl guild, User user) {
        this.api = (JDAImpl)user.getJDA();
        this.guild = guild;
        this.user = user;
        this.joinDate = 0L;
        boolean cacheState = this.api.isCacheFlagSet(CacheFlag.VOICE_STATE) || user.equals(this.api.getSelfUser());
        this.voiceState = cacheState ? new GuildVoiceStateImpl(this) : null;
    }

    public MemberPresenceImpl getPresence() {
        CacheView.SimpleCacheView<MemberPresenceImpl> presences = this.guild.getPresenceView();
        return presences == null ? null : (MemberPresenceImpl)presences.get(this.getIdLong());
    }

    @Override
    @Nonnull
    public User getUser() {
        User realUser = this.getJDA().getUserById(this.user.getIdLong());
        if (realUser != null) {
            this.user = realUser;
        }
        return this.user;
    }

    @Override
    @Nonnull
    public GuildImpl getGuild() {
        GuildImpl realGuild = (GuildImpl)this.api.getGuildById(this.guild.getIdLong());
        if (realGuild != null) {
            this.guild = realGuild;
        }
        return this.guild;
    }

    @Override
    @Nonnull
    public JDA getJDA() {
        return this.api;
    }

    @Override
    @Nonnull
    public OffsetDateTime getTimeJoined() {
        if (this.hasTimeJoined()) {
            return OffsetDateTime.ofInstant(Instant.ofEpochMilli(this.joinDate), OFFSET);
        }
        return this.getGuild().getTimeCreated();
    }

    @Override
    public boolean hasTimeJoined() {
        return this.joinDate != 0L;
    }

    @Override
    @Nullable
    public OffsetDateTime getTimeBoosted() {
        return this.boostDate != 0L ? OffsetDateTime.ofInstant(Instant.ofEpochMilli(this.boostDate), OFFSET) : null;
    }

    @Override
    public GuildVoiceState getVoiceState() {
        return this.voiceState;
    }

    @Override
    @Nonnull
    public List<Activity> getActivities() {
        MemberPresenceImpl presence = this.getPresence();
        return presence == null ? Collections.emptyList() : presence.getActivities();
    }

    @Override
    @Nonnull
    public OnlineStatus getOnlineStatus() {
        MemberPresenceImpl presence = this.getPresence();
        return presence == null ? OnlineStatus.OFFLINE : presence.getOnlineStatus();
    }

    @Override
    @Nonnull
    public OnlineStatus getOnlineStatus(@Nonnull ClientType type) {
        Checks.notNull((Object)type, "Type");
        MemberPresenceImpl presence = this.getPresence();
        if (presence == null) {
            return OnlineStatus.OFFLINE;
        }
        OnlineStatus status = presence.getClientStatus().get((Object)type);
        return status == null ? OnlineStatus.OFFLINE : status;
    }

    @Override
    @Nonnull
    public EnumSet<ClientType> getActiveClients() {
        MemberPresenceImpl presence = this.getPresence();
        return presence == null ? EnumSet.noneOf(ClientType.class) : Helpers.copyEnumSet(ClientType.class, presence.getClientStatus().keySet());
    }

    @Override
    public String getNickname() {
        return this.nickname;
    }

    @Override
    @Nonnull
    public String getEffectiveName() {
        return this.nickname != null ? this.nickname : this.getUser().getName();
    }

    @Override
    @Nonnull
    public List<Role> getRoles() {
        ArrayList<Role> roleList = new ArrayList<Role>(this.roles);
        roleList.sort(Comparator.reverseOrder());
        return Collections.unmodifiableList(roleList);
    }

    @Override
    public Color getColor() {
        int raw = this.getColorRaw();
        return raw != 0x1FFFFFFF ? new Color(raw) : null;
    }

    @Override
    public int getColorRaw() {
        for (Role r2 : this.getRoles()) {
            int colorRaw = r2.getColorRaw();
            if (colorRaw == 0x1FFFFFFF) continue;
            return colorRaw;
        }
        return 0x1FFFFFFF;
    }

    @Override
    @Nonnull
    public EnumSet<Permission> getPermissions() {
        return Permission.getPermissions(PermissionUtil.getEffectivePermission(this));
    }

    @Override
    @Nonnull
    public EnumSet<Permission> getPermissions(@Nonnull GuildChannel channel) {
        Checks.notNull(channel, "Channel");
        if (!this.getGuild().equals(channel.getGuild())) {
            throw new IllegalArgumentException("Provided channel is not in the same guild as this member!");
        }
        return Permission.getPermissions(PermissionUtil.getEffectivePermission(channel, this));
    }

    @Override
    @Nonnull
    public EnumSet<Permission> getPermissionsExplicit() {
        return Permission.getPermissions(PermissionUtil.getExplicitPermission(this));
    }

    @Override
    @Nonnull
    public EnumSet<Permission> getPermissionsExplicit(@Nonnull GuildChannel channel) {
        return Permission.getPermissions(PermissionUtil.getExplicitPermission(channel, this));
    }

    @Override
    public boolean hasPermission(Permission ... permissions) {
        return PermissionUtil.checkPermission(this, permissions);
    }

    @Override
    public boolean hasPermission(@Nonnull Collection<Permission> permissions) {
        Checks.notNull(permissions, "Permission Collection");
        return this.hasPermission(permissions.toArray(Permission.EMPTY_PERMISSIONS));
    }

    @Override
    public boolean hasPermission(@Nonnull GuildChannel channel, Permission ... permissions) {
        return PermissionUtil.checkPermission(channel, this, permissions);
    }

    @Override
    public boolean hasPermission(@Nonnull GuildChannel channel, @Nonnull Collection<Permission> permissions) {
        Checks.notNull(permissions, "Permission Collection");
        return this.hasPermission(channel, permissions.toArray(Permission.EMPTY_PERMISSIONS));
    }

    @Override
    public boolean canSync(@Nonnull GuildChannel targetChannel, @Nonnull GuildChannel syncSource) {
        boolean hasLocalAdmin;
        Checks.notNull(targetChannel, "Channel");
        Checks.notNull(syncSource, "Channel");
        Checks.check(targetChannel.getGuild().equals(this.getGuild()), "Channels must be from the same guild!");
        Checks.check(syncSource.getGuild().equals(this.getGuild()), "Channels must be from the same guild!");
        long userPerms = PermissionUtil.getEffectivePermission(targetChannel, this);
        if ((userPerms & Permission.MANAGE_PERMISSIONS.getRawValue()) == 0L) {
            return false;
        }
        long channelPermissions = PermissionUtil.getExplicitPermission(targetChannel, this, false);
        boolean bl2 = hasLocalAdmin = (userPerms & Permission.ADMINISTRATOR.getRawValue() | channelPermissions & Permission.MANAGE_PERMISSIONS.getRawValue()) != 0L;
        if (hasLocalAdmin) {
            return true;
        }
        TLongObjectMap<PermissionOverride> existingOverrides = ((AbstractChannelImpl)targetChannel).getOverrideMap();
        for (PermissionOverride override : syncSource.getPermissionOverrides()) {
            PermissionOverride existing = existingOverrides.get(override.getIdLong());
            long allow = override.getAllowedRaw();
            long deny = override.getDeniedRaw();
            if (existing != null) {
                allow ^= existing.getAllowedRaw();
                deny ^= existing.getDeniedRaw();
            }
            if (((allow | deny) & (userPerms ^ 0xFFFFFFFFFFFFFFFFL)) == 0L) continue;
            return false;
        }
        return true;
    }

    @Override
    public boolean canSync(@Nonnull GuildChannel channel) {
        Checks.notNull(channel, "Channel");
        Checks.check(channel.getGuild().equals(this.getGuild()), "Channels must be from the same guild!");
        long userPerms = PermissionUtil.getEffectivePermission(channel, this);
        if ((userPerms & Permission.MANAGE_PERMISSIONS.getRawValue()) == 0L) {
            return false;
        }
        long channelPermissions = PermissionUtil.getExplicitPermission(channel, this, false);
        return (userPerms & Permission.ADMINISTRATOR.getRawValue() | channelPermissions & Permission.MANAGE_PERMISSIONS.getRawValue()) != 0L;
    }

    @Override
    public boolean canInteract(@Nonnull Member member) {
        return PermissionUtil.canInteract((Member)this, member);
    }

    @Override
    public boolean canInteract(@Nonnull Role role) {
        return PermissionUtil.canInteract((Member)this, role);
    }

    @Override
    public boolean canInteract(@Nonnull Emote emote) {
        return PermissionUtil.canInteract((Member)this, emote);
    }

    @Override
    public boolean isOwner() {
        return this.user.getIdLong() == this.getGuild().getOwnerIdLong();
    }

    @Override
    public boolean isPending() {
        return this.pending;
    }

    @Override
    public long getIdLong() {
        return this.user.getIdLong();
    }

    public MemberImpl setNickname(String nickname) {
        this.nickname = nickname;
        return this;
    }

    public MemberImpl setJoinDate(long joinDate) {
        this.joinDate = joinDate;
        return this;
    }

    public MemberImpl setBoostDate(long boostDate) {
        this.boostDate = boostDate;
        return this;
    }

    public MemberImpl setPending(boolean pending) {
        this.pending = pending;
        return this;
    }

    public Set<Role> getRoleSet() {
        return this.roles;
    }

    public long getBoostDateRaw() {
        return this.boostDate;
    }

    public boolean equals(Object o2) {
        if (o2 == this) {
            return true;
        }
        if (!(o2 instanceof MemberImpl)) {
            return false;
        }
        MemberImpl oMember = (MemberImpl)o2;
        return oMember.user.getIdLong() == this.user.getIdLong() && oMember.guild.getIdLong() == this.guild.getIdLong();
    }

    public int hashCode() {
        return (this.guild.getIdLong() + this.user.getId()).hashCode();
    }

    public String toString() {
        return "MB:" + this.getEffectiveName() + '(' + this.getUser().toString() + " / " + this.getGuild().toString() + ')';
    }

    @Override
    @Nonnull
    public String getAsMention() {
        return (this.nickname == null ? "<@" : "<@!") + this.user.getId() + '>';
    }

    @Override
    @Nullable
    public TextChannel getDefaultChannel() {
        return this.getGuild().getTextChannelsView().stream().sorted(Comparator.reverseOrder()).filter(c2 -> this.hasPermission((GuildChannel)c2, Permission.MESSAGE_READ)).findFirst().orElse(null);
    }
}

