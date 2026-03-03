/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.events.guild.member.update;

import java.time.OffsetDateTime;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.guild.member.update.GenericGuildMemberUpdateEvent;

public class GuildMemberUpdateBoostTimeEvent
extends GenericGuildMemberUpdateEvent<OffsetDateTime> {
    public static final String IDENTIFIER = "boost_time";

    public GuildMemberUpdateBoostTimeEvent(@Nonnull JDA api2, long responseNumber, @Nonnull Member member, @Nullable OffsetDateTime previous) {
        super(api2, responseNumber, member, previous, member.getTimeBoosted(), IDENTIFIER);
    }

    @Nullable
    public OffsetDateTime getOldTimeBoosted() {
        return (OffsetDateTime)this.getOldValue();
    }

    @Nullable
    public OffsetDateTime getNewTimeBoosted() {
        return (OffsetDateTime)this.getNewValue();
    }
}

