/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.utils.cache;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.utils.cache.CacheView;

public interface ShardCacheView
extends CacheView<JDA> {
    @Nullable
    public JDA getElementById(int var1);

    @Nullable
    default public JDA getElementById(@Nonnull String id2) {
        return this.getElementById(Integer.parseUnsignedInt(id2));
    }
}

