/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.events.guild.update;

import java.util.Set;
import javax.annotation.Nonnull;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.guild.update.GenericGuildUpdateEvent;

public class GuildUpdateFeaturesEvent
extends GenericGuildUpdateEvent<Set<String>> {
    public static final String IDENTIFIER = "features";

    public GuildUpdateFeaturesEvent(@Nonnull JDA api2, long responseNumber, @Nonnull Guild guild, @Nonnull Set<String> oldFeatures) {
        super(api2, responseNumber, guild, oldFeatures, guild.getFeatures(), IDENTIFIER);
    }

    @Nonnull
    public Set<String> getOldFeatures() {
        return this.getOldValue();
    }

    @Nonnull
    public Set<String> getNewFeatures() {
        return this.getNewValue();
    }

    @Override
    @Nonnull
    public Set<String> getOldValue() {
        return (Set)super.getOldValue();
    }

    @Override
    @Nonnull
    public Set<String> getNewValue() {
        return (Set)super.getNewValue();
    }
}

