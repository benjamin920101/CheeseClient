/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.internal.utils;

import java.util.HashSet;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;
import net.dv8tion.jda.api.utils.ClosableIterator;
import net.dv8tion.jda.api.utils.cache.CacheView;
import net.dv8tion.jda.internal.utils.JDALogger;
import org.slf4j.Logger;

public class ChainedClosableIterator<T>
implements ClosableIterator<T> {
    private static final Logger log = JDALogger.getLog(ClosableIterator.class);
    private final Set<T> items = new HashSet<T>();
    private final Iterator<? extends CacheView<T>> generator;
    private ClosableIterator<T> currentIterator;
    private T item;

    public ChainedClosableIterator(Iterator<? extends CacheView<T>> generator) {
        this.generator = generator;
    }

    public Set<T> getItems() {
        return this.items;
    }

    @Override
    public void close() {
        if (this.currentIterator != null) {
            this.currentIterator.close();
        }
        this.currentIterator = null;
    }

    @Override
    public boolean hasNext() {
        if (this.item != null) {
            return true;
        }
        if (this.currentIterator != null) {
            if (!this.currentIterator.hasNext()) {
                this.currentIterator.close();
                this.currentIterator = null;
            } else {
                if (this.findNext()) {
                    return true;
                }
                this.currentIterator.close();
                this.currentIterator = null;
            }
        }
        return this.processChain();
    }

    private boolean processChain() {
        while (this.item == null) {
            CacheView<T> view = null;
            while (this.generator.hasNext() && (view = this.generator.next()).isEmpty()) {
                view = null;
            }
            if (view == null) {
                return false;
            }
            this.currentIterator = view.lockedIterator();
            if (!this.findNext()) continue;
            break;
        }
        return true;
    }

    private boolean findNext() {
        while (this.currentIterator.hasNext()) {
            Object next = this.currentIterator.next();
            if (this.items.contains(next)) continue;
            this.item = next;
            this.items.add(this.item);
            return true;
        }
        return false;
    }

    @Override
    public T next() {
        if (!this.hasNext()) {
            throw new NoSuchElementException();
        }
        T tmp = this.item;
        this.item = null;
        return tmp;
    }

    @Deprecated
    protected void finalize() {
        if (this.currentIterator != null) {
            log.error("Finalizing without closing, performing force close on lock");
            this.close();
        }
    }
}

