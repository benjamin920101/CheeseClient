/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.events.message.guild;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.guild.GenericGuildMessageEvent;

public class GuildMessageReceivedEvent
extends GenericGuildMessageEvent {
    private final Message message;

    public GuildMessageReceivedEvent(@Nonnull JDA api2, long responseNumber, @Nonnull Message message) {
        super(api2, responseNumber, message.getIdLong(), message.getTextChannel());
        this.message = message;
    }

    @Nonnull
    public Message getMessage() {
        return this.message;
    }

    @Nonnull
    public User getAuthor() {
        return this.message.getAuthor();
    }

    @Nullable
    public Member getMember() {
        return this.message.getMember();
    }

    public boolean isWebhookMessage() {
        return this.getMessage().isWebhookMessage();
    }
}

