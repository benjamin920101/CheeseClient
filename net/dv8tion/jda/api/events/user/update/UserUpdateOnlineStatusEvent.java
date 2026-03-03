/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.events.user.update;

import javax.annotation.Nonnull;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.user.update.GenericUserPresenceEvent;
import net.dv8tion.jda.api.events.user.update.GenericUserUpdateEvent;

public class UserUpdateOnlineStatusEvent
extends GenericUserUpdateEvent<OnlineStatus>
implements GenericUserPresenceEvent {
    public static final String IDENTIFIER = "status";
    private final Guild guild;
    private final Member member;

    public UserUpdateOnlineStatusEvent(@Nonnull JDA api2, long responseNumber, @Nonnull Member member, @Nonnull OnlineStatus oldOnlineStatus) {
        super(api2, responseNumber, member.getUser(), oldOnlineStatus, member.getOnlineStatus(), IDENTIFIER);
        this.guild = member.getGuild();
        this.member = member;
    }

    @Override
    @Nonnull
    public Guild getGuild() {
        return this.guild;
    }

    @Override
    @Nonnull
    public Member getMember() {
        return this.member;
    }

    @Nonnull
    public OnlineStatus getOldOnlineStatus() {
        return this.getOldValue();
    }

    @Nonnull
    public OnlineStatus getNewOnlineStatus() {
        return this.getNewValue();
    }

    @Override
    @Nonnull
    public OnlineStatus getOldValue() {
        return (OnlineStatus)((Object)super.getOldValue());
    }

    @Override
    @Nonnull
    public OnlineStatus getNewValue() {
        return (OnlineStatus)((Object)super.getNewValue());
    }
}

