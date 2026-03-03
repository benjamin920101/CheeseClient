/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.entities;

import java.util.Objects;
import java.util.regex.Matcher;
import javax.annotation.Nonnull;
import net.dv8tion.jda.api.entities.Emote;
import net.dv8tion.jda.api.entities.IMentionable;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.utils.data.DataObject;
import net.dv8tion.jda.api.utils.data.SerializableData;
import net.dv8tion.jda.internal.utils.Checks;
import net.dv8tion.jda.internal.utils.EncodingUtil;

public class Emoji
implements SerializableData,
IMentionable {
    private final String name;
    private final long id;
    private final boolean animated;

    private Emoji(String name, long id2, boolean animated) {
        this.name = name;
        this.id = id2;
        this.animated = animated;
    }

    @Nonnull
    public String getName() {
        return this.name;
    }

    @Override
    public long getIdLong() {
        return this.id;
    }

    public boolean isAnimated() {
        return this.animated;
    }

    public boolean isUnicode() {
        return this.id == 0L;
    }

    public boolean isCustom() {
        return !this.isUnicode();
    }

    @Nonnull
    public static Emoji fromUnicode(@Nonnull String code) {
        Checks.notEmpty(code, "Unicode");
        if (code.startsWith("U+") || code.startsWith("u+")) {
            String[] codepoints;
            StringBuilder emoji = new StringBuilder();
            for (String codepoint : codepoints = code.trim().split("\\s*[uU]\\+")) {
                emoji.append(codepoint.isEmpty() ? "" : EncodingUtil.decodeCodepoint("U+" + codepoint));
            }
            code = emoji.toString();
        }
        return new Emoji(code, 0L, false);
    }

    @Nonnull
    public static Emoji fromEmote(@Nonnull String name, long id2, boolean animated) {
        Checks.notEmpty(name, "Name");
        return new Emoji(name, id2, animated);
    }

    @Nonnull
    public static Emoji fromEmote(@Nonnull Emote emote) {
        Checks.notNull(emote, "Emote");
        return Emoji.fromEmote(emote.getName(), emote.getIdLong(), emote.isAnimated());
    }

    @Nonnull
    public static Emoji fromMarkdown(@Nonnull String code) {
        Matcher matcher = Message.MentionType.EMOTE.getPattern().matcher(code);
        if (matcher.matches()) {
            return Emoji.fromEmote(matcher.group(1), Long.parseUnsignedLong(matcher.group(2)), code.startsWith("<a"));
        }
        return Emoji.fromUnicode(code);
    }

    @Nonnull
    public static Emoji fromData(@Nonnull DataObject emoji) {
        return new Emoji(emoji.getString("name"), emoji.getUnsignedLong("id", 0L), emoji.getBoolean("animated"));
    }

    @Override
    @Nonnull
    public DataObject toData() {
        DataObject json = DataObject.empty().put("name", this.name);
        if (this.id != 0L) {
            json.put("id", this.id).put("animated", this.animated);
        }
        return json;
    }

    @Override
    @Nonnull
    public String getAsMention() {
        return this.id == 0L ? this.name : String.format("<%s:%s:%s>", this.animated ? "a" : "", this.name, Long.toUnsignedString(this.id));
    }

    public int hashCode() {
        return Objects.hash(this.name, this.id, this.animated);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Emoji)) {
            return false;
        }
        Emoji other = (Emoji)obj;
        return other.id == this.id && other.animated == this.animated && Objects.equals(other.name, this.name);
    }

    public String toString() {
        return "E:" + this.name + "(" + this.id + ")";
    }
}

