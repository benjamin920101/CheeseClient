/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.internal.requests.restaction;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.BooleanSupplier;
import java.util.stream.Collectors;
import javax.annotation.Nonnull;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.interactions.commands.Command;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.requests.Request;
import net.dv8tion.jda.api.requests.Response;
import net.dv8tion.jda.api.requests.restaction.CommandListUpdateAction;
import net.dv8tion.jda.api.utils.data.DataArray;
import net.dv8tion.jda.api.utils.data.DataObject;
import net.dv8tion.jda.internal.entities.GuildImpl;
import net.dv8tion.jda.internal.requests.RestActionImpl;
import net.dv8tion.jda.internal.requests.Route;
import net.dv8tion.jda.internal.utils.Checks;
import okhttp3.RequestBody;

public class CommandListUpdateActionImpl
extends RestActionImpl<List<Command>>
implements CommandListUpdateAction {
    private final List<CommandData> commands = new ArrayList<CommandData>();
    private final GuildImpl guild;

    public CommandListUpdateActionImpl(JDA api2, GuildImpl guild, Route.CompiledRoute route) {
        super(api2, route);
        this.guild = guild;
    }

    @Override
    @Nonnull
    public CommandListUpdateAction timeout(long timeout, @Nonnull TimeUnit unit) {
        return (CommandListUpdateAction)super.timeout(timeout, unit);
    }

    @Override
    @Nonnull
    public CommandListUpdateAction addCheck(@Nonnull BooleanSupplier checks) {
        return (CommandListUpdateAction)super.addCheck(checks);
    }

    @Override
    @Nonnull
    public CommandListUpdateAction setCheck(BooleanSupplier checks) {
        return (CommandListUpdateAction)super.setCheck(checks);
    }

    @Override
    @Nonnull
    public CommandListUpdateAction deadline(long timestamp) {
        return (CommandListUpdateAction)super.deadline(timestamp);
    }

    @Override
    @Nonnull
    public CommandListUpdateAction addCommands(@Nonnull Collection<? extends CommandData> commands) {
        Checks.noneNull(commands, "Command");
        Checks.check(this.commands.size() + commands.size() <= 100, "Cannot have more than 100 commands! Try using subcommands instead.");
        this.commands.addAll(commands);
        return this;
    }

    @Override
    protected RequestBody finalizeData() {
        DataArray json = DataArray.empty();
        json.addAll(this.commands);
        return this.getRequestBody(json);
    }

    @Override
    protected void handleSuccess(Response response, Request<List<Command>> request) {
        List commands = response.getArray().stream(DataArray::getObject).map((? super T obj) -> new Command(this.api, this.guild, (DataObject)obj)).collect(Collectors.toList());
        request.onSuccess(commands);
    }
}

