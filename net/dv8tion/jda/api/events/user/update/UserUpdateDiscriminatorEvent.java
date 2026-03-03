/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.events.user.update;

import javax.annotation.Nonnull;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.user.update.GenericUserUpdateEvent;

public class UserUpdateDiscriminatorEvent
extends GenericUserUpdateEvent<String> {
    public static final String IDENTIFIER = "discriminator";

    public UserUpdateDiscriminatorEvent(@Nonnull JDA api2, long responseNumber, @Nonnull User user, @Nonnull String oldDiscriminator) {
        super(api2, responseNumber, user, oldDiscriminator, user.getDiscriminator(), IDENTIFIER);
    }

    @Nonnull
    public String getOldDiscriminator() {
        return this.getOldValue();
    }

    @Nonnull
    public String getNewDiscriminator() {
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

