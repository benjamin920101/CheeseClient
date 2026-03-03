/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.events.interaction;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.interaction.GenericInteractionCreateEvent;
import net.dv8tion.jda.api.interactions.components.Button;
import net.dv8tion.jda.api.interactions.components.ButtonInteraction;
import net.dv8tion.jda.api.interactions.components.Component;
import net.dv8tion.jda.api.requests.restaction.interactions.UpdateInteractionAction;

public class ButtonClickEvent
extends GenericInteractionCreateEvent
implements ButtonInteraction {
    private final ButtonInteraction interaction;

    public ButtonClickEvent(@Nonnull JDA api2, long responseNumber, @Nonnull ButtonInteraction interaction) {
        super(api2, responseNumber, interaction);
        this.interaction = interaction;
    }

    @Override
    @Nonnull
    public MessageChannel getChannel() {
        return this.interaction.getChannel();
    }

    @Override
    @Nonnull
    public UpdateInteractionAction deferEdit() {
        return this.interaction.deferEdit();
    }

    @Override
    @Nonnull
    public ButtonInteraction getInteraction() {
        return this.interaction;
    }

    @Override
    @Nonnull
    public String getComponentId() {
        return this.interaction.getComponentId();
    }

    @Override
    @Nullable
    public Message getMessage() {
        return this.interaction.getMessage();
    }

    @Override
    public long getMessageIdLong() {
        return this.interaction.getMessageIdLong();
    }

    @Override
    @Nonnull
    public Component.Type getComponentType() {
        return this.interaction.getComponentType();
    }

    @Override
    @Nullable
    public Button getButton() {
        return this.interaction.getButton();
    }
}

