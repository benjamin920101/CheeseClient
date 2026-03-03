/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.events.user;

import javax.annotation.Nonnull;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.Event;

public abstract class GenericUserEvent
extends Event {
    private final User user;

    public GenericUserEvent(@Nonnull JDA api2, long responseNumber, @Nonnull User user) {
        super(api2, responseNumber);
        this.user = user;
    }

    @Nonnull
    public User getUser() {
        return this.user;
    }
}

