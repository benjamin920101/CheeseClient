/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.events.channel.text.update;

import javax.annotation.Nonnull;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.channel.text.update.GenericTextChannelUpdateEvent;

public class TextChannelUpdatePositionEvent
extends GenericTextChannelUpdateEvent<Integer> {
    public static final String IDENTIFIER = "position";

    public TextChannelUpdatePositionEvent(@Nonnull JDA api2, long responseNumber, @Nonnull TextChannel channel, int oldPosition) {
        super(api2, responseNumber, channel, oldPosition, channel.getPositionRaw(), IDENTIFIER);
    }

    public int getOldPosition() {
        return (Integer)this.getOldValue();
    }

    public int getNewPosition() {
        return (Integer)this.getNewValue();
    }
}

