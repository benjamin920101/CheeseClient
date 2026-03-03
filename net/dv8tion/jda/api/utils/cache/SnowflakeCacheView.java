/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.utils.cache;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.dv8tion.jda.api.entities.ISnowflake;
import net.dv8tion.jda.api.utils.MiscUtil;
import net.dv8tion.jda.api.utils.cache.CacheView;

public interface SnowflakeCacheView<T extends ISnowflake>
extends CacheView<T> {
    @Nullable
    public T getElementById(long var1);

    @Nullable
    default public T getElementById(@Nonnull String id2) {
        return this.getElementById(MiscUtil.parseSnowflake(id2));
    }
}

