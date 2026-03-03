/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.internal.handle;

import gnu.trove.iterator.TLongIterator;
import gnu.trove.iterator.TLongObjectIterator;
import gnu.trove.map.TLongObjectMap;
import gnu.trove.map.hash.TLongObjectHashMap;
import gnu.trove.set.TLongSet;
import gnu.trove.set.hash.TLongHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import javax.annotation.Nullable;
import net.dv8tion.jda.api.audio.hooks.ConnectionListener;
import net.dv8tion.jda.api.audio.hooks.ConnectionStatus;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.events.guild.GuildAvailableEvent;
import net.dv8tion.jda.api.events.guild.GuildJoinEvent;
import net.dv8tion.jda.api.events.guild.GuildReadyEvent;
import net.dv8tion.jda.api.events.guild.UnavailableGuildJoinedEvent;
import net.dv8tion.jda.api.managers.AudioManager;
import net.dv8tion.jda.api.utils.data.DataArray;
import net.dv8tion.jda.api.utils.data.DataObject;
import net.dv8tion.jda.internal.JDAImpl;
import net.dv8tion.jda.internal.entities.GuildImpl;
import net.dv8tion.jda.internal.handle.EventCache;
import net.dv8tion.jda.internal.handle.GuildSetupController;
import net.dv8tion.jda.internal.managers.AudioManagerImpl;
import net.dv8tion.jda.internal.utils.UnlockHook;
import net.dv8tion.jda.internal.utils.cache.AbstractCacheView;

public class GuildSetupNode {
    private final long id;
    private final GuildSetupController controller;
    private final List<DataObject> cachedEvents = new LinkedList<DataObject>();
    private TLongObjectMap<DataObject> members;
    private TLongSet removedMembers;
    private DataObject partialGuild;
    private int expectedMemberCount = 1;
    boolean requestedChunk;
    final Type type;
    boolean firedUnavailableJoin = false;
    boolean markedUnavailable = false;
    GuildSetupController.Status status = GuildSetupController.Status.INIT;

    GuildSetupNode(long id2, GuildSetupController controller, Type type) {
        this.id = id2;
        this.controller = controller;
        this.type = type;
    }

    public long getIdLong() {
        return this.id;
    }

    public String getId() {
        return Long.toUnsignedString(this.id);
    }

    public GuildSetupController.Status getStatus() {
        return this.status;
    }

    @Nullable
    public DataObject getGuildPayload() {
        return this.partialGuild;
    }

    public int getExpectedMemberCount() {
        return this.expectedMemberCount;
    }

    public int getCurrentMemberCount() {
        TLongHashSet knownMembers = new TLongHashSet(this.members.keySet());
        knownMembers.removeAll(this.removedMembers);
        return knownMembers.size();
    }

    public Type getType() {
        return this.type;
    }

    public boolean isJoin() {
        return this.type == Type.JOIN;
    }

    public boolean isMarkedUnavailable() {
        return this.markedUnavailable;
    }

    public boolean requestedChunks() {
        return this.requestedChunk;
    }

    public boolean containsMember(long userId) {
        if (this.members == null || this.members.isEmpty()) {
            return false;
        }
        return this.members.containsKey(userId);
    }

    public String toString() {
        return "GuildSetupNode[" + this.id + "|" + (Object)((Object)this.status) + ']' + '{' + "expectedMemberCount=" + this.expectedMemberCount + ", requestedChunk=" + this.requestedChunk + ", type=" + (Object)((Object)this.type) + ", markedUnavailable=" + this.markedUnavailable + '}';
    }

