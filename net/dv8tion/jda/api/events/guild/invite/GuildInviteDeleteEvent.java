/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.events.guild.invite;

import javax.annotation.Nonnull;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.GuildChannel;
import net.dv8tion.jda.api.events.guild.invite.GenericGuildInviteEvent;

public class GuildInviteDeleteEvent
extends GenericGuildInviteEvent {
    public GuildInviteDeleteEvent(@Nonnull JDA api2, long responseNumber, @Nonnull String code, @Nonnull GuildChannel channel) {
        super(api2, responseNumber, code, channel);
    }
}

