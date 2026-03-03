/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.interactions.components;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.dv8tion.jda.api.entities.Emoji;
import net.dv8tion.jda.api.interactions.components.ButtonStyle;
import net.dv8tion.jda.api.interactions.components.Component;
import net.dv8tion.jda.internal.interactions.ButtonImpl;
import net.dv8tion.jda.internal.utils.Checks;

public interface Button
extends Component {
    @Nonnull
    public String getLabel();

    @Nonnull
    public ButtonStyle getStyle();

    @Nullable
    public String getUrl();

    @Nullable
    public Emoji getEmoji();

    public boolean isDisabled();

    @Nonnull
    default public Button asDisabled() {
        return new ButtonImpl(this.getId(), this.getLabel(), this.getStyle(), this.getUrl(), true, this.getEmoji());
    }

    @Nonnull
    default public Button asEnabled() {
        return new ButtonImpl(this.getId(), this.getLabel(), this.getStyle(), this.getUrl(), false, this.getEmoji());
    }

    @Nonnull
    default public Button withDisabled(boolean disabled) {
        return new ButtonImpl(this.getId(), this.getLabel(), this.getStyle(), this.getUrl(), disabled, this.getEmoji());
    }

    @Nonnull
    default public Button withEmoji(@Nullable Emoji emoji) {
        return new ButtonImpl(this.getId(), this.getLabel(), this.getStyle(), this.getUrl(), this.isDisabled(), emoji);
    }

    @Nonnull
    default public Button withLabel(@Nonnull String label) {
        Checks.notEmpty(label, "Label");
        Checks.notLonger(label, 80, "Label");
        return new ButtonImpl(this.getId(), label, this.getStyle(), this.getUrl(), this.isDisabled(), this.getEmoji());
    }

    @Nonnull
    default public Button withId(@Nonnull String id2) {
        Checks.notEmpty(id2, "ID");
        Checks.notLonger(id2, 100, "ID");
        return new ButtonImpl(id2, this.getLabel(), this.getStyle(), null, this.isDisabled(), this.getEmoji());
    }

    @Nonnull
    default public Button withUrl(@Nonnull String url) {
        Checks.notEmpty(url, "URL");
        Checks.notLonger(url, 512, "URL");
        return new ButtonImpl(null, this.getLabel(), ButtonStyle.LINK, url, this.isDisabled(), this.getEmoji());
    }

    @Nonnull
    default public Button withStyle(@Nonnull ButtonStyle style) {
        Checks.notNull((Object)style, "Style");
        Checks.check(style != ButtonStyle.UNKNOWN, "Cannot make button with unknown style!");
        if (this.getStyle() == ButtonStyle.LINK && style != ButtonStyle.LINK) {
            throw new IllegalArgumentException("You cannot change a link button to another style!");
        }
        if (this.getStyle() != ButtonStyle.LINK && style == ButtonStyle.LINK) {
            throw new IllegalArgumentException("You cannot change a styled button to a link button!");
        }
        return new ButtonImpl(this.getId(), this.getLabel(), style, this.getUrl(), this.isDisabled(), this.getEmoji());
    }

    @Nonnull
    public static Button primary(@Nonnull String id2, @Nonnull String label) {
        Checks.notEmpty(id2, "Id");
        Checks.notEmpty(label, "Label");
        Checks.notLonger(id2, 100, "Id");
        Checks.notLonger(label, 80, "Label");
        return new ButtonImpl(id2, label, ButtonStyle.PRIMARY, false, null);
    }

    @Nonnull
    public static Button primary(@Nonnull String id2, @Nonnull Emoji emoji) {
        Checks.notEmpty(id2, "Id");
        Checks.notNull(emoji, "Emoji");
        Checks.notLonger(id2, 100, "Id");
        return new ButtonImpl(id2, "", ButtonStyle.PRIMARY, false, emoji);
    }

    @Nonnull
    public static Button secondary(@Nonnull String id2, @Nonnull String label) {
        Checks.notEmpty(id2, "Id");
        Checks.notEmpty(label, "Label");
        Checks.notLonger(id2, 100, "Id");
        Checks.notLonger(label, 80, "Label");
        return new ButtonImpl(id2, label, ButtonStyle.SECONDARY, false, null);
    }

    @Nonnull
    public static Button secondary(@Nonnull String id2, @Nonnull Emoji emoji) {
        Checks.notEmpty(id2, "Id");
        Checks.notNull(emoji, "Emoji");
        Checks.notLonger(id2, 100, "Id");
        return new ButtonImpl(id2, "", ButtonStyle.SECONDARY, false, emoji);
    }

    @Nonnull
    public static Button success(@Nonnull String id2, @Nonnull String label) {
        Checks.notEmpty(id2, "Id");
        Checks.notEmpty(label, "Label");
        Checks.notLonger(id2, 100, "Id");
        Checks.notLonger(label, 80, "Label");
        return new ButtonImpl(id2, label, ButtonStyle.SUCCESS, false, null);
    }

    @Nonnull
    public static Button success(@Nonnull String id2, @Nonnull Emoji emoji) {
        Checks.notEmpty(id2, "Id");
        Checks.notNull(emoji, "Emoji");
        Checks.notLonger(id2, 100, "Id");
        return new ButtonImpl(id2, "", ButtonStyle.SUCCESS, false, emoji);
    }

    @Nonnull
    public static Button danger(@Nonnull String id2, @Nonnull String label) {
        Checks.notEmpty(id2, "Id");
        Checks.notEmpty(label, "Label");
        Checks.notLonger(id2, 100, "Id");
        Checks.notLonger(label, 80, "Label");
        return new ButtonImpl(id2, label, ButtonStyle.DANGER, false, null);
    }

    @Nonnull
    public static Button danger(@Nonnull String id2, @Nonnull Emoji emoji) {
        Checks.notEmpty(id2, "Id");
        Checks.notNull(emoji, "Emoji");
        Checks.notLonger(id2, 100, "Id");
        return new ButtonImpl(id2, "", ButtonStyle.DANGER, false, emoji);
    }

    @Nonnull
    public static Button link(@Nonnull String url, @Nonnull String label) {
        Checks.notEmpty(url, "URL");
        Checks.notEmpty(label, "Label");
        Checks.notLonger(url, 512, "URL");
        Checks.notLonger(label, 80, "Label");
        return new ButtonImpl(null, label, ButtonStyle.LINK, url, false, null);
    }

    @Nonnull
    public static Button link(@Nonnull String url, @Nonnull Emoji emoji) {
        Checks.notEmpty(url, "URL");
        Checks.notNull(emoji, "Emoji");
        Checks.notLonger(url, 512, "URL");
        return new ButtonImpl(null, "", ButtonStyle.LINK, url, false, emoji);
    }

    @Nonnull
    public static Button of(@Nonnull ButtonStyle style, @Nonnull String idOrUrl, @Nonnull String label) {
        Checks.check(style != ButtonStyle.UNKNOWN, "Cannot make button with unknown style!");
        Checks.notNull((Object)style, "Style");
        Checks.notNull(label, "Label");
        Checks.notLonger(label, 80, "Label");
        if (style == ButtonStyle.LINK) {
            return Button.link(idOrUrl, label);
        }
        Checks.notEmpty(idOrUrl, "Id");
        Checks.notLonger(idOrUrl, 100, "Id");
        return new ButtonImpl(idOrUrl, label, style, false, null);
    }

    @Nonnull
    public static Button of(@Nonnull ButtonStyle style, @Nonnull String idOrUrl, @Nonnull Emoji emoji) {
        Checks.check(style != ButtonStyle.UNKNOWN, "Cannot make button with unknown style!");
        Checks.notNull((Object)style, "Style");
        Checks.notNull(emoji, "Emoji");
        if (style == ButtonStyle.LINK) {
            return Button.link(idOrUrl, emoji);
        }
        Checks.notEmpty(idOrUrl, "Id");
        Checks.notLonger(idOrUrl, 100, "Id");
        return new ButtonImpl(idOrUrl, "", style, false, emoji);
    }

    @Nonnull
    public static Button of(@Nonnull ButtonStyle style, @Nonnull String idOrUrl, @Nullable String label, @Nullable Emoji emoji) {
        if (label != null) {
            return Button.of(style, idOrUrl, label).withEmoji(emoji);
        }
        if (emoji != null) {
            return Button.of(style, idOrUrl, emoji);
        }
        throw new IllegalArgumentException("Cannot build a button without a label and emoji. At least one has to be provided as non-null.");
    }
}

