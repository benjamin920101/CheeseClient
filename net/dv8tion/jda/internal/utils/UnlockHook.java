/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.internal.utils;

import java.util.concurrent.locks.Lock;

public class UnlockHook
implements AutoCloseable {
    private final Lock lock;

    public UnlockHook(Lock lock) {
        this.lock = lock;
    }

    @Override
    public void close() {
        this.lock.unlock();
    }
}

