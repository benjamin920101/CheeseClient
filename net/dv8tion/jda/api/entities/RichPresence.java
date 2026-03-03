/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.entities;

import java.util.EnumSet;
import java.util.Objects;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.ActivityFlag;
import net.dv8tion.jda.internal.utils.Helpers;

public interface RichPresence
extends Activity {
    public long getApplicationIdLong();

    @Nonnull
    public String getApplicationId();

    @Nullable
    public String getSessionId();

    @Nullable
    public String getSyncId();

    public int getFlags();

    public EnumSet<ActivityFlag> getFlagSet();

    @Nullable
    public String getState();

    @Nullable
    public String getDetails();

    @Nullable
    public Party getParty();

    @Nullable
    public Image getLargeImage();

    @Nullable
    public Image getSmallImage();

    public static class Party {
        protected final String id;
        protected final long size;
        protected final long max;

        public Party(String id2, long size, long max) {
            this.id = id2;
            this.size = size;
            this.max = max;
        }

        @Nullable
        public String getId() {
            return this.id;
        }

        public long getSize() {
            return this.size;
        }

        public long getMax() {
            return this.max;
        }

        public String toString() {
            return Helpers.format("RichPresenceParty(%s | [%d, %d])", this.id, this.size, this.max);
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof Party)) {
                return false;
            }
            Party p2 = (Party)obj;
            return this.size == p2.size && this.max == p2.max && Objects.equals(this.id, p2.id);
        }

        public int hashCode() {
            return Objects.hash(this.id, this.size, this.max);
        }
    }

    public static class Image {
        protected final String key;
        protected final String text;
        protected final String applicationId;

        public Image(long applicationId, String key, String text) {
            this.applicationId = Long.toUnsignedString(applicationId);
            this.key = key;
            this.text = text;
        }

        @Nonnull
        public String getKey() {
            return this.key;
        }

        @Nullable
        public String getText() {
            return this.text;
        }

        @Nonnull
        public String getUrl() {
            if (this.key.startsWith("spotify:")) {
                return "https://i.scdn.co/image/" + this.key.substring("spotify:".length());
            }
            if (this.key.startsWith("twitch:")) {
                return String.format("https://static-cdn.jtvnw.net/previews-ttv/live_user_%s-1920x1080.png", this.key.substring("twitch:".length()));
            }
            return "https://cdn.discordapp.com/app-assets/" + this.applicationId + "/" + this.key + ".png";
        }

        public String toString() {
            return String.format("RichPresenceImage(%s | %s)", this.key, this.text);
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof Image)) {
                return false;
            }
            Image i2 = (Image)obj;
            return Objects.equals(this.key, i2.key) && Objects.equals(this.text, i2.text);
        }

        public int hashCode() {
            return Objects.hash(this.key, this.text);
        }
    }
}

