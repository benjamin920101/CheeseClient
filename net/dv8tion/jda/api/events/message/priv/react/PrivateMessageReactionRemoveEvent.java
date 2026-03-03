/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.events.message.priv.react;

import javax.annotation.Nonnull;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.MessageReaction;
import net.dv8tion.jda.api.events.message.priv.react.GenericPrivateMessageReactionEvent;

public class PrivateMessageReactionRemoveEvent
extends GenericPrivateMessageReactionEvent {
    public PrivateMessageReactionRemoveEvent(@Nonnull JDA api2, long responseNumber, @Nonnull MessageReaction reaction, long userId) {
        super(api2, responseNumber, reaction, userId);
    }
}

