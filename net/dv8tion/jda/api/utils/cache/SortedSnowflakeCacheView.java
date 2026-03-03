/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.utils.cache;

import java.util.NavigableSet;
import java.util.function.Consumer;
import java.util.stream.Stream;
import javax.annotation.Nonnull;
import net.dv8tion.jda.api.entities.ISnowflake;
import net.dv8tion.jda.api.utils.cache.SnowflakeCacheView;

public interface SortedSnowflakeCacheView<T extends Comparable<? super T> & ISnowflake>
extends SnowflakeCacheView<T> {
    @Override
    public void forEachUnordered(@Nonnull Consumer<? super T> var1);

    @Override
    @Nonnull
    public NavigableSet<T> asSet();

    @Nonnull
    public Stream<T> streamUnordered();

    @Nonnull
    public Stream<T> parallelStreamUnordered();
}

