/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.events.emote.update;

import java.util.List;
import javax.annotation.Nonnull;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Emote;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.emote.update.GenericEmoteUpdateEvent;

public class EmoteUpdateRolesEvent
extends GenericEmoteUpdateEvent<List<Role>> {
    public static final String IDENTIFIER = "roles";

    public EmoteUpdateRolesEvent(@Nonnull JDA api2, long responseNumber, @Nonnull Emote emote, @Nonnull List<Role> oldRoles) {
        super(api2, responseNumber, emote, oldRoles, emote.getRoles(), IDENTIFIER);
    }

    @Nonnull
    public List<Role> getOldRoles() {
        return this.getOldValue();
    }

    @Nonnull
    public List<Role> getNewRoles() {
        return this.getNewValue();
    }

    @Override
    @Nonnull
    public List<Role> getOldValue() {
        return (List)super.getOldValue();
    }

    @Override
    @Nonnull
    public List<Role> getNewValue() {
        return (List)super.getNewValue();
    }
}

