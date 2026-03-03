/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.events.guild.member;

import javax.annotation.Nonnull;
import net.dv8tion.jda.annotations.DeprecatedSince;
import net.dv8tion.jda.annotations.ReplaceWith;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.guild.member.GenericGuildMemberEvent;

@Deprecated
@DeprecatedSince(value="4.2.0")
@ReplaceWith(value="GuildMemberRemoveEvent")
public class GuildMemberLeaveEvent
extends GenericGuildMemberEvent {
    public GuildMemberLeaveEvent(@Nonnull JDA api2, long responseNumber, @Nonnull Member member) {
        super(api2, responseNumber, member);
    }
}

