/*
 * Decompiled with CFR 0.152.
 */
public class azz
extends axu {
    private static final avh.a[] a = new avh.a[]{avh.a.F, avh.a.J, avh.a.I, avh.a.L, avh.a.G, avh.a.H, avh.a.O, avh.a.K};
    private static final avh.a[] f = new avh.a[]{avh.a.M, avh.a.N};
    private final axu g;
    private final avh h;
    private String i;
    private String r;
    private int s;
    private boolean t = false;

    public azz(axu axu2, avh avh2) {
        this.g = axu2;
        this.h = avh2;
    }

    @Override
    public void b() {
        int n2 = 0;
        this.i = bnq.a("options.stream.title", new Object[0]);
        this.r = bnq.a("options.stream.chat.title", new Object[0]);
        for (avh.a a2 : a) {
            if (a2.a()) {
                this.n.add(new awj(a2.c(), this.l / 2 - 155 + n2 % 2 * 160, this.m / 6 + 24 * (n2 >> 1), a2));
            } else {
                this.n.add(new awe(a2.c(), this.l / 2 - 155 + n2 % 2 * 160, this.m / 6 + 24 * (n2 >> 1), a2, this.h.c(a2)));
            }
            ++n2;
        }
        if (n2 % 2 == 1) {
            ++n2;
        }
        this.s = this.m / 6 + 24 * (n2 >> 1) + 6;
        n2 += 2;
        for (avh.a a2 : f) {
            if (a2.a()) {
                this.n.add(new awj(a2.c(), this.l / 2 - 155 + n2 % 2 * 160, this.m / 6 + 24 * (n2 >> 1), a2));
            } else {
                this.n.add(new awe(a2.c(), this.l / 2 - 155 + n2 % 2 * 160, this.m / 6 + 24 * (n2 >> 1), a2, this.h.c(a2)));
            }
            ++n2;
        }
        this.n.add(new avs(200, this.l / 2 - 155, this.m / 6 + 168, 150, 20, bnq.a("gui.done", new Object[0])));
        avs avs2 = new avs(201, this.l / 2 + 5, this.m / 6 + 168, 150, 20, bnq.a("options.stream.ingestSelection", new Object[0]));
        avs2.l = this.j.Y().j() && this.j.Y().s().length > 0 || this.j.Y().w();
        this.n.add(avs2);
    }

    @Override
    protected void a(avs avs22) {
        avs avs22;
        if (!avs22.l) {
            return;
        }
        if (avs22.k < 100 && avs22 instanceof awe) {
            avh.a a2 = ((awe)avs22).c();
            this.h.a(a2, 1);
            avs22.j = this.h.c(avh.a.a(avs22.k));
            if (this.j.Y().k() && a2 != avh.a.M && a2 != avh.a.N) {
                this.t = true;
            }
        } else if (avs22 instanceof awj) {
            if (avs22.k == avh.a.G.c()) {
                this.j.Y().p();
            } else if (avs22.k == avh.a.H.c()) {
                this.j.Y().p();
            } else if (this.j.Y().k()) {
                this.t = true;
            }
        }
        if (avs22.k == 200) {
            this.j.t.b();
            this.j.a(this.g);
        } else if (avs22.k == 201) {
            this.j.t.b();
            this.j.a(new azy(this));
        }
    }

    @Override
    public void a(int n2, int n3, float f2) {
        this.c();
        this.a(this.q, this.i, this.l / 2, 20, 0xFFFFFF);
        this.a(this.q, this.r, this.l / 2, this.s, 0xFFFFFF);
        if (this.t) {
            this.a(this.q, (Object)((Object)a.m) + bnq.a("options.stream.changes", new Object[0]), this.l / 2, 20 + this.q.a, 0xFFFFFF);
        }
        super.a(n2, n3, f2);
    }
}

