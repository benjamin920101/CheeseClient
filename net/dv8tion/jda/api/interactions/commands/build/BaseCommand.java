/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.interactions.commands.build;

import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.Nonnull;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.dv8tion.jda.api.utils.data.DataArray;
import net.dv8tion.jda.api.utils.data.DataObject;
import net.dv8tion.jda.api.utils.data.SerializableData;
import net.dv8tion.jda.internal.utils.Checks;

public abstract class BaseCommand<T extends BaseCommand<T>>
implements SerializableData {
    protected final DataArray options = DataArray.empty();
    protected String name;
    protected String description;

    public BaseCommand(@Nonnull String name, @Nonnull String description) {
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
    public T setName(@Nonnull String name) {
        Checks.notEmpty(name, "Name");
        Checks.notLonger(name, 32, "Name");
        Checks.isLowercase(name, "Name");
        Checks.matches(name, Checks.ALPHANUMERIC_WITH_DASH, "Name");
        this.name = name;
        return (T)this;
    }

    @Nonnull
    public T setDescription(@Nonnull String description) {
        Checks.notEmpty(description, "Description");
        Checks.notLonger(description, 100, "Description");
        this.description = description;
        return (T)this;
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
    public List<OptionData> getOptions() {
        return this.options.stream(DataArray::getObject).map(OptionData::fromData).filter(it2 -> it2.getType().getKey() > OptionType.SUB_COMMAND_GROUP.getKey()).collect(Collectors.toList());
    }

    @Override
    @Nonnull
    public DataObject toData() {
        return DataObject.empty().put("name", this.name).put("description", this.description).put("options", this.options);
    }
}

