/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.events.guild.member;

import java.util.Collections;
import java.util.List;
import javax.annotation.Nonnull;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.guild.member.GenericGuildMemberEvent;

public class GuildMemberRoleRemoveEvent
extends GenericGuildMemberEvent {
    private final List<Role> removedRoles;

    public GuildMemberRoleRemoveEvent(@Nonnull JDA api2, long responseNumber, @Nonnull Member member, @Nonnull List<Role> removedRoles) {
        super(api2, responseNumber, member);
        this.removedRoles = Collections.unmodifiableList(removedRoles);
    }

    public List<Role> getRoles() {
        return this.removedRoles;
    }
}

