/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.interactions.commands;

import gnu.trove.map.TLongObjectMap;
import java.util.Objects;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.dv8tion.jda.api.entities.AbstractChannel;
import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.entities.GuildChannel;
import net.dv8tion.jda.api.entities.IMentionable;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.utils.data.DataObject;

public class OptionMapping {
    private final DataObject data;
    private final OptionType type;
    private final String name;
    private final TLongObjectMap<Object> resolved;

    public OptionMapping(DataObject data, TLongObjectMap<Object> resolved) {
        this.data = data;
        this.type = OptionType.fromKey(data.getInt("type", -1));
        this.name = data.getString("name");
        this.resolved = resolved;
    }

    @Nonnull
    public OptionType getType() {
        return this.type;
    }

    @Nonnull
    public String getName() {
        return this.name;
    }

    @Nonnull
    public String getAsString() {
        return this.data.getString("value");
    }

    public boolean getAsBoolean() {
        if (this.type != OptionType.BOOLEAN) {
            throw new IllegalStateException("Cannot convert option of type " + (Object)((Object)this.type) + " to boolean");
        }
        return this.data.getBoolean("value");
    }

    public long getAsLong() {
        switch (this.type) {
            default: {
                throw new IllegalStateException("Cannot convert option of type " + (Object)((Object)this.type) + " to long");
            }
            case STRING: 
            case MENTIONABLE: 
            case CHANNEL: 
            case ROLE: 
            case USER: 
            case INTEGER: 
        }
        return this.data.getLong("value");
    }

    @Nonnull
    public IMentionable getAsMentionable() {
        Object entity = this.resolved.get(this.getAsLong());
        if (entity instanceof IMentionable) {
            return (IMentionable)entity;
        }
        throw new IllegalStateException("Cannot resolve option of type " + (Object)((Object)this.type) + " to IMentionable");
    }

    @Nullable
    public Member getAsMember() {
        if (this.type != OptionType.USER) {
            throw new IllegalStateException("Cannot resolve Member for option " + this.getName() + " of type " + (Object)((Object)this.type));
        }
        Object object = this.resolved.get(this.getAsLong());
        if (object instanceof Member) {
            return (Member)object;
        }
        return null;
    }

    @Nonnull
    public User getAsUser() {
        if (this.type != OptionType.USER) {
            throw new IllegalStateException("Cannot resolve User for option " + this.getName() + " of type " + (Object)((Object)this.type));
        }
        Object object = this.resolved.get(this.getAsLong());
        if (object instanceof Member) {
            return ((Member)object).getUser();
        }
        if (object instanceof User) {
            return (User)object;
        }
        throw new IllegalStateException("Could not resolve user!");
    }

    @Nonnull
    public Role getAsRole() {
        if (this.type != OptionType.ROLE) {
            throw new IllegalStateException("Cannot resolve Role for option " + this.getName() + " of type " + (Object)((Object)this.type));
        }
        Object role = this.resolved.get(this.getAsLong());
        if (role instanceof Role) {
            return (Role)role;
        }
        throw new IllegalStateException("Could not resolve role!");
    }

    @Nonnull
    public GuildChannel getAsGuildChannel() {
        AbstractChannel value = this.getAsChannel();
        if (value instanceof GuildChannel) {
            return (GuildChannel)value;
        }
        throw new IllegalStateException("Could not resolve GuildChannel!");
    }

    @Nullable
    public MessageChannel getAsMessageChannel() {
        AbstractChannel value = this.getAsChannel();
        return value instanceof MessageChannel ? (MessageChannel)value : null;
    }

    @Nonnull
    public ChannelType getChannelType() {
        AbstractChannel channel = this.getAsChannel();
        return channel == null ? ChannelType.UNKNOWN : channel.getType();
    }

    public String toString() {
        return "Option[" + (Object)((Object)this.getType()) + "](" + this.getName() + "=" + this.getAsString() + ")";
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.getType(), this.getName()});
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof OptionMapping)) {
            return false;
        }
        OptionMapping data = (OptionMapping)obj;
        return this.getType() == data.getType() && this.getName().equals(data.getName());
    }

    @Nullable
    private AbstractChannel getAsChannel() {
        if (this.type != OptionType.CHANNEL) {
            throw new IllegalStateException("Cannot resolve AbstractChannel for option " + this.getName() + " of type " + (Object)((Object)this.type));
        }
        return (AbstractChannel)this.resolved.get(this.getAsLong());
    }
}

