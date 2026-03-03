/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.events.role.update;

import javax.annotation.Nonnull;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.role.update.GenericRoleUpdateEvent;

public class RoleUpdatePositionEvent
extends GenericRoleUpdateEvent<Integer> {
    public static final String IDENTIFIER = "position";
    private final int oldPositionRaw;
    private final int newPositionRaw;

    public RoleUpdatePositionEvent(@Nonnull JDA api2, long responseNumber, @Nonnull Role role, int oldPosition, int oldPositionRaw) {
        super(api2, responseNumber, role, oldPosition, role.getPosition(), IDENTIFIER);
        this.oldPositionRaw = oldPositionRaw;
        this.newPositionRaw = role.getPositionRaw();
    }

    public int getOldPosition() {
        return this.getOldValue();
    }

    public int getOldPositionRaw() {
        return this.oldPositionRaw;
    }

    public int getNewPosition() {
        return this.getNewValue();
    }

    public int getNewPositionRaw() {
        return this.newPositionRaw;
    }

    @Override
    @Nonnull
    public Integer getOldValue() {
        return (Integer)super.getOldValue();
    }

    @Override
    @Nonnull
    public Integer getNewValue() {
        return (Integer)super.getNewValue();
    }
}

