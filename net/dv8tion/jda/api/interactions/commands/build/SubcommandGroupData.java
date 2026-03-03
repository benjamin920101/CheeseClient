/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.interactions.commands.build;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.Nonnull;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.SubcommandData;
import net.dv8tion.jda.api.utils.data.DataArray;
import net.dv8tion.jda.api.utils.data.DataObject;
import net.dv8tion.jda.api.utils.data.SerializableData;
import net.dv8tion.jda.internal.utils.Checks;

public class SubcommandGroupData
implements SerializableData {
    private final DataArray options = DataArray.empty();
    private String name;
    private String description;

    public SubcommandGroupData(@Nonnull String name, @Nonnull String description) {
        Checks.notEmpty(name, "Name");
        Checks.notEmpty(description, "Description");
        Checks.notLonger(name, 32, "Name");
        Checks.notLonger(description, 100, "Description");
        Checks.matches(name, Checks.ALPHANUMERIC_WITH_DASH, "Name");
        Checks.isLowercase(name, "Name");
        this.name = name;
        this.description = description;
    }

    @Nonnull
    public SubcommandGroupData setName(@Nonnull String name) {
        Checks.notEmpty(name, "Name");
        Checks.notLonger(name, 32, "Name");
        Checks.isLowercase(name, "Name");
        Checks.matches(name, Checks.ALPHANUMERIC_WITH_DASH, "Name");
        this.name = name;
        return this;
    }

    @Nonnull
    public SubcommandGroupData setDescription(@Nonnull String description) {
        Checks.notEmpty(description, "Description");
        Checks.notLonger(description, 100, "Description");
        this.description = description;
        return this;
    }

    @Nonnull
    public String getName() {
        return this.name;
    }

    @Nonnull
    public String getDescription() {
        return this.description;
    }

    @Nonnull
    public List<SubcommandData> getSubcommands() {
        return this.options.stream(DataArray::getObject).map(SubcommandData::fromData).collect(Collectors.toList());
    }

    @Nonnull
    public SubcommandGroupData addSubcommands(SubcommandData ... subcommands) {
        Checks.noneNull(subcommands, "Subcommand");
        Checks.check(subcommands.length + this.options.length() <= 25, "Cannot have more than 25 subcommands in one group!");
        for (SubcommandData subcommand : subcommands) {
            this.options.add(subcommand);
        }
        return this;
    }

    @Nonnull
    public SubcommandGroupData addSubcommands(@Nonnull Collection<? extends SubcommandData> subcommands) {
        Checks.noneNull(subcommands, "Subcommands");
        return this.addSubcommands(subcommands.toArray(new SubcommandData[0]));
    }

    @Override
    @Nonnull
    public DataObject toData() {
        return DataObject.empty().put("type", OptionType.SUB_COMMAND_GROUP.getKey()).put("name", this.name).put("description", this.description).put("options", this.options);
    }

    @Nonnull
    public static SubcommandGroupData fromData(@Nonnull DataObject json) {
        String name = json.getString("name");
        String description = json.getString("description");
        SubcommandGroupData group = new SubcommandGroupData(name, description);
        json.optArray("options").ifPresent(arr2 -> arr2.stream(DataArray::getObject).map(SubcommandData::fromData).forEach(xva$0 -> group.addSubcommands((SubcommandData)xva$0)));
        return group;
    }
}

