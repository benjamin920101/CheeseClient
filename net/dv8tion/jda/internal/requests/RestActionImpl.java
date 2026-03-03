/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.internal.requests;

import java.util.concurrent.CancellationException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.function.BiFunction;
import java.util.function.BooleanSupplier;
import java.util.function.Consumer;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.exceptions.ErrorResponseException;
import net.dv8tion.jda.api.exceptions.RateLimitedException;
import net.dv8tion.jda.api.requests.Request;
import net.dv8tion.jda.api.requests.Response;
import net.dv8tion.jda.api.requests.RestAction;
import net.dv8tion.jda.api.requests.RestFuture;
import net.dv8tion.jda.api.utils.data.DataArray;
import net.dv8tion.jda.api.utils.data.DataObject;
import net.dv8tion.jda.internal.JDAImpl;
import net.dv8tion.jda.internal.requests.CallbackContext;
import net.dv8tion.jda.internal.requests.Requester;
import net.dv8tion.jda.internal.requests.Route;
import net.dv8tion.jda.internal.utils.Checks;
import net.dv8tion.jda.internal.utils.JDALogger;
import okhttp3.RequestBody;
import org.apache.commons.collections4.map.CaseInsensitiveMap;
import org.slf4j.Logger;

public class RestActionImpl<T>
implements RestAction<T> {
    public static final Logger LOG = JDALogger.getLog(RestAction.class);
    private static Consumer<Object> DEFAULT_SUCCESS = o2 -> {};
    private static Consumer<? super Throwable> DEFAULT_FAILURE = t2 -> {
        if (t2 instanceof CancellationException || t2 instanceof TimeoutException) {
            LOG.debug(t2.getMessage());
        } else if (LOG.isDebugEnabled() || !(t2 instanceof ErrorResponseException)) {
            LOG.error("RestAction queue returned failure", (Throwable)t2);
        } else if (t2.getCause() != null) {
            LOG.error("RestAction queue returned failure: [{}] {}", t2.getClass().getSimpleName(), t2.getMessage(), t2.getCause());
        } else {
            LOG.error("RestAction queue returned failure: [{}] {}", (Object)t2.getClass().getSimpleName(), (Object)t2.getMessage());
        }
    };
    protected static boolean passContext = true;
    protected static long defaultTimeout = 0L;
    protected final JDAImpl api;
    private final Route.CompiledRoute route;
    private final RequestBody data;
    private final BiFunction<Response, Request<T>, T> handler;
    private boolean priority = false;
    private long deadline = 0L;
    private Object rawData;
    private BooleanSupplier checks;

    public static void setPassContext(boolean enable) {
        passContext = enable;
    }

    public static boolean isPassContext() {
        return passContext;
    }

    public static void setDefaultFailure(Consumer<? super Throwable> callback) {
        DEFAULT_FAILURE = callback == null ? t2 -> {} : callback;
    }

    public static void setDefaultSuccess(Consumer<Object> callback) {
        DEFAULT_SUCCESS = callback == null ? t2 -> {} : callback;
    }

    public static void setDefaultTimeout(long timeout, @Nonnull TimeUnit unit) {
        Checks.notNull((Object)unit, "TimeUnit");
        defaultTimeout = unit.toMillis(timeout);
    }

    public static long getDefaultTimeout() {
        return defaultTimeout;
    }

    public static Consumer<? super Throwable> getDefaultFailure() {
        return DEFAULT_FAILURE;
    }

    public static Consumer<Object> getDefaultSuccess() {
        return DEFAULT_SUCCESS;
    }

    public RestActionImpl(JDA api2, Route.CompiledRoute route) {
        this(api2, route, (RequestBody)null, null);
    }

    public RestActionImpl(JDA api2, Route.CompiledRoute route, DataObject data) {
        this(api2, route, data, null);
    }

    public RestActionImpl(JDA api2, Route.CompiledRoute route, RequestBody data) {
        this(api2, route, data, null);
    }

    public RestActionImpl(JDA api2, Route.CompiledRoute route, BiFunction<Response, Request<T>, T> handler) {
        this(api2, route, (RequestBody)null, handler);
    }

    public RestActionImpl(JDA api2, Route.CompiledRoute route, DataObject data, BiFunction<Response, Request<T>, T> handler) {
        this(api2, route, data == null ? null : RequestBody.create(Requester.MEDIA_TYPE_JSON, data.toJson()), handler);
        this.rawData = data;
    }

    public RestActionImpl(JDA api2, Route.CompiledRoute route, RequestBody data, BiFunction<Response, Request<T>, T> handler) {
        Checks.notNull(api2, "api");
        this.api = (JDAImpl)api2;
        this.route = route;
        this.data = data;
        this.handler = handler;
    }

    public RestActionImpl<T> priority() {
        this.priority = true;
        return this;
    }

    @Override
    @Nonnull
    public JDA getJDA() {
        return this.api;
    }

    @Override
    @Nonnull
    public RestAction<T> setCheck(BooleanSupplier checks) {
        this.checks = checks;
        return this;
    }

    @Override
    @Nullable
    public BooleanSupplier getCheck() {
        return this.checks;
    }

    @Override
    @Nonnull
    public RestAction<T> deadline(long timestamp) {
        this.deadline = timestamp;
        return this;
    }

    @Override
    public void queue(Consumer<? super T> success, Consumer<? super Throwable> failure) {
        Route.CompiledRoute route = this.finalizeRoute();
        Checks.notNull(route, "Route");
        RequestBody data = this.finalizeData();
        CaseInsensitiveMap<String, String> headers = this.finalizeHeaders();
        CheckWrapper finisher = this.getFinisher();
        if (success == null) {
            success = DEFAULT_SUCCESS;
        }
        if (failure == null) {
            failure = DEFAULT_FAILURE;
        }
        this.api.getRequester().request(new Request<T>(this, success, failure, finisher, true, data, this.rawData, this.getDeadline(), this.priority, route, headers));
    }

    @Override
    @Nonnull
    public CompletableFuture<T> submit(boolean shouldQueue) {
        Route.CompiledRoute route = this.finalizeRoute();
        Checks.notNull(route, "Route");
        RequestBody data = this.finalizeData();
        CaseInsensitiveMap<String, String> headers = this.finalizeHeaders();
        CheckWrapper finisher = this.getFinisher();
        return new RestFuture(this, shouldQueue, finisher, data, this.rawData, this.getDeadline(), this.priority, route, headers);
    }

    @Override
    public T complete(boolean shouldQueue) throws RateLimitedException {
        if (CallbackContext.isCallbackContext()) {
            throw new IllegalStateException("Preventing use of complete() in callback threads! This operation can be a deadlock cause");
        }
        try {
            return this.submit(shouldQueue).join();
        }
        catch (CompletionException e2) {
            if (e2.getCause() != null) {
                Throwable cause = e2.getCause();
                if (cause instanceof ErrorResponseException) {
                    throw (ErrorResponseException)cause.fillInStackTrace();
                }
                if (cause instanceof RateLimitedException) {
                    throw (RateLimitedException)cause.fillInStackTrace();
                }
                if (cause instanceof RuntimeException) {
                    throw (RuntimeException)cause;
                }
                if (cause instanceof Error) {
                    throw (Error)cause;
                }
            }
            throw e2;
        }
    }

    protected RequestBody finalizeData() {
        return this.data;
    }

    protected Route.CompiledRoute finalizeRoute() {
        return this.route;
    }

    protected CaseInsensitiveMap<String, String> finalizeHeaders() {
        return null;
    }

    protected BooleanSupplier finalizeChecks() {
        return null;
    }

    protected RequestBody getRequestBody(DataObject object) {
        this.rawData = object;
        return object == null ? null : RequestBody.create(Requester.MEDIA_TYPE_JSON, object.toJson());
    }

    protected RequestBody getRequestBody(DataArray array) {
        this.rawData = array;
        return array == null ? null : RequestBody.create(Requester.MEDIA_TYPE_JSON, array.toJson());
    }

    private CheckWrapper getFinisher() {
        BooleanSupplier pre = this.finalizeChecks();
        BooleanSupplier wrapped = this.checks;
        return pre != null || wrapped != null ? new CheckWrapper(wrapped, pre) : CheckWrapper.EMPTY;
    }

    public void handleResponse(Response response, Request<T> request) {
        if (response.isOk()) {
            this.handleSuccess(response, request);
        } else {
            request.onFailure(response);
        }
    }

    protected void handleSuccess(Response response, Request<T> request) {
        if (this.handler == null) {
            request.onSuccess(null);
        } else {
            request.onSuccess(this.handler.apply(response, request));
        }
    }

    private long getDeadline() {
        return this.deadline > 0L ? this.deadline : (defaultTimeout > 0L ? System.currentTimeMillis() + defaultTimeout : 0L);
    }

    protected static class CheckWrapper
    implements BooleanSupplier {
        public static final CheckWrapper EMPTY = new CheckWrapper(null, null){

            @Override
            public boolean getAsBoolean() {
                return true;
            }
        };
        protected final BooleanSupplier pre;
        protected final BooleanSupplier wrapped;

        public CheckWrapper(BooleanSupplier wrapped, BooleanSupplier pre) {
            this.pre = pre;
            this.wrapped = wrapped;
        }

        public boolean pre() {
            return this.pre == null || this.pre.getAsBoolean();
        }

        public boolean test() {
            return this.wrapped == null || this.wrapped.getAsBoolean();
        }

        @Override
        public boolean getAsBoolean() {
            return this.pre() && this.test();
        }
    }
}

