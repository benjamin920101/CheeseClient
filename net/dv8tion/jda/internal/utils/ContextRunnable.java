/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.internal.utils;

import java.util.concurrent.Callable;
import net.dv8tion.jda.api.audit.ThreadLocalReason;

public class ContextRunnable<E>
implements Runnable,
Callable<E> {
    private final String localReason = ThreadLocalReason.getCurrent();
    private final Runnable runnable;
    private final Callable<E> callable;

    public ContextRunnable(Runnable runnable) {
        this.runnable = runnable;
        this.callable = null;
    }

    public ContextRunnable(Callable<E> callable) {
        this.runnable = null;
        this.callable = callable;
    }

    @Override
    public void run() {
        try (ThreadLocalReason.Closable __ = ThreadLocalReason.closable(this.localReason);){
            this.runnable.run();
        }
    }

    @Override
    public E call() throws Exception {
        try (ThreadLocalReason.Closable __ = ThreadLocalReason.closable(this.localReason);){
            E e2 = this.callable.call();
            return e2;
        }
    }
}

