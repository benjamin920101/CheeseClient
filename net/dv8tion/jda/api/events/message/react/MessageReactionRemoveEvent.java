/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.events.message.react;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.MessageReaction;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.react.GenericMessageReactionEvent;

public class MessageReactionRemoveEvent
extends GenericMessageReactionEvent {
    public MessageReactionRemoveEvent(@Nonnull JDA api2, long responseNumber, @Nullable User user, @Nullable Member member, @Nonnull MessageReaction reaction, long userId) {
        super(api2, responseNumber, user, member, reaction, userId);
    }
}

