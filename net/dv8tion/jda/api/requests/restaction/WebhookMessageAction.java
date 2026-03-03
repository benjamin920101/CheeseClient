/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.requests.restaction;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.interactions.components.ActionRow;
import net.dv8tion.jda.api.interactions.components.Component;
import net.dv8tion.jda.api.requests.RestAction;
import net.dv8tion.jda.api.utils.AllowedMentions;
import net.dv8tion.jda.api.utils.AttachmentOption;
import net.dv8tion.jda.internal.utils.Checks;

public interface WebhookMessageAction<T>
extends RestAction<T>,
AllowedMentions<WebhookMessageAction<T>> {
    @Nonnull
    @CheckReturnValue
    public WebhookMessageAction<T> setEphemeral(boolean var1);

    @Nonnull
    @CheckReturnValue
    public WebhookMessageAction<T> setContent(@Nullable String var1);

    @Nonnull
    @CheckReturnValue
    public WebhookMessageAction<T> setTTS(boolean var1);

    @Nonnull
    @CheckReturnValue
    public WebhookMessageAction<T> addEmbeds(@Nonnull Collection<? extends MessageEmbed> var1);

    @Nonnull
    @CheckReturnValue
    default public WebhookMessageAction<T> addEmbeds(@Nonnull MessageEmbed embed, MessageEmbed ... other) {
        ArrayList<MessageEmbed> embeds = new ArrayList<MessageEmbed>();
        embeds.add(embed);
        Collections.addAll(embeds, other);
        return this.addEmbeds(embeds);
    }

    @Nonnull
    @CheckReturnValue
    public WebhookMessageAction<T> addFile(@Nonnull InputStream var1, @Nonnull String var2, AttachmentOption ... var3);

    @Nonnull
    @CheckReturnValue
    default public WebhookMessageAction<T> addFile(@Nonnull byte[] data, @Nonnull String name, AttachmentOption ... options) {
        Checks.notNull(name, "Name");
        Checks.notNull(data, "Data");
        return this.addFile(new ByteArrayInputStream(data), name, options);
    }

    @Nonnull
    @CheckReturnValue
    default public WebhookMessageAction<T> addFile(@Nonnull File file, @Nonnull String name, AttachmentOption ... options) {
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
    default public WebhookMessageAction<T> addFile(@Nonnull File file, AttachmentOption ... options) {
        Checks.notNull(file, "File");
        return this.addFile(file, file.getName(), options);
    }

    @Nonnull
    @CheckReturnValue
    default public WebhookMessageAction<T> addActionRow(Component ... components) {
        return this.addActionRows(ActionRow.of(components));
    }

    @Nonnull
    @CheckReturnValue
    default public WebhookMessageAction<T> addActionRow(@Nonnull Collection<? extends Component> components) {
        return this.addActionRows(ActionRow.of(components));
    }

    @Nonnull
    @CheckReturnValue
    default public WebhookMessageAction<T> addActionRows(@Nonnull Collection<? extends ActionRow> rows) {
        Checks.noneNull(rows, "ActionRows");
        return this.addActionRows(rows.toArray(new ActionRow[0]));
    }

    @Nonnull
    @CheckReturnValue
    public WebhookMessageAction<T> addActionRows(ActionRow ... var1);

    @Nonnull
    @CheckReturnValue
    public WebhookMessageAction<T> applyMessage(@Nonnull Message var1);
}

