/*
 * Decompiled with CFR 0.152.
 */
public final class ki
extends RuntimeException {
    public static final ki a = new ki();

    private ki() {
        this.setStackTrace(new StackTraceElement[0]);
    }

    @Override
    public synchronized Throwable fillInStackTrace() {
        this.setStackTrace(new StackTraceElement[0]);
        return this;
    }
}

