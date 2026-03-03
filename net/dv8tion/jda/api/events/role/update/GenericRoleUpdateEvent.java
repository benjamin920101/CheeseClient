/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.events.role.update;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.UpdateEvent;
import net.dv8tion.jda.api.events.role.GenericRoleEvent;

public abstract class GenericRoleUpdateEvent<T>
extends GenericRoleEvent
implements UpdateEvent<Role, T> {
    protected final T previous;
    protected final T next;
    protected final String identifier;

    public GenericRoleUpdateEvent(@Nonnull JDA api2, long responseNumber, @Nonnull Role role, @Nullable T previous, @Nullable T next, @Nonnull String identifier) {
        super(api2, responseNumber, role);
        this.previous = previous;
        this.next = next;
        this.identifier = identifier;
    }

    @Override
    @Nonnull
    public Role getEntity() {
        return this.role;
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
        return "RoleUpdate[" + this.getPropertyIdentifier() + "](" + this.getOldValue() + "->" + this.getNewValue() + ")";
    }
}

