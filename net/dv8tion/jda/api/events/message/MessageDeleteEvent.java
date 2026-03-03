/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.events.message;

import javax.annotation.Nonnull;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.GenericMessageEvent;

public class MessageDeleteEvent
extends GenericMessageEvent {
    public MessageDeleteEvent(@Nonnull JDA api2, long responseNumber, long messageId, @Nonnull MessageChannel channel) {
        super(api2, responseNumber, messageId, channel);
    }
}

