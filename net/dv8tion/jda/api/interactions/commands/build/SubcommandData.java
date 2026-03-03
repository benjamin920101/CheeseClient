/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.interactions.commands.build;

import java.util.Collection;
import javax.annotation.Nonnull;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.BaseCommand;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.dv8tion.jda.api.utils.data.DataArray;
import net.dv8tion.jda.api.utils.data.DataObject;
import net.dv8tion.jda.api.utils.data.SerializableData;
import net.dv8tion.jda.internal.utils.Checks;

public class SubcommandData
extends BaseCommand<CommandData>
implements SerializableData {
    private boolean allowRequired = true;

    public SubcommandData(@Nonnull String name, @Nonnull String description) {
        super(name, description);
    }

    @Nonnull
    public SubcommandData addOptions(OptionData ... options) {
        Checks.noneNull(options, "Option");
        Checks.check(options.length + this.options.length() <= 25, "Cannot have more than 25 options for a subcommand!");
        for (OptionData option : options) {
            Checks.check(option.getType() != OptionType.SUB_COMMAND, "Cannot add a subcommand to a subcommand!");
            Checks.check(option.getType() != OptionType.SUB_COMMAND_GROUP, "Cannot add a subcommand group to a subcommand!");
            Checks.check(this.allowRequired || !option.isRequired(), "Cannot add required options after non-required options!");
            this.allowRequired = option.isRequired();
            this.options.add(option);
        }
        return this;
    }

    @Nonnull
    public SubcommandData addOptions(@Nonnull Collection<? extends OptionData> options) {
        Checks.noneNull(options, "Options");
        return this.addOptions(options.toArray(new OptionData[0]));
    }

    @Nonnull
    public SubcommandData addOption(@Nonnull OptionType type, @Nonnull String name, @Nonnull String description, boolean required) {
        return this.addOptions(new OptionData(type, name, description).setRequired(required));
    }

    @Nonnull
    public SubcommandData addOption(@Nonnull OptionType type, @Nonnull String name, @Nonnull String description) {
        return this.addOption(type, name, description, false);
    }

    @Override
    @Nonnull
    public DataObject toData() {
        return super.toData().put("type", OptionType.SUB_COMMAND.getKey());
    }

    @Nonnull
    public static SubcommandData fromData(@Nonnull DataObject json) {
        String name = json.getString("name");
        String description = json.getString("description");
        SubcommandData sub = new SubcommandData(name, description);
        json.optArray("options").ifPresent(arr2 -> arr2.stream(DataArray::getObject).map(OptionData::fromData).forEach(xva$0 -> sub.addOptions((OptionData)xva$0)));
        return sub;
    }
}

