/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.requests.restaction;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Collection;
import java.util.EnumSet;
import java.util.concurrent.TimeUnit;
import java.util.function.BiConsumer;
import java.util.function.BooleanSupplier;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.dv8tion.jda.api.entities.ISnowflake;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.interactions.components.ActionRow;
import net.dv8tion.jda.api.interactions.components.Component;
import net.dv8tion.jda.api.requests.RestAction;
import net.dv8tion.jda.api.utils.AllowedMentions;
import net.dv8tion.jda.api.utils.AttachmentOption;
import net.dv8tion.jda.api.utils.MiscUtil;
import net.dv8tion.jda.internal.requests.restaction.MessageActionImpl;
import net.dv8tion.jda.internal.utils.Checks;

public interface MessageAction
extends RestAction<Message>,
Appendable,
AllowedMentions<MessageAction> {
    public static void setDefaultMentions(@Nullable Collection<Message.MentionType> allowedMentions) {
        AllowedMentions.setDefaultMentions(allowedMentions);
    }

    @Nonnull
    public static EnumSet<Message.MentionType> getDefaultMentions() {
        return AllowedMentions.getDefaultMentions();
    }

    public static void setDefaultMentionRepliedUser(boolean mention) {
        AllowedMentions.setDefaultMentionRepliedUser(mention);
    }

    public static boolean isDefaultMentionRepliedUser() {
        return AllowedMentions.isDefaultMentionRepliedUser();
    }

    public static void setDefaultFailOnInvalidReply(boolean fail) {
        MessageActionImpl.setDefaultFailOnInvalidReply(fail);
    }

    public static boolean isDefaultFailOnInvalidReply() {
        return MessageActionImpl.isDefaultFailOnInvalidReply();
    }

    @Nonnull
    public MessageAction setCheck(@Nullable BooleanSupplier var1);

    @Nonnull
    public MessageAction timeout(long var1, @Nonnull TimeUnit var3);

    @Nonnull
    public MessageAction deadline(long var1);

    @Nonnull
    public MessageChannel getChannel();

    public boolean isEmpty();

    public boolean isEdit();

    @Nonnull
    @CheckReturnValue
    public MessageAction apply(@Nullable Message var1);

    @Nonnull
    @CheckReturnValue
    public MessageAction referenceById(long var1);

    @Nonnull
    @CheckReturnValue
    default public MessageAction referenceById(@Nonnull String messageId) {
        return this.referenceById(MiscUtil.parseSnowflake(messageId));
    }

    @Nonnull
    @CheckReturnValue
    default public MessageAction reference(@Nonnull Message message) {
        Checks.notNull(message, "Message");
        return this.referenceById(message.getIdLong());
    }

    @Override
    @Nonnull
    @CheckReturnValue
    public MessageAction mentionRepliedUser(boolean var1);

    @Nonnull
    @CheckReturnValue
    public MessageAction failOnInvalidReply(boolean var1);

    @Nonnull
    @CheckReturnValue
    public MessageAction tts(boolean var1);

    @Nonnull
    @CheckReturnValue
    public MessageAction reset();

    @Nonnull
    @CheckReturnValue
    public MessageAction nonce(@Nullable String var1);

    @Nonnull
    @CheckReturnValue
    public MessageAction content(@Nullable String var1);

    @Nonnull
    @CheckReturnValue
    public MessageAction embed(@Nullable MessageEmbed var1);

    @Override
    @Nonnull
    @CheckReturnValue
    default public MessageAction append(@Nonnull CharSequence csq) {
        return this.append(csq, 0, csq.length());
    }

    @Override
    @Nonnull
    @CheckReturnValue
    public MessageAction append(@Nullable CharSequence var1, int var2, int var3);

    @Override
    @Nonnull
    @CheckReturnValue
    public MessageAction append(char var1);

    @Nonnull
    @CheckReturnValue
    default public MessageAction appendFormat(@Nonnull String format, Object ... args) {
        return this.append(String.format(format, args));
    }

    @Nonnull
    @CheckReturnValue
    public MessageAction addFile(@Nonnull InputStream var1, @Nonnull String var2, AttachmentOption ... var3);

    @Nonnull
    @CheckReturnValue
    default public MessageAction addFile(@Nonnull byte[] data, @Nonnull String name, AttachmentOption ... options) {
        Checks.notNull(data, "Data");
        long maxSize = this.getJDA().getSelfUser().getAllowedFileSize();
        Checks.check((long)data.length <= maxSize, "File may not exceed the maximum file length of %d bytes!", (Object)maxSize);
        return this.addFile(new ByteArrayInputStream(data), name, options);
    }

    @Nonnull
    @CheckReturnValue
    default public MessageAction addFile(@Nonnull File file, AttachmentOption ... options) {
        Checks.notNull(file, "File");
        return this.addFile(file, file.getName(), options);
    }

    @Nonnull
    @CheckReturnValue
    public MessageAction addFile(@Nonnull File var1, @Nonnull String var2, AttachmentOption ... var3);

    @Nonnull
    @CheckReturnValue
    public MessageAction clearFiles();

    @Nonnull
    @CheckReturnValue
    public MessageAction clearFiles(@Nonnull BiConsumer<String, InputStream> var1);

    @Nonnull
    @CheckReturnValue
    public MessageAction clearFiles(@Nonnull Consumer<InputStream> var1);

    @Nonnull
    @CheckReturnValue
    public MessageAction retainFilesById(@Nonnull Collection<String> var1);

    @Nonnull
    @CheckReturnValue
    default public MessageAction retainFilesById(String ... ids) {
        Checks.notNull(ids, "IDs");
        return this.retainFilesById(Arrays.asList(ids));
    }

    @Nonnull
    @CheckReturnValue
    default public MessageAction retainFilesById(long ... ids) {
        Checks.notNull(ids, "IDs");
        return this.retainFilesById(Arrays.stream(ids).mapToObj(Long::toUnsignedString).collect(Collectors.toList()));
    }

    @Nonnull
    @CheckReturnValue
    default public MessageAction retainFiles(@Nonnull Collection<? extends Message.Attachment> attachments) {
        Checks.noneNull(attachments, "Attachments");
        return this.retainFilesById(attachments.stream().map(ISnowflake::getId).collect(Collectors.toList()));
    }

    @Nonnull
    @CheckReturnValue
    default public MessageAction setActionRows(@Nonnull Collection<? extends ActionRow> rows) {
        Checks.noneNull(rows, "ActionRows");
        return this.setActionRows(rows.toArray(new ActionRow[0]));
    }

    @Nonnull
    @CheckReturnValue
    public MessageAction setActionRows(ActionRow ... var1);

    @Nonnull
    @CheckReturnValue
    default public MessageAction setActionRow(@Nonnull Collection<? extends Component> components) {
        return this.setActionRows(ActionRow.of(components));
    }

    @Nonnull
    @CheckReturnValue
    default public MessageAction setActionRow(Component ... components) {
        return this.setActionRows(ActionRow.of(components));
    }

    @Nonnull
    @CheckReturnValue
    public MessageAction override(boolean var1);
}

