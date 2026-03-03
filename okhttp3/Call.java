/*
 * Decompiled with CFR 0.152.
 */
package okhttp3;

import java.io.IOException;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.Response;
import okio.Timeout;

public interface Call
extends Cloneable {
    public Request request();

    public Response execute() throws IOException;

    public void enqueue(Callback var1);

    public void cancel();

    public boolean isExecuted();

    public boolean isCanceled();

    public Timeout timeout();

    public Call clone();

    public static interface Factory {
        public Call newCall(Request var1);
    }
}

