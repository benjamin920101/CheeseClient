/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.events.message;

import java.util.Collections;
import java.util.List;
import javax.annotation.Nonnull;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.message.GenericMessageEvent;

public class MessageEmbedEvent
extends GenericMessageEvent {
    private final List<MessageEmbed> embeds;

    public MessageEmbedEvent(@Nonnull JDA api2, long responseNumber, long messageId, @Nonnull MessageChannel channel, @Nonnull List<MessageEmbed> embeds) {
        super(api2, responseNumber, messageId, channel);
        this.embeds = Collections.unmodifiableList(embeds);
    }

    @Nonnull
    public List<MessageEmbed> getMessageEmbeds() {
        return this.embeds;
    }
}

