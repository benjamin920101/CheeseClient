/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.events.interaction;

import java.util.List;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.interaction.GenericInteractionCreateEvent;
import net.dv8tion.jda.api.interactions.commands.CommandInteraction;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.internal.interactions.CommandInteractionImpl;

public class SlashCommandEvent
extends GenericInteractionCreateEvent
implements CommandInteraction {
    private final CommandInteractionImpl commandInteraction;

    public SlashCommandEvent(@Nonnull JDA api2, long responseNumber, @Nonnull CommandInteractionImpl interaction) {
        super(api2, responseNumber, interaction);
        this.commandInteraction = interaction;
    }

    @Override
    @Nonnull
    public MessageChannel getChannel() {
        return this.commandInteraction.getChannel();
    }

    @Override
    @Nonnull
    public String getName() {
        return this.commandInteraction.getName();
    }

    @Override
    @Nullable
    public String getSubcommandName() {
        return this.commandInteraction.getSubcommandName();
    }

    @Override
    @Nullable
    public String getSubcommandGroup() {
        return this.commandInteraction.getSubcommandGroup();
    }

    @Override
    public long getCommandIdLong() {
        return this.commandInteraction.getCommandIdLong();
    }

    @Override
    @Nonnull
    public List<OptionMapping> getOptions() {
        return this.commandInteraction.getOptions();
    }
}

