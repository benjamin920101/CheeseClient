/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.internal.entities;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import javax.annotation.Nonnull;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.ListedEmote;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.exceptions.InsufficientPermissionException;
import net.dv8tion.jda.api.managers.EmoteManager;
import net.dv8tion.jda.api.requests.restaction.AuditableRestAction;
import net.dv8tion.jda.internal.JDAImpl;
import net.dv8tion.jda.internal.entities.GuildImpl;
import net.dv8tion.jda.internal.managers.EmoteManagerImpl;
import net.dv8tion.jda.internal.requests.Route;
import net.dv8tion.jda.internal.requests.restaction.AuditableRestActionImpl;

public class EmoteImpl
implements ListedEmote {
    private final long id;
    private final JDAImpl api;
    private final Set<Role> roles;
    private EmoteManager manager;
    private GuildImpl guild;
    private boolean managed = false;
    private boolean available = true;
    private boolean animated = false;
    private String name;
    private User user;

    public EmoteImpl(long id2, GuildImpl guild) {
        this.id = id2;
        this.api = guild.getJDA();
        this.guild = guild;
        this.roles = ConcurrentHashMap.newKeySet();
    }

    public EmoteImpl(long id2, JDAImpl api2) {
        this.id = id2;
        this.api = api2;
        this.guild = null;
        this.roles = null;
    }

    @Override
    public GuildImpl getGuild() {
        if (this.guild == null) {
            return null;
        }
        GuildImpl realGuild = (GuildImpl)this.api.getGuildById(this.guild.getIdLong());
        if (realGuild != null) {
            this.guild = realGuild;
        }
        return this.guild;
    }

    @Override
    @Nonnull
    public List<Role> getRoles() {
        if (!this.canProvideRoles()) {
            throw new IllegalStateException("Unable to return roles because this emote is from a message. (We do not know the origin Guild of this emote)");
        }
        return Collections.unmodifiableList(new LinkedList<Role>(this.roles));
    }

    @Override
    public boolean canProvideRoles() {
        return this.roles != null;
    }

    @Override
    @Nonnull
    public String getName() {
        return this.name;
    }

    @Override
    public boolean isManaged() {
        return this.managed;
    }

    @Override
    public boolean isAvailable() {
        return this.available;
    }

    @Override
    public long getIdLong() {
        return this.id;
    }

    @Override
    @Nonnull
    public JDAImpl getJDA() {
        return this.api;
    }

    @Override
    @Nonnull
    public User getUser() {
        if (!this.hasUser()) {
            throw new IllegalStateException("This emote does not have a user");
        }
        return this.user;
    }

    @Override
    public boolean hasUser() {
        return this.user != null;
    }

    @Override
    @Nonnull
    public EmoteManager getManager() {
        if (this.manager == null) {
            this.manager = new EmoteManagerImpl(this);
            return this.manager;
        }
        return this.manager;
    }

    @Override
    public boolean isAnimated() {
        return this.animated;
    }

    @Override
    @Nonnull
    public AuditableRestAction<Void> delete() {
        if (this.getGuild() == null) {
            throw new IllegalStateException("The emote you are trying to delete is not an actual emote we have access to (it is from a message)!");
        }
        if (this.managed) {
            throw new UnsupportedOperationException("You cannot delete a managed emote!");
        }
        if (!this.getGuild().getSelfMember().hasPermission(Permission.MANAGE_EMOTES)) {
            throw new InsufficientPermissionException(this.getGuild(), Permission.MANAGE_EMOTES);
        }
        Route.CompiledRoute route = Route.Emotes.DELETE_EMOTE.compile(this.getGuild().getId(), this.getId());
        return new AuditableRestActionImpl<Void>(this.getJDA(), route);
    }

    public EmoteImpl setName(String name) {
        this.name = name;
        return this;
    }

    public EmoteImpl setAnimated(boolean animated) {
        this.animated = animated;
        return this;
    }

    public EmoteImpl setManaged(boolean val) {
        this.managed = val;
        return this;
    }

    public EmoteImpl setAvailable(boolean available) {
        this.available = available;
        return this;
    }

    public EmoteImpl setUser(User user) {
        this.user = user;
        return this;
    }

    public Set<Role> getRoleSet() {
        return this.roles;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof EmoteImpl)) {
            return false;
        }
        EmoteImpl oEmote = (EmoteImpl)obj;
        return this.id == oEmote.id && this.getName().equals(oEmote.getName());
    }

    public int hashCode() {
        return Long.hashCode(this.id);
    }

    public String toString() {
        return "E:" + this.getName() + '(' + this.getIdLong() + ')';
    }

    public EmoteImpl clone() {
        EmoteImpl copy = new EmoteImpl(this.id, this.getGuild()).setUser(this.user).setManaged(this.managed).setAnimated(this.animated).setName(this.name);
        copy.roles.addAll(this.roles);
        return copy;
    }
}

