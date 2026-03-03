/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.events;

import javax.annotation.Nonnull;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.events.Event;
import net.dv8tion.jda.api.events.UpdateEvent;

public class StatusChangeEvent
extends Event
implements UpdateEvent<JDA, JDA.Status> {
    public static final String IDENTIFIER = "status";
    protected final JDA.Status newStatus;
    protected final JDA.Status oldStatus;

    public StatusChangeEvent(@Nonnull JDA api2, @Nonnull JDA.Status newStatus, @Nonnull JDA.Status oldStatus) {
        super(api2);
        this.newStatus = newStatus;
        this.oldStatus = oldStatus;
    }

    @Nonnull
    public JDA.Status getNewStatus() {
        return this.newStatus;
    }

    @Nonnull
    public JDA.Status getOldStatus() {
        return this.oldStatus;
    }

    @Override
    @Nonnull
    public String getPropertyIdentifier() {
        return IDENTIFIER;
    }

    @Override
    @Nonnull
    public JDA getEntity() {
        return this.getJDA();
    }

    @Override
    @Nonnull
    public JDA.Status getOldValue() {
        return this.oldStatus;
    }

    @Override
    @Nonnull
    public JDA.Status getNewValue() {
        return this.newStatus;
    }

    public String toString() {
        return "StatusUpdate(" + (Object)((Object)this.getOldStatus()) + "->" + (Object)((Object)this.getNewStatus()) + ')';
    }
}

