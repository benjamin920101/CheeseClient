/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.interactions.components;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.dv8tion.jda.api.utils.data.SerializableData;

public interface Component
extends SerializableData {
    @Nonnull
    public Type getType();

    @Nullable
    public String getId();

    public static enum Type {
        UNKNOWN(-1),
        ACTION_ROW(1),
        BUTTON(2);

        private final int key;

        private Type(int key) {
            this.key = key;
        }

        @Nonnull
        public static Type fromKey(int type) {
            for (Type t2 : Type.values()) {
                if (t2.key != type) continue;
                return t2;
            }
            return UNKNOWN;
        }
    }
}

