/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.events.channel.priv;

import javax.annotation.Nonnull;
import net.dv8tion.jda.annotations.DeprecatedSince;
import net.dv8tion.jda.annotations.ForRemoval;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.PrivateChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.Event;

@Deprecated
@ForRemoval(deadline="4.4.0")
@DeprecatedSince(value="4.3.0")
public class PrivateChannelCreateEvent
extends Event {
    private final PrivateChannel channel;

    public PrivateChannelCreateEvent(@Nonnull JDA api2, long responseNumber, @Nonnull PrivateChannel channel) {
        super(api2, responseNumber);
        this.channel = channel;
    }

    @Nonnull
    public User getUser() {
        return this.channel.getUser();
    }

    @Nonnull
    public PrivateChannel getChannel() {
        return this.channel;
    }
}

