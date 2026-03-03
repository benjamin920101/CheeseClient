/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.interactions.components;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.interactions.Interaction;
import net.dv8tion.jda.api.interactions.components.ActionRow;
import net.dv8tion.jda.api.interactions.components.Component;
import net.dv8tion.jda.api.interactions.components.ComponentLayout;
import net.dv8tion.jda.api.requests.restaction.interactions.UpdateInteractionAction;
import net.dv8tion.jda.internal.requests.restaction.interactions.UpdateInteractionActionImpl;
import net.dv8tion.jda.internal.utils.Checks;

public interface ComponentInteraction
extends Interaction {
    @Nonnull
    public String getComponentId();

    @Nullable
    public Component getComponent();

    @Nullable
    public Message getMessage();

    public long getMessageIdLong();

    @Nonnull
    default public String getMessageId() {
        return Long.toUnsignedString(this.getMessageIdLong());
    }

    @Nonnull
    public Component.Type getComponentType();

    @Override
    @Nonnull
    public MessageChannel getChannel();

    @Nonnull
    @CheckReturnValue
    public UpdateInteractionAction deferEdit();

    @Nonnull
    @CheckReturnValue
    default public UpdateInteractionAction editMessage(@Nonnull Message message) {
        Checks.notNull(message, "Message");
        UpdateInteractionActionImpl action = (UpdateInteractionActionImpl)this.deferEdit();
        return action.applyMessage(message);
    }

    @Nonnull
    @CheckReturnValue
    default public UpdateInteractionAction editMessage(@Nonnull String content) {
        Checks.notNull(content, "Content");
        return this.deferEdit().setContent(content);
    }

    @Nonnull
    @CheckReturnValue
    default public UpdateInteractionAction editComponents(@Nonnull Collection<? extends ComponentLayout> components) {
        Checks.noneNull(components, "Components");
        if (components.stream().anyMatch(it2 -> !(it2 instanceof ActionRow))) {
            throw new UnsupportedOperationException("The provided component layout is not supported");
        }
        List actionRows = components.stream().map(ActionRow.class::cast).collect(Collectors.toList());
        return this.deferEdit().setActionRows(actionRows);
    }

    @Nonnull
    @CheckReturnValue
    default public UpdateInteractionAction editComponents(ComponentLayout ... components) {
        Checks.noneNull(components, "ComponentLayouts");
        return this.editComponents(Arrays.asList(components));
    }

    @Nonnull
    @CheckReturnValue
    default public UpdateInteractionAction editMessageEmbeds(@Nonnull Collection<? extends MessageEmbed> embeds) {
        Checks.noneNull(embeds, "MessageEmbed");
        return this.deferEdit().setEmbeds(embeds);
    }

    @Nonnull
    @CheckReturnValue
    default public UpdateInteractionAction editMessageEmbeds(MessageEmbed ... embeds) {
        Checks.noneNull(embeds, "MessageEmbed");
        return this.deferEdit().setEmbeds(embeds);
    }

    @Nonnull
    @CheckReturnValue
    default public UpdateInteractionAction editMessageFormat(@Nonnull String format, Object ... args) {
        Checks.notNull(format, "Format String");
        return this.editMessage(String.format(format, args));
    }
}

