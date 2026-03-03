/*
 * Decompiled with CFR 0.152.
 */
package jaco.mp3.player;

import jaco.mp3.player.E;
import jaco.mp3.player.H;
import jaco.mp3.player.M;
import jaco.mp3.player.r;
import jaco.mp3.player.x;

final class b
extends x {
    private float j;

    public b(int n2) {
        super(n2);
    }

    @Override
    public final void a(H h2, M m2, r r2) {
        super.a(h2, m2, r2);
    }

    @Override
    public final void a(H h2, M m2) {
        if (this.d != 0) {
            this.e = m[h2.d(6)];
            this.j = m[h2.d(6)];
        }
    }

    @Override
    public final boolean a(H h2) {
        return super.a(h2);
    }

    @Override
    public final boolean a(int n2, E e2, E e3) {
        if (this.d != 0) {
            this.g = this.g * this.h + this.i;
            if (n2 == 0) {
                float f2 = this.g * this.e;
                float f3 = this.g * this.j;
                e2.a(f2, this.c);
                e3.a(f3, this.c);
            } else if (n2 == 1) {
                float f4 = this.g * this.e;
                e2.a(f4, this.c);
            } else {
                float f5 = this.g * this.j;
                e2.a(f5, this.c);
            }
        }
        return true;
    }
}

