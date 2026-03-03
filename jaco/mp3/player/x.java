/*
 * Decompiled with CFR 0.152.
 */
package jaco.mp3.player;

import jaco.mp3.player.E;
import jaco.mp3.player.H;
import jaco.mp3.player.M;
import jaco.mp3.player.r;
import jaco.mp3.player.s;
import jaco.mp3.player.y;

class x
extends s {
    public static final float[] a = new float[]{0.0f, 0.6666667f, 0.2857143f, 0.13333334f, 0.06451613f, 0.031746034f, 0.015748031f, 0.007843138f, 0.0039138943f, 0.0019550342f, 9.770396E-4f, 4.884005E-4f, 2.4417043E-4f, 1.2207776E-4f, 6.103702E-5f};
    public static final float[] b = new float[]{0.0f, -0.6666667f, -0.8571429f, -0.9333334f, -0.9677419f, -0.98412704f, -0.992126f, -0.9960785f, -0.99804306f, -0.9990225f, -0.9995115f, -0.99975586f, -0.9998779f, -0.99993896f, -0.9999695f};
    protected int c;
    private int j;
    protected int d;
    protected float e;
    protected int f;
    protected float g;
    protected float h;
    protected float i;

    public x(int n2) {
        this.c = n2;
        this.j = 0;
    }

    @Override
    public void a(H h2, M m2, r r2) {
        this.d = h2.d(4);
        if (this.d == 15) {
            throw new y(514, null);
        }
        if (r2 != null) {
            r2.a(this.d, 4);
        }
        if (this.d != 0) {
            this.f = this.d + 1;
            this.h = a[this.d];
            this.i = b[this.d];
        }
    }

    @Override
    public void a(H h2, M m2) {
        if (this.d != 0) {
            this.e = m[h2.d(6)];
        }
    }

    @Override
    public boolean a(H h2) {
        if (this.d != 0) {
            this.g = h2.d(this.f);
        }
        if (++this.j == 12) {
            this.j = 0;
            return true;
        }
        return false;
    }

    @Override
    public boolean a(int n2, E e2, E e3) {
        if (this.d != 0 && n2 != 2) {
            float f2 = (this.g * this.h + this.i) * this.e;
            e2.a(f2, this.c);
        }
        return true;
    }
}

