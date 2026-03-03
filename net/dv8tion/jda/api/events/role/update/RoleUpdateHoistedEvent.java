/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.events.role.update;

import javax.annotation.Nonnull;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.role.update.GenericRoleUpdateEvent;

public class RoleUpdateHoistedEvent
extends GenericRoleUpdateEvent<Boolean> {
    public static final String IDENTIFIER = "hoist";

    public RoleUpdateHoistedEvent(@Nonnull JDA api2, long responseNumber, @Nonnull Role role, boolean wasHoisted) {
        super(api2, responseNumber, role, wasHoisted, !wasHoisted, IDENTIFIER);
    }

    public boolean wasHoisted() {
        return this.getOldValue();
    }

    @Override
    @Nonnull
    public Boolean getOldValue() {
        return (Boolean)super.getOldValue();
    }

    @Override
    @Nonnull
    public Boolean getNewValue() {
        return (Boolean)super.getNewValue();
    }
}

