/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.events.message.guild.react;

import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageReaction;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.guild.GenericGuildMessageEvent;
import net.dv8tion.jda.api.requests.RestAction;
import net.dv8tion.jda.internal.requests.CompletedRestAction;

public abstract class GenericGuildMessageReactionEvent
extends GenericGuildMessageEvent {
    protected final long userId;
    protected final Member issuer;
    protected final MessageReaction reaction;

    public GenericGuildMessageReactionEvent(@Nonnull JDA api2, long responseNumber, @Nullable Member user, @Nonnull MessageReaction reaction, long userId) {
        super(api2, responseNumber, reaction.getMessageIdLong(), (TextChannel)reaction.getChannel());
        this.issuer = user;
        this.reaction = reaction;
        this.userId = userId;
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
        return this.issuer == null ? this.getJDA().getUserById(this.userId) : this.issuer.getUser();
    }

    @Nullable
    public Member getMember() {
        return this.issuer;
    }

    @Nonnull
    public MessageReaction getReaction() {
        return this.reaction;
    }

    @Nonnull
    public MessageReaction.ReactionEmote getReactionEmote() {
        return this.reaction.getReactionEmote();
    }

    @Nonnull
    @CheckReturnValue
    public RestAction<User> retrieveUser() {
        if (this.issuer != null) {
            return new CompletedRestAction<User>(this.getJDA(), this.issuer.getUser());
        }
        return this.getJDA().retrieveUserById(this.getUserIdLong());
    }

    @Nonnull
    @CheckReturnValue
    public RestAction<Member> retrieveMember() {
        if (this.issuer != null) {
            return new CompletedRestAction<Member>(this.getJDA(), this.issuer);
        }
        return this.getGuild().retrieveMemberById(this.getUserIdLong());
    }

    @Nonnull
    @CheckReturnValue
    public RestAction<Message> retrieveMessage() {
        return this.getChannel().retrieveMessageById(this.getMessageId());
    }
}

