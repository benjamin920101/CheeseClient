/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.requests.restaction.order;

import java.util.Comparator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.BooleanSupplier;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.dv8tion.jda.api.requests.RestAction;

public interface OrderAction<T, M extends OrderAction<T, M>>
extends RestAction<Void> {
    @Nonnull
    public M setCheck(@Nullable BooleanSupplier var1);

    @Nonnull
    public M timeout(long var1, @Nonnull TimeUnit var3);

    @Nonnull
    public M deadline(long var1);

    public boolean isAscendingOrder();

    @Nonnull
    public List<T> getCurrentOrder();

    @Nonnull
    public M selectPosition(int var1);

    @Nonnull
    public M selectPosition(@Nonnull T var1);

    public int getSelectedPosition();

    @Nonnull
    public T getSelectedEntity();

    @Nonnull
    public M moveUp(int var1);

    @Nonnull
    public M moveDown(int var1);

    @Nonnull
    public M moveTo(int var1);

    @Nonnull
    public M swapPosition(int var1);

    @Nonnull
    public M swapPosition(@Nonnull T var1);

    @Nonnull
    public M reverseOrder();

    @Nonnull
    public M shuffleOrder();

    @Nonnull
    public M sortOrder(@Nonnull Comparator<T> var1);
}

