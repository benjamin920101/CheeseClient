/*
 * Decompiled with CFR 0.152.
 */
package okhttp3.internal.http2;

import java.io.IOException;
import okhttp3.internal.http2.ErrorCode;

public final class StreamResetException
extends IOException {
    public final ErrorCode errorCode;

    public StreamResetException(ErrorCode errorCode) {
        super("stream was reset: " + (Object)((Object)errorCode));
        this.errorCode = errorCode;
    }
}

