/*
 * Decompiled with CFR 0.152.
 */
public class axz
extends axu {
    private final axu f;
    private final avh g;
    protected String a = "Options";
    private String h;

    public axz(axu axu2, avh avh2) {
        this.f = axu2;
        this.g = avh2;
    }

    @Override
    public void b() {
        int n2 = 0;
        this.a = bnq.a("options.sounds.title", new Object[0]);
        this.h = bnq.a("options.off", new Object[0]);
        this.n.add(new a(bpg.a.b(), this.l / 2 - 155 + n2 % 2 * 160, this.m / 6 - 12 + 24 * (n2 >> 1), bpg.a, true));
        n2 += 2;
        for (bpg bpg2 : bpg.values()) {
            if (bpg2 == bpg.a) continue;
            this.n.add(new a(bpg2.b(), this.l / 2 - 155 + n2 % 2 * 160, this.m / 6 - 12 + 24 * (n2 >> 1), bpg2, false));
            ++n2;
        }
        this.n.add(new avs(200, this.l / 2 - 100, this.m / 6 + 168, bnq.a("gui.done", new Object[0])));
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
    public void a(int n2, int n3, float f2) {
        this.c();
        this.a(this.q, this.a, this.l / 2, 15, 0xFFFFFF);
        super.a(n2, n3, f2);
    }

    protected String a(bpg bpg2) {
        float f2 = this.g.a(bpg2);
        if (f2 == 0.0f) {
            return this.h;
        }
        return (int)(f2 * 100.0f) + "%";
    }

    class a
    extends avs {
        private final bpg r;
        private final String s;
        public float o;
        public boolean p;

        public a(int n2, int n3, int n4, bpg bpg2, boolean bl2) {
            super(n2, n3, n4, bl2 ? 310 : 150, 20, "");
            this.o = 1.0f;
            this.r = bpg2;
            this.s = bnq.a("soundCategory." + bpg2.a(), new Object[0]);
            this.j = this.s + ": " + axz.this.a(bpg2);
            this.o = axz.this.g.a(bpg2);
        }

        @Override
        protected int a(boolean bl2) {
            return 0;
        }

        @Override
        protected void b(ave ave2, int n2, int n3) {
            if (!this.m) {
                return;
            }
            if (this.p) {
                this.o = (float)(n2 - (this.h + 4)) / (float)(this.f - 8);
                this.o = ns.a(this.o, 0.0f, 1.0f);
                ave2.t.a(this.r, this.o);
                ave2.t.b();
                this.j = this.s + ": " + axz.this.a(this.r);
            }
            bfl.c(1.0f, 1.0f, 1.0f, 1.0f);
            this.b(this.h + (int)(this.o * (float)(this.f - 8)), this.i, 0, 66, 4, 20);
            this.b(this.h + (int)(this.o * (float)(this.f - 8)) + 4, this.i, 196, 66, 4, 20);
        }

        @Override
        public boolean c(ave ave2, int n2, int n3) {
            if (super.c(ave2, n2, n3)) {
                this.o = (float)(n2 - (this.h + 4)) / (float)(this.f - 8);
                this.o = ns.a(this.o, 0.0f, 1.0f);
                ave2.t.a(this.r, this.o);
                ave2.t.b();
                this.j = this.s + ": " + axz.this.a(this.r);
                this.p = true;
                return true;
            }
            return false;
        }

        @Override
        public void a(bpz bpz2) {
        }

        @Override
        public void a(int n2, int n3) {
            if (this.p) {
                float f2 = this.r == bpg.a ? 1.0f : axz.this.g.a(this.r);
                axz.this.j.W().a(bpf.a(new jy("gui.button.press"), 1.0f));
            }
            this.p = false;
        }
    }
}

