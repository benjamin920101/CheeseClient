/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.internal.utils.cache;

import java.util.function.Function;
import net.dv8tion.jda.api.entities.ISnowflake;
import net.dv8tion.jda.api.utils.cache.SnowflakeCacheView;
import net.dv8tion.jda.internal.utils.cache.AbstractCacheView;

public class SnowflakeCacheViewImpl<T extends ISnowflake>
extends AbstractCacheView<T>
implements SnowflakeCacheView<T> {
    public SnowflakeCacheViewImpl(Class<T> type, Function<T, String> nameMapper) {
        super(type, nameMapper);
    }

    @Override
    public T getElementById(long id2) {
        if (this.elements.isEmpty()) {
            return null;
        }
        return (T)((ISnowflake)this.get(id2));
    }
}

