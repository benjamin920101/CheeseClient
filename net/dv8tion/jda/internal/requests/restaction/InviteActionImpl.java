/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.internal.requests.restaction;

import java.util.concurrent.TimeUnit;
import java.util.function.BooleanSupplier;
import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnull;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Invite;
import net.dv8tion.jda.api.requests.Request;
import net.dv8tion.jda.api.requests.Response;
import net.dv8tion.jda.api.requests.restaction.InviteAction;
import net.dv8tion.jda.api.utils.data.DataObject;
import net.dv8tion.jda.internal.requests.Route;
import net.dv8tion.jda.internal.requests.restaction.AuditableRestActionImpl;
import net.dv8tion.jda.internal.utils.Checks;
import okhttp3.RequestBody;

public class InviteActionImpl
extends AuditableRestActionImpl<Invite>
implements InviteAction {
    private Integer maxAge = null;
    private Integer maxUses = null;
    private Boolean temporary = null;
    private Boolean unique = null;

    public InviteActionImpl(JDA api2, String channelId) {
        super(api2, Route.Invites.CREATE_INVITE.compile(channelId));
    }

    @Override
    @Nonnull
    public InviteActionImpl setCheck(BooleanSupplier checks) {
        return (InviteActionImpl)super.setCheck(checks);
    }

    @Override
    @Nonnull
    public InviteActionImpl timeout(long timeout, @Nonnull TimeUnit unit) {
        return (InviteActionImpl)super.timeout(timeout, unit);
    }

    @Override
    @Nonnull
    public InviteActionImpl deadline(long timestamp) {
        return (InviteActionImpl)super.deadline(timestamp);
    }

    @Override
    @Nonnull
    @CheckReturnValue
    public InviteActionImpl setMaxAge(Integer maxAge) {
        if (maxAge != null) {
            Checks.notNegative(maxAge, "maxAge");
        }
        this.maxAge = maxAge;
        return this;
    }

    @Override
    @Nonnull
    @CheckReturnValue
    public InviteActionImpl setMaxAge(Long maxAge, @Nonnull TimeUnit timeUnit) {
        if (maxAge == null) {
            return this.setMaxAge(null);
        }
        Checks.notNegative(maxAge, "maxAge");
        Checks.notNull((Object)timeUnit, "timeUnit");
        return this.setMaxAge(Math.toIntExact(timeUnit.toSeconds(maxAge)));
    }

    @Override
    @Nonnull
    @CheckReturnValue
    public InviteActionImpl setMaxUses(Integer maxUses) {
        if (maxUses != null) {
            Checks.notNegative(maxUses, "maxUses");
        }
        this.maxUses = maxUses;
        return this;
    }

    @Override
    @Nonnull
    @CheckReturnValue
    public InviteActionImpl setTemporary(Boolean temporary) {
        this.temporary = temporary;
        return this;
    }

    @Override
    @Nonnull
    @CheckReturnValue
    public InviteActionImpl setUnique(Boolean unique) {
        this.unique = unique;
        return this;
    }

    @Override
    protected RequestBody finalizeData() {
        DataObject object = DataObject.empty();
        if (this.maxAge != null) {
            object.put("max_age", this.maxAge);
        }
        if (this.maxUses != null) {
            object.put("max_uses", this.maxUses);
        }
        if (this.temporary != null) {
            object.put("temporary", this.temporary);
        }
        if (this.unique != null) {
            object.put("unique", this.unique);
        }
        return this.getRequestBody(object);
    }

    @Override
    protected void handleSuccess(Response response, Request<Invite> request) {
        request.onSuccess(this.api.getEntityBuilder().createInvite(response.getObject()));
    }
}

