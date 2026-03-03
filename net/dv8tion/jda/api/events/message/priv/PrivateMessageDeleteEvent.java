/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.events.message.priv;

import javax.annotation.Nonnull;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.PrivateChannel;
import net.dv8tion.jda.api.events.message.priv.GenericPrivateMessageEvent;

public class PrivateMessageDeleteEvent
extends GenericPrivateMessageEvent {
    public PrivateMessageDeleteEvent(@Nonnull JDA api2, long responseNumber, long messageId, @Nonnull PrivateChannel channel) {
        super(api2, responseNumber, messageId, channel);
    }
}

