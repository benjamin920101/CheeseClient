/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.internal.interactions;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.dv8tion.jda.api.entities.AbstractChannel;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.PrivateChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.interactions.Interaction;
import net.dv8tion.jda.api.interactions.InteractionHook;
import net.dv8tion.jda.api.utils.data.DataObject;
import net.dv8tion.jda.internal.JDAImpl;
import net.dv8tion.jda.internal.entities.GuildImpl;
import net.dv8tion.jda.internal.entities.MemberImpl;
import net.dv8tion.jda.internal.interactions.InteractionHookImpl;
import net.dv8tion.jda.internal.requests.restaction.interactions.ReplyActionImpl;

public class InteractionImpl
implements Interaction {
    protected final InteractionHookImpl hook;
    protected final long id;
    protected final int type;
    protected final String token;
    protected final Guild guild;
    protected final Member member;
    protected final User user;
    protected final AbstractChannel channel;
    protected final JDAImpl api;

    public InteractionImpl(JDAImpl jda, DataObject data) {
        this.api = jda;
        this.id = data.getUnsignedLong("id");
        this.token = data.getString("token");
        this.type = data.getInt("type");
        this.guild = jda.getGuildById(data.getUnsignedLong("guild_id", 0L));
        this.hook = new InteractionHookImpl(this, jda);
        if (this.guild != null) {
            this.member = jda.getEntityBuilder().createMember((GuildImpl)this.guild, data.getObject("member"));
            jda.getEntityBuilder().updateMemberCache((MemberImpl)this.member);
            this.user = this.member.getUser();
            this.channel = this.guild.getGuildChannelById(data.getUnsignedLong("channel_id"));
        } else {
            this.member = null;
            long channelId = data.getUnsignedLong("channel_id");
            PrivateChannel channel = jda.getPrivateChannelById(channelId);
            if (channel == null) {
                channel = jda.getEntityBuilder().createPrivateChannel(DataObject.empty().put("id", channelId).put("recipient", data.getObject("user")));
            }
            this.channel = channel;
            this.user = channel.getUser();
        }
    }

    public InteractionImpl(long id2, int type, String token, Guild guild, Member member, User user, AbstractChannel channel) {
        this.id = id2;
        this.type = type;
        this.token = token;
        this.guild = guild;
        this.member = member;
        this.user = user;
        this.channel = channel;
        this.api = (JDAImpl)user.getJDA();
        this.hook = new InteractionHookImpl(this, this.api);
    }

    @Override
    public long getIdLong() {
        return this.id;
    }

    @Override
    public int getTypeRaw() {
        return this.type;
    }

    @Override
    @Nonnull
    public String getToken() {
        return this.token;
    }

    @Override
    @Nullable
    public Guild getGuild() {
        return this.guild;
    }

    @Override
    @Nullable
    public AbstractChannel getChannel() {
        return this.channel;
    }

    @Override
    @Nonnull
    public InteractionHook getHook() {
        return this.hook;
    }

    @Override
    @Nonnull
    public User getUser() {
        return this.user;
    }

    @Override
    @Nullable
    public Member getMember() {
        return this.member;
    }

    @Override
    public boolean isAcknowledged() {
        return this.hook.isAck();
    }

    @Override
    @Nonnull
    public ReplyActionImpl deferReply() {
        return new ReplyActionImpl(this.hook);
    }
}

