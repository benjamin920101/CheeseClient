/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.internal.entities;

import java.io.InputStream;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.Nonnull;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.WebhookClient;
import net.dv8tion.jda.api.interactions.components.ActionRow;
import net.dv8tion.jda.api.interactions.components.ComponentLayout;
import net.dv8tion.jda.api.requests.RestAction;
import net.dv8tion.jda.api.requests.restaction.WebhookMessageUpdateAction;
import net.dv8tion.jda.api.utils.AttachmentOption;
import net.dv8tion.jda.internal.requests.RestActionImpl;
import net.dv8tion.jda.internal.requests.Route;
import net.dv8tion.jda.internal.requests.restaction.WebhookMessageActionImpl;
import net.dv8tion.jda.internal.requests.restaction.WebhookMessageUpdateActionImpl;
import net.dv8tion.jda.internal.utils.Checks;

public abstract class AbstractWebhookClient<T>
implements WebhookClient<T> {
    protected final long id;
    protected final JDA api;
    protected String token;

    protected AbstractWebhookClient(long webhookId, String webhookToken, JDA api2) {
        this.id = webhookId;
        this.token = webhookToken;
        this.api = api2;
    }

    public abstract WebhookMessageActionImpl<T> sendRequest();

    public abstract WebhookMessageUpdateActionImpl<T> editRequest(String var1);

    @Override
    @Nonnull
    public WebhookMessageActionImpl<T> sendMessage(@Nonnull String content) {
        return this.sendRequest().setContent(content);
    }

    @Override
    @Nonnull
    public WebhookMessageActionImpl<T> sendMessageEmbeds(@Nonnull Collection<? extends MessageEmbed> embeds) {
        return this.sendRequest().addEmbeds(embeds);
    }

    @Override
    @Nonnull
    public WebhookMessageActionImpl<T> sendMessage(@Nonnull Message message) {
        return this.sendRequest().applyMessage(message);
    }

    @Override
    @Nonnull
    public WebhookMessageActionImpl<T> sendFile(@Nonnull InputStream data, @Nonnull String name, AttachmentOption ... options) {
        return this.sendRequest().addFile(data, name, options);
    }

    @Override
    @Nonnull
    public WebhookMessageUpdateActionImpl<T> editMessageById(@Nonnull String messageId, @Nonnull String content) {
        return (WebhookMessageUpdateActionImpl)this.editRequest(messageId).setContent(content);
    }

    @Override
    @Nonnull
    public WebhookMessageUpdateAction<T> editMessageComponentsById(@Nonnull String messageId, @Nonnull Collection<? extends ComponentLayout> components) {
        Checks.noneNull(components, "Components");
        if (components.stream().anyMatch(x2 -> !(x2 instanceof ActionRow))) {
            throw new UnsupportedOperationException("The provided component layout is not supported");
        }
        List actionRows = components.stream().map(ActionRow.class::cast).collect(Collectors.toList());
        return this.editRequest(messageId).setActionRows(actionRows);
    }

    @Override
    @Nonnull
    public WebhookMessageUpdateActionImpl<T> editMessageEmbedsById(@Nonnull String messageId, @Nonnull Collection<? extends MessageEmbed> embeds) {
        return (WebhookMessageUpdateActionImpl)this.editRequest(messageId).setEmbeds(embeds);
    }

    @Override
    @Nonnull
    public WebhookMessageUpdateActionImpl<T> editMessageById(@Nonnull String messageId, @Nonnull Message message) {
        return (WebhookMessageUpdateActionImpl)this.editRequest(messageId).applyMessage(message);
    }

    @Override
    @Nonnull
    public WebhookMessageUpdateActionImpl<T> editMessageById(@Nonnull String messageId, @Nonnull InputStream data, @Nonnull String name, AttachmentOption ... options) {
        return (WebhookMessageUpdateActionImpl)this.editRequest(messageId).addFile(data, name, options);
    }

    @Override
    @Nonnull
    public RestAction<Void> deleteMessageById(@Nonnull String messageId) {
        Checks.isSnowflake(messageId);
        Route.CompiledRoute route = Route.Webhooks.EXECUTE_WEBHOOK_DELETE.compile(Long.toUnsignedString(this.id), this.token, messageId);
        return new RestActionImpl<Void>(this.api, route);
    }
}

