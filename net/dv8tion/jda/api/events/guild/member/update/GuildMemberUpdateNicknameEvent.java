/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.events.guild.member.update;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.guild.member.update.GenericGuildMemberUpdateEvent;

public class GuildMemberUpdateNicknameEvent
extends GenericGuildMemberUpdateEvent<String> {
    public static final String IDENTIFIER = "nick";

    public GuildMemberUpdateNicknameEvent(@Nonnull JDA api2, long responseNumber, @Nonnull Member member, @Nullable String oldNick) {
        super(api2, responseNumber, member, oldNick, member.getNickname(), IDENTIFIER);
    }

    @Nullable
    public String getOldNickname() {
        return (String)this.getOldValue();
    }

    @Nullable
    public String getNewNickname() {
        return (String)this.getNewValue();
    }
}

