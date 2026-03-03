/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.internal.handle;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.application.ApplicationCommandDeleteEvent;
import net.dv8tion.jda.api.interactions.commands.Command;
import net.dv8tion.jda.api.utils.data.DataObject;
import net.dv8tion.jda.internal.JDAImpl;
import net.dv8tion.jda.internal.handle.EventCache;
import net.dv8tion.jda.internal.handle.SocketHandler;

public class ApplicationCommandDeleteHandler
extends SocketHandler {
    public ApplicationCommandDeleteHandler(JDAImpl api2) {
        super(api2);
    }

    @Override
    protected Long handleInternally(DataObject content) {
        long guildId = content.getUnsignedLong("guild_id");
        if (this.api.getGuildSetupController().isLocked(guildId)) {
            return guildId;
        }
        Guild guild = this.api.getGuildById(guildId);
        if (guildId != 0L && guild == null) {
            EventCache.LOG.debug("Received APPLICATION_COMMAND_UPDATE for Guild that isn't cache. GuildId: {}", (Object)guildId);
            this.api.getEventCache().cache(EventCache.Type.GUILD, guildId, this.responseNumber, this.allContent, this::handle);
            return null;
        }
        Command command = new Command(this.api, guild, content);
        this.api.handleEvent(new ApplicationCommandDeleteEvent(this.api, this.responseNumber, command, guild));
        return null;
    }
}

