/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.internal.entities;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Set;
import javax.annotation.Nonnull;
import net.dv8tion.jda.annotations.DeprecatedSince;
import net.dv8tion.jda.annotations.ReplaceWith;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.GuildChannel;
import net.dv8tion.jda.api.entities.Invite;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.exceptions.InsufficientPermissionException;
import net.dv8tion.jda.api.requests.RestAction;
import net.dv8tion.jda.api.requests.restaction.AuditableRestAction;
import net.dv8tion.jda.api.utils.data.DataArray;
import net.dv8tion.jda.api.utils.data.DataObject;
import net.dv8tion.jda.internal.JDAImpl;
import net.dv8tion.jda.internal.entities.EntityBuilder;
import net.dv8tion.jda.internal.requests.CompletedRestAction;
import net.dv8tion.jda.internal.requests.RestActionImpl;
import net.dv8tion.jda.internal.requests.Route;
import net.dv8tion.jda.internal.requests.restaction.AuditableRestActionImpl;
import net.dv8tion.jda.internal.utils.Checks;

public class InviteImpl
implements Invite {
    private final JDAImpl api;
    private final Invite.Channel channel;
    private final String code;
    private final boolean expanded;
    private final Invite.Guild guild;
    private final Invite.Group group;
    private final User inviter;
    private final int maxAge;
    private final int maxUses;
    private final boolean temporary;
    private final OffsetDateTime timeCreated;
    private final int uses;
    private final Invite.InviteType type;

    public InviteImpl(JDAImpl api2, String code, boolean expanded, User inviter, int maxAge, int maxUses, boolean temporary, OffsetDateTime timeCreated, int uses, Invite.Channel channel, Invite.Guild guild, Invite.Group group, Invite.InviteType type) {
        this.api = api2;
        this.code = code;
        this.expanded = expanded;
        this.inviter = inviter;
        this.maxAge = maxAge;
        this.maxUses = maxUses;
        this.temporary = temporary;
        this.timeCreated = timeCreated;
        this.uses = uses;
        this.channel = channel;
        this.guild = guild;
        this.group = group;
        this.type = type;
    }

    public static RestAction<Invite> resolve(JDA api2, String code, boolean withCounts) {
        Checks.notNull(code, "code");
        Checks.notNull(api2, "api");
        Route.CompiledRoute route = Route.Invites.GET_INVITE.compile(code);
        if (withCounts) {
            route = route.withQueryParams("with_counts", "true");
        }
        JDAImpl jda = (JDAImpl)api2;
        return new RestActionImpl<Invite>(api2, route, (response, request) -> jda.getEntityBuilder().createInvite(response.getObject()));
    }

    @Override
    @Nonnull
    public AuditableRestAction<Void> delete() {
        Route.CompiledRoute route = Route.Invites.DELETE_INVITE.compile(this.code);
        return new AuditableRestActionImpl<Void>(this.api, route);
    }

    @Override
    @Nonnull
    public RestAction<Invite> expand() {
        Route.CompiledRoute route;
        GuildChannel channel;
        if (this.expanded) {
            return new CompletedRestAction<Invite>((JDA)this.getJDA(), this);
        }
        if (this.type != Invite.InviteType.GUILD) {
            throw new IllegalStateException("Only guild invites can be expanded");
        }
        Guild guild = this.api.getGuildById(this.guild.getIdLong());
        if (guild == null) {
            throw new UnsupportedOperationException("You're not in the guild this invite points to");
        }
        Member member = guild.getSelfMember();
        GuildChannel guildChannel = channel = this.channel.getType() == ChannelType.TEXT ? guild.getTextChannelById(this.channel.getIdLong()) : guild.getVoiceChannelById(this.channel.getIdLong());
        if (member.hasPermission(channel, Permission.MANAGE_CHANNEL)) {
            route = Route.Invites.GET_CHANNEL_INVITES.compile(channel.getId());
        } else if (member.hasPermission(Permission.MANAGE_SERVER)) {
            route = Route.Invites.GET_GUILD_INVITES.compile(guild.getId());
        } else {
            throw new InsufficientPermissionException(channel, Permission.MANAGE_CHANNEL, "You don't have the permission to view the full invite info");
        }
        return new RestActionImpl<Invite>((JDA)this.api, route, (response, request) -> {
            EntityBuilder entityBuilder = this.api.getEntityBuilder();
            DataArray array = response.getArray();
            for (int i2 = 0; i2 < array.length(); ++i2) {
                DataObject object = array.getObject(i2);
                if (!this.code.equals(object.getString("code"))) continue;
                return entityBuilder.createInvite(object);
            }
            throw new IllegalStateException("Missing the invite in the channel/guild invite list");
        });
    }

    @Override
    @Nonnull
    public Invite.InviteType getType() {
        return this.type;
    }

    @Override
    public Invite.Channel getChannel() {
        return this.channel;
    }

    @Override
    @Nonnull
    public String getCode() {
        return this.code;
    }

    @Override
    @Nonnull
    @Deprecated
    @DeprecatedSince(value="4.BETA.0")
    @ReplaceWith(value="getTimeCreated()")
    public OffsetDateTime getCreationTime() {
        return this.getTimeCreated();
    }

    @Override
    public Invite.Guild getGuild() {
        return this.guild;
    }

    @Override
    public Invite.Group getGroup() {
        return this.group;
    }

    @Override
    public User getInviter() {
        return this.inviter;
    }

    @Override
    @Nonnull
    public JDAImpl getJDA() {
        return this.api;
    }

    @Override
    public int getMaxAge() {
        if (!this.expanded) {
            throw new IllegalStateException("Only valid for expanded invites");
        }
        return this.maxAge;
    }

    @Override
    public int getMaxUses() {
        if (!this.expanded) {
            throw new IllegalStateException("Only valid for expanded invites");
        }
        return this.maxUses;
    }

    @Override
    @Nonnull
    public OffsetDateTime getTimeCreated() {
        if (!this.expanded) {
            throw new IllegalStateException("Only valid for expanded invites");
        }
        return this.timeCreated;
    }

    @Override
    public int getUses() {
        if (!this.expanded) {
            throw new IllegalStateException("Only valid for expanded invites");
        }
        return this.uses;
    }

    @Override
    public boolean isExpanded() {
        return this.expanded;
    }

    @Override
    public boolean isTemporary() {
        if (!this.expanded) {
            throw new IllegalStateException("Only valid for expanded invites");
        }
        return this.temporary;
    }

    public int hashCode() {
        return this.code.hashCode();
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof InviteImpl)) {
            return false;
        }
        InviteImpl impl = (InviteImpl)obj;
        return impl.code.equals(this.code);
    }

    public String toString() {
        return "Invite(" + this.code + ")";
    }

    public static class GroupImpl
    implements Invite.Group {
        private final String iconId;
        private final String name;
        private final long id;
        private final List<String> users;

        public GroupImpl(String iconId, String name, long id2, List<String> users) {
            this.iconId = iconId;
            this.name = name;
            this.id = id2;
            this.users = users;
        }

        @Override
        public String getIconId() {
            return this.iconId;
        }

        @Override
        public String getIconUrl() {
            return this.iconId == null ? null : "https://cdn.discordapp.com/channel-icons/" + this.id + "/" + this.iconId + ".png";
        }

        @Override
        public String getName() {
            return this.name;
        }

        @Override
        public long getIdLong() {
            return this.id;
        }

        @Override
        public List<String> getUsers() {
            return this.users;
        }
    }

    public static class GuildImpl
    implements Invite.Guild {
        private final String iconId;
        private final String name;
        private final String splashId;
        private final int presenceCount;
        private final int memberCount;
        private final long id;
        private final Guild.VerificationLevel verificationLevel;
        private final Set<String> features;

        public GuildImpl(long id2, String iconId, String name, String splashId, Guild.VerificationLevel verificationLevel, int presenceCount, int memberCount, Set<String> features) {
            this.id = id2;
            this.iconId = iconId;
            this.name = name;
            this.splashId = splashId;
            this.verificationLevel = verificationLevel;
            this.presenceCount = presenceCount;
            this.memberCount = memberCount;
            this.features = features;
        }

        public GuildImpl(Guild guild) {
            this(guild.getIdLong(), guild.getIconId(), guild.getName(), guild.getSplashId(), guild.getVerificationLevel(), -1, -1, guild.getFeatures());
        }

        @Override
        public String getIconId() {
            return this.iconId;
        }

        @Override
        public String getIconUrl() {
            return this.iconId == null ? null : "https://cdn.discordapp.com/icons/" + this.id + "/" + this.iconId + ".png";
        }

        @Override
        public long getIdLong() {
            return this.id;
        }

        @Override
        @Nonnull
        public String getName() {
            return this.name;
        }

        @Override
        public String getSplashId() {
            return this.splashId;
        }

        @Override
        public String getSplashUrl() {
            return this.splashId == null ? null : "https://cdn.discordapp.com/splashes/" + this.id + "/" + this.splashId + ".png";
        }

        @Override
        @Nonnull
        public Guild.VerificationLevel getVerificationLevel() {
            return this.verificationLevel;
        }

        @Override
        public int getOnlineCount() {
            return this.presenceCount;
        }

        @Override
        public int getMemberCount() {
            return this.memberCount;
        }

        @Override
        @Nonnull
        public Set<String> getFeatures() {
            return this.features;
        }
    }

    public static class ChannelImpl
    implements Invite.Channel {
        private final long id;
        private final String name;
        private final ChannelType type;

        public ChannelImpl(long id2, String name, ChannelType type) {
            this.id = id2;
            this.name = name;
            this.type = type;
        }

        public ChannelImpl(GuildChannel channel) {
            this(channel.getIdLong(), channel.getName(), channel.getType());
        }

        @Override
        public long getIdLong() {
            return this.id;
        }

        @Override
        @Nonnull
        public String getName() {
            return this.name;
        }

        @Override
        @Nonnull
        public ChannelType getType() {
            return this.type;
        }
    }
}

