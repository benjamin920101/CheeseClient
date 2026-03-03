/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.events.guild.update;

import javax.annotation.Nonnull;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.guild.update.GenericGuildUpdateEvent;

public class GuildUpdateMFALevelEvent
extends GenericGuildUpdateEvent<Guild.MFALevel> {
    public static final String IDENTIFIER = "mfa_level";

    public GuildUpdateMFALevelEvent(@Nonnull JDA api2, long responseNumber, @Nonnull Guild guild, @Nonnull Guild.MFALevel oldMFALevel) {
        super(api2, responseNumber, guild, oldMFALevel, guild.getRequiredMFALevel(), IDENTIFIER);
    }

    @Nonnull
    public Guild.MFALevel getOldMFALevel() {
        return this.getOldValue();
    }

    @Nonnull
    public Guild.MFALevel getNewMFALevel() {
        return this.getNewValue();
    }

    @Override
    @Nonnull
    public Guild.MFALevel getOldValue() {
        return (Guild.MFALevel)((Object)super.getOldValue());
    }

    @Override
    @Nonnull
    public Guild.MFALevel getNewValue() {
        return (Guild.MFALevel)((Object)super.getNewValue());
    }
}

