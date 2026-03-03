/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.events.guild.update;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.guild.update.GenericGuildUpdateEvent;

public class GuildUpdateOwnerEvent
extends GenericGuildUpdateEvent<Member> {
    public static final String IDENTIFIER = "owner";
    private final long prevId;
    private final long nextId;

    public GuildUpdateOwnerEvent(@Nonnull JDA api2, long responseNumber, @Nonnull Guild guild, @Nullable Member oldOwner, long prevId, long nextId) {
        super(api2, responseNumber, guild, oldOwner, guild.getOwner(), IDENTIFIER);
        this.prevId = prevId;
        this.nextId = nextId;
    }

    public long getNewOwnerIdLong() {
        return this.nextId;
    }

    @Nonnull
    public String getNewOwnerId() {
        return Long.toUnsignedString(this.nextId);
    }

    public long getOldOwnerIdLong() {
        return this.prevId;
    }

    @Nonnull
    public String getOldOwnerId() {
        return Long.toUnsignedString(this.prevId);
    }

    @Nullable
    public Member getOldOwner() {
        return (Member)this.getOldValue();
    }

    @Nullable
    public Member getNewOwner() {
        return (Member)this.getNewValue();
    }
}

