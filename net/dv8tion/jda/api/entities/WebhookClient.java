/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.entities;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnull;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.interactions.components.ComponentLayout;
import net.dv8tion.jda.api.requests.RestAction;
import net.dv8tion.jda.api.requests.restaction.WebhookMessageAction;
import net.dv8tion.jda.api.requests.restaction.WebhookMessageUpdateAction;
import net.dv8tion.jda.api.utils.AttachmentOption;
import net.dv8tion.jda.internal.utils.Checks;

public interface WebhookClient<T> {
    @Nonnull
    @CheckReturnValue
    public WebhookMessageAction<T> sendMessage(@Nonnull String var1);

    @Nonnull
    @CheckReturnValue
    public WebhookMessageAction<T> sendMessage(@Nonnull Message var1);

    @Nonnull
    @CheckReturnValue
    default public WebhookMessageAction<T> sendMessageFormat(@Nonnull String format, Object ... args) {
        Checks.notNull(format, "Format String");
        return this.sendMessage(String.format(format, args));
    }

    @Nonnull
    @CheckReturnValue
    public WebhookMessageAction<T> sendMessageEmbeds(@Nonnull Collection<? extends MessageEmbed> var1);

    @Nonnull
    @CheckReturnValue
    default public WebhookMessageAction<T> sendMessageEmbeds(@Nonnull MessageEmbed embed, MessageEmbed ... embeds) {
        Checks.notNull(embed, "MessageEmbeds");
        Checks.noneNull(embeds, "MessageEmbeds");
        ArrayList<MessageEmbed> embedList = new ArrayList<MessageEmbed>();
        embedList.add(embed);
        Collections.addAll(embedList, embeds);
        return this.sendMessageEmbeds(embedList);
    }

    @Nonnull
    @CheckReturnValue
    public WebhookMessageAction<T> sendFile(@Nonnull InputStream var1, @Nonnull String var2, AttachmentOption ... var3);

    @Nonnull
    @CheckReturnValue
    default public WebhookMessageAction<T> sendFile(@Nonnull File file, AttachmentOption ... options) {
        Checks.notNull(file, "File");
        return this.sendFile(file, file.getName(), options);
    }

    @Nonnull
    @CheckReturnValue
    default public WebhookMessageAction<T> sendFile(@Nonnull File file, @Nonnull String name, AttachmentOption ... options) {
        Checks.notNull(file, "File");
        Checks.check(file.exists() && file.canRead(), "Provided file doesn't exist or cannot be read!");
        Checks.notNull(name, "Name");
        try {
            return this.sendFile(new FileInputStream(file), name, options);
        }
        catch (FileNotFoundException ex2) {
            throw new IllegalArgumentException(ex2);
        }
    }

    @Nonnull
    @CheckReturnValue
    default public WebhookMessageAction<T> sendFile(@Nonnull byte[] data, @Nonnull String name, AttachmentOption ... options) {
        Checks.notNull(data, "Data");
        Checks.notNull(name, "Name");
        return this.sendFile(new ByteArrayInputStream(data), name, options);
    }

    @Nonnull
    @CheckReturnValue
    public WebhookMessageUpdateAction<T> editMessageById(@Nonnull String var1, @Nonnull String var2);

    @Nonnull
    @CheckReturnValue
    default public WebhookMessageUpdateAction<T> editMessageById(long messageId, @Nonnull String content) {
        return this.editMessageById(Long.toUnsignedString(messageId), content);
    }

    @Nonnull
    @CheckReturnValue
    public WebhookMessageUpdateAction<T> editMessageById(@Nonnull String var1, @Nonnull Message var2);

    @Nonnull
    @CheckReturnValue
    default public WebhookMessageUpdateAction<T> editMessageById(long messageId, Message message) {
        return this.editMessageById(Long.toUnsignedString(messageId), message);
    }

    @Nonnull
    @CheckReturnValue
    default public WebhookMessageUpdateAction<T> editMessageFormatById(@Nonnull String messageId, @Nonnull String format, Object ... args) {
        Checks.notNull(format, "Format String");
        return this.editMessageById(messageId, String.format(format, args));
    }

    @Nonnull
    @CheckReturnValue
    default public WebhookMessageUpdateAction<T> editMessageFormatById(long messageId, @Nonnull String format, Object ... args) {
        return this.editMessageFormatById(Long.toUnsignedString(messageId), format, args);
    }

    @Nonnull
    @CheckReturnValue
    public WebhookMessageUpdateAction<T> editMessageEmbedsById(@Nonnull String var1, @Nonnull Collection<? extends MessageEmbed> var2);

    @Nonnull
    @CheckReturnValue
    default public WebhookMessageUpdateAction<T> editMessageEmbedsById(long messageId, @Nonnull Collection<? extends MessageEmbed> embeds) {
        return this.editMessageEmbedsById(Long.toUnsignedString(messageId), embeds);
    }

