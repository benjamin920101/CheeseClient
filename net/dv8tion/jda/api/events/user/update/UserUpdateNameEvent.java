/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.events.user.update;

import javax.annotation.Nonnull;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.user.update.GenericUserUpdateEvent;

public class UserUpdateNameEvent
extends GenericUserUpdateEvent<String> {
    public static final String IDENTIFIER = "name";

    public UserUpdateNameEvent(@Nonnull JDA api2, long responseNumber, @Nonnull User user, @Nonnull String oldName) {
        super(api2, responseNumber, user, oldName, user.getName(), IDENTIFIER);
    }

    @Nonnull
    public String getOldName() {
        return this.getOldValue();
    }

    @Nonnull
    public String getNewName() {
        return this.getNewValue();
    }

    @Override
    @Nonnull
    public String getOldValue() {
        return (String)super.getOldValue();
    }

    @Override
    @Nonnull
    public String getNewValue() {
        return (String)super.getNewValue();
    }
}

