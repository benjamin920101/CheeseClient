/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.internal.handle;

import gnu.trove.iterator.TLongObjectIterator;
import gnu.trove.map.TLongObjectMap;
import gnu.trove.map.hash.TLongObjectHashMap;
import gnu.trove.set.TLongSet;
import gnu.trove.set.hash.TLongHashSet;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import javax.annotation.Nullable;
import net.dv8tion.jda.api.events.guild.GuildTimeoutEvent;
import net.dv8tion.jda.api.events.guild.UnavailableGuildLeaveEvent;
import net.dv8tion.jda.api.utils.MiscUtil;
import net.dv8tion.jda.api.utils.data.DataArray;
import net.dv8tion.jda.api.utils.data.DataObject;
import net.dv8tion.jda.internal.JDAImpl;
import net.dv8tion.jda.internal.handle.GuildSetupNode;
import net.dv8tion.jda.internal.requests.MemberChunkManager;
import net.dv8tion.jda.internal.requests.WebSocketClient;
import net.dv8tion.jda.internal.utils.JDALogger;
import org.slf4j.Logger;

public class GuildSetupController {
    protected static final Logger log = JDALogger.getLog(GuildSetupController.class);
    private static final long timeoutDuration = 75L;
    private static final int timeoutThreshold = 60;
    private final JDAImpl api;
    private final TLongObjectMap<GuildSetupNode> setupNodes = new TLongObjectHashMap<GuildSetupNode>();
    private final TLongSet chunkingGuilds = new TLongHashSet();
    private final TLongSet unavailableGuilds = new TLongHashSet();
    private int incompleteCount = 0;
    private Future<?> timeoutHandle;
    protected StatusListener listener = (id2, oldStatus, newStatus) -> log.trace("[{}] Updated status {}->{}", new Object[]{id2, oldStatus, newStatus});

    public GuildSetupController(JDAImpl api2) {
        this.api = api2;
    }

    JDAImpl getJDA() {
        return this.api;
    }

    void addGuildForChunking(long id2, boolean join) {
        log.trace("Adding guild for chunking ID: {}", (Object)id2);
        if (join || this.incompleteCount <= 0) {
            if (this.incompleteCount <= 0) {
                this.sendChunkRequest(id2);
                return;
            }
            ++this.incompleteCount;
        }
        this.chunkingGuilds.add(id2);
        this.tryChunking();
    }

    void remove(long id2) {
        this.unavailableGuilds.remove(id2);
        this.setupNodes.remove(id2);
        this.chunkingGuilds.remove(id2);
        this.checkReady();
    }

    public void ready(long id2) {
        this.remove(id2);
        --this.incompleteCount;
        this.checkReady();
    }

    private void checkReady() {
        WebSocketClient client = this.getJDA().getClient();
        if (this.incompleteCount < 1 && !client.isReady()) {
            if (this.timeoutHandle != null) {
                this.timeoutHandle.cancel(false);
            }
            this.timeoutHandle = null;
            client.ready();
        } else if (this.incompleteCount <= 60) {
            this.startTimeout();
        }
    }

    public boolean setIncompleteCount(int count) {
        this.incompleteCount = count;
        log.debug("Setting incomplete count to {}", (Object)this.incompleteCount);
        this.checkReady();
        return count != 0;
    }

    public void onReady(long id2, DataObject obj) {
        log.trace("Adding id to setup cache {}", (Object)id2);
        GuildSetupNode node = new GuildSetupNode(id2, this, GuildSetupNode.Type.INIT);
        this.setupNodes.put(id2, node);
        node.handleReady(obj);
        if (node.markedUnavailable) {
            --this.incompleteCount;
            this.tryChunking();
        }
    }

