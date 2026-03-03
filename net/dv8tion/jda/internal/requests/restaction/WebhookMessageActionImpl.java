/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.internal.requests.restaction;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.IMentionable;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.interactions.components.ActionRow;
import net.dv8tion.jda.api.requests.Request;
import net.dv8tion.jda.api.requests.Response;
import net.dv8tion.jda.api.requests.restaction.WebhookMessageAction;
import net.dv8tion.jda.api.utils.AttachmentOption;
import net.dv8tion.jda.api.utils.data.DataArray;
import net.dv8tion.jda.api.utils.data.DataObject;
import net.dv8tion.jda.internal.requests.Requester;
import net.dv8tion.jda.internal.requests.Route;
import net.dv8tion.jda.internal.requests.restaction.TriggerRestAction;
import net.dv8tion.jda.internal.utils.AllowedMentionsImpl;
import net.dv8tion.jda.internal.utils.Checks;
import net.dv8tion.jda.internal.utils.IOUtil;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class WebhookMessageActionImpl<T>
extends TriggerRestAction<T>
implements WebhookMessageAction<T> {
    private final StringBuilder content = new StringBuilder();
    private final List<MessageEmbed> embeds = new ArrayList<MessageEmbed>();
    private final Map<String, InputStream> files = new HashMap<String, InputStream>();
    private final AllowedMentionsImpl allowedMentions = new AllowedMentionsImpl();
    private final List<ActionRow> components = new ArrayList<ActionRow>();
    private final MessageChannel channel;
    private final Function<DataObject, T> transformer;
    private boolean ephemeral;
    private boolean tts;
    private String username;
    private String avatarUrl;

    public WebhookMessageActionImpl(JDA api2, MessageChannel channel, Route.CompiledRoute route, Function<DataObject, T> transformer) {
        super(api2, route);
        this.channel = channel;
        this.transformer = transformer;
    }

    @Override
    @Nonnull
    public WebhookMessageActionImpl<T> applyMessage(@Nonnull Message message) {
        Checks.notNull(message, "Message");
        this.tts = message.isTTS();
        this.embeds.addAll(message.getEmbeds());
        this.allowedMentions.applyMessage(message);
        this.components.addAll(message.getActionRows());
        return this.setContent(message.getContentRaw());
    }

    @Override
    @Nonnull
    public WebhookMessageActionImpl<T> setEphemeral(boolean ephemeral) {
        this.ephemeral = ephemeral;
        return this;
    }

    @Override
    @Nonnull
    public WebhookMessageActionImpl<T> setContent(@Nullable String content) {
        this.content.setLength(0);
        if (content != null) {
            this.content.append(content);
        }
        return this;
    }

    @Override
    @Nonnull
    public WebhookMessageActionImpl<T> setTTS(boolean tts) {
        this.tts = tts;
        return this;
    }

    @Override
    @Nonnull
    public WebhookMessageActionImpl<T> addEmbeds(@Nonnull Collection<? extends MessageEmbed> embeds) {
        Checks.noneNull(embeds, "Message Embeds");
        Checks.check(this.embeds.size() + embeds.size() <= 10, "Cannot have more than 10 embeds in a message!");
        this.embeds.addAll(embeds);
        return this;
    }

    @Override
    @Nonnull
    public WebhookMessageActionImpl<T> addFile(@Nonnull InputStream data, @Nonnull String name, AttachmentOption ... options) {
        Checks.notNull(name, "Name");
        Checks.notNull(data, "Data");
        Checks.notNull(options, "AttachmentOption");
        Checks.check(this.files.size() < 10, "Cannot have more than 10 files in a message!");
        if (options.length > 0 && options[0] == AttachmentOption.SPOILER) {
            name = "SPOILER_" + name;
        }
        this.files.put(name, data);
        return this;
    }

    @Override
    @Nonnull
    public WebhookMessageActionImpl<T> addActionRows(ActionRow ... rows) {
        Checks.noneNull(rows, "ActionRows");
        Checks.check(rows.length + this.components.size() <= 5, "Can only have 5 action rows per message!");
        Collections.addAll(this.components, rows);
        return this;
    }

    private DataObject toData() {
        DataObject data = DataObject.empty();
        data.put("content", this.content.toString());
        data.put("tts", this.tts);
        if (this.username != null) {
            data.put("username", this.username);
        }
        if (this.avatarUrl != null) {
            data.put("avatar_url", this.avatarUrl);
        }
        if (this.ephemeral) {
            data.put("flags", 64);
        }
        if (!this.embeds.isEmpty()) {
            data.put("embeds", DataArray.fromCollection(this.embeds));
        }
        if (!this.components.isEmpty()) {
            data.put("components", DataArray.fromCollection(this.components));
        }
        data.put("allowed_mentions", this.allowedMentions);
        return data;
    }

    @Override
    protected RequestBody finalizeData() {
        DataObject data = this.toData();
        if (this.files.isEmpty()) {
            return this.getRequestBody(data);
        }
        MultipartBody.Builder body = new MultipartBody.Builder().setType(MultipartBody.FORM);
        int i2 = 0;
        for (Map.Entry<String, InputStream> file : this.files.entrySet()) {
            RequestBody stream = IOUtil.createRequestBody(Requester.MEDIA_TYPE_OCTET, file.getValue());
            body.addFormDataPart("file" + i2++, file.getKey(), stream);
        }
        body.addFormDataPart("payload_json", data.toString());
        this.files.clear();
        return body.build();
    }

    @Override
    protected void handleSuccess(Response response, Request<T> request) {
        T message = this.transformer.apply(response.getObject());
        request.onSuccess(message);
    }

    @Override
    @Nonnull
    public WebhookMessageActionImpl<T> mentionRepliedUser(boolean mention) {
        this.allowedMentions.mentionRepliedUser(mention);
        return this;
    }

    @Override
    @Nonnull
    public WebhookMessageActionImpl<T> allowedMentions(@Nullable Collection<Message.MentionType> allowedMentions) {
        this.allowedMentions.allowedMentions((Collection)allowedMentions);
        return this;
    }

    @Override
    @Nonnull
    public WebhookMessageActionImpl<T> mention(IMentionable ... mentions) {
        this.allowedMentions.mention(mentions);
        return this;
    }

    @Override
    @Nonnull
    public WebhookMessageActionImpl<T> mentionUsers(String ... userIds) {
        this.allowedMentions.mentionUsers(userIds);
        return this;
    }

    @Override
    @Nonnull
    public WebhookMessageActionImpl<T> mentionRoles(String ... roleIds) {
        this.allowedMentions.mentionRoles(roleIds);
        return this;
    }
}

