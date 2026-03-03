/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.events.user.update;

import java.util.List;
import javax.annotation.Nonnull;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.user.update.GenericUserPresenceEvent;
import net.dv8tion.jda.api.events.user.update.GenericUserUpdateEvent;
import net.dv8tion.jda.internal.JDAImpl;

public class UserUpdateActivityOrderEvent
extends GenericUserUpdateEvent<List<Activity>>
implements GenericUserPresenceEvent {
    public static final String IDENTIFIER = "activity_order";
    private final Member member;

    public UserUpdateActivityOrderEvent(@Nonnull JDAImpl api2, long responseNumber, @Nonnull List<Activity> previous, @Nonnull Member member) {
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

    @Override
    @Nonnull
    public List<Activity> getOldValue() {
        return (List)super.getOldValue();
    }

    @Override
    @Nonnull
    public List<Activity> getNewValue() {
        return (List)super.getNewValue();
    }
}

