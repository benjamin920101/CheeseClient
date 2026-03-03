/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.entities;

import java.io.UncheckedIOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.entities.GuildChannel;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.exceptions.InsufficientPermissionException;
import net.dv8tion.jda.api.exceptions.MissingAccessException;
import net.dv8tion.jda.api.requests.Request;
import net.dv8tion.jda.api.requests.Response;
import net.dv8tion.jda.api.requests.RestAction;
import net.dv8tion.jda.api.utils.MiscUtil;
import net.dv8tion.jda.api.utils.data.DataArray;
import net.dv8tion.jda.api.utils.data.DataObject;
import net.dv8tion.jda.internal.JDAImpl;
import net.dv8tion.jda.internal.entities.EntityBuilder;
import net.dv8tion.jda.internal.requests.RestActionImpl;
import net.dv8tion.jda.internal.requests.Route;
import net.dv8tion.jda.internal.utils.Checks;
import org.apache.commons.collections4.map.ListOrderedMap;

public class MessageHistory {
    protected final MessageChannel channel;
    protected final ListOrderedMap<Long, Message> history = new ListOrderedMap();

    public MessageHistory(@Nonnull MessageChannel channel) {
        Checks.notNull(channel, "Channel");
        this.channel = channel;
        if (channel instanceof TextChannel) {
            TextChannel tc2 = (TextChannel)channel;
            Member selfMember = tc2.getGuild().getSelfMember();
            if (!selfMember.hasAccess(tc2)) {
                throw new MissingAccessException(tc2, Permission.VIEW_CHANNEL);
            }
            if (!selfMember.hasPermission((GuildChannel)tc2, Permission.MESSAGE_HISTORY)) {
                throw new InsufficientPermissionException(tc2, Permission.MESSAGE_HISTORY);
            }
        }
    }

    @Nonnull
    public JDA getJDA() {
        return this.channel.getJDA();
    }

    public int size() {
        return this.history.size();
    }

    public boolean isEmpty() {
        return this.size() == 0;
    }

    @Nonnull
    public MessageChannel getChannel() {
        return this.channel;
    }

    @Nonnull
    @CheckReturnValue
    public RestAction<List<Message>> retrievePast(int amount) {
        if (amount > 100 || amount < 1) {
            throw new IllegalArgumentException("Message retrieval limit is between 1 and 100 messages. No more, no less. Limit provided: " + amount);
        }
        Route.CompiledRoute route = Route.Messages.GET_MESSAGE_HISTORY.compile(this.channel.getId()).withQueryParams("limit", Integer.toString(amount));
        if (!this.history.isEmpty()) {
            route = route.withQueryParams("before", String.valueOf(this.history.lastKey()));
        }
        JDAImpl jda = (JDAImpl)this.getJDA();
        return new RestActionImpl<List<Message>>((JDA)jda, route, (response, request) -> {
            EntityBuilder builder = jda.getEntityBuilder();
            LinkedList<Message> messages = new LinkedList<Message>();
            DataArray historyJson = response.getArray();
            for (int i2 = 0; i2 < historyJson.length(); ++i2) {
                messages.add(builder.createMessage(historyJson.getObject(i2), this.channel, false));
            }
            messages.forEach(msg -> this.history.put(msg.getIdLong(), (Message)msg));
            return messages;
        });
    }

    @Nonnull
    @CheckReturnValue
    public RestAction<List<Message>> retrieveFuture(int amount) {
        if (amount > 100 || amount < 1) {
            throw new IllegalArgumentException("Message retrieval limit is between 1 and 100 messages. No more, no less. Limit provided: " + amount);
        }
        if (this.history.isEmpty()) {
            throw new IllegalStateException("No messages have been retrieved yet, so there is no message to act as a marker to retrieve more recent messages based on.");
        }
        Route.CompiledRoute route = Route.Messages.GET_MESSAGE_HISTORY.compile(this.channel.getId()).withQueryParams("limit", Integer.toString(amount), "after", String.valueOf(this.history.firstKey()));
        JDAImpl jda = (JDAImpl)this.getJDA();
        return new RestActionImpl<List<Message>>((JDA)jda, route, (response, request) -> {
            EntityBuilder builder = jda.getEntityBuilder();
            LinkedList<Message> messages = new LinkedList<Message>();
            DataArray historyJson = response.getArray();
            for (int i2 = 0; i2 < historyJson.length(); ++i2) {
                messages.add(builder.createMessage(historyJson.getObject(i2), this.channel, false));
            }
            Iterator it2 = messages.descendingIterator();
            while (it2.hasNext()) {
                Message m2 = (Message)it2.next();
                this.history.put(0, m2.getIdLong(), m2);
            }
            return messages;
        });
    }

