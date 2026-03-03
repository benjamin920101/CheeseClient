/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.events.message.guild.react;

import javax.annotation.Nonnull;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.MessageReaction;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.guild.GenericGuildMessageEvent;

public class GuildMessageReactionRemoveEmoteEvent
extends GenericGuildMessageEvent {
    private final MessageReaction reaction;

    public GuildMessageReactionRemoveEmoteEvent(@Nonnull JDA api2, long responseNumber, @Nonnull TextChannel channel, @Nonnull MessageReaction reaction, long messageId) {
        super(api2, responseNumber, messageId, channel);
        this.reaction = reaction;
    }

    @Override
    @Nonnull
    public TextChannel getChannel() {
        return this.channel;
    }

    @Nonnull
    public MessageReaction getReaction() {
        return this.reaction;
    }

    @Nonnull
    public MessageReaction.ReactionEmote getReactionEmote() {
        return this.reaction.getReactionEmote();
    }

    @Override
    public long getMessageIdLong() {
        return this.messageId;
    }

    @Override
    @Nonnull
    public String getMessageId() {
        return Long.toUnsignedString(this.messageId);
    }
}

