/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.events.emote;

import javax.annotation.Nonnull;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Emote;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.Event;

public abstract class GenericEmoteEvent
extends Event {
    protected final Emote emote;

    public GenericEmoteEvent(@Nonnull JDA api2, long responseNumber, @Nonnull Emote emote) {
        super(api2, responseNumber);
        this.emote = emote;
    }

    @Nonnull
    public Guild getGuild() {
        return this.emote.getGuild();
    }

    @Nonnull
    public Emote getEmote() {
        return this.emote;
    }

    public boolean isManaged() {
        return this.emote.isManaged();
    }
}

