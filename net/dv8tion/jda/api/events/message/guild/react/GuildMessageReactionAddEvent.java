/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.events.message.guild.react;

import javax.annotation.Nonnull;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.MessageReaction;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.guild.react.GenericGuildMessageReactionEvent;

public class GuildMessageReactionAddEvent
extends GenericGuildMessageReactionEvent {
    public GuildMessageReactionAddEvent(@Nonnull JDA api2, long responseNumber, @Nonnull Member member, @Nonnull MessageReaction reaction) {
        super(api2, responseNumber, member, reaction, member.getIdLong());
    }

    @Override
    @Nonnull
    public User getUser() {
        return super.getUser();
    }

    @Override
    @Nonnull
    public Member getMember() {
        return super.getMember();
    }
}

