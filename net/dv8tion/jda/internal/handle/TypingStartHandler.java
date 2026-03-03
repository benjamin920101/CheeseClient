/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.internal.handle;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.PrivateChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.user.UserTypingEvent;
import net.dv8tion.jda.api.utils.data.DataObject;
import net.dv8tion.jda.internal.JDAImpl;
import net.dv8tion.jda.internal.entities.EntityBuilder;
import net.dv8tion.jda.internal.entities.GuildImpl;
import net.dv8tion.jda.internal.entities.MemberImpl;
import net.dv8tion.jda.internal.handle.SocketHandler;

public class TypingStartHandler
extends SocketHandler {
    public TypingStartHandler(JDAImpl api2) {
        super(api2);
    }

    @Override
    protected Long handleInternally(DataObject content) {
        GuildImpl guild = null;
        if (!content.isNull("guild_id")) {
            long guildId = content.getUnsignedLong("guild_id");
            guild = (GuildImpl)this.getJDA().getGuildById(guildId);
            if (this.getJDA().getGuildSetupController().isLocked(guildId)) {
                return guildId;
            }
            if (guild == null) {
                return null;
            }
        }
        long channelId = content.getLong("channel_id");
        MessageChannel channel = (MessageChannel)this.getJDA().getTextChannelsView().get(channelId);
        if (channel == null) {
            channel = (MessageChannel)this.getJDA().getPrivateChannelsView().get(channelId);
        }
        if (channel == null) {
            return null;
        }
        long userId = content.getLong("user_id");
        MemberImpl member = null;
        User user = channel instanceof PrivateChannel ? ((PrivateChannel)channel).getUser() : (User)this.getJDA().getUsersView().get(userId);
        if (!content.isNull("member")) {
            EntityBuilder entityBuilder = this.getJDA().getEntityBuilder();
            member = entityBuilder.createMember(guild, content.getObject("member"));
            entityBuilder.updateMemberCache(member);
            user = member.getUser();
        }
        if (user == null) {
            return null;
        }
        OffsetDateTime timestamp = Instant.ofEpochSecond(content.getInt("timestamp")).atOffset(ZoneOffset.UTC);
        this.getJDA().handleEvent(new UserTypingEvent(this.getJDA(), this.responseNumber, user, channel, timestamp, member));
        return null;
    }
}