    @Nonnull
    public List<Message> getRetrievedHistory() {
        int size = this.size();
        if (size == 0) {
            return Collections.emptyList();
        }
        if (size == 1) {
            return Collections.singletonList(this.history.getValue(0));
        }
        return Collections.unmodifiableList(new ArrayList<Message>(this.history.values()));
    }

    @Nullable
    public Message getMessageById(@Nonnull String id2) {
        return this.getMessageById(MiscUtil.parseSnowflake(id2));
    }

    @Nullable
    public Message getMessageById(long id2) {
        return (Message)this.history.get(id2);
    }

    @Nonnull
    @CheckReturnValue
    public static MessageRetrieveAction getHistoryAfter(@Nonnull MessageChannel channel, @Nonnull String messageId) {
        MessageHistory.checkArguments(channel, messageId);
        Route.CompiledRoute route = Route.Messages.GET_MESSAGE_HISTORY.compile(channel.getId()).withQueryParams("after", messageId);
        return new MessageRetrieveAction(route, channel);
    }

    @Nonnull
    @CheckReturnValue
    public static MessageRetrieveAction getHistoryBefore(@Nonnull MessageChannel channel, @Nonnull String messageId) {
        MessageHistory.checkArguments(channel, messageId);
        Route.CompiledRoute route = Route.Messages.GET_MESSAGE_HISTORY.compile(channel.getId()).withQueryParams("before", messageId);
        return new MessageRetrieveAction(route, channel);
    }

    @Nonnull
    @CheckReturnValue
    public static MessageRetrieveAction getHistoryAround(@Nonnull MessageChannel channel, @Nonnull String messageId) {
        MessageHistory.checkArguments(channel, messageId);
        Route.CompiledRoute route = Route.Messages.GET_MESSAGE_HISTORY.compile(channel.getId()).withQueryParams("around", messageId);
        return new MessageRetrieveAction(route, channel);
    }

    @Nonnull
    @CheckReturnValue
    public static MessageRetrieveAction getHistoryFromBeginning(@Nonnull MessageChannel channel) {
        return MessageHistory.getHistoryAfter(channel, "0");
    }

    private static void checkArguments(MessageChannel channel, String messageId) {
        Checks.isSnowflake(messageId, "Message ID");
        Checks.notNull(channel, "Channel");
        if (channel.getType() == ChannelType.TEXT) {
            TextChannel t2 = (TextChannel)channel;
            Member selfMember = t2.getGuild().getSelfMember();
            if (!selfMember.hasAccess(t2)) {
                throw new MissingAccessException(t2, Permission.VIEW_CHANNEL);
            }
            if (!selfMember.hasPermission((GuildChannel)t2, Permission.MESSAGE_HISTORY)) {
                throw new InsufficientPermissionException(t2, Permission.MESSAGE_HISTORY);
            }
        }
    }

    public static class MessageRetrieveAction
    extends RestActionImpl<MessageHistory> {
        private final MessageChannel channel;
        private Integer limit;

        protected MessageRetrieveAction(Route.CompiledRoute route, MessageChannel channel) {
            super(channel.getJDA(), route);
            this.channel = channel;
        }

        @Nonnull
        @CheckReturnValue
        public MessageRetrieveAction limit(@Nullable Integer limit) {
            if (limit != null) {
                Checks.positive(limit, "Limit");
                Checks.check(limit <= 100, "Limit may not exceed 100!");
            }
            this.limit = limit;
            return this;
        }

        @Override
        protected Route.CompiledRoute finalizeRoute() {
            Route.CompiledRoute route = super.finalizeRoute();
            return this.limit == null ? route : route.withQueryParams("limit", String.valueOf(this.limit));
        }

        @Override
        protected void handleSuccess(Response response, Request<MessageHistory> request) {
            MessageHistory result = new MessageHistory(this.channel);
            DataArray array = response.getArray();
            EntityBuilder builder = this.api.getEntityBuilder();
            for (int i2 = 0; i2 < array.length(); ++i2) {
                try {
                    DataObject obj = array.getObject(i2);
                    result.history.put(obj.getLong("id"), builder.createMessage(obj, this.channel, false));
                    continue;
                }
                catch (UncheckedIOException | NullPointerException e2) {
                    LOG.warn("Encountered exception in MessagePagination", e2);
                }
            }
            request.onSuccess(result);
        }
    }
}

