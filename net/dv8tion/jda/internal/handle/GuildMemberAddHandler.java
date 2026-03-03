/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.internal.handle;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.utils.data.DataObject;
import net.dv8tion.jda.internal.JDAImpl;
import net.dv8tion.jda.internal.entities.GuildImpl;
import net.dv8tion.jda.internal.entities.MemberImpl;
import net.dv8tion.jda.internal.handle.EventCache;
import net.dv8tion.jda.internal.handle.SocketHandler;

public class GuildMemberAddHandler
extends SocketHandler {
    public GuildMemberAddHandler(JDAImpl api2) {
        super(api2);
    }

    @Override
    protected Long handleInternally(DataObject content) {
        long id2 = content.getLong("guild_id");
        boolean setup = this.getJDA().getGuildSetupController().onAddMember(id2, content);
        if (setup) {
            return null;
        }
        GuildImpl guild = (GuildImpl)this.getJDA().getGuildById(id2);
        if (guild == null) {
            this.getJDA().getEventCache().cache(EventCache.Type.GUILD, id2, this.responseNumber, this.allContent, this::handle);
            EventCache.LOG.debug("Caching member for guild that is not yet cached. Guild ID: {} JSON: {}", (Object)id2, (Object)content);
            return null;
        }
        guild.onMemberAdd();
        MemberImpl member = this.getJDA().getEntityBuilder().createMember(guild, content);
        this.getJDA().getEntityBuilder().updateMemberCache(member);
        this.getJDA().handleEvent(new GuildMemberJoinEvent((JDA)this.getJDA(), this.responseNumber, member));
        return null;
    }
}

