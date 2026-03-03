/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.internal.utils.cache;

import java.lang.ref.WeakReference;
import java.util.Collections;
import java.util.List;
import java.util.NavigableSet;
import java.util.Set;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import net.dv8tion.jda.api.utils.MiscUtil;
import net.dv8tion.jda.internal.utils.UnlockHook;

public abstract class ReadWriteLockCache<T> {
    protected final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    protected WeakReference<List<T>> cachedList;
    protected WeakReference<Set<T>> cachedSet;

    public UnlockHook writeLock() {
        if (this.lock.getReadHoldCount() > 0) {
            throw new IllegalStateException("Unable to acquire write-lock while holding read-lock!");
        }
        ReentrantReadWriteLock.WriteLock writeLock = this.lock.writeLock();
        MiscUtil.tryLock(writeLock);
        this.onAcquireWriteLock();
        this.clearCachedLists();
        return new UnlockHook(writeLock);
    }

    public UnlockHook readLock() {
        ReentrantReadWriteLock.ReadLock readLock = this.lock.readLock();
        MiscUtil.tryLock(readLock);
        this.onAcquireReadLock();
        return new UnlockHook(readLock);
    }

    public void clearCachedLists() {
        this.cachedList = null;
        this.cachedSet = null;
    }

    protected void onAcquireWriteLock() {
    }

    protected void onAcquireReadLock() {
    }

    protected List<T> getCachedList() {
        return this.cachedList == null ? null : (List)this.cachedList.get();
    }

    protected Set<T> getCachedSet() {
        return this.cachedSet == null ? null : (Set)this.cachedSet.get();
    }

    protected List<T> cache(List<T> list) {
        list = Collections.unmodifiableList(list);
        this.cachedList = new WeakReference<List<List<T>>>(list);
        return list;
    }

    protected Set<T> cache(Set<T> set) {
        set = Collections.unmodifiableSet(set);
        this.cachedSet = new WeakReference<Set<Set<T>>>(set);
        return set;
    }

    protected NavigableSet<T> cache(NavigableSet<T> set) {
        set = Collections.unmodifiableNavigableSet(set);
        this.cachedSet = new WeakReference<NavigableSet<Set<T>>>(set);
        return set;
    }
}

