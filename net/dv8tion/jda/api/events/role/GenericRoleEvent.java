/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.events.role;

import javax.annotation.Nonnull;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.Event;

public abstract class GenericRoleEvent
extends Event {
    protected final Role role;

    public GenericRoleEvent(@Nonnull JDA api2, long responseNumber, @Nonnull Role role) {
        super(api2, responseNumber);
        this.role = role;
    }

    @Nonnull
    public Role getRole() {
        return this.role;
    }

    @Nonnull
    public Guild getGuild() {
        return this.role.getGuild();
    }
}

