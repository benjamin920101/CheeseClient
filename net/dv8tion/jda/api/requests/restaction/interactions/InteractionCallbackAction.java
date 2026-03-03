/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.requests.restaction.interactions;

import net.dv8tion.jda.api.interactions.InteractionHook;
import net.dv8tion.jda.api.requests.RestAction;

public interface InteractionCallbackAction
extends RestAction<InteractionHook> {

    public static enum ResponseType {
        CHANNEL_MESSAGE_WITH_SOURCE(4),
        DEFERRED_CHANNEL_MESSAGE_WITH_SOURCE(5),
        DEFERRED_MESSAGE_UPDATE(6),
        MESSAGE_UPDATE(7);

        private final int raw;

        private ResponseType(int raw) {
            this.raw = raw;
        }

        public int getRaw() {
            return this.raw;
        }
    }
}

