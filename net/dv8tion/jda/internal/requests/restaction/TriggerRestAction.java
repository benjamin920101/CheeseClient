/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.internal.requests.restaction;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import javax.annotation.Nonnull;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.requests.Request;
import net.dv8tion.jda.api.requests.Response;
import net.dv8tion.jda.api.requests.RestAction;
import net.dv8tion.jda.api.utils.MiscUtil;
import net.dv8tion.jda.api.utils.data.DataObject;
import net.dv8tion.jda.internal.requests.RestActionImpl;
import net.dv8tion.jda.internal.requests.Route;
import okhttp3.RequestBody;

public class TriggerRestAction<T>
extends RestActionImpl<T> {
    private final ReentrantLock mutex = new ReentrantLock();
    private final List<Runnable> callbacks = new LinkedList<Runnable>();
    private volatile boolean isReady;
    private volatile Throwable exception;

    public TriggerRestAction(JDA api2, Route.CompiledRoute route) {
        super(api2, route);
    }

    public TriggerRestAction(JDA api2, Route.CompiledRoute route, DataObject data) {
        super(api2, route, data);
    }

    public TriggerRestAction(JDA api2, Route.CompiledRoute route, RequestBody data) {
        super(api2, route, data);
    }

    public TriggerRestAction(JDA api2, Route.CompiledRoute route, BiFunction<Response, Request<T>, T> handler) {
        super(api2, route, handler);
    }

    public TriggerRestAction(JDA api2, Route.CompiledRoute route, DataObject data, BiFunction<Response, Request<T>, T> handler) {
        super(api2, route, data, handler);
    }

    public TriggerRestAction(JDA api2, Route.CompiledRoute route, RequestBody data, BiFunction<Response, Request<T>, T> handler) {
        super(api2, route, data, handler);
    }

    public void run() {
        MiscUtil.locked(this.mutex, () -> {
            this.isReady = true;
            this.callbacks.forEach(Runnable::run);
        });
    }

    public void fail(Throwable throwable) {
        MiscUtil.locked(this.mutex, () -> {
            this.exception = throwable;
            this.callbacks.forEach(Runnable::run);
        });
    }

    public void onReady(Runnable callback) {
        MiscUtil.locked(this.mutex, () -> {
            if (this.isReady || this.exception != null) {
                callback.run();
            } else {
                this.callbacks.add(callback);
            }
        });
    }

    @Override
    public void queue(Consumer<? super T> success, Consumer<? super Throwable> failure) {
        if (this.isReady) {
            super.queue(success, failure);
        } else {
            this.onReady(() -> {
                if (this.exception != null) {
                    if (failure != null) {
                        failure.accept(this.exception);
                    } else {
                        RestAction.getDefaultFailure().accept(this.exception);
                    }
                } else {
                    super.queue(success, failure);
                }
            });
        }
    }

    @Override
    @Nonnull
    public CompletableFuture<T> submit(boolean shouldQueue) {
        if (this.isReady) {
            return super.submit(shouldQueue);
        }
        CompletableFuture future = new CompletableFuture();
        this.onReady(() -> {
            if (this.exception != null) {
                future.completeExceptionally(this.exception);
                return;
            }
            CompletableFuture handle = super.submit(shouldQueue);
            handle.whenComplete((success, error) -> {
                if (error != null) {
                    future.completeExceptionally((Throwable)error);
                } else {
                    future.complete(success);
                }
            });
            future.whenComplete((r2, e2) -> {
                if (future.isCancelled()) {
                    handle.cancel(false);
                }
            });
        });
        return future;
    }
}

