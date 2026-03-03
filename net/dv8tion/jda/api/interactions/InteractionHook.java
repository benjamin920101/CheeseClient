/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.interactions;

import java.io.File;
import java.io.InputStream;
import java.util.Collection;
import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnull;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.WebhookClient;
import net.dv8tion.jda.api.interactions.Interaction;
import net.dv8tion.jda.api.interactions.components.ComponentLayout;
import net.dv8tion.jda.api.requests.RestAction;
import net.dv8tion.jda.api.requests.restaction.WebhookMessageUpdateAction;
import net.dv8tion.jda.api.utils.AttachmentOption;
import net.dv8tion.jda.internal.utils.Checks;

public interface InteractionHook
extends WebhookClient<Message> {
    @Nonnull
    public Interaction getInteraction();

    @Nonnull
    public InteractionHook setEphemeral(boolean var1);

    @Nonnull
    public JDA getJDA();

    @Nonnull
    @CheckReturnValue
    public RestAction<Message> retrieveOriginal();

    @Nonnull
    @CheckReturnValue
    default public WebhookMessageUpdateAction<Message> editOriginal(@Nonnull String content) {
        return this.editMessageById("@original", content);
    }

    @Nonnull
    @CheckReturnValue
    default public WebhookMessageUpdateAction<Message> editOriginalComponents(@Nonnull Collection<? extends ComponentLayout> components) {
        return this.editMessageComponentsById("@original", components);
    }

    @Nonnull
    @CheckReturnValue
    default public WebhookMessageUpdateAction<Message> editOriginalComponents(ComponentLayout ... components) {
        return this.editMessageComponentsById("@original", components);
    }

    @Nonnull
    @CheckReturnValue
    default public WebhookMessageUpdateAction<Message> editOriginalEmbeds(@Nonnull Collection<? extends MessageEmbed> embeds) {
        return this.editMessageEmbedsById("@original", embeds);
    }

    @Nonnull
    @CheckReturnValue
    default public WebhookMessageUpdateAction<Message> editOriginalEmbeds(MessageEmbed ... embeds) {
        return this.editMessageEmbedsById("@original", embeds);
    }

    @Nonnull
    @CheckReturnValue
    default public WebhookMessageUpdateAction<Message> editOriginal(@Nonnull Message message) {
        return this.editMessageById("@original", message);
    }

    @Nonnull
    @CheckReturnValue
    default public WebhookMessageUpdateAction<Message> editOriginalFormat(@Nonnull String format, Object ... args) {
        Checks.notNull(format, "Format String");
        return this.editOriginal(String.format(format, args));
    }

    @Nonnull
    @CheckReturnValue
    default public WebhookMessageUpdateAction<Message> editOriginal(@Nonnull InputStream data, @Nonnull String name, AttachmentOption ... options) {
        return this.editMessageById("@original", data, name, options);
    }

    @Nonnull
    @CheckReturnValue
    default public WebhookMessageUpdateAction<Message> editOriginal(@Nonnull File file, AttachmentOption ... options) {
        return this.editMessageById("@original", file, options);
    }

    @Nonnull
    @CheckReturnValue
    default public WebhookMessageUpdateAction<Message> editOriginal(@Nonnull File file, @Nonnull String name, AttachmentOption ... options) {
        return this.editMessageById("@original", file, name, options);
    }

    @Nonnull
    @CheckReturnValue
    default public WebhookMessageUpdateAction<Message> editOriginal(@Nonnull byte[] data, @Nonnull String name, AttachmentOption ... options) {
        return this.editMessageById("@original", data, name, options);
    }

    @Nonnull
    @CheckReturnValue
    default public RestAction<Void> deleteOriginal() {
        return this.deleteMessageById("@original");
    }
}

