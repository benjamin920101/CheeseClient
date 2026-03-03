/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.utils.concurrent;

import java.util.function.Consumer;
import javax.annotation.Nonnull;

public interface Task<T> {
    public boolean isStarted();

    @Nonnull
    public Task<T> onError(@Nonnull Consumer<? super Throwable> var1);

    @Nonnull
    public Task<T> onSuccess(@Nonnull Consumer<? super T> var1);

    @Nonnull
    public T get();

    public void cancel();
}

