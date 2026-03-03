/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.internal.entities;

import java.util.Collections;
import java.util.List;
import javax.annotation.Nonnull;
import net.dv8tion.jda.api.entities.ApplicationTeam;
import net.dv8tion.jda.api.entities.TeamMember;

public class ApplicationTeamImpl
implements ApplicationTeam {
    private final String iconId;
    private final List<TeamMember> members;
    private final long id;
    private final long ownerId;

    public ApplicationTeamImpl(String iconId, List<TeamMember> members, long id2, long ownerId) {
        this.iconId = iconId;
        this.members = Collections.unmodifiableList(members);
        this.id = id2;
        this.ownerId = ownerId;
    }

    @Override
    public long getOwnerIdLong() {
        return this.ownerId;
    }

    @Override
    public String getIconId() {
        return this.iconId;
    }

    @Override
    @Nonnull
    public List<TeamMember> getMembers() {
        return this.members;
    }

    @Override
    public long getIdLong() {
        return this.id;
    }

    public int hashCode() {
        return Long.hashCode(this.id);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ApplicationTeamImpl)) {
            return false;
        }
        ApplicationTeamImpl app2 = (ApplicationTeamImpl)obj;
        return app2.id == this.id;
    }

    public String toString() {
        return "ApplicationTeam(" + this.getId() + ')';
    }
}

