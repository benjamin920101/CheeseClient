/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.internal.handle;

import java.util.LinkedList;
import java.util.List;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.guild.member.GuildMemberUpdateEvent;
import net.dv8tion.jda.api.utils.data.DataArray;
import net.dv8tion.jda.api.utils.data.DataObject;
import net.dv8tion.jda.internal.JDAImpl;
import net.dv8tion.jda.internal.entities.GuildImpl;
import net.dv8tion.jda.internal.entities.MemberImpl;
import net.dv8tion.jda.internal.handle.EventCache;
import net.dv8tion.jda.internal.handle.SocketHandler;

public class GuildMemberUpdateHandler
extends SocketHandler {
    public GuildMemberUpdateHandler(JDAImpl api2) {
        super(api2);
    }

    @Override
    protected Long handleInternally(DataObject content) {
        long id2 = content.getLong("guild_id");
        if (this.getJDA().getGuildSetupController().isLocked(id2)) {
            return id2;
        }
        DataObject userJson = content.getObject("user");
        long userId = userJson.getLong("id");
        GuildImpl guild = (GuildImpl)this.getJDA().getGuildById(id2);
        if (guild == null) {
            EventCache.LOG.debug("Got GuildMember update but JDA currently does not have the Guild cached. Ignoring. {}", (Object)content);
            return null;
        }
        MemberImpl member = (MemberImpl)guild.getMembersView().get(userId);
        if (member == null) {
            member = this.getJDA().getEntityBuilder().createMember(guild, content);
        } else {
            List<Role> newRoles = this.toRolesList(guild, content.getArray("roles"));
            this.getJDA().getEntityBuilder().updateMember(guild, member, content, newRoles);
        }
        this.getJDA().getEntityBuilder().updateMemberCache(member);
        this.getJDA().handleEvent(new GuildMemberUpdateEvent((JDA)this.getJDA(), this.responseNumber, member));
        return null;
    }

    private List<Role> toRolesList(GuildImpl guild, DataArray array) {
        LinkedList<Role> roles = new LinkedList<Role>();
        for (int i2 = 0; i2 < array.length(); ++i2) {
            long id2 = array.getLong(i2);
            Role r2 = (Role)guild.getRolesView().get(id2);
            if (r2 == null) {
                this.getJDA().getEventCache().cache(EventCache.Type.ROLE, id2, this.responseNumber, this.allContent, this::handle);
                EventCache.LOG.debug("Got GuildMember update but one of the Roles for the Member is not yet cached.");
                return null;
            }
            roles.add(r2);
        }
        return roles;
    }
}

