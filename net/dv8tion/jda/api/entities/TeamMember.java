/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.entities;

import javax.annotation.Nonnull;
import net.dv8tion.jda.api.entities.User;

public interface TeamMember {
    @Nonnull
    public User getUser();

    @Nonnull
    public MembershipState getMembershipState();

    @Nonnull
    default public String getTeamId() {
        return Long.toUnsignedString(this.getTeamIdLong());
    }

    public long getTeamIdLong();

    public static enum MembershipState {
        INVITED(1),
        ACCEPTED(2),
        UNKNOWN(-1);

        private final int key;

        private MembershipState(int key) {
            this.key = key;
        }

        public int getKey() {
            return this.key;
        }

        @Nonnull
        public static MembershipState fromKey(int key) {
            for (MembershipState state : MembershipState.values()) {
                if (state.key != key) continue;
                return state;
            }
            return UNKNOWN;
        }
    }
}

