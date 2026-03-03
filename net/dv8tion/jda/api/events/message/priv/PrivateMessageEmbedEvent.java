/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.events.message.priv;

import java.util.List;
import javax.annotation.Nonnull;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.PrivateChannel;
import net.dv8tion.jda.api.events.message.priv.GenericPrivateMessageEvent;

public class PrivateMessageEmbedEvent
extends GenericPrivateMessageEvent {
    private final List<MessageEmbed> embeds;

    public PrivateMessageEmbedEvent(@Nonnull JDA api2, long responseNumber, long messageId, @Nonnull PrivateChannel channel, @Nonnull List<MessageEmbed> embeds) {
        super(api2, responseNumber, messageId, channel);
        this.embeds = embeds;
    }

    @Nonnull
    public List<MessageEmbed> getMessageEmbeds() {
        return this.embeds;
    }
}

