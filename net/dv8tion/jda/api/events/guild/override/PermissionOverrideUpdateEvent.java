/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.events.guild.override;

import java.util.EnumSet;
import javax.annotation.Nonnull;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.GuildChannel;
import net.dv8tion.jda.api.entities.PermissionOverride;
import net.dv8tion.jda.api.events.guild.override.GenericPermissionOverrideEvent;

public class PermissionOverrideUpdateEvent
extends GenericPermissionOverrideEvent {
    private final long oldAllow;
    private final long oldDeny;

    public PermissionOverrideUpdateEvent(@Nonnull JDA api2, long responseNumber, @Nonnull GuildChannel channel, @Nonnull PermissionOverride override, long oldAllow, long oldDeny) {
        super(api2, responseNumber, channel, override);
        this.oldAllow = oldAllow;
        this.oldDeny = oldDeny;
    }

    public long getOldAllowRaw() {
        return this.oldAllow;
    }

    public long getOldDenyRaw() {
        return this.oldDeny;
    }

    public long getOldInheritedRaw() {
        return (this.oldAllow | this.oldDeny) ^ 0xFFFFFFFFFFFFFFFFL;
    }

    @Nonnull
    public EnumSet<Permission> getOldAllow() {
        return Permission.getPermissions(this.oldAllow);
    }

    @Nonnull
    public EnumSet<Permission> getOldDeny() {
        return Permission.getPermissions(this.oldDeny);
    }

    @Nonnull
    public EnumSet<Permission> getOldInherited() {
        return Permission.getPermissions(this.getOldInheritedRaw());
    }
}

