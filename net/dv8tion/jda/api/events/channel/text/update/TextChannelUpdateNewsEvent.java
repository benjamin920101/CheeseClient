/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.events.channel.text.update;

import javax.annotation.Nonnull;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.channel.text.update.GenericTextChannelUpdateEvent;

public class TextChannelUpdateNewsEvent
extends GenericTextChannelUpdateEvent<Boolean> {
    public static final String IDENTIFIER = "news";

    public TextChannelUpdateNewsEvent(@Nonnull JDA api2, long responseNumber, @Nonnull TextChannel channel) {
        super(api2, responseNumber, channel, !channel.isNews(), channel.isNews(), IDENTIFIER);
    }

    @Override
    @Nonnull
    public Boolean getOldValue() {
        return (Boolean)super.getOldValue();
    }

    @Override
    @Nonnull
    public Boolean getNewValue() {
        return (Boolean)super.getNewValue();
    }
}

