/*
 * Decompiled with CFR 0.152.
 */
package jaco.mp3.player;

import jaco.mp3.player.E;
import jaco.mp3.player.H;
import jaco.mp3.player.M;
import jaco.mp3.player.k;
import jaco.mp3.player.r;

final class Q
extends k {
    private int n;
    private int o;
    private float p;
    private float q;
    private float r;
    private int[] s = new int[1];
    private float[] t = new float[]{0.0f};
    private float[] u;
    private float[] v = new float[]{0.0f};
    private float[] w = new float[]{0.0f};

    public Q(int n2) {
        super(n2);
        this.u = new float[3];
    }

    @Override
    public final void a(H h2, M m2, r r2) {
        int n2 = this.a(m2);
        this.b = h2.d(n2);
        this.n = h2.d(n2);
        if (r2 != null) {
            r2.a(this.b, n2);
            r2.a(this.n, n2);
        }
    }

    @Override
    public final void a(H h2, r r2) {
        if (this.b != 0) {
            this.c = h2.d(2);
            if (r2 != null) {
                r2.a(this.c, 2);
            }
        }
        if (this.n != 0) {
            this.o = h2.d(2);
            if (r2 != null) {
                r2.a(this.o, 2);
            }
        }
    }

    @Override
    public final void a(H h2, M m2) {
        super.a(h2, m2);
        if (this.n != 0) {
            switch (this.o) {
                case 0: {
                    this.p = m[h2.d(6)];
                    this.q = m[h2.d(6)];
                    this.r = m[h2.d(6)];
                    break;
                }
                case 1: {
                    this.p = this.q = m[h2.d(6)];
                    this.r = m[h2.d(6)];
                    break;
                }
                case 2: {
                    this.q = this.r = m[h2.d(6)];
                    this.p = this.r;
                    break;
                }
                case 3: {
                    this.p = m[h2.d(6)];
                    this.q = this.r = m[h2.d(6)];
                }
            }
            this.a(m2, this.n, 1, this.t, this.s, this.v, this.w);
        }
    }

    @Override
    public final boolean a(H h2) {
        boolean bl2 = super.a(h2);
        if (((Q)object).n != 0) {
            if (((Q)object).g[1] != null) {
                int n2 = h2.d(((Q)object).s[0]);
                n2 += n2 << 1;
                float[] fArray = ((Q)object).u;
                Object object = ((Q)object).g[1];
                int n3 = n2;
                fArray[0] = (float)object[n2];
                fArray[1] = (float)object[++n3];
                fArray[2] = (float)object[++n3];
            } else {
                ((Q)object).u[0] = (float)((double)((float)h2.d(((Q)object).s[0]) * ((Q)object).t[0]) - 1.0);
                ((Q)object).u[1] = (float)((double)((float)h2.d(((Q)object).s[0]) * ((Q)object).t[0]) - 1.0);
                ((Q)object).u[2] = (float)((double)((float)h2.d(((Q)object).s[0]) * ((Q)object).t[0]) - 1.0);
            }
        }
        return bl2;
    }

    @Override
    public final boolean a(int n2, E e2, E e3) {
        boolean bl2 = super.a(n2, e2, e3);
        if (this.n != 0 && n2 != 1) {
            float f2 = this.u[this.i - 1];
            if (this.g[1] == null) {
                f2 = (f2 + this.w[0]) * this.v[0];
            }
            f2 = this.h <= 4 ? (f2 *= this.p) : (this.h <= 8 ? (f2 *= this.q) : (f2 *= this.r));
            if (n2 == 0) {
                e3.a(f2, this.a);
            } else {
                e2.a(f2, this.a);
            }
        }
        return bl2;
    }
}

