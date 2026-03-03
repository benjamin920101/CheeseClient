/*
 * Decompiled with CFR 0.152.
 */
package okhttp3.internal;

import java.io.IOException;
import java.net.Socket;
import javax.annotation.Nullable;
import javax.net.ssl.SSLSocket;
import okhttp3.Address;
import okhttp3.Call;
import okhttp3.ConnectionPool;
import okhttp3.ConnectionSpec;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Route;
import okhttp3.internal.cache.InternalCache;
import okhttp3.internal.connection.RealConnection;
import okhttp3.internal.connection.RouteDatabase;
import okhttp3.internal.connection.StreamAllocation;
import okhttp3.internal.http.HttpCodec;

public abstract class Internal {
    public static Internal instance;

    public static void initializeInstanceForTests() {
        new OkHttpClient();
    }

    public abstract void addLenient(Headers.Builder var1, String var2);

    public abstract void addLenient(Headers.Builder var1, String var2, String var3);

    public abstract void setCache(OkHttpClient.Builder var1, InternalCache var2);

    public abstract void acquire(ConnectionPool var1, Address var2, StreamAllocation var3, @Nullable Route var4);

    public abstract boolean equalsNonHost(Address var1, Address var2);

    @Nullable
    public abstract Socket deduplicate(ConnectionPool var1, Address var2, StreamAllocation var3);

    public abstract void put(ConnectionPool var1, RealConnection var2);

    public abstract boolean connectionBecameIdle(ConnectionPool var1, RealConnection var2);

    public abstract RouteDatabase routeDatabase(ConnectionPool var1);

    public abstract int code(Response.Builder var1);

    public abstract void apply(ConnectionSpec var1, SSLSocket var2, boolean var3);

    public abstract boolean isInvalidHttpUrlHost(IllegalArgumentException var1);

    public abstract StreamAllocation streamAllocation(Call var1);

    @Nullable
    public abstract IOException timeoutExit(Call var1, @Nullable IOException var2);

    public abstract Call newWebSocketCall(OkHttpClient var1, Request var2);

    public abstract void initCodec(Response.Builder var1, HttpCodec var2);
}

