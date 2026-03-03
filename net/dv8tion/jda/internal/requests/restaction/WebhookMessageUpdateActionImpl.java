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
import java.util.stream.Collectors;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.interactions.components.ActionRow;
import net.dv8tion.jda.api.requests.Request;
import net.dv8tion.jda.api.requests.Response;
import net.dv8tion.jda.api.requests.restaction.WebhookMessageUpdateAction;
import net.dv8tion.jda.api.utils.AttachmentOption;
import net.dv8tion.jda.api.utils.data.DataArray;
import net.dv8tion.jda.api.utils.data.DataObject;
import net.dv8tion.jda.internal.requests.Requester;
import net.dv8tion.jda.internal.requests.Route;
import net.dv8tion.jda.internal.requests.restaction.TriggerRestAction;
import net.dv8tion.jda.internal.utils.Checks;
import net.dv8tion.jda.internal.utils.IOUtil;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class WebhookMessageUpdateActionImpl<T>
extends TriggerRestAction<T>
implements WebhookMessageUpdateAction<T> {
    private static final int CONTENT = 1;
    private static final int EMBEDS = 2;
    private static final int FILES = 4;
    private static final int COMPONENTS = 8;
    private static final int RETAINED_FILES = 16;
    private int set = 0;
    private final List<ActionRow> components = new ArrayList<ActionRow>();
    private final List<MessageEmbed> embeds = new ArrayList<MessageEmbed>();
    private final List<String> retainedFiles = new ArrayList<String>();
    private final Map<String, InputStream> files = new HashMap<String, InputStream>();
    private final Function<DataObject, T> transformer;
    private String content;

    public WebhookMessageUpdateActionImpl(JDA api2, Route.CompiledRoute route, Function<DataObject, T> transformer) {
        super(api2, route);
        this.transformer = transformer;
    }

    @Override
    @Nonnull
    public WebhookMessageUpdateAction<T> setContent(@Nullable String content) {
        if (content != null) {
            Checks.notLonger(content, 2000, "Content");
        }
        this.content = content;
        this.set |= 1;
        return this;
    }

    @Override
    @Nonnull
    public WebhookMessageUpdateAction<T> setEmbeds(@Nonnull Collection<? extends MessageEmbed> embeds) {
        Checks.noneNull(embeds, "MessageEmbeds");
        Checks.check(embeds.size() <= 10, "Cannot have more than 10 embeds in one message!");
        this.embeds.clear();
        this.embeds.addAll(embeds);
        this.set |= 2;
        return this;
    }

    @Override
    @Nonnull
    public WebhookMessageUpdateAction<T> addFile(@Nonnull InputStream data, @Nonnull String name, AttachmentOption ... options) {
        Checks.notNull(name, "File name");
        Checks.notNull(data, "File data");
        Checks.noneNull((Object[])options, "AttachmentOptions");
        if (options.length > 0) {
            name = "SPOILER_" + name;
        }
        this.files.put(name, data);
        this.set |= 4;
        return this;
    }

    @Override
    @Nonnull
    public WebhookMessageUpdateAction<T> retainFilesById(@Nonnull Collection<String> ids) {
        Checks.noneNull(ids, "IDs");
        ids.forEach(Checks::isSnowflake);
        this.retainedFiles.clear();
        this.retainedFiles.addAll(ids);
        this.set |= 0x10;
        return this;
    }

    @Override
    @Nonnull
    public WebhookMessageUpdateAction<T> setActionRows(ActionRow ... rows) {
        Checks.noneNull(rows, "ActionRows");
        this.components.clear();
        Collections.addAll(this.components, rows);
        this.set |= 8;
        return this;
    }

    @Override
    @Nonnull
    public WebhookMessageUpdateAction<T> applyMessage(@Nonnull Message message) {
        Checks.notNull(message, "Message");
        this.setContent(message.getContentRaw());
        this.setActionRows(message.getActionRows());
        this.setEmbeds(message.getEmbeds());
        return this;
    }

    private boolean isUpdate(int flag) {
        return (this.set & flag) == flag;
    }

    @Override
    protected RequestBody finalizeData() {
        DataObject json = DataObject.empty();
        if (this.isUpdate(1)) {
            json.put("content", this.content);
        }
        if (this.isUpdate(2)) {
            json.put("embeds", DataArray.fromCollection(this.embeds));
        }
        if (this.isUpdate(8)) {
            json.put("components", DataArray.fromCollection(this.components));
        }
        if (this.isUpdate(16)) {
            json.put("attachments", DataArray.fromCollection(this.retainedFiles.stream().map((? super T id2) -> DataObject.empty().put("id", id2)).collect(Collectors.toList())));
        }
        if (!this.isUpdate(4)) {
            return this.getRequestBody(json);
        }
        MultipartBody.Builder body = new MultipartBody.Builder().setType(MultipartBody.FORM);
        int i2 = 0;
        for (Map.Entry<String, InputStream> file : this.files.entrySet()) {
            RequestBody stream = IOUtil.createRequestBody(Requester.MEDIA_TYPE_OCTET, file.getValue());
            body.addFormDataPart("file" + i2++, file.getKey(), stream);
        }
        body.addFormDataPart("payload_json", json.toString());
        this.files.clear();
        return body.build();
    }

    @Override
    protected void handleSuccess(Response response, Request<T> request) {
        T message = this.transformer.apply(response.getObject());
        request.onSuccess(message);
    }
}

