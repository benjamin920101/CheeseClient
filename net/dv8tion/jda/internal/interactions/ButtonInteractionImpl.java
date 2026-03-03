/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.internal.interactions;

import javax.annotation.Nonnull;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.interactions.components.Button;
import net.dv8tion.jda.api.interactions.components.ButtonInteraction;
import net.dv8tion.jda.api.interactions.components.Component;
import net.dv8tion.jda.api.requests.restaction.interactions.UpdateInteractionAction;
import net.dv8tion.jda.api.utils.data.DataObject;
import net.dv8tion.jda.internal.JDAImpl;
import net.dv8tion.jda.internal.interactions.InteractionImpl;
import net.dv8tion.jda.internal.requests.restaction.interactions.UpdateInteractionActionImpl;

public class ButtonInteractionImpl
extends InteractionImpl
implements ButtonInteraction {
    private final String customId;
    private final Message message;
    private final long messageId;
    private final Button button;

    public ButtonInteractionImpl(JDAImpl jda, DataObject data) {
        super(jda, data);
        this.customId = data.getObject("data").getString("custom_id");
        DataObject messageJson = data.getObject("message");
        this.messageId = messageJson.getUnsignedLong("id");
        this.message = messageJson.isNull("type") ? null : jda.getEntityBuilder().createMessage(messageJson);
        this.button = this.message != null ? this.message.getButtonById(this.customId) : null;
    }

    @Override
    @Nonnull
    public MessageChannel getChannel() {
        return (MessageChannel)super.getChannel();
    }

    @Override
    @Nonnull
    public UpdateInteractionAction deferEdit() {
        return new UpdateInteractionActionImpl(this.hook);
    }

    @Override
    @Nonnull
    public String getComponentId() {
        return this.customId;
    }

    @Override
    public Message getMessage() {
        return this.message;
    }

    @Override
    public long getMessageIdLong() {
        return this.messageId;
    }

    @Override
    @Nonnull
    public Component.Type getComponentType() {
        return Component.Type.BUTTON;
    }

    @Override
    @Nonnull
    public Button getButton() {
        return this.button;
    }
}

