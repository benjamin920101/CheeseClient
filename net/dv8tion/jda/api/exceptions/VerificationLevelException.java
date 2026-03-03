/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.exceptions;

import net.dv8tion.jda.annotations.DeprecatedSince;
import net.dv8tion.jda.annotations.ForRemoval;
import net.dv8tion.jda.api.entities.Guild;

@Deprecated
@ForRemoval(deadline="4.4.0")
@DeprecatedSince(value="4.2.0")
public class VerificationLevelException
extends IllegalStateException {
    public VerificationLevelException(Guild.VerificationLevel level) {
        super("Messages to this Guild can not be sent due to the Guilds verification level. (" + level.toString() + ')');
    }
}

