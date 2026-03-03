/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.utils;

import javax.annotation.Nonnull;
import net.dv8tion.jda.internal.utils.Checks;

@FunctionalInterface
public interface ChunkingFilter {
    public static final ChunkingFilter ALL = x2 -> true;
    public static final ChunkingFilter NONE = x2 -> false;

    public boolean filter(long var1);

    @Nonnull
    public static ChunkingFilter include(long ... ids) {
        Checks.notNull(ids, "ID array");
        if (ids.length == 0) {
            return NONE;
        }
        return guild -> {
            for (long id2 : ids) {
                if (id2 != guild) continue;
                return true;
            }
            return false;
        };
    }

    @Nonnull
    public static ChunkingFilter exclude(long ... ids) {
        Checks.notNull(ids, "ID array");
        if (ids.length == 0) {
            return ALL;
        }
        return guild -> {
            for (long id2 : ids) {
                if (id2 != guild) continue;
                return false;
            }
            return true;
        };
    }
}

