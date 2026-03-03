/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.events.channel.category;

import javax.annotation.Nonnull;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Category;
import net.dv8tion.jda.api.events.channel.category.GenericCategoryEvent;

public class CategoryCreateEvent
extends GenericCategoryEvent {
    public CategoryCreateEvent(@Nonnull JDA api2, long responseNumber, @Nonnull Category category) {
        super(api2, responseNumber, category);
    }
}

