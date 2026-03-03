/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.internal.handle;

import net.dv8tion.jda.api.utils.data.DataObject;
import net.dv8tion.jda.internal.JDAImpl;
import net.dv8tion.jda.internal.entities.GuildImpl;
import net.dv8tion.jda.internal.handle.SocketHandler;

public class GuildCreateHandler
extends SocketHandler {
    public GuildCreateHandler(JDAImpl api2) {
        super(api2);
    }

    @Override
    protected Long handleInternally(DataObject content) {
        long id2 = content.getLong("id");
        GuildImpl guild = (GuildImpl)this.getJDA().getGuildById(id2);
        if (guild == null) {
            this.getJDA().getGuildSetupController().onCreate(id2, content);
        }
        return null;
    }
}

