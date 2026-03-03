/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.internal.entities;

import gnu.trove.map.TLongObjectMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.dv8tion.jda.api.Region;
import net.dv8tion.jda.api.entities.Category;
import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.PermissionOverride;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.requests.restaction.ChannelAction;
import net.dv8tion.jda.api.utils.MiscUtil;
import net.dv8tion.jda.internal.entities.AbstractChannelImpl;
import net.dv8tion.jda.internal.entities.GuildImpl;
import net.dv8tion.jda.internal.utils.Checks;

public class VoiceChannelImpl
extends AbstractChannelImpl<VoiceChannel, VoiceChannelImpl>
implements VoiceChannel {
    private final TLongObjectMap<Member> connectedMembers = MiscUtil.newLongMap();
    private int userLimit;
    private int bitrate;
    private String region;

    public VoiceChannelImpl(long id2, GuildImpl guild) {
        super(id2, guild);
    }

    @Override
    public VoiceChannelImpl setPosition(int rawPosition) {
        this.getGuild().getVoiceChannelsView().clearCachedLists();
        return (VoiceChannelImpl)super.setPosition(rawPosition);
    }

    @Override
    public int getUserLimit() {
        return this.userLimit;
    }

    @Override
    public int getBitrate() {
        return this.bitrate;
    }

    @Override
    @Nonnull
    public ChannelType getType() {
        return ChannelType.VOICE;
    }

    @Override
    @Nonnull
    public Region getRegion() {
        return this.region == null ? Region.AUTOMATIC : Region.fromKey(this.region);
    }

    @Override
    @Nullable
    public String getRegionRaw() {
        return this.region;
    }

    @Override
    @Nonnull
    public List<Member> getMembers() {
        return Collections.unmodifiableList(new ArrayList<Member>(this.getConnectedMembersMap().valueCollection()));
    }

    @Override
    public int getPosition() {
        List<VoiceChannel> channels = this.getGuild().getVoiceChannels();
        for (int i2 = 0; i2 < channels.size(); ++i2) {
            if (!this.equals(channels.get(i2))) continue;
            return i2;
        }
        throw new IllegalStateException("Somehow when determining position we never found the VoiceChannel in the Guild's channels? wtf?");
    }

    @Override
    @Nonnull
    public ChannelAction<VoiceChannel> createCopy(@Nonnull Guild guild) {
        Checks.notNull(guild, "Guild");
        ChannelAction<VoiceChannel> action = guild.createVoiceChannel(this.name).setBitrate(this.bitrate).setUserlimit(this.userLimit);
        if (guild.equals(this.getGuild())) {
            Category parent = this.getParent();
            if (parent != null) {
                action.setParent(parent);
            }
            for (PermissionOverride o2 : this.overrides.valueCollection()) {
                if (o2.isMemberOverride()) {
                    action.addMemberPermissionOverride(o2.getIdLong(), o2.getAllowedRaw(), o2.getDeniedRaw());
                    continue;
                }
                action.addRolePermissionOverride(o2.getIdLong(), o2.getAllowedRaw(), o2.getDeniedRaw());
            }
        }
        return action;
    }

    @Override
    public boolean equals(Object o2) {
        if (!(o2 instanceof VoiceChannel)) {
            return false;
        }
        VoiceChannel oVChannel = (VoiceChannel)o2;
        return this == oVChannel || this.getIdLong() == oVChannel.getIdLong();
    }

    public String toString() {
        return "VC:" + this.getName() + '(' + this.id + ')';
    }

    public VoiceChannelImpl setUserLimit(int userLimit) {
        this.userLimit = userLimit;
        return this;
    }

    public VoiceChannelImpl setBitrate(int bitrate) {
        this.bitrate = bitrate;
        return this;
    }

    public VoiceChannelImpl setRegion(String region) {
        this.region = region;
        return this;
    }

    public TLongObjectMap<Member> getConnectedMembersMap() {
        this.connectedMembers.transformValues(member -> {
            Member real = this.getGuild().getMemberById(member.getIdLong());
            return real != null ? real : member;
        });
        return this.connectedMembers;
    }
}

