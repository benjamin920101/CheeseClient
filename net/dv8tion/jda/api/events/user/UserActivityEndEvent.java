/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.events.user;

import javax.annotation.Nonnull;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.user.GenericUserEvent;
import net.dv8tion.jda.api.events.user.update.GenericUserPresenceEvent;

public class UserActivityEndEvent
extends GenericUserEvent
implements GenericUserPresenceEvent {
    private final Activity oldActivity;
    private final Member member;

    public UserActivityEndEvent(@Nonnull JDA api2, long responseNumber, @Nonnull Member member, @Nonnull Activity oldActivity) {
        super(api2, responseNumber, member.getUser());
        this.oldActivity = oldActivity;
        this.member = member;
    }

    @Nonnull
    public Activity getOldActivity() {
        return this.oldActivity;
    }

    @Override
    @Nonnull
    public Guild getGuild() {
        return this.member.getGuild();
    }

    @Override
    @Nonnull
    public Member getMember() {
        return this.member;
    }
}

