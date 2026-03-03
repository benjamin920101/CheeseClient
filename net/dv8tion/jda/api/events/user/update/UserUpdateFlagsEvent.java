/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.events.user.update;

import java.util.Collection;
import java.util.EnumSet;
import javax.annotation.Nonnull;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.user.update.GenericUserUpdateEvent;

public class UserUpdateFlagsEvent
extends GenericUserUpdateEvent<EnumSet<User.UserFlag>> {
    public static final String IDENTIFIER = "public_flags";

    public UserUpdateFlagsEvent(@Nonnull JDA api2, long responseNumber, @Nonnull User user, @Nonnull EnumSet<User.UserFlag> oldFlags) {
        super(api2, responseNumber, user, oldFlags, user.getFlags(), IDENTIFIER);
    }

    @Nonnull
    public EnumSet<User.UserFlag> getOldFlags() {
        return (EnumSet)this.getOldValue();
    }

    public int getOldFlagsRaw() {
        return User.UserFlag.getRaw((Collection)this.previous);
    }

    @Nonnull
    public EnumSet<User.UserFlag> getNewFlags() {
        return (EnumSet)this.getNewValue();
    }

    public int getNewFlagsRaw() {
        return User.UserFlag.getRaw((Collection)this.next);
    }
}

