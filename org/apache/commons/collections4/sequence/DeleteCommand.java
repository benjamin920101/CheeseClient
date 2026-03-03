/*
 * Decompiled with CFR 0.152.
 */
package org.apache.commons.collections4.sequence;

import org.apache.commons.collections4.sequence.CommandVisitor;
import org.apache.commons.collections4.sequence.EditCommand;

public class DeleteCommand<T>
extends EditCommand<T> {
    public DeleteCommand(T object) {
        super(object);
    }

    @Override
    public void accept(CommandVisitor<T> visitor) {
        visitor.visitDeleteCommand(this.getObject());
    }
}

