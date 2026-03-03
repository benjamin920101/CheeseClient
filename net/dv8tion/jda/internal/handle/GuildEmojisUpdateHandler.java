/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.internal.handle;

import gnu.trove.map.TLongObjectMap;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import net.dv8tion.jda.api.entities.Emote;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.emote.EmoteAddedEvent;
import net.dv8tion.jda.api.events.emote.EmoteRemovedEvent;
import net.dv8tion.jda.api.events.emote.update.EmoteUpdateNameEvent;
import net.dv8tion.jda.api.events.emote.update.EmoteUpdateRolesEvent;
import net.dv8tion.jda.api.utils.cache.CacheFlag;
import net.dv8tion.jda.api.utils.data.DataArray;
import net.dv8tion.jda.api.utils.data.DataObject;
import net.dv8tion.jda.internal.JDAImpl;
import net.dv8tion.jda.internal.entities.EmoteImpl;
import net.dv8tion.jda.internal.entities.GuildImpl;
import net.dv8tion.jda.internal.handle.EventCache;
import net.dv8tion.jda.internal.handle.SocketHandler;
import net.dv8tion.jda.internal.utils.UnlockHook;
import net.dv8tion.jda.internal.utils.cache.SnowflakeCacheViewImpl;
import org.apache.commons.collections4.CollectionUtils;

public class GuildEmojisUpdateHandler
extends SocketHandler {
    public GuildEmojisUpdateHandler(JDAImpl api2) {
        super(api2);
    }

    @Override
    protected Long handleInternally(DataObject content) {
        ArrayList<EmoteImpl> newEmotes;
        ArrayList oldEmotes;
        if (!this.getJDA().isCacheFlagSet(CacheFlag.EMOTE)) {
            return null;
        }
        long guildId = content.getLong("guild_id");
        if (this.getJDA().getGuildSetupController().isLocked(guildId)) {
            return guildId;
        }
        GuildImpl guild = (GuildImpl)this.getJDA().getGuildById(guildId);
        if (guild == null) {
            this.getJDA().getEventCache().cache(EventCache.Type.GUILD, guildId, this.responseNumber, this.allContent, this::handle);
            return null;
        }
        DataArray array = content.getArray("emojis");
        SnowflakeCacheViewImpl<Emote> emoteView = guild.getEmotesView();
        try (UnlockHook hook = emoteView.writeLock();){
            TLongObjectMap tLongObjectMap = emoteView.getMap();
            oldEmotes = new ArrayList(tLongObjectMap.valueCollection());
            newEmotes = new ArrayList<EmoteImpl>();
            for (int i2 = 0; i2 < array.length(); ++i2) {
                DataObject current = array.getObject(i2);
                long emoteId = current.getLong("id");
                EmoteImpl emote = (EmoteImpl)tLongObjectMap.get(emoteId);
                EmoteImpl oldEmote = null;
                if (emote == null) {
                    emote = new EmoteImpl(emoteId, guild);
                    newEmotes.add(emote);
                } else {
                    oldEmotes.remove(emote);
                    oldEmote = emote.clone();
                }
                emote.setName(current.getString("name")).setAnimated(current.getBoolean("animated")).setManaged(current.getBoolean("managed"));
                DataArray roles = current.getArray("roles");
                Set<Role> newRoles = emote.getRoleSet();
                HashSet<Role> oldRoles = new HashSet<Role>(newRoles);
                for (int j2 = 0; j2 < roles.length(); ++j2) {
                    Role role = guild.getRoleById(roles.getString(j2));
                    if (role == null) continue;
                    newRoles.add(role);
                    oldRoles.remove(role);
                }
                for (Role r2 : oldRoles) {
                    newRoles.remove(r2);
                }
                tLongObjectMap.put(emote.getIdLong(), emote);
                this.handleReplace(oldEmote, emote);
            }
            for (Emote e2 : oldEmotes) {
                tLongObjectMap.remove(e2.getIdLong());
            }
        }
        for (Emote emote : oldEmotes) {
            this.getJDA().handleEvent(new EmoteRemovedEvent(this.getJDA(), this.responseNumber, emote));
        }
        for (Emote emote : newEmotes) {
            this.getJDA().handleEvent(new EmoteAddedEvent(this.getJDA(), this.responseNumber, emote));
        }
        return null;
    }

    private void handleReplace(Emote oldEmote, Emote newEmote) {
        if (oldEmote == null || newEmote == null) {
            return;
        }
        if (!Objects.equals(oldEmote.getName(), newEmote.getName())) {
            this.getJDA().handleEvent(new EmoteUpdateNameEvent(this.getJDA(), this.responseNumber, newEmote, oldEmote.getName()));
        }
        if (!CollectionUtils.isEqualCollection(oldEmote.getRoles(), newEmote.getRoles())) {
            this.getJDA().handleEvent(new EmoteUpdateRolesEvent(this.getJDA(), this.responseNumber, newEmote, oldEmote.getRoles()));
        }
    }
}

