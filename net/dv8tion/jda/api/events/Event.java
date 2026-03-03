/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.events;

import javax.annotation.Nonnull;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.events.GenericEvent;

public abstract class Event
implements GenericEvent {
    protected final JDA api;
    protected final long responseNumber;

    public Event(@Nonnull JDA api2, long responseNumber) {
        this.api = api2;
        this.responseNumber = responseNumber;
    }

    public Event(@Nonnull JDA api2) {
        this(api2, api2.getResponseTotal());
    }

    @Override
    @Nonnull
    public JDA getJDA() {
        return this.api;
    }

    @Override
    public long getResponseNumber() {
        return this.responseNumber;
    }
}

