/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.internal.requests.restaction.interactions;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.interactions.components.ActionRow;
import net.dv8tion.jda.api.requests.restaction.interactions.InteractionCallbackAction;
import net.dv8tion.jda.api.requests.restaction.interactions.UpdateInteractionAction;
import net.dv8tion.jda.api.utils.AttachmentOption;
import net.dv8tion.jda.api.utils.data.DataArray;
import net.dv8tion.jda.api.utils.data.DataObject;
import net.dv8tion.jda.internal.interactions.InteractionHookImpl;
import net.dv8tion.jda.internal.requests.restaction.interactions.InteractionCallbackActionImpl;
import net.dv8tion.jda.internal.utils.Checks;

public class UpdateInteractionActionImpl
extends InteractionCallbackActionImpl
implements UpdateInteractionAction {
    private List<String> retainedFiles = null;
    private List<MessageEmbed> embeds = null;
    private List<ActionRow> components = null;
    private String content = null;

    public UpdateInteractionActionImpl(InteractionHookImpl hook) {
        super(hook);
    }

    private boolean isEmpty() {
        return this.content == null && this.embeds == null && this.components == null && this.files.isEmpty();
    }

    @Override
    protected DataObject toData() {
        DataObject json = DataObject.empty();
        if (this.isEmpty()) {
            return json.put("type", InteractionCallbackAction.ResponseType.DEFERRED_MESSAGE_UPDATE.getRaw());
        }
        json.put("type", InteractionCallbackAction.ResponseType.MESSAGE_UPDATE.getRaw());
        DataObject data = DataObject.empty();
        if (this.content != null) {
            data.put("content", this.content);
        }
        if (this.embeds != null) {
            data.put("embeds", DataArray.fromCollection(this.embeds));
        }
        if (this.components != null) {
            data.put("components", DataArray.fromCollection(this.components));
        }
        if (this.retainedFiles != null) {
            json.put("attachments", DataArray.fromCollection(this.retainedFiles.stream().map((? super T id2) -> DataObject.empty().put("id", id2)).collect(Collectors.toList())));
        }
        json.put("data", data);
        return json;
    }

    public UpdateInteractionAction applyMessage(Message message) {
        this.content = message.getContentRaw();
        this.embeds = new ArrayList<MessageEmbed>(message.getEmbeds());
        this.components = new ArrayList<ActionRow>(message.getActionRows());
        return this;
    }

    @Override
    @Nonnull
    public UpdateInteractionAction setEmbeds(@Nonnull Collection<? extends MessageEmbed> embeds) {
        Checks.noneNull(embeds, "MessageEmbed");
        Checks.check(embeds.size() <= 10, "Cannot have more than 10 embeds per message!");
        for (MessageEmbed messageEmbed : embeds) {
            Checks.check(messageEmbed.isSendable(), "Provided Message contains an empty embed or an embed with a length greater than %d characters, which is the max for bot accounts!", (Object)6000);
        }
        if (this.embeds == null) {
            this.embeds = new ArrayList<MessageEmbed>();
        }
        this.embeds.clear();
        this.embeds.addAll(embeds);
        return this;
    }

    @Override
    @Nonnull
    public UpdateInteractionAction setActionRows(ActionRow ... rows) {
        Checks.noneNull(rows, "ActionRows");
        Checks.check(rows.length <= 5, "Can only have 5 action rows per message!");
        this.components = new ArrayList<ActionRow>();
        Collections.addAll(this.components, rows);
        return this;
    }

    @Override
    @Nonnull
    public UpdateInteractionAction addFile(@Nonnull InputStream data, @Nonnull String name, AttachmentOption ... options) {
        Checks.notNull(data, "Data");
        Checks.notEmpty(name, "Name");
        Checks.noneNull((Object[])options, "Options");
        if (options.length > 0) {
            name = "SPOILER_" + name;
        }
        this.files.put(name, data);
        return this;
    }

    @Override
    @Nonnull
    public UpdateInteractionAction setContent(@Nullable String content) {
        if (content != null) {
            Checks.notLonger(content, 2000, "Content");
        }
        this.content = content == null ? "" : content;
        return this;
    }
}

