/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.interactions.commands;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnull;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.ISnowflake;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.privileges.CommandPrivilege;
import net.dv8tion.jda.api.requests.RestAction;
import net.dv8tion.jda.api.requests.restaction.CommandEditAction;
import net.dv8tion.jda.api.utils.data.DataArray;
import net.dv8tion.jda.api.utils.data.DataObject;
import net.dv8tion.jda.api.utils.data.DataType;
import net.dv8tion.jda.internal.JDAImpl;
import net.dv8tion.jda.internal.requests.RestActionImpl;
import net.dv8tion.jda.internal.requests.Route;
import net.dv8tion.jda.internal.requests.restaction.CommandEditActionImpl;
import net.dv8tion.jda.internal.utils.Checks;

public class Command
implements ISnowflake {
    private static final EnumSet<OptionType> OPTIONS = EnumSet.complementOf(EnumSet.of(OptionType.SUB_COMMAND, OptionType.SUB_COMMAND_GROUP));
    private static final Predicate<DataObject> OPTION_TEST = it2 -> OPTIONS.contains((Object)OptionType.fromKey(it2.getInt("type")));
    private static final Predicate<DataObject> SUBCOMMAND_TEST = it2 -> OptionType.fromKey(it2.getInt("type")) == OptionType.SUB_COMMAND;
    private static final Predicate<DataObject> GROUP_TEST = it2 -> OptionType.fromKey(it2.getInt("type")) == OptionType.SUB_COMMAND_GROUP;
    private final JDAImpl api;
    private final Guild guild;
    private final String name;
    private final String description;
    private final List<Option> options;
    private final List<SubcommandGroup> groups;
    private final List<Subcommand> subcommands;
    private final long id;
    private final long guildId;
    private final long applicationId;
    private final boolean defaultEnabled;

    public Command(JDAImpl api2, Guild guild, DataObject json) {
        this.api = api2;
        this.guild = guild;
        this.name = json.getString("name");
        this.description = json.getString("description");
        this.id = json.getUnsignedLong("id");
        this.defaultEnabled = json.getBoolean("default_permission");
        this.guildId = guild != null ? guild.getIdLong() : 0L;
        this.applicationId = json.getUnsignedLong("application_id", api2.getSelfUser().getApplicationIdLong());
        this.options = Command.parseOptions(json, OPTION_TEST, Option::new);
        this.groups = Command.parseOptions(json, GROUP_TEST, SubcommandGroup::new);
        this.subcommands = Command.parseOptions(json, SUBCOMMAND_TEST, Subcommand::new);
    }

    protected static <T> List<T> parseOptions(DataObject json, Predicate<DataObject> test, Function<DataObject, T> transform) {
        return json.optArray("options").map(arr2 -> arr2.stream(DataArray::getObject).filter(test).map(transform).collect(Collectors.toList())).orElse(Collections.emptyList());
    }

    @Nonnull
    @CheckReturnValue
    public RestAction<Void> delete() {
        if (this.applicationId != this.api.getSelfUser().getApplicationIdLong()) {
            throw new IllegalStateException("Cannot delete a command from another bot!");
        }
        String appId = this.getJDA().getSelfUser().getApplicationId();
        Route.CompiledRoute route = this.guildId != 0L ? Route.Interactions.DELETE_GUILD_COMMAND.compile(appId, Long.toUnsignedString(this.guildId), this.getId()) : Route.Interactions.DELETE_COMMAND.compile(appId, this.getId());
        return new RestActionImpl<Void>(this.api, route);
    }

    @Nonnull
    @CheckReturnValue
    public CommandEditAction editCommand() {
        if (this.applicationId != this.api.getSelfUser().getApplicationIdLong()) {
            throw new IllegalStateException("Cannot edit a command from another bot!");
        }
        return this.guild == null ? new CommandEditActionImpl((JDA)this.api, this.getId()) : new CommandEditActionImpl(this.guild, this.getId());
    }

    @Nonnull
    @CheckReturnValue
    public RestAction<List<CommandPrivilege>> retrievePrivileges(@Nonnull Guild guild) {
        Checks.notNull(guild, "Guild");
        return guild.retrieveCommandPrivilegesById(this.id);
    }

    @Nonnull
    @CheckReturnValue
    public RestAction<List<CommandPrivilege>> updatePrivileges(@Nonnull Guild guild, @Nonnull Collection<? extends CommandPrivilege> privileges) {
        if (this.applicationId != this.api.getSelfUser().getApplicationIdLong()) {
            throw new IllegalStateException("Cannot update privileges for a command from another bot!");
        }
        Checks.notNull(guild, "Guild");
        return guild.updateCommandPrivilegesById(this.id, privileges);
    }

    @Nonnull
    @CheckReturnValue
    public RestAction<List<CommandPrivilege>> updatePrivileges(@Nonnull Guild guild, CommandPrivilege ... privileges) {
        Checks.noneNull(privileges, "CommandPrivileges");
        return this.updatePrivileges(guild, Arrays.asList(privileges));
    }

    @Nonnull
    public JDA getJDA() {
        return this.api;
    }

    @Nonnull
    public String getName() {
        return this.name;
    }

    @Nonnull
    public String getDescription() {
        return this.description;
    }

    public boolean isDefaultEnabled() {
        return this.defaultEnabled;
    }

    @Nonnull
    public List<Option> getOptions() {
        return this.options;
    }

    @Nonnull
    public List<Subcommand> getSubcommands() {
        return this.subcommands;
    }

    @Nonnull
    public List<SubcommandGroup> getSubcommandGroups() {
        return this.groups;
    }

    public long getApplicationIdLong() {
        return this.applicationId;
    }

    @Nonnull
    public String getApplicationId() {
        return Long.toUnsignedString(this.applicationId);
    }

    @Override
    public long getIdLong() {
        return this.id;
    }

    public String toString() {
        return "C:" + this.getName() + "(" + this.getId() + ")";
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Command)) {
            return false;
        }
        return this.id == ((Command)obj).id;
    }

    public int hashCode() {
        return Long.hashCode(this.id);
    }

    public static class SubcommandGroup {
        private final String name;
        private final String description;
        private final List<Subcommand> subcommands;

        public SubcommandGroup(DataObject json) {
            this.name = json.getString("name");
            this.description = json.getString("description");
            this.subcommands = Command.parseOptions(json, SUBCOMMAND_TEST, Subcommand::new);
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
        public List<Subcommand> getSubcommands() {
            return this.subcommands;
        }

        public int hashCode() {
            return Objects.hash(this.name, this.description, this.subcommands);
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof SubcommandGroup)) {
                return false;
            }
            SubcommandGroup other = (SubcommandGroup)obj;
            return Objects.equals(other.name, this.name) && Objects.equals(other.description, this.description) && Objects.equals(other.subcommands, this.subcommands);
        }

        public String toString() {
            return "SubcommandGroup(" + this.name + ")";
        }
    }

    public static class Subcommand {
        private final String name;
        private final String description;
        private final List<Option> options;

        public Subcommand(DataObject json) {
            this.name = json.getString("name");
            this.description = json.getString("description");
            this.options = Command.parseOptions(json, OPTION_TEST, Option::new);
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
        public List<Option> getOptions() {
            return this.options;
        }

        public int hashCode() {
            return Objects.hash(this.name, this.description, this.options);
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof Subcommand)) {
                return false;
            }
            Subcommand other = (Subcommand)obj;
            return Objects.equals(other.name, this.name) && Objects.equals(other.description, this.description) && Objects.equals(other.options, this.options);
        }

        public String toString() {
            return "Subcommand(" + this.name + ")";
        }
    }

    public static class Option {
        private final String name;
        private final String description;
        private final int type;
        private final List<Choice> choices;

        public Option(@Nonnull DataObject json) {
            this.name = json.getString("name");
            this.description = json.getString("description");
            this.type = json.getInt("type");
            this.choices = json.optArray("choices").map(it2 -> it2.stream(DataArray::getObject).map(Choice::new).collect(Collectors.toList())).orElse(Collections.emptyList());
        }

        @Nonnull
        public String getName() {
            return this.name;
        }

        @Nonnull
        public String getDescription() {
            return this.description;
        }

        public int getTypeRaw() {
            return this.type;
        }

        @Nonnull
        public OptionType getType() {
            return OptionType.fromKey(this.type);
        }

        @Nonnull
        public List<Choice> getChoices() {
            return this.choices;
        }

        public int hashCode() {
            return Objects.hash(this.name, this.description, this.type, this.choices);
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof Option)) {
                return false;
            }
            Option other = (Option)obj;
            return Objects.equals(other.name, this.name) && Objects.equals(other.description, this.description) && Objects.equals(other.choices, this.choices) && other.type == this.type;
        }

        public String toString() {
            return "Option[" + (Object)((Object)this.getType()) + "](" + this.name + ")";
        }
    }

    public static class Choice {
        private final String name;
        private final long intValue;
        private final String stringValue;

        public Choice(@Nonnull String name, long value) {
            this.name = name;
            this.intValue = value;
            this.stringValue = Long.toString(value);
        }

        public Choice(@Nonnull String name, @Nonnull String value) {
            this.name = name;
            this.intValue = 0L;
            this.stringValue = value;
        }

        public Choice(@Nonnull DataObject json) {
            Checks.notNull(json, "DataObject");
            this.name = json.getString("name");
            if (json.isType("value", DataType.INT)) {
                this.intValue = json.getLong("value");
                this.stringValue = Long.toString(this.intValue);
            } else {
                this.intValue = 0L;
                this.stringValue = json.getString("value");
            }
        }

        @Nonnull
        public String getName() {
            return this.name;
        }

        public long getAsLong() {
            return this.intValue;
        }

        @Nonnull
        public String getAsString() {
            return this.stringValue;
        }

        public int hashCode() {
            return Objects.hash(this.name, this.stringValue);
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof Choice)) {
                return false;
            }
            Choice other = (Choice)obj;
            return Objects.equals(other.name, this.name) && Objects.equals(other.stringValue, this.stringValue);
        }

        public String toString() {
            return "Choice(" + this.name + "," + this.stringValue + ")";
        }
    }
}

