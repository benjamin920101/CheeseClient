/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.events.user.update;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.user.update.GenericUserUpdateEvent;

public class UserUpdateAvatarEvent
extends GenericUserUpdateEvent<String> {
    public static final String IDENTIFIER = "avatar";

    public UserUpdateAvatarEvent(@Nonnull JDA api2, long responseNumber, @Nonnull User user, @Nullable String oldAvatar) {
        super(api2, responseNumber, user, oldAvatar, user.getAvatarId(), IDENTIFIER);
    }

    @Nullable
    public String getOldAvatarId() {
        return (String)this.getOldValue();
    }

    @Nullable
    public String getOldAvatarUrl() {
        return this.previous == null ? null : String.format("https://cdn.discordapp.com/avatars/%s/%s.%s", this.getUser().getId(), this.previous, ((String)this.previous).startsWith("a_") ? "gif" : "png");
    }

    @Nullable
    public String getNewAvatarId() {
        return (String)this.getNewValue();
    }

    @Nullable
    public String getNewAvatarUrl() {
        return this.next == null ? null : String.format("https://cdn.discordapp.com/avatars/%s/%s.%s", this.getUser().getId(), this.next, ((String)this.next).startsWith("a_") ? "gif" : "png");
    }
}

