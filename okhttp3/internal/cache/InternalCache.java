/*
 * Decompiled with CFR 0.152.
 */
package okhttp3.internal.cache;

import java.io.IOException;
import javax.annotation.Nullable;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.internal.cache.CacheRequest;
import okhttp3.internal.cache.CacheStrategy;

public interface InternalCache {
    @Nullable
    public Response get(Request var1) throws IOException;

    @Nullable
    public CacheRequest put(Response var1) throws IOException;

    public void remove(Request var1) throws IOException;

    public void update(Response var1, Response var2);

    public void trackConditionalCacheHit();

    public void trackResponse(CacheStrategy var1);
}

