/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.events;

import javax.annotation.Nonnull;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.events.Event;

public class ResumedEvent
extends Event {
    public ResumedEvent(@Nonnull JDA api2, long responseNumber) {
        super(api2, responseNumber);
    }
}

