/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.events.channel.category.update;

import javax.annotation.Nonnull;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Category;
import net.dv8tion.jda.api.events.channel.category.update.GenericCategoryUpdateEvent;

public class CategoryUpdatePositionEvent
extends GenericCategoryUpdateEvent<Integer> {
    public static final String IDENTIFIER = "position";

    public CategoryUpdatePositionEvent(@Nonnull JDA api2, long responseNumber, @Nonnull Category category, int oldPosition) {
        super(api2, responseNumber, category, oldPosition, category.getPositionRaw(), IDENTIFIER);
    }

    public int getOldPosition() {
        return (Integer)this.getOldValue();
    }

    public int getNewPosition() {
        return (Integer)this.getNewValue();
    }
}

