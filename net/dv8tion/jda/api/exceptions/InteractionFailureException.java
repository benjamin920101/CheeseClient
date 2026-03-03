/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.exceptions;

import java.util.concurrent.CancellationException;

public class InteractionFailureException
extends CancellationException {
    public InteractionFailureException() {
        super("Cascading failure caused by interaction callback failure");
    }
}

