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

public class GuildMemberRoleAddEvent
extends GenericGuildMemberEvent {
    private final List<Role> addedRoles;

    public GuildMemberRoleAddEvent(@Nonnull JDA api2, long responseNumber, @Nonnull Member member, @Nonnull List<Role> addedRoles) {
        super(api2, responseNumber, member);
        this.addedRoles = Collections.unmodifiableList(addedRoles);
    }

    @Nonnull
    public List<Role> getRoles() {
        return this.addedRoles;
    }
}

