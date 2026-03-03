/*
 * Decompiled with CFR 0.152.
 */
package org.apache.commons.collections4.sequence;

import org.apache.commons.collections4.sequence.CommandVisitor;
import org.apache.commons.collections4.sequence.EditCommand;

public class InsertCommand<T>
extends EditCommand<T> {
    public InsertCommand(T object) {
        super(object);
    }

    @Override
    public void accept(CommandVisitor<T> visitor) {
        visitor.visitInsertCommand(this.getObject());
    }
}

