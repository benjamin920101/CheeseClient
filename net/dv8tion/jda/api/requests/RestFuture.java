/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.requests;

import java.util.concurrent.CompletableFuture;
import java.util.function.BooleanSupplier;
import net.dv8tion.jda.api.requests.Request;
import net.dv8tion.jda.internal.JDAImpl;
import net.dv8tion.jda.internal.requests.RestActionImpl;
import net.dv8tion.jda.internal.requests.Route;
import okhttp3.RequestBody;
import org.apache.commons.collections4.map.CaseInsensitiveMap;

public class RestFuture<T>
extends CompletableFuture<T> {
    final Request<T> request;

    public RestFuture(RestActionImpl<T> restAction, boolean shouldQueue, BooleanSupplier checks, RequestBody data, Object rawData, long deadline, boolean priority, Route.CompiledRoute route, CaseInsensitiveMap<String, String> headers) {
        this.request = new Request<Object>(restAction, this::complete, this::completeExceptionally, checks, shouldQueue, data, rawData, deadline, priority, route, headers);
        ((JDAImpl)restAction.getJDA()).getRequester().request(this.request);
    }

    public RestFuture(T t2) {
        this.complete(t2);
        this.request = null;
    }

    public RestFuture(Throwable t2) {
        this.completeExceptionally(t2);
        this.request = null;
    }

    @Override
    public boolean cancel(boolean mayInterrupt) {
        if (this.request != null) {
            this.request.cancel();
        }
        return !this.isDone() && !this.isCancelled() && super.cancel(mayInterrupt);
    }
}

