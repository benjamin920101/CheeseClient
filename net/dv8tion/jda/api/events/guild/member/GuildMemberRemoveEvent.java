/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.events.guild.member;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.guild.GenericGuildEvent;

public class GuildMemberRemoveEvent
extends GenericGuildEvent {
    private final User user;
    private final Member member;

    public GuildMemberRemoveEvent(@Nonnull JDA api2, long responseNumber, @Nonnull Guild guild, @Nonnull User user, @Nullable Member member) {
        super(api2, responseNumber, guild);
        this.user = user;
        this.member = member;
    }

    @Nonnull
    public User getUser() {
        return this.user;
    }

    @Nullable
    public Member getMember() {
        return this.member;
    }
}

