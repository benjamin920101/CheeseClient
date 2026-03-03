/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.events.channel.text;

import javax.annotation.Nonnull;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.channel.text.GenericTextChannelEvent;

public class TextChannelDeleteEvent
extends GenericTextChannelEvent {
    public TextChannelDeleteEvent(@Nonnull JDA api2, long responseNumber, @Nonnull TextChannel channel) {
        super(api2, responseNumber, channel);
    }
}

