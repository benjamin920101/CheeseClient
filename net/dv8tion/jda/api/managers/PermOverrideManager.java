/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.managers;

import java.util.Collection;
import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnull;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.GuildChannel;
import net.dv8tion.jda.api.entities.PermissionOverride;
import net.dv8tion.jda.api.managers.Manager;
import net.dv8tion.jda.internal.utils.Checks;

public interface PermOverrideManager
extends Manager<PermOverrideManager> {
    public static final long DENIED = 1L;
    public static final long ALLOWED = 2L;
    public static final long PERMISSIONS = 3L;

    @Override
    @Nonnull
    public PermOverrideManager reset(long var1);

    @Override
    @Nonnull
    public PermOverrideManager reset(long ... var1);

    @Nonnull
    default public Guild getGuild() {
        return this.getPermissionOverride().getGuild();
    }

    @Nonnull
    default public GuildChannel getChannel() {
        return this.getPermissionOverride().getChannel();
    }

    @Nonnull
    public PermissionOverride getPermissionOverride();

    @Nonnull
    @CheckReturnValue
    public PermOverrideManager grant(long var1);

    @Nonnull
    @CheckReturnValue
    default public PermOverrideManager grant(Permission ... permissions) {
        Checks.notNull(permissions, "Permissions");
        return this.grant(Permission.getRaw(permissions));
    }

    @Nonnull
    @CheckReturnValue
    default public PermOverrideManager grant(@Nonnull Collection<Permission> permissions) {
        return this.grant(Permission.getRaw(permissions));
    }

    @Nonnull
    @CheckReturnValue
    public PermOverrideManager deny(long var1);

    @Nonnull
    @CheckReturnValue
    default public PermOverrideManager deny(Permission ... permissions) {
        Checks.notNull(permissions, "Permissions");
        return this.deny(Permission.getRaw(permissions));
    }

    @Nonnull
    @CheckReturnValue
    default public PermOverrideManager deny(@Nonnull Collection<Permission> permissions) {
        return this.deny(Permission.getRaw(permissions));
    }

    @Nonnull
    @CheckReturnValue
    public PermOverrideManager clear(long var1);

    @Nonnull
    @CheckReturnValue
    default public PermOverrideManager clear(Permission ... permissions) {
        Checks.notNull(permissions, "Permissions");
        return this.clear(Permission.getRaw(permissions));
    }

    @Nonnull
    @CheckReturnValue
    default public PermOverrideManager clear(@Nonnull Collection<Permission> permissions) {
        return this.clear(Permission.getRaw(permissions));
    }
}

