/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.entities.templates;

import java.time.OffsetDateTime;
import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.templates.TemplateGuild;
import net.dv8tion.jda.api.exceptions.InsufficientPermissionException;
import net.dv8tion.jda.api.managers.TemplateManager;
import net.dv8tion.jda.api.requests.RestAction;
import net.dv8tion.jda.internal.JDAImpl;
import net.dv8tion.jda.internal.managers.TemplateManagerImpl;
import net.dv8tion.jda.internal.requests.RestActionImpl;
import net.dv8tion.jda.internal.requests.Route;
import net.dv8tion.jda.internal.utils.Checks;

public class Template {
    private final JDAImpl api;
    private final String code;
    private final String name;
    private final String description;
    private final int uses;
    private final User creator;
    private final OffsetDateTime createdAt;
    private final OffsetDateTime updatedAt;
    private final TemplateGuild guild;
    private final boolean synced;
    protected TemplateManager manager;

    public Template(JDAImpl api2, String code, String name, String description, int uses, User creator, OffsetDateTime createdAt, OffsetDateTime updatedAt, TemplateGuild guild, boolean synced) {
        this.api = api2;
        this.code = code;
        this.name = name;
        this.description = description;
        this.uses = uses;
        this.creator = creator;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.guild = guild;
        this.synced = synced;
    }

    @Nonnull
    public static RestAction<Template> resolve(JDA api2, String code) {
        Checks.notEmpty(code, "code");
        Checks.noWhitespace(code, "code");
        Checks.notNull(api2, "api");
        Route.CompiledRoute route = Route.Templates.GET_TEMPLATE.compile(code);
        JDAImpl jda = (JDAImpl)api2;
        return new RestActionImpl<Template>(api2, route, (response, request) -> jda.getEntityBuilder().createTemplate(response.getObject()));
    }

    @Nonnull
    @CheckReturnValue
    public RestAction<Template> sync() {
        this.checkInteraction();
        Route.CompiledRoute route = Route.Templates.SYNC_TEMPLATE.compile(this.guild.getId(), this.code);
        return new RestActionImpl<Template>((JDA)this.api, route, (response, request) -> this.api.getEntityBuilder().createTemplate(response.getObject()));
    }

    @Nonnull
    @CheckReturnValue
    public RestAction<Void> delete() {
        this.checkInteraction();
        Route.CompiledRoute route = Route.Templates.DELETE_TEMPLATE.compile(this.guild.getId(), this.code);
        return new RestActionImpl<Void>(this.api, route);
    }

    @Nonnull
    public String getCode() {
        return this.code;
    }

    @Nonnull
    public String getName() {
        return this.name;
    }

    @Nullable
    public String getDescription() {
        return this.description;
    }

    public int getUses() {
        return this.uses;
    }

    @Nonnull
    public User getCreator() {
        return this.creator;
    }

    @Nonnull
    public OffsetDateTime getTimeCreated() {
        return this.createdAt;
    }

    @Nonnull
    public OffsetDateTime getTimeUpdated() {
        return this.updatedAt;
    }

    @Nonnull
    public TemplateGuild getGuild() {
        return this.guild;
    }

    public boolean isSynced() {
        return this.synced;
    }

    @Nonnull
    public TemplateManager getManager() {
        this.checkInteraction();
        if (this.manager == null) {
            this.manager = new TemplateManagerImpl(this);
            return this.manager;
        }
        return this.manager;
    }

    private void checkInteraction() {
        Guild guild = this.api.getGuildById(this.guild.getIdLong());
        if (guild == null) {
            throw new IllegalStateException("Cannot interact with a template without shared guild");
        }
        if (!guild.getSelfMember().hasPermission(Permission.MANAGE_SERVER)) {
            throw new InsufficientPermissionException(guild, Permission.MANAGE_SERVER);
        }
    }

    @Nonnull
    public JDA getJDA() {
        return this.api;
    }

    public int hashCode() {
        return this.code.hashCode();
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Template)) {
            return false;
        }
        Template impl = (Template)obj;
        return impl.code.equals(this.code);
    }

    public String toString() {
        return "Template(" + this.code + ")";
    }
}

