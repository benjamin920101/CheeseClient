/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.events;

import javax.annotation.Nonnull;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.events.Event;
import net.dv8tion.jda.api.utils.data.DataObject;

public class RawGatewayEvent
extends Event {
    private final DataObject data;

    public RawGatewayEvent(@Nonnull JDA api2, long responseNumber, @Nonnull DataObject data) {
        super(api2, responseNumber);
        this.data = data;
    }

    @Nonnull
    public DataObject getPackage() {
        return this.data;
    }

    @Nonnull
    public DataObject getPayload() {
        return this.data.getObject("d");
    }

    @Nonnull
    public String getType() {
        return this.data.getString("t");
    }
}

