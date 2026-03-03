/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.internal.utils.config.sharding;

import java.util.EnumSet;
import java.util.concurrent.ConcurrentMap;
import java.util.function.IntFunction;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.dv8tion.jda.api.GatewayEncoding;
import net.dv8tion.jda.api.utils.Compression;
import net.dv8tion.jda.api.utils.cache.CacheFlag;
import net.dv8tion.jda.internal.utils.config.MetaConfig;
import net.dv8tion.jda.internal.utils.config.flags.ConfigFlag;

public class ShardingMetaConfig
extends MetaConfig {
    private static final ShardingMetaConfig defaultConfig = new ShardingMetaConfig(2048, null, null, ConfigFlag.getDefault(), Compression.ZLIB, GatewayEncoding.JSON);
    private final Compression compression;
    private final GatewayEncoding encoding;
    private final IntFunction<? extends ConcurrentMap<String, String>> contextProvider;

    public ShardingMetaConfig(int maxBufferSize, @Nullable IntFunction<? extends ConcurrentMap<String, String>> contextProvider, @Nullable EnumSet<CacheFlag> cacheFlags, EnumSet<ConfigFlag> flags, Compression compression, GatewayEncoding encoding) {
        super(maxBufferSize, null, cacheFlags, flags);
        this.compression = compression;
        this.contextProvider = contextProvider;
        this.encoding = encoding;
    }

    @Nullable
    public ConcurrentMap<String, String> getContextMap(int shardId) {
        return this.contextProvider == null ? null : this.contextProvider.apply(shardId);
    }

    public Compression getCompression() {
        return this.compression;
    }

    public GatewayEncoding getEncoding() {
        return this.encoding;
    }

    @Nullable
    public IntFunction<? extends ConcurrentMap<String, String>> getContextProvider() {
        return this.contextProvider;
    }

    @Nonnull
    public static ShardingMetaConfig getDefault() {
        return defaultConfig;
    }
}

