/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.internal.handle;

import net.dv8tion.jda.api.events.guild.GuildBanEvent;
import net.dv8tion.jda.api.events.guild.GuildUnbanEvent;
import net.dv8tion.jda.api.utils.data.DataObject;
import net.dv8tion.jda.internal.JDAImpl;
import net.dv8tion.jda.internal.entities.GuildImpl;
import net.dv8tion.jda.internal.entities.UserImpl;
import net.dv8tion.jda.internal.handle.EventCache;
import net.dv8tion.jda.internal.handle.SocketHandler;
import net.dv8tion.jda.internal.utils.JDALogger;

public class GuildBanHandler
extends SocketHandler {
    private final boolean banned;

    public GuildBanHandler(JDAImpl api2, boolean banned) {
        super(api2);
        this.banned = banned;
    }

    @Override
    protected Long handleInternally(DataObject content) {
        long id2 = content.getLong("guild_id");
        if (this.getJDA().getGuildSetupController().isLocked(id2)) {
            return id2;
        }
        DataObject userJson = content.getObject("user");
        GuildImpl guild = (GuildImpl)this.getJDA().getGuildById(id2);
        if (guild == null) {
            this.getJDA().getEventCache().cache(EventCache.Type.GUILD, id2, this.responseNumber, this.allContent, this::handle);
            EventCache.LOG.debug("Received Guild Member {} event for a Guild not yet cached.", JDALogger.getLazyString(() -> this.banned ? "Ban" : "Unban"));
            return null;
        }
        UserImpl user = this.getJDA().getEntityBuilder().createUser(userJson);
        if (this.banned) {
            this.getJDA().handleEvent(new GuildBanEvent(this.getJDA(), this.responseNumber, guild, user));
        } else {
            this.getJDA().handleEvent(new GuildUnbanEvent(this.getJDA(), this.responseNumber, guild, user));
        }
        return null;
    }
}

