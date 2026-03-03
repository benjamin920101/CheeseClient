/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.internal.managers;

import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.templates.Template;
import net.dv8tion.jda.api.exceptions.InsufficientPermissionException;
import net.dv8tion.jda.api.managers.TemplateManager;
import net.dv8tion.jda.api.utils.data.DataObject;
import net.dv8tion.jda.internal.managers.ManagerBase;
import net.dv8tion.jda.internal.requests.Route;
import net.dv8tion.jda.internal.utils.Checks;
import okhttp3.RequestBody;

public class TemplateManagerImpl
extends ManagerBase<TemplateManager>
implements TemplateManager {
    protected final Template template;
    protected final JDA api;
    protected String name;
    protected String description;

    public TemplateManagerImpl(Template template) {
        super(template.getJDA(), Route.Templates.MODIFY_TEMPLATE.compile(template.getGuild().getId(), template.getCode()));
        this.template = template;
        this.api = template.getJDA();
        if (TemplateManagerImpl.isPermissionChecksEnabled()) {
            this.checkPermissions();
        }
    }

    @Override
    @Nonnull
    @CheckReturnValue
    public TemplateManagerImpl reset(long fields) {
        super.reset(fields);
        if ((fields & 1L) == 1L) {
            this.name = null;
        }
        if ((fields & 2L) == 2L) {
            this.description = null;
        }
        return this;
    }

    @Override
    @Nonnull
    @CheckReturnValue
    public TemplateManagerImpl reset(long ... fields) {
        super.reset(fields);
        return this;
    }

    @Override
    @Nonnull
    @CheckReturnValue
    public TemplateManagerImpl reset() {
        super.reset();
        this.name = null;
        this.description = null;
        return this;
    }

    @Override
    @Nonnull
    @CheckReturnValue
    public TemplateManagerImpl setName(@Nonnull String name) {
        Checks.notEmpty(name, "Name");
        Checks.notLonger(name, 100, "Name");
        this.name = name;
        this.set |= 1L;
        return this;
    }

    @Override
    @Nonnull
    @CheckReturnValue
    public TemplateManagerImpl setDescription(@Nullable String description) {
        if (description != null) {
            Checks.notLonger(this.name, 120, "Description");
        }
        this.description = description;
        this.set |= 2L;
        return this;
    }

    @Override
    protected RequestBody finalizeData() {
        DataObject body = DataObject.empty();
        if (this.shouldUpdate(1L)) {
            body.put("name", this.name);
        }
        if (this.shouldUpdate(2L)) {
            body.put("description", this.name);
        }
        this.reset();
        return this.getRequestBody(body);
    }

    @Override
    protected boolean checkPermissions() {
        Guild guild = this.api.getGuildById(this.template.getGuild().getIdLong());
        if (guild == null) {
            return true;
        }
        if (!guild.getSelfMember().hasPermission(Permission.MANAGE_SERVER)) {
            throw new InsufficientPermissionException(guild, Permission.MANAGE_SERVER);
        }
        return super.checkPermissions();
    }
}

