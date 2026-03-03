/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.events.channel.text.update;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Category;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.channel.text.update.GenericTextChannelUpdateEvent;

public class TextChannelUpdateParentEvent
extends GenericTextChannelUpdateEvent<Category> {
    public static final String IDENTIFIER = "parent";

    public TextChannelUpdateParentEvent(@Nonnull JDA api2, long responseNumber, @Nonnull TextChannel channel, @Nullable Category oldParent) {
        super(api2, responseNumber, channel, oldParent, channel.getParent(), IDENTIFIER);
    }

    @Nullable
    public Category getOldParent() {
        return (Category)this.getOldValue();
    }

    @Nullable
    public Category getNewParent() {
        return (Category)this.getNewValue();
    }
}

