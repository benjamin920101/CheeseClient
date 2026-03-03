/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.events.role.update;

import java.awt.Color;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.role.update.GenericRoleUpdateEvent;

public class RoleUpdateColorEvent
extends GenericRoleUpdateEvent<Integer> {
    public static final String IDENTIFIER = "color";

    public RoleUpdateColorEvent(@Nonnull JDA api2, long responseNumber, @Nonnull Role role, int oldColor) {
        super(api2, responseNumber, role, oldColor, role.getColorRaw(), IDENTIFIER);
    }

    @Nullable
    public Color getOldColor() {
        return (Integer)this.previous != 0x1FFFFFFF ? new Color((Integer)this.previous) : null;
    }

    public int getOldColorRaw() {
        return this.getOldValue();
    }

    @Nullable
    public Color getNewColor() {
        return (Integer)this.next != 0x1FFFFFFF ? new Color((Integer)this.next) : null;
    }

    public int getNewColorRaw() {
        return this.getNewValue();
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

