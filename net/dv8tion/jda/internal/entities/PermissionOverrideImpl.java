/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.internal.entities;

import java.util.EnumSet;
import java.util.Objects;
import javax.annotation.Nonnull;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.GuildChannel;
import net.dv8tion.jda.api.entities.IPermissionHolder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.PermissionOverride;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.exceptions.InsufficientPermissionException;
import net.dv8tion.jda.api.exceptions.MissingAccessException;
import net.dv8tion.jda.api.requests.restaction.AuditableRestAction;
import net.dv8tion.jda.api.requests.restaction.PermissionOverrideAction;
import net.dv8tion.jda.internal.JDAImpl;
import net.dv8tion.jda.internal.requests.Route;
import net.dv8tion.jda.internal.requests.restaction.AuditableRestActionImpl;
import net.dv8tion.jda.internal.requests.restaction.PermissionOverrideActionImpl;

public class PermissionOverrideImpl
implements PermissionOverride {
    private final long id;
    private final boolean isRole;
    private final JDAImpl api;
    private GuildChannel channel;
    protected PermissionOverrideAction manager;
    private long allow;
    private long deny;

    public PermissionOverrideImpl(GuildChannel channel, long id2, boolean isRole) {
        this.isRole = isRole;
        this.api = (JDAImpl)channel.getJDA();
        this.channel = channel;
        this.id = id2;
    }

    @Override
    public long getAllowedRaw() {
        return this.allow;
    }

    @Override
    public long getInheritRaw() {
        return (this.allow | this.deny) ^ 0xFFFFFFFFFFFFFFFFL;
    }

    @Override
    public long getDeniedRaw() {
        return this.deny;
    }

    @Override
    @Nonnull
    public EnumSet<Permission> getAllowed() {
        return Permission.getPermissions(this.allow);
    }

    @Override
    @Nonnull
    public EnumSet<Permission> getInherit() {
        return Permission.getPermissions(this.getInheritRaw());
    }

    @Override
    @Nonnull
    public EnumSet<Permission> getDenied() {
        return Permission.getPermissions(this.deny);
    }

    @Override
    @Nonnull
    public JDA getJDA() {
        return this.api;
    }

    @Override
    public IPermissionHolder getPermissionHolder() {
        return this.isRole ? this.getRole() : this.getMember();
    }

    @Override
    public Member getMember() {
        return this.getGuild().getMemberById(this.id);
    }

    @Override
    public Role getRole() {
        return this.getGuild().getRoleById(this.id);
    }

    @Override
    @Nonnull
    public GuildChannel getChannel() {
        GuildChannel realChannel = this.api.getGuildChannelById(this.channel.getType(), this.channel.getIdLong());
        if (realChannel != null) {
            this.channel = realChannel;
        }
        return this.channel;
    }

    @Override
    @Nonnull
    public Guild getGuild() {
        return this.getChannel().getGuild();
    }

    @Override
    public boolean isMemberOverride() {
        return !this.isRole;
    }

    @Override
    public boolean isRoleOverride() {
        return this.isRole;
    }

    @Override
    @Nonnull
    public PermissionOverrideAction getManager() {
        Member selfMember = this.getGuild().getSelfMember();
        GuildChannel channel = this.getChannel();
        if (!selfMember.hasPermission(channel, Permission.VIEW_CHANNEL)) {
            throw new MissingAccessException(channel, Permission.VIEW_CHANNEL);
        }
        if (!selfMember.hasAccess(channel)) {
            throw new MissingAccessException(channel, Permission.VOICE_CONNECT);
        }
        if (!selfMember.hasPermission(channel, Permission.MANAGE_PERMISSIONS)) {
            throw new InsufficientPermissionException(channel, Permission.MANAGE_PERMISSIONS);
        }
        if (this.manager == null) {
            this.manager = new PermissionOverrideActionImpl(this).setOverride(false);
            return this.manager;
        }
        return this.manager;
    }

    @Override
    @Nonnull
    public AuditableRestAction<Void> delete() {
        Member selfMember = this.getGuild().getSelfMember();
        GuildChannel channel = this.getChannel();
        if (!selfMember.hasPermission(channel, Permission.VIEW_CHANNEL)) {
            throw new MissingAccessException(channel, Permission.VIEW_CHANNEL);
        }
        if (!selfMember.hasAccess(channel)) {
            throw new MissingAccessException(channel, Permission.VOICE_CONNECT);
        }
        if (!selfMember.hasPermission(channel, Permission.MANAGE_PERMISSIONS)) {
            throw new InsufficientPermissionException(channel, Permission.MANAGE_PERMISSIONS);
        }
        Route.CompiledRoute route = Route.Channels.DELETE_PERM_OVERRIDE.compile(this.channel.getId(), this.getId());
        return new AuditableRestActionImpl<Void>(this.getJDA(), route);
    }

    @Override
    public long getIdLong() {
        return this.id;
    }

    public PermissionOverrideImpl setAllow(long allow) {
        this.allow = allow;
        return this;
    }

    public PermissionOverrideImpl setDeny(long deny) {
        this.deny = deny;
        return this;
    }

    public boolean equals(Object o2) {
        if (o2 == this) {
            return true;
        }
        if (!(o2 instanceof PermissionOverrideImpl)) {
            return false;
        }
        PermissionOverrideImpl oPerm = (PermissionOverrideImpl)o2;
        return this.id == oPerm.id && this.channel.getIdLong() == oPerm.channel.getIdLong();
    }

    public int hashCode() {
        return Objects.hash(this.id, this.channel.getIdLong());
    }

    public String toString() {
        return "PermOver:(" + (this.isMemberOverride() ? "M" : "R") + ")(" + this.channel.getId() + " | " + this.getId() + ")";
    }
}

