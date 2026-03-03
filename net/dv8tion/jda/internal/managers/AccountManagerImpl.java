/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.internal.managers;

import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnull;
import net.dv8tion.jda.api.entities.Icon;
import net.dv8tion.jda.api.entities.SelfUser;
import net.dv8tion.jda.api.managers.AccountManager;
import net.dv8tion.jda.api.requests.Request;
import net.dv8tion.jda.api.requests.Response;
import net.dv8tion.jda.api.utils.data.DataObject;
import net.dv8tion.jda.internal.managers.ManagerBase;
import net.dv8tion.jda.internal.requests.Route;
import net.dv8tion.jda.internal.utils.Checks;
import okhttp3.RequestBody;

public class AccountManagerImpl
extends ManagerBase<AccountManager>
implements AccountManager {
    protected final SelfUser selfUser;
    protected String name;
    protected Icon avatar;

    public AccountManagerImpl(SelfUser selfUser) {
        super(selfUser.getJDA(), Route.Self.MODIFY_SELF.compile(new String[0]));
        this.selfUser = selfUser;
    }

    @Override
    @Nonnull
    public SelfUser getSelfUser() {
        return this.selfUser;
    }

    @Override
    @Nonnull
    @CheckReturnValue
    public AccountManagerImpl reset(long fields) {
        super.reset(fields);
        if ((fields & 2L) == 2L) {
            this.avatar = null;
        }
        return this;
    }

    @Override
    @Nonnull
    @CheckReturnValue
    public AccountManagerImpl reset(long ... fields) {
        super.reset(fields);
        return this;
    }

    @Override
    @Nonnull
    @CheckReturnValue
    public AccountManagerImpl reset() {
        super.reset();
        this.avatar = null;
        return this;
    }

    @Override
    @Nonnull
    @CheckReturnValue
    public AccountManagerImpl setName(@Nonnull String name) {
        Checks.notBlank(name, "Name");
        name = name.trim();
        Checks.notEmpty(name, "Name");
        Checks.notLonger(name, 32, "Name");
        this.name = name;
        this.set |= 1L;
        return this;
    }

    @Override
    @Nonnull
    @CheckReturnValue
    public AccountManagerImpl setAvatar(Icon avatar) {
        this.avatar = avatar;
        this.set |= 2L;
        return this;
    }

    @Override
    protected RequestBody finalizeData() {
        DataObject body = DataObject.empty();
        body.put("username", this.getSelfUser().getName());
        body.put("avatar", this.getSelfUser().getAvatarId());
        if (this.shouldUpdate(1L)) {
            body.put("username", this.name);
        }
        if (this.shouldUpdate(2L)) {
            body.put("avatar", this.avatar == null ? null : this.avatar.getEncoding());
        }
        this.reset();
        return this.getRequestBody(body);
    }

    @Override
    protected void handleSuccess(Response response, Request<Void> request) {
        String newToken = response.getObject().getString("token").replace("Bot ", "");
        this.api.setToken(newToken);
        request.onSuccess(null);
    }
}

