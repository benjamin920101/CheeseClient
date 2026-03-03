/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api;

import java.util.Collections;
import java.util.EnumSet;
import java.util.Set;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public enum Region {
    AMSTERDAM("amsterdam", "Amsterdam", "\ud83c\uddf3\ud83c\uddf1", false),
    BRAZIL("brazil", "Brazil", "\ud83c\udde7\ud83c\uddf7", false),
    EUROPE("europe", "Europe", "\ud83c\uddea\ud83c\uddfa", false),
    EU_CENTRAL("eu-central", "EU Central", "\ud83c\uddea\ud83c\uddfa", false),
    EU_WEST("eu-west", "EU West", "\ud83c\uddea\ud83c\uddfa", false),
    FRANKFURT("frankfurt", "Frankfurt", "\ud83c\udde9\ud83c\uddea", false),
    HONG_KONG("hongkong", "Hong Kong", "\ud83c\udded\ud83c\uddf0", false),
    JAPAN("japan", "Japan", "\ud83c\uddef\ud83c\uddf5", false),
    SOUTH_KOREA("south-korea", "South Korea", "\ud83c\uddf0\ud83c\uddf7", false),
    LONDON("london", "London", "\ud83c\uddec\ud83c\udde7", false),
    RUSSIA("russia", "Russia", "\ud83c\uddf7\ud83c\uddfa", false),
    INDIA("india", "India", "\ud83c\uddee\ud83c\uddf3", false),
    SINGAPORE("singapore", "Singapore", "\ud83c\uddf8\ud83c\uddec", false),
    SOUTH_AFRICA("southafrica", "South Africa", "\ud83c\uddff\ud83c\udde6", false),
    SYDNEY("sydney", "Sydney", "\ud83c\udde6\ud83c\uddfa", false),
    US_CENTRAL("us-central", "US Central", "\ud83c\uddfa\ud83c\uddf8", false),
    US_EAST("us-east", "US East", "\ud83c\uddfa\ud83c\uddf8", false),
    US_SOUTH("us-south", "US South", "\ud83c\uddfa\ud83c\uddf8", false),
    US_WEST("us-west", "US West", "\ud83c\uddfa\ud83c\uddf8", false),
    VIP_AMSTERDAM("vip-amsterdam", "Amsterdam (VIP)", "\ud83c\uddf3\ud83c\uddf1", true),
    VIP_BRAZIL("vip-brazil", "Brazil (VIP)", "\ud83c\udde7\ud83c\uddf7", true),
    VIP_EU_CENTRAL("vip-eu-central", "EU Central (VIP)", "\ud83c\uddea\ud83c\uddfa", true),
    VIP_EU_WEST("vip-eu-west", "EU West (VIP)", "\ud83c\uddea\ud83c\uddfa", true),
    VIP_FRANKFURT("vip-frankfurt", "Frankfurt (VIP)", "\ud83c\udde9\ud83c\uddea", true),
    VIP_JAPAN("vip-japan", "Japan (VIP)", "\ud83c\uddef\ud83c\uddf5", true),
    VIP_SOUTH_KOREA("vip-south-korea", "South Korea (VIP)", "\ud83c\uddf0\ud83c\uddf7", true),
    VIP_LONDON("vip-london", "London (VIP)", "\ud83c\uddec\ud83c\udde7", true),
    VIP_SINGAPORE("vip-singapore", "Singapore (VIP)", "\ud83c\uddf8\ud83c\uddec", true),
    VIP_SOUTH_AFRICA("vip-southafrica", "South Africa (VIP)", "\ud83c\uddff\ud83c\udde6", true),
    VIP_SYDNEY("vip-sydney", "Sydney (VIP)", "\ud83c\udde6\ud83c\uddfa", true),
    VIP_US_CENTRAL("vip-us-central", "US Central (VIP)", "\ud83c\uddfa\ud83c\uddf8", true),
    VIP_US_EAST("vip-us-east", "US East (VIP)", "\ud83c\uddfa\ud83c\uddf8", true),
    VIP_US_SOUTH("vip-us-south", "US South (VIP)", "\ud83c\uddfa\ud83c\uddf8", true),
    VIP_US_WEST("vip-us-west", "US West (VIP)", "\ud83c\uddfa\ud83c\uddf8", true),
    UNKNOWN("", "Unknown Region", null, false),
    AUTOMATIC("automatic", "Automatic", null, false);

    public static final Set<Region> VOICE_CHANNEL_REGIONS;
    private final String key;
    private final String name;
    private final String emoji;
    private final boolean vip;

    private Region(String key, String name, String emoji, boolean vip) {
        this.key = key;
        this.name = name;
        this.emoji = emoji;
        this.vip = vip;
    }

    @Nonnull
    public String getName() {
        return this.name;
    }

    @Nonnull
    public String getKey() {
        return this.key;
    }

    @Nonnull
    public String getEmoji() {
        return this.emoji;
    }

    public boolean isVip() {
        return this.vip;
    }

    @Nonnull
    public static Region fromKey(@Nullable String key) {
        for (Region region : Region.values()) {
            if (!region.getKey().equals(key)) continue;
            return region;
        }
        return UNKNOWN;
    }

    public String toString() {
        return this.getName();
    }

    static {
        VOICE_CHANNEL_REGIONS = Collections.unmodifiableSet(EnumSet.of(AUTOMATIC, new Region[]{US_WEST, US_EAST, US_CENTRAL, US_SOUTH, SINGAPORE, SOUTH_AFRICA, SYDNEY, EUROPE, INDIA, SOUTH_KOREA, BRAZIL, JAPAN, RUSSIA}));
    }
}

