/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.events.channel.text.update;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.channel.text.update.GenericTextChannelUpdateEvent;

public class TextChannelUpdateTopicEvent
extends GenericTextChannelUpdateEvent<String> {
    public static final String IDENTIFIER = "topic";

    public TextChannelUpdateTopicEvent(@Nonnull JDA api2, long responseNumber, @Nonnull TextChannel channel, @Nullable String oldTopic) {
        super(api2, responseNumber, channel, oldTopic, channel.getTopic(), IDENTIFIER);
    }

    @Nullable
    public String getOldTopic() {
        return (String)this.getOldValue();
    }

    @Nullable
    public String getNewTopic() {
        return (String)this.getNewValue();
    }
}

