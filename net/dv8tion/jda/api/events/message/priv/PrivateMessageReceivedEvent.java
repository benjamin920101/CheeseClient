/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.events.message.priv;

import javax.annotation.Nonnull;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.priv.GenericPrivateMessageEvent;

public class PrivateMessageReceivedEvent
extends GenericPrivateMessageEvent {
    private final Message message;

    public PrivateMessageReceivedEvent(@Nonnull JDA api2, long responseNumber, @Nonnull Message message) {
        super(api2, responseNumber, message.getIdLong(), message.getPrivateChannel());
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
}

