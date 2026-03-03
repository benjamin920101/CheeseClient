/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.internal.utils.concurrent;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Supplier;
import javax.annotation.Nonnull;

public class CountingThreadFactory
implements ThreadFactory {
    private final Supplier<String> identifier;
    private final AtomicLong count = new AtomicLong(1L);
    private final boolean daemon;

    public CountingThreadFactory(@Nonnull Supplier<String> identifier, @Nonnull String specifier) {
        this(identifier, specifier, true);
    }

    public CountingThreadFactory(@Nonnull Supplier<String> identifier, @Nonnull String specifier, boolean daemon) {
        this.identifier = () -> (String)identifier.get() + " " + specifier;
        this.daemon = daemon;
    }

    @Override
    @Nonnull
    public Thread newThread(@Nonnull Runnable r2) {
        Thread thread = new Thread(r2, this.identifier.get() + "-Worker " + this.count.getAndIncrement());
        thread.setDaemon(this.daemon);
        return thread;
    }
}

