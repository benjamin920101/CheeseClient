/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.events.guild.update;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.guild.update.GenericGuildUpdateEvent;

public class GuildUpdateDescriptionEvent
extends GenericGuildUpdateEvent<String> {
    public static final String IDENTIFIER = "description";

    public GuildUpdateDescriptionEvent(@Nonnull JDA api2, long responseNumber, @Nonnull Guild guild, @Nullable String previous) {
        super(api2, responseNumber, guild, previous, guild.getDescription(), IDENTIFIER);
    }

    @Nullable
    public String getOldDescription() {
        return (String)this.getOldValue();
    }

    @Nullable
    public String getNewDescription() {
        return (String)this.getNewValue();
    }
}

