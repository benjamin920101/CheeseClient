/*
 * Decompiled with CFR 0.152.
 */
public class ayj
extends axu {
    private static final avh.a[] h = new avh.a[]{avh.a.a, avh.a.b, avh.a.y};
    private axu i;
    protected String a = "Controls";
    private avh r;
    public avb f = null;
    public long g;
    private ayi s;
    private avs t;

    public ayj(axu axu2, avh avh2) {
        this.i = axu2;
        this.r = avh2;
    }

    @Override
    public void b() {
        this.s = new ayi(this, this.j);
        this.n.add(new avs(200, this.l / 2 - 155, this.m - 29, 150, 20, bnq.a("gui.done", new Object[0])));
        this.t = new avs(201, this.l / 2 - 155 + 160, this.m - 29, 150, 20, bnq.a("controls.resetAll", new Object[0]));
        this.n.add(this.t);
        this.a = bnq.a("controls.title", new Object[0]);
        int n2 = 0;
        for (avh.a a2 : h) {
            if (a2.a()) {
                this.n.add(new awj(a2.c(), this.l / 2 - 155 + n2 % 2 * 160, 18 + 24 * (n2 >> 1), a2));
            } else {
                this.n.add(new awe(a2.c(), this.l / 2 - 155 + n2 % 2 * 160, 18 + 24 * (n2 >> 1), a2, this.r.c(a2)));
            }
            ++n2;
        }
    }

    @Override
    public void k() {
        super.k();
        this.s.p();
    }

    @Override
    protected void a(avs avs22) {
        avs avs22;
        if (avs22.k == 200) {
            this.j.a(this.i);
        } else if (avs22.k == 201) {
            for (avb avb2 : this.j.t.aw) {
                avb2.b(avb2.h());
            }
            avb.b();
        } else if (avs22.k < 100 && avs22 instanceof awe) {
            this.r.a(((awe)avs22).c(), 1);
            avs22.j = this.r.c(avh.a.a(avs22.k));
        }
    }

    @Override
    protected void a(int n2, int n3, int n4) {
        if (this.f != null) {
            this.r.a(this.f, -100 + n4);
            this.f = null;
            avb.b();
        } else if (n4 != 0 || !this.s.b(n2, n3, n4)) {
            super.a(n2, n3, n4);
        }
    }

    @Override
    protected void b(int n2, int n3, int n4) {
        if (n4 != 0 || !this.s.c(n2, n3, n4)) {
            super.b(n2, n3, n4);
        }
    }

    @Override
    protected void a(char c2, int n2) {
        if (this.f != null) {
            if (n2 == 1) {
                this.r.a(this.f, 0);
            } else if (n2 != 0) {
                this.r.a(this.f, n2);
            } else if (c2 > '\u0000') {
                this.r.a(this.f, c2 + 256);
            }
            this.f = null;
            this.g = ave.J();
            avb.b();
        } else {
            super.a(c2, n2);
        }
    }

    @Override
    public void a(int n2, int n3, float f2) {
        this.c();
        this.s.a(n2, n3, f2);
        this.a(this.q, this.a, this.l / 2, 8, 0xFFFFFF);
        boolean bl2 = true;
        for (avb avb2 : this.r.aw) {
            if (avb2.i() == avb2.h()) continue;
            bl2 = false;
            break;
        }
        this.t.l = !bl2;
        super.a(n2, n3, f2);
    }
}

