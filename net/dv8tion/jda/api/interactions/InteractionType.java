/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.interactions;

import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnull;

public enum InteractionType {
    UNKNOWN(-1),
    PING(1),
    SLASH_COMMAND(2),
    COMPONENT(3);

    private final int key;

    private InteractionType(int key) {
        this.key = key;
    }

    public int getKey() {
        return this.key;
    }

    @Nonnull
    @CheckReturnValue
    public static InteractionType fromKey(int key) {
        switch (key) {
            case 1: {
                return PING;
            }
            case 2: {
                return SLASH_COMMAND;
            }
            case 3: {
                return COMPONENT;
            }
        }
        return UNKNOWN;
    }
}

