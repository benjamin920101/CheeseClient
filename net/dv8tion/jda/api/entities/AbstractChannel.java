/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.entities;

import javax.annotation.Nonnull;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.entities.ISnowflake;

public interface AbstractChannel
extends ISnowflake {
    @Nonnull
    public String getName();

    @Nonnull
    public ChannelType getType();

    @Nonnull
    public JDA getJDA();
}

