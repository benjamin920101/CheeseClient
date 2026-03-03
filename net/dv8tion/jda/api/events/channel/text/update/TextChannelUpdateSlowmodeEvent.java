/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.events.channel.text.update;

import javax.annotation.Nonnull;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.channel.text.update.GenericTextChannelUpdateEvent;

public class TextChannelUpdateSlowmodeEvent
extends GenericTextChannelUpdateEvent<Integer> {
    public static final String IDENTIFIER = "slowmode";

    public TextChannelUpdateSlowmodeEvent(@Nonnull JDA api2, long responseNumber, @Nonnull TextChannel channel, int oldSlowmode) {
        super(api2, responseNumber, channel, oldSlowmode, channel.getSlowmode(), IDENTIFIER);
    }

    public int getOldSlowmode() {
        return (Integer)this.getOldValue();
    }

    public int getNewSlowmode() {
        return (Integer)this.getNewValue();
    }
}

