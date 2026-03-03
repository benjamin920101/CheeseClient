/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.internal.handle;

import java.util.Objects;
import net.dv8tion.jda.api.events.role.update.RoleUpdateColorEvent;
import net.dv8tion.jda.api.events.role.update.RoleUpdateHoistedEvent;
import net.dv8tion.jda.api.events.role.update.RoleUpdateMentionableEvent;
import net.dv8tion.jda.api.events.role.update.RoleUpdateNameEvent;
import net.dv8tion.jda.api.events.role.update.RoleUpdatePermissionsEvent;
import net.dv8tion.jda.api.events.role.update.RoleUpdatePositionEvent;
import net.dv8tion.jda.api.utils.data.DataObject;
import net.dv8tion.jda.internal.JDAImpl;
import net.dv8tion.jda.internal.entities.GuildImpl;
import net.dv8tion.jda.internal.entities.RoleImpl;
import net.dv8tion.jda.internal.handle.EventCache;
import net.dv8tion.jda.internal.handle.SocketHandler;

public class GuildRoleUpdateHandler
extends SocketHandler {
    public GuildRoleUpdateHandler(JDAImpl api2) {
        super(api2);
    }

    @Override
    protected Long handleInternally(DataObject content) {
        long guildId = content.getLong("guild_id");
        if (this.getJDA().getGuildSetupController().isLocked(guildId)) {
            return guildId;
        }
        DataObject rolejson = content.getObject("role");
        GuildImpl guild = (GuildImpl)this.getJDA().getGuildById(guildId);
        if (guild == null) {
            this.getJDA().getEventCache().cache(EventCache.Type.GUILD, guildId, this.responseNumber, this.allContent, this::handle);
            EventCache.LOG.debug("Received a Role Update for a Guild that is not yet cached: {}", (Object)content);
            return null;
        }
        long roleId = rolejson.getLong("id");
        RoleImpl role = (RoleImpl)guild.getRolesView().get(roleId);
        if (role == null) {
            this.getJDA().getEventCache().cache(EventCache.Type.ROLE, roleId, this.responseNumber, this.allContent, this::handle);
            EventCache.LOG.debug("Received a Role Update for Role that is not yet cached: {}", (Object)content);
            return null;
        }
        String name = rolejson.getString("name");
        int color = rolejson.getInt("color");
        if (color == 0) {
            color = 0x1FFFFFFF;
        }
        int position = rolejson.getInt("position");
        long permissions = rolejson.getLong("permissions");
        boolean hoisted = rolejson.getBoolean("hoist");
        boolean mentionable = rolejson.getBoolean("mentionable");
        if (!Objects.equals(name, role.getName())) {
            String oldName = role.getName();
            role.setName(name);
            this.getJDA().handleEvent(new RoleUpdateNameEvent(this.getJDA(), this.responseNumber, role, oldName));
        }
        if (color != role.getColorRaw()) {
            int oldColor = role.getColorRaw();
            role.setColor(color);
            this.getJDA().handleEvent(new RoleUpdateColorEvent(this.getJDA(), this.responseNumber, role, oldColor));
        }
        if (!Objects.equals(position, role.getPositionRaw())) {
            int oldPosition = role.getPosition();
            int oldPositionRaw = role.getPositionRaw();
            role.setRawPosition(position);
            this.getJDA().handleEvent(new RoleUpdatePositionEvent(this.getJDA(), this.responseNumber, role, oldPosition, oldPositionRaw));
        }
        if (!Objects.equals(permissions, role.getPermissionsRaw())) {
            long oldPermissionsRaw = role.getPermissionsRaw();
            role.setRawPermissions(permissions);
            this.getJDA().handleEvent(new RoleUpdatePermissionsEvent(this.getJDA(), this.responseNumber, role, oldPermissionsRaw));
        }
        if (hoisted != role.isHoisted()) {
            boolean wasHoisted = role.isHoisted();
            role.setHoisted(hoisted);
            this.getJDA().handleEvent(new RoleUpdateHoistedEvent(this.getJDA(), this.responseNumber, role, wasHoisted));
        }
        if (mentionable != role.isMentionable()) {
            boolean wasMentionable = role.isMentionable();
            role.setMentionable(mentionable);
            this.getJDA().handleEvent(new RoleUpdateMentionableEvent(this.getJDA(), this.responseNumber, role, wasMentionable));
        }
        return null;
    }
}

