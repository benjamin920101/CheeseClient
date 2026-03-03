/*
 * Decompiled with CFR 0.152.
 */
package jaco.mp3.player;

import jaco.mp3.player.t;

public final class y
extends t {
    private y(String string, Throwable throwable) {
        super(string, throwable);
    }

    public y(int n2, Throwable throwable) {
        this("Decoder errorcode " + Integer.toHexString(n2), throwable);
    }
}

