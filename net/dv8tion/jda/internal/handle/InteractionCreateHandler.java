/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.internal.handle;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.events.interaction.ButtonClickEvent;
import net.dv8tion.jda.api.events.interaction.GenericInteractionCreateEvent;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.interactions.InteractionType;
import net.dv8tion.jda.api.utils.data.DataObject;
import net.dv8tion.jda.internal.JDAImpl;
import net.dv8tion.jda.internal.handle.SocketHandler;
import net.dv8tion.jda.internal.interactions.ButtonInteractionImpl;
import net.dv8tion.jda.internal.interactions.CommandInteractionImpl;
import net.dv8tion.jda.internal.interactions.InteractionImpl;
import net.dv8tion.jda.internal.requests.WebSocketClient;

public class InteractionCreateHandler
extends SocketHandler {
    public InteractionCreateHandler(JDAImpl api2) {
        super(api2);
    }

    @Override
    protected Long handleInternally(DataObject content) {
        int type = content.getInt("type");
        if (content.getInt("version", 1) != 1) {
            WebSocketClient.LOG.debug("Received interaction with version {}. This version is currently unsupported by this version of JDA. Consider updating!", (Object)content.getInt("version", 1));
            return null;
        }
        long guildId = content.getUnsignedLong("guild_id", 0L);
        if (this.api.getGuildSetupController().isLocked(guildId)) {
            return guildId;
        }
        if (guildId != 0L && this.api.getGuildById(guildId) == null) {
            return null;
        }
        switch (InteractionType.fromKey(type)) {
            case SLASH_COMMAND: {
                this.handleCommand(content);
                break;
            }
            case COMPONENT: {
                this.handleAction(content);
            }
            default: {
                this.api.handleEvent(new GenericInteractionCreateEvent(this.api, this.responseNumber, new InteractionImpl(this.api, content)));
            }
        }
        return null;
    }

    private void handleCommand(DataObject content) {
        this.api.handleEvent(new SlashCommandEvent((JDA)this.api, this.responseNumber, new CommandInteractionImpl(this.api, content)));
    }

    private void handleAction(DataObject content) {
        if (content.getObject("data").getInt("component_type") != 2) {
            return;
        }
        this.api.handleEvent(new ButtonClickEvent((JDA)this.api, this.responseNumber, new ButtonInteractionImpl(this.api, content)));
    }
}

