/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.events.channel.text.update;

import javax.annotation.Nonnull;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.channel.text.update.GenericTextChannelUpdateEvent;

public class TextChannelUpdateNSFWEvent
extends GenericTextChannelUpdateEvent<Boolean> {
    public static final String IDENTIFIER = "nsfw";

    public TextChannelUpdateNSFWEvent(@Nonnull JDA api2, long responseNumber, @Nonnull TextChannel channel, boolean oldNsfw) {
        super(api2, responseNumber, channel, oldNsfw, channel.isNSFW(), IDENTIFIER);
    }

    public boolean getOldNSFW() {
        return (Boolean)this.getOldValue();
    }
}

