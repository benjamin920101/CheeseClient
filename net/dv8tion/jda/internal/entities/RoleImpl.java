/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.internal.entities;

import gnu.trove.map.TLongObjectMap;
import java.awt.Color;
import java.time.OffsetDateTime;
import java.util.Collection;
import java.util.EnumSet;
import java.util.Objects;
import javax.annotation.Nonnull;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.GuildChannel;
import net.dv8tion.jda.api.entities.PermissionOverride;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.exceptions.HierarchyException;
import net.dv8tion.jda.api.exceptions.InsufficientPermissionException;
import net.dv8tion.jda.api.managers.RoleManager;
import net.dv8tion.jda.api.requests.restaction.AuditableRestAction;
import net.dv8tion.jda.api.requests.restaction.RoleAction;
import net.dv8tion.jda.api.utils.cache.CacheFlag;
import net.dv8tion.jda.api.utils.data.DataObject;
import net.dv8tion.jda.internal.JDAImpl;
import net.dv8tion.jda.internal.entities.AbstractChannelImpl;
import net.dv8tion.jda.internal.managers.RoleManagerImpl;
import net.dv8tion.jda.internal.requests.Route;
import net.dv8tion.jda.internal.requests.restaction.AuditableRestActionImpl;
import net.dv8tion.jda.internal.utils.Checks;
import net.dv8tion.jda.internal.utils.PermissionUtil;
import net.dv8tion.jda.internal.utils.cache.SortedSnowflakeCacheViewImpl;

