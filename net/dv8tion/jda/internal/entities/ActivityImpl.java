/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.internal.entities;

import java.util.Objects;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.RichPresence;

public class ActivityImpl
implements Activity {
    protected final String name;
    protected final String url;
    protected final Activity.ActivityType type;
    protected final Activity.Timestamps timestamps;
    protected final Activity.Emoji emoji;

    protected ActivityImpl(String name) {
        this(name, null, Activity.ActivityType.DEFAULT);
    }

    protected ActivityImpl(String name, String url) {
        this(name, url, Activity.ActivityType.STREAMING);
    }

    protected ActivityImpl(String name, String url, Activity.ActivityType type) {
        this(name, url, type, null, null);
    }

    protected ActivityImpl(String name, String url, Activity.ActivityType type, Activity.Timestamps timestamps, Activity.Emoji emoji) {
        this.name = name;
        this.url = url;
        this.type = type;
        this.timestamps = timestamps;
        this.emoji = emoji;
    }

    @Override
    public boolean isRich() {
        return false;
    }

    @Override
    public RichPresence asRichPresence() {
        return null;
    }

    @Override
    @Nonnull
    public String getName() {
        return this.name;
    }

    @Override
    public String getUrl() {
        return this.url;
    }

    @Override
    @Nonnull
    public Activity.ActivityType getType() {
        return this.type;
    }

    @Override
    @Nullable
    public Activity.Timestamps getTimestamps() {
        return this.timestamps;
    }

    @Override
    @Nullable
    public Activity.Emoji getEmoji() {
        return this.emoji;
    }

    public boolean equals(Object o2) {
        if (o2 == this) {
            return true;
        }
        if (!(o2 instanceof ActivityImpl)) {
            return false;
        }
        ActivityImpl oGame = (ActivityImpl)o2;
        return oGame.getType() == this.type && Objects.equals(this.name, oGame.getName()) && Objects.equals(this.url, oGame.getUrl()) && Objects.equals(this.timestamps, oGame.timestamps);
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.name, this.type, this.url, this.timestamps});
    }

    public String toString() {
        if (this.url != null) {
            return String.format("Activity(%s | %s)", this.name, this.url);
        }
        return String.format("Activity(%s)", this.name);
    }
}

