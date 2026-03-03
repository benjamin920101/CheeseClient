/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.events;

import javax.annotation.Nonnull;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.events.Event;
import net.dv8tion.jda.internal.JDAImpl;
import net.dv8tion.jda.internal.handle.GuildSetupController;

public class ReadyEvent
extends Event {
    private final int availableGuilds = (int)this.getJDA().getGuildCache().size();
    private final int unavailableGuilds;

    public ReadyEvent(@Nonnull JDA api2, long responseNumber) {
        super(api2, responseNumber);
        GuildSetupController setupController = ((JDAImpl)this.getJDA()).getGuildSetupController();
        this.unavailableGuilds = setupController.getSetupNodes(GuildSetupController.Status.UNAVAILABLE).size() + setupController.getUnavailableGuilds().size();
    }

    public int getGuildAvailableCount() {
        return this.availableGuilds;
    }

    public int getGuildUnavailableCount() {
        return this.unavailableGuilds;
    }

    public int getGuildTotalCount() {
        return this.getGuildAvailableCount() + this.getGuildUnavailableCount();
    }
}

