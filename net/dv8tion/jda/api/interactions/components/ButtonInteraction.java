/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.interactions.components;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.interactions.components.ActionRow;
import net.dv8tion.jda.api.interactions.components.Button;
import net.dv8tion.jda.api.interactions.components.Component;
import net.dv8tion.jda.api.interactions.components.ComponentInteraction;
import net.dv8tion.jda.api.requests.RestAction;

public interface ButtonInteraction
extends ComponentInteraction {
    @Override
    @Nullable
    default public Button getComponent() {
        return this.getButton();
    }

    @Nullable
    public Button getButton();

    @Nonnull
    @CheckReturnValue
    default public RestAction<Void> editButton(@Nullable Button newButton) {
        Message message = this.getMessage();
        if (message == null) {
            throw new IllegalStateException("Cannot update button for ephemeral messages! Discord does not provide enough information to perform the update.");
        }
        ArrayList<ActionRow> components = new ArrayList<ActionRow>(message.getActionRows());
        String id2 = this.getComponentId();
        Iterator rows = components.iterator();
        block0: while (rows.hasNext()) {
            List<Component> row = ((ActionRow)rows.next()).getComponents();
            ListIterator<Component> it3 = row.listIterator();
            while (it3.hasNext()) {
                Component component = it3.next();
                if (!id2.equals(component.getId())) continue;
                if (newButton == null) {
                    it3.remove();
                } else {
                    it3.set(newButton);
                }
                if (!row.isEmpty()) break block0;
                rows.remove();
                break block0;
            }
        }
        if (this.isAcknowledged()) {
            return this.getHook().editMessageComponentsById(message.getId(), components).map(it2 -> null);
        }
        return this.editComponents(components).map(it2 -> null);
    }
}

