/*
 * Decompiled with CFR 0.152.
 */
package okhttp3.internal.cache;

import java.io.IOException;
import okio.Sink;

public interface CacheRequest {
    public Sink body() throws IOException;

    public void abort();
}

