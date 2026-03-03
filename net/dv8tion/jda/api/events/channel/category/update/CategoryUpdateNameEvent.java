/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.events.channel.category.update;

import javax.annotation.Nonnull;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Category;
import net.dv8tion.jda.api.events.channel.category.update.GenericCategoryUpdateEvent;

public class CategoryUpdateNameEvent
extends GenericCategoryUpdateEvent<String> {
    public static final String IDENTIFIER = "name";

    public CategoryUpdateNameEvent(@Nonnull JDA api2, long responseNumber, @Nonnull Category category, @Nonnull String oldName) {
        super(api2, responseNumber, category, oldName, category.getName(), IDENTIFIER);
    }

    @Nonnull
    public String getOldName() {
        return this.getOldValue();
    }

    @Nonnull
    public String getNewName() {
        return this.getNewValue();
    }

    @Override
    @Nonnull
    public String getOldValue() {
        return (String)super.getOldValue();
    }

    @Override
    @Nonnull
    public String getNewValue() {
        return (String)super.getNewValue();
    }
}

