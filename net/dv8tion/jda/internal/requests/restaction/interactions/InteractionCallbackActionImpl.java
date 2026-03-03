/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.internal.requests.restaction.interactions;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;
import javax.annotation.Nonnull;
import net.dv8tion.jda.api.exceptions.InteractionFailureException;
import net.dv8tion.jda.api.interactions.InteractionHook;
import net.dv8tion.jda.api.requests.Request;
import net.dv8tion.jda.api.requests.Response;
import net.dv8tion.jda.api.requests.RestAction;
import net.dv8tion.jda.api.requests.restaction.interactions.InteractionCallbackAction;
import net.dv8tion.jda.api.utils.data.DataObject;
import net.dv8tion.jda.internal.interactions.InteractionHookImpl;
import net.dv8tion.jda.internal.requests.Requester;
import net.dv8tion.jda.internal.requests.RestActionImpl;
import net.dv8tion.jda.internal.requests.Route;
import net.dv8tion.jda.internal.utils.IOUtil;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public abstract class InteractionCallbackActionImpl
extends RestActionImpl<InteractionHook>
implements InteractionCallbackAction {
    protected final InteractionHookImpl hook;
    protected final Map<String, InputStream> files = new HashMap<String, InputStream>();

    public InteractionCallbackActionImpl(InteractionHookImpl hook) {
        super(hook.getJDA(), Route.Interactions.CALLBACK.compile(hook.getInteraction().getId(), hook.getInteraction().getToken()));
        this.hook = hook;
    }

    protected abstract DataObject toData();

    @Override
    protected RequestBody finalizeData() {
        DataObject json = this.toData();
        if (this.files.isEmpty()) {
            return this.getRequestBody(json);
        }
        MultipartBody.Builder body = new MultipartBody.Builder().setType(MultipartBody.FORM);
        int i2 = 0;
        for (Map.Entry<String, InputStream> file : this.files.entrySet()) {
            RequestBody stream = IOUtil.createRequestBody(Requester.MEDIA_TYPE_OCTET, file.getValue());
            body.addFormDataPart("file" + i2++, file.getKey(), stream);
        }
        body.addFormDataPart("payload_json", json.toString());
        this.files.clear();
        return body.build();
    }

    @Override
    protected void handleSuccess(Response response, Request<InteractionHook> request) {
        this.hook.ready();
        request.onSuccess(this.hook);
    }

    @Override
    public void handleResponse(Response response, Request<InteractionHook> request) {
        if (!response.isOk()) {
            this.hook.fail(new InteractionFailureException());
        }
        super.handleResponse(response, request);
    }

    private IllegalStateException tryAck() {
        return this.hook.ack() ? new IllegalStateException("This interaction has already been acknowledged or replied to. You can only reply or acknowledge an interaction (or slash command) once!") : null;
    }

    @Override
    public void queue(Consumer<? super InteractionHook> success, Consumer<? super Throwable> failure) {
        IllegalStateException exception = this.tryAck();
        if (exception != null) {
            if (failure != null) {
                failure.accept(exception);
            } else {
                RestAction.getDefaultFailure().accept(exception);
            }
            return;
        }
        super.queue(success, failure);
    }

    @Override
    @Nonnull
    public CompletableFuture<InteractionHook> submit(boolean shouldQueue) {
        IllegalStateException exception = this.tryAck();
        if (exception != null) {
            CompletableFuture<InteractionHook> future = new CompletableFuture<InteractionHook>();
            future.completeExceptionally(exception);
            return future;
        }
        return super.submit(shouldQueue);
    }
}

