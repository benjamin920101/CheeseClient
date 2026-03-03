/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.internal.managers;

import java.util.Collection;
import java.util.EnumSet;
import java.util.concurrent.locks.ReentrantLock;
import javax.annotation.Nonnull;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.audio.AudioReceiveHandler;
import net.dv8tion.jda.api.audio.AudioSendHandler;
import net.dv8tion.jda.api.audio.SpeakingMode;
import net.dv8tion.jda.api.audio.hooks.ConnectionListener;
import net.dv8tion.jda.api.audio.hooks.ConnectionStatus;
import net.dv8tion.jda.api.audio.hooks.ListenerProxy;
import net.dv8tion.jda.api.entities.GuildChannel;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.exceptions.InsufficientPermissionException;
import net.dv8tion.jda.api.managers.AudioManager;
import net.dv8tion.jda.api.utils.MiscUtil;
import net.dv8tion.jda.internal.JDAImpl;
import net.dv8tion.jda.internal.audio.AudioConnection;
import net.dv8tion.jda.internal.entities.GuildImpl;
import net.dv8tion.jda.internal.utils.Checks;
import net.dv8tion.jda.internal.utils.PermissionUtil;

public class AudioManagerImpl
implements AudioManager {
    public final ReentrantLock CONNECTION_LOCK = new ReentrantLock();
    protected final ListenerProxy connectionListener = new ListenerProxy();
    protected final GuildImpl guild;
    protected AudioConnection audioConnection = null;
    protected EnumSet<SpeakingMode> speakingModes = EnumSet.of(SpeakingMode.VOICE);
    protected AudioSendHandler sendHandler;
    protected AudioReceiveHandler receiveHandler;
    protected long queueTimeout = 100L;
    protected boolean shouldReconnect = true;
    protected boolean selfMuted = false;
    protected boolean selfDeafened = false;
    protected long timeout = 10000L;
    protected int speakingDelay = 0;

    public AudioManagerImpl(GuildImpl guild) {
        this.guild = guild;
    }

    public AudioConnection getAudioConnection() {
        return this.audioConnection;
    }

    @Override
    public void openAudioConnection(VoiceChannel channel) {
        Checks.notNull(channel, "Provided VoiceChannel");
        if (!this.getGuild().equals(channel.getGuild())) {
            throw new IllegalArgumentException("The provided VoiceChannel is not a part of the Guild that this AudioManager handles.Please provide a VoiceChannel from the proper Guild");
        }
        Member self = this.getGuild().getSelfMember();
        if (this.audioConnection != null && channel.equals(this.audioConnection.getChannel())) {
            return;
        }
        this.checkChannel(channel, self);
        this.getJDA().getDirectAudioController().connect(channel);
        if (this.audioConnection != null) {
            this.audioConnection.setChannel(channel);
        }
    }

    private void checkChannel(VoiceChannel channel, Member self) {
        EnumSet<Permission> perms = Permission.getPermissions(PermissionUtil.getEffectivePermission((GuildChannel)channel, self));
        if (!perms.contains((Object)Permission.VOICE_CONNECT)) {
            throw new InsufficientPermissionException(channel, Permission.VOICE_CONNECT);
        }
        int userLimit = channel.getUserLimit();
        if (userLimit > 0 && !perms.contains((Object)Permission.ADMINISTRATOR) && userLimit <= channel.getMembers().size() && !perms.contains((Object)Permission.VOICE_MOVE_OTHERS)) {
            throw new InsufficientPermissionException(channel, Permission.VOICE_MOVE_OTHERS, "Unable to connect to VoiceChannel due to userlimit! Requires permission VOICE_MOVE_OTHERS to bypass");
        }
    }

    @Override
    public void closeAudioConnection() {
        this.getJDA().getAudioLifeCyclePool().execute(() -> {
            this.getJDA().setContext();
            this.closeAudioConnection(ConnectionStatus.NOT_CONNECTED);
        });
    }

    public void closeAudioConnection(ConnectionStatus reason) {
        MiscUtil.locked(this.CONNECTION_LOCK, () -> {
            if (this.audioConnection != null) {
                this.audioConnection.close(reason);
            } else if (reason != ConnectionStatus.DISCONNECTED_REMOVED_FROM_GUILD) {
                this.getJDA().getDirectAudioController().disconnect(this.getGuild());
            }
            this.audioConnection = null;
        });
    }

    @Override
    public void setSpeakingMode(@Nonnull Collection<SpeakingMode> mode) {
        Checks.notEmpty(mode, "Speaking Mode");
        this.speakingModes = EnumSet.copyOf(mode);
        if (this.audioConnection != null) {
            this.audioConnection.setSpeakingMode(this.speakingModes);
        }
    }

    @Override
    @Nonnull
    public EnumSet<SpeakingMode> getSpeakingMode() {
        return EnumSet.copyOf(this.speakingModes);
    }

    @Override
    public void setSpeakingDelay(int millis) {
        this.speakingDelay = millis;
        if (this.audioConnection != null) {
            this.audioConnection.setSpeakingDelay(millis);
        }
    }

    @Override
    @Nonnull
    public JDAImpl getJDA() {
        return this.getGuild().getJDA();
    }

    @Override
    @Nonnull
    public GuildImpl getGuild() {
        return this.guild;
    }

    @Override
    @Deprecated
    public boolean isAttemptingToConnect() {
        return false;
    }

    @Override
    @Deprecated
    public VoiceChannel getQueuedAudioConnection() {
        return null;
    }

    @Override
    public VoiceChannel getConnectedChannel() {
        return this.audioConnection == null ? null : this.audioConnection.getChannel();
    }

    @Override
    public boolean isConnected() {
        return this.audioConnection != null;
    }

    @Override
    public void setConnectTimeout(long timeout) {
        this.timeout = timeout;
    }

    @Override
    public long getConnectTimeout() {
        return this.timeout;
    }

    @Override
    public void setSendingHandler(AudioSendHandler handler) {
        this.sendHandler = handler;
        if (this.audioConnection != null) {
            this.audioConnection.setSendingHandler(handler);
        }
    }

    @Override
    public AudioSendHandler getSendingHandler() {
        return this.sendHandler;
    }

    @Override
    public void setReceivingHandler(AudioReceiveHandler handler) {
        this.receiveHandler = handler;
        if (this.audioConnection != null) {
            this.audioConnection.setReceivingHandler(handler);
        }
    }

    @Override
    public AudioReceiveHandler getReceivingHandler() {
        return this.receiveHandler;
    }

    @Override
    public void setConnectionListener(ConnectionListener listener) {
        this.connectionListener.setListener(listener);
    }

    @Override
    public ConnectionListener getConnectionListener() {
        return this.connectionListener.getListener();
    }

    @Override
    @Nonnull
    public ConnectionStatus getConnectionStatus() {
        if (this.audioConnection != null) {
            return this.audioConnection.getConnectionStatus();
        }
        return ConnectionStatus.NOT_CONNECTED;
    }

    @Override
    public void setAutoReconnect(boolean shouldReconnect) {
        this.shouldReconnect = shouldReconnect;
        if (this.audioConnection != null) {
            this.audioConnection.setAutoReconnect(shouldReconnect);
        }
    }

    @Override
    public boolean isAutoReconnect() {
        return this.shouldReconnect;
    }

    @Override
    public void setSelfMuted(boolean muted) {
        if (this.selfMuted != muted) {
            this.selfMuted = muted;
            this.updateVoiceState();
        }
    }

    @Override
    public boolean isSelfMuted() {
        return this.selfMuted;
    }

    @Override
    public void setSelfDeafened(boolean deafened) {
        if (this.selfDeafened != deafened) {
            this.selfDeafened = deafened;
            this.updateVoiceState();
        }
    }

    @Override
    public boolean isSelfDeafened() {
        return this.selfDeafened;
    }

    public ConnectionListener getListenerProxy() {
        return this.connectionListener;
    }

    public void setAudioConnection(AudioConnection audioConnection) {
        if (audioConnection == null) {
            this.audioConnection = null;
            return;
        }
        if (this.audioConnection != null) {
            this.closeAudioConnection(ConnectionStatus.AUDIO_REGION_CHANGE);
        }
        this.audioConnection = audioConnection;
        audioConnection.setSendingHandler(this.sendHandler);
        audioConnection.setReceivingHandler(this.receiveHandler);
        audioConnection.setQueueTimeout(this.queueTimeout);
        audioConnection.setSpeakingMode(this.speakingModes);
        audioConnection.setSpeakingDelay(this.speakingDelay);
    }

    public void setConnectedChannel(VoiceChannel channel) {
        if (this.audioConnection != null) {
            this.audioConnection.setChannel(channel);
        }
    }

    public void setQueueTimeout(long queueTimeout) {
        this.queueTimeout = queueTimeout;
        if (this.audioConnection != null) {
            this.audioConnection.setQueueTimeout(queueTimeout);
        }
    }

    protected void updateVoiceState() {
        VoiceChannel channel = this.getConnectedChannel();
        if (channel != null) {
            this.getJDA().getDirectAudioController().connect(channel);
        }
    }

    protected void finalize() {
        if (this.audioConnection != null) {
            LOG.warn("Finalized AudioManager with active audio connection. GuildId: {}", (Object)this.getGuild().getId());
            this.audioConnection.close(ConnectionStatus.DISCONNECTED_REMOVED_FROM_GUILD);
        }
        this.audioConnection = null;
    }
}

