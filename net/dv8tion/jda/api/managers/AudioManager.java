/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.managers;

import java.util.Arrays;
import java.util.Collection;
import java.util.EnumSet;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.dv8tion.jda.annotations.DeprecatedSince;
import net.dv8tion.jda.annotations.ForRemoval;
import net.dv8tion.jda.annotations.Incubating;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.audio.AudioReceiveHandler;
import net.dv8tion.jda.api.audio.AudioSendHandler;
import net.dv8tion.jda.api.audio.SpeakingMode;
import net.dv8tion.jda.api.audio.hooks.ConnectionListener;
import net.dv8tion.jda.api.audio.hooks.ConnectionStatus;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.internal.utils.Checks;
import net.dv8tion.jda.internal.utils.JDALogger;
import org.slf4j.Logger;

public interface AudioManager {
    public static final long DEFAULT_CONNECTION_TIMEOUT = 10000L;
    public static final Logger LOG = JDALogger.getLog(AudioManager.class);

    public void openAudioConnection(VoiceChannel var1);

    public void closeAudioConnection();

    @Incubating
    public void setSpeakingMode(@Nonnull Collection<SpeakingMode> var1);

    @Incubating
    default public void setSpeakingMode(SpeakingMode ... mode) {
        Checks.notNull(mode, "Speaking Mode");
        this.setSpeakingMode(Arrays.asList(mode));
    }

    @Nonnull
    @Incubating
    public EnumSet<SpeakingMode> getSpeakingMode();

    public void setSpeakingDelay(int var1);

    @Nonnull
    public JDA getJDA();

    @Nonnull
    public Guild getGuild();

    @Deprecated
    @ForRemoval
    @DeprecatedSince(value="4.2.0")
    public boolean isAttemptingToConnect();

    @Nullable
    @Deprecated
    @ForRemoval
    @DeprecatedSince(value="4.2.0")
    public VoiceChannel getQueuedAudioConnection();

    @Nullable
    public VoiceChannel getConnectedChannel();

    public boolean isConnected();

    public void setConnectTimeout(long var1);

    public long getConnectTimeout();

    public void setSendingHandler(@Nullable AudioSendHandler var1);

    @Nullable
    public AudioSendHandler getSendingHandler();

    public void setReceivingHandler(@Nullable AudioReceiveHandler var1);

    @Nullable
    public AudioReceiveHandler getReceivingHandler();

    public void setConnectionListener(@Nullable ConnectionListener var1);

    @Nullable
    public ConnectionListener getConnectionListener();

    @Nonnull
    public ConnectionStatus getConnectionStatus();

    public void setAutoReconnect(boolean var1);

    public boolean isAutoReconnect();

    public void setSelfMuted(boolean var1);

    public boolean isSelfMuted();

    public void setSelfDeafened(boolean var1);

    public boolean isSelfDeafened();
}