    public void onCreate(long id2, DataObject obj) {
        GuildSetupNode node;
        boolean available = obj.isNull("unavailable") || !obj.getBoolean("unavailable");
        log.trace("Received guild create for id: {} available: {}", (Object)id2, (Object)available);
        if (available && this.unavailableGuilds.contains(id2) && !this.setupNodes.containsKey(id2)) {
            this.unavailableGuilds.remove(id2);
            this.setupNodes.put(id2, new GuildSetupNode(id2, this, GuildSetupNode.Type.AVAILABLE));
        }
        if ((node = this.setupNodes.get(id2)) == null) {
            node = new GuildSetupNode(id2, this, GuildSetupNode.Type.JOIN);
            this.setupNodes.put(id2, node);
        } else if (node.markedUnavailable && available && this.incompleteCount > 0) {
            ++this.incompleteCount;
        }
        node.handleCreate(obj);
    }

    public boolean onDelete(long id2, DataObject obj) {
        boolean available;
        boolean bl2 = available = obj.isNull("unavailable") || !obj.getBoolean("unavailable");
        if (this.isUnavailable(id2) && available) {
            log.debug("Leaving unavailable guild with id {}", (Object)id2);
            this.remove(id2);
            this.api.getEventManager().handle(new UnavailableGuildLeaveEvent(this.api, this.api.getResponseTotal(), id2));
            return true;
        }
        GuildSetupNode node = this.setupNodes.get(id2);
        if (node == null) {
            return false;
        }
        log.debug("Received guild delete for id: {} available: {}", (Object)id2, (Object)available);
        if (!available) {
            if (!node.markedUnavailable) {
                node.markedUnavailable = true;
                if (this.incompleteCount > 0) {
                    this.chunkingGuilds.remove(id2);
                    --this.incompleteCount;
                }
            }
            node.reset();
        } else {
            node.cleanup();
            if (node.isJoin() && !node.requestedChunk) {
                this.remove(id2);
            } else {
                this.ready(id2);
            }
            this.api.getEventManager().handle(new UnavailableGuildLeaveEvent(this.api, this.api.getResponseTotal(), id2));
        }
        log.debug("Updated incompleteCount to {}", (Object)this.incompleteCount);
        this.checkReady();
        return true;
    }

    public void onMemberChunk(long id2, DataObject chunk) {
        DataArray members = chunk.getArray("members");
        int index = chunk.getInt("chunk_index");
        int count = chunk.getInt("chunk_count");
        log.debug("Received member chunk for guild id: {} size: {} index: {}/{}", id2, members.length(), index, count);
        GuildSetupNode node = this.setupNodes.get(id2);
        if (node != null) {
            node.handleMemberChunk(MemberChunkManager.isLastChunk(chunk), members);
        }
    }

    public boolean onAddMember(long id2, DataObject member) {
        GuildSetupNode node = this.setupNodes.get(id2);
        if (node == null) {
            return false;
        }
        log.debug("Received GUILD_MEMBER_ADD during setup, adding member to guild. GuildID: {}", (Object)id2);
        node.handleAddMember(member);
        return true;
    }

    public boolean onRemoveMember(long id2, DataObject member) {
        GuildSetupNode node = this.setupNodes.get(id2);
        if (node == null) {
            return false;
        }
        log.debug("Received GUILD_MEMBER_REMOVE during setup, removing member from guild. GuildID: {}", (Object)id2);
        node.handleRemoveMember(member);
        return true;
    }

    public void onSync(long id2, DataObject obj) {
        GuildSetupNode node = this.setupNodes.get(id2);
        if (node != null) {
            node.handleSync(obj);
        }
    }

    public boolean isLocked(long id2) {
        return this.setupNodes.containsKey(id2);
    }

    public boolean isUnavailable(long id2) {
        return this.unavailableGuilds.contains(id2);
    }

    public boolean isKnown(long id2) {
        return this.isLocked(id2) || this.isUnavailable(id2);
    }

    public void cacheEvent(long guildId, DataObject event) {
        GuildSetupNode node = this.setupNodes.get(guildId);
        if (node != null) {
            node.cacheEvent(event);
        } else {
            log.warn("Attempted to cache event for a guild that is not locked. {}", (Object)event, (Object)new IllegalStateException());
        }
    }

