/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.events;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.dv8tion.jda.api.events.GenericEvent;

public interface UpdateEvent<E, T>
extends GenericEvent {
    @Nonnull
    default public Class<E> getEntityType() {
        return this.getEntity().getClass();
    }

    @Nonnull
    public String getPropertyIdentifier();

    @Nonnull
    public E getEntity();

    @Nullable
    public T getOldValue();

    @Nullable
    public T getNewValue();
}

