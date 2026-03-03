/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.events.emote.update;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Emote;
import net.dv8tion.jda.api.events.UpdateEvent;
import net.dv8tion.jda.api.events.emote.GenericEmoteEvent;

public abstract class GenericEmoteUpdateEvent<T>
extends GenericEmoteEvent
implements UpdateEvent<Emote, T> {
    protected final T previous;
    protected final T next;
    protected final String identifier;

    public GenericEmoteUpdateEvent(@Nonnull JDA api2, long responseNumber, @Nonnull Emote emote, @Nullable T previous, @Nullable T next, @Nonnull String identifier) {
        super(api2, responseNumber, emote);
        this.previous = previous;
        this.next = next;
        this.identifier = identifier;
    }

    @Override
    @Nonnull
    public Emote getEntity() {
        return this.getEmote();
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
        return "EmoteUpdate[" + this.getPropertyIdentifier() + "](" + this.getOldValue() + "->" + this.getNewValue() + ')';
    }
}

