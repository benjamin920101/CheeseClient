/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.events.user.update;

import java.util.List;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.user.update.GenericUserPresenceEvent;
import net.dv8tion.jda.api.events.user.update.GenericUserUpdateEvent;

public class UserUpdateActivitiesEvent
extends GenericUserUpdateEvent<List<Activity>>
implements GenericUserPresenceEvent {
    public static final String IDENTIFIER = "activities";
    private final Member member;

    public UserUpdateActivitiesEvent(@Nonnull JDA api2, long responseNumber, @Nonnull Member member, @Nullable List<Activity> previous) {
        super(api2, responseNumber, member.getUser(), previous, member.getActivities(), IDENTIFIER);
        this.member = member;
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

