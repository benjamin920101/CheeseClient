/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.events.guild.override;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Category;
import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.entities.GuildChannel;
import net.dv8tion.jda.api.entities.IPermissionHolder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.PermissionOverride;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.StoreChannel;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.events.guild.GenericGuildEvent;

public class GenericPermissionOverrideEvent
extends GenericGuildEvent {
    protected final GuildChannel channel;
    protected final PermissionOverride override;

    public GenericPermissionOverrideEvent(@Nonnull JDA api2, long responseNumber, @Nonnull GuildChannel channel, @Nonnull PermissionOverride override) {
        super(api2, responseNumber, channel.getGuild());
        this.channel = channel;
        this.override = override;
    }

    @Nonnull
    public ChannelType getChannelType() {
        return this.channel.getType();
    }

    @Nonnull
    public GuildChannel getChannel() {
        return this.channel;
    }

    @Nonnull
    public TextChannel getTextChannel() {
        if (this.channel instanceof TextChannel) {
            return (TextChannel)this.channel;
        }
        throw new IllegalStateException("This override is for a channel of type " + (Object)((Object)this.getChannelType()));
    }

    @Nonnull
    public VoiceChannel getVoiceChannel() {
        if (this.channel instanceof VoiceChannel) {
            return (VoiceChannel)this.channel;
        }
        throw new IllegalStateException("This override is for a channel of type " + (Object)((Object)this.getChannelType()));
    }

    @Nonnull
    public StoreChannel getStoreChannel() {
        if (this.channel instanceof StoreChannel) {
            return (StoreChannel)this.channel;
        }
        throw new IllegalStateException("This override is for a channel of type " + (Object)((Object)this.getChannelType()));
    }

    @Nonnull
    public Category getCategory() {
        if (this.channel instanceof Category) {
            return (Category)this.channel;
        }
        throw new IllegalStateException("This override is for a channel of type " + (Object)((Object)this.getChannelType()));
    }

    @Nonnull
    public PermissionOverride getPermissionOverride() {
        return this.override;
    }

    public boolean isRoleOverride() {
        return this.override.isRoleOverride();
    }

    public boolean isMemberOverride() {
        return this.override.isMemberOverride();
    }

    @Nullable
    public IPermissionHolder getPermissionHolder() {
        return this.isMemberOverride() ? this.override.getMember() : this.override.getRole();
    }

    @Nullable
    public Member getMember() {
        return this.override.getMember();
    }

    @Nullable
    public Role getRole() {
        return this.override.getRole();
    }
}

