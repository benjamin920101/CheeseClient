/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.events.message.priv.react;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.MessageReaction;
import net.dv8tion.jda.api.entities.PrivateChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.priv.GenericPrivateMessageEvent;

public class GenericPrivateMessageReactionEvent
extends GenericPrivateMessageEvent {
    protected final long userId;
    protected final MessageReaction reaction;

    public GenericPrivateMessageReactionEvent(@Nonnull JDA api2, long responseNumber, @Nonnull MessageReaction reaction, long userId) {
        super(api2, responseNumber, reaction.getMessageIdLong(), (PrivateChannel)reaction.getChannel());
        this.userId = userId;
        this.reaction = reaction;
    }

    @Nonnull
    public String getUserId() {
        return Long.toUnsignedString(this.userId);
    }

    public long getUserIdLong() {
        return this.userId;
    }

    @Nullable
    public User getUser() {
        return this.userId == this.getJDA().getSelfUser().getIdLong() ? this.getJDA().getSelfUser() : this.getChannel().getUser();
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

