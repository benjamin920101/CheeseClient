/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.entities;

import java.time.OffsetDateTime;
import javax.annotation.Nonnull;
import net.dv8tion.jda.api.utils.TimeUtil;

public interface ISnowflake {
    @Nonnull
    default public String getId() {
        return Long.toUnsignedString(this.getIdLong());
    }

    public long getIdLong();

    @Nonnull
    default public OffsetDateTime getTimeCreated() {
        return TimeUtil.getTimeCreated(this.getIdLong());
    }
}

