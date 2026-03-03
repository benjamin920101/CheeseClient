/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.requests.restaction;

import java.util.concurrent.TimeUnit;
import java.util.function.BooleanSupplier;
import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.dv8tion.jda.api.entities.Invite;
import net.dv8tion.jda.api.requests.restaction.AuditableRestAction;

public interface InviteAction
extends AuditableRestAction<Invite> {
    @Override
    @Nonnull
    public InviteAction setCheck(@Nullable BooleanSupplier var1);

    @Override
    @Nonnull
    public InviteAction timeout(long var1, @Nonnull TimeUnit var3);

    @Override
    @Nonnull
    public InviteAction deadline(long var1);

    @Nonnull
    @CheckReturnValue
    public InviteAction setMaxAge(@Nullable Integer var1);

    @Nonnull
    @CheckReturnValue
    public InviteAction setMaxAge(@Nullable Long var1, @Nonnull TimeUnit var2);

    @Nonnull
    @CheckReturnValue
    public InviteAction setMaxUses(@Nullable Integer var1);

    @Nonnull
    @CheckReturnValue
    public InviteAction setTemporary(@Nullable Boolean var1);

    @Nonnull
    @CheckReturnValue
    public InviteAction setUnique(@Nullable Boolean var1);
}

