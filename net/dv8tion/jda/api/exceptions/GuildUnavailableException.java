/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.exceptions;

import net.dv8tion.jda.annotations.DeprecatedSince;
import net.dv8tion.jda.annotations.ForRemoval;

@Deprecated
@ForRemoval(deadline="4.4.0")
@DeprecatedSince(value="4.1.0")
public class GuildUnavailableException
extends RuntimeException {
    public GuildUnavailableException() {
        this("This operation is not possible due to the Guild being temporarily unavailable");
    }

    public GuildUnavailableException(String reason) {
        super(reason);
    }
}

