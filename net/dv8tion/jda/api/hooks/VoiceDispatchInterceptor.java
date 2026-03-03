/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.hooks;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.GuildVoiceState;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.managers.DirectAudioController;
import net.dv8tion.jda.api.utils.data.DataObject;
import net.dv8tion.jda.api.utils.data.SerializableData;

public interface VoiceDispatchInterceptor {
    public void onVoiceServerUpdate(@Nonnull VoiceServerUpdate var1);

    public boolean onVoiceStateUpdate(@Nonnull VoiceStateUpdate var1);

    public static class VoiceStateUpdate
    implements VoiceUpdate {
        private final VoiceChannel channel;
        private final GuildVoiceState voiceState;
        private final DataObject json;

        public VoiceStateUpdate(VoiceChannel channel, GuildVoiceState voiceState, DataObject json) {
            this.channel = channel;
            this.voiceState = voiceState;
            this.json = json;
        }

        @Override
        @Nonnull
        public Guild getGuild() {
            return this.voiceState.getGuild();
        }

        @Override
        @Nonnull
        public DataObject toData() {
            return this.json;
        }

        @Nullable
        public VoiceChannel getChannel() {
            return this.channel;
        }

        @Nonnull
        public GuildVoiceState getVoiceState() {
            return this.voiceState;
        }
    }

    public static class VoiceServerUpdate
    implements VoiceUpdate {
        private final Guild guild;
        private final String endpoint;
        private final String token;
        private final String sessionId;
        private final DataObject json;

        public VoiceServerUpdate(Guild guild, String endpoint, String token, String sessionId, DataObject json) {
            this.guild = guild;
            this.endpoint = endpoint;
            this.token = token;
            this.sessionId = sessionId;
            this.json = json;
        }

        @Override
        @Nonnull
        public Guild getGuild() {
            return this.guild;
        }

        @Override
        @Nonnull
        public DataObject toData() {
            return this.json;
        }

        @Nonnull
        public String getEndpoint() {
            return this.endpoint;
        }

        @Nonnull
        public String getToken() {
            return this.token;
        }

        @Nonnull
        public String getSessionId() {
            return this.sessionId;
        }
    }

    public static interface VoiceUpdate
    extends SerializableData {
        @Nonnull
        public Guild getGuild();

        @Override
        @Nonnull
        public DataObject toData();

        @Nonnull
        default public DirectAudioController getAudioController() {
            return this.getJDA().getDirectAudioController();
        }

        default public long getGuildIdLong() {
            return this.getGuild().getIdLong();
        }

        @Nonnull
        default public String getGuildId() {
            return Long.toUnsignedString(this.getGuildIdLong());
        }

        @Nonnull
        default public JDA getJDA() {
            return this.getGuild().getJDA();
        }

        @Nullable
        default public JDA.ShardInfo getShardInfo() {
            return this.getJDA().getShardInfo();
        }
    }
}

