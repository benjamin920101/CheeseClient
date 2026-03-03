/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.hooks;

import java.util.List;
import javax.annotation.Nonnull;
import net.dv8tion.jda.api.events.GenericEvent;

public interface IEventManager {
    public void register(@Nonnull Object var1);

    public void unregister(@Nonnull Object var1);

    public void handle(@Nonnull GenericEvent var1);

    @Nonnull
    public List<Object> getRegisteredListeners();
}

