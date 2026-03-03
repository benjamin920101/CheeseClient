/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.events.application;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.application.GenericApplicationCommandEvent;
import net.dv8tion.jda.api.interactions.commands.Command;

public class ApplicationCommandDeleteEvent
extends GenericApplicationCommandEvent {
    public ApplicationCommandDeleteEvent(@Nonnull JDA api2, long responseNumber, @Nonnull Command command, @Nullable Guild guild) {
        super(api2, responseNumber, command, guild);
    }
}

