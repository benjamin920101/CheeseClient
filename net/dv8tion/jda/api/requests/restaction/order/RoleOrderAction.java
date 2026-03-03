/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.requests.restaction.order;

import javax.annotation.Nonnull;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.requests.restaction.order.OrderAction;

public interface RoleOrderAction
extends OrderAction<Role, RoleOrderAction> {
    @Nonnull
    public Guild getGuild();
}

