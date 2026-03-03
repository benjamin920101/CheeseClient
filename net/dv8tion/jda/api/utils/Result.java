/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.utils;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.dv8tion.jda.internal.utils.Checks;

public class Result<T> {
    private final T value;
    private final Throwable error;

    private Result(T value, Throwable error) {
        this.value = value;
        this.error = error;
    }

    @Nonnull
    @CheckReturnValue
    public static <E> Result<E> success(@Nullable E value) {
        return new Result<E>(value, null);
    }

    @Nonnull
    @CheckReturnValue
    public static <E> Result<E> failure(@Nonnull Throwable error) {
        Checks.notNull(error, "Error");
        return new Result<Object>(null, error);
    }

    @Nonnull
    @CheckReturnValue
    public static <E> Result<E> defer(@Nonnull Supplier<? extends E> supplier) {
        Checks.notNull(supplier, "Supplier");
        try {
            return Result.success(supplier.get());
        }
        catch (Exception ex2) {
            return Result.failure(ex2);
        }
    }

    public boolean isFailure() {
        return this.error != null;
    }

    public boolean isSuccess() {
        return this.error == null;
    }

    @Nonnull
    public Result<T> onFailure(@Nonnull Consumer<? super Throwable> callback) {
        Checks.notNull(callback, "Callback");
        if (this.isFailure()) {
            callback.accept(this.error);
        }
        return this;
    }

    @Nonnull
    public Result<T> onSuccess(@Nonnull Consumer<? super T> callback) {
        Checks.notNull(callback, "Callback");
        if (this.isSuccess()) {
            callback.accept(this.value);
        }
        return this;
    }

    @Nonnull
    @CheckReturnValue
    public <U> Result<U> map(@Nonnull Function<? super T, ? extends U> function) {
        Checks.notNull(function, "Function");
        if (this.isSuccess()) {
            return Result.defer(() -> function.apply((T)this.value));
        }
        return this;
    }

    @Nonnull
    @CheckReturnValue
    public <U> Result<U> flatMap(@Nonnull Function<? super T, ? extends Result<U>> function) {
        Checks.notNull(function, "Function");
        try {
            if (this.isSuccess()) {
                return function.apply(this.value);
            }
        }
        catch (Exception ex2) {
            return Result.failure(ex2);
        }
        return this;
    }

    public T get() {
        if (this.isFailure()) {
            throw new IllegalStateException(this.error);
        }
        return this.value;
    }

    @Nullable
    public Throwable getFailure() {
        return this.error;
    }

    @Nonnull
    public Result<T> expect(@Nonnull Predicate<? super Throwable> predicate) {
        Checks.notNull(predicate, "Predicate");
        if (this.isFailure() && predicate.test(this.error)) {
            throw new IllegalStateException(this.error);
        }
        return this;
    }

    public String toString() {
        return this.isSuccess() ? "Result(success=" + this.value + ")" : "Result(error=" + this.error + ")";
    }
}

