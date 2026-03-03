/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.entities;

import javax.annotation.Nonnull;

public enum EmbedType {
    IMAGE("image"),
    VIDEO("video"),
    LINK("link"),
    RICH("rich"),
    UNKNOWN("");

    private final String key;

    private EmbedType(String key) {
        this.key = key;
    }

    @Nonnull
    public static EmbedType fromKey(String key) {
        for (EmbedType type : EmbedType.values()) {
            if (!type.key.equals(key)) continue;
            return type;
        }
        return UNKNOWN;
    }
}

