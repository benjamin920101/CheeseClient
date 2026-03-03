/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.entities;

import java.awt.Color;
import java.time.OffsetDateTime;
import java.util.EnumSet;
import java.util.List;
import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.dv8tion.jda.annotations.Incubating;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.ClientType;
import net.dv8tion.jda.api.entities.Emote;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.GuildVoiceState;
import net.dv8tion.jda.api.entities.IMentionable;
import net.dv8tion.jda.api.entities.IPermissionHolder;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.requests.restaction.AuditableRestAction;

public interface Member
extends IMentionable,
IPermissionHolder {
    @Nonnull
    public User getUser();

    @Override
    @Nonnull
    public Guild getGuild();

    @Nonnull
    public JDA getJDA();

    @Nonnull
    public OffsetDateTime getTimeJoined();

    public boolean hasTimeJoined();

    @Nullable
    public OffsetDateTime getTimeBoosted();

    @Nullable
    public GuildVoiceState getVoiceState();

    @Nonnull
    public List<Activity> getActivities();

    @Nonnull
    public OnlineStatus getOnlineStatus();

    @Nonnull
    public OnlineStatus getOnlineStatus(@Nonnull ClientType var1);

    @Nonnull
    public EnumSet<ClientType> getActiveClients();

    @Nullable
    public String getNickname();

    @Nonnull
    public String getEffectiveName();

    @Nonnull
    public List<Role> getRoles();

    @Nullable
    public Color getColor();

    public int getColorRaw();

    public boolean canInteract(@Nonnull Member var1);

    public boolean canInteract(@Nonnull Role var1);

    public boolean canInteract(@Nonnull Emote var1);

    public boolean isOwner();

    @Incubating
    public boolean isPending();

    @Nullable
    public TextChannel getDefaultChannel();

    @Nonnull
    @CheckReturnValue
    default public AuditableRestAction<Void> ban(int delDays) {
        return this.getGuild().ban(this, delDays);
    }

    @Nonnull
    @CheckReturnValue
    default public AuditableRestAction<Void> ban(int delDays, @Nullable String reason) {
        return this.getGuild().ban(this, delDays, reason);
    }

    @Nonnull
    @CheckReturnValue
    default public AuditableRestAction<Void> kick() {
        return this.getGuild().kick(this);
    }

    @Nonnull
    @CheckReturnValue
    default public AuditableRestAction<Void> kick(@Nullable String reason) {
        return this.getGuild().kick(this, reason);
    }

    @Nonnull
    @CheckReturnValue
    default public AuditableRestAction<Void> mute(boolean mute) {
        return this.getGuild().mute(this, mute);
    }

    @Nonnull
    @CheckReturnValue
    default public AuditableRestAction<Void> deafen(boolean deafen) {
        return this.getGuild().deafen(this, deafen);
    }

    @Nonnull
    @CheckReturnValue
    default public AuditableRestAction<Void> modifyNickname(@Nullable String nickname) {
        return this.getGuild().modifyNickname(this, nickname);
    }
}

