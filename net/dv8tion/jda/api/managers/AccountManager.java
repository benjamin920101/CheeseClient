/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.managers;

import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.dv8tion.jda.api.entities.Icon;
import net.dv8tion.jda.api.entities.SelfUser;
import net.dv8tion.jda.api.managers.Manager;

public interface AccountManager
extends Manager<AccountManager> {
    public static final long NAME = 1L;
    public static final long AVATAR = 2L;

    @Nonnull
    public SelfUser getSelfUser();

    @Override
    @Nonnull
    @CheckReturnValue
    public AccountManager reset(long var1);

    @Override
    @Nonnull
    @CheckReturnValue
    public AccountManager reset(long ... var1);

    @Nonnull
    @CheckReturnValue
    public AccountManager setName(@Nonnull String var1);

    @Nonnull
    @CheckReturnValue
    public AccountManager setAvatar(@Nullable Icon var1);
}

