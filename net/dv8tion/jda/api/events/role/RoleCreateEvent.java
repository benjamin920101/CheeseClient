/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.events.role;

import javax.annotation.Nonnull;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.role.GenericRoleEvent;

public class RoleCreateEvent
extends GenericRoleEvent {
    public RoleCreateEvent(@Nonnull JDA api2, long responseNumber, @Nonnull Role createdRole) {
        super(api2, responseNumber, createdRole);
    }
}

