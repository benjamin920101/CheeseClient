/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.internal.utils.config.sharding;

import javax.annotation.Nonnull;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.MemberCachePolicy;

public class ShardingConfig {
    private int shardsTotal;
    private int intents;
    private MemberCachePolicy memberCachePolicy;
    private final boolean useShutdownNow;

    public ShardingConfig(int shardsTotal, boolean useShutdownNow, int intents, MemberCachePolicy memberCachePolicy) {
        this.shardsTotal = shardsTotal;
        this.useShutdownNow = useShutdownNow;
        this.intents = intents;
        this.memberCachePolicy = memberCachePolicy;
    }

    public void setShardsTotal(int shardsTotal) {
        this.shardsTotal = shardsTotal;
    }

    public int getShardsTotal() {
        return this.shardsTotal;
    }

    public int getIntents() {
        return this.intents;
    }

    public MemberCachePolicy getMemberCachePolicy() {
        return this.memberCachePolicy;
    }

    public boolean isUseShutdownNow() {
        return this.useShutdownNow;
    }

    @Nonnull
    public static ShardingConfig getDefault() {
        return new ShardingConfig(1, false, GatewayIntent.ALL_INTENTS, MemberCachePolicy.ALL);
    }
}

