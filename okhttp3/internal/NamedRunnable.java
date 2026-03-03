/*
 * Decompiled with CFR 0.152.
 */
package okhttp3.internal;

import okhttp3.internal.Util;

public abstract class NamedRunnable
implements Runnable {
    protected final String name;

    public NamedRunnable(String format, Object ... args) {
        this.name = Util.format(format, args);
    }

    @Override
    public final void run() {
        String oldName = Thread.currentThread().getName();
        Thread.currentThread().setName(this.name);
        try {
            this.execute();
        }
        finally {
            Thread.currentThread().setName(oldName);
        }
    }

    protected abstract void execute();
}

