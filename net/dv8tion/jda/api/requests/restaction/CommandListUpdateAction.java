/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.requests.restaction;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.BooleanSupplier;
import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.dv8tion.jda.api.interactions.commands.Command;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.requests.RestAction;
import net.dv8tion.jda.internal.utils.Checks;

public interface CommandListUpdateAction
extends RestAction<List<Command>> {
    @Nonnull
    public CommandListUpdateAction timeout(long var1, @Nonnull TimeUnit var3);

    @Nonnull
    public CommandListUpdateAction deadline(long var1);

    @Nonnull
    public CommandListUpdateAction setCheck(@Nullable BooleanSupplier var1);

    @Nonnull
    public CommandListUpdateAction addCheck(@Nonnull BooleanSupplier var1);

    @Nonnull
    @CheckReturnValue
    public CommandListUpdateAction addCommands(@Nonnull Collection<? extends CommandData> var1);

    @Nonnull
    @CheckReturnValue
    default public CommandListUpdateAction addCommands(CommandData ... commands) {
        Checks.noneNull(commands, "Command");
        return this.addCommands(Arrays.asList(commands));
    }
}

