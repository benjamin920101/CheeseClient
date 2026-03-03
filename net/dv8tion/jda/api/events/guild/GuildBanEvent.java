/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.events.guild;

import javax.annotation.Nonnull;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.guild.GenericGuildEvent;

public class GuildBanEvent
extends GenericGuildEvent {
    private final User user;

    public GuildBanEvent(@Nonnull JDA api2, long responseNumber, @Nonnull Guild guild, @Nonnull User user) {
        super(api2, responseNumber, guild);
        this.user = user;
    }

    @Nonnull
    public User getUser() {
        return this.user;
    }
}

