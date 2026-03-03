/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.managers;

import java.util.Set;
import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.dv8tion.jda.api.entities.Emote;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.managers.Manager;

public interface EmoteManager
extends Manager<EmoteManager> {
    public static final long NAME = 1L;
    public static final long ROLES = 2L;

    @Override
    @Nonnull
    public EmoteManager reset(long var1);

    @Override
    @Nonnull
    public EmoteManager reset(long ... var1);

    @Nonnull
    default public Guild getGuild() {
        return this.getEmote().getGuild();
    }

    @Nonnull
    public Emote getEmote();

    @Nonnull
    @CheckReturnValue
    public EmoteManager setName(@Nonnull String var1);

    @Nonnull
    @CheckReturnValue
    public EmoteManager setRoles(@Nullable Set<Role> var1);
}

