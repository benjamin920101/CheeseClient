/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.events.user.update;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.UpdateEvent;
import net.dv8tion.jda.api.events.user.GenericUserEvent;

public abstract class GenericUserUpdateEvent<T>
extends GenericUserEvent
implements UpdateEvent<User, T> {
    protected final T previous;
    protected final T next;
    protected final String identifier;

    public GenericUserUpdateEvent(@Nonnull JDA api2, long responseNumber, @Nonnull User user, @Nullable T previous, @Nullable T next, @Nonnull String identifier) {
        super(api2, responseNumber, user);
        this.previous = previous;
        this.next = next;
        this.identifier = identifier;
    }

    @Override
    @Nonnull
    public User getEntity() {
        return this.getUser();
    }

    @Override
    @Nonnull
    public String getPropertyIdentifier() {
        return this.identifier;
    }

    @Override
    @Nullable
    public T getOldValue() {
        return this.previous;
    }

    @Override
    @Nullable
    public T getNewValue() {
        return this.next;
    }

    public String toString() {
        return "UserUpdate[" + this.getPropertyIdentifier() + "](" + this.getOldValue() + "->" + this.getNewValue() + ')';
    }
}

