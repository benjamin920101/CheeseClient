/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.events.channel.category.update;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Category;
import net.dv8tion.jda.api.events.UpdateEvent;
import net.dv8tion.jda.api.events.channel.category.GenericCategoryEvent;

public abstract class GenericCategoryUpdateEvent<T>
extends GenericCategoryEvent
implements UpdateEvent<Category, T> {
    protected final T previous;
    protected final T next;
    protected final String identifier;

    public GenericCategoryUpdateEvent(@Nonnull JDA api2, long responseNumber, @Nonnull Category category, @Nullable T previous, @Nullable T next, @Nonnull String identifier) {
        super(api2, responseNumber, category);
        this.previous = previous;
        this.next = next;
        this.identifier = identifier;
    }

    @Override
    @Nonnull
    public Category getEntity() {
        return this.getCategory();
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
        return "CategoryUpdate[" + this.getPropertyIdentifier() + "](" + this.getOldValue() + "->" + this.getNewValue() + ')';
    }
}

