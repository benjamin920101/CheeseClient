/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.events;

import javax.annotation.Nonnull;
import net.dv8tion.jda.api.JDA;

public interface GenericEvent {
    @Nonnull
    public JDA getJDA();

    public long getResponseNumber();
}

