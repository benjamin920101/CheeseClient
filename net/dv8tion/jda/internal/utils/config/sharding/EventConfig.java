/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.internal.utils.config.sharding;

import java.util.ArrayList;
import java.util.List;
import java.util.function.IntFunction;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.dv8tion.jda.api.hooks.IEventManager;
import net.dv8tion.jda.internal.utils.Checks;

public class EventConfig {
    private final List<Object> listeners = new ArrayList<Object>();
    private final List<IntFunction<Object>> listenerProviders = new ArrayList<IntFunction<Object>>();
    private final IntFunction<? extends IEventManager> eventManagerProvider;

    public EventConfig(@Nullable IntFunction<? extends IEventManager> eventManagerProvider) {
        this.eventManagerProvider = eventManagerProvider;
    }

    public void addEventListener(@Nonnull Object listener) {
        Checks.notNull(listener, "Listener");
        this.listeners.add(listener);
    }

    public void removeEventListener(@Nonnull Object listener) {
        Checks.notNull(listener, "Listener");
        this.listeners.remove(listener);
    }

    public void addEventListenerProvider(@Nonnull IntFunction<Object> provider) {
        Checks.notNull(provider, "Provider");
        this.listenerProviders.add(provider);
    }

    public void removeEventListenerProvider(@Nonnull IntFunction<Object> provider) {
        Checks.notNull(provider, "Provider");
        this.listenerProviders.remove(provider);
    }

    @Nonnull
    public List<Object> getListeners() {
        return this.listeners;
    }

    @Nonnull
    public List<IntFunction<Object>> getListenerProviders() {
        return this.listenerProviders;
    }

    @Nullable
    public IntFunction<? extends IEventManager> getEventManagerProvider() {
        return this.eventManagerProvider;
    }

    @Nonnull
    public static EventConfig getDefault() {
        return new EventConfig(null);
    }
}

