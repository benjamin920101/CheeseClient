/*
 * Decompiled with CFR 0.152.
 */
package okhttp3;

import javax.annotation.Nullable;
import okhttp3.Request;
import okhttp3.WebSocketListener;
import okio.ByteString;

public interface WebSocket {
    public Request request();

    public long queueSize();

    public boolean send(String var1);

    public boolean send(ByteString var1);

    public boolean close(int var1, @Nullable String var2);

    public void cancel();

    public static interface Factory {
        public WebSocket newWebSocket(Request var1, WebSocketListener var2);
    }
}

