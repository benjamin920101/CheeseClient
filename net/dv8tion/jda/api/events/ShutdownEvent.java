/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.events;

import java.time.OffsetDateTime;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.events.Event;
import net.dv8tion.jda.api.requests.CloseCode;

public class ShutdownEvent
extends Event {
    protected final OffsetDateTime shutdownTime;
    protected final int code;

    public ShutdownEvent(@Nonnull JDA api2, @Nonnull OffsetDateTime shutdownTime, int code) {
        super(api2);
        this.shutdownTime = shutdownTime;
        this.code = code;
    }

    @Nonnull
    public OffsetDateTime getTimeShutdown() {
        return this.shutdownTime;
    }

    @Nullable
    public CloseCode getCloseCode() {
        return CloseCode.from(this.code);
    }

    public int getCode() {
        return this.code;
    }
}

