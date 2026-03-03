/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.events.guild;

import javax.annotation.Nonnull;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.events.Event;

public class GuildTimeoutEvent
extends Event {
    private final long guildId;

    public GuildTimeoutEvent(@Nonnull JDA api2, long guildId) {
        super(api2);
        this.guildId = guildId;
    }

    public long getGuildIdLong() {
        return this.guildId;
    }

    @Nonnull
    public String getGuildId() {
        return Long.toUnsignedString(this.guildId);
    }
}

