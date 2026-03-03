/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.internal.interactions;

import java.util.Objects;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.dv8tion.jda.api.entities.Emoji;
import net.dv8tion.jda.api.interactions.components.Button;
import net.dv8tion.jda.api.interactions.components.ButtonStyle;
import net.dv8tion.jda.api.interactions.components.Component;
import net.dv8tion.jda.api.utils.data.DataObject;

public class ButtonImpl
implements Button {
    private final String id;
    private final String label;
    private final ButtonStyle style;
    private final String url;
    private final boolean disabled;
    private final Emoji emoji;

    public ButtonImpl(DataObject data) {
        this(data.getString("custom_id", null), data.getString("label", ""), ButtonStyle.fromKey(data.getInt("style")), data.getString("url", null), data.getBoolean("disabled"), data.optObject("emoji").map(Emoji::fromData).orElse(null));
    }

    public ButtonImpl(String id2, String label, ButtonStyle style, boolean disabled, Emoji emoji) {
        this(id2, label, style, null, disabled, emoji);
    }

    public ButtonImpl(String id2, String label, ButtonStyle style, String url, boolean disabled, Emoji emoji) {
        this.id = id2;
        this.label = label;
        this.style = style;
        this.url = url;
        this.disabled = disabled;
        this.emoji = emoji;
    }

    @Override
    @Nonnull
    public Component.Type getType() {
        return Component.Type.BUTTON;
    }

    @Override
    @Nullable
    public String getId() {
        return this.id;
    }

    @Override
    @Nonnull
    public String getLabel() {
        return this.label;
    }

    @Override
    @Nonnull
    public ButtonStyle getStyle() {
        return this.style;
    }

    @Override
    @Nullable
    public String getUrl() {
        return this.url;
    }

    @Override
    @Nullable
    public Emoji getEmoji() {
        return this.emoji;
    }

    @Override
    public boolean isDisabled() {
        return this.disabled;
    }

    @Override
    @Nonnull
    public DataObject toData() {
        DataObject json = DataObject.empty();
        json.put("type", 2);
        json.put("label", this.label);
        json.put("style", this.style.getKey());
        json.put("disabled", this.disabled);
        if (this.emoji != null) {
            json.put("emoji", this.emoji);
        }
        if (this.url != null) {
            json.put("url", this.url);
        } else {
            json.put("custom_id", this.id);
        }
        return json;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.id, this.label, this.style, this.url, this.disabled, this.emoji});
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ButtonImpl)) {
            return false;
        }
        ButtonImpl other = (ButtonImpl)obj;
        return Objects.equals(other.id, this.id) && Objects.equals(other.label, this.label) && Objects.equals(other.url, this.url) && Objects.equals(other.emoji, this.emoji) && other.disabled == this.disabled && other.style == this.style;
    }

    public String toString() {
        return "B:" + this.label + "(" + this.id + ")";
    }
}

