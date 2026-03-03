/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.entities;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Formattable;
import java.util.Formatter;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeSet;
import java.util.concurrent.CompletableFuture;
import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnull;
import net.dv8tion.jda.api.AccountType;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.AbstractChannel;
import net.dv8tion.jda.api.entities.Emote;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.MessageHistory;
import net.dv8tion.jda.api.exceptions.AccountTypeException;
import net.dv8tion.jda.api.requests.RestAction;
import net.dv8tion.jda.api.requests.restaction.AuditableRestAction;
import net.dv8tion.jda.api.requests.restaction.MessageAction;
import net.dv8tion.jda.api.requests.restaction.pagination.MessagePaginationAction;
import net.dv8tion.jda.api.requests.restaction.pagination.ReactionPaginationAction;
import net.dv8tion.jda.api.utils.AttachmentOption;
import net.dv8tion.jda.api.utils.MiscUtil;
import net.dv8tion.jda.api.utils.data.DataArray;
import net.dv8tion.jda.internal.JDAImpl;
import net.dv8tion.jda.internal.entities.EntityBuilder;
import net.dv8tion.jda.internal.requests.RestActionImpl;
import net.dv8tion.jda.internal.requests.Route;
import net.dv8tion.jda.internal.requests.restaction.AuditableRestActionImpl;
import net.dv8tion.jda.internal.requests.restaction.MessageActionImpl;
import net.dv8tion.jda.internal.requests.restaction.pagination.MessagePaginationActionImpl;
import net.dv8tion.jda.internal.requests.restaction.pagination.ReactionPaginationActionImpl;
import net.dv8tion.jda.internal.utils.Checks;
import net.dv8tion.jda.internal.utils.EncodingUtil;

