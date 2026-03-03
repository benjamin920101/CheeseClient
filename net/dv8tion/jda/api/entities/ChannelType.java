/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.entities;

import java.util.EnumSet;
import javax.annotation.Nonnull;

public enum ChannelType {
    TEXT(0, 0, true),
    PRIVATE(1, -1),
    VOICE(2, 1, true),
    GROUP(3, -1),
    CATEGORY(4, 2, true),
    STORE(6, 0, true),
    UNKNOWN(-1, -2);

    private final int sortBucket;
    private final int id;
    private final boolean isGuild;

    private ChannelType(int id2, int sortBucket) {
        this(id2, sortBucket, false);
    }

    private ChannelType(int id2, int sortBucket, boolean isGuild) {
        this.id = id2;
        this.sortBucket = sortBucket;
        this.isGuild = isGuild;
    }

    public int getSortBucket() {
        return this.sortBucket;
    }

    public int getId() {
        return this.id;
    }

    public boolean isGuild() {
        return this.isGuild;
    }

    @Nonnull
    public static ChannelType fromId(int id2) {
        if (id2 == 5) {
            return TEXT;
        }
        for (ChannelType type : ChannelType.values()) {
            if (type.id != id2) continue;
            return type;
        }
        return UNKNOWN;
    }

    @Nonnull
    public static EnumSet<ChannelType> fromSortBucket(int bucket) {
        EnumSet<ChannelType> types = EnumSet.noneOf(ChannelType.class);
        for (ChannelType type : ChannelType.values()) {
            if (type.getSortBucket() != bucket) continue;
            types.add(type);
        }
        return types;
    }
}

