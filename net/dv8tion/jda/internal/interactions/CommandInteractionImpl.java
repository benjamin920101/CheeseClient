/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.internal.interactions;

import gnu.trove.map.TLongObjectMap;
import gnu.trove.map.hash.TLongObjectHashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.annotation.Nonnull;
import net.dv8tion.jda.api.entities.GuildChannel;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.interactions.commands.CommandInteraction;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.utils.data.DataArray;
import net.dv8tion.jda.api.utils.data.DataObject;
import net.dv8tion.jda.internal.JDAImpl;
import net.dv8tion.jda.internal.entities.EntityBuilder;
import net.dv8tion.jda.internal.entities.GuildImpl;
import net.dv8tion.jda.internal.entities.MemberImpl;
import net.dv8tion.jda.internal.entities.UserImpl;
import net.dv8tion.jda.internal.interactions.InteractionImpl;

public class CommandInteractionImpl
extends InteractionImpl
implements CommandInteraction {
    private final long commandId;
    private final List<OptionMapping> options = new ArrayList<OptionMapping>();
    private final TLongObjectMap<Object> resolved = new TLongObjectHashMap<Object>();
    private final String name;
    private String subcommand;
    private String group;

    public CommandInteractionImpl(JDAImpl jda, DataObject data) {
        super(jda, data);
        DataObject commandData = data.getObject("data");
        this.commandId = commandData.getUnsignedLong("id");
        this.name = commandData.getString("name");
        DataArray options = commandData.optArray("options").orElseGet(DataArray::empty);
        DataObject resolveJson = commandData.optObject("resolved").orElseGet(DataObject::empty);
        if (options.length() == 1) {
            DataObject option = options.getObject(0);
            switch (OptionType.fromKey(option.getInt("type"))) {
                case SUB_COMMAND_GROUP: {
                    this.group = option.getString("name");
                    options = option.getArray("options");
                    option = options.getObject(0);
                }
                case SUB_COMMAND: {
                    this.subcommand = option.getString("name");
                    options = option.optArray("options").orElseGet(DataArray::empty);
                }
            }
        }
        this.parseResolved(jda, resolveJson);
        this.parseOptions(options);
    }

    private void parseOptions(DataArray options) {
        options.stream(DataArray::getObject).map(json -> new OptionMapping((DataObject)json, this.resolved)).forEach(this.options::add);
    }

    private void parseResolved(JDAImpl jda, DataObject resolveJson) {
        EntityBuilder entityBuilder = jda.getEntityBuilder();
        resolveJson.optObject("users").ifPresent(users -> users.keys().forEach(userId -> {
            DataObject userJson = users.getObject((String)userId);
            UserImpl userArg = entityBuilder.createUser(userJson);
            this.resolved.put(userArg.getIdLong(), userArg);
        }));
        if (this.guild != null) {
            resolveJson.optObject("members").ifPresent(members -> members.keys().forEach(memberId -> {
                DataObject userJson = resolveJson.getObject("users").getObject((String)memberId);
                DataObject memberJson = members.getObject((String)memberId);
                memberJson.put("user", userJson);
                MemberImpl optionMember = entityBuilder.createMember((GuildImpl)this.guild, memberJson);
                entityBuilder.updateMemberCache(optionMember);
                this.resolved.put(optionMember.getIdLong(), optionMember);
            }));
            resolveJson.optObject("roles").ifPresent(roles -> roles.keys().stream().map(this.guild::getRoleById).filter(Objects::nonNull).forEach(role -> this.resolved.put(role.getIdLong(), role)));
            resolveJson.optObject("channels").ifPresent(channels -> channels.keys().forEach(id2 -> {
                GuildChannel channelObj = jda.getGuildChannelById((String)id2);
                if (channelObj != null) {
                    this.resolved.put(channelObj.getIdLong(), channelObj);
                }
            }));
        }
    }

    @Override
    @Nonnull
    public MessageChannel getChannel() {
        return (MessageChannel)super.getChannel();
    }

    @Override
    @Nonnull
    public String getName() {
        return this.name;
    }

    @Override
    public String getSubcommandName() {
        return this.subcommand;
    }

    @Override
    public String getSubcommandGroup() {
        return this.group;
    }

    @Override
    public long getCommandIdLong() {
        return this.commandId;
    }

    @Override
    @Nonnull
    public List<OptionMapping> getOptions() {
        return this.options;
    }
}

