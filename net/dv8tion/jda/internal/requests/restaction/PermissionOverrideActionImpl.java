/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.internal.requests.restaction;

import java.util.EnumSet;
import java.util.concurrent.TimeUnit;
import java.util.function.BooleanSupplier;
import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnull;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.GuildChannel;
import net.dv8tion.jda.api.entities.IPermissionHolder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.PermissionOverride;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.exceptions.InsufficientPermissionException;
import net.dv8tion.jda.api.exceptions.MissingAccessException;
import net.dv8tion.jda.api.requests.Request;
import net.dv8tion.jda.api.requests.Response;
import net.dv8tion.jda.api.requests.restaction.PermissionOverrideAction;
import net.dv8tion.jda.api.utils.data.DataObject;
import net.dv8tion.jda.internal.entities.AbstractChannelImpl;
import net.dv8tion.jda.internal.entities.PermissionOverrideImpl;
import net.dv8tion.jda.internal.requests.Route;
import net.dv8tion.jda.internal.requests.restaction.AuditableRestActionImpl;
import net.dv8tion.jda.internal.utils.PermissionUtil;
import okhttp3.RequestBody;

public class PermissionOverrideActionImpl
extends AuditableRestActionImpl<PermissionOverride>
implements PermissionOverrideAction {
    private boolean isOverride = true;
    private boolean allowSet = false;
    private boolean denySet = false;
    private long allow = 0L;
    private long deny = 0L;
    private final AbstractChannelImpl<?, ?> channel;
    private final IPermissionHolder permissionHolder;
    private final boolean isRole;
    private final long id;

    public PermissionOverrideActionImpl(PermissionOverride override) {
        super(override.getJDA(), Route.Channels.MODIFY_PERM_OVERRIDE.compile(override.getChannel().getId(), override.getId()));
        this.channel = (AbstractChannelImpl)override.getChannel();
        this.permissionHolder = override.getPermissionHolder();
        this.isRole = override.isRoleOverride();
        this.id = override.getIdLong();
    }

    public PermissionOverrideActionImpl(JDA api2, GuildChannel channel, IPermissionHolder permissionHolder) {
        super(api2, Route.Channels.CREATE_PERM_OVERRIDE.compile(channel.getId(), permissionHolder.getId()));
        this.channel = (AbstractChannelImpl)channel;
        this.permissionHolder = permissionHolder;
        this.isRole = permissionHolder instanceof Role;
        this.id = permissionHolder.getIdLong();
    }

    public PermissionOverrideActionImpl setOverride(boolean override) {
        this.isOverride = override;
        return this;
    }

    @Override
    protected BooleanSupplier finalizeChecks() {
        return () -> {
            Member selfMember = this.getGuild().getSelfMember();
            if (!selfMember.hasPermission(this.channel, Permission.VIEW_CHANNEL)) {
                throw new MissingAccessException(this.channel, Permission.VIEW_CHANNEL);
            }
            if (!selfMember.hasAccess(this.channel)) {
                throw new MissingAccessException(this.channel, Permission.VOICE_CONNECT);
            }
            if (!selfMember.hasPermission(this.channel, Permission.MANAGE_PERMISSIONS)) {
                throw new InsufficientPermissionException(this.channel, Permission.MANAGE_PERMISSIONS);
            }
            return true;
        };
    }

    @Override
    @Nonnull
    public PermissionOverrideActionImpl setCheck(BooleanSupplier checks) {
        return (PermissionOverrideActionImpl)super.setCheck(checks);
    }

    @Override
    @Nonnull
    public PermissionOverrideActionImpl timeout(long timeout, @Nonnull TimeUnit unit) {
        return (PermissionOverrideActionImpl)super.timeout(timeout, unit);
    }

    @Override
    @Nonnull
    public PermissionOverrideActionImpl deadline(long timestamp) {
        return (PermissionOverrideActionImpl)super.deadline(timestamp);
    }

    @Override
    @Nonnull
    public PermissionOverrideAction resetAllow() {
        this.allow = this.getOriginalAllow();
        this.allowSet = false;
        return this;
    }

    @Override
    @Nonnull
    public PermissionOverrideAction resetDeny() {
        this.deny = this.getOriginalDeny();
        this.denySet = false;
        return this;
    }

    @Override
    @Nonnull
    public GuildChannel getChannel() {
        return this.channel;
    }

    @Override
    public Role getRole() {
        return this.isRole() ? (Role)this.permissionHolder : null;
    }

    @Override
    public Member getMember() {
        return this.isMember() ? (Member)this.permissionHolder : null;
    }

    @Override
    public long getAllow() {
        return this.allow;
    }

    @Override
    public long getDeny() {
        return this.deny;
    }

    @Override
    public long getInherited() {
        return (this.allow ^ 0xFFFFFFFFFFFFFFFFL) & (this.deny ^ 0xFFFFFFFFFFFFFFFFL);
    }

    @Override
    public boolean isMember() {
        return !this.isRole;
    }

    @Override
    public boolean isRole() {
        return this.isRole;
    }

    @Override
    @Nonnull
    @CheckReturnValue
    public PermissionOverrideActionImpl setAllow(long allowBits) {
        this.checkPermissions(this.getOriginalAllow() ^ allowBits);
        this.allow = allowBits;
        this.deny &= allowBits ^ 0xFFFFFFFFFFFFFFFFL;
        this.denySet = true;
        this.allowSet = true;
        return this;
    }

    @Override
    @Nonnull
    public PermissionOverrideAction grant(long allowBits) {
        return this.setAllow(this.getCurrentAllow() | allowBits);
    }

    @Override
    @Nonnull
    @CheckReturnValue
    public PermissionOverrideActionImpl setDeny(long denyBits) {
        this.checkPermissions(this.getOriginalDeny() ^ denyBits);
        this.deny = denyBits;
        this.allow &= denyBits ^ 0xFFFFFFFFFFFFFFFFL;
        this.denySet = true;
        this.allowSet = true;
        return this;
    }

    @Override
    @Nonnull
    public PermissionOverrideAction deny(long denyBits) {
        return this.setDeny(this.getCurrentDeny() | denyBits);
    }

    @Override
    @Nonnull
    public PermissionOverrideAction clear(long inheritedBits) {
        return this.setAllow(this.getCurrentAllow() & (inheritedBits ^ 0xFFFFFFFFFFFFFFFFL)).setDeny(this.getCurrentDeny() & (inheritedBits ^ 0xFFFFFFFFFFFFFFFFL));
    }

    protected void checkPermissions(long changed) {
        long botPerms;
        EnumSet<Permission> missing;
        long channelPermissions;
        Member selfMember = this.getGuild().getSelfMember();
        if (changed != 0L && !selfMember.hasPermission(Permission.ADMINISTRATOR) && ((channelPermissions = PermissionUtil.getExplicitPermission(this.channel, selfMember, false)) & Permission.MANAGE_PERMISSIONS.getRawValue()) == 0L && !(missing = Permission.getPermissions(changed & ((botPerms = PermissionUtil.getEffectivePermission(this.channel, selfMember)) ^ 0xFFFFFFFFFFFFFFFFL))).isEmpty()) {
            throw new InsufficientPermissionException(this.channel, Permission.MANAGE_PERMISSIONS, "You must have Permission.MANAGE_PERMISSIONS on the channel explicitly in order to set permissions you don't already have!");
        }
    }

    @Override
    @Nonnull
    @CheckReturnValue
    public PermissionOverrideActionImpl setPermissions(long allowBits, long denyBits) {
        return this.setAllow(allowBits).setDeny(denyBits);
    }

    private long getCurrentAllow() {
        if (this.allowSet) {
            return this.allow;
        }
        return this.isOverride ? 0L : this.getOriginalAllow();
    }

    private long getCurrentDeny() {
        if (this.denySet) {
            return this.deny;
        }
        return this.isOverride ? 0L : this.getOriginalDeny();
    }

    private long getOriginalDeny() {
        PermissionOverride override = this.channel.getOverrideMap().get(this.id);
        return override == null ? 0L : override.getDeniedRaw();
    }

    private long getOriginalAllow() {
        PermissionOverride override = this.channel.getOverrideMap().get(this.id);
        return override == null ? 0L : override.getAllowedRaw();
    }

    @Override
    protected RequestBody finalizeData() {
        DataObject object = DataObject.empty();
        object.put("type", this.isRole() ? 0 : 1);
        object.put("allow", this.getCurrentAllow());
        object.put("deny", this.getCurrentDeny());
        this.reset();
        return this.getRequestBody(object);
    }

    @Override
    protected void handleSuccess(Response response, Request<PermissionOverride> request) {
        DataObject object = (DataObject)request.getRawBody();
        PermissionOverrideImpl override = new PermissionOverrideImpl(this.channel, this.id, this.isRole());
        override.setAllow(object.getLong("allow"));
        override.setDeny(object.getLong("deny"));
        request.onSuccess(override);
    }
}

