/*
 * Decompiled with CFR 0.152.
 */
package jaco.mp3.player;

import jaco.mp3.player.h;

public final class i
implements Cloneable {
    private h a = new h();

    public final Object clone() {
        try {
            return super.clone();
        }
        catch (CloneNotSupportedException cloneNotSupportedException) {
            throw new InternalError(this + ": " + cloneNotSupportedException);
        }
    }

    public final h a() {
        return this.a;
    }
}

