/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.events.channel.text.update;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.UpdateEvent;
import net.dv8tion.jda.api.events.channel.text.GenericTextChannelEvent;

public abstract class GenericTextChannelUpdateEvent<T>
extends GenericTextChannelEvent
implements UpdateEvent<TextChannel, T> {
    protected final T previous;
    protected final T next;
    protected final String identifier;

    public GenericTextChannelUpdateEvent(@Nonnull JDA api2, long responseNumber, @Nonnull TextChannel channel, @Nullable T previous, @Nullable T next, @Nonnull String identifier) {
        super(api2, responseNumber, channel);
        this.previous = previous;
        this.next = next;
        this.identifier = identifier;
    }

    @Override
    @Nonnull
    public TextChannel getEntity() {
        return this.getChannel();
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
        return "TextChannelUpdate[" + this.getPropertyIdentifier() + "](" + this.getOldValue() + "->" + this.getNewValue() + ')';
    }
}

