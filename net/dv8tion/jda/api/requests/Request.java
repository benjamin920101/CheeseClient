/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.requests;

import java.util.concurrent.CancellationException;
import java.util.concurrent.TimeoutException;
import java.util.function.BooleanSupplier;
import java.util.function.Consumer;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.dv8tion.jda.api.audit.ThreadLocalReason;
import net.dv8tion.jda.api.events.ExceptionEvent;
import net.dv8tion.jda.api.events.http.HttpRequestEvent;
import net.dv8tion.jda.api.exceptions.ContextException;
import net.dv8tion.jda.api.exceptions.ErrorResponseException;
import net.dv8tion.jda.api.exceptions.RateLimitedException;
import net.dv8tion.jda.api.requests.ErrorResponse;
import net.dv8tion.jda.api.requests.Response;
import net.dv8tion.jda.api.requests.RestAction;
import net.dv8tion.jda.internal.JDAImpl;
import net.dv8tion.jda.internal.requests.CallbackContext;
import net.dv8tion.jda.internal.requests.RestActionImpl;
import net.dv8tion.jda.internal.requests.Route;
import okhttp3.RequestBody;
import org.apache.commons.collections4.map.CaseInsensitiveMap;

public class Request<T> {
    private final JDAImpl api;
    private final RestActionImpl<T> restAction;
    private final Consumer<? super T> onSuccess;
    private final Consumer<? super Throwable> onFailure;
    private final BooleanSupplier checks;
    private final boolean shouldQueue;
    private final Route.CompiledRoute route;
    private final RequestBody body;
    private final Object rawBody;
    private final CaseInsensitiveMap<String, String> headers;
    private final long deadline;
    private final boolean priority;
    private final String localReason;
    private boolean done = false;
    private boolean isCancelled = false;

    public Request(RestActionImpl<T> restAction, Consumer<? super T> onSuccess, Consumer<? super Throwable> onFailure, BooleanSupplier checks, boolean shouldQueue, RequestBody body, Object rawBody, long deadline, boolean priority, Route.CompiledRoute route, CaseInsensitiveMap<String, String> headers) {
        this.deadline = deadline;
        this.priority = priority;
        this.restAction = restAction;
        this.onSuccess = onSuccess;
        this.onFailure = onFailure instanceof ContextException.ContextConsumer ? onFailure : (RestActionImpl.isPassContext() ? ContextException.here(onFailure) : onFailure);
        this.checks = checks;
        this.shouldQueue = shouldQueue;
        this.body = body;
        this.rawBody = rawBody;
        this.route = route;
        this.headers = headers;
        this.api = (JDAImpl)restAction.getJDA();
        this.localReason = ThreadLocalReason.getCurrent();
    }

    public void onSuccess(T successObj) {
        if (this.done) {
            return;
        }
        this.done = true;
        this.api.getCallbackPool().execute(() -> {
            block14: {
                try (ThreadLocalReason.Closable __ = ThreadLocalReason.closable(this.localReason);
                     CallbackContext ___ = CallbackContext.getInstance();){
                    this.onSuccess.accept(successObj);
                }
                catch (Throwable t2) {
                    RestActionImpl.LOG.error("Encountered error while processing success consumer", t2);
                    if (!(t2 instanceof Error)) break block14;
                    this.api.handleEvent(new ExceptionEvent(this.api, t2, true));
                    throw (Error)t2;
                }
            }
        });
    }

    public void onFailure(Response response) {
        if (response.code == 429) {
            this.onFailure(new RateLimitedException(this.route, response.retryAfter));
        } else {
            this.onFailure(ErrorResponseException.create(ErrorResponse.fromJSON(response.optObject().orElse(null)), response));
        }
    }

    public void onFailure(Throwable failException) {
        if (this.done) {
            return;
        }
        this.done = true;
        this.api.getCallbackPool().execute(() -> {
            block15: {
                try (ThreadLocalReason.Closable __ = ThreadLocalReason.closable(this.localReason);
                     CallbackContext ___ = CallbackContext.getInstance();){
                    this.onFailure.accept(failException);
                    if (failException instanceof Error) {
                        this.api.handleEvent(new ExceptionEvent(this.api, failException, false));
                    }
                }
                catch (Throwable t2) {
                    RestActionImpl.LOG.error("Encountered error while processing failure consumer", t2);
                    if (!(t2 instanceof Error)) break block15;
                    this.api.handleEvent(new ExceptionEvent(this.api, t2, true));
                    throw (Error)t2;
                }
            }
        });
    }

    public void onCancelled() {
        this.onFailure(new CancellationException("RestAction has been cancelled"));
    }

    public void onTimeout() {
        this.onFailure(new TimeoutException("RestAction has timed out"));
    }

    @Nonnull
    public JDAImpl getJDA() {
        return this.api;
    }

    @Nonnull
    public RestAction<T> getRestAction() {
        return this.restAction;
    }

    @Nonnull
    public Consumer<? super T> getOnSuccess() {
        return this.onSuccess;
    }

    @Nonnull
    public Consumer<? super Throwable> getOnFailure() {
        return this.onFailure;
    }

    public boolean isPriority() {
        return this.priority;
    }

    public boolean isSkipped() {
        if (this.isTimeout()) {
            this.onTimeout();
            return true;
        }
        boolean skip = this.runChecks();
        if (skip) {
            this.onCancelled();
        }
        return skip;
    }

    private boolean isTimeout() {
        return this.deadline > 0L && this.deadline < System.currentTimeMillis();
    }

    private boolean runChecks() {
        try {
            return this.isCancelled() || this.checks != null && !this.checks.getAsBoolean();
        }
        catch (Exception e2) {
            this.onFailure(e2);
            return true;
        }
    }

    @Nullable
    public CaseInsensitiveMap<String, String> getHeaders() {
        return this.headers;
    }

    @Nonnull
    public Route.CompiledRoute getRoute() {
        return this.route;
    }

    @Nullable
    public RequestBody getBody() {
        return this.body;
    }

    @Nullable
    public Object getRawBody() {
        return this.rawBody;
    }

    public boolean shouldQueue() {
        return this.shouldQueue;
    }

    public void cancel() {
        this.isCancelled = true;
    }

    public boolean isCancelled() {
        return this.isCancelled;
    }

    public void handleResponse(@Nonnull Response response) {
        this.restAction.handleResponse(response, this);
        this.api.handleEvent(new HttpRequestEvent(this, response));
    }
}

