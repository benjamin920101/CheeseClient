/*
 * Decompiled with CFR 0.152.
 */
package jaco.mp3.player;

import jaco.mp3.player.B;
import jaco.mp3.player.E;
import jaco.mp3.player.H;
import jaco.mp3.player.J;
import jaco.mp3.player.K;
import jaco.mp3.player.L;
import jaco.mp3.player.M;
import jaco.mp3.player.d;
import jaco.mp3.player.h;
import jaco.mp3.player.i;
import jaco.mp3.player.y;

public final class c {
    private static final i a = new i();
    private L b;
    private E c;
    private E d;
    private d e;
    private K f;
    private B g;
    private int h;
    private int i;
    private h j = new h();
    private i k;
    private boolean l;

    public c() {
        this(null);
    }

    private c(i object) {
        object = a;
        this.k = object;
        object = this.k.a();
        if (object != null) {
            this.j.a((h)object);
        }
    }

    public final L a(M object, H h2) {
        int n2;
        M m2;
        if (!c4.l) {
            m2 = object;
            c c2 = c4;
            float f2 = 32700.0f;
            n2 = m2.f();
            int n3 = n2 = n2 == 3 ? 1 : 2;
            if (c2.b == null) {
                c2.b = new J(m2.e(), n2);
            }
            c2.j.a();
            c2.c = new E(0, f2);
            if (n2 == 2) {
                c2.d = new E(1, f2);
            }
            c2.i = n2;
            c2.h = m2.e();
            c2.l = true;
        }
        int n4 = ((M)object).b();
        c4.b.c();
        n2 = n4;
        H h3 = h2;
        m2 = object;
        c c3 = c4;
        object = null;
        switch (n2) {
            case 3: {
                if (c3.e == null) {
                    c3.e = new d(h3, m2, c3.c, c3.d, c3.b, 0);
                }
                object = c3.e;
                break;
            }
            case 2: {
                if (c3.f == null) {
                    c3.f = new K();
                    c3.f.a(h3, m2, c3.c, c3.d, c3.b, 0);
                }
                object = c3.f;
                break;
            }
            case 1: {
                if (c3.g == null) {
                    c3.g = new B();
                    c3.g.a(h3, m2, c3.c, c3.d, c3.b, 0);
                }
                object = c3.g;
            }
        }
        if (object == null) {
            c c4 = null;
            n2 = 513;
            throw new y(513, null);
        }
        object.a();
        return c4.b;
    }

    public final int a() {
        return this.h;
    }

    public final int b() {
        return this.i;
    }
}

