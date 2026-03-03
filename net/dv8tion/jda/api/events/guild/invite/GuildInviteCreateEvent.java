/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.events.guild.invite;

import javax.annotation.Nonnull;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.GuildChannel;
import net.dv8tion.jda.api.entities.Invite;
import net.dv8tion.jda.api.events.guild.invite.GenericGuildInviteEvent;

public class GuildInviteCreateEvent
extends GenericGuildInviteEvent {
    private final Invite invite;

    public GuildInviteCreateEvent(@Nonnull JDA api2, long responseNumber, @Nonnull Invite invite, @Nonnull GuildChannel channel) {
        super(api2, responseNumber, invite.getCode(), channel);
        this.invite = invite;
    }

    @Nonnull
    public Invite getInvite() {
        return this.invite;
    }
}

