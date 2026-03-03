/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.interactions.commands.build;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.Nonnull;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.BaseCommand;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.dv8tion.jda.api.interactions.commands.build.SubcommandData;
import net.dv8tion.jda.api.interactions.commands.build.SubcommandGroupData;
import net.dv8tion.jda.api.utils.data.DataArray;
import net.dv8tion.jda.api.utils.data.DataObject;
import net.dv8tion.jda.api.utils.data.SerializableData;
import net.dv8tion.jda.internal.utils.Checks;

public class CommandData
extends BaseCommand<CommandData>
implements SerializableData {
    private boolean allowSubcommands = true;
    private boolean allowGroups = true;
    private boolean allowOption = true;
    private boolean defaultPermissions = true;
    private boolean allowRequired = true;

    public CommandData(@Nonnull String name, @Nonnull String description) {
        super(name, description);
    }

    @Override
    @Nonnull
    public DataObject toData() {
        return super.toData().put("default_permission", this.defaultPermissions);
    }

    @Nonnull
    public List<SubcommandData> getSubcommands() {
        return this.options.stream(DataArray::getObject).filter(obj -> {
            OptionType type = OptionType.fromKey(obj.getInt("type"));
            return type == OptionType.SUB_COMMAND;
        }).map(SubcommandData::fromData).collect(Collectors.toList());
    }

    @Nonnull
    public List<SubcommandGroupData> getSubcommandGroups() {
        return this.options.stream(DataArray::getObject).filter(obj -> {
            OptionType type = OptionType.fromKey(obj.getInt("type"));
            return type == OptionType.SUB_COMMAND_GROUP;
        }).map(SubcommandGroupData::fromData).collect(Collectors.toList());
    }

    @Nonnull
    public CommandData setDefaultEnabled(boolean enabled) {
        this.defaultPermissions = enabled;
        return this;
    }

    @Nonnull
    public CommandData addOptions(OptionData ... options) {
        Checks.noneNull(options, "Option");
        Checks.check(options.length + this.options.length() <= 25, "Cannot have more than 25 options for a command!");
        Checks.check(this.allowOption, "You cannot mix options with subcommands/groups.");
        this.allowGroups = false;
        this.allowSubcommands = false;
        for (OptionData option : options) {
            Checks.check(option.getType() != OptionType.SUB_COMMAND, "Cannot add a subcommand with addOptions(...). Use addSubcommands(...) instead!");
            Checks.check(option.getType() != OptionType.SUB_COMMAND_GROUP, "Cannot add a subcommand group with addOptions(...). Use addSubcommandGroups(...) instead!");
            Checks.check(this.allowRequired || !option.isRequired(), "Cannot add required options after non-required options!");
            this.allowRequired = option.isRequired();
            this.options.add(option);
        }
        return this;
    }

    @Nonnull
    public CommandData addOptions(@Nonnull Collection<? extends OptionData> options) {
        Checks.noneNull(options, "Option");
        return this.addOptions(options.toArray(new OptionData[0]));
    }

    @Nonnull
    public CommandData addOption(@Nonnull OptionType type, @Nonnull String name, @Nonnull String description, boolean required) {
        return this.addOptions(new OptionData(type, name, description).setRequired(required));
    }

    @Nonnull
    public CommandData addOption(@Nonnull OptionType type, @Nonnull String name, @Nonnull String description) {
        return this.addOption(type, name, description, false);
    }

    @Nonnull
    public CommandData addSubcommands(SubcommandData ... subcommands) {
        Checks.noneNull(subcommands, "Subcommands");
        if (!this.allowSubcommands) {
            throw new IllegalArgumentException("You cannot mix options with subcommands/groups.");
        }
        this.allowGroups = false;
        this.allowOption = false;
        Checks.check(subcommands.length + this.options.length() <= 25, "Cannot have more than 25 subcommands for a command!");
        for (SubcommandData data : subcommands) {
            this.options.add(data);
        }
        return this;
    }

    @Nonnull
    public CommandData addSubcommands(@Nonnull Collection<? extends SubcommandData> subcommands) {
        Checks.noneNull(subcommands, "Subcommands");
        return this.addSubcommands(subcommands.toArray(new SubcommandData[0]));
    }

    @Nonnull
    public CommandData addSubcommandGroups(SubcommandGroupData ... groups) {
        Checks.noneNull(groups, "SubcommandGroups");
        if (!this.allowGroups) {
            throw new IllegalArgumentException("You cannot mix options with subcommands/groups.");
        }
        this.allowOption = false;
        this.allowSubcommands = false;
        Checks.check(groups.length + this.options.length() <= 25, "Cannot have more than 25 subcommand groups for a command!");
        for (SubcommandGroupData data : groups) {
            this.options.add(data);
        }
        return this;
    }

    @Nonnull
    public CommandData addSubcommandGroups(@Nonnull Collection<? extends SubcommandGroupData> groups) {
        Checks.noneNull(groups, "SubcommandGroups");
        return this.addSubcommandGroups(groups.toArray(new SubcommandGroupData[0]));
    }

    @Nonnull
    public static CommandData fromData(@Nonnull DataObject object) {
        Checks.notNull(object, "DataObject");
        String name = object.getString("name");
        String description = object.getString("description");
        DataArray options = object.optArray("options").orElseGet(DataArray::empty);
        CommandData command = new CommandData(name, description);
        options.stream(DataArray::getObject).forEach(opt -> {
            OptionType type = OptionType.fromKey(opt.getInt("type"));
            switch (type) {
                case SUB_COMMAND: {
                    command.addSubcommands(SubcommandData.fromData(opt));
                    break;
                }
                case SUB_COMMAND_GROUP: {
                    command.addSubcommandGroups(SubcommandGroupData.fromData(opt));
                    break;
                }
                default: {
                    command.addOptions(OptionData.fromData(opt));
                }
            }
        });
        return command;
    }

    @Nonnull
    public static List<CommandData> fromList(@Nonnull DataArray array) {
        Checks.notNull(array, "DataArray");
        return array.stream(DataArray::getObject).map(CommandData::fromData).collect(Collectors.toList());
    }

    @Nonnull
    public static List<CommandData> fromList(@Nonnull Collection<? extends DataObject> collection) {
        Checks.noneNull(collection, "CommandData");
        return CommandData.fromList(DataArray.fromCollection(collection));
    }
}

