/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.events.message.react;

import javax.annotation.Nonnull;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.MessageReaction;
import net.dv8tion.jda.api.events.message.GenericMessageEvent;

public class MessageReactionRemoveEmoteEvent
extends GenericMessageEvent {
    private final MessageReaction reaction;

    public MessageReactionRemoveEmoteEvent(@Nonnull JDA api2, long responseNumber, long messageId, @Nonnull MessageChannel channel, @Nonnull MessageReaction reaction) {
        super(api2, responseNumber, messageId, channel);
        this.reaction = reaction;
    }

    @Nonnull
    public MessageReaction getReaction() {
        return this.reaction;
    }

    @Nonnull
    public MessageReaction.ReactionEmote getReactionEmote() {
        return this.reaction.getReactionEmote();
    }
}

