/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.internal.utils.config.sharding;

import com.neovisionaries.ws.client.WebSocketFactory;
import java.util.EnumSet;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.dv8tion.jda.api.audio.factory.IAudioSendFactory;
import net.dv8tion.jda.api.hooks.VoiceDispatchInterceptor;
import net.dv8tion.jda.api.utils.SessionController;
import net.dv8tion.jda.internal.utils.IOUtil;
import net.dv8tion.jda.internal.utils.config.SessionConfig;
import net.dv8tion.jda.internal.utils.config.flags.ConfigFlag;
import net.dv8tion.jda.internal.utils.config.flags.ShardingConfigFlag;
import okhttp3.OkHttpClient;

public class ShardingSessionConfig
extends SessionConfig {
    private final OkHttpClient.Builder builder;
    private final IAudioSendFactory audioSendFactory;
    private final EnumSet<ShardingConfigFlag> shardingFlags;

    public ShardingSessionConfig(@Nullable SessionController sessionController, @Nullable VoiceDispatchInterceptor interceptor, @Nullable OkHttpClient httpClient, @Nullable OkHttpClient.Builder httpClientBuilder, @Nullable WebSocketFactory webSocketFactory, @Nullable IAudioSendFactory audioSendFactory, EnumSet<ConfigFlag> flags, EnumSet<ShardingConfigFlag> shardingFlags, int maxReconnectDelay, int largeThreshold) {
        super(sessionController, httpClient, webSocketFactory, interceptor, flags, maxReconnectDelay, largeThreshold);
        this.builder = httpClient == null ? (httpClientBuilder == null ? IOUtil.newHttpClientBuilder() : httpClientBuilder) : null;
        this.audioSendFactory = audioSendFactory;
        this.shardingFlags = shardingFlags;
    }

    public SessionConfig toSessionConfig(OkHttpClient client) {
        return new SessionConfig(this.getSessionController(), client, this.getWebSocketFactory(), this.getVoiceDispatchInterceptor(), this.getFlags(), this.getMaxReconnectDelay(), this.getLargeThreshold());
    }

    public EnumSet<ShardingConfigFlag> getShardingFlags() {
        return this.shardingFlags;
    }

    @Nullable
    public OkHttpClient.Builder getHttpBuilder() {
        return this.builder;
    }

    @Nullable
    public IAudioSendFactory getAudioSendFactory() {
        return this.audioSendFactory;
    }

    @Nonnull
    public static ShardingSessionConfig getDefault() {
        return new ShardingSessionConfig(null, null, new OkHttpClient(), null, null, null, ConfigFlag.getDefault(), ShardingConfigFlag.getDefault(), 900, 250);
    }
}

