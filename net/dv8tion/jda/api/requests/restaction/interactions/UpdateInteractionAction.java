/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.requests.restaction.interactions;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Collection;
import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.interactions.components.ActionRow;
import net.dv8tion.jda.api.interactions.components.Component;
import net.dv8tion.jda.api.requests.restaction.interactions.InteractionCallbackAction;
import net.dv8tion.jda.api.utils.AttachmentOption;
import net.dv8tion.jda.internal.utils.Checks;

public interface UpdateInteractionAction
extends InteractionCallbackAction {
    @Nonnull
    public UpdateInteractionAction setContent(@Nullable String var1);

    @Nonnull
    @CheckReturnValue
    default public UpdateInteractionAction setEmbeds(MessageEmbed ... embeds) {
        Checks.noneNull(embeds, "MessageEmbed");
        return this.setEmbeds(Arrays.asList(embeds));
    }

    @Nonnull
    @CheckReturnValue
    public UpdateInteractionAction setEmbeds(@Nonnull Collection<? extends MessageEmbed> var1);

    @Nonnull
    @CheckReturnValue
    default public UpdateInteractionAction setActionRows(@Nonnull Collection<? extends ActionRow> rows) {
        Checks.noneNull(rows, "ActionRows");
        return this.setActionRows(rows.toArray(new ActionRow[0]));
    }

    @Nonnull
    @CheckReturnValue
    public UpdateInteractionAction setActionRows(ActionRow ... var1);

    @Nonnull
    @CheckReturnValue
    default public UpdateInteractionAction setActionRow(Component ... components) {
        return this.setActionRows(ActionRow.of(components));
    }

    @Nonnull
    @CheckReturnValue
    default public UpdateInteractionAction setActionRow(@Nonnull Collection<? extends Component> components) {
        return this.setActionRows(ActionRow.of(components));
    }

    @Nonnull
    @CheckReturnValue
    default public UpdateInteractionAction addFile(@Nonnull File file, AttachmentOption ... options) {
        Checks.notNull(file, "File");
        return this.addFile(file, file.getName(), options);
    }

    @Nonnull
    @CheckReturnValue
    default public UpdateInteractionAction addFile(@Nonnull File file, @Nonnull String name, AttachmentOption ... options) {
        try {
            Checks.notNull(file, "File");
            Checks.check(file.exists() && file.canRead(), "Provided file either does not exist or cannot be read from!");
            return this.addFile(new FileInputStream(file), name, options);
        }
        catch (FileNotFoundException e2) {
            throw new IllegalArgumentException(e2);
        }
    }

    @Nonnull
    @CheckReturnValue
    default public UpdateInteractionAction addFile(@Nonnull byte[] data, @Nonnull String name, AttachmentOption ... options) {
        Checks.notNull(data, "Data");
        return this.addFile(new ByteArrayInputStream(data), name, options);
    }

    @Nonnull
    @CheckReturnValue
    public UpdateInteractionAction addFile(@Nonnull InputStream var1, @Nonnull String var2, AttachmentOption ... var3);
}

