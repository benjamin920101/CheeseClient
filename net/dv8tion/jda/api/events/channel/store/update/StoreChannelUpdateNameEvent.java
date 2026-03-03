/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.events.channel.store.update;

import javax.annotation.Nonnull;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.StoreChannel;
import net.dv8tion.jda.api.events.channel.store.update.GenericStoreChannelUpdateEvent;

public class StoreChannelUpdateNameEvent
extends GenericStoreChannelUpdateEvent<String> {
    public static final String IDENTIFIER = "name";

    public StoreChannelUpdateNameEvent(@Nonnull JDA api2, long responseNumber, @Nonnull StoreChannel channel, @Nonnull String prev) {
        super(api2, responseNumber, channel, prev, channel.getName(), IDENTIFIER);
    }

    @Nonnull
    public String getOldName() {
        return this.getOldValue();
    }

    @Nonnull
    public String getNewName() {
        return this.getNewValue();
    }

    @Override
    @Nonnull
    public String getOldValue() {
        return (String)super.getOldValue();
    }

    @Override
    @Nonnull
    public String getNewValue() {
        return (String)super.getNewValue();
    }
}

