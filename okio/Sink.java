/*
 * Decompiled with CFR 0.152.
 */
package okio;

import java.io.Closeable;
import java.io.Flushable;
import java.io.IOException;
import okio.Buffer;
import okio.Timeout;

public interface Sink
extends Closeable,
Flushable {
    public void write(Buffer var1, long var2) throws IOException;

    @Override
    public void flush() throws IOException;

    public Timeout timeout();

    @Override
    public void close() throws IOException;
}

