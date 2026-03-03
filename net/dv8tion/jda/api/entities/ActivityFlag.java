/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.entities;

import java.util.EnumSet;
import javax.annotation.Nonnull;

public enum ActivityFlag {
    INSTANCE(0),
    JOIN(1),
    SPECTATE(2),
    JOIN_REQUEST(3),
    SYNC(4),
    PLAY(5);

    private final int offset;
    private final int raw;

    private ActivityFlag(int offset) {
        this.offset = offset;
        this.raw = 1 << offset;
    }

    public int getOffset() {
        return this.offset;
    }

    public int getRaw() {
        return this.raw;
    }

    @Nonnull
    public static EnumSet<ActivityFlag> getFlags(int raw) {
        EnumSet<ActivityFlag> set = EnumSet.noneOf(ActivityFlag.class);
        if (raw == 0) {
            return set;
        }
        for (ActivityFlag flag : ActivityFlag.values()) {
            if ((flag.getRaw() & raw) != flag.getRaw()) continue;
            set.add(flag);
        }
        return set;
    }
}

