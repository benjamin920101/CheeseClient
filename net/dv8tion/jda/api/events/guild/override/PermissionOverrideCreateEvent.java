/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.events.guild.override;

import javax.annotation.Nonnull;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.GuildChannel;
import net.dv8tion.jda.api.entities.PermissionOverride;
import net.dv8tion.jda.api.events.guild.override.GenericPermissionOverrideEvent;

public class PermissionOverrideCreateEvent
extends GenericPermissionOverrideEvent {
    public PermissionOverrideCreateEvent(@Nonnull JDA api2, long responseNumber, @Nonnull GuildChannel channel, @Nonnull PermissionOverride override) {
        super(api2, responseNumber, channel, override);
    }
}

