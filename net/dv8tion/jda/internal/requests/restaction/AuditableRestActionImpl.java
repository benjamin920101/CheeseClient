/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.internal.requests.restaction;

import java.util.concurrent.TimeUnit;
import java.util.function.BiFunction;
import java.util.function.BooleanSupplier;
import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.audit.ThreadLocalReason;
import net.dv8tion.jda.api.requests.Request;
import net.dv8tion.jda.api.requests.Response;
import net.dv8tion.jda.api.requests.restaction.AuditableRestAction;
import net.dv8tion.jda.api.utils.data.DataObject;
import net.dv8tion.jda.internal.requests.RestActionImpl;
import net.dv8tion.jda.internal.requests.Route;
import net.dv8tion.jda.internal.utils.EncodingUtil;
import okhttp3.RequestBody;
import org.apache.commons.collections4.map.CaseInsensitiveMap;

public class AuditableRestActionImpl<T>
extends RestActionImpl<T>
implements AuditableRestAction<T> {
    protected String reason = null;

    public AuditableRestActionImpl(JDA api2, Route.CompiledRoute route) {
        super(api2, route);
    }

    public AuditableRestActionImpl(JDA api2, Route.CompiledRoute route, RequestBody data) {
        super(api2, route, data);
    }

    public AuditableRestActionImpl(JDA api2, Route.CompiledRoute route, DataObject data) {
        super(api2, route, data);
    }

    public AuditableRestActionImpl(JDA api2, Route.CompiledRoute route, BiFunction<Response, Request<T>, T> handler) {
        super(api2, route, handler);
    }

    public AuditableRestActionImpl(JDA api2, Route.CompiledRoute route, DataObject data, BiFunction<Response, Request<T>, T> handler) {
        super(api2, route, data, handler);
    }

    public AuditableRestActionImpl(JDA api2, Route.CompiledRoute route, RequestBody data, BiFunction<Response, Request<T>, T> handler) {
        super(api2, route, data, handler);
    }

    @Override
    @Nonnull
    public AuditableRestAction<T> setCheck(BooleanSupplier checks) {
        return (AuditableRestAction)super.setCheck(checks);
    }

    @Override
    @Nonnull
    public AuditableRestAction<T> timeout(long timeout, @Nonnull TimeUnit unit) {
        return (AuditableRestAction)super.timeout(timeout, unit);
    }

    @Override
    @Nonnull
    public AuditableRestAction<T> deadline(long timestamp) {
        return (AuditableRestAction)super.deadline(timestamp);
    }

    @Override
    @Nonnull
    @CheckReturnValue
    public AuditableRestActionImpl<T> reason(@Nullable String reason) {
        this.reason = reason;
        return this;
    }

    @Override
    protected CaseInsensitiveMap<String, String> finalizeHeaders() {
        CaseInsensitiveMap<String, String> headers = super.finalizeHeaders();
        if (this.reason == null || this.reason.isEmpty()) {
            String localReason = ThreadLocalReason.getCurrent();
            if (localReason == null || localReason.isEmpty()) {
                return headers;
            }
            return this.generateHeaders(headers, localReason);
        }
        return this.generateHeaders(headers, this.reason);
    }

    @Nonnull
    private CaseInsensitiveMap<String, String> generateHeaders(CaseInsensitiveMap<String, String> headers, String reason) {
        if (headers == null) {
            headers = new CaseInsensitiveMap();
        }
        headers.put("X-Audit-Log-Reason", this.uriEncode(reason));
        return headers;
    }

    private String uriEncode(String input) {
        String formEncode = EncodingUtil.encodeUTF8(input);
        return formEncode.replace('+', ' ');
    }
}

