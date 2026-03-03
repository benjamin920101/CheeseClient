/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.events.guild.member;

import javax.annotation.Nonnull;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.guild.member.GenericGuildMemberEvent;

public class GuildMemberJoinEvent
extends GenericGuildMemberEvent {
    public GuildMemberJoinEvent(@Nonnull JDA api2, long responseNumber, @Nonnull Member member) {
        super(api2, responseNumber, member);
    }
}

