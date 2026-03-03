/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.events.channel.category;

import javax.annotation.Nonnull;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Category;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.Event;

public abstract class GenericCategoryEvent
extends Event {
    protected final Category category;

    public GenericCategoryEvent(@Nonnull JDA api2, long responseNumber, @Nonnull Category category) {
        super(api2, responseNumber);
        this.category = category;
    }

    @Nonnull
    public Category getCategory() {
        return this.category;
    }

    @Nonnull
    public String getId() {
        return Long.toUnsignedString(this.getIdLong());
    }

    public long getIdLong() {
        return this.category.getIdLong();
    }

    @Nonnull
    public Guild getGuild() {
        return this.category.getGuild();
    }
}

