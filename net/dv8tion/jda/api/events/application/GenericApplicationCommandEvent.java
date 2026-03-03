/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.events.application;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.Event;
import net.dv8tion.jda.api.interactions.commands.Command;

public abstract class GenericApplicationCommandEvent
extends Event {
    private final Command command;
    private final Guild guild;

    public GenericApplicationCommandEvent(@Nonnull JDA api2, long responseNumber, @Nonnull Command command, @Nullable Guild guild) {
        super(api2, responseNumber);
        this.command = command;
        this.guild = guild;
    }

    @Nonnull
    public Command getCommand() {
        return this.command;
    }

    @Nullable
    public Guild getGuild() {
        return this.guild;
    }
}

