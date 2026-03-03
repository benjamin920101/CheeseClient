/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.events.self;

import javax.annotation.Nonnull;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.events.self.GenericSelfUpdateEvent;

public class SelfUpdateNameEvent
extends GenericSelfUpdateEvent<String> {
    public static final String IDENTIFIER = "name";

    public SelfUpdateNameEvent(@Nonnull JDA api2, long responseNumber, @Nonnull String oldName) {
        super(api2, responseNumber, oldName, api2.getSelfUser().getName(), IDENTIFIER);
    }

    @Nonnull
    public String getOldName() {
        return this.getOldValue();
    }

    @Nonnull
    public String getNewName() {
        return this.getNewValue();
    }

    @Override
    @Nonnull
    public String getOldValue() {
        return (String)super.getOldValue();
    }

    @Override
    @Nonnull
    public String getNewValue() {
        return (String)super.getNewValue();
    }
}

