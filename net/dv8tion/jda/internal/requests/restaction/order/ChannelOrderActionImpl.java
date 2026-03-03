/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.internal.requests.restaction.order;

import java.util.Collection;
import java.util.stream.Collectors;
import javax.annotation.Nonnull;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.GuildChannel;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.exceptions.InsufficientPermissionException;
import net.dv8tion.jda.api.requests.restaction.order.ChannelOrderAction;
import net.dv8tion.jda.api.utils.data.DataArray;
import net.dv8tion.jda.api.utils.data.DataObject;
import net.dv8tion.jda.internal.requests.Route;
import net.dv8tion.jda.internal.requests.restaction.order.OrderActionImpl;
import net.dv8tion.jda.internal.utils.Checks;
import okhttp3.RequestBody;

public class ChannelOrderActionImpl
extends OrderActionImpl<GuildChannel, ChannelOrderAction>
implements ChannelOrderAction {
    protected final Guild guild;
    protected final int bucket;

    public ChannelOrderActionImpl(Guild guild, int bucket) {
        this(guild, bucket, ChannelOrderActionImpl.getChannelsOfType(guild, bucket));
    }

    public ChannelOrderActionImpl(Guild guild, int bucket, Collection<? extends GuildChannel> channels) {
        super(guild.getJDA(), Route.Guilds.MODIFY_CHANNELS.compile(guild.getId()));
        Checks.notNull(channels, "Channels to order");
        Checks.notEmpty(channels, "Channels to order");
        Checks.check(channels.stream().allMatch(c2 -> guild.equals(c2.getGuild())), "One or more channels are not from the correct guild");
        Checks.check(channels.stream().allMatch(c2 -> c2.getType().getSortBucket() == bucket), "One or more channels did not match the expected bucket " + bucket);
        this.guild = guild;
        this.bucket = bucket;
        this.orderList.addAll(channels);
    }

    @Override
    @Nonnull
    public Guild getGuild() {
        return this.guild;
    }

    @Override
    public int getSortBucket() {
        return this.bucket;
    }

    @Override
    protected RequestBody finalizeData() {
        Member self = this.guild.getSelfMember();
        if (!self.hasPermission(Permission.MANAGE_CHANNEL)) {
            throw new InsufficientPermissionException(this.guild, Permission.MANAGE_CHANNEL);
        }
        DataArray array = DataArray.empty();
        for (int i2 = 0; i2 < this.orderList.size(); ++i2) {
            GuildChannel chan = (GuildChannel)this.orderList.get(i2);
            array.add(DataObject.empty().put("id", chan.getId()).put("position", i2));
        }
        return this.getRequestBody(array);
    }

    @Override
    protected void validateInput(GuildChannel entity) {
        Checks.check(entity.getGuild().equals(this.guild), "Provided channel is not from this Guild!");
        Checks.check(this.orderList.contains(entity), "Provided channel is not in the list of orderable channels!");
    }

    protected static Collection<GuildChannel> getChannelsOfType(Guild guild, int bucket) {
        return guild.getChannels().stream().filter(it2 -> it2.getType().getSortBucket() == bucket).sorted().collect(Collectors.toList());
    }
}

