/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.events.user.update;

import javax.annotation.Nonnull;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.GenericEvent;

public interface GenericUserPresenceEvent
extends GenericEvent {
    @Nonnull
    public Guild getGuild();

    @Nonnull
    public Member getMember();
}

