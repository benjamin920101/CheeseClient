/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.events.guild;

import javax.annotation.Nonnull;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.guild.GenericGuildEvent;

public class GuildLeaveEvent
extends GenericGuildEvent {
    public GuildLeaveEvent(@Nonnull JDA api2, long responseNumber, @Nonnull Guild guild) {
        super(api2, responseNumber, guild);
    }
}

