/*
 * Decompiled with CFR 0.152.
 */
package okhttp3.internal.http;

import java.io.IOException;
import okhttp3.Headers;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Sink;

public interface HttpCodec {
    public static final int DISCARD_STREAM_TIMEOUT_MILLIS = 100;

    public Sink createRequestBody(Request var1, long var2);

    public void writeRequestHeaders(Request var1) throws IOException;

    public void flushRequest() throws IOException;

    public void finishRequest() throws IOException;

    public Response.Builder readResponseHeaders(boolean var1) throws IOException;

    public ResponseBody openResponseBody(Response var1) throws IOException;

    public Headers trailers() throws IOException;

    public void cancel();
}

