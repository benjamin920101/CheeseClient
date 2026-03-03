/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.internal.requests.restaction.interactions;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.BooleanSupplier;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.dv8tion.jda.api.entities.IMentionable;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.interactions.components.ActionRow;
import net.dv8tion.jda.api.requests.restaction.interactions.InteractionCallbackAction;
import net.dv8tion.jda.api.requests.restaction.interactions.ReplyAction;
import net.dv8tion.jda.api.utils.AttachmentOption;
import net.dv8tion.jda.api.utils.data.DataArray;
import net.dv8tion.jda.api.utils.data.DataObject;
import net.dv8tion.jda.internal.interactions.InteractionHookImpl;
import net.dv8tion.jda.internal.requests.restaction.interactions.InteractionCallbackActionImpl;
import net.dv8tion.jda.internal.utils.AllowedMentionsImpl;
import net.dv8tion.jda.internal.utils.Checks;
import net.dv8tion.jda.internal.utils.Helpers;

public class ReplyActionImpl
extends InteractionCallbackActionImpl
implements ReplyAction {
    private final List<MessageEmbed> embeds = new ArrayList<MessageEmbed>();
    private final AllowedMentionsImpl allowedMentions = new AllowedMentionsImpl();
    private final List<ActionRow> components = new ArrayList<ActionRow>();
    private String content = "";
    private int flags;
    private boolean tts;

    public ReplyActionImpl(InteractionHookImpl hook) {
        super(hook);
    }

    public ReplyActionImpl applyMessage(Message message) {
        this.content = message.getContentRaw();
        this.tts = message.isTTS();
        this.embeds.addAll(message.getEmbeds());
        this.components.addAll(message.getActionRows());
        this.allowedMentions.applyMessage(message);
        return this;
    }

    @Override
    protected DataObject toData() {
        DataObject json = DataObject.empty();
        if (this.isEmpty()) {
            json.put("type", InteractionCallbackAction.ResponseType.DEFERRED_CHANNEL_MESSAGE_WITH_SOURCE.getRaw());
            if (this.flags != 0) {
                json.put("data", DataObject.empty().put("flags", this.flags));
            }
        } else {
            DataObject payload = DataObject.empty();
            payload.put("allowed_mentions", this.allowedMentions);
            payload.put("content", this.content);
            payload.put("tts", this.tts);
            payload.put("flags", this.flags);
            if (!this.embeds.isEmpty()) {
                payload.put("embeds", DataArray.fromCollection(this.embeds));
            }
            if (!this.components.isEmpty()) {
                payload.put("components", DataArray.fromCollection(this.components));
            }
            json.put("data", payload);
            json.put("type", InteractionCallbackAction.ResponseType.CHANNEL_MESSAGE_WITH_SOURCE.getRaw());
        }
        return json;
    }

    private boolean isEmpty() {
        return Helpers.isEmpty(this.content) && this.embeds.isEmpty() && this.files.isEmpty() && this.components.isEmpty();
    }

    @Override
    @Nonnull
    public ReplyActionImpl setEphemeral(boolean ephemeral) {
        this.flags = ephemeral ? (this.flags |= 0x40) : (this.flags &= 0xFFFFFFBF);
        return this;
    }

    @Override
    @Nonnull
    public ReplyAction addFile(@Nonnull InputStream data, @Nonnull String name, AttachmentOption ... options) {
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
    public ReplyAction addEmbeds(@Nonnull Collection<? extends MessageEmbed> embeds) {
        Checks.noneNull(embeds, "MessageEmbed");
        for (MessageEmbed messageEmbed : embeds) {
            Checks.check(messageEmbed.isSendable(), "Provided Message contains an empty embed or an embed with a length greater than %d characters, which is the max for bot accounts!", (Object)6000);
        }
        if (embeds.size() + this.embeds.size() > 10) {
            throw new IllegalStateException("Cannot have more than 10 embeds per message!");
        }
        this.embeds.addAll(embeds);
        return this;
    }

    @Override
    @Nonnull
    public ReplyAction addActionRows(ActionRow ... rows) {
        Checks.noneNull(rows, "ActionRows");
        Checks.check(this.components.size() + rows.length <= 5, "Can only have 5 action rows per message!");
        Collections.addAll(this.components, rows);
        return this;
    }

    @Override
    @Nonnull
    public ReplyAction setCheck(BooleanSupplier checks) {
        return (ReplyAction)super.setCheck(checks);
    }

    @Override
    @Nonnull
    public ReplyAction timeout(long timeout, @Nonnull TimeUnit unit) {
        return (ReplyAction)super.timeout(timeout, unit);
    }

    @Override
    @Nonnull
    public ReplyAction deadline(long timestamp) {
        return (ReplyAction)super.deadline(timestamp);
    }

    @Override
    @Nonnull
    public ReplyActionImpl setTTS(boolean isTTS) {
        this.tts = isTTS;
        return this;
    }

    @Override
    @Nonnull
    public ReplyActionImpl setContent(String content) {
        if (content != null) {
            Checks.notLonger(content, 2000, "Content");
        }
        this.content = content == null ? "" : content;
        return this;
    }

    @Override
    @Nonnull
    public ReplyAction mentionRepliedUser(boolean mention) {
        this.allowedMentions.mentionRepliedUser(mention);
        return this;
    }

    @Override
    @Nonnull
    public ReplyAction allowedMentions(@Nullable Collection<Message.MentionType> allowedMentions) {
        this.allowedMentions.allowedMentions((Collection)allowedMentions);
        return this;
    }

    @Override
    @Nonnull
    public ReplyAction mention(IMentionable ... mentions) {
        this.allowedMentions.mention(mentions);
        return this;
    }

    @Override
    @Nonnull
    public ReplyAction mentionUsers(String ... userIds) {
        this.allowedMentions.mentionUsers(userIds);
        return this;
    }

    @Override
    @Nonnull
    public ReplyAction mentionRoles(String ... roleIds) {
        this.allowedMentions.mentionRoles(roleIds);
        return this;
    }
}

