/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.internal.requests.restaction;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.function.BiConsumer;
import java.util.function.BooleanSupplier;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.entities.EmbedType;
import net.dv8tion.jda.api.entities.GuildChannel;
import net.dv8tion.jda.api.entities.IMentionable;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.exceptions.InsufficientPermissionException;
import net.dv8tion.jda.api.exceptions.MissingAccessException;
import net.dv8tion.jda.api.interactions.components.ActionRow;
import net.dv8tion.jda.api.requests.Request;
import net.dv8tion.jda.api.requests.Response;
import net.dv8tion.jda.api.requests.restaction.MessageAction;
import net.dv8tion.jda.api.utils.AttachmentOption;
import net.dv8tion.jda.api.utils.data.DataArray;
import net.dv8tion.jda.api.utils.data.DataObject;
import net.dv8tion.jda.internal.requests.Method;
import net.dv8tion.jda.internal.requests.Requester;
import net.dv8tion.jda.internal.requests.RestActionImpl;
import net.dv8tion.jda.internal.requests.Route;
import net.dv8tion.jda.internal.utils.AllowedMentionsImpl;
import net.dv8tion.jda.internal.utils.Checks;
import net.dv8tion.jda.internal.utils.Helpers;
import net.dv8tion.jda.internal.utils.IOUtil;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class MessageActionImpl
extends RestActionImpl<Message>
implements MessageAction {
    private static final String CONTENT_TOO_BIG = Helpers.format("A message may not exceed %d characters. Please limit your input!", 2000);
    protected static boolean defaultFailOnInvalidReply = false;
    protected final Map<String, InputStream> files = new HashMap<String, InputStream>();
    protected final Set<InputStream> ownedResources = new HashSet<InputStream>();
    protected final StringBuilder content;
    protected final MessageChannel channel;
    protected final AllowedMentionsImpl allowedMentions = new AllowedMentionsImpl();
    protected List<ActionRow> components;
    protected List<String> retainedAttachments;
    protected MessageEmbed embed = null;
    protected String nonce = null;
    protected boolean tts = false;
    protected boolean override = false;
    protected boolean failOnInvalidReply = defaultFailOnInvalidReply;
    protected long messageReference;

    public static void setDefaultFailOnInvalidReply(boolean fail) {
        defaultFailOnInvalidReply = fail;
    }

    public static boolean isDefaultFailOnInvalidReply() {
        return defaultFailOnInvalidReply;
    }

    public MessageActionImpl(JDA api2, Route.CompiledRoute route, MessageChannel channel) {
        super(api2, route);
        this.content = new StringBuilder();
        this.channel = channel;
    }

    public MessageActionImpl(JDA api2, Route.CompiledRoute route, MessageChannel channel, StringBuilder contentBuilder) {
        super(api2, route);
        Checks.check(contentBuilder.length() <= 2000, "Cannot build a Message with more than %d characters. Please limit your input.", (Object)2000);
        this.content = contentBuilder;
        this.channel = channel;
    }

    @Override
    @Nonnull
    public MessageAction setCheck(BooleanSupplier checks) {
        return (MessageAction)super.setCheck(checks);
    }

    @Override
    @Nonnull
    public MessageAction timeout(long timeout, @Nonnull TimeUnit unit) {
        return (MessageAction)super.timeout(timeout, unit);
    }

    @Override
    @Nonnull
    public MessageAction deadline(long timestamp) {
        return (MessageAction)super.deadline(timestamp);
    }

    @Override
    @Nonnull
    public MessageChannel getChannel() {
        return this.channel;
    }

    @Override
    public boolean isEmpty() {
        return !this.isEdit() && Helpers.isBlank(this.content) && (this.embed == null || this.embed.isEmpty() || !this.hasPermission(Permission.MESSAGE_EMBED_LINKS));
    }

    @Override
    public boolean isEdit() {
        return this.finalizeRoute().getMethod() == Method.PATCH;
    }

    @Override
    @Nonnull
    @CheckReturnValue
    public MessageActionImpl apply(Message message) {
        if (message == null || message.getType().isSystem()) {
            return this;
        }
        List<MessageEmbed> embeds = message.getEmbeds();
        if (embeds != null && !embeds.isEmpty() && embeds.get(0).getType() == EmbedType.RICH) {
            this.embed(embeds.get(0));
        }
        this.files.clear();
        this.components = new ArrayList<ActionRow>();
        this.components.addAll(message.getActionRows());
        this.allowedMentions.applyMessage(message);
        String content = message.getContentRaw();
        return this.content(content).tts(message.isTTS());
    }

    @Override
    @Nonnull
    public MessageActionImpl referenceById(long messageId) {
        this.messageReference = messageId;
        return this;
    }

    @Override
    @Nonnull
    public MessageAction failOnInvalidReply(boolean fail) {
        this.failOnInvalidReply = fail;
        return this;
    }

    @Override
    @Nonnull
    @CheckReturnValue
    public MessageActionImpl tts(boolean isTTS) {
        this.tts = isTTS;
        return this;
    }

    @Override
    @Nonnull
    @CheckReturnValue
    public MessageActionImpl reset() {
        return this.content(null).nonce(null).embed(null).tts(false).override(false).clearFiles();
    }

    @Override
    @Nonnull
    @CheckReturnValue
    public MessageActionImpl nonce(String nonce) {
        this.nonce = nonce;
        return this;
    }

    @Override
    @Nonnull
    @CheckReturnValue
    public MessageActionImpl content(String content) {
        if (content == null || content.isEmpty()) {
            this.content.setLength(0);
        } else if (content.length() <= 2000) {
            this.content.replace(0, this.content.length(), content);
        } else {
            throw new IllegalArgumentException(CONTENT_TOO_BIG);
        }
        return this;
    }

    @Override
    @Nonnull
    @CheckReturnValue
    public MessageActionImpl embed(MessageEmbed embed) {
        if (embed != null) {
            Checks.check(embed.isSendable(), "Provided Message contains an empty embed or an embed with a length greater than %d characters, which is the max for bot accounts!", (Object)6000);
        }
        this.embed = embed;
        return this;
    }

    @Override
    @Nonnull
    @CheckReturnValue
    public MessageActionImpl append(CharSequence csq, int start, int end) {
        if (this.content.length() + end - start > 2000) {
            throw new IllegalArgumentException("A message may not exceed 2000 characters. Please limit your input!");
        }
        this.content.append(csq, start, end);
        return this;
    }

    @Override
    @Nonnull
    @CheckReturnValue
    public MessageActionImpl append(char c2) {
        if (this.content.length() == 2000) {
            throw new IllegalArgumentException("A message may not exceed 2000 characters. Please limit your input!");
        }
        this.content.append(c2);
        return this;
    }

    @Override
    @Nonnull
    @CheckReturnValue
    public MessageActionImpl addFile(@Nonnull InputStream data, @Nonnull String name, AttachmentOption ... options) {
        Checks.notNull(data, "Data");
        Checks.notBlank(name, "Name");
        Checks.noneNull((Object[])options, "Options");
        this.checkFileAmount();
        this.checkPermission(Permission.MESSAGE_ATTACH_FILES);
        name = this.applyOptions(name, options);
        this.files.put(name, data);
        return this;
    }

    @Override
    @Nonnull
    @CheckReturnValue
    public MessageActionImpl addFile(@Nonnull File file, @Nonnull String name, AttachmentOption ... options) {
        Checks.notNull(file, "File");
        Checks.noneNull((Object[])options, "Options");
        Checks.check(file.exists() && file.canRead(), "Provided file either does not exist or cannot be read from!");
        long maxSize = this.getMaxFileSize();
        Checks.check(file.length() <= maxSize, "File may not exceed the maximum file length of %d bytes!", (Object)maxSize);
        try {
            FileInputStream data = new FileInputStream(file);
            this.ownedResources.add(data);
            name = this.applyOptions(name, options);
            return this.addFile(data, name, new AttachmentOption[0]);
        }
        catch (FileNotFoundException e2) {
            throw new IllegalArgumentException(e2);
        }
    }

    @Override
    @Nonnull
    @CheckReturnValue
    public MessageActionImpl clearFiles() {
        this.files.clear();
        this.clearResources();
        return this;
    }

    @Override
    @Nonnull
    @CheckReturnValue
    public MessageActionImpl clearFiles(@Nonnull BiConsumer<String, InputStream> finalizer) {
        Checks.notNull(finalizer, "Finalizer");
        Iterator<Map.Entry<String, InputStream>> it2 = this.files.entrySet().iterator();
        while (it2.hasNext()) {
            Map.Entry<String, InputStream> entry = it2.next();
            finalizer.accept(entry.getKey(), entry.getValue());
            it2.remove();
        }
        this.clearResources();
        return this;
    }

    @Override
    @Nonnull
    @CheckReturnValue
    public MessageActionImpl clearFiles(@Nonnull Consumer<InputStream> finalizer) {
        Checks.notNull(finalizer, "Finalizer");
        Iterator<InputStream> it2 = this.files.values().iterator();
        while (it2.hasNext()) {
            finalizer.accept(it2.next());
            it2.remove();
        }
        this.clearResources();
        return this;
    }

    @Override
    @Nonnull
    public MessageAction retainFilesById(@Nonnull Collection<String> ids) {
        if (!this.isEdit()) {
            return this;
        }
        if (this.retainedAttachments == null) {
            this.retainedAttachments = new ArrayList<String>();
        }
        this.retainedAttachments.addAll(ids);
        return this;
    }

    @Override
    @Nonnull
    public MessageActionImpl setActionRows(ActionRow ... rows) {
        Checks.noneNull(rows, "ActionRows");
        if (this.components == null) {
            this.components = new ArrayList<ActionRow>();
        }
        Checks.check(rows.length <= 5, "Can only have 5 action rows per message!");
        this.components.clear();
        Collections.addAll(this.components, rows);
        return this;
    }

    @Override
    @Nonnull
    @CheckReturnValue
    public MessageActionImpl override(boolean bool) {
        this.override = this.isEdit() && bool;
        return this;
    }

    @Override
    @Nonnull
    public MessageAction mentionRepliedUser(boolean mention) {
        this.allowedMentions.mentionRepliedUser(mention);
        return this;
    }

    @Override
    @Nonnull
    public MessageAction allowedMentions(@Nullable Collection<Message.MentionType> allowedMentions) {
        this.allowedMentions.allowedMentions((Collection)allowedMentions);
        return this;
    }

    @Override
    @Nonnull
    public MessageAction mention(IMentionable ... mentions) {
        this.allowedMentions.mention(mentions);
        return this;
    }

    @Override
    @Nonnull
    public MessageAction mentionUsers(String ... userIds) {
        this.allowedMentions.mentionUsers(userIds);
        return this;
    }

    @Override
    @Nonnull
    public MessageAction mentionRoles(String ... roleIds) {
        this.allowedMentions.mentionRoles(roleIds);
        return this;
    }

    private String applyOptions(String name, AttachmentOption[] options) {
        for (AttachmentOption opt : options) {
            if (opt != AttachmentOption.SPOILER) continue;
            name = "SPOILER_" + name;
            break;
        }
        return name;
    }

    private void clearResources() {
        for (InputStream ownedResource : this.ownedResources) {
            try {
                ownedResource.close();
            }
            catch (IOException ex2) {
                if (ex2.getMessage().toLowerCase().contains("closed")) continue;
                LOG.error("Encountered IOException trying to close owned resource", ex2);
            }
        }
        this.ownedResources.clear();
    }

    private long getMaxFileSize() {
        if (this.channel.getType().isGuild()) {
            return ((GuildChannel)((Object)this.channel)).getGuild().getMaxFileSize();
        }
        return this.getJDA().getSelfUser().getAllowedFileSize();
    }

    protected RequestBody asMultipart() {
        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
        int index = 0;
        for (Map.Entry<String, InputStream> entry : this.files.entrySet()) {
            RequestBody body = IOUtil.createRequestBody(Requester.MEDIA_TYPE_OCTET, entry.getValue());
            builder.addFormDataPart("file" + index++, entry.getKey(), body);
        }
        if (this.messageReference != 0L || this.components != null || this.retainedAttachments != null || !this.isEmpty()) {
            builder.addFormDataPart("payload_json", this.getJSON().toString());
        }
        this.files.clear();
        this.ownedResources.clear();
        return builder.build();
    }

    protected RequestBody asJSON() {
        return RequestBody.create(Requester.MEDIA_TYPE_JSON, this.getJSON().toJson());
    }

    protected DataObject getJSON() {
        DataObject obj = DataObject.empty();
        if (this.override) {
            if (this.embed == null) {
                obj.putNull("embed");
            } else {
                obj.put("embed", this.embed);
            }
            if (this.content.length() == 0) {
                obj.putNull("content");
            } else {
                obj.put("content", this.content.toString());
            }
            if (this.nonce == null) {
                obj.putNull("nonce");
            } else {
                obj.put("nonce", this.nonce);
            }
            if (this.components == null) {
                obj.putNull("components");
            } else {
                obj.put("components", DataArray.fromCollection(this.components));
            }
            if (this.retainedAttachments != null) {
                obj.put("attachments", DataArray.fromCollection(this.retainedAttachments.stream().map((? super T id2) -> DataObject.empty().put("id", id2)).collect(Collectors.toList())));
            } else {
                obj.put("attachments", DataArray.empty());
            }
        } else {
            if (this.embed != null) {
                obj.put("embed", this.embed);
            }
            if (this.content.length() > 0) {
                obj.put("content", this.content.toString());
            }
            if (this.nonce != null) {
                obj.put("nonce", this.nonce);
            }
            if (this.components != null) {
                obj.put("components", DataArray.fromCollection(this.components));
            }
            if (this.retainedAttachments != null) {
                obj.put("attachments", DataArray.fromCollection(this.retainedAttachments.stream().map((? super T id2) -> DataObject.empty().put("id", id2)).collect(Collectors.toList())));
            }
        }
        if (this.messageReference != 0L) {
            obj.put("message_reference", DataObject.empty().put("message_id", this.messageReference).put("channel_id", this.channel.getId()).put("fail_if_not_exists", this.failOnInvalidReply));
        }
        obj.put("tts", this.tts);
        obj.put("allowed_mentions", this.allowedMentions);
        return obj;
    }

    protected void checkFileAmount() {
        if (this.files.size() >= 10) {
            throw new IllegalStateException("Cannot add more than 10 files!");
        }
    }

    protected void checkEdit() {
        if (this.isEdit()) {
            throw new IllegalStateException("Cannot add files to an existing message! Edit-Message does not support this operation!");
        }
    }

    protected void checkPermission(Permission perm) {
        if (!this.channel.getType().isGuild()) {
            return;
        }
        GuildChannel gc2 = (GuildChannel)((Object)this.channel);
        if (!gc2.getGuild().getSelfMember().hasAccess(gc2)) {
            throw new MissingAccessException(gc2, Permission.VIEW_CHANNEL);
        }
        if (!this.hasPermission(perm)) {
            throw new InsufficientPermissionException(gc2, perm);
        }
    }

    protected boolean hasPermission(Permission perm) {
        if (this.channel.getType() != ChannelType.TEXT) {
            return true;
        }
        TextChannel text = (TextChannel)this.channel;
        Member self = text.getGuild().getSelfMember();
        return self.hasPermission((GuildChannel)text, perm);
    }

    @Override
    protected RequestBody finalizeData() {
        if (!this.files.isEmpty()) {
            return this.asMultipart();
        }
        if (!this.isEmpty()) {
            return this.asJSON();
        }
        throw new IllegalStateException("Cannot build a message without content!");
    }

    @Override
    protected void handleSuccess(Response response, Request<Message> request) {
        request.onSuccess(this.api.getEntityBuilder().createMessage(response.getObject(), this.channel, false));
    }

    protected void finalize() {
        if (this.ownedResources.isEmpty()) {
            return;
        }
        LOG.warn("Found unclosed resources in MessageAction instance, closing on finalization step!");
        this.clearResources();
    }
}

