/*
 * Decompiled with CFR 0.152.
 */
package jaco.mp3.player;

import jaco.mp3.player.E;
import jaco.mp3.player.H;
import jaco.mp3.player.M;
import jaco.mp3.player.r;
import jaco.mp3.player.x;

final class D
extends x {
    private int j;
    private float k;
    private int l;
    private float n;
    private float o;
    private float p;

    public D(int n2) {
        super(n2);
    }

    @Override
    public final void a(H h2, M m2, r r2) {
        this.d = h2.d(4);
        this.j = h2.d(4);
        if (r2 != null) {
            r2.a(this.d, 4);
            r2.a(this.j, 4);
        }
        if (this.d != 0) {
            this.f = this.d + 1;
            this.h = a[this.d];
            this.i = b[this.d];
        }
        if (this.j != 0) {
            this.l = this.j + 1;
            this.o = a[this.j];
            this.p = b[this.j];
        }
    }

    @Override
    public final void a(H h2, M m2) {
        if (this.d != 0) {
            this.e = m[h2.d(6)];
        }
        if (this.j != 0) {
            this.k = m[h2.d(6)];
        }
    }

    @Override
    public final boolean a(H h2) {
        boolean bl2 = super.a(h2);
        if (this.j != 0) {
            this.n = h2.d(this.l);
        }
        return bl2;
    }

    @Override
    public final boolean a(int n2, E e2, E e3) {
        super.a(n2, e2, e3);
        if (this.j != 0 && n2 != 1) {
            float f2 = (this.n * this.o + this.p) * this.k;
            if (n2 == 0) {
                e3.a(f2, this.c);
            } else {
                e2.a(f2, this.c);
            }
        }
        return true;
    }
}

