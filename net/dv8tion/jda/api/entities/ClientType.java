/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.entities;

import javax.annotation.Nonnull;

public enum ClientType {
    DESKTOP("desktop"),
    MOBILE("mobile"),
    WEB("web"),
    UNKNOWN("unknown");

    private final String key;

    private ClientType(String key) {
        this.key = key;
    }

    public String getKey() {
        return this.key;
    }

    @Nonnull
    public static ClientType fromKey(@Nonnull String key) {
        for (ClientType type : ClientType.values()) {
            if (!type.key.equals(key)) continue;
            return type;
        }
        return UNKNOWN;
    }
}

