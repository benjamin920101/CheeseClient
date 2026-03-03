/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.internal.requests.restaction.pagination;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nonnull;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.entities.GuildChannel;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.exceptions.InsufficientPermissionException;
import net.dv8tion.jda.api.exceptions.MissingAccessException;
import net.dv8tion.jda.api.exceptions.ParsingException;
import net.dv8tion.jda.api.requests.Request;
import net.dv8tion.jda.api.requests.Response;
import net.dv8tion.jda.api.requests.restaction.pagination.MessagePaginationAction;
import net.dv8tion.jda.api.utils.data.DataArray;
import net.dv8tion.jda.internal.entities.EntityBuilder;
import net.dv8tion.jda.internal.requests.Route;
import net.dv8tion.jda.internal.requests.restaction.pagination.PaginationActionImpl;

public class MessagePaginationActionImpl
extends PaginationActionImpl<Message, MessagePaginationAction>
implements MessagePaginationAction {
    private final MessageChannel channel;

    public MessagePaginationActionImpl(MessageChannel channel) {
        super(channel.getJDA(), Route.Messages.GET_MESSAGE_HISTORY.compile(channel.getId()), 1, 100, 100);
        if (channel.getType() == ChannelType.TEXT) {
            TextChannel textChannel = (TextChannel)channel;
            Member selfMember = textChannel.getGuild().getSelfMember();
            if (!selfMember.hasAccess(textChannel)) {
                throw new MissingAccessException(textChannel, Permission.VIEW_CHANNEL);
            }
            if (!selfMember.hasPermission((GuildChannel)textChannel, Permission.MESSAGE_HISTORY)) {
                throw new InsufficientPermissionException(textChannel, Permission.MESSAGE_HISTORY);
            }
        }
        this.channel = channel;
    }

    @Override
    @Nonnull
    public MessageChannel getChannel() {
        return this.channel;
    }

    @Override
    protected Route.CompiledRoute finalizeRoute() {
        Route.CompiledRoute route = super.finalizeRoute();
        String limit = String.valueOf(this.getLimit());
        long last = this.lastKey;
        route = route.withQueryParams("limit", limit);
        if (last != 0L) {
            route = route.withQueryParams("before", Long.toUnsignedString(last));
        }
        return route;
    }

    @Override
    protected void handleSuccess(Response response, Request<List<Message>> request) {
        DataArray array = response.getArray();
        ArrayList<Message> messages = new ArrayList<Message>(array.length());
        EntityBuilder builder = this.api.getEntityBuilder();
        for (int i2 = 0; i2 < array.length(); ++i2) {
            try {
                Message msg = builder.createMessage(array.getObject(i2), this.channel, false);
                messages.add(msg);
                if (this.useCache) {
                    this.cached.add(msg);
                }
                this.last = msg;
                this.lastKey = ((Message)this.last).getIdLong();
                continue;
            }
            catch (NullPointerException | ParsingException e2) {
                LOG.warn("Encountered an exception in MessagePagination", e2);
                continue;
            }
            catch (IllegalArgumentException e3) {
                if ("UNKNOWN_MESSAGE_TYPE".equals(e3.getMessage())) {
                    LOG.warn("Skipping unknown message type during pagination", e3);
                    continue;
                }
                LOG.warn("Unexpected issue trying to parse message during pagination", e3);
            }
        }
        request.onSuccess(messages);
    }

    @Override
    protected long getKey(Message it2) {
        return it2.getIdLong();
    }
}

