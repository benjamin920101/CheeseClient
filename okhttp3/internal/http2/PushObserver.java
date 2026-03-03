/*
 * Decompiled with CFR 0.152.
 */
package okhttp3.internal.http2;

import java.io.IOException;
import java.util.List;
import okhttp3.internal.http2.ErrorCode;
import okhttp3.internal.http2.Header;
import okio.BufferedSource;

public interface PushObserver {
    public static final PushObserver CANCEL = new PushObserver(){

        @Override
        public boolean onRequest(int streamId, List<Header> requestHeaders) {
            return true;
        }

        @Override
        public boolean onHeaders(int streamId, List<Header> responseHeaders, boolean last) {
            return true;
        }

        @Override
        public boolean onData(int streamId, BufferedSource source, int byteCount, boolean last) throws IOException {
            source.skip(byteCount);
            return true;
        }

        @Override
        public void onReset(int streamId, ErrorCode errorCode) {
        }
    };

    public boolean onRequest(int var1, List<Header> var2);

    public boolean onHeaders(int var1, List<Header> var2, boolean var3);

    public boolean onData(int var1, BufferedSource var2, int var3, boolean var4) throws IOException;

    public void onReset(int var1, ErrorCode var2);
}

