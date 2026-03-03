/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.hooks;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.annotation.Nonnull;
import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.hooks.EventListener;
import net.dv8tion.jda.api.hooks.IEventManager;
import net.dv8tion.jda.internal.JDAImpl;
import net.dv8tion.jda.internal.utils.JDALogger;

public class InterfacedEventManager
implements IEventManager {
    private final CopyOnWriteArrayList<EventListener> listeners = new CopyOnWriteArrayList();

    @Override
    public void register(@Nonnull Object listener) {
        if (!(listener instanceof EventListener)) {
            throw new IllegalArgumentException("Listener must implement EventListener");
        }
        this.listeners.add((EventListener)listener);
    }

    @Override
    public void unregister(@Nonnull Object listener) {
        if (!(listener instanceof EventListener)) {
            JDALogger.getLog(this.getClass()).warn("Trying to remove a listener that does not implement EventListener: {}", (Object)(listener == null ? "null" : listener.getClass().getName()));
        }
        this.listeners.remove(listener);
    }

    @Override
    @Nonnull
    public List<Object> getRegisteredListeners() {
        return Collections.unmodifiableList(new ArrayList<EventListener>(this.listeners));
    }

    @Override
    public void handle(@Nonnull GenericEvent event) {
        for (EventListener listener : this.listeners) {
            try {
                listener.onEvent(event);
            }
            catch (Throwable throwable) {
                JDAImpl.LOG.error("One of the EventListeners had an uncaught exception", throwable);
                if (!(throwable instanceof Error)) continue;
                throw (Error)throwable;
            }
        }
    }
}

