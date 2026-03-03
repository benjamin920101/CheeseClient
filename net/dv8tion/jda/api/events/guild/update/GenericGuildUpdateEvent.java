/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.events.guild.update;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.UpdateEvent;
import net.dv8tion.jda.api.events.guild.GenericGuildEvent;

public abstract class GenericGuildUpdateEvent<T>
extends GenericGuildEvent
implements UpdateEvent<Guild, T> {
    protected final T previous;
    protected final T next;
    protected final String identifier;

    public GenericGuildUpdateEvent(@Nonnull JDA api2, long responseNumber, @Nonnull Guild guild, @Nullable T previous, @Nullable T next, @Nonnull String identifier) {
        super(api2, responseNumber, guild);
        this.previous = previous;
        this.next = next;
        this.identifier = identifier;
    }

    @Override
    @Nonnull
    public Guild getEntity() {
        return this.getGuild();
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
        return "GuildUpdate[" + this.getPropertyIdentifier() + "](" + this.getOldValue() + "->" + this.getNewValue() + ')';
    }
}

