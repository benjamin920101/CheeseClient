/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.events;

import javax.annotation.Nonnull;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.events.Event;

public class ExceptionEvent
extends Event {
    protected final Throwable throwable;
    protected final boolean logged;

    public ExceptionEvent(@Nonnull JDA api2, @Nonnull Throwable throwable, boolean logged) {
        super(api2);
        this.throwable = throwable;
        this.logged = logged;
    }

    public boolean isLogged() {
        return this.logged;
    }

    @Nonnull
    public Throwable getCause() {
        return this.throwable;
    }
}

