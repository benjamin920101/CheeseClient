/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.utils.data;

import javax.annotation.Nonnull;
import net.dv8tion.jda.api.utils.data.DataObject;

public interface SerializableData {
    @Nonnull
    public DataObject toData();
}

