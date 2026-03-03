/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.entities;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.dv8tion.jda.api.Region;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.GuildChannel;
import net.dv8tion.jda.api.requests.restaction.ChannelAction;

public interface VoiceChannel
extends GuildChannel {
    public int getUserLimit();

    public int getBitrate();

    @Nonnull
    public Region getRegion();

    @Nullable
    public String getRegionRaw();

    @Nonnull
    public ChannelAction<VoiceChannel> createCopy(@Nonnull Guild var1);

    @Nonnull
    public ChannelAction<VoiceChannel> createCopy();
}

