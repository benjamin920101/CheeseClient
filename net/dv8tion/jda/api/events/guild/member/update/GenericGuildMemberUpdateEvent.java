/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.events.guild.member.update;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.UpdateEvent;
import net.dv8tion.jda.api.events.guild.member.GenericGuildMemberEvent;

public abstract class GenericGuildMemberUpdateEvent<T>
extends GenericGuildMemberEvent
implements UpdateEvent<Member, T> {
    protected final T previous;
    protected final T next;
    protected final String identifier;

    public GenericGuildMemberUpdateEvent(@Nonnull JDA api2, long responseNumber, @Nonnull Member member, @Nullable T previous, @Nullable T next, @Nonnull String identifier) {
        super(api2, responseNumber, member);
        this.previous = previous;
        this.next = next;
        this.identifier = identifier;
    }

    @Override
    @Nonnull
    public String getPropertyIdentifier() {
        return this.identifier;
    }

    @Override
    @Nonnull
    public Member getEntity() {
        return this.getMember();
    }

    @Override
    @Nullable
    public T getOldValue() {
        return this.previous;
    }

    @Override
    @Nullable
    public T getNewValue() {
        return this.next;
    }

    public String toString() {
        return "GenericGuildMemberUpdateEvent[" + this.getPropertyIdentifier() + "](" + this.getOldValue() + "->" + this.getNewValue() + ")";
    }
}

