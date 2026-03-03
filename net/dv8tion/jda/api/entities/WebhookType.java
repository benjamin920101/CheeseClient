/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.entities;

import javax.annotation.Nonnull;

public enum WebhookType {
    UNKNOWN(-1),
    INCOMING(1),
    FOLLOWER(2);

    private final int key;

    private WebhookType(int key) {
        this.key = key;
    }

    public int getKey() {
        return this.key;
    }

    @Nonnull
    public static WebhookType fromKey(int key) {
        for (WebhookType type : WebhookType.values()) {
            if (type.key != key) continue;
            return type;
        }
        return UNKNOWN;
    }
}

