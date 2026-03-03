/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.exceptions;

public class ParsingException
extends IllegalStateException {
    public ParsingException(String message, Exception cause) {
        super(message, cause);
    }

    public ParsingException(String message) {
        super(message);
    }

    public ParsingException(Exception cause) {
        super(cause);
    }
}

