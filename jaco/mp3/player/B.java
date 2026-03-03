/*
 * Decompiled with CFR 0.152.
 */
package jaco.mp3.player;

import jaco.mp3.player.D;
import jaco.mp3.player.E;
import jaco.mp3.player.H;
import jaco.mp3.player.L;
import jaco.mp3.player.M;
import jaco.mp3.player.b;
import jaco.mp3.player.m;
import jaco.mp3.player.r;
import jaco.mp3.player.s;
import jaco.mp3.player.x;

class B
implements m {
    protected H a;
    protected M b;
    private E g;
    private E h;
    private L i;
    private int j;
    protected int c;
    protected int d;
    protected s[] e;
    protected r f = new r();

    public final void a(H h2, M m2, E e2, E e3, L l2, int n2) {
        this.a = h2;
        this.b = m2;
        this.g = e2;
        this.h = e3;
        this.i = l2;
        this.j = 0;
    }

    @Override
    public final void a() {
        this.d = this.b.j();
        this.e = new s[32];
        this.c = this.b.f();
        this.b();
        B b2 = this;
        int n2 = 0;
        while (n2 < b2.d) {
            b2.e[n2].a(b2.a, b2.b, b2.f);
            ++n2;
        }
        this.c();
        if (this.f != null || this.b.g()) {
            b2 = this;
            n2 = 0;
            while (n2 < b2.d) {
                b2.e[n2].a(b2.a, b2.b);
                ++n2;
            }
            b2 = this;
            n2 = 0;
            boolean bl2 = false;
            int n3 = b2.b.f();
            do {
                int n4 = 0;
                while (n4 < b2.d) {
                    n2 = b2.e[n4].a(b2.a) ? '\u0001' : '\u0000';
                    ++n4;
                }
                do {
                    n4 = 0;
                    while (n4 < b2.d) {
                        bl2 = b2.e[n4].a(b2.j, b2.g, b2.h);
                        ++n4;
                    }
                    b2.g.a(b2.i);
                    if (b2.j != 0 || n3 == 3) continue;
                    b2.h.a(b2.i);
                } while (!bl2);
            } while (n2 == 0);
        }
    }

    protected void b() {
        if (this.c == 3) {
            int n2 = 0;
            while (n2 < this.d) {
                this.e[n2] = new x(n2);
                ++n2;
            }
            return;
        }
        if (this.c == 1) {
            int n3 = 0;
            while (n3 < this.b.k()) {
                this.e[n3] = new D(n3);
                ++n3;
            }
            while (n3 < this.d) {
                this.e[n3] = new b(n3);
                ++n3;
            }
            return;
        }
        int n4 = 0;
        while (n4 < this.d) {
            this.e[n4] = new D(n4);
            ++n4;
        }
    }

    protected void c() {
    }
}

