/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.entities;

import javax.annotation.Nonnull;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.managers.AccountManager;

public interface SelfUser
extends User {
    public long getApplicationIdLong();

    @Nonnull
    default public String getApplicationId() {
        return Long.toUnsignedString(this.getApplicationIdLong());
    }

    public boolean isVerified();

    public boolean isMfaEnabled();

    public long getAllowedFileSize();

    @Nonnull
    public AccountManager getManager();
}

