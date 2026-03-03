/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.internal.requests;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.function.BooleanSupplier;
import java.util.function.Consumer;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.exceptions.RateLimitedException;
import net.dv8tion.jda.api.requests.RestAction;
import net.dv8tion.jda.api.requests.restaction.AuditableRestAction;

public class CompletedRestAction<T>
implements AuditableRestAction<T> {
    private final JDA api;
    private final T value;
    private final Throwable error;

    public CompletedRestAction(JDA api2, T value, Throwable error) {
        this.api = api2;
        this.value = value;
        this.error = error;
    }

    public CompletedRestAction(JDA api2, T value) {
        this(api2, value, null);
    }

    public CompletedRestAction(JDA api2, Throwable error) {
        this(api2, null, error);
    }

    @Override
    @Nonnull
    public AuditableRestAction<T> reason(@Nullable String reason) {
        return this;
    }

    @Override
    @Nonnull
    public JDA getJDA() {
        return this.api;
    }

    @Override
    @Nonnull
    public AuditableRestAction<T> setCheck(@Nullable BooleanSupplier checks) {
        return this;
    }

    @Override
    @Nonnull
    public AuditableRestAction<T> timeout(long timeout, @Nonnull TimeUnit unit) {
        return this;
    }

    @Override
    @Nonnull
    public AuditableRestAction<T> deadline(long timestamp) {
        return this;
    }

    @Override
    public void queue(@Nullable Consumer<? super T> success, @Nullable Consumer<? super Throwable> failure) {
        if (this.error == null) {
            if (success == null) {
                RestAction.getDefaultSuccess().accept(this.value);
            } else {
                success.accept(this.value);
            }
        } else if (failure == null) {
            RestAction.getDefaultFailure().accept(this.error);
        } else {
            failure.accept(this.error);
        }
    }

    @Override
    public T complete(boolean shouldQueue) throws RateLimitedException {
        if (this.error != null) {
            if (this.error instanceof RateLimitedException) {
                throw (RateLimitedException)this.error;
            }
            if (this.error instanceof RuntimeException) {
                throw (RuntimeException)this.error;
            }
            if (this.error instanceof Error) {
                throw (Error)this.error;
            }
            throw new IllegalStateException(this.error);
        }
        return this.value;
    }

    @Override
    @Nonnull
    public CompletableFuture<T> submit(boolean shouldQueue) {
        CompletableFuture<T> future = new CompletableFuture<T>();
        if (this.error != null) {
            future.completeExceptionally(this.error);
        } else {
            future.complete(this.value);
        }
        return future;
    }
}

