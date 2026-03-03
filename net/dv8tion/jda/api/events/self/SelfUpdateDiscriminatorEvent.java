/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.events.self;

import javax.annotation.Nonnull;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.events.self.GenericSelfUpdateEvent;

public class SelfUpdateDiscriminatorEvent
extends GenericSelfUpdateEvent<String> {
    public static final String IDENTIFIER = "discriminator";

    public SelfUpdateDiscriminatorEvent(@Nonnull JDA api2, long responseNumber, @Nonnull String oldDiscriminator) {
        super(api2, responseNumber, oldDiscriminator, api2.getSelfUser().getDiscriminator(), IDENTIFIER);
    }

    @Nonnull
    public String getOldDiscriminator() {
        return this.getOldValue();
    }

    @Nonnull
    public String getNewDiscriminator() {
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

