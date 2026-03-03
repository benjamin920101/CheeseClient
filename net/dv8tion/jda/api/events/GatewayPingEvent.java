/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.events;

import javax.annotation.Nonnull;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.events.Event;
import net.dv8tion.jda.api.events.UpdateEvent;

public class GatewayPingEvent
extends Event
implements UpdateEvent<JDA, Long> {
    public static final String IDENTIFIER = "gateway-ping";
    private final long next;
    private final long prev;

    public GatewayPingEvent(@Nonnull JDA api2, long old) {
        super(api2);
        this.next = api2.getGatewayPing();
        this.prev = old;
    }

    public long getNewPing() {
        return this.next;
    }

    public long getOldPing() {
        return this.prev;
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
    public Long getOldValue() {
        return this.prev;
    }

    @Override
    @Nonnull
    public Long getNewValue() {
        return this.next;
    }

    public String toString() {
        return "GatewayUpdate[ping](" + this.getOldValue() + "->" + this.getNewValue() + ')';
    }
}

