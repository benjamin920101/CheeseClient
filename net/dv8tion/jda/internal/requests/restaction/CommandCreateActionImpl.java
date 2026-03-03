/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.internal.requests.restaction;

import java.util.concurrent.TimeUnit;
import java.util.function.BooleanSupplier;
import javax.annotation.Nonnull;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.interactions.commands.Command;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.dv8tion.jda.api.interactions.commands.build.SubcommandData;
import net.dv8tion.jda.api.interactions.commands.build.SubcommandGroupData;
import net.dv8tion.jda.api.requests.Request;
import net.dv8tion.jda.api.requests.Response;
import net.dv8tion.jda.api.requests.restaction.CommandCreateAction;
import net.dv8tion.jda.api.utils.data.DataObject;
import net.dv8tion.jda.internal.JDAImpl;
import net.dv8tion.jda.internal.requests.RestActionImpl;
import net.dv8tion.jda.internal.requests.Route;
import net.dv8tion.jda.internal.utils.Checks;
import okhttp3.RequestBody;

public class CommandCreateActionImpl
extends RestActionImpl<Command>
implements CommandCreateAction {
    private final Guild guild;
    private CommandData data;

    public CommandCreateActionImpl(JDAImpl api2, CommandData command) {
        super(api2, Route.Interactions.CREATE_COMMAND.compile(api2.getSelfUser().getApplicationId()));
        this.guild = null;
        this.data = command;
    }

    public CommandCreateActionImpl(Guild guild, CommandData command) {
        super(guild.getJDA(), Route.Interactions.CREATE_GUILD_COMMAND.compile(guild.getJDA().getSelfUser().getApplicationId(), guild.getId()));
        this.guild = guild;
        this.data = command;
    }

    @Override
    @Nonnull
    public CommandCreateAction addCheck(@Nonnull BooleanSupplier checks) {
        return (CommandCreateAction)super.addCheck(checks);
    }

    @Override
    @Nonnull
    public CommandCreateAction setCheck(BooleanSupplier checks) {
        return (CommandCreateAction)super.setCheck(checks);
    }

    @Override
    @Nonnull
    public CommandCreateAction deadline(long timestamp) {
        return (CommandCreateAction)super.deadline(timestamp);
    }

    @Override
    @Nonnull
    public CommandCreateAction setDefaultEnabled(boolean enabled) {
        this.data.setDefaultEnabled(enabled);
        return this;
    }

    @Override
    @Nonnull
    public CommandCreateAction timeout(long timeout, @Nonnull TimeUnit unit) {
        return (CommandCreateAction)super.timeout(timeout, unit);
    }

    @Override
    @Nonnull
    public CommandCreateAction setName(@Nonnull String name) {
        Checks.notEmpty(name, "Name");
        Checks.notLonger(name, 32, "Name");
        Checks.matches(name, Checks.ALPHANUMERIC_WITH_DASH, "Name");
        this.data.setName(name);
        return this;
    }

    @Override
    @Nonnull
    public CommandCreateAction setDescription(@Nonnull String description) {
        Checks.notEmpty(description, "Description");
        Checks.notLonger(description, 100, "Description");
        this.data.setDescription(description);
        return this;
    }

    @Override
    @Nonnull
    public CommandCreateAction addOptions(OptionData ... options) {
        this.data.addOptions(options);
        return this;
    }

    @Override
    @Nonnull
    public CommandCreateAction addSubcommands(@Nonnull SubcommandData subcommand) {
        this.data.addSubcommands(subcommand);
        return this;
    }

    @Override
    @Nonnull
    public CommandCreateAction addSubcommandGroups(@Nonnull SubcommandGroupData group) {
        this.data.addSubcommandGroups(group);
        return this;
    }

    @Override
    public RequestBody finalizeData() {
        return this.getRequestBody(this.data.toData());
    }

    @Override
    protected void handleSuccess(Response response, Request<Command> request) {
        DataObject json = response.getObject();
        request.onSuccess(new Command(this.api, this.guild, json));
    }
}

