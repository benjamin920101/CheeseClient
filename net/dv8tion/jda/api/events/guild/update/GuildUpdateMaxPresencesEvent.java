/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.events.guild.update;

import javax.annotation.Nonnull;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.guild.update.GenericGuildUpdateEvent;

public class GuildUpdateMaxPresencesEvent
extends GenericGuildUpdateEvent<Integer> {
    public static final String IDENTIFIER = "max_presences";

    public GuildUpdateMaxPresencesEvent(@Nonnull JDA api2, long responseNumber, @Nonnull Guild guild, int previous) {
        super(api2, responseNumber, guild, previous, guild.getMaxPresences(), IDENTIFIER);
    }

    public int getOldMaxPresences() {
        return this.getOldValue();
    }

    public int getNewMaxPresences() {
        return this.getNewValue();
    }

    @Override
    @Nonnull
    public Integer getOldValue() {
        return (Integer)super.getOldValue();
    }

    @Override
    @Nonnull
    public Integer getNewValue() {
        return (Integer)super.getNewValue();
    }
}

