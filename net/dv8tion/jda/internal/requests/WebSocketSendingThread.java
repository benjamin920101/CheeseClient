/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.internal.requests;

import gnu.trove.map.TLongObjectMap;
import java.util.Queue;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.GuildVoiceState;
import net.dv8tion.jda.api.managers.AudioManager;
import net.dv8tion.jda.api.utils.data.DataObject;
import net.dv8tion.jda.internal.JDAImpl;
import net.dv8tion.jda.internal.audio.ConnectionRequest;
import net.dv8tion.jda.internal.audio.ConnectionStage;
import net.dv8tion.jda.internal.requests.WebSocketClient;
import org.slf4j.Logger;

class WebSocketSendingThread
implements Runnable {
    private static final Logger LOG = WebSocketClient.LOG;
    private final WebSocketClient client;
    private final JDAImpl api;
    private final ReentrantLock queueLock;
    private final Queue<DataObject> chunkQueue;
    private final Queue<DataObject> ratelimitQueue;
    private final TLongObjectMap<ConnectionRequest> queuedAudioConnections;
    private final ScheduledExecutorService executor;
    private Future<?> handle;
    private boolean needRateLimit = false;
    private boolean attemptedToSend = false;
    private boolean shutdown = false;

    WebSocketSendingThread(WebSocketClient client) {
        this.client = client;
        this.api = client.api;
        this.queueLock = client.queueLock;
        this.chunkQueue = client.chunkSyncQueue;
        this.ratelimitQueue = client.ratelimitQueue;
        this.queuedAudioConnections = client.queuedAudioConnections;
        this.executor = client.executor;
    }

    public void shutdown() {
        this.shutdown = true;
        if (this.handle != null) {
            this.handle.cancel(false);
        }
    }

    public void start() {
        this.shutdown = false;
        this.handle = this.executor.submit(this);
    }

    private void scheduleIdle() {
        if (this.shutdown) {
            return;
        }
        this.handle = this.executor.schedule(this, 500L, TimeUnit.MILLISECONDS);
    }

    private void scheduleSentMessage() {
        if (this.shutdown) {
            return;
        }
        this.handle = this.executor.schedule(this, 10L, TimeUnit.MILLISECONDS);
    }

    private void scheduleRateLimit() {
        if (this.shutdown) {
            return;
        }
        this.handle = this.executor.schedule(this, 1L, TimeUnit.MINUTES);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void run() {
        if (!this.client.sentAuthInfo) {
            this.scheduleIdle();
            return;
        }
        ConnectionRequest audioRequest = null;
        DataObject chunkRequest = null;
        try {
            this.api.setContext();
            this.attemptedToSend = false;
            this.needRateLimit = false;
            audioRequest = this.client.getNextAudioConnectRequest();
            if (!this.queueLock.tryLock() && !this.queueLock.tryLock(10L, TimeUnit.SECONDS)) {
                this.scheduleNext();
                return;
            }
            chunkRequest = this.chunkQueue.peek();
            if (chunkRequest != null) {
                this.handleChunkSync(chunkRequest);
            } else if (audioRequest != null) {
                this.handleAudioRequest(audioRequest);
            } else {
                this.handleNormalRequest();
            }
        }
        catch (InterruptedException ignored) {
            LOG.debug("Main WS send thread interrupted. Most likely JDA is disconnecting the websocket.");
            return;
        }
        catch (Throwable ex2) {
            LOG.error("Encountered error in gateway worker", ex2);
            if (!this.attemptedToSend) {
                if (chunkRequest != null) {
                    this.client.chunkSyncQueue.remove(chunkRequest);
                } else if (audioRequest != null) {
                    this.client.removeAudioConnection(audioRequest.getGuildIdLong());
                }
            }
            if (ex2 instanceof Error) {
                throw (Error)ex2;
            }
        }
        finally {
            this.client.maybeUnlock();
        }
        this.scheduleNext();
    }

    private void scheduleNext() {
        try {
            if (this.needRateLimit) {
                this.scheduleRateLimit();
            } else if (!this.attemptedToSend) {
                this.scheduleIdle();
            } else {
                this.scheduleSentMessage();
            }
        }
        catch (RejectedExecutionException ex2) {
            if (this.api.getStatus() == JDA.Status.SHUTTING_DOWN || this.api.getStatus() == JDA.Status.SHUTDOWN) {
                LOG.debug("Rejected task after shutdown", ex2);
            }
            LOG.error("Was unable to schedule next packet due to rejected execution by threadpool", ex2);
        }
    }

    private void handleChunkSync(DataObject chunkOrSyncRequest) {
        LOG.debug("Sending chunk/sync request {}", (Object)chunkOrSyncRequest);
        boolean success = this.send(DataObject.empty().put("op", 8).put("d", chunkOrSyncRequest));
        if (success) {
            this.chunkQueue.remove();
        }
    }

    private void handleAudioRequest(ConnectionRequest audioRequest) {
        DataObject packet;
        long channelId = audioRequest.getChannelId();
        long guildId = audioRequest.getGuildIdLong();
        Guild guild = this.api.getGuildById(guildId);
        if (guild == null) {
            LOG.debug("Discarding voice request due to null guild {}", (Object)guildId);
            this.queuedAudioConnections.remove(guildId);
            return;
        }
        ConnectionStage stage = audioRequest.getStage();
        AudioManager audioManager = guild.getAudioManager();
        switch (stage) {
            case RECONNECT: 
            case DISCONNECT: {
                packet = this.newVoiceClose(guildId);
                break;
            }
            default: {
                packet = this.newVoiceOpen(audioManager, channelId, guild.getIdLong());
            }
        }
        LOG.debug("Sending voice request {}", (Object)packet);
        if (this.send(packet)) {
            audioRequest.setNextAttemptEpoch(System.currentTimeMillis() + 10000L);
            GuildVoiceState voiceState = guild.getSelfMember().getVoiceState();
            this.client.updateAudioConnection0(guild.getIdLong(), voiceState.getChannel());
        }
    }

    private void handleNormalRequest() {
        DataObject message = this.ratelimitQueue.peek();
        if (message != null) {
            LOG.debug("Sending normal message {}", (Object)message);
            if (this.send(message)) {
                this.ratelimitQueue.remove();
            }
        }
    }

    private boolean send(DataObject request) {
        this.needRateLimit = !this.client.send(request, false);
        this.attemptedToSend = true;
        return !this.needRateLimit;
    }

    protected DataObject newVoiceClose(long guildId) {
        return DataObject.empty().put("op", 4).put("d", DataObject.empty().put("guild_id", Long.toUnsignedString(guildId)).putNull("channel_id").put("self_mute", false).put("self_deaf", false));
    }

    protected DataObject newVoiceOpen(AudioManager manager, long channel, long guild) {
        return DataObject.empty().put("op", 4).put("d", DataObject.empty().put("guild_id", guild).put("channel_id", channel).put("self_mute", manager.isSelfMuted()).put("self_deaf", manager.isSelfDeafened()));
    }
}

