/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.utils.data;

import javax.annotation.Nonnull;
import net.dv8tion.jda.api.utils.data.DataArray;

public interface SerializableArray {
    @Nonnull
    public DataArray toDataArray();
}

