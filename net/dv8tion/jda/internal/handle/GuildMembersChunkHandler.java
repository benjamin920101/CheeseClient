/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.internal.handle;

import gnu.trove.map.TLongObjectMap;
import gnu.trove.map.hash.TLongObjectHashMap;
import net.dv8tion.jda.api.utils.data.DataArray;
import net.dv8tion.jda.api.utils.data.DataObject;
import net.dv8tion.jda.internal.JDAImpl;
import net.dv8tion.jda.internal.entities.EntityBuilder;
import net.dv8tion.jda.internal.entities.GuildImpl;
import net.dv8tion.jda.internal.entities.MemberImpl;
import net.dv8tion.jda.internal.handle.SocketHandler;
import net.dv8tion.jda.internal.requests.WebSocketClient;

public class GuildMembersChunkHandler
extends SocketHandler {
    public GuildMembersChunkHandler(JDAImpl api2) {
        super(api2);
    }

    @Override
    protected Long handleInternally(DataObject content) {
        long guildId = content.getLong("guild_id");
        DataArray members = content.getArray("members");
        GuildImpl guild = (GuildImpl)this.getJDA().getGuildById(guildId);
        if (guild != null) {
            if (this.api.getClient().getChunkManager().handleChunk(guildId, content)) {
                return null;
            }
            WebSocketClient.LOG.debug("Received member chunk for guild that is already in cache. GuildId: {} Count: {} Index: {}/{}", guildId, members.length(), content.getInt("chunk_index"), content.getInt("chunk_count"));
            EntityBuilder builder = this.getJDA().getEntityBuilder();
            TLongObjectMap presences = content.optArray("presences").map(it2 -> builder.convertToUserMap(o2 -> o2.getObject("user").getUnsignedLong("id"), (DataArray)it2)).orElseGet(TLongObjectHashMap::new);
            for (int i2 = 0; i2 < members.length(); ++i2) {
                DataObject object = members.getObject(i2);
                long userId = object.getObject("user").getUnsignedLong("id");
                DataObject presence = (DataObject)presences.get(userId);
                MemberImpl member = builder.createMember(guild, object, null, presence);
                builder.updateMemberCache(member);
            }
            return null;
        }
        this.getJDA().getGuildSetupController().onMemberChunk(guildId, content);
        return null;
    }
}

