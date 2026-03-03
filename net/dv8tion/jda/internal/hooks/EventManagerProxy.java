/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.internal.hooks;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.RejectedExecutionException;
import javax.annotation.Nonnull;
import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.hooks.IEventManager;
import net.dv8tion.jda.api.hooks.InterfacedEventManager;
import net.dv8tion.jda.internal.JDAImpl;

public class EventManagerProxy
implements IEventManager {
    private final ExecutorService executor;
    private IEventManager subject;

    public EventManagerProxy(IEventManager subject, ExecutorService executor) {
        this.subject = subject;
        this.executor = executor;
    }

    public void setSubject(IEventManager subject) {
        this.subject = subject == null ? new InterfacedEventManager() : subject;
    }

    public IEventManager getSubject() {
        return this.subject;
    }

    @Override
    public void register(@Nonnull Object listener) {
        this.subject.register(listener);
    }

    @Override
    public void unregister(@Nonnull Object listener) {
        this.subject.unregister(listener);
    }

    @Override
    public void handle(@Nonnull GenericEvent event) {
        try {
            if (this.executor != null && !this.executor.isShutdown()) {
                this.executor.execute(() -> this.handleInternally(event));
            } else {
                this.handleInternally(event);
            }
        }
        catch (RejectedExecutionException ex2) {
            JDAImpl.LOG.warn("Event-Pool rejected event execution! Running on handling thread instead...");
            this.handleInternally(event);
        }
        catch (Exception ex3) {
            JDAImpl.LOG.error("Encountered exception trying to schedule event", ex3);
        }
    }

    private void handleInternally(@Nonnull GenericEvent event) {
        try {
            this.subject.handle(event);
        }
        catch (RuntimeException e2) {
            JDAImpl.LOG.error("The EventManager.handle() call had an uncaught exception", e2);
        }
    }

    @Override
    @Nonnull
    public List<Object> getRegisteredListeners() {
        return this.subject.getRegisteredListeners();
    }
}

