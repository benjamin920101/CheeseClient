/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.internal.entities;

import java.util.EnumSet;
import java.util.Objects;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.ActivityFlag;
import net.dv8tion.jda.api.entities.RichPresence;
import net.dv8tion.jda.internal.entities.ActivityImpl;

public class RichPresenceImpl
extends ActivityImpl
implements RichPresence {
    protected final long applicationId;
    protected final RichPresence.Party party;
    protected final String details;
    protected final String state;
    protected final RichPresence.Image largeImage;
    protected final RichPresence.Image smallImage;
    protected final String sessionId;
    protected final String syncId;
    protected final int flags;

    protected RichPresenceImpl(Activity.ActivityType type, String name, String url, long applicationId, Activity.Emoji emoji, RichPresence.Party party, String details, String state, Activity.Timestamps timestamps, String syncId, String sessionId, int flags, String largeImageKey, String largeImageText, String smallImageKey, String smallImageText) {
        super(name, url, type, timestamps, emoji);
        this.applicationId = applicationId;
        this.party = party;
        this.details = details;
        this.state = state;
        this.sessionId = sessionId;
        this.syncId = syncId;
        this.flags = flags;
        this.largeImage = largeImageKey != null ? new RichPresence.Image(applicationId, largeImageKey, largeImageText) : null;
        this.smallImage = smallImageKey != null ? new RichPresence.Image(applicationId, smallImageKey, smallImageText) : null;
    }

    @Override
    public boolean isRich() {
        return true;
    }

    @Override
    public RichPresence asRichPresence() {
        return this;
    }

    @Override
    public long getApplicationIdLong() {
        return this.applicationId;
    }

    @Override
    @Nonnull
    public String getApplicationId() {
        return Long.toUnsignedString(this.applicationId);
    }

    @Override
    @Nullable
    public String getSessionId() {
        return this.sessionId;
    }

    @Override
    @Nullable
    public String getSyncId() {
        return this.syncId;
    }

    @Override
    public int getFlags() {
        return this.flags;
    }

    @Override
    public EnumSet<ActivityFlag> getFlagSet() {
        return ActivityFlag.getFlags(this.getFlags());
    }

    @Override
    @Nullable
    public String getState() {
        return this.state;
    }

    @Override
    @Nullable
    public String getDetails() {
        return this.details;
    }

    @Override
    @Nullable
    public RichPresence.Party getParty() {
        return this.party;
    }

    @Override
    @Nullable
    public RichPresence.Image getLargeImage() {
        return this.largeImage;
    }

    @Override
    @Nullable
    public RichPresence.Image getSmallImage() {
        return this.smallImage;
    }

    @Override
    public String toString() {
        return String.format("RichPresence(%s / %s)", this.name, this.getApplicationId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.applicationId, this.state, this.details, this.party, this.sessionId, this.syncId, this.flags, this.timestamps, this.largeImage, this.smallImage);
    }

    @Override
    public boolean equals(Object o2) {
        if (this == o2) {
            return true;
        }
        if (!(o2 instanceof RichPresenceImpl)) {
            return false;
        }
        RichPresenceImpl p2 = (RichPresenceImpl)o2;
        return this.applicationId == p2.applicationId && Objects.equals(this.name, p2.name) && Objects.equals(this.url, p2.url) && Objects.equals((Object)this.type, (Object)p2.type) && Objects.equals(this.state, p2.state) && Objects.equals(this.details, p2.details) && Objects.equals(this.party, p2.party) && Objects.equals(this.sessionId, p2.sessionId) && Objects.equals(this.syncId, p2.syncId) && Objects.equals(this.flags, p2.flags) && Objects.equals(this.timestamps, p2.timestamps) && Objects.equals(this.largeImage, p2.largeImage) && Objects.equals(this.smallImage, p2.smallImage);
    }
}

