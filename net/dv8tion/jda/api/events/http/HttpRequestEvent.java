/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.events.http;

import java.util.Set;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.dv8tion.jda.api.events.Event;
import net.dv8tion.jda.api.requests.Request;
import net.dv8tion.jda.api.requests.RestAction;
import net.dv8tion.jda.api.utils.data.DataArray;
import net.dv8tion.jda.api.utils.data.DataObject;
import net.dv8tion.jda.internal.requests.Route;
import okhttp3.Headers;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class HttpRequestEvent
extends Event {
    private final Request<?> request;
    private final net.dv8tion.jda.api.requests.Response response;

    public HttpRequestEvent(@Nonnull Request<?> request, @Nonnull net.dv8tion.jda.api.requests.Response response) {
        super(request.getJDA());
        this.request = request;
        this.response = response;
    }

    @Nonnull
    public Request<?> getRequest() {
        return this.request;
    }

    @Nullable
    public RequestBody getRequestBody() {
        return this.request.getBody();
    }

    @Nullable
    public Object getRequestBodyRaw() {
        return this.request.getRawBody();
    }

    @Nullable
    public Headers getRequestHeaders() {
        return this.response.getRawResponse() == null ? null : this.response.getRawResponse().request().headers();
    }

    @Nullable
    public okhttp3.Request getRequestRaw() {
        return this.response.getRawResponse() == null ? null : this.response.getRawResponse().request();
    }

    @Nullable
    public net.dv8tion.jda.api.requests.Response getResponse() {
        return this.response;
    }

    @Nullable
    public ResponseBody getResponseBody() {
        return this.response.getRawResponse() == null ? null : this.response.getRawResponse().body();
    }

    @Nullable
    public DataArray getResponseBodyAsArray() {
        return this.response.getArray();
    }

    @Nullable
    public DataObject getResponseBodyAsObject() {
        return this.response.getObject();
    }

    @Nullable
    public String getResponseBodyAsString() {
        return this.response.getString();
    }

    @Nullable
    public Headers getResponseHeaders() {
        return this.response.getRawResponse() == null ? null : this.response.getRawResponse().headers();
    }

    @Nullable
    public Response getResponseRaw() {
        return this.response.getRawResponse();
    }

    @Nonnull
    public Set<String> getCFRays() {
        return this.response.getCFRays();
    }

    @Nonnull
    public RestAction<?> getRestAction() {
        return this.request.getRestAction();
    }

    @Nonnull
    public Route.CompiledRoute getRoute() {
        return this.request.getRoute();
    }

    public boolean isRateLimit() {
        return this.response.isRateLimit();
    }
}