public interface MessageChannel
extends AbstractChannel,
Formattable {
    @Nonnull
    default public String getLatestMessageId() {
        return Long.toUnsignedString(this.getLatestMessageIdLong());
    }

    @Nonnull
    default public List<CompletableFuture<Void>> purgeMessagesById(@Nonnull List<String> messageIds) {
        if (messageIds == null || messageIds.isEmpty()) {
            return Collections.emptyList();
        }
        long[] ids = new long[messageIds.size()];
        for (int i2 = 0; i2 < ids.length; ++i2) {
            ids[i2] = MiscUtil.parseSnowflake(messageIds.get(i2));
        }
        return this.purgeMessagesById(ids);
    }

    @Nonnull
    default public List<CompletableFuture<Void>> purgeMessagesById(String ... messageIds) {
        if (messageIds == null || messageIds.length == 0) {
            return Collections.emptyList();
        }
        return this.purgeMessagesById(Arrays.asList(messageIds));
    }

    @Nonnull
    default public List<CompletableFuture<Void>> purgeMessages(Message ... messages) {
        if (messages == null || messages.length == 0) {
            return Collections.emptyList();
        }
        return this.purgeMessages(Arrays.asList(messages));
    }

    @Nonnull
    default public List<CompletableFuture<Void>> purgeMessages(@Nonnull List<? extends Message> messages) {
        if (messages == null || messages.isEmpty()) {
            return Collections.emptyList();
        }
        long[] ids = new long[messages.size()];
        for (int i2 = 0; i2 < ids.length; ++i2) {
            ids[i2] = messages.get(i2).getIdLong();
        }
        return this.purgeMessagesById(ids);
    }

    @Nonnull
    default public List<CompletableFuture<Void>> purgeMessagesById(long ... messageIds) {
        if (messageIds == null || messageIds.length == 0) {
            return Collections.emptyList();
        }
        ArrayList<CompletableFuture<Void>> list = new ArrayList<CompletableFuture<Void>>(messageIds.length);
        TreeSet sortedIds = new TreeSet(Comparator.reverseOrder());
        for (long messageId : messageIds) {
            sortedIds.add(messageId);
        }
        Object object = sortedIds.iterator();
        while (object.hasNext()) {
            long messageId = (Long)object.next();
            list.add(this.deleteMessageById(messageId).submit());
        }
        return list;
    }

    public long getLatestMessageIdLong();

    public boolean hasLatestMessage();

    @Nonnull
    @CheckReturnValue
    default public MessageAction sendMessage(@Nonnull CharSequence text) {
        Checks.notEmpty(text, "Provided text for message");
        Checks.check(text.length() <= 2000, "Provided text for message must be less than 2000 characters in length");
        Route.CompiledRoute route = Route.Messages.SEND_MESSAGE.compile(this.getId());
        if (text instanceof StringBuilder) {
            return new MessageActionImpl(this.getJDA(), route, this, (StringBuilder)text);
        }
        return new MessageActionImpl(this.getJDA(), route, this).append(text);
    }

    @Nonnull
    @CheckReturnValue
    default public MessageAction sendMessageFormat(@Nonnull String format, Object ... args) {
        Checks.notEmpty(format, "Format");
        return this.sendMessage(String.format(format, args));
    }

    @Nonnull
    @CheckReturnValue
    default public MessageAction sendMessage(@Nonnull MessageEmbed embed) {
        Checks.notNull(embed, "Provided embed");
        Route.CompiledRoute route = Route.Messages.SEND_MESSAGE.compile(this.getId());
        return new MessageActionImpl(this.getJDA(), route, this).embed(embed);
    }

    @Nonnull
    @CheckReturnValue
    default public MessageAction sendMessage(@Nonnull Message msg) {
        Checks.notNull(msg, "Message");
        Route.CompiledRoute route = Route.Messages.SEND_MESSAGE.compile(this.getId());
        return new MessageActionImpl(this.getJDA(), route, this).apply(msg);
    }

    @Nonnull
    @CheckReturnValue
    default public MessageAction sendFile(@Nonnull File file, AttachmentOption ... options) {
        Checks.notNull(file, "file");
        return this.sendFile(file, file.getName(), options);
    }

    @Nonnull
    @CheckReturnValue
    default public MessageAction sendFile(@Nonnull File file, @Nonnull String fileName, AttachmentOption ... options) {
        Checks.notNull(file, "file");
        Checks.check(file.exists() && file.canRead(), "Provided file doesn't exist or cannot be read!");
        Checks.notNull(fileName, "fileName");
        try {
            return this.sendFile(new FileInputStream(file), fileName, options);
        }
        catch (FileNotFoundException ex2) {
            throw new IllegalArgumentException(ex2);
        }
    }

    @Nonnull
    @CheckReturnValue
    default public MessageAction sendFile(@Nonnull InputStream data, @Nonnull String fileName, AttachmentOption ... options) {
        Checks.notNull(data, "data InputStream");
        Checks.notNull(fileName, "fileName");
        Route.CompiledRoute route = Route.Messages.SEND_MESSAGE.compile(this.getId());
        return new MessageActionImpl(this.getJDA(), route, this).addFile(data, fileName, options);
    }

    @Nonnull
    @CheckReturnValue
    default public MessageAction sendFile(@Nonnull byte[] data, @Nonnull String fileName, AttachmentOption ... options) {
        Checks.notNull(data, "data");
        Checks.notNull(fileName, "fileName");
        return this.sendFile(new ByteArrayInputStream(data), fileName, options);
    }

    @Nonnull
    @CheckReturnValue
    default public RestAction<Message> retrieveMessageById(@Nonnull String messageId) {
        AccountTypeException.check(this.getJDA().getAccountType(), AccountType.BOT);
        Checks.isSnowflake(messageId, "Message ID");
        JDAImpl jda = (JDAImpl)this.getJDA();
        Route.CompiledRoute route = Route.Messages.GET_MESSAGE.compile(this.getId(), messageId);
        return new RestActionImpl<Message>((JDA)jda, route, (response, request) -> jda.getEntityBuilder().createMessage(response.getObject(), this, false));
    }

    @Nonnull
    @CheckReturnValue
    default public RestAction<Message> retrieveMessageById(long messageId) {
        return this.retrieveMessageById(Long.toUnsignedString(messageId));
    }

    @Nonnull
    @CheckReturnValue
    default public AuditableRestAction<Void> deleteMessageById(@Nonnull String messageId) {
        Checks.isSnowflake(messageId, "Message ID");
        Route.CompiledRoute route = Route.Messages.DELETE_MESSAGE.compile(this.getId(), messageId);
        return new AuditableRestActionImpl<Void>(this.getJDA(), route);
    }

    @Nonnull
    @CheckReturnValue
    default public AuditableRestAction<Void> deleteMessageById(long messageId) {
        return this.deleteMessageById(Long.toUnsignedString(messageId));
    }

    default public MessageHistory getHistory() {
        return new MessageHistory(this);
    }

    @Nonnull
    @CheckReturnValue
    default public MessagePaginationAction getIterableHistory() {
        return new MessagePaginationActionImpl(this);
    }

    @Nonnull
    @CheckReturnValue
    default public MessageHistory.MessageRetrieveAction getHistoryAround(@Nonnull String messageId, int limit) {
        return MessageHistory.getHistoryAround(this, messageId).limit(limit);
    }

    @Nonnull
    @CheckReturnValue
    default public MessageHistory.MessageRetrieveAction getHistoryAround(long messageId, int limit) {
        return this.getHistoryAround(Long.toUnsignedString(messageId), limit);
    }

    @Nonnull
    @CheckReturnValue
    default public MessageHistory.MessageRetrieveAction getHistoryAround(@Nonnull Message message, int limit) {
        Checks.notNull(message, "Provided target message");
        return this.getHistoryAround(message.getId(), limit);
    }

    @Nonnull
    @CheckReturnValue
    default public MessageHistory.MessageRetrieveAction getHistoryAfter(@Nonnull String messageId, int limit) {
        return MessageHistory.getHistoryAfter(this, messageId).limit(limit);
    }

    @Nonnull
    @CheckReturnValue
    default public MessageHistory.MessageRetrieveAction getHistoryAfter(long messageId, int limit) {
        return this.getHistoryAfter(Long.toUnsignedString(messageId), limit);
    }

    @Nonnull
    @CheckReturnValue
    default public MessageHistory.MessageRetrieveAction getHistoryAfter(@Nonnull Message message, int limit) {
        Checks.notNull(message, "Message");
        return this.getHistoryAfter(message.getId(), limit);
    }

    @Nonnull
    @CheckReturnValue
    default public MessageHistory.MessageRetrieveAction getHistoryBefore(@Nonnull String messageId, int limit) {
        return MessageHistory.getHistoryBefore(this, messageId).limit(limit);
    }

    @Nonnull
    @CheckReturnValue
    default public MessageHistory.MessageRetrieveAction getHistoryBefore(long messageId, int limit) {
        return this.getHistoryBefore(Long.toUnsignedString(messageId), limit);
    }

    @Nonnull
    @CheckReturnValue
    default public MessageHistory.MessageRetrieveAction getHistoryBefore(@Nonnull Message message, int limit) {
        Checks.notNull(message, "Message");
        return this.getHistoryBefore(message.getId(), limit);
    }

    @Nonnull
    @CheckReturnValue
    default public MessageHistory.MessageRetrieveAction getHistoryFromBeginning(int limit) {
        return MessageHistory.getHistoryFromBeginning(this).limit(limit);
    }

    @Nonnull
    @CheckReturnValue
    default public RestAction<Void> sendTyping() {
        Route.CompiledRoute route = Route.Channels.SEND_TYPING.compile(this.getId());
        return new RestActionImpl<Void>(this.getJDA(), route);
    }

    @Nonnull
    @CheckReturnValue
    default public RestAction<Void> addReactionById(@Nonnull String messageId, @Nonnull String unicode) {
        Checks.isSnowflake(messageId, "Message ID");
        Checks.notNull(unicode, "Provided Unicode");
        unicode = unicode.trim();
        Checks.notEmpty(unicode, "Provided Unicode");
        String encoded = EncodingUtil.encodeReaction(unicode);
        Route.CompiledRoute route = Route.Messages.ADD_REACTION.compile(this.getId(), messageId, encoded, "@me");
        return new RestActionImpl<Void>(this.getJDA(), route);
    }

    @Nonnull
    @CheckReturnValue
    default public RestAction<Void> addReactionById(long messageId, @Nonnull String unicode) {
        return this.addReactionById(Long.toUnsignedString(messageId), unicode);
    }

    @Nonnull
    @CheckReturnValue
    default public RestAction<Void> addReactionById(@Nonnull String messageId, @Nonnull Emote emote) {
        Checks.notNull(emote, "Emote");
        return this.addReactionById(messageId, emote.getName() + ":" + emote.getId());
    }

    @Nonnull
    @CheckReturnValue
    default public RestAction<Void> addReactionById(long messageId, @Nonnull Emote emote) {
        return this.addReactionById(Long.toUnsignedString(messageId), emote);
    }

    @Nonnull
    @CheckReturnValue
    default public RestAction<Void> removeReactionById(@Nonnull String messageId, @Nonnull String unicode) {
        Checks.isSnowflake(messageId, "Message ID");
        Checks.notNull(unicode, "Provided Unicode");
        unicode = unicode.trim();
        Checks.notEmpty(unicode, "Provided Unicode");
        String encoded = EncodingUtil.encodeReaction(unicode);
        Route.CompiledRoute route = Route.Messages.REMOVE_REACTION.compile(this.getId(), messageId, encoded, "@me");
        return new RestActionImpl<Void>(this.getJDA(), route);
    }

    @Nonnull
    @CheckReturnValue
    default public RestAction<Void> removeReactionById(long messageId, @Nonnull String unicode) {
        return this.removeReactionById(Long.toUnsignedString(messageId), unicode);
    }

    @Nonnull
    @CheckReturnValue
    default public RestAction<Void> removeReactionById(@Nonnull String messageId, @Nonnull Emote emote) {
        Checks.notNull(emote, "Emote");
        return this.removeReactionById(messageId, emote.getName() + ":" + emote.getId());
    }

    @Nonnull
    @CheckReturnValue
    default public RestAction<Void> removeReactionById(long messageId, @Nonnull Emote emote) {
        return this.removeReactionById(Long.toUnsignedString(messageId), emote);
    }

    @Nonnull
    @CheckReturnValue
    default public ReactionPaginationAction retrieveReactionUsersById(@Nonnull String messageId, @Nonnull String unicode) {
        Checks.isSnowflake(messageId, "Message ID");
        Checks.notEmpty(unicode, "Emoji");
        Checks.noWhitespace(unicode, "Emoji");
        return new ReactionPaginationActionImpl(this, messageId, EncodingUtil.encodeReaction(unicode));
    }

    @Nonnull
    @CheckReturnValue
    default public ReactionPaginationAction retrieveReactionUsersById(long messageId, @Nonnull String unicode) {
        return this.retrieveReactionUsersById(Long.toUnsignedString(messageId), unicode);
    }

    @Nonnull
    @CheckReturnValue
    default public ReactionPaginationAction retrieveReactionUsersById(@Nonnull String messageId, @Nonnull Emote emote) {
        Checks.isSnowflake(messageId, "Message ID");
        Checks.notNull(emote, "Emote");
        return new ReactionPaginationActionImpl(this, messageId, String.format("%s:%s", emote, emote.getId()));
    }

    @Nonnull
    @CheckReturnValue
    default public ReactionPaginationAction retrieveReactionUsersById(long messageId, @Nonnull Emote emote) {
        return this.retrieveReactionUsersById(Long.toUnsignedString(messageId), emote);
    }

    @Nonnull
    @CheckReturnValue
    default public RestAction<Void> pinMessageById(@Nonnull String messageId) {
        Checks.isSnowflake(messageId, "Message ID");
        Route.CompiledRoute route = Route.Messages.ADD_PINNED_MESSAGE.compile(this.getId(), messageId);
        return new RestActionImpl<Void>(this.getJDA(), route);
    }

    @Nonnull
    @CheckReturnValue
    default public RestAction<Void> pinMessageById(long messageId) {
        return this.pinMessageById(Long.toUnsignedString(messageId));
    }

    @Nonnull
    @CheckReturnValue
    default public RestAction<Void> unpinMessageById(@Nonnull String messageId) {
        Checks.isSnowflake(messageId, "Message ID");
        Route.CompiledRoute route = Route.Messages.REMOVE_PINNED_MESSAGE.compile(this.getId(), messageId);
        return new RestActionImpl<Void>(this.getJDA(), route);
    }

    @Nonnull
    @CheckReturnValue
    default public RestAction<Void> unpinMessageById(long messageId) {
        return this.unpinMessageById(Long.toUnsignedString(messageId));
    }

    @Nonnull
    @CheckReturnValue
    default public RestAction<List<Message>> retrievePinnedMessages() {
        JDAImpl jda = (JDAImpl)this.getJDA();
        Route.CompiledRoute route = Route.Messages.GET_PINNED_MESSAGES.compile(this.getId());
        return new RestActionImpl<List<Message>>((JDA)jda, route, (response, request) -> {
            LinkedList<Message> pinnedMessages = new LinkedList<Message>();
            EntityBuilder builder = jda.getEntityBuilder();
            DataArray pins = response.getArray();
            for (int i2 = 0; i2 < pins.length(); ++i2) {
                pinnedMessages.add(builder.createMessage(pins.getObject(i2), this, false));
            }
            return Collections.unmodifiableList(pinnedMessages);
        });
    }

    @Nonnull
    @CheckReturnValue
    default public MessageAction editMessageById(@Nonnull String messageId, @Nonnull CharSequence newContent) {
        Checks.isSnowflake(messageId, "Message ID");
        Checks.notEmpty(newContent, "Provided message content");
        Checks.check(newContent.length() <= 2000, "Provided newContent length must be 2000 or less characters.");
        Route.CompiledRoute route = Route.Messages.EDIT_MESSAGE.compile(this.getId(), messageId);
        if (newContent instanceof StringBuilder) {
            return new MessageActionImpl(this.getJDA(), route, this, (StringBuilder)newContent);
        }
        return new MessageActionImpl(this.getJDA(), route, this).append(newContent);
    }

    @Nonnull
    @CheckReturnValue
    default public MessageAction editMessageById(long messageId, @Nonnull CharSequence newContent) {
        return this.editMessageById(Long.toUnsignedString(messageId), newContent);
    }

    @Nonnull
    @CheckReturnValue
    default public MessageAction editMessageById(@Nonnull String messageId, @Nonnull Message newContent) {
        Checks.isSnowflake(messageId, "Message ID");
        Checks.notNull(newContent, "message");
        Route.CompiledRoute route = Route.Messages.EDIT_MESSAGE.compile(this.getId(), messageId);
        return new MessageActionImpl(this.getJDA(), route, this).apply(newContent);
    }

    @Nonnull
    @CheckReturnValue
    default public MessageAction editMessageById(long messageId, @Nonnull Message newContent) {
        return this.editMessageById(Long.toUnsignedString(messageId), newContent);
    }

    @Nonnull
    @CheckReturnValue
    default public MessageAction editMessageFormatById(@Nonnull String messageId, @Nonnull String format, Object ... args) {
        Checks.notBlank(format, "Format String");
        return this.editMessageById(messageId, (CharSequence)String.format(format, args));
    }

    @Nonnull
    @CheckReturnValue
    default public MessageAction editMessageFormatById(long messageId, @Nonnull String format, Object ... args) {
        Checks.notBlank(format, "Format String");
        return this.editMessageById(messageId, (CharSequence)String.format(format, args));
    }

    @Nonnull
    @CheckReturnValue
    default public MessageAction editMessageById(@Nonnull String messageId, @Nonnull MessageEmbed newEmbed) {
        Checks.isSnowflake(messageId, "Message ID");
        Checks.notNull(newEmbed, "MessageEmbed");
        Route.CompiledRoute route = Route.Messages.EDIT_MESSAGE.compile(this.getId(), messageId);
        return new MessageActionImpl(this.getJDA(), route, this).embed(newEmbed);
    }

    @Nonnull
    @CheckReturnValue
    default public MessageAction editMessageById(long messageId, @Nonnull MessageEmbed newEmbed) {
        return this.editMessageById(Long.toUnsignedString(messageId), newEmbed);
    }

    @Override
    default public void formatTo(Formatter formatter, int flags, int width, int precision) {
        String out;
        boolean leftJustified = (flags & 1) == 1;
        boolean upper = (flags & 2) == 2;
        boolean alt2 = (flags & 4) == 4;
        String string = out = upper ? this.getName().toUpperCase(formatter.locale()) : this.getName();
        if (alt2) {
            out = "#" + out;
        }
        MiscUtil.appendTo(formatter, width, precision, leftJustified, out);
    }
}

