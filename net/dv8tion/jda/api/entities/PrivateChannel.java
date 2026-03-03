/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.entities;

import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnull;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.requests.RestAction;

public interface PrivateChannel
extends MessageChannel {
    @Nonnull
    public User getUser();

    @Nonnull
    @CheckReturnValue
    public RestAction<Void> close();
}

