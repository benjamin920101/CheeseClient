/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.events.channel.store.update;

import javax.annotation.Nonnull;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.StoreChannel;
import net.dv8tion.jda.api.events.channel.store.update.GenericStoreChannelUpdateEvent;

public class StoreChannelUpdatePositionEvent
extends GenericStoreChannelUpdateEvent<Integer> {
    public static final String IDENTIFIER = "position";

    public StoreChannelUpdatePositionEvent(@Nonnull JDA api2, long responseNumber, @Nonnull StoreChannel channel, int prev) {
        super(api2, responseNumber, channel, prev, channel.getPositionRaw(), IDENTIFIER);
    }

    public int getOldPosition() {
        return (Integer)this.getOldValue();
    }

    public int getNewPosition() {
        return (Integer)this.getNewValue();
    }
}

