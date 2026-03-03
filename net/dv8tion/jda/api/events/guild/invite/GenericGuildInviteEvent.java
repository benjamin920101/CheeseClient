/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.events.guild.invite;

import javax.annotation.Nonnull;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Category;
import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.entities.GuildChannel;
import net.dv8tion.jda.api.entities.StoreChannel;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.events.guild.GenericGuildEvent;

public class GenericGuildInviteEvent
extends GenericGuildEvent {
    private final String code;
    private final GuildChannel channel;

    public GenericGuildInviteEvent(@Nonnull JDA api2, long responseNumber, @Nonnull String code, @Nonnull GuildChannel channel) {
        super(api2, responseNumber, channel.getGuild());
        this.code = code;
        this.channel = channel;
    }

    @Nonnull
    public String getCode() {
        return this.code;
    }

    @Nonnull
    public String getUrl() {
        return "https://discord.gg/" + this.code;
    }

    @Nonnull
    public GuildChannel getChannel() {
        return this.channel;
    }

    @Nonnull
    public ChannelType getChannelType() {
        return this.channel.getType();
    }

    @Nonnull
    public TextChannel getTextChannel() {
        if (this.getChannelType() != ChannelType.TEXT) {
            throw new IllegalStateException("The channel is not of type TEXT");
        }
        return (TextChannel)this.getChannel();
    }

    @Nonnull
    public VoiceChannel getVoiceChannel() {
        if (this.getChannelType() != ChannelType.VOICE) {
            throw new IllegalStateException("The channel is not of type VOICE");
        }
        return (VoiceChannel)this.getChannel();
    }

    @Nonnull
    public StoreChannel getStoreChannel() {
        if (this.getChannelType() != ChannelType.STORE) {
            throw new IllegalStateException("The channel is not of type STORE");
        }
        return (StoreChannel)this.getChannel();
    }

    @Nonnull
    public Category getCategory() {
        if (this.getChannelType() != ChannelType.CATEGORY) {
            throw new IllegalStateException("The channel is not of type CATEGORY");
        }
        return (Category)this.getChannel();
    }
}

