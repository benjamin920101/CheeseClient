/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.audit;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public final class ThreadLocalReason {
    private static ThreadLocal<String> currentReason;

    private ThreadLocalReason() {
        throw new UnsupportedOperationException();
    }

    public static void setCurrent(@Nullable String reason) {
        if (reason != null) {
            if (currentReason == null) {
                currentReason = new ThreadLocal();
            }
            currentReason.set(reason);
        } else if (currentReason != null) {
            currentReason.remove();
        }
    }

    public static void resetCurrent() {
        if (currentReason != null) {
            currentReason.remove();
        }
    }

    @Nullable
    public static String getCurrent() {
        return currentReason == null ? null : currentReason.get();
    }

    @Nonnull
    public static Closable closable(@Nullable String reason) {
        return new Closable(reason);
    }

    public static class Closable
    implements AutoCloseable {
        private final String previous = ThreadLocalReason.getCurrent();

        public Closable(@Nullable String reason) {
            ThreadLocalReason.setCurrent(reason);
        }

        @Override
        public void close() {
            ThreadLocalReason.setCurrent(this.previous);
        }
    }
}

