/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.requests.restaction;

import java.util.Collection;
import java.util.concurrent.TimeUnit;
import java.util.function.BooleanSupplier;
import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.dv8tion.jda.api.interactions.commands.Command;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.dv8tion.jda.api.interactions.commands.build.SubcommandData;
import net.dv8tion.jda.api.interactions.commands.build.SubcommandGroupData;
import net.dv8tion.jda.api.requests.RestAction;
import net.dv8tion.jda.internal.utils.Checks;

public interface CommandCreateAction
extends RestAction<Command> {
    @Nonnull
    public CommandCreateAction setCheck(@Nullable BooleanSupplier var1);

    @Nonnull
    public CommandCreateAction addCheck(@Nonnull BooleanSupplier var1);

    @Nonnull
    public CommandCreateAction timeout(long var1, @Nonnull TimeUnit var3);

    @Nonnull
    public CommandCreateAction deadline(long var1);

    @Nonnull
    @CheckReturnValue
    public CommandCreateAction setDefaultEnabled(boolean var1);

    @Nonnull
    @CheckReturnValue
    public CommandCreateAction setName(@Nonnull String var1);

    @Nonnull
    @CheckReturnValue
    public CommandCreateAction setDescription(@Nonnull String var1);

    @Nonnull
    @CheckReturnValue
    public CommandCreateAction addOptions(OptionData ... var1);

    @Nonnull
    @CheckReturnValue
    default public CommandCreateAction addOptions(@Nonnull Collection<? extends OptionData> options) {
        Checks.noneNull(options, "Option");
        return this.addOptions(options.toArray(new OptionData[0]));
    }

    @Nonnull
    @CheckReturnValue
    default public CommandCreateAction addOption(@Nonnull OptionType type, @Nonnull String name, @Nonnull String description, boolean required) {
        return this.addOptions(new OptionData(type, name, description).setRequired(required));
    }

    @Nonnull
    @CheckReturnValue
    default public CommandCreateAction addOption(@Nonnull OptionType type, @Nonnull String name, @Nonnull String description) {
        return this.addOption(type, name, description, false);
    }

    @Nonnull
    @CheckReturnValue
    public CommandCreateAction addSubcommands(@Nonnull SubcommandData var1);

    @Nonnull
    @CheckReturnValue
    public CommandCreateAction addSubcommandGroups(@Nonnull SubcommandGroupData var1);
}

