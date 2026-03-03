/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.events.guild;

import javax.annotation.Nonnull;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.events.Event;

public class UnavailableGuildJoinedEvent
extends Event {
    private final long guildId;

    public UnavailableGuildJoinedEvent(@Nonnull JDA api2, long responseNumber, long guildId) {
        super(api2, responseNumber);
        this.guildId = guildId;
    }

    @Nonnull
    public String getGuildId() {
        return Long.toUnsignedString(this.guildId);
    }

    public long getGuildIdLong() {
        return this.guildId;
    }
}

