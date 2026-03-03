/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.events.interaction;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.dv8tion.jda.annotations.Incubating;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.AbstractChannel;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.Event;
import net.dv8tion.jda.api.interactions.Interaction;
import net.dv8tion.jda.api.interactions.InteractionHook;
import net.dv8tion.jda.api.requests.restaction.interactions.ReplyAction;

@Incubating
public class GenericInteractionCreateEvent
extends Event
implements Interaction {
    private final Interaction interaction;

    public GenericInteractionCreateEvent(@Nonnull JDA api2, long responseNumber, @Nonnull Interaction interaction) {
        super(api2, responseNumber);
        this.interaction = interaction;
    }

    @Nonnull
    public Interaction getInteraction() {
        return this.interaction;
    }

    @Override
    @Nonnull
    public String getToken() {
        return this.interaction.getToken();
    }

    @Override
    public int getTypeRaw() {
        return this.interaction.getTypeRaw();
    }

    @Override
    @Nullable
    public Guild getGuild() {
        return this.interaction.getGuild();
    }

    @Override
    @Nullable
    public AbstractChannel getChannel() {
        return this.interaction.getChannel();
    }

    @Override
    @Nonnull
    public InteractionHook getHook() {
        return this.interaction.getHook();
    }

    @Override
    @Nullable
    public Member getMember() {
        return this.interaction.getMember();
    }

    @Override
    @Nonnull
    public User getUser() {
        return this.interaction.getUser();
    }

    @Override
    public long getIdLong() {
        return this.interaction.getIdLong();
    }

    @Override
    public boolean isAcknowledged() {
        return this.interaction.isAcknowledged();
    }

    @Override
    @Nonnull
    public ReplyAction deferReply() {
        return this.interaction.deferReply();
    }
}

