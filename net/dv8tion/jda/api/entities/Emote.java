/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.entities;

import java.util.List;
import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.dv8tion.jda.annotations.DeprecatedSince;
import net.dv8tion.jda.annotations.ReplaceWith;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.IMentionable;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.managers.EmoteManager;
import net.dv8tion.jda.api.requests.restaction.AuditableRestAction;
import net.dv8tion.jda.internal.utils.PermissionUtil;

public interface Emote
extends IMentionable {
    public static final String ICON_URL = "https://cdn.discordapp.com/emojis/%s.%s";

    @Nullable
    public Guild getGuild();

    @Nonnull
    public List<Role> getRoles();

    @Deprecated
    @DeprecatedSince(value="3.8.0")
    @ReplaceWith(value="canProvideRoles()")
    default public boolean hasRoles() {
        return this.canProvideRoles();
    }

    public boolean canProvideRoles();

    @Nonnull
    public String getName();

    public boolean isManaged();

    public boolean isAvailable();

    @Nonnull
    public JDA getJDA();

    @Nonnull
    @CheckReturnValue
    public AuditableRestAction<Void> delete();

    @Nonnull
    public EmoteManager getManager();

    public boolean isAnimated();

    @Nonnull
    default public String getImageUrl() {
        return String.format(ICON_URL, this.getId(), this.isAnimated() ? "gif" : "png");
    }

    @Override
    @Nonnull
    default public String getAsMention() {
        return (this.isAnimated() ? "<a:" : "<:") + this.getName() + ":" + this.getId() + ">";
    }

    default public boolean canInteract(Member issuer) {
        return PermissionUtil.canInteract(issuer, this);
    }

    default public boolean canInteract(User issuer, MessageChannel channel) {
        return PermissionUtil.canInteract(issuer, this, channel);
    }

    default public boolean canInteract(User issuer, MessageChannel channel, boolean botOverride) {
        return PermissionUtil.canInteract(issuer, this, channel, botOverride);
    }
}

