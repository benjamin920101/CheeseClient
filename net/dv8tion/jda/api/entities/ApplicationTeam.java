/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.entities;

import java.util.List;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.dv8tion.jda.api.entities.ISnowflake;
import net.dv8tion.jda.api.entities.TeamMember;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.utils.MiscUtil;
import net.dv8tion.jda.internal.utils.Checks;

public interface ApplicationTeam
extends ISnowflake {
    public static final String ICON_URL = "https://cdn.discordapp.com/team-icons/%s/%s.png";

    @Nullable
    default public TeamMember getOwner() {
        return this.getMemberById(this.getOwnerIdLong());
    }

    @Nonnull
    default public String getOwnerId() {
        return Long.toUnsignedString(this.getOwnerIdLong());
    }

    public long getOwnerIdLong();

    @Nullable
    public String getIconId();

    @Nullable
    default public String getIconUrl() {
        String iconId = this.getIconId();
        return iconId == null ? null : String.format(ICON_URL, this.getId(), iconId);
    }

    @Nonnull
    public List<TeamMember> getMembers();

    default public boolean isMember(@Nonnull User user) {
        return this.getMember(user) != null;
    }

    @Nullable
    default public TeamMember getMember(@Nonnull User user) {
        Checks.notNull(user, "User");
        return this.getMemberById(user.getIdLong());
    }

    @Nullable
    default public TeamMember getMemberById(@Nonnull String userId) {
        return this.getMemberById(MiscUtil.parseSnowflake(userId));
    }

    @Nullable
    default public TeamMember getMemberById(long userId) {
        for (TeamMember member : this.getMembers()) {
            if (member.getUser().getIdLong() != userId) continue;
            return member;
        }
        return null;
    }
}

