/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.interactions.commands.build;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.annotation.Nonnull;
import net.dv8tion.jda.api.interactions.commands.Command;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.utils.data.DataArray;
import net.dv8tion.jda.api.utils.data.DataObject;
import net.dv8tion.jda.api.utils.data.SerializableData;
import net.dv8tion.jda.internal.utils.Checks;

public class OptionData
implements SerializableData {
    private final OptionType type;
    private String name;
    private String description;
    private boolean isRequired;
    private Map<String, Object> choices;

    public OptionData(@Nonnull OptionType type, @Nonnull String name, @Nonnull String description) {
        this(type, name, description, false);
    }

    public OptionData(@Nonnull OptionType type, @Nonnull String name, @Nonnull String description, boolean isRequired) {
        Checks.notNull((Object)type, "Type");
        Checks.notEmpty(name, "Name");
        Checks.notEmpty(description, "Description");
        Checks.notLonger(name, 32, "Name");
        Checks.notLonger(description, 100, "Description");
        Checks.matches(name, Checks.ALPHANUMERIC_WITH_DASH, "Name");
        Checks.isLowercase(name, "Name");
        this.type = type;
        this.name = name;
        this.description = description;
        this.isRequired = isRequired;
        if (type.canSupportChoices()) {
            this.choices = new LinkedHashMap<String, Object>();
        }
    }

    @Nonnull
    public OptionType getType() {
        return this.type;
    }

    @Nonnull
    public String getName() {
        return this.name;
    }

    @Nonnull
    public String getDescription() {
        return this.description;
    }

    public boolean isRequired() {
        return this.isRequired;
    }

    @Nonnull
    public List<Command.Choice> getChoices() {
        if (this.choices == null || this.choices.isEmpty()) {
            return Collections.emptyList();
        }
        return this.choices.entrySet().stream().map(entry -> {
            if (entry.getValue() instanceof String) {
                return new Command.Choice((String)entry.getKey(), entry.getValue().toString());
            }
            return new Command.Choice((String)entry.getKey(), ((Number)entry.getValue()).longValue());
        }).collect(Collectors.toList());
    }

    @Nonnull
    public OptionData setName(@Nonnull String name) {
        Checks.notEmpty(name, "Name");
        Checks.notLonger(name, 32, "Name");
        Checks.isLowercase(name, "Name");
        Checks.matches(name, Checks.ALPHANUMERIC_WITH_DASH, "Name");
        this.name = name;
        return this;
    }

    @Nonnull
    public OptionData setDescription(@Nonnull String description) {
        Checks.notEmpty(description, "Description");
        Checks.notLonger(description, 100, "Description");
        this.description = description;
        return this;
    }

    @Nonnull
    public OptionData setRequired(boolean required) {
        this.isRequired = required;
        return this;
    }

    @Nonnull
    public OptionData addChoice(@Nonnull String name, int value) {
        Checks.notEmpty(name, "Name");
        Checks.notLonger(name, 100, "Name");
        Checks.check(this.choices.size() < 25, "Cannot have more than 25 choices for an option!");
        if (this.type != OptionType.INTEGER) {
            throw new IllegalArgumentException("Cannot add int choice for OptionType." + (Object)((Object)this.type));
        }
        this.choices.put(name, value);
        return this;
    }

    @Nonnull
    public OptionData addChoice(@Nonnull String name, @Nonnull String value) {
        Checks.notEmpty(name, "Name");
        Checks.notEmpty(value, "Value");
        Checks.notLonger(name, 100, "Name");
        Checks.notLonger(value, 100, "Value");
        Checks.check(this.choices.size() < 25, "Cannot have more than 25 choices for an option!");
        if (this.type != OptionType.STRING) {
            throw new IllegalArgumentException("Cannot add string choice for OptionType." + (Object)((Object)this.type));
        }
        this.choices.put(name, value);
        return this;
    }

    @Nonnull
    public OptionData addChoices(Command.Choice ... choices) {
        if (this.choices == null) {
            throw new IllegalStateException("Cannot add choices for an option of type " + (Object)((Object)this.type));
        }
        Checks.noneNull(choices, "Choices");
        Checks.check(choices.length + this.choices.size() <= 25, "Cannot have more than 25 choices for one option!");
        for (Command.Choice choice : choices) {
            if (this.type == OptionType.INTEGER) {
                this.addChoice(choice.getName(), (int)choice.getAsLong());
                continue;
            }
            if (this.type == OptionType.STRING) {
                this.addChoice(choice.getName(), choice.getAsString());
                continue;
            }
            throw new IllegalArgumentException("Cannot add choice for type " + (Object)((Object)this.type));
        }
        return this;
    }

    @Nonnull
    public OptionData addChoices(@Nonnull Collection<? extends Command.Choice> choices) {
        Checks.noneNull(choices, "Choices");
        return this.addChoices(choices.toArray(new Command.Choice[0]));
    }

    @Override
    @Nonnull
    public DataObject toData() {
        DataObject json = DataObject.empty().put("type", this.type.getKey()).put("name", this.name).put("description", this.description);
        if (this.type != OptionType.SUB_COMMAND && this.type != OptionType.SUB_COMMAND_GROUP) {
            json.put("required", this.isRequired);
        }
        if (this.choices != null && !this.choices.isEmpty()) {
            json.put("choices", DataArray.fromCollection(this.choices.entrySet().stream().map(entry -> DataObject.empty().put("name", entry.getKey()).put("value", entry.getValue())).collect(Collectors.toList())));
        }
        return json;
    }

    @Nonnull
    public static OptionData fromData(@Nonnull DataObject json) {
        String name = json.getString("name");
        String description = json.getString("description");
        OptionType type = OptionType.fromKey(json.getInt("type"));
        OptionData option = new OptionData(type, name, description);
        option.setRequired(json.getBoolean("required"));
        json.optArray("choices").ifPresent(choices1 -> choices1.stream(DataArray::getObject).forEach(o2 -> {
            Object value = o2.get("value");
            if (value instanceof Number) {
                option.addChoice(o2.getString("name"), ((Number)value).intValue());
            } else {
                option.addChoice(o2.getString("name"), value.toString());
            }
        }));
        return option;
    }
}

