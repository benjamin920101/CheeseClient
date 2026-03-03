/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.events.channel.store;

import javax.annotation.Nonnull;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.StoreChannel;
import net.dv8tion.jda.api.events.channel.store.GenericStoreChannelEvent;

public class StoreChannelDeleteEvent
extends GenericStoreChannelEvent {
    public StoreChannelDeleteEvent(@Nonnull JDA api2, long responseNumber, @Nonnull StoreChannel channel) {
        super(api2, responseNumber, channel);
    }
}

