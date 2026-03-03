/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.utils;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.concurrent.locks.Lock;
import javax.annotation.Nonnull;
import net.dv8tion.jda.api.utils.ClosableIterator;
import net.dv8tion.jda.internal.utils.JDALogger;
import org.slf4j.Logger;

public class LockIterator<T>
implements ClosableIterator<T> {
    private static final Logger log = JDALogger.getLog(ClosableIterator.class);
    private final Iterator<? extends T> it;
    private Lock lock;

    public LockIterator(@Nonnull Iterator<? extends T> it2, Lock lock) {
        this.it = it2;
        this.lock = lock;
    }

    @Override
    public void close() {
        if (this.lock != null) {
            this.lock.unlock();
        }
        this.lock = null;
    }

    @Override
    public boolean hasNext() {
        if (this.lock == null) {
            return false;
        }
        boolean hasNext = this.it.hasNext();
        if (!hasNext) {
            this.close();
        }
        return hasNext;
    }

    @Override
    @Nonnull
    public T next() {
        if (this.lock == null) {
            throw new NoSuchElementException();
        }
        return this.it.next();
    }

    @Deprecated
    protected void finalize() {
        if (this.lock != null) {
            log.error("Finalizing without closing, performing force close on lock");
            this.close();
        }
    }
}