    public void clearCache() {
        this.setupNodes.clear();
        this.chunkingGuilds.clear();
        this.unavailableGuilds.clear();
        this.incompleteCount = 0;
        this.close();
    }

    public void close() {
        if (this.timeoutHandle != null) {
            this.timeoutHandle.cancel(false);
        }
        this.timeoutHandle = null;
    }

    public boolean containsMember(long userId, @Nullable GuildSetupNode excludedNode) {
        TLongObjectIterator<GuildSetupNode> it2 = this.setupNodes.iterator();
        while (it2.hasNext()) {
            it2.advance();
            GuildSetupNode node = it2.value();
            if (node == excludedNode || !node.containsMember(userId)) continue;
            return true;
        }
        return false;
    }

    public TLongSet getUnavailableGuilds() {
        return this.unavailableGuilds;
    }

    public Set<GuildSetupNode> getSetupNodes() {
        return new HashSet<GuildSetupNode>(this.setupNodes.valueCollection());
    }

    public Set<GuildSetupNode> getSetupNodes(Status status) {
        return this.getSetupNodes().stream().filter(node -> node.status == status).collect(Collectors.toSet());
    }

    public GuildSetupNode getSetupNodeById(long id2) {
        return this.setupNodes.get(id2);
    }

    public GuildSetupNode getSetupNodeById(String id2) {
        return this.getSetupNodeById(MiscUtil.parseSnowflake(id2));
    }

    public void setStatusListener(StatusListener listener) {
        this.listener = Objects.requireNonNull(listener);
    }

    int getIncompleteCount() {
        return this.incompleteCount;
    }

    int getChunkingCount() {
        return this.chunkingGuilds.size();
    }

    void sendChunkRequest(Object obj) {
        log.debug("Sending chunking requests for {} guilds", (Object)(obj instanceof DataArray ? ((DataArray)obj).length() : 1));
        this.getJDA().getClient().sendChunkRequest(DataObject.empty().put("guild_id", obj).put("query", "").put("limit", 0));
    }

    private void tryChunking() {
        this.chunkingGuilds.forEach(id2 -> {
            this.sendChunkRequest(id2);
            return true;
        });
        this.chunkingGuilds.clear();
    }

    private void startTimeout() {
        if (this.timeoutHandle != null || this.incompleteCount < 1) {
            return;
        }
        log.debug("Starting {} second timeout for {} guilds", (Object)75L, (Object)this.incompleteCount);
        this.timeoutHandle = this.getJDA().getGatewayPool().schedule(this::onTimeout, 75L, TimeUnit.SECONDS);
    }

    public void onUnavailable(long id2) {
        this.unavailableGuilds.add(id2);
        log.debug("Guild with id {} is now marked unavailable. Total: {}", (Object)id2, (Object)this.unavailableGuilds.size());
    }

    public void onTimeout() {
        if (this.incompleteCount < 1) {
            return;
        }
        log.warn("Automatically marking {} guilds as unavailable due to timeout!", (Object)this.incompleteCount);
        TLongObjectIterator<GuildSetupNode> iterator = this.setupNodes.iterator();
        while (iterator.hasNext()) {
            iterator.advance();
            GuildSetupNode node = iterator.value();
            iterator.remove();
            this.unavailableGuilds.add(node.getIdLong());
            this.getJDA().handleEvent(new GuildTimeoutEvent(this.getJDA(), node.getIdLong()));
        }
        this.incompleteCount = 0;
        this.checkReady();
    }

    @FunctionalInterface
    public static interface StatusListener {
        public void onStatusChange(long var1, Status var3, Status var4);
    }

    public static enum Status {
        INIT,
        CHUNKING,
        BUILDING,
        READY,
        UNAVAILABLE,
        REMOVED;

    }
}

