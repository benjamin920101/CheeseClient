/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.internal.utils.concurrent.task;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;
import javax.annotation.Nonnull;
import net.dv8tion.jda.api.exceptions.ContextException;
import net.dv8tion.jda.api.utils.concurrent.Task;
import net.dv8tion.jda.internal.requests.WebSocketClient;
import net.dv8tion.jda.internal.utils.Checks;
import net.dv8tion.jda.internal.utils.JDALogger;
import org.slf4j.Logger;

public class GatewayTask<T>
implements Task<T> {
    private static final Logger log = JDALogger.getLog(Task.class);
    private final Runnable onCancel;
    private final CompletableFuture<T> future;

    public GatewayTask(CompletableFuture<T> future, Runnable onCancel) {
        this.future = future;
        this.onCancel = onCancel;
    }

    @Override
    public boolean isStarted() {
        return true;
    }

    @Override
    @Nonnull
    public Task<T> onError(@Nonnull Consumer<? super Throwable> callback) {
        Checks.notNull(callback, "Callback");
        Consumer<Throwable> failureHandler = ContextException.here(error -> log.error("Task Failure callback threw error", (Throwable)error));
        this.future.exceptionally(error -> {
            block2: {
                try {
                    callback.accept((Throwable)error);
                }
                catch (Throwable e2) {
                    failureHandler.accept(e2);
                    if (!(e2 instanceof Error)) break block2;
                    throw e2;
                }
            }
            return null;
        });
        return this;
    }

    @Override
    @Nonnull
    public Task<T> onSuccess(@Nonnull Consumer<? super T> callback) {
        Checks.notNull(callback, "Callback");
        Consumer<Throwable> failureHandler = ContextException.here(error -> log.error("Task Success callback threw error", (Throwable)error));
        this.future.thenAccept(result -> {
            block2: {
                try {
                    callback.accept(result);
                }
                catch (Throwable error) {
                    failureHandler.accept(error);
                    if (!(error instanceof Error)) break block2;
                    throw error;
                }
            }
        });
        return this;
    }

    @Override
    @Nonnull
    public T get() {
        if (WebSocketClient.WS_THREAD.get().booleanValue()) {
            throw new UnsupportedOperationException("Blocking operations are not permitted on the gateway thread");
        }
        return this.future.join();
    }

    @Override
    public void cancel() {
        this.onCancel.run();
    }
}

