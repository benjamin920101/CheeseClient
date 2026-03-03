/*
 * Decompiled with CFR 0.152.
 */
public class axn
extends axu
implements awx {
    private static final avh.a[] f = new avh.a[]{avh.a.c};
    private final axu g;
    private final avh h;
    private avs i;
    private awc r;
    protected String a = "Options";

    public axn(axu axu2, avh avh2) {
        this.g = axu2;
        this.h = avh2;
    }

    @Override
    public void b() {
        int n2 = 0;
        this.a = bnq.a("options.title", new Object[0]);
        for (avh.a a2 : f) {
            if (a2.a()) {
                this.n.add(new awj(a2.c(), this.l / 2 - 155 + n2 % 2 * 160, this.m / 6 - 12 + 24 * (n2 >> 1), a2));
            } else {
                awe awe2 = new awe(a2.c(), this.l / 2 - 155 + n2 % 2 * 160, this.m / 6 - 12 + 24 * (n2 >> 1), a2, this.h.c(a2));
                this.n.add(awe2);
            }
            ++n2;
        }
        if (this.j.f != null) {
            oj oj2 = this.j.f.aa();
            this.i = new avs(108, this.l / 2 - 155 + n2 % 2 * 160, this.m / 6 - 12 + 24 * (n2 >> 1), 150, 20, this.a(oj2));
            this.n.add(this.i);
            if (this.j.F() && !this.j.f.P().t()) {
                this.i.a(this.i.b() - 20);
                this.r = new awc(109, this.i.h + this.i.b(), this.i.i);
                this.n.add(this.r);
                this.r.b(this.j.f.P().z());
                this.r.l = !this.r.c();
                this.i.l = !this.r.c();
            } else {
                this.i.l = false;
            }
        }
        this.n.add(new avs(110, this.l / 2 - 155, this.m / 6 + 48 - 6, 150, 20, bnq.a("options.skinCustomisation", new Object[0])));
        this.n.add(new avs(8675309, this.l / 2 + 5, this.m / 6 + 48 - 6, 150, 20, "Super Secret Settings..."){

            @Override
            public void a(bpz bpz2) {
                bpy bpy2 = bpz2.a(bpg.g, bpg.e, bpg.f, bpg.h, bpg.d);
                if (bpy2 != null) {
                    bpz2.a(bpf.a(bpy2.c(), 0.5f));
                }
            }
        });
        this.n.add(new avs(106, this.l / 2 - 155, this.m / 6 + 72 - 6, 150, 20, bnq.a("options.sounds", new Object[0])));
        this.n.add(new avs(107, this.l / 2 + 5, this.m / 6 + 72 - 6, 150, 20, bnq.a("options.stream", new Object[0])));
        this.n.add(new avs(101, this.l / 2 - 155, this.m / 6 + 96 - 6, 150, 20, bnq.a("options.video", new Object[0])));
        this.n.add(new avs(100, this.l / 2 + 5, this.m / 6 + 96 - 6, 150, 20, bnq.a("options.controls", new Object[0])));
        this.n.add(new avs(102, this.l / 2 - 155, this.m / 6 + 120 - 6, 150, 20, bnq.a("options.language", new Object[0])));
        this.n.add(new avs(103, this.l / 2 + 5, this.m / 6 + 120 - 6, 150, 20, bnq.a("options.chat.title", new Object[0])));
        this.n.add(new avs(105, this.l / 2 - 155, this.m / 6 + 144 - 6, 150, 20, bnq.a("options.resourcepack", new Object[0])));
        this.n.add(new avs(104, this.l / 2 + 5, this.m / 6 + 144 - 6, 150, 20, bnq.a("options.snooper.view", new Object[0])));
        this.n.add(new avs(200, this.l / 2 - 100, this.m / 6 + 168, bnq.a("gui.done", new Object[0])));
    }

    public String a(oj oj2) {
        fa fa2 = new fa("");
        fa2.a(new fb("options.difficulty", new Object[0]));
        fa2.a(": ");
        fa2.a(new fb(oj2.b(), new Object[0]));
        return fa2.d();
    }

    @Override
    public void a(boolean bl2, int n2) {
        this.j.a(this);
        if (n2 == 109 && bl2 && this.j.f != null) {
            this.j.f.P().e(true);
            this.r.b(true);
            this.r.l = false;
            this.i.l = false;
        }
    }

    @Override
    protected void a(avs avs22) {
        avs avs22;
        Object object;
        if (!avs22.l) {
            return;
        }
        if (avs22.k < 100 && avs22 instanceof awe) {
            object = ((awe)avs22).c();
            this.h.a((avh.a)((Object)object), 1);
            avs22.j = this.h.c(avh.a.a(avs22.k));
        }
        if (avs22.k == 108) {
            this.j.f.P().a(oj.a(this.j.f.aa().a() + 1));
            this.i.j = this.a(this.j.f.aa());
        }
        if (avs22.k == 109) {
            this.j.a(new awy(this, new fb("difficulty.lock.title", new Object[0]).d(), new fb("difficulty.lock.question", new fb(this.j.f.P().y().b(), new Object[0])).d(), 109));
        }
        if (avs22.k == 110) {
            this.j.t.b();
            this.j.a(new axx(this));
        }
        if (avs22.k == 8675309) {
            this.j.o.d();
        }
        if (avs22.k == 101) {
            this.j.t.b();
            this.j.a(new ayb(this, this.h));
        }
        if (avs22.k == 100) {
            this.j.t.b();
            this.j.a(new ayj(this, this.h));
        }
        if (avs22.k == 102) {
            this.j.t.b();
            this.j.a(new axl(this, this.h, this.j.S()));
        }
        if (avs22.k == 103) {
            this.j.t.b();
            this.j.a(new awu(this, this.h));
        }
        if (avs22.k == 104) {
            this.j.t.b();
            this.j.a(new axy(this, this.h));
        }
        if (avs22.k == 200) {
            this.j.t.b();
            this.j.a(this.g);
        }
        if (avs22.k == 105) {
            this.j.t.b();
            this.j.a(new azo(this));
        }
        if (avs22.k == 106) {
            this.j.t.b();
            this.j.a(new axz(this, this.h));
        }
        if (avs22.k == 107) {
            this.j.t.b();
            object = this.j.Y();
            if (object.i() && object.A()) {
                this.j.a(new azz(this, this.h));
            } else {
                baa.a(this);
            }
        }
    }

    @Override
    public void a(int n2, int n3, float f2) {
        this.c();
        this.a(this.q, this.a, this.l / 2, 15, 0xFFFFFF);
        super.a(n2, n3, f2);
    }
}

