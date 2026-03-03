/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.requests.restaction;

import java.awt.Color;
import java.util.Collection;
import java.util.concurrent.TimeUnit;
import java.util.function.BooleanSupplier;
import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.requests.restaction.AuditableRestAction;
import net.dv8tion.jda.internal.utils.Checks;

public interface RoleAction
extends AuditableRestAction<Role> {
    @Override
    @Nonnull
    public RoleAction setCheck(@Nullable BooleanSupplier var1);

    @Override
    @Nonnull
    public RoleAction timeout(long var1, @Nonnull TimeUnit var3);

    @Override
    @Nonnull
    public RoleAction deadline(long var1);

    @Nonnull
    public Guild getGuild();

    @Nonnull
    @CheckReturnValue
    public RoleAction setName(@Nullable String var1);

    @Nonnull
    @CheckReturnValue
    public RoleAction setHoisted(@Nullable Boolean var1);

    @Nonnull
    @CheckReturnValue
    public RoleAction setMentionable(@Nullable Boolean var1);

    @Nonnull
    @CheckReturnValue
    default public RoleAction setColor(@Nullable Color color) {
        return this.setColor(color != null ? Integer.valueOf(color.getRGB()) : null);
    }

    @Nonnull
    @CheckReturnValue
    public RoleAction setColor(@Nullable Integer var1);

    @Nonnull
    @CheckReturnValue
    default public RoleAction setPermissions(Permission ... permissions) {
        if (permissions != null) {
            Checks.noneNull((Object[])permissions, "Permissions");
        }
        return this.setPermissions(permissions == null ? null : Long.valueOf(Permission.getRaw(permissions)));
    }

    @Nonnull
    @CheckReturnValue
    default public RoleAction setPermissions(@Nullable Collection<Permission> permissions) {
        if (permissions != null) {
            Checks.noneNull(permissions, "Permissions");
        }
        return this.setPermissions(permissions == null ? null : Long.valueOf(Permission.getRaw(permissions)));
    }

    @Nonnull
    @CheckReturnValue
    public RoleAction setPermissions(@Nullable Long var1);
}

