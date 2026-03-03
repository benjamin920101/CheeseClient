/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.internal.requests.restaction;

import java.util.concurrent.TimeUnit;
import java.util.function.BooleanSupplier;
import javax.annotation.Nonnull;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.interactions.commands.Command;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.dv8tion.jda.api.interactions.commands.build.SubcommandData;
import net.dv8tion.jda.api.interactions.commands.build.SubcommandGroupData;
import net.dv8tion.jda.api.requests.Request;
import net.dv8tion.jda.api.requests.Response;
import net.dv8tion.jda.api.requests.restaction.CommandEditAction;
import net.dv8tion.jda.api.utils.data.DataObject;
import net.dv8tion.jda.internal.requests.RestActionImpl;
import net.dv8tion.jda.internal.requests.Route;
import net.dv8tion.jda.internal.utils.Checks;
import okhttp3.RequestBody;
import org.jetbrains.annotations.Nullable;

public class CommandEditActionImpl
extends RestActionImpl<Command>
implements CommandEditAction {
    private static final String UNDEFINED = "undefined";
    private static final int NAME_SET = 1;
    private static final int DESCRIPTION_SET = 2;
    private static final int OPTIONS_SET = 4;
    private final Guild guild;
    private int mask = 0;
    private CommandData data = new CommandData("undefined", "undefined");

    public CommandEditActionImpl(JDA api2, String id2) {
        super(api2, Route.Interactions.EDIT_COMMAND.compile(api2.getSelfUser().getApplicationId(), id2));
        this.guild = null;
    }

    public CommandEditActionImpl(Guild guild, String id2) {
        super(guild.getJDA(), Route.Interactions.EDIT_GUILD_COMMAND.compile(guild.getJDA().getSelfUser().getApplicationId(), guild.getId(), id2));
        this.guild = guild;
    }

    @Override
    @Nonnull
    public CommandEditAction setCheck(BooleanSupplier checks) {
        return (CommandEditAction)super.setCheck(checks);
    }

    @Override
    @Nonnull
    public CommandEditAction deadline(long timestamp) {
        return (CommandEditAction)super.deadline(timestamp);
    }

    @Override
    @Nonnull
    public CommandEditAction apply(@Nonnull CommandData commandData) {
        Checks.notNull(commandData, "Command Data");
        this.mask = 7;
        this.data = commandData;
        return this;
    }

    @Override
    @Nonnull
    public CommandEditAction setDefaultEnabled(boolean enabled) {
        this.data.setDefaultEnabled(enabled);
        return this;
    }

    @Override
    @Nonnull
    public CommandEditAction addCheck(@Nonnull BooleanSupplier checks) {
        return (CommandEditAction)super.addCheck(checks);
    }

    @Override
    @Nonnull
    public CommandEditAction timeout(long timeout, @Nonnull TimeUnit unit) {
        return (CommandEditAction)super.timeout(timeout, unit);
    }

    @Override
    @Nonnull
    public CommandEditAction setName(@Nullable String name) {
        if (name == null) {
            this.mask &= 0xFFFFFFFE;
            return this;
        }
        this.data.setName(name);
        this.mask |= 1;
        return this;
    }

    @Override
    @Nonnull
    public CommandEditAction setDescription(@Nullable String description) {
        if (description == null) {
            this.mask &= 0xFFFFFFFD;
            return this;
        }
        this.data.setDescription(description);
        this.mask |= 2;
        return this;
    }

    @Override
    @Nonnull
    public CommandEditAction clearOptions() {
        this.data = new CommandData(this.data.getName(), this.data.getDescription());
        this.mask &= 0xFFFFFFFB;
        return this;
    }

    @Override
    @Nonnull
    public CommandEditAction addOptions(OptionData ... options) {
        this.data.addOptions(options);
        this.mask |= 4;
        return this;
    }

    @Override
    @Nonnull
    public CommandEditAction addSubcommands(SubcommandData ... subcommands) {
        this.data.addSubcommands(subcommands);
        this.mask |= 4;
        return this;
    }

    @Override
    @Nonnull
    public CommandEditAction addSubcommandGroups(SubcommandGroupData ... groups) {
        this.data.addSubcommandGroups(groups);
        this.mask |= 4;
        return this;
    }

    private boolean isUnchanged(int flag) {
        return (this.mask & flag) != flag;
    }

    @Override
    protected RequestBody finalizeData() {
        DataObject json = this.data.toData();
        if (this.isUnchanged(1)) {
            json.remove("name");
        }
        if (this.isUnchanged(2)) {
            json.remove("description");
        }
        if (this.isUnchanged(4)) {
            json.remove("options");
        }
        this.mask = 0;
        this.data = new CommandData(UNDEFINED, UNDEFINED);
        return this.getRequestBody(json);
    }

    @Override
    protected void handleSuccess(Response response, Request<Command> request) {
        DataObject json = response.getObject();
        request.onSuccess(new Command(this.api, this.guild, json));
    }
}

