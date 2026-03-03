/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.internal.handle;

import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.events.channel.category.CategoryCreateEvent;
import net.dv8tion.jda.api.events.channel.store.StoreChannelCreateEvent;
import net.dv8tion.jda.api.events.channel.text.TextChannelCreateEvent;
import net.dv8tion.jda.api.events.channel.voice.VoiceChannelCreateEvent;
import net.dv8tion.jda.api.utils.data.DataObject;
import net.dv8tion.jda.internal.JDAImpl;
import net.dv8tion.jda.internal.entities.EntityBuilder;
import net.dv8tion.jda.internal.handle.SocketHandler;
import net.dv8tion.jda.internal.requests.WebSocketClient;

public class ChannelCreateHandler
extends SocketHandler {
    public ChannelCreateHandler(JDAImpl api2) {
        super(api2);
    }

    @Override
    protected Long handleInternally(DataObject content) {
        ChannelType type = ChannelType.fromId(content.getInt("type"));
        long guildId = 0L;
        JDAImpl jda = this.getJDA();
        if (type.isGuild()) {
            guildId = content.getLong("guild_id");
            if (jda.getGuildSetupController().isLocked(guildId)) {
                return guildId;
            }
        }
        EntityBuilder builder = jda.getEntityBuilder();
        switch (type) {
            case STORE: {
                builder.createStoreChannel(content, guildId);
                jda.handleEvent(new StoreChannelCreateEvent(jda, this.responseNumber, builder.createStoreChannel(content, guildId)));
                break;
            }
            case TEXT: {
                jda.handleEvent(new TextChannelCreateEvent(jda, this.responseNumber, builder.createTextChannel(content, guildId)));
                break;
            }
            case VOICE: {
                jda.handleEvent(new VoiceChannelCreateEvent(jda, this.responseNumber, builder.createVoiceChannel(content, guildId)));
                break;
            }
            case CATEGORY: {
                jda.handleEvent(new CategoryCreateEvent(jda, this.responseNumber, builder.createCategory(content, guildId)));
                break;
            }
            case GROUP: {
                WebSocketClient.LOG.warn("Received a CREATE_CHANNEL for a group which is not supported");
                return null;
            }
            default: {
                WebSocketClient.LOG.debug("Discord provided an CREATE_CHANNEL event with an unknown channel type! JSON: {}", (Object)content);
            }
        }
        return null;
    }
}

