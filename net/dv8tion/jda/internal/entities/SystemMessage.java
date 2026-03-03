/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.internal.entities;

import gnu.trove.set.TLongSet;
import java.time.OffsetDateTime;
import java.util.Collections;
import java.util.List;
import javax.annotation.Nonnull;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageActivity;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.MessageReaction;
import net.dv8tion.jda.api.entities.MessageSticker;
import net.dv8tion.jda.api.entities.MessageType;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.requests.RestAction;
import net.dv8tion.jda.api.requests.restaction.MessageAction;
import net.dv8tion.jda.internal.entities.ReceivedMessage;

public class SystemMessage
extends ReceivedMessage {
    public SystemMessage(long id2, MessageChannel channel, MessageType type, boolean fromWebhook, boolean mentionsEveryone, TLongSet mentionedUsers, TLongSet mentionedRoles, boolean tts, boolean pinned, String content, String nonce, User author, Member member, MessageActivity activity, OffsetDateTime editTime, List<MessageReaction> reactions, List<Message.Attachment> attachments, List<MessageEmbed> embeds, List<MessageSticker> stickers, int flags) {
        super(id2, channel, type, null, fromWebhook, mentionsEveryone, mentionedUsers, mentionedRoles, tts, pinned, content, nonce, author, member, activity, editTime, reactions, attachments, embeds, stickers, Collections.emptyList(), flags);
    }

    @Override
    @Nonnull
    public RestAction<Void> pin() {
        throw new UnsupportedOperationException("Cannot pin message of this Message Type. MessageType: " + (Object)((Object)this.getType()));
    }

    @Override
    @Nonnull
    public RestAction<Void> unpin() {
        throw new UnsupportedOperationException("Cannot unpin message of this Message Type. MessageType: " + (Object)((Object)this.getType()));
    }

    @Override
    @Nonnull
    public MessageAction editMessage(@Nonnull CharSequence newContent) {
        throw new UnsupportedOperationException("Cannot edit message of this Message Type. MessageType: " + (Object)((Object)this.getType()));
    }

    @Override
    @Nonnull
    public MessageAction editMessage(@Nonnull MessageEmbed newContent) {
        throw new UnsupportedOperationException("Cannot edit message of this Message Type. MessageType: " + (Object)((Object)this.getType()));
    }

    @Override
    @Nonnull
    public MessageAction editMessageFormat(@Nonnull String format, Object ... args) {
        throw new UnsupportedOperationException("Cannot edit message of this Message Type. MessageType: " + (Object)((Object)this.getType()));
    }

    @Override
    @Nonnull
    public MessageAction editMessage(@Nonnull Message newContent) {
        throw new UnsupportedOperationException("Cannot edit message of this Message Type. MessageType: " + (Object)((Object)this.getType()));
    }

    @Override
    public String toString() {
        return "M:[" + (Object)((Object)this.type) + ']' + this.author + '(' + this.id + ')';
    }
}

