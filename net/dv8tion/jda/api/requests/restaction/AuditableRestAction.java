/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.requests.restaction;

import java.util.concurrent.TimeUnit;
import java.util.function.BooleanSupplier;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.dv8tion.jda.api.requests.RestAction;

public interface AuditableRestAction<T>
extends RestAction<T> {
    @Nonnull
    public AuditableRestAction<T> reason(@Nullable String var1);

    @Override
    @Nonnull
    public AuditableRestAction<T> setCheck(@Nullable BooleanSupplier var1);

    @Override
    @Nonnull
    public AuditableRestAction<T> timeout(long var1, @Nonnull TimeUnit var3);

    @Override
    @Nonnull
    public AuditableRestAction<T> deadline(long var1);
}

