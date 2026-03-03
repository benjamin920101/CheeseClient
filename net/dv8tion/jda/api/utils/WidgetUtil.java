/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.utils;

import gnu.trove.map.TLongObjectMap;
import gnu.trove.map.hash.TLongObjectHashMap;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.IMentionable;
import net.dv8tion.jda.api.entities.ISnowflake;
import net.dv8tion.jda.api.exceptions.RateLimitedException;
import net.dv8tion.jda.api.utils.MiscUtil;
import net.dv8tion.jda.api.utils.data.DataArray;
import net.dv8tion.jda.api.utils.data.DataObject;
import net.dv8tion.jda.internal.entities.EntityBuilder;
import net.dv8tion.jda.internal.requests.Requester;
import net.dv8tion.jda.internal.utils.Checks;
import net.dv8tion.jda.internal.utils.Helpers;
import net.dv8tion.jda.internal.utils.IOUtil;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class WidgetUtil {
    public static final String WIDGET_PNG = Requester.DISCORD_API_PREFIX + "guilds/%s/widget.png?style=%s";
    public static final String WIDGET_URL = Requester.DISCORD_API_PREFIX + "guilds/%s/widget.json";
    public static final String WIDGET_HTML = "<iframe src=\"https://discord.com/widget?id=%s&theme=%s\" width=\"%d\" height=\"%d\" allowtransparency=\"true\" frameborder=\"0\"></iframe>";

    @Nonnull
    public static String getWidgetBanner(@Nonnull Guild guild, @Nonnull BannerType type) {
        Checks.notNull(guild, "Guild");
        return WidgetUtil.getWidgetBanner(guild.getId(), type);
    }

    @Nonnull
    public static String getWidgetBanner(@Nonnull String guildId, @Nonnull BannerType type) {
        Checks.notNull(guildId, "GuildId");
        Checks.notNull((Object)type, "BannerType");
        return String.format(WIDGET_PNG, guildId, type.name().toLowerCase());
    }

    @Nonnull
    public static String getPremadeWidgetHtml(@Nonnull Guild guild, @Nonnull WidgetTheme theme, int width, int height) {
        Checks.notNull(guild, "Guild");
        return WidgetUtil.getPremadeWidgetHtml(guild.getId(), theme, width, height);
    }

    @Nonnull
    public static String getPremadeWidgetHtml(@Nonnull String guildId, @Nonnull WidgetTheme theme, int width, int height) {
        Checks.notNull(guildId, "GuildId");
        Checks.notNull((Object)theme, "WidgetTheme");
        Checks.notNegative(width, "Width");
        Checks.notNegative(height, "Height");
        return Helpers.format(WIDGET_HTML, guildId, theme.name().toLowerCase(), width, height);
    }

    @Nullable
    public static Widget getWidget(@Nonnull String guildId) throws RateLimitedException {
        return WidgetUtil.getWidget(MiscUtil.parseSnowflake(guildId));
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive exception aggregation
     */
    @Nullable
    public static Widget getWidget(long guildId) throws RateLimitedException {
        Checks.notNull(guildId, "GuildId");
        client = new OkHttpClient.Builder().build();
        request = new Request.Builder().url(String.format(WidgetUtil.WIDGET_URL, new Object[]{guildId})).method("GET", null).header("user-agent", Requester.USER_AGENT).header("accept-encoding", "gzip").build();
        try {
            response = client.newCall(request).execute();
            try {
                code = response.code();
                data = IOUtil.getBody(response);
                switch (code) {
                    case 200: {
                        stream = data;
                        try {
                            var9_14 = new Widget(DataObject.fromJson(stream));
                            if (stream == null) ** GOTO lbl28
                        }
                        catch (Throwable var9_15) {
                            try {
                                if (stream != null) {
                                    try {
                                        stream.close();
                                    }
                                    catch (Throwable var10_16) {
                                        var9_15.addSuppressed(var10_16);
                                    }
                                }
                                throw var9_15;
                            }
                            catch (IOException e) {
                                throw new UncheckedIOException(e);
                            }
                        }
                        stream.close();
lbl28:
                        // 2 sources

                        return var9_14;
                    }
                    case 400: 
                    case 404: {
                        e = null;
                        return e;
                    }
                    case 403: {
                        e = new Widget(guildId);
                        return e;
                    }
                    case 429: {
                        try {
                            stream = data;
                            try {
                                retryAfter = DataObject.fromJson(stream).getLong("retry_after");
                            }
                            finally {
                                if (stream != null) {
                                    stream.close();
                                }
                            }
                        }
                        catch (Exception e) {
                            retryAfter = 0L;
                        }
                        throw new RateLimitedException(WidgetUtil.WIDGET_URL, retryAfter);
                    }
                }
                throw new IllegalStateException("An unknown status was returned: " + code + " " + response.message());
            }
            finally {
                if (response != null) {
                    response.close();
                }
            }
        }
        catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    public static class Widget
    implements ISnowflake {
        private final boolean isAvailable;
        private final long id;
        private final String name;
        private final String invite;
        private final TLongObjectMap<VoiceChannel> channels;
        private final TLongObjectMap<Member> members;

        private Widget(long guildId) {
            this.isAvailable = false;
            this.id = guildId;
            this.name = null;
            this.invite = null;
            this.channels = new TLongObjectHashMap<VoiceChannel>();
            this.members = new TLongObjectHashMap<Member>();
        }

        private Widget(@Nonnull DataObject json) {
            String inviteCode = json.getString("instant_invite", null);
            if (inviteCode != null) {
                inviteCode = inviteCode.substring(inviteCode.lastIndexOf("/") + 1);
            }
            this.isAvailable = true;
            this.id = json.getLong("id");
            this.name = json.getString("name");
            this.invite = inviteCode;
            this.channels = MiscUtil.newLongMap();
            this.members = MiscUtil.newLongMap();
            DataArray channelsJson = json.getArray("channels");
            for (int i2 = 0; i2 < channelsJson.length(); ++i2) {
                DataObject channel = channelsJson.getObject(i2);
                this.channels.put(channel.getLong("id"), new VoiceChannel(channel, this));
            }
            DataArray membersJson = json.getArray("members");
            for (int i3 = 0; i3 < membersJson.length(); ++i3) {
                DataObject memberJson = membersJson.getObject(i3);
                Member member = new Member(memberJson, this);
                if (!memberJson.isNull("channel_id")) {
                    VoiceChannel channel = this.channels.get(memberJson.getLong("channel_id"));
                    member.setVoiceState(new VoiceState(channel, memberJson.getBoolean("mute"), memberJson.getBoolean("deaf"), memberJson.getBoolean("suppress"), memberJson.getBoolean("self_mute"), memberJson.getBoolean("self_deaf"), member, this));
                    channel.addMember(member);
                }
                this.members.put(member.getIdLong(), member);
            }
        }

        public boolean isAvailable() {
            return this.isAvailable;
        }

        @Override
        public long getIdLong() {
            return this.id;
        }

        @Nonnull
        public String getName() {
            this.checkAvailable();
            return this.name;
        }

        @Nullable
        public String getInviteCode() {
            this.checkAvailable();
            return this.invite;
        }

        @Nonnull
        public List<VoiceChannel> getVoiceChannels() {
            this.checkAvailable();
            return Collections.unmodifiableList(new ArrayList<VoiceChannel>(this.channels.valueCollection()));
        }

        @Nullable
        public VoiceChannel getVoiceChannelById(String id2) {
            this.checkAvailable();
            return this.channels.get(MiscUtil.parseSnowflake(id2));
        }

        @Nullable
        public VoiceChannel getVoiceChannelById(long id2) {
            this.checkAvailable();
            return this.channels.get(id2);
        }

        @Nonnull
        public List<Member> getMembers() {
            this.checkAvailable();
            return Collections.unmodifiableList(new ArrayList<Member>(this.members.valueCollection()));
        }

        @Nullable
        public Member getMemberById(String id2) {
            this.checkAvailable();
            return this.members.get(MiscUtil.parseSnowflake(id2));
        }

        @Nullable
        public Member getMemberById(long id2) {
            this.checkAvailable();
            return this.members.get(id2);
        }

        public int hashCode() {
            return Long.hashCode(this.id);
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof Widget)) {
                return false;
            }
            Widget oWidget = (Widget)obj;
            return this == oWidget || this.id == oWidget.getIdLong();
        }

        public String toString() {
            return "W:" + (this.isAvailable() ? this.getName() : "") + '(' + this.id + ')';
        }

        private void checkAvailable() {
            if (!this.isAvailable) {
                throw new IllegalStateException("The widget for this Guild is unavailable!");
            }
        }

        public static class VoiceState {
            private final VoiceChannel channel;
            private final boolean muted;
            private final boolean deafened;
            private final boolean suppress;
            private final boolean selfMute;
            private final boolean selfDeaf;
            private final Member member;
            private final Widget widget;

            private VoiceState(@Nonnull Member member, @Nonnull Widget widget) {
                this(null, false, false, false, false, false, member, widget);
            }

            private VoiceState(@Nullable VoiceChannel channel, boolean muted, boolean deafened, boolean suppress, boolean selfMute, boolean selfDeaf, @Nonnull Member member, @Nonnull Widget widget) {
                this.channel = channel;
                this.muted = muted;
                this.deafened = deafened;
                this.suppress = suppress;
                this.selfMute = selfMute;
                this.selfDeaf = selfDeaf;
                this.member = member;
                this.widget = widget;
            }

            @Nullable
            public VoiceChannel getChannel() {
                return this.channel;
            }

            public boolean inVoiceChannel() {
                return this.channel != null;
            }

            public boolean isGuildMuted() {
                return this.muted;
            }

            public boolean isGuildDeafened() {
                return this.deafened;
            }

            public boolean isSuppressed() {
                return this.suppress;
            }

            public boolean isSelfMuted() {
                return this.selfMute;
            }

            public boolean isSelfDeafened() {
                return this.selfDeaf;
            }

            public boolean isMuted() {
                return this.selfMute || this.muted;
            }

            public boolean isDeafened() {
                return this.selfDeaf || this.deafened;
            }

            @Nonnull
            public Member getMember() {
                return this.member;
            }

            @Nonnull
            public Widget getWidget() {
                return this.widget;
            }

            public int hashCode() {
                return this.member.hashCode();
            }

            public boolean equals(Object obj) {
                if (!(obj instanceof VoiceState)) {
                    return false;
                }
                VoiceState oState = (VoiceState)obj;
                return this == oState || this.member.equals(oState.getMember()) && this.widget.equals(oState.getWidget());
            }

            public String toString() {
                return "VS:" + this.widget.getName() + ':' + this.member.getEffectiveName();
            }
        }

        public static class VoiceChannel
        implements ISnowflake {
            private final int position;
            private final long id;
            private final String name;
            private final List<Member> members;
            private final Widget widget;

            private VoiceChannel(@Nonnull DataObject json, @Nonnull Widget widget) {
                this.widget = widget;
                this.position = json.getInt("position");
                this.id = json.getLong("id");
                this.name = json.getString("name");
                this.members = new ArrayList<Member>();
            }

            private void addMember(@Nonnull Member member) {
                this.members.add(member);
            }

            public int getPosition() {
                return this.position;
            }

            @Override
            public long getIdLong() {
                return this.id;
            }

            @Nonnull
            public String getName() {
                return this.name;
            }

            @Nonnull
            public List<Member> getMembers() {
                return this.members;
            }

            @Nonnull
            public Widget getWidget() {
                return this.widget;
            }

            public int hashCode() {
                return Long.hashCode(this.id);
            }

            public boolean equals(Object obj) {
                if (!(obj instanceof VoiceChannel)) {
                    return false;
                }
                VoiceChannel oVChannel = (VoiceChannel)obj;
                return this == oVChannel || this.id == oVChannel.getIdLong();
            }

            public String toString() {
                return "W.VC:" + this.getName() + '(' + this.id + ')';
            }
        }

        public static class Member
        implements IMentionable {
            private final boolean bot;
            private final long id;
            private final String username;
            private final String discriminator;
            private final String avatar;
            private final String nickname;
            private final OnlineStatus status;
            private final Activity game;
            private final Widget widget;
            private VoiceState state;

            private Member(@Nonnull DataObject json, @Nonnull Widget widget) {
                this.widget = widget;
                this.bot = json.getBoolean("bot");
                this.id = json.getLong("id");
                this.username = json.getString("username");
                this.discriminator = json.getString("discriminator");
                this.avatar = json.getString("avatar", null);
                this.nickname = json.getString("nick", null);
                this.status = OnlineStatus.fromKey(json.getString("status"));
                this.game = json.isNull("game") ? null : EntityBuilder.createActivity(json.getObject("game"));
            }

            private void setVoiceState(VoiceState voiceState) {
                this.state = voiceState;
            }

            public boolean isBot() {
                return this.bot;
            }

            @Nonnull
            public String getName() {
                return this.username;
            }

            @Override
            public long getIdLong() {
                return this.id;
            }

            @Override
            @Nonnull
            public String getAsMention() {
                return "<@" + this.getId() + ">";
            }

            @Nonnull
            public String getDiscriminator() {
                return this.discriminator;
            }

            @Nullable
            public String getAvatarId() {
                return this.avatar;
            }

            @Nullable
            public String getAvatarUrl() {
                String avatarId = this.getAvatarId();
                return avatarId == null ? null : String.format("https://cdn.discordapp.com/avatars/%s/%s.%s", this.getId(), avatarId, avatarId.startsWith("a_") ? ".gif" : ".png");
            }

            @Nonnull
            public String getDefaultAvatarId() {
                return String.valueOf(Integer.parseInt(this.getDiscriminator()) % 5);
            }

            @Nonnull
            public String getDefaultAvatarUrl() {
                return String.format("https://cdn.discordapp.com/embed/avatars/%s.png", this.getDefaultAvatarId());
            }

            @Nonnull
            public String getEffectiveAvatarUrl() {
                String avatarUrl = this.getAvatarUrl();
                return avatarUrl == null ? this.getDefaultAvatarUrl() : avatarUrl;
            }

            @Nullable
            public String getNickname() {
                return this.nickname;
            }

            @Nonnull
            public String getEffectiveName() {
                return this.nickname == null ? this.username : this.nickname;
            }

            @Nonnull
            public OnlineStatus getOnlineStatus() {
                return this.status;
            }

            @Nullable
            public Activity getActivity() {
                return this.game;
            }

            @Nonnull
            public VoiceState getVoiceState() {
                return this.state == null ? new VoiceState(this, this.widget) : this.state;
            }

            @Nonnull
            public Widget getWidget() {
                return this.widget;
            }

            public int hashCode() {
                return (this.widget.getId() + ' ' + this.id).hashCode();
            }

            public boolean equals(Object obj) {
                if (!(obj instanceof Member)) {
                    return false;
                }
                Member oMember = (Member)obj;
                return this == oMember || this.id == oMember.getIdLong() && this.widget.getIdLong() == oMember.getWidget().getIdLong();
            }

            public String toString() {
                return "W.M:" + this.getName() + '(' + this.id + ')';
            }
        }
    }

    public static enum WidgetTheme {
        LIGHT,
        DARK;

    }

    public static enum BannerType {
        SHIELD,
        BANNER1,
        BANNER2,
        BANNER3,
        BANNER4;

    }
}

