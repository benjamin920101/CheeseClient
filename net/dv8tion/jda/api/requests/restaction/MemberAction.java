/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.requests.restaction;

import java.util.Collection;
import java.util.concurrent.TimeUnit;
import java.util.function.BooleanSupplier;
import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.requests.RestAction;

public interface MemberAction
extends RestAction<Void> {
    @Nonnull
    public MemberAction setCheck(@Nullable BooleanSupplier var1);

    @Nonnull
    public MemberAction timeout(long var1, @Nonnull TimeUnit var3);

    @Nonnull
    public MemberAction deadline(long var1);

    @Nonnull
    public String getAccessToken();

    @Nonnull
    public String getUserId();

    @Nullable
    public User getUser();

    @Nonnull
    public Guild getGuild();

    @Nonnull
    @CheckReturnValue
    public MemberAction setNickname(@Nullable String var1);

    @Nonnull
    @CheckReturnValue
    public MemberAction setRoles(@Nullable Collection<Role> var1);

    @Nonnull
    @CheckReturnValue
    public MemberAction setRoles(Role ... var1);

    @Nonnull
    @CheckReturnValue
    public MemberAction setMute(boolean var1);

    @Nonnull
    @CheckReturnValue
    public MemberAction setDeafen(boolean var1);
}

