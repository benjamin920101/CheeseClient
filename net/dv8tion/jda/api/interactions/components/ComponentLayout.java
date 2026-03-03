/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.interactions.components;

import java.util.List;
import javax.annotation.Nonnull;
import net.dv8tion.jda.api.interactions.components.Button;
import net.dv8tion.jda.api.interactions.components.Component;
import net.dv8tion.jda.api.utils.data.SerializableData;

public interface ComponentLayout
extends SerializableData,
Iterable<Component> {
    @Nonnull
    public List<Component> getComponents();

    @Nonnull
    public List<Button> getButtons();

    @Nonnull
    public Type getType();

    public static enum Type {
        UNKNOWN(-1),
        ACTION_ROW(1);

        private final int key;

        private Type(int key) {
            this.key = key;
        }

        public int getKey() {
            return this.key;
        }
    }
}

