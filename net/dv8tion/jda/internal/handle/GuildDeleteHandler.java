/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.internal.handle;

import gnu.trove.set.TLongSet;
import net.dv8tion.jda.api.audio.hooks.ConnectionStatus;
import net.dv8tion.jda.api.entities.Category;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.StoreChannel;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.events.guild.GuildLeaveEvent;
import net.dv8tion.jda.api.events.guild.GuildUnavailableEvent;
import net.dv8tion.jda.api.managers.AudioManager;
import net.dv8tion.jda.api.utils.data.DataObject;
import net.dv8tion.jda.internal.JDAImpl;
import net.dv8tion.jda.internal.entities.GuildImpl;
import net.dv8tion.jda.internal.handle.EventCache;
import net.dv8tion.jda.internal.handle.GuildSetupController;
import net.dv8tion.jda.internal.handle.SocketHandler;
import net.dv8tion.jda.internal.managers.AudioManagerImpl;
import net.dv8tion.jda.internal.requests.WebSocketClient;
import net.dv8tion.jda.internal.utils.UnlockHook;
import net.dv8tion.jda.internal.utils.cache.AbstractCacheView;
import net.dv8tion.jda.internal.utils.cache.SnowflakeCacheViewImpl;

public class GuildDeleteHandler
extends SocketHandler {
    public GuildDeleteHandler(JDAImpl api2) {
        super(api2);
    }

    @Override
    protected Long handleInternally(DataObject content) {
        long id2 = content.getLong("id");
        GuildSetupController setupController = this.getJDA().getGuildSetupController();
        boolean wasInit = setupController.onDelete(id2, content);
        if (wasInit || setupController.isUnavailable(id2)) {
            return null;
        }
        GuildImpl guild = (GuildImpl)this.getJDA().getGuildById(id2);
        boolean unavailable = content.getBoolean("unavailable");
        if (guild == null) {
            WebSocketClient.LOG.debug("Received GUILD_DELETE for a Guild that is not currently cached. ID: {} unavailable: {}", (Object)id2, (Object)unavailable);
            return null;
        }
        if (setupController.isUnavailable(id2) && unavailable) {
            return null;
        }
        SnowflakeCacheViewImpl<Guild> guildView = this.getJDA().getGuildsView();
        SnowflakeCacheViewImpl<StoreChannel> storeView = this.getJDA().getStoreChannelsView();
        SnowflakeCacheViewImpl<TextChannel> textView = this.getJDA().getTextChannelsView();
        SnowflakeCacheViewImpl<VoiceChannel> voiceView = this.getJDA().getVoiceChannelsView();
        SnowflakeCacheViewImpl<Category> categoryView = this.getJDA().getCategoriesView();
        guildView.remove(id2);
        try (UnlockHook hook = storeView.writeLock();){
            guild.getStoreChannelCache().forEachUnordered(chan -> storeView.getMap().remove(chan.getIdLong()));
        }
        hook = textView.writeLock();
        try {
            guild.getTextChannelCache().forEachUnordered(chan -> textView.getMap().remove(chan.getIdLong()));
        }
        finally {
            if (hook != null) {
                hook.close();
            }
        }
        hook = voiceView.writeLock();
        try {
            guild.getVoiceChannelCache().forEachUnordered(chan -> voiceView.getMap().remove(chan.getIdLong()));
        }
        finally {
            if (hook != null) {
                hook.close();
            }
        }
        hook = categoryView.writeLock();
        try {
            guild.getCategoryCache().forEachUnordered(chan -> categoryView.getMap().remove(chan.getIdLong()));
        }
        finally {
            if (hook != null) {
                hook.close();
            }
        }
        this.getJDA().getClient().removeAudioConnection(id2);
        AbstractCacheView<AudioManager> audioManagerView = this.getJDA().getAudioManagersView();
        AudioManagerImpl manager = (AudioManagerImpl)audioManagerView.get(id2);
        if (manager != null) {
            manager.closeAudioConnection(ConnectionStatus.DISCONNECTED_REMOVED_FROM_GUILD);
        }
        audioManagerView.remove(id2);
        TLongSet memberIds = guild.getMembersView().keySet();
        this.getJDA().getGuildCache().stream().map(GuildImpl.class::cast).forEach(g2 -> memberIds.removeAll(g2.getMembersView().keySet()));
        SnowflakeCacheViewImpl<User> userView = this.getJDA().getUsersView();
        try (UnlockHook hook = userView.writeLock();){
            long selfId = this.getJDA().getSelfUser().getIdLong();
            memberIds.forEach(memberId -> {
                if (memberId == selfId) {
                    return true;
                }
                userView.remove(memberId);
                this.getJDA().getEventCache().clear(EventCache.Type.USER, memberId);
                return true;
            });
        }
        if (unavailable) {
            setupController.onUnavailable(id2);
            guild.setAvailable(false);
            this.getJDA().handleEvent(new GuildUnavailableEvent(this.getJDA(), this.responseNumber, guild));
        } else {
            this.getJDA().handleEvent(new GuildLeaveEvent(this.getJDA(), this.responseNumber, guild));
        }
        this.getJDA().getEventCache().clear(EventCache.Type.GUILD, id2);
        return null;
    }
}