public class RoleImpl
implements Role {
    private final long id;
    private final JDAImpl api;
    private Guild guild;
    private RoleManager manager;
    private RoleTagsImpl tags;
    private String name;
    private boolean managed;
    private boolean hoisted;
    private boolean mentionable;
    private long rawPermissions;
    private int color;
    private int rawPosition;

    public RoleImpl(long id2, Guild guild) {
        this.id = id2;
        this.api = (JDAImpl)guild.getJDA();
        this.guild = guild;
        this.tags = this.api.isCacheFlagSet(CacheFlag.ROLE_TAGS) ? new RoleTagsImpl() : null;
    }

    @Override
    public int getPosition() {
        Guild guild = this.getGuild();
        if (this.equals(guild.getPublicRole())) {
            return -1;
        }
        int i2 = guild.getRoles().size() - 2;
        for (Role r2 : guild.getRoles()) {
            if (this.equals(r2)) {
                return i2;
            }
            --i2;
        }
        throw new IllegalStateException("Somehow when determining position we never found the role in the Guild's roles? wtf?");
    }

    @Override
    public int getPositionRaw() {
        return this.rawPosition;
    }

    @Override
    @Nonnull
    public String getName() {
        return this.name;
    }

    @Override
    public boolean isManaged() {
        return this.managed;
    }

    @Override
    public boolean isHoisted() {
        return this.hoisted;
    }

    @Override
    public boolean isMentionable() {
        return this.mentionable;
    }

    @Override
    public long getPermissionsRaw() {
        return this.rawPermissions;
    }

    @Override
    @Nonnull
    public EnumSet<Permission> getPermissions() {
        return Permission.getPermissions(this.rawPermissions);
    }

    @Override
    @Nonnull
    public EnumSet<Permission> getPermissions(@Nonnull GuildChannel channel) {
        return Permission.getPermissions(PermissionUtil.getEffectivePermission(channel, this));
    }

    @Override
    @Nonnull
    public EnumSet<Permission> getPermissionsExplicit() {
        return this.getPermissions();
    }

    @Override
    @Nonnull
    public EnumSet<Permission> getPermissionsExplicit(@Nonnull GuildChannel channel) {
        return Permission.getPermissions(PermissionUtil.getExplicitPermission(channel, this));
    }

    @Override
    public Color getColor() {
        return this.color != 0x1FFFFFFF ? new Color(this.color) : null;
    }

    @Override
    public int getColorRaw() {
        return this.color;
    }

    @Override
    public boolean isPublicRole() {
        return this.equals(this.getGuild().getPublicRole());
    }

    @Override
    public boolean hasPermission(Permission ... permissions) {
        long effectivePerms = this.rawPermissions | this.getGuild().getPublicRole().getPermissionsRaw();
        for (Permission perm : permissions) {
            long rawValue = perm.getRawValue();
            if ((effectivePerms & rawValue) == rawValue) continue;
            return false;
        }
        return true;
    }

    @Override
    public boolean hasPermission(@Nonnull Collection<Permission> permissions) {
        Checks.notNull(permissions, "Permission Collection");
        return this.hasPermission(permissions.toArray(Permission.EMPTY_PERMISSIONS));
    }

    @Override
    public boolean hasPermission(@Nonnull GuildChannel channel, Permission ... permissions) {
        long effectivePerms = PermissionUtil.getEffectivePermission(channel, this);
        for (Permission perm : permissions) {
            long rawValue = perm.getRawValue();
            if ((effectivePerms & rawValue) == rawValue) continue;
            return false;
        }
        return true;
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
        long rolePerms = PermissionUtil.getEffectivePermission(targetChannel, this);
        if ((rolePerms & Permission.MANAGE_PERMISSIONS.getRawValue()) == 0L) {
            return false;
        }
        long channelPermissions = PermissionUtil.getExplicitPermission(targetChannel, this, false);
        boolean bl2 = hasLocalAdmin = (rolePerms & Permission.ADMINISTRATOR.getRawValue() | channelPermissions & Permission.MANAGE_PERMISSIONS.getRawValue()) != 0L;
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
            if (((allow | deny) & (rolePerms ^ 0xFFFFFFFFFFFFFFFFL)) == 0L) continue;
            return false;
        }
        return true;
    }

    @Override
    public boolean canSync(@Nonnull GuildChannel channel) {
        Checks.notNull(channel, "Channel");
        Checks.check(channel.getGuild().equals(this.getGuild()), "Channels must be from the same guild!");
        long rolePerms = PermissionUtil.getEffectivePermission(channel, this);
        if ((rolePerms & Permission.MANAGE_PERMISSIONS.getRawValue()) == 0L) {
            return false;
        }
        long channelPermissions = PermissionUtil.getExplicitPermission(channel, this, false);
        return (rolePerms & Permission.ADMINISTRATOR.getRawValue() | channelPermissions & Permission.MANAGE_PERMISSIONS.getRawValue()) != 0L;
    }

    @Override
    public boolean canInteract(@Nonnull Role role) {
        return PermissionUtil.canInteract(this, role);
    }

    @Override
    @Nonnull
    public Guild getGuild() {
        Guild realGuild = this.api.getGuildById(this.guild.getIdLong());
        if (realGuild != null) {
            this.guild = realGuild;
        }
        return this.guild;
    }

    @Override
    @Nonnull
    public RoleAction createCopy(@Nonnull Guild guild) {
        Checks.notNull(guild, "Guild");
        return guild.createRole().setColor(this.color).setHoisted(this.hoisted).setMentionable(this.mentionable).setName(this.name).setPermissions(this.rawPermissions);
    }

    @Override
    @Nonnull
    public RoleManager getManager() {
        if (this.manager == null) {
            this.manager = new RoleManagerImpl(this);
            return this.manager;
        }
        return this.manager;
    }

    @Override
    @Nonnull
    public AuditableRestAction<Void> delete() {
        Guild guild = this.getGuild();
        if (!guild.getSelfMember().hasPermission(Permission.MANAGE_ROLES)) {
            throw new InsufficientPermissionException(guild, Permission.MANAGE_ROLES);
        }
        if (!PermissionUtil.canInteract(guild.getSelfMember(), (Role)this)) {
            throw new HierarchyException("Can't delete role >= highest self-role");
        }
        if (this.managed) {
            throw new UnsupportedOperationException("Cannot delete a Role that is managed. ");
        }
        Route.CompiledRoute route = Route.Roles.DELETE_ROLE.compile(guild.getId(), this.getId());
        return new AuditableRestActionImpl<Void>(this.getJDA(), route);
    }

    @Override
    @Nonnull
    public JDA getJDA() {
        return this.api;
    }

    @Override
    @Nonnull
    public Role.RoleTags getTags() {
        return this.tags == null ? RoleTagsImpl.EMPTY : this.tags;
    }

    @Override
    @Nonnull
    public String getAsMention() {
        return this.isPublicRole() ? "@everyone" : "<@&" + this.getId() + '>';
    }

    @Override
    public long getIdLong() {
        return this.id;
    }

    public boolean equals(Object o2) {
        if (o2 == this) {
            return true;
        }
        if (!(o2 instanceof Role)) {
            return false;
        }
        Role oRole = (Role)o2;
        return this.getIdLong() == oRole.getIdLong();
    }

    public int hashCode() {
        return Long.hashCode(this.id);
    }

    public String toString() {
        return "R:" + this.getName() + '(' + this.id + ')';
    }

    @Override
    public int compareTo(@Nonnull Role r2) {
        if (this == r2) {
            return 0;
        }
        if (!(r2 instanceof RoleImpl)) {
            throw new IllegalArgumentException("Cannot compare different role implementations");
        }
        RoleImpl impl = (RoleImpl)r2;
        if (this.guild.getIdLong() != impl.guild.getIdLong()) {
            throw new IllegalArgumentException("Cannot compare roles that aren't from the same guild!");
        }
        if (this.getPositionRaw() != r2.getPositionRaw()) {
            return this.getPositionRaw() - r2.getPositionRaw();
        }
        OffsetDateTime thisTime = this.getTimeCreated();
        OffsetDateTime rTime = r2.getTimeCreated();
        return rTime.compareTo(thisTime);
    }

    public RoleImpl setName(String name) {
        this.name = name;
        return this;
    }

    public RoleImpl setColor(int color) {
        this.color = color;
        return this;
    }

    public RoleImpl setManaged(boolean managed) {
        this.managed = managed;
        return this;
    }

    public RoleImpl setHoisted(boolean hoisted) {
        this.hoisted = hoisted;
        return this;
    }

    public RoleImpl setMentionable(boolean mentionable) {
        this.mentionable = mentionable;
        return this;
    }

    public RoleImpl setRawPermissions(long rawPermissions) {
        this.rawPermissions = rawPermissions;
        return this;
    }

    public RoleImpl setRawPosition(int rawPosition) {
        SortedSnowflakeCacheViewImpl roleCache = (SortedSnowflakeCacheViewImpl)this.getGuild().getRoleCache();
        roleCache.clearCachedLists();
        this.rawPosition = rawPosition;
        return this;
    }

    public RoleImpl setTags(DataObject tags) {
        if (this.tags == null) {
            return this;
        }
        this.tags = new RoleTagsImpl(tags);
        return this;
    }

    public static class RoleTagsImpl
    implements Role.RoleTags {
        public static final Role.RoleTags EMPTY = new RoleTagsImpl();
        private final long botId;
        private final long integrationId;
        private final boolean premiumSubscriber;

        public RoleTagsImpl() {
            this.botId = 0L;
            this.integrationId = 0L;
            this.premiumSubscriber = false;
        }

        public RoleTagsImpl(DataObject tags) {
            this.botId = tags.hasKey("bot_id") ? tags.getUnsignedLong("bot_id") : 0L;
            this.integrationId = tags.hasKey("integration_id") ? tags.getUnsignedLong("integration_id") : 0L;
            this.premiumSubscriber = tags.hasKey("premium_subscriber");
        }

        @Override
        public boolean isBot() {
            return this.botId != 0L;
        }

        @Override
        public long getBotIdLong() {
            return this.botId;
        }

        @Override
        public boolean isBoost() {
            return this.premiumSubscriber;
        }

        @Override
        public boolean isIntegration() {
            return this.integrationId != 0L;
        }

        @Override
        public long getIntegrationIdLong() {
            return this.integrationId;
        }

        public int hashCode() {
            return Objects.hash(this.botId, this.integrationId, this.premiumSubscriber);
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof RoleTagsImpl)) {
                return false;
            }
            RoleTagsImpl other = (RoleTagsImpl)obj;
            return this.botId == other.botId && this.integrationId == other.integrationId && this.premiumSubscriber == other.premiumSubscriber;
        }

        public String toString() {
            return "RoleTags(bot=" + this.getBotId() + ",integration=" + this.getIntegrationId() + ",boost=" + this.isBoost() + ")";
        }
    }
}

