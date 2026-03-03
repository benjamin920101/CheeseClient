/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.interactions.commands;

import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.interactions.Interaction;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.internal.utils.Checks;

public interface CommandInteraction
extends Interaction {
    @Nonnull
    public String getName();

    @Nullable
    public String getSubcommandName();

    @Nullable
    public String getSubcommandGroup();

    @Nonnull
    default public String getCommandPath() {
        StringBuilder builder = new StringBuilder(this.getName());
        if (this.getSubcommandGroup() != null) {
            builder.append('/').append(this.getSubcommandGroup());
        }
        if (this.getSubcommandName() != null) {
            builder.append('/').append(this.getSubcommandName());
        }
        return builder.toString();
    }

    @Override
    @Nonnull
    public MessageChannel getChannel();

    public long getCommandIdLong();

    @Nonnull
    default public String getCommandId() {
        return Long.toUnsignedString(this.getCommandIdLong());
    }

    @Nonnull
    public List<OptionMapping> getOptions();

    @Nonnull
    default public List<OptionMapping> getOptionsByName(@Nonnull String name) {
        Checks.notNull(name, "Name");
        return this.getOptions().stream().filter(opt -> opt.getName().equals(name)).collect(Collectors.toList());
    }

    @Nonnull
    default public List<OptionMapping> getOptionsByType(@Nonnull OptionType type) {
        Checks.notNull((Object)type, "Type");
        return this.getOptions().stream().filter(it2 -> it2.getType() == type).collect(Collectors.toList());
    }

    @Nullable
    default public OptionMapping getOption(@Nonnull String name) {
        List<OptionMapping> options = this.getOptionsByName(name);
        return options.isEmpty() ? null : options.get(0);
    }
}

