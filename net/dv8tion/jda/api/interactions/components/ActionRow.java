/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.interactions.components;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import javax.annotation.Nonnull;
import net.dv8tion.jda.api.interactions.components.Button;
import net.dv8tion.jda.api.interactions.components.Component;
import net.dv8tion.jda.api.interactions.components.ComponentLayout;
import net.dv8tion.jda.api.utils.data.DataArray;
import net.dv8tion.jda.api.utils.data.DataObject;
import net.dv8tion.jda.internal.interactions.ButtonImpl;
import net.dv8tion.jda.internal.utils.Checks;

public class ActionRow
implements ComponentLayout,
Iterable<Component> {
    private final List<Component> components = new ArrayList<Component>();

    private ActionRow() {
    }

    @Nonnull
    public static ActionRow fromData(@Nonnull DataObject data) {
        Checks.notNull(data, "Data");
        ActionRow row = new ActionRow();
        if (data.getInt("type", 0) != 1) {
            throw new IllegalArgumentException("Data has incorrect type. Expected: 1 Found: " + data.getInt("type"));
        }
        data.getArray("components").stream(DataArray::getObject).map(obj -> {
            switch (Component.Type.fromKey(obj.getInt("type"))) {
                case BUTTON: {
                    return new ButtonImpl((DataObject)obj);
                }
            }
            return null;
        }).filter(Objects::nonNull).forEach(row.components::add);
        return row;
    }

    @Nonnull
    public static ActionRow of(@Nonnull Collection<? extends Component> components) {
        Checks.noneNull(components, "Components");
        return ActionRow.of(components.toArray(new Component[0]));
    }

    @Nonnull
    public static ActionRow of(Component ... components) {
        Checks.noneNull(components, "Components");
        Checks.check(components.length <= 5, "Can only have 5 components per action row!");
        Checks.check(components.length > 0, "Cannot have empty row!");
        ActionRow row = new ActionRow();
        Collections.addAll(row.components, components);
        return row;
    }

    @Override
    @Nonnull
    public List<Component> getComponents() {
        return this.components;
    }

    @Override
    @Nonnull
    public List<Button> getButtons() {
        return Collections.unmodifiableList(this.getComponents().stream().filter(Button.class::isInstance).map(Button.class::cast).collect(Collectors.toList()));
    }

    @Override
    @Nonnull
    public ComponentLayout.Type getType() {
        return ComponentLayout.Type.ACTION_ROW;
    }

    @Override
    @Nonnull
    public DataObject toData() {
        return DataObject.empty().put("type", 1).put("components", DataArray.fromCollection(this.components));
    }

    @Override
    @Nonnull
    public Iterator<Component> iterator() {
        return this.components.iterator();
    }
}

