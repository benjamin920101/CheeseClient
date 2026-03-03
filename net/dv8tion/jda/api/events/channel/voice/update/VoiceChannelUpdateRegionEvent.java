/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.events.channel.voice.update;

import javax.annotation.Nonnull;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.Region;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.events.channel.voice.update.GenericVoiceChannelUpdateEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class VoiceChannelUpdateRegionEvent
extends GenericVoiceChannelUpdateEvent<String> {
    public static final String IDENTIFIER = "region";

    public VoiceChannelUpdateRegionEvent(@NotNull JDA api2, long responseNumber, @NotNull VoiceChannel channel, @Nullable String oldRegion) {
        super(api2, responseNumber, channel, oldRegion, channel.getRegionRaw(), IDENTIFIER);
    }

    @Nonnull
    public Region getOldRegion() {
        return this.getOldValue() == null ? Region.AUTOMATIC : Region.fromKey((String)this.getOldValue());
    }

    @Nonnull
    public Region getNewRegion() {
        return this.getNewValue() == null ? Region.AUTOMATIC : Region.fromKey((String)this.getNewValue());
    }

    @Nullable
    public String getOldRegionRaw() {
        return (String)this.getOldValue();
    }

    @Nullable
    public String getNewRegionRaw() {
        return (String)this.getNewValue();
    }
}

