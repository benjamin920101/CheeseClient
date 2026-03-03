/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.hooks;

import javax.annotation.Nonnull;
import net.dv8tion.jda.api.events.GenericEvent;

@FunctionalInterface
public interface EventListener {
    public void onEvent(@Nonnull GenericEvent var1);
}

