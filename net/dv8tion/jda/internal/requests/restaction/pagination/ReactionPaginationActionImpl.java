/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.internal.requests.restaction.pagination;

import java.util.LinkedList;
import java.util.List;
import javax.annotation.Nonnull;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.MessageReaction;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.exceptions.ParsingException;
import net.dv8tion.jda.api.requests.Request;
import net.dv8tion.jda.api.requests.Response;
import net.dv8tion.jda.api.requests.restaction.pagination.ReactionPaginationAction;
import net.dv8tion.jda.api.utils.data.DataArray;
import net.dv8tion.jda.internal.entities.EntityBuilder;
import net.dv8tion.jda.internal.entities.UserImpl;
import net.dv8tion.jda.internal.requests.Route;
import net.dv8tion.jda.internal.requests.restaction.pagination.PaginationActionImpl;
import net.dv8tion.jda.internal.utils.EncodingUtil;

public class ReactionPaginationActionImpl
extends PaginationActionImpl<User, ReactionPaginationAction>
implements ReactionPaginationAction {
    protected final MessageReaction reaction;

    public ReactionPaginationActionImpl(MessageReaction reaction) {
        super(reaction.getJDA(), Route.Messages.GET_REACTION_USERS.compile(reaction.getChannel().getId(), reaction.getMessageId(), ReactionPaginationActionImpl.getCode(reaction)), 1, 100, 100);
        this.reaction = reaction;
    }

    public ReactionPaginationActionImpl(Message message, String code) {
        super(message.getJDA(), Route.Messages.GET_REACTION_USERS.compile(message.getChannel().getId(), message.getId(), code), 1, 100, 100);
        this.reaction = null;
    }

    public ReactionPaginationActionImpl(MessageChannel channel, String messageId, String code) {
        super(channel.getJDA(), Route.Messages.GET_REACTION_USERS.compile(channel.getId(), messageId, code), 1, 100, 100);
        this.reaction = null;
    }

    protected static String getCode(MessageReaction reaction) {
        MessageReaction.ReactionEmote emote = reaction.getReactionEmote();
        return emote.isEmote() ? emote.getName() + ":" + emote.getId() : EncodingUtil.encodeUTF8(emote.getName());
    }

    @Override
    @Nonnull
    public MessageReaction getReaction() {
        if (this.reaction == null) {
            throw new IllegalStateException("Cannot get reaction for this action");
        }
        return this.reaction;
    }

    @Override
    protected Route.CompiledRoute finalizeRoute() {
        Route.CompiledRoute route = super.finalizeRoute();
        String after = null;
        String limit = String.valueOf(this.getLimit());
        long last = this.lastKey;
        if (last != 0L) {
            after = Long.toUnsignedString(last);
        }
        route = route.withQueryParams("limit", limit);
        if (after != null) {
            route = route.withQueryParams("after", after);
        }
        return route;
    }

    @Override
    protected void handleSuccess(Response response, Request<List<User>> request) {
        EntityBuilder builder = this.api.getEntityBuilder();
        DataArray array = response.getArray();
        LinkedList<UserImpl> users = new LinkedList<UserImpl>();
        for (int i2 = 0; i2 < array.length(); ++i2) {
            try {
                UserImpl user = builder.createUser(array.getObject(i2));
                users.add(user);
                if (this.useCache) {
                    this.cached.add(user);
                }
                this.last = user;
                this.lastKey = ((User)this.last).getIdLong();
                continue;
            }
            catch (NullPointerException | ParsingException e2) {
                LOG.warn("Encountered exception in ReactionPagination", e2);
            }
        }
        request.onSuccess(users);
    }

    @Override
    protected long getKey(User it2) {
        return it2.getIdLong();
    }
}

