/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.requests.restaction;

import java.util.Collection;
import java.util.EnumSet;
import java.util.concurrent.TimeUnit;
import java.util.function.BooleanSupplier;
import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.GuildChannel;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.PermissionOverride;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.requests.restaction.AuditableRestAction;
import net.dv8tion.jda.internal.utils.Checks;

public interface PermissionOverrideAction
extends AuditableRestAction<PermissionOverride> {
    @Override
    @Nonnull
    public PermissionOverrideAction setCheck(@Nullable BooleanSupplier var1);

    @Override
    @Nonnull
    public PermissionOverrideAction timeout(long var1, @Nonnull TimeUnit var3);

    @Override
    @Nonnull
    public PermissionOverrideAction deadline(long var1);

    @Nonnull
    default public PermissionOverrideAction reset() {
        return this.resetAllow().resetDeny();
    }

    @Nonnull
    public PermissionOverrideAction resetAllow();

    @Nonnull
    public PermissionOverrideAction resetDeny();

    @Nonnull
    public GuildChannel getChannel();

    @Nullable
    public Role getRole();

    @Nullable
    public Member getMember();

    @Nonnull
    default public Guild getGuild() {
        return this.getChannel().getGuild();
    }

    public long getAllow();

    @Nonnull
    default public EnumSet<Permission> getAllowedPermissions() {
        return Permission.getPermissions(this.getAllow());
    }

    public long getDeny();

    @Nonnull
    default public EnumSet<Permission> getDeniedPermissions() {
        return Permission.getPermissions(this.getDeny());
    }

    public long getInherited();

    @Nonnull
    default public EnumSet<Permission> getInheritedPermissions() {
        return Permission.getPermissions(this.getInherited());
    }

    public boolean isMember();

    public boolean isRole();

    @Nonnull
    @CheckReturnValue
    public PermissionOverrideAction setAllow(long var1);

    @Nonnull
    @CheckReturnValue
    default public PermissionOverrideAction setAllow(@Nullable Collection<Permission> permissions) {
        if (permissions == null || permissions.isEmpty()) {
            return this.setAllow(0L);
        }
        Checks.noneNull(permissions, "Permissions");
        return this.setAllow(Permission.getRaw(permissions));
    }

    @Nonnull
    @CheckReturnValue
    default public PermissionOverrideAction setAllow(Permission ... permissions) {
        if (permissions == null || permissions.length == 0) {
            return this.setAllow(0L);
        }
        Checks.noneNull((Object[])permissions, "Permissions");
        return this.setAllow(Permission.getRaw(permissions));
    }

    @Nonnull
    @CheckReturnValue
    public PermissionOverrideAction grant(long var1);

    @Nonnull
    @CheckReturnValue
    default public PermissionOverrideAction grant(@Nonnull Collection<Permission> permissions) {
        return this.grant(Permission.getRaw(permissions));
    }

    @Nonnull
    @CheckReturnValue
    default public PermissionOverrideAction grant(Permission ... permissions) {
        return this.grant(Permission.getRaw(permissions));
    }

    @Nonnull
    @CheckReturnValue
    public PermissionOverrideAction setDeny(long var1);

    @Nonnull
    @CheckReturnValue
    default public PermissionOverrideAction setDeny(@Nullable Collection<Permission> permissions) {
        if (permissions == null || permissions.isEmpty()) {
            return this.setDeny(0L);
        }
        Checks.noneNull(permissions, "Permissions");
        return this.setDeny(Permission.getRaw(permissions));
    }

    @Nonnull
    @CheckReturnValue
    default public PermissionOverrideAction setDeny(Permission ... permissions) {
        if (permissions == null || permissions.length == 0) {
            return this.setDeny(0L);
        }
        Checks.noneNull((Object[])permissions, "Permissions");
        return this.setDeny(Permission.getRaw(permissions));
    }

    @Nonnull
    @CheckReturnValue
    public PermissionOverrideAction deny(long var1);

    @Nonnull
    @CheckReturnValue
    default public PermissionOverrideAction deny(@Nonnull Collection<Permission> permissions) {
        return this.deny(Permission.getRaw(permissions));
    }

    @Nonnull
    @CheckReturnValue
    default public PermissionOverrideAction deny(Permission ... permissions) {
        return this.deny(Permission.getRaw(permissions));
    }

    @Nonnull
    @CheckReturnValue
    public PermissionOverrideAction clear(long var1);

    @Nonnull
    @CheckReturnValue
    default public PermissionOverrideAction clear(@Nonnull Collection<Permission> permissions) {
        return this.clear(Permission.getRaw(permissions));
    }

    @Nonnull
    @CheckReturnValue
    default public PermissionOverrideAction clear(Permission ... permissions) {
        return this.clear(Permission.getRaw(permissions));
    }

    @Nonnull
    @CheckReturnValue
    public PermissionOverrideAction setPermissions(long var1, long var3);

    @Nonnull
    @CheckReturnValue
    default public PermissionOverrideAction setPermissions(@Nullable Collection<Permission> grantPermissions, @Nullable Collection<Permission> denyPermissions) {
        return this.setAllow(grantPermissions).setDeny(denyPermissions);
    }
}

