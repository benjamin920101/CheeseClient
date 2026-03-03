/*
 * Decompiled with CFR 0.152.
 */
package okhttp3;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;
import okhttp3.Call;
import okhttp3.Connection;
import okhttp3.Request;
import okhttp3.Response;

public interface Interceptor {
    public Response intercept(Chain var1) throws IOException;

    public static interface Chain {
        public Request request();

        public Response proceed(Request var1) throws IOException;

        @Nullable
        public Connection connection();

        public Call call();

        public int connectTimeoutMillis();

        public Chain withConnectTimeout(int var1, TimeUnit var2);

        public int readTimeoutMillis();

        public Chain withReadTimeout(int var1, TimeUnit var2);

        public int writeTimeoutMillis();

        public Chain withWriteTimeout(int var1, TimeUnit var2);
    }
}

