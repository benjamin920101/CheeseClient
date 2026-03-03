/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.entities;

import java.util.List;
import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnull;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.GuildChannel;
import net.dv8tion.jda.api.entities.StoreChannel;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.requests.restaction.ChannelAction;
import net.dv8tion.jda.api.requests.restaction.order.CategoryOrderAction;

public interface Category
extends GuildChannel {
    @Nonnull
    public List<GuildChannel> getChannels();

    @Nonnull
    public List<StoreChannel> getStoreChannels();

    @Nonnull
    public List<TextChannel> getTextChannels();

    @Nonnull
    public List<VoiceChannel> getVoiceChannels();

    @Nonnull
    @CheckReturnValue
    public ChannelAction<TextChannel> createTextChannel(@Nonnull String var1);

    @Nonnull
    @CheckReturnValue
    public ChannelAction<VoiceChannel> createVoiceChannel(@Nonnull String var1);

    @Nonnull
    @CheckReturnValue
    public CategoryOrderAction modifyTextChannelPositions();

    @Nonnull
    @CheckReturnValue
    public CategoryOrderAction modifyVoiceChannelPositions();

    @Nonnull
    public ChannelAction<Category> createCopy(@Nonnull Guild var1);

    @Nonnull
    public ChannelAction<Category> createCopy();
}

