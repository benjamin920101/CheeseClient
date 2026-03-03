/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.events.user;

import java.time.OffsetDateTime;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.PrivateChannel;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.user.GenericUserEvent;

public class UserTypingEvent
extends GenericUserEvent {
    private final Member member;
    private final MessageChannel channel;
    private final OffsetDateTime timestamp;

    public UserTypingEvent(@Nonnull JDA api2, long responseNumber, @Nonnull User user, @Nonnull MessageChannel channel, @Nonnull OffsetDateTime timestamp, @Nullable Member member) {
        super(api2, responseNumber, user);
        this.member = member;
        this.channel = channel;
        this.timestamp = timestamp;
    }

    @Nonnull
    public OffsetDateTime getTimestamp() {
        return this.timestamp;
    }

    @Nonnull
    public MessageChannel getChannel() {
        return this.channel;
    }

    public boolean isFromType(@Nonnull ChannelType type) {
        return this.channel.getType() == type;
    }

    @Nonnull
    public ChannelType getType() {
        return this.channel.getType();
    }

    @Nullable
    public PrivateChannel getPrivateChannel() {
        return this.isFromType(ChannelType.PRIVATE) ? (PrivateChannel)this.channel : null;
    }

    @Nullable
    public TextChannel getTextChannel() {
        return this.isFromType(ChannelType.TEXT) ? (TextChannel)this.channel : null;
    }

    @Nullable
    public Guild getGuild() {
        return this.isFromType(ChannelType.TEXT) ? this.member.getGuild() : null;
    }

    @Nullable
    public Member getMember() {
        return this.member;
    }
}

