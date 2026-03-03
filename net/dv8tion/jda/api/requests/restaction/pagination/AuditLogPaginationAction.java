/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.requests.restaction.pagination;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.dv8tion.jda.api.audit.ActionType;
import net.dv8tion.jda.api.audit.AuditLogEntry;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.requests.restaction.pagination.PaginationAction;

public interface AuditLogPaginationAction
extends PaginationAction<AuditLogEntry, AuditLogPaginationAction> {
    @Nonnull
    public Guild getGuild();

    @Nonnull
    public AuditLogPaginationAction type(@Nullable ActionType var1);

    @Nonnull
    public AuditLogPaginationAction user(@Nullable User var1);

    @Nonnull
    public AuditLogPaginationAction user(@Nullable String var1);

    @Nonnull
    public AuditLogPaginationAction user(long var1);
}

