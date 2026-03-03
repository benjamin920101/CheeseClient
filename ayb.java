/*
 * Decompiled with CFR 0.152.
 */
public class ayb
extends axu {
    private axu f;
    protected String a = "Video Settings";
    private avh g;
    private awd h;
    private static final avh.a[] i = new avh.a[]{avh.a.l, avh.a.f, avh.a.m, avh.a.i, avh.a.h, avh.a.g, avh.a.n, avh.a.d, avh.a.k, avh.a.o, avh.a.v, avh.a.w, avh.a.D, avh.a.P, avh.a.x, avh.a.R};

    public ayb(axu axu2, avh avh2) {
        this.f = axu2;
        this.g = avh2;
    }

    @Override
    public void b() {
        this.a = bnq.a("options.videoTitle", new Object[0]);
        this.n.clear();
        this.n.add(new avs(200, this.l / 2 - 100, this.m - 27, bnq.a("gui.done", new Object[0])));
        if (!bqs.P) {
            avh.a[] aArray = new avh.a[i.length - 1];
            int \u26032 = 0;
            for (avh.a a2 : i) {
                if (a2 == avh.a.x) break;
                aArray[\u26032] = a2;
                ++\u26032;
            }
            this.h = new awf(this.j, this.l, this.m, 32, this.m - 32, 25, aArray);
        } else {
            this.h = new awf(this.j, this.l, this.m, 32, this.m - 32, 25, i);
        }
    }

    @Override
    public void k() {
        super.k();
        this.h.p();
    }

    @Override
    protected void a(avs avs2) {
        if (!avs2.l) {
            return;
        }
        if (avs2.k == 200) {
            this.j.t.b();
            this.j.a(this.f);
        }
    }

    @Override
    protected void a(int n2, int n3, int n4) {
        \u2603 = this.g.aK;
        super.a(n2, n3, n4);
        this.h.b(n2, n3, n4);
        if (this.g.aK != \u2603) {
            avr avr2 = new avr(this.j);
            int \u26032 = avr2.a();
            int \u26033 = avr2.b();
            this.a(this.j, \u26032, \u26033);
        }
    }

    @Override
    protected void b(int n2, int n3, int n4) {
        \u2603 = this.g.aK;
        super.b(n2, n3, n4);
        this.h.c(n2, n3, n4);
        if (this.g.aK != \u2603) {
            avr avr2 = new avr(this.j);
            int \u26032 = avr2.a();
            int \u26033 = avr2.b();
            this.a(this.j, \u26032, \u26033);
        }
    }

    @Override
    public void a(int n2, int n3, float f2) {
        this.c();
        this.h.a(n2, n3, f2);
        this.a(this.q, this.a, this.l / 2, 5, 0xFFFFFF);
        super.a(n2, n3, f2);
    }
}

