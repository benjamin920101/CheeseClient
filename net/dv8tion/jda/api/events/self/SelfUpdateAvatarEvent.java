/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.events.self;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.events.self.GenericSelfUpdateEvent;

public class SelfUpdateAvatarEvent
extends GenericSelfUpdateEvent<String> {
    public static final String IDENTIFIER = "avatar";
    private static final String AVATAR_URL = "https://cdn.discordapp.com/avatars/%s/%s%s";

    public SelfUpdateAvatarEvent(@Nonnull JDA api2, long responseNumber, @Nullable String oldAvatarId) {
        super(api2, responseNumber, oldAvatarId, api2.getSelfUser().getAvatarId(), IDENTIFIER);
    }

    @Nullable
    public String getOldAvatarId() {
        return (String)this.getOldValue();
    }

    @Nullable
    public String getOldAvatarUrl() {
        return this.previous == null ? null : String.format(AVATAR_URL, this.getSelfUser().getId(), this.previous, ((String)this.previous).startsWith("a_") ? ".gif" : ".png");
    }

    @Nullable
    public String getNewAvatarId() {
        return (String)this.getNewValue();
    }

    @Nullable
    public String getNewAvatarUrl() {
        return this.next == null ? null : String.format(AVATAR_URL, this.getSelfUser().getId(), this.next, ((String)this.next).startsWith("a_") ? ".gif" : ".png");
    }
}

