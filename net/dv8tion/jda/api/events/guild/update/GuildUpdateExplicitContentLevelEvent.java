/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.events.guild.update;

import javax.annotation.Nonnull;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.guild.update.GenericGuildUpdateEvent;

public class GuildUpdateExplicitContentLevelEvent
extends GenericGuildUpdateEvent<Guild.ExplicitContentLevel> {
    public static final String IDENTIFIER = "explicit_content_filter";

    public GuildUpdateExplicitContentLevelEvent(@Nonnull JDA api2, long responseNumber, @Nonnull Guild guild, @Nonnull Guild.ExplicitContentLevel oldLevel) {
        super(api2, responseNumber, guild, oldLevel, guild.getExplicitContentLevel(), IDENTIFIER);
    }

    @Nonnull
    public Guild.ExplicitContentLevel getOldLevel() {
        return this.getOldValue();
    }

    @Nonnull
    public Guild.ExplicitContentLevel getNewLevel() {
        return this.getNewValue();
    }

    @Override
    @Nonnull
    public Guild.ExplicitContentLevel getOldValue() {
        return (Guild.ExplicitContentLevel)((Object)super.getOldValue());
    }

    @Override
    @Nonnull
    public Guild.ExplicitContentLevel getNewValue() {
        return (Guild.ExplicitContentLevel)((Object)super.getNewValue());
    }
}

