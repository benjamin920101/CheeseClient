/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.events.guild.update;

import javax.annotation.Nonnull;
import net.dv8tion.jda.annotations.DeprecatedSince;
import net.dv8tion.jda.annotations.ReplaceWith;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.Region;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.guild.update.GenericGuildUpdateEvent;

@Deprecated
@ReplaceWith(value="VoiceChannelUpdateRegionEvent")
@DeprecatedSince(value="4.3.0")
public class GuildUpdateRegionEvent
extends GenericGuildUpdateEvent<Region> {
    public static final String IDENTIFIER = "region";
    private final String oldRegion;
    private final String newRegion;

    public GuildUpdateRegionEvent(@Nonnull JDA api2, long responseNumber, @Nonnull Guild guild, @Nonnull String oldRegion) {
        super(api2, responseNumber, guild, Region.fromKey(oldRegion), guild.getRegion(), IDENTIFIER);
        this.oldRegion = oldRegion;
        this.newRegion = guild.getRegionRaw();
    }

    @Nonnull
    public Region getOldRegion() {
        return this.getOldValue();
    }

    @Nonnull
    public String getOldRegionRaw() {
        return this.oldRegion;
    }

    @Nonnull
    public Region getNewRegion() {
        return this.getNewValue();
    }

    @Nonnull
    public String getNewRegionRaw() {
        return this.newRegion;
    }

    @Override
    @Nonnull
    public Region getOldValue() {
        return (Region)((Object)super.getOldValue());
    }

    @Override
    @Nonnull
    public Region getNewValue() {
        return (Region)((Object)super.getNewValue());
    }
}

