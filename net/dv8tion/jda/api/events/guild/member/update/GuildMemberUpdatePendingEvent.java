/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.events.guild.member.update;

import javax.annotation.Nonnull;
import net.dv8tion.jda.annotations.Incubating;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.guild.member.update.GenericGuildMemberUpdateEvent;

@Incubating
public class GuildMemberUpdatePendingEvent
extends GenericGuildMemberUpdateEvent<Boolean> {
    public static final String IDENTIFIER = "pending";

    public GuildMemberUpdatePendingEvent(@Nonnull JDA api2, long responseNumber, @Nonnull Member member, boolean previous) {
        super(api2, responseNumber, member, previous, member.isPending(), IDENTIFIER);
    }

    public boolean getOldPending() {
        return (Boolean)this.getOldValue();
    }

    public boolean getNewPending() {
        return (Boolean)this.getNewValue();
    }
}

