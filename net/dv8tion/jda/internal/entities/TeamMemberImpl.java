/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.internal.entities;

import java.util.Objects;
import javax.annotation.Nonnull;
import net.dv8tion.jda.api.entities.TeamMember;
import net.dv8tion.jda.api.entities.User;

public class TeamMemberImpl
implements TeamMember {
    private final User user;
    private final TeamMember.MembershipState state;
    private final long teamId;

    public TeamMemberImpl(User user, TeamMember.MembershipState state, long teamId) {
        this.user = user;
        this.state = state;
        this.teamId = teamId;
    }

    @Override
    @Nonnull
    public User getUser() {
        return this.user;
    }

    @Override
    @Nonnull
    public TeamMember.MembershipState getMembershipState() {
        return this.state;
    }

    @Override
    public long getTeamIdLong() {
        return this.teamId;
    }

    public int hashCode() {
        return Objects.hash(this.user, this.teamId);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof TeamMemberImpl)) {
            return false;
        }
        TeamMemberImpl member = (TeamMemberImpl)obj;
        return member.teamId == this.teamId && member.user.equals(this.user);
    }

    public String toString() {
        return "TeamMember(" + this.getTeamId() + ", " + this.user + ")";
    }
}

