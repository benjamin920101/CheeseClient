/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.requests.restaction.pagination;

import javax.annotation.Nonnull;
import net.dv8tion.jda.api.entities.MessageReaction;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.requests.restaction.pagination.PaginationAction;

public interface ReactionPaginationAction
extends PaginationAction<User, ReactionPaginationAction> {
    @Nonnull
    public MessageReaction getReaction();
}

