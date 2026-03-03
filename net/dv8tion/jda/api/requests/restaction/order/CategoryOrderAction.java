/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.requests.restaction.order;

import javax.annotation.Nonnull;
import net.dv8tion.jda.api.entities.Category;
import net.dv8tion.jda.api.requests.restaction.order.ChannelOrderAction;

public interface CategoryOrderAction
extends ChannelOrderAction {
    @Nonnull
    public Category getCategory();
}

