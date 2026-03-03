/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.sharding;

import java.util.concurrent.ExecutorService;
import javax.annotation.Nullable;

public interface ThreadPoolProvider<T extends ExecutorService> {
    @Nullable
    public T provide(int var1);

    default public boolean shouldShutdownAutomatically(int shardId) {
        return false;
    }
}