    public int hashCode() {
        return Long.hashCode(this.id);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof GuildSetupNode)) {
            return false;
        }
        GuildSetupNode node = (GuildSetupNode)obj;
        return node.id == this.id;
    }

    private GuildSetupController getController() {
        return this.controller;
    }

    void updateStatus(GuildSetupController.Status status) {
        if (status == this.status) {
            return;
        }
        try {
            this.getController().listener.onStatusChange(this.id, this.status, status);
        }
        catch (Exception ex2) {
            GuildSetupController.log.error("Uncaught exception in status listener", ex2);
        }
        this.status = status;
    }

    void reset() {
        this.updateStatus(GuildSetupController.Status.UNAVAILABLE);
        this.expectedMemberCount = 1;
        this.partialGuild = null;
        this.requestedChunk = false;
        if (this.members != null) {
            this.members.clear();
        }
        if (this.removedMembers != null) {
            this.removedMembers.clear();
        }
        this.cachedEvents.clear();
    }

    void handleReady(DataObject obj) {
    }

    void handleCreate(DataObject obj) {
        if (this.partialGuild == null) {
            this.partialGuild = obj;
        } else {
            for (String key : obj.keys()) {
                this.partialGuild.put(key, obj.opt(key).orElse(null));
            }
        }
        boolean unavailable = this.partialGuild.getBoolean("unavailable");
        boolean wasMarkedUnavailable = this.markedUnavailable;
        this.markedUnavailable = unavailable;
        if (unavailable) {
            if (!this.firedUnavailableJoin && this.isJoin()) {
                this.firedUnavailableJoin = true;
                JDAImpl api2 = this.getController().getJDA();
                api2.handleEvent(new UnavailableGuildJoinedEvent(api2, api2.getResponseTotal(), this.id));
            }
            return;
        }
        this.ensureMembers();
    }

    void handleSync(DataObject obj) {
        if (this.partialGuild == null) {
            GuildSetupController.log.debug("Dropping sync update due to unavailable guild");
            return;
        }
        for (String key : obj.keys()) {
            this.partialGuild.put(key, obj.opt(key).orElse(null));
        }
        this.ensureMembers();
    }

    boolean handleMemberChunk(boolean last, DataArray arr2) {
        if (this.partialGuild == null) {
            GuildSetupController.log.debug("Dropping member chunk due to unavailable guild");
            return true;
        }
        for (int index = 0; index < arr2.length(); ++index) {
            DataObject obj = arr2.getObject(index);
            long id2 = obj.getObject("user").getLong("id");
            this.members.put(id2, obj);
        }
        if (last || this.members.size() >= this.expectedMemberCount || !this.getController().getJDA().chunkGuild(this.id)) {
            this.completeSetup();
            return false;
        }
        return true;
    }

    void handleAddMember(DataObject member) {
        if (this.members == null || this.removedMembers == null) {
            return;
        }
        ++this.expectedMemberCount;
        long userId = member.getObject("user").getLong("id");
        this.members.put(userId, member);
        this.removedMembers.remove(userId);
    }

    void handleRemoveMember(DataObject member) {
        if (this.members == null || this.removedMembers == null) {
            return;
        }
        --this.expectedMemberCount;
        long userId = member.getObject("user").getLong("id");
        this.members.remove(userId);
        this.removedMembers.add(userId);
        EventCache eventCache = this.getController().getJDA().getEventCache();
        if (!this.getController().containsMember(userId, this)) {
            eventCache.clear(EventCache.Type.USER, userId);
        }
    }

    void cacheEvent(DataObject event) {
        GuildSetupController.log.trace("Caching {} event during init. GuildId: {}", (Object)event.getString("t"), (Object)this.id);
        this.cachedEvents.add(event);
        int cacheSize = this.cachedEvents.size();
        if (cacheSize >= 2000 && cacheSize % 1000 == 0) {
            GuildSetupController controller = this.getController();
            GuildSetupController.log.warn("Accumulating suspicious amounts of cached events during guild setup, something might be wrong. Cached: {} Members: {}/{} Status: {} GuildId: {} Incomplete: {}/{}", new Object[]{cacheSize, this.getCurrentMemberCount(), this.getExpectedMemberCount(), this.status, this.id, controller.getChunkingCount(), controller.getIncompleteCount()});
            if (this.status == GuildSetupController.Status.CHUNKING) {
                GuildSetupController.log.debug("Forcing new chunk request for guild: {}", (Object)this.id);
                controller.sendChunkRequest(this.id);
            }
        }
    }

    void cleanup() {
        this.updateStatus(GuildSetupController.Status.REMOVED);
        EventCache eventCache = this.getController().getJDA().getEventCache();
        eventCache.clear(EventCache.Type.GUILD, this.id);
        if (this.partialGuild == null) {
            return;
        }
        Optional<DataArray> channels = this.partialGuild.optArray("channels");
        Optional<DataArray> roles = this.partialGuild.optArray("roles");
        channels.ifPresent(arr2 -> {
            for (int i2 = 0; i2 < arr2.length(); ++i2) {
                DataObject json = arr2.getObject(i2);
                long id2 = json.getLong("id");
                eventCache.clear(EventCache.Type.CHANNEL, id2);
            }
        });
        roles.ifPresent(arr2 -> {
            for (int i2 = 0; i2 < arr2.length(); ++i2) {
                DataObject json = arr2.getObject(i2);
                long id2 = json.getLong("id");
                eventCache.clear(EventCache.Type.ROLE, id2);
            }
        });
        if (this.members != null) {
            TLongObjectIterator<DataObject> it2 = this.members.iterator();
            while (it2.hasNext()) {
                it2.advance();
                long userId = it2.key();
                if (this.getController().containsMember(userId, this)) continue;
                eventCache.clear(EventCache.Type.USER, userId);
            }
        }
    }

    private void completeSetup() {
        this.updateStatus(GuildSetupController.Status.BUILDING);
        JDAImpl api2 = this.getController().getJDA();
        TLongIterator it2 = this.removedMembers.iterator();
        while (it2.hasNext()) {
            this.members.remove(it2.next());
        }
        this.removedMembers.clear();
        GuildImpl guild = api2.getEntityBuilder().createGuild(this.id, this.partialGuild, this.members, this.expectedMemberCount);
        this.updateAudioManagerReference(guild);
        switch (this.type) {
            case AVAILABLE: {
                api2.handleEvent(new GuildAvailableEvent(api2, api2.getResponseTotal(), guild));
                this.getController().remove(this.id);
                break;
            }
            case JOIN: {
                api2.handleEvent(new GuildJoinEvent(api2, api2.getResponseTotal(), guild));
                if (this.requestedChunk) {
                    this.getController().ready(this.id);
                    break;
                }
                this.getController().remove(this.id);
                break;
            }
            default: {
                api2.handleEvent(new GuildReadyEvent(api2, api2.getResponseTotal(), guild));
                this.getController().ready(this.id);
            }
        }
        this.updateStatus(GuildSetupController.Status.READY);
        GuildSetupController.log.debug("Finished setup for guild {} firing cached events {}", (Object)this.id, (Object)this.cachedEvents.size());
        api2.getClient().handle(this.cachedEvents);
        api2.getEventCache().playbackCache(EventCache.Type.GUILD, this.id);
    }

    private void ensureMembers() {
        this.expectedMemberCount = this.partialGuild.getInt("member_count");
        this.members = new TLongObjectHashMap<DataObject>(this.expectedMemberCount);
        this.removedMembers = new TLongHashSet();
        DataArray memberArray = this.partialGuild.getArray("members");
        if (!this.getController().getJDA().chunkGuild(this.id)) {
            this.handleMemberChunk(true, memberArray);
        } else if (memberArray.length() < this.expectedMemberCount && !this.requestedChunk) {
            this.updateStatus(GuildSetupController.Status.CHUNKING);
            this.getController().addGuildForChunking(this.id, this.isJoin());
            this.requestedChunk = true;
        } else if (this.handleMemberChunk(false, memberArray) && !this.requestedChunk) {
            GuildSetupController.log.trace("Received suspicious members with a guild payload. Attempting to chunk. member_count: {} members: {} actual_members: {} guild_id: {}", this.expectedMemberCount, memberArray.length(), this.members.size(), this.id);
            this.members.clear();
            this.updateStatus(GuildSetupController.Status.CHUNKING);
            this.getController().addGuildForChunking(this.id, this.isJoin());
            this.requestedChunk = true;
        }
    }

    private void updateAudioManagerReference(GuildImpl guild) {
        JDAImpl api2 = this.getController().getJDA();
        AbstractCacheView<AudioManager> managerView = api2.getAudioManagersView();
        try (UnlockHook hook = managerView.writeLock();){
            TLongObjectMap<AudioManager> audioManagerMap = managerView.getMap();
            AudioManagerImpl mng = (AudioManagerImpl)audioManagerMap.get(this.id);
            if (mng == null) {
                return;
            }
            ConnectionListener listener = mng.getConnectionListener();
            AudioManagerImpl newMng = new AudioManagerImpl(guild);
            newMng.setSelfMuted(mng.isSelfMuted());
            newMng.setSelfDeafened(mng.isSelfDeafened());
            newMng.setQueueTimeout(mng.getConnectTimeout());
            newMng.setSendingHandler(mng.getSendingHandler());
            newMng.setReceivingHandler(mng.getReceivingHandler());
            newMng.setConnectionListener(listener);
            newMng.setAutoReconnect(mng.isAutoReconnect());
            if (mng.isConnected()) {
                long channelId = mng.getConnectedChannel().getIdLong();
                VoiceChannel channel = api2.getVoiceChannelById(channelId);
                if (channel != null) {
                    if (mng.isConnected()) {
                        mng.closeAudioConnection(ConnectionStatus.ERROR_CANNOT_RESUME);
                    }
                } else {
                    api2.getClient().removeAudioConnection(this.id);
                    if (listener != null) {
                        listener.onStatusChange(ConnectionStatus.DISCONNECTED_CHANNEL_DELETED);
                    }
                }
            }
            audioManagerMap.put(this.id, newMng);
        }
    }

    public static enum Type {
        INIT,
        JOIN,
        AVAILABLE;

    }
}