    @Nonnull
    @CheckReturnValue
    default public WebhookMessageUpdateAction<T> editMessageEmbedsById(@Nonnull String messageId, MessageEmbed ... embeds) {
        Checks.noneNull(embeds, "MessageEmbeds");
        return this.editMessageEmbedsById(messageId, Arrays.asList(embeds));
    }

    @Nonnull
    @CheckReturnValue
    default public WebhookMessageUpdateAction<T> editMessageEmbedsById(long messageId, MessageEmbed ... embeds) {
        return this.editMessageEmbedsById(Long.toUnsignedString(messageId), embeds);
    }

    @Nonnull
    @CheckReturnValue
    public WebhookMessageUpdateAction<T> editMessageComponentsById(@Nonnull String var1, @Nonnull Collection<? extends ComponentLayout> var2);

    @Nonnull
    @CheckReturnValue
    default public WebhookMessageUpdateAction<T> editMessageComponentsById(long messageId, @Nonnull Collection<? extends ComponentLayout> components) {
        return this.editMessageComponentsById(Long.toUnsignedString(messageId), components);
    }

    @Nonnull
    @CheckReturnValue
    default public WebhookMessageUpdateAction<T> editMessageComponentsById(@Nonnull String messageId, ComponentLayout ... components) {
        Checks.noneNull(components, "ComponentLayouts");
        return this.editMessageComponentsById(messageId, Arrays.asList(components));
    }

    @Nonnull
    @CheckReturnValue
    default public WebhookMessageUpdateAction<T> editMessageComponentsById(long messageId, ComponentLayout ... components) {
        return this.editMessageComponentsById(Long.toUnsignedString(messageId), components);
    }

    @Nonnull
    @CheckReturnValue
    public WebhookMessageUpdateAction<T> editMessageById(@Nonnull String var1, @Nonnull InputStream var2, @Nonnull String var3, AttachmentOption ... var4);

    @Nonnull
    @CheckReturnValue
    default public WebhookMessageUpdateAction<T> editMessageById(@Nonnull String messageId, @Nonnull File file, AttachmentOption ... options) {
        Checks.notNull(file, "File");
        return this.editMessageById(messageId, file, file.getName(), options);
    }

    @Nonnull
    @CheckReturnValue
    default public WebhookMessageUpdateAction<T> editMessageById(@Nonnull String messageId, @Nonnull File file, @Nonnull String name, AttachmentOption ... options) {
        Checks.notNull(file, "File");
        Checks.check(file.exists() && file.canRead(), "Provided file doesn't exist or cannot be read!");
        Checks.notNull(name, "Name");
        try {
            return this.editMessageById(messageId, (InputStream)new FileInputStream(file), name, options);
        }
        catch (FileNotFoundException ex2) {
            throw new IllegalArgumentException(ex2);
        }
    }

    @Nonnull
    @CheckReturnValue
    default public WebhookMessageUpdateAction<T> editMessageById(@Nonnull String messageId, @Nonnull byte[] data, @Nonnull String name, AttachmentOption ... options) {
        Checks.notNull(data, "Data");
        Checks.notNull(name, "Name");
        return this.editMessageById(messageId, (InputStream)new ByteArrayInputStream(data), name, options);
    }

    @Nonnull
    @CheckReturnValue
    default public WebhookMessageUpdateAction<T> editMessageById(long messageId, @Nonnull InputStream data, @Nonnull String name, AttachmentOption ... options) {
        return this.editMessageById(Long.toUnsignedString(messageId), data, name, options);
    }

    @Nonnull
    @CheckReturnValue
    default public WebhookMessageUpdateAction<T> editMessageById(long messageId, @Nonnull File file, AttachmentOption ... options) {
        return this.editMessageById(Long.toUnsignedString(messageId), file, options);
    }

    @Nonnull
    @CheckReturnValue
    default public WebhookMessageUpdateAction<T> editMessageById(long messageId, @Nonnull File file, @Nonnull String name, AttachmentOption ... options) {
        return this.editMessageById(Long.toUnsignedString(messageId), file, name, options);
    }

    @Nonnull
    @CheckReturnValue
    default public WebhookMessageUpdateAction<T> editMessageById(long messageId, @Nonnull byte[] data, @Nonnull String name, AttachmentOption ... options) {
        return this.editMessageById(Long.toUnsignedString(messageId), data, name, options);
    }

    @Nonnull
    @CheckReturnValue
    public RestAction<Void> deleteMessageById(@Nonnull String var1);

    @Nonnull
    @CheckReturnValue
    default public RestAction<Void> deleteMessageById(long messageId) {
        return this.deleteMessageById(Long.toUnsignedString(messageId));
    }
}

