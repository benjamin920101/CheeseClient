/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.events.message.guild;

import javax.annotation.Nonnull;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.guild.GenericGuildEvent;

public abstract class GenericGuildMessageEvent
extends GenericGuildEvent {
    protected final long messageId;
    protected final TextChannel channel;

    public GenericGuildMessageEvent(@Nonnull JDA api2, long responseNumber, long messageId, @Nonnull TextChannel channel) {
        super(api2, responseNumber, channel.getGuild());
        this.messageId = messageId;
        this.channel = channel;
    }

    @Nonnull
    public String getMessageId() {
        return Long.toUnsignedString(this.messageId);
    }

    public long getMessageIdLong() {
        return this.messageId;
    }

    @Nonnull
    public TextChannel getChannel() {
        return this.channel;
    }
}

