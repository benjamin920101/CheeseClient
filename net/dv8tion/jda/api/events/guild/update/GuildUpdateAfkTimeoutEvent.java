/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.events.guild.update;

import javax.annotation.Nonnull;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.guild.update.GenericGuildUpdateEvent;

public class GuildUpdateAfkTimeoutEvent
extends GenericGuildUpdateEvent<Guild.Timeout> {
    public static final String IDENTIFIER = "afk_timeout";

    public GuildUpdateAfkTimeoutEvent(@Nonnull JDA api2, long responseNumber, @Nonnull Guild guild, @Nonnull Guild.Timeout oldAfkTimeout) {
        super(api2, responseNumber, guild, oldAfkTimeout, guild.getAfkTimeout(), IDENTIFIER);
    }

    @Nonnull
    public Guild.Timeout getOldAfkTimeout() {
        return this.getOldValue();
    }

    @Nonnull
    public Guild.Timeout getNewAfkTimeout() {
        return this.getNewValue();
    }

    @Override
    @Nonnull
    public Guild.Timeout getOldValue() {
        return (Guild.Timeout)((Object)super.getOldValue());
    }

    @Override
    @Nonnull
    public Guild.Timeout getNewValue() {
        return (Guild.Timeout)((Object)super.getNewValue());
    }
}

