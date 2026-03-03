/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.events.channel.store;

import javax.annotation.Nonnull;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.StoreChannel;
import net.dv8tion.jda.api.events.Event;

public abstract class GenericStoreChannelEvent
extends Event {
    protected final StoreChannel channel;

    public GenericStoreChannelEvent(@Nonnull JDA api2, long responseNumber, @Nonnull StoreChannel channel) {
        super(api2, responseNumber);
        this.channel = channel;
    }

    @Nonnull
    public StoreChannel getChannel() {
        return this.channel;
    }
}

