/*
 * Decompiled with CFR 0.152.
 */
package jaco.mp3.player;

import jaco.mp3.player.t;

public final class v
extends t {
    private int a;

    public v(String string, Throwable throwable) {
        super(string, throwable);
        this.a = 256;
    }

    public v(int n2, Throwable throwable) {
        int n3 = n2;
        this("Bitstream errorcode " + Integer.toHexString(n3), throwable);
        this.a = n2;
    }

    public final int a() {
        return this.a;
    }
}

