/*
 * Decompiled with CFR 0.152.
 */
package okhttp3.internal.http;

import javax.annotation.Nullable;
import okhttp3.MediaType;
import okhttp3.ResponseBody;
import okio.BufferedSource;

public final class RealResponseBody
extends ResponseBody {
    @Nullable
    private final String contentTypeString;
    private final long contentLength;
    private final BufferedSource source;

    public RealResponseBody(@Nullable String contentTypeString, long contentLength, BufferedSource source) {
        this.contentTypeString = contentTypeString;
        this.contentLength = contentLength;
        this.source = source;
    }

    @Override
    public MediaType contentType() {
        return this.contentTypeString != null ? MediaType.parse(this.contentTypeString) : null;
    }

    @Override
    public long contentLength() {
        return this.contentLength;
    }

    @Override
    public BufferedSource source() {
        return this.source;
    }
}

