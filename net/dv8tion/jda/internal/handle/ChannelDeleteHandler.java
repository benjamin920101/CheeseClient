/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.internal.handle;

import net.dv8tion.jda.api.entities.Category;
import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.entities.PrivateChannel;
import net.dv8tion.jda.api.entities.StoreChannel;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.events.channel.category.CategoryDeleteEvent;
import net.dv8tion.jda.api.events.channel.store.StoreChannelDeleteEvent;
import net.dv8tion.jda.api.events.channel.text.TextChannelDeleteEvent;
import net.dv8tion.jda.api.events.channel.voice.VoiceChannelDeleteEvent;
import net.dv8tion.jda.api.utils.data.DataObject;
import net.dv8tion.jda.internal.JDAImpl;
import net.dv8tion.jda.internal.entities.GuildImpl;
import net.dv8tion.jda.internal.handle.EventCache;
import net.dv8tion.jda.internal.handle.SocketHandler;
import net.dv8tion.jda.internal.requests.WebSocketClient;
import net.dv8tion.jda.internal.utils.cache.SnowflakeCacheViewImpl;

public class ChannelDeleteHandler
extends SocketHandler {
    public ChannelDeleteHandler(JDAImpl api2) {
        super(api2);
    }

    @Override
    protected Long handleInternally(DataObject content) {
        ChannelType type = ChannelType.fromId(content.getInt("type"));
        long guildId = 0L;
        if (type.isGuild()) {
            guildId = content.getLong("guild_id");
            if (this.getJDA().getGuildSetupController().isLocked(guildId)) {
                return guildId;
            }
        }
        GuildImpl guild = (GuildImpl)this.getJDA().getGuildById(guildId);
        long channelId = content.getLong("id");
        switch (type) {
            case STORE: {
                StoreChannel channel = (StoreChannel)this.getJDA().getStoreChannelsView().remove(channelId);
                if (channel == null || guild == null) {
                    WebSocketClient.LOG.debug("CHANNEL_DELETE attempted to delete a store channel that is not yet cached. JSON: {}", (Object)content);
                    return null;
                }
                guild.getStoreChannelView().remove(channelId);
                this.getJDA().handleEvent(new StoreChannelDeleteEvent(this.getJDA(), this.responseNumber, channel));
                break;
            }
            case TEXT: {
                TextChannel channel = (TextChannel)this.getJDA().getTextChannelsView().remove(channelId);
                if (channel == null || guild == null) {
                    WebSocketClient.LOG.debug("CHANNEL_DELETE attempted to delete a text channel that is not yet cached. JSON: {}", (Object)content);
                    return null;
                }
                guild.getTextChannelsView().remove(channel.getIdLong());
                this.getJDA().handleEvent(new TextChannelDeleteEvent(this.getJDA(), this.responseNumber, channel));
                break;
            }
            case VOICE: {
                VoiceChannel channel = (VoiceChannel)this.getJDA().getVoiceChannelsView().remove(channelId);
                if (channel == null || guild == null) {
                    WebSocketClient.LOG.debug("CHANNEL_DELETE attempted to delete a voice channel that is not yet cached. JSON: {}", (Object)content);
                    return null;
                }
                guild.getVoiceChannelsView().remove(channel.getIdLong());
                this.getJDA().handleEvent(new VoiceChannelDeleteEvent(this.getJDA(), this.responseNumber, channel));
                break;
            }
            case CATEGORY: {
                Category category = (Category)this.getJDA().getCategoriesView().remove(channelId);
                if (category == null || guild == null) {
                    WebSocketClient.LOG.debug("CHANNEL_DELETE attempted to delete a category channel that is not yet cached. JSON: {}", (Object)content);
                    return null;
                }
                guild.getCategoriesView().remove(channelId);
                this.getJDA().handleEvent(new CategoryDeleteEvent(this.getJDA(), this.responseNumber, category));
                break;
            }
            case PRIVATE: {
                SnowflakeCacheViewImpl<PrivateChannel> privateView = this.getJDA().getPrivateChannelsView();
                PrivateChannel channel = (PrivateChannel)privateView.remove(channelId);
                if (channel != null) break;
                WebSocketClient.LOG.debug("CHANNEL_DELETE attempted to delete a private channel that is not yet cached. JSON: {}", (Object)content);
                return null;
            }
            case GROUP: {
                WebSocketClient.LOG.warn("Received a CHANNEL_DELETE for a channel of type GROUP which is not supported!");
                return null;
            }
            default: {
                WebSocketClient.LOG.debug("CHANNEL_DELETE provided an unknown channel type. JSON: {}", (Object)content);
            }
        }
        this.getJDA().getEventCache().clear(EventCache.Type.CHANNEL, channelId);
        return null;
    }
}

