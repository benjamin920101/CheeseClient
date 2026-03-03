/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.requests.restaction;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;
import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.dv8tion.jda.api.entities.ISnowflake;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.interactions.components.ActionRow;
import net.dv8tion.jda.api.interactions.components.Component;
import net.dv8tion.jda.api.requests.RestAction;
import net.dv8tion.jda.api.utils.AttachmentOption;
import net.dv8tion.jda.internal.utils.Checks;

public interface WebhookMessageUpdateAction<T>
extends RestAction<T> {
    @Nonnull
    @CheckReturnValue
    public WebhookMessageUpdateAction<T> setContent(@Nullable String var1);

    @Nonnull
    @CheckReturnValue
    public WebhookMessageUpdateAction<T> setEmbeds(@Nonnull Collection<? extends MessageEmbed> var1);

    @Nonnull
    @CheckReturnValue
    default public WebhookMessageUpdateAction<T> setEmbeds(MessageEmbed ... embeds) {
        Checks.noneNull(embeds, "MessageEmbeds");
        return this.setEmbeds(Arrays.asList(embeds));
    }

    @Nonnull
    @CheckReturnValue
    default public WebhookMessageUpdateAction<T> setActionRow(Component ... components) {
        return this.setActionRows(ActionRow.of(components));
    }

    @Nonnull
    @CheckReturnValue
    default public WebhookMessageUpdateAction<T> setActionRow(@Nonnull Collection<? extends Component> components) {
        return this.setActionRows(ActionRow.of(components));
    }

    @Nonnull
    @CheckReturnValue
    default public WebhookMessageUpdateAction<T> setActionRows(@Nonnull Collection<? extends ActionRow> rows) {
        Checks.noneNull(rows, "ActionRows");
        return this.setActionRows(rows.toArray(new ActionRow[0]));
    }

    @Nonnull
    @CheckReturnValue
    public WebhookMessageUpdateAction<T> setActionRows(ActionRow ... var1);

    @Nonnull
    @CheckReturnValue
    public WebhookMessageUpdateAction<T> applyMessage(@Nonnull Message var1);

    @Nonnull
    @CheckReturnValue
    public WebhookMessageUpdateAction<T> addFile(@Nonnull InputStream var1, @Nonnull String var2, AttachmentOption ... var3);

    @Nonnull
    @CheckReturnValue
    default public WebhookMessageUpdateAction<T> addFile(@Nonnull byte[] data, @Nonnull String name, AttachmentOption ... options) {
        Checks.notNull(name, "Name");
        Checks.notNull(data, "Data");
        return this.addFile(new ByteArrayInputStream(data), name, options);
    }

    @Nonnull
    @CheckReturnValue
    default public WebhookMessageUpdateAction<T> addFile(@Nonnull File file, @Nonnull String name, AttachmentOption ... options) {
        Checks.notEmpty(name, "Name");
        Checks.notNull(file, "File");
        try {
            return this.addFile(new FileInputStream(file), name, options);
        }
        catch (FileNotFoundException e2) {
            throw new IllegalArgumentException(e2);
        }
    }

    @Nonnull
    @CheckReturnValue
    default public WebhookMessageUpdateAction<T> addFile(@Nonnull File file, AttachmentOption ... options) {
        Checks.notNull(file, "File");
        return this.addFile(file, file.getName(), options);
    }

    @Nonnull
    @CheckReturnValue
    public WebhookMessageUpdateAction<T> retainFilesById(@Nonnull Collection<String> var1);

    @Nonnull
    @CheckReturnValue
    default public WebhookMessageUpdateAction<T> retainFilesById(String ... ids) {
        Checks.notNull(ids, "IDs");
        return this.retainFilesById(Arrays.asList(ids));
    }

    @Nonnull
    @CheckReturnValue
    default public WebhookMessageUpdateAction<T> retainFilesById(long ... ids) {
        Checks.notNull(ids, "IDs");
        return this.retainFilesById(Arrays.stream(ids).mapToObj(Long::toUnsignedString).collect(Collectors.toList()));
    }

    @Nonnull
    @CheckReturnValue
    default public WebhookMessageUpdateAction<T> retainFiles(@Nonnull Collection<? extends Message.Attachment> attachments) {
        Checks.noneNull(attachments, "Attachments");
        return this.retainFilesById(attachments.stream().map(ISnowflake::getId).collect(Collectors.toList()));
    }
}

