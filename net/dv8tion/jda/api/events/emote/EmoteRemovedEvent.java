/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.events.emote;

import javax.annotation.Nonnull;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Emote;
import net.dv8tion.jda.api.events.emote.GenericEmoteEvent;

public class EmoteRemovedEvent
extends GenericEmoteEvent {
    public EmoteRemovedEvent(@Nonnull JDA api2, long responseNumber, @Nonnull Emote emote) {
        super(api2, responseNumber, emote);
    }
}

