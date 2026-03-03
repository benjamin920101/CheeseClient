/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.events.message.guild.react;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.MessageReaction;
import net.dv8tion.jda.api.events.message.guild.react.GenericGuildMessageReactionEvent;

public class GuildMessageReactionRemoveEvent
extends GenericGuildMessageReactionEvent {
    public GuildMessageReactionRemoveEvent(@Nonnull JDA api2, long responseNumber, @Nullable Member member, @Nonnull MessageReaction reaction, long userId) {
        super(api2, responseNumber, member, reaction, userId);
    }
}

