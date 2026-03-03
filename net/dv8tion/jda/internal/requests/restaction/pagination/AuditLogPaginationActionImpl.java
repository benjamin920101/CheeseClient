/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.internal.requests.restaction.pagination;

import gnu.trove.map.hash.TLongObjectHashMap;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nonnull;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.audit.ActionType;
import net.dv8tion.jda.api.audit.AuditLogEntry;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.exceptions.InsufficientPermissionException;
import net.dv8tion.jda.api.exceptions.ParsingException;
import net.dv8tion.jda.api.requests.Request;
import net.dv8tion.jda.api.requests.Response;
import net.dv8tion.jda.api.requests.restaction.pagination.AuditLogPaginationAction;
import net.dv8tion.jda.api.utils.data.DataArray;
import net.dv8tion.jda.api.utils.data.DataObject;
import net.dv8tion.jda.internal.entities.EntityBuilder;
import net.dv8tion.jda.internal.entities.GuildImpl;
import net.dv8tion.jda.internal.requests.Route;
import net.dv8tion.jda.internal.requests.restaction.pagination.PaginationActionImpl;
import net.dv8tion.jda.internal.utils.Checks;

public class AuditLogPaginationActionImpl
extends PaginationActionImpl<AuditLogEntry, AuditLogPaginationAction>
implements AuditLogPaginationAction {
    protected final Guild guild;
    protected ActionType type = null;
    protected String userId = null;

    public AuditLogPaginationActionImpl(Guild guild) {
        super(guild.getJDA(), Route.Guilds.GET_AUDIT_LOGS.compile(guild.getId()), 1, 100, 100);
        if (!guild.getSelfMember().hasPermission(Permission.VIEW_AUDIT_LOGS)) {
            throw new InsufficientPermissionException(guild, Permission.VIEW_AUDIT_LOGS);
        }
        this.guild = guild;
    }

    @Override
    @Nonnull
    public AuditLogPaginationActionImpl type(ActionType type) {
        this.type = type;
        return this;
    }

    @Override
    @Nonnull
    public AuditLogPaginationActionImpl user(User user) {
        return this.user(user == null ? null : user.getId());
    }

    @Override
    @Nonnull
    public AuditLogPaginationActionImpl user(String userId) {
        if (userId != null) {
            Checks.isSnowflake(userId, "User ID");
        }
        this.userId = userId;
        return this;
    }

    @Override
    @Nonnull
    public AuditLogPaginationActionImpl user(long userId) {
        return this.user(Long.toUnsignedString(userId));
    }

    @Override
    @Nonnull
    public Guild getGuild() {
        return this.guild;
    }

    @Override
    protected Route.CompiledRoute finalizeRoute() {
        Route.CompiledRoute route = super.finalizeRoute();
        String limit = String.valueOf(this.limit.get());
        long last = this.lastKey;
        route = route.withQueryParams("limit", limit);
        if (this.type != null) {
            route = route.withQueryParams("action_type", String.valueOf(this.type.getKey()));
        }
        if (this.userId != null) {
            route = route.withQueryParams("user_id", this.userId);
        }
        if (last != 0L) {
            route = route.withQueryParams("before", Long.toUnsignedString(last));
        }
        return route;
    }

    @Override
    protected void handleSuccess(Response response, Request<List<AuditLogEntry>> request) {
        int i2;
        DataObject obj = response.getObject();
        DataArray users = obj.getArray("users");
        DataArray webhooks = obj.getArray("webhooks");
        DataArray entries = obj.getArray("audit_log_entries");
        ArrayList<AuditLogEntry> list = new ArrayList<AuditLogEntry>(entries.length());
        EntityBuilder builder = this.api.getEntityBuilder();
        TLongObjectHashMap<DataObject> userMap = new TLongObjectHashMap<DataObject>();
        for (int i3 = 0; i3 < users.length(); ++i3) {
            DataObject user = users.getObject(i3);
            userMap.put(user.getLong("id"), user);
        }
        TLongObjectHashMap<DataObject> webhookMap = new TLongObjectHashMap<DataObject>();
        for (i2 = 0; i2 < webhooks.length(); ++i2) {
            DataObject webhook = webhooks.getObject(i2);
            webhookMap.put(webhook.getLong("id"), webhook);
        }
        for (i2 = 0; i2 < entries.length(); ++i2) {
            try {
                DataObject entry = entries.getObject(i2);
                DataObject user = (DataObject)userMap.get(entry.getLong("user_id", 0L));
                DataObject webhook = (DataObject)webhookMap.get(entry.getLong("target_id", 0L));
                AuditLogEntry result = builder.createAuditLogEntry((GuildImpl)this.guild, entry, user, webhook);
                list.add(result);
                if (this.useCache) {
                    this.cached.add(result);
                }
                this.last = result;
                this.lastKey = ((AuditLogEntry)this.last).getIdLong();
                continue;
            }
            catch (NullPointerException | ParsingException e2) {
                LOG.warn("Encountered exception in AuditLogPagination", e2);
            }
        }
        request.onSuccess(list);
    }

    @Override
    protected long getKey(AuditLogEntry it2) {
        return it2.getIdLong();
    }
}

