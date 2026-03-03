/*
 * Decompiled with CFR 0.152.
 */
package jaco.mp3.player;

import jaco.mp3.player.E;
import jaco.mp3.player.H;
import jaco.mp3.player.M;
import jaco.mp3.player.k;
import jaco.mp3.player.r;

final class z
extends k {
    private int n;
    private float o;
    private float p;
    private float q;

    public z(int n2) {
        super(n2);
    }

    @Override
    public final void a(H h2, M m2, r r2) {
        super.a(h2, m2, r2);
    }

    @Override
    public final void a(H h2, r r2) {
        if (this.b != 0) {
            this.c = h2.d(2);
            this.n = h2.d(2);
            if (r2 != null) {
                r2.a(this.c, 2);
                r2.a(this.n, 2);
            }
        }
    }

    @Override
    public final void a(H h2, M m2) {
        if (this.b != 0) {
            super.a(h2, m2);
            switch (this.n) {
                case 0: {
                    this.o = m[h2.d(6)];
                    this.p = m[h2.d(6)];
                    this.q = m[h2.d(6)];
                    return;
                }
                case 1: {
                    this.o = this.p = m[h2.d(6)];
                    this.q = m[h2.d(6)];
                    return;
                }
                case 2: {
                    this.p = this.q = m[h2.d(6)];
                    this.o = this.q;
                    return;
                }
                case 3: {
                    this.o = m[h2.d(6)];
                    this.p = this.q = m[h2.d(6)];
                }
            }
        }
    }

    @Override
    public final boolean a(H h2) {
        return super.a(h2);
    }

    @Override
    public final boolean a(int n2, E e2, E e3) {
        if (this.b != 0) {
            float f2 = this.j[this.i];
            if (this.g[0] == null) {
                f2 = (f2 + this.l[0]) * this.k[0];
            }
            if (n2 == 0) {
                float f3 = f2;
                if (this.h <= 4) {
                    f2 *= this.d;
                    f3 *= this.o;
                } else if (this.h <= 8) {
                    f2 *= this.e;
                    f3 *= this.p;
                } else {
                    f2 *= this.f;
                    f3 *= this.q;
                }
                e2.a(f2, this.a);
                e3.a(f3, this.a);
            } else if (n2 == 1) {
                f2 = this.h <= 4 ? (f2 *= this.d) : (this.h <= 8 ? (f2 *= this.e) : (f2 *= this.f));
                e2.a(f2, this.a);
            } else {
                f2 = this.h <= 4 ? (f2 *= this.o) : (this.h <= 8 ? (f2 *= this.p) : (f2 *= this.q));
                e2.a(f2, this.a);
            }
        }
        return ++this.i == 3;
    }
}

