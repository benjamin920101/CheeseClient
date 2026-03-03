/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.entities;

import javax.annotation.Nonnull;
import net.dv8tion.jda.api.entities.Emote;
import net.dv8tion.jda.api.entities.User;

public interface ListedEmote
extends Emote {
    @Nonnull
    public User getUser();

    public boolean hasUser();
}

