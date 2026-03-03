/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.internal.handle;

import gnu.trove.map.hash.TLongObjectHashMap;
import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.utils.data.DataArray;
import net.dv8tion.jda.api.utils.data.DataObject;
import net.dv8tion.jda.internal.JDAImpl;
import net.dv8tion.jda.internal.entities.EntityBuilder;
import net.dv8tion.jda.internal.handle.SocketHandler;
import net.dv8tion.jda.internal.requests.WebSocketClient;

public class ReadyHandler
extends SocketHandler {
    public ReadyHandler(JDAImpl api2) {
        super(api2);
    }

    @Override
    protected Long handleInternally(DataObject content) {
        EntityBuilder builder = this.getJDA().getEntityBuilder();
        DataArray guilds = content.getArray("guilds");
        TLongObjectHashMap<DataObject> distinctGuilds = new TLongObjectHashMap<DataObject>();
        for (int i2 = 0; i2 < guilds.length(); ++i2) {
            DataObject guild2 = guilds.getObject(i2);
            long id3 = guild2.getUnsignedLong("id");
            DataObject previous = distinctGuilds.put(id3, guild2);
            if (previous == null) continue;
            WebSocketClient.LOG.warn("Found duplicate guild for id {} in ready payload", (Object)id3);
        }
        DataObject selfJson = content.getObject("user");
        selfJson.put("application_id", content.optObject("application").map(obj -> obj.getUnsignedLong("id")).orElse(selfJson.getUnsignedLong("id")));
        builder.createSelfUser(selfJson);
        if (this.getJDA().getGuildSetupController().setIncompleteCount(distinctGuilds.size())) {
            distinctGuilds.forEachEntry((id2, guild) -> {
                this.getJDA().getGuildSetupController().onReady(id2, (DataObject)guild);
                return true;
            });
        }
        this.handleReady(content);
        return null;
    }

    public void handleReady(DataObject content) {
        EntityBuilder builder = this.getJDA().getEntityBuilder();
        DataArray privateChannels = content.getArray("private_channels");
        block3: for (int i2 = 0; i2 < privateChannels.length(); ++i2) {
            DataObject chan = privateChannels.getObject(i2);
            ChannelType type = ChannelType.fromId(chan.getInt("type"));
            switch (type) {
                case PRIVATE: {
                    builder.createPrivateChannel(chan);
                    continue block3;
                }
                default: {
                    WebSocketClient.LOG.warn("Received a Channel in the private_channels array in READY of an unknown type! Type: {}", (Object)type);
                }
            }
        }
    }
}

