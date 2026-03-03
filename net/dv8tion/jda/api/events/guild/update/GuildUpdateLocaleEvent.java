/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.events.guild.update;

import java.util.Locale;
import javax.annotation.Nonnull;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.guild.update.GenericGuildUpdateEvent;

public class GuildUpdateLocaleEvent
extends GenericGuildUpdateEvent<Locale> {
    public static final String IDENTIFIER = "locale";

    public GuildUpdateLocaleEvent(@Nonnull JDA api2, long responseNumber, @Nonnull Guild guild, @Nonnull Locale previous) {
        super(api2, responseNumber, guild, previous, guild.getLocale(), IDENTIFIER);
    }

    @Override
    @Nonnull
    public Locale getOldValue() {
        return (Locale)super.getOldValue();
    }

    @Override
    @Nonnull
    public Locale getNewValue() {
        return (Locale)super.getNewValue();
    }
}

