/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.events.self;

import javax.annotation.Nonnull;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.events.self.GenericSelfUpdateEvent;

public class SelfUpdateMFAEvent
extends GenericSelfUpdateEvent<Boolean> {
    public static final String IDENTIFIER = "mfa_enabled";

    public SelfUpdateMFAEvent(@Nonnull JDA api2, long responseNumber, boolean wasMfaEnabled) {
        super(api2, responseNumber, wasMfaEnabled, !wasMfaEnabled, IDENTIFIER);
    }

    public boolean wasMfaEnabled() {
        return this.getOldValue();
    }

    @Override
    @Nonnull
    public Boolean getOldValue() {
        return (Boolean)super.getOldValue();
    }

    @Override
    @Nonnull
    public Boolean getNewValue() {
        return (Boolean)super.getNewValue();
    }
}

