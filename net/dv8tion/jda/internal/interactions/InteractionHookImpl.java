/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.internal.interactions;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Function;
import javax.annotation.Nonnull;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.interactions.Interaction;
import net.dv8tion.jda.api.interactions.InteractionHook;
import net.dv8tion.jda.api.requests.RestAction;
import net.dv8tion.jda.api.utils.MiscUtil;
import net.dv8tion.jda.api.utils.data.DataObject;
import net.dv8tion.jda.internal.JDAImpl;
import net.dv8tion.jda.internal.entities.AbstractWebhookClient;
import net.dv8tion.jda.internal.interactions.InteractionImpl;
import net.dv8tion.jda.internal.requests.Route;
import net.dv8tion.jda.internal.requests.restaction.TriggerRestAction;
import net.dv8tion.jda.internal.requests.restaction.WebhookMessageActionImpl;
import net.dv8tion.jda.internal.requests.restaction.WebhookMessageUpdateActionImpl;
import net.dv8tion.jda.internal.utils.Checks;
import net.dv8tion.jda.internal.utils.JDALogger;

public class InteractionHookImpl
extends AbstractWebhookClient<Message>
implements InteractionHook {
    public static final String TIMEOUT_MESSAGE = "Timed out waiting for interaction acknowledgement";
    private final InteractionImpl interaction;
    private final List<TriggerRestAction<?>> readyCallbacks = new LinkedList();
    private final Future<?> timeoutHandle;
    private final ReentrantLock mutex = new ReentrantLock();
    private Exception exception;
    private boolean isReady;
    private boolean ephemeral;
    private volatile boolean isAck;

    public InteractionHookImpl(@Nonnull InteractionImpl interaction, @Nonnull JDA api2) {
        super(api2.getSelfUser().getApplicationIdLong(), interaction.getToken(), api2);
        this.interaction = interaction;
        this.timeoutHandle = api2.getGatewayPool().schedule(() -> this.fail(new TimeoutException(TIMEOUT_MESSAGE)), 10L, TimeUnit.SECONDS);
    }

    public synchronized boolean ack() {
        boolean wasAck = this.isAck;
        this.isAck = true;
        return wasAck;
    }

    public synchronized boolean isAck() {
        return this.isAck;
    }

    public void ready() {
        MiscUtil.locked(this.mutex, () -> {
            this.timeoutHandle.cancel(false);
            this.isReady = true;
            this.readyCallbacks.forEach(TriggerRestAction::run);
        });
    }

    public void fail(Exception exception) {
        MiscUtil.locked(this.mutex, () -> {
            if (!this.isReady && this.exception == null) {
                this.exception = exception;
                if (!this.readyCallbacks.isEmpty()) {
                    if (exception instanceof TimeoutException) {
                        JDALogger.getLog(InteractionHook.class).warn("Up to {} Interaction Followup Messages Timed out! Did you forget to acknowledge the interaction?", (Object)this.readyCallbacks.size());
                    }
                    this.readyCallbacks.forEach(callback -> callback.fail(exception));
                }
            }
        });
    }

    private <T extends TriggerRestAction<R>, R> T onReady(T runnable) {
        return (T)MiscUtil.locked(this.mutex, () -> {
            if (this.isReady) {
                runnable.run();
            } else if (this.exception != null) {
                runnable.fail(this.exception);
            } else {
                this.readyCallbacks.add(runnable);
            }
            return runnable;
        });
    }

    @Override
    @Nonnull
    public Interaction getInteraction() {
        return this.interaction;
    }

    @Override
    @Nonnull
    public InteractionHook setEphemeral(boolean ephemeral) {
        this.ephemeral = ephemeral;
        return this;
    }

    @Override
    @Nonnull
    public JDA getJDA() {
        return this.api;
    }

    @Override
    @Nonnull
    public RestAction<Message> retrieveOriginal() {
        JDAImpl jda = (JDAImpl)this.getJDA();
        Route.CompiledRoute route = Route.Interactions.GET_ORIGINAL.compile(jda.getSelfUser().getApplicationId(), this.interaction.getToken());
        return this.onReady(new TriggerRestAction<Message>((JDA)jda, route, (response, request) -> jda.getEntityBuilder().createMessage(response.getObject(), this.getInteraction().getMessageChannel(), false)));
    }

    @Override
    @Nonnull
    public WebhookMessageActionImpl<Message> sendRequest() {
        Route.CompiledRoute route = Route.Interactions.CREATE_FOLLOWUP.compile(this.getJDA().getSelfUser().getApplicationId(), this.interaction.getToken());
        route = route.withQueryParams("wait", "true");
        Function<DataObject, Message> transform = json -> ((JDAImpl)this.api).getEntityBuilder().createMessage((DataObject)json, this.getInteraction().getMessageChannel(), false);
        return this.onReady(new WebhookMessageActionImpl<Message>(this.getJDA(), this.interaction.getMessageChannel(), route, transform)).setEphemeral(this.ephemeral);
    }

    @Override
    @Nonnull
    public WebhookMessageUpdateActionImpl<Message> editRequest(String messageId) {
        if (!"@original".equals(messageId)) {
            Checks.isSnowflake(messageId);
        }
        Route.CompiledRoute route = Route.Interactions.EDIT_FOLLOWUP.compile(this.getJDA().getSelfUser().getApplicationId(), this.interaction.getToken(), messageId);
        route = route.withQueryParams("wait", "true");
        Function<DataObject, Message> transform = json -> ((JDAImpl)this.api).getEntityBuilder().createMessage((DataObject)json, this.getInteraction().getMessageChannel(), false);
        return this.onReady(new WebhookMessageUpdateActionImpl<Message>(this.getJDA(), route, transform));
    }

    @Override
    @Nonnull
    public RestAction<Void> deleteMessageById(@Nonnull String messageId) {
        if (!"@original".equals(messageId)) {
            Checks.isSnowflake(messageId);
        }
        Route.CompiledRoute route = Route.Interactions.DELETE_FOLLOWUP.compile(this.getJDA().getSelfUser().getApplicationId(), this.interaction.getToken(), messageId);
        return this.onReady(new TriggerRestAction(this.getJDA(), route));
    }
}

