/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.utils;

import javax.annotation.Nonnull;
import net.dv8tion.jda.annotations.DeprecatedSince;
import net.dv8tion.jda.annotations.ForRemoval;
import net.dv8tion.jda.annotations.ReplaceWith;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.internal.utils.tuple.Pair;

public interface SessionController {
    public static final int IDENTIFY_DELAY = 5;

    default public void setConcurrency(int level) {
    }

    public void appendSession(@Nonnull SessionConnectNode var1);

    public void removeSession(@Nonnull SessionConnectNode var1);

    public long getGlobalRatelimit();

    public void setGlobalRatelimit(long var1);

    @Nonnull
    public String getGateway(@Nonnull JDA var1);

    @Nonnull
    @Deprecated
    @ForRemoval(deadline="4.4.0")
    @DeprecatedSince(value="4.0.0")
    @ReplaceWith(value="getShardedGateway(api)")
    public Pair<String, Integer> getGatewayBot(@Nonnull JDA var1);

    @Nonnull
    default public ShardedGateway getShardedGateway(@Nonnull JDA api2) {
        Pair<String, Integer> tuple = this.getGatewayBot(api2);
        return new ShardedGateway(tuple.getLeft(), tuple.getRight());
    }

    public static interface SessionConnectNode {
        public boolean isReconnect();

        @Nonnull
        public JDA getJDA();

        @Nonnull
        public JDA.ShardInfo getShardInfo();

        public void run(boolean var1) throws InterruptedException;
    }

    public static class ShardedGateway {
        private final String url;
        private final int shardTotal;
        private final int concurrency;

        public ShardedGateway(String url, int shardTotal) {
            this(url, shardTotal, 1);
        }

        public ShardedGateway(String url, int shardTotal, int concurrency) {
            this.url = url;
            this.shardTotal = shardTotal;
            this.concurrency = concurrency;
        }

        public String getUrl() {
            return this.url;
        }

        public int getShardTotal() {
            return this.shardTotal;
        }

        public int getConcurrency() {
            return this.concurrency;
        }
    }
}

