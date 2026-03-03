/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.events.role.update;

import javax.annotation.Nonnull;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.role.update.GenericRoleUpdateEvent;

public class RoleUpdateMentionableEvent
extends GenericRoleUpdateEvent<Boolean> {
    public static final String IDENTIFIER = "mentionable";

    public RoleUpdateMentionableEvent(@Nonnull JDA api2, long responseNumber, @Nonnull Role role, boolean wasMentionable) {
        super(api2, responseNumber, role, wasMentionable, !wasMentionable, IDENTIFIER);
    }

    public boolean wasMentionable() {
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

